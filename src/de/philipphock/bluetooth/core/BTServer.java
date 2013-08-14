package de.philipphock.bluetooth.core;

import java.io.IOException;
import java.util.Vector;

import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

import de.philipphock.bluetooth.core.mvc.observable.BTServerStateObservable;
import de.philipphock.bluetooth.exceptions.ServerAlreadyStartedException;
import de.philipphock.bluetooth.service.BluetoothService;

public class BTServer implements Runnable {

	private final BTServerStateObservable serverState;
	// private ArrayBlockingQueue<Runnable> queue;

	private boolean active = true;
	private StreamConnection con;
	private StreamConnectionNotifier streamConnectionNotifier;
	private final BluetoothService btservice;

	// private ThreadPoolExecutor threadPool;
	private boolean alreadyStarted = false;

	private final Vector<BTHandler> handler;

	private final BTHandlerFactory handlerFactory; 
	private Thread listenThread;
	public BTServer(BTHandlerFactory handlerFactory, BluetoothService service) {
		this.handlerFactory = handlerFactory;
		this.btservice = service;
		this.serverState = new BTServerStateObservable();
		this.handler = new Vector<>();
		
	}

	
	public BTServerStateObservable getBTServerState() {
		return serverState;
	}

	public synchronized void init() throws  ServerAlreadyStartedException {
		if (alreadyStarted) {
			throw new ServerAlreadyStartedException();
		}
		// queue = new ArrayBlockingQueue<Runnable>(10);
		// threadPool = new ThreadPoolExecutor(1,1,10,TimeUnit.SECONDS,queue);
		try{
			LocalDevice.getLocalDevice().setDiscoverable(DiscoveryAgent.GIAC);			
		}catch (IOException e){
			e.printStackTrace();
		}
		
		serverState.notifyAllListener(BTServerStateObservable.SERVER_STARTED);
		alreadyStarted = true;

	}

	public void listen() {
		System.out.println("Server now listening");
		listenThread = new Thread(this);
		listenThread.setDaemon(true);
		listenThread.start();
		
	}

	@SuppressWarnings("unchecked")
	public void stop() {
		System.out.println("try to stop");
		// threadPool.shutdown();
		
		Vector<BTHandler> clonedHander =(Vector<BTHandler>) this.handler.clone();
		for(BTHandler h:clonedHander){
			h.stop();
		}
		active = false;
		
		try {
			streamConnectionNotifier.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//bt_unclosable_bug workaround
		serverState.notifyAllListener(BTServerStateObservable.SERVER_STOPPED);
	}

	@Override
	public void run() {
		serverState.notifyAllListener(BTServerStateObservable.SERVER_LISTENING);
		
		

		try {
			
			
			String uri = "btspp://localhost:" + new UUID(btservice.getServiceUUID(),false).toString()
					+ ";name=" + btservice.getServiceName()+";authenticate=false;encrypt=false;master=false";
			streamConnectionNotifier = (StreamConnectionNotifier) Connector
					.open(uri);
			System.out.println(uri);
			while (active) {
				try {
					serverState.notifyAllListener(BTServerStateObservable.SERVER_WAITING);
					System.out.println("waiting");
					con = (StreamConnection) streamConnectionNotifier.acceptAndOpen();
					System.out.println("accepted");
					serverState.notifyAllListener(BTServerStateObservable.SERVER_ACCEPT);
					BTHandler handler = handlerFactory.createHandler(this);
					this.handler.add(handler);
					handler.init(con);
					// threadPool.execute(handler); //for multiple connection
					// handles
					// handler.run();
					System.out.println("handler init");
					handler.run();
					System.out.println("handler done");
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			//TODO
			//actually here we would call serverState.notifyAllListener(BTServerStateObservable.SERVER_STOPPED);
			//but due to a bug, we cannot close the inputstream in BTHandler, therefore, the stream never stops until the remote client quits the connection
			//so we ignore this, by setting serverState.notifyAllListener(BTServerStateObservable.SERVER_STOPPED); in stop()
			//but keep in mind that the thread is still open
			//we will refer this as bt_unclosable_bug 
		} catch (IOException e1) {
			serverState.notifyAllListener(BTServerStateObservable.SERVER_ERROR, e1);
			return;
		}

	}

	public void handlerShutdown(BTHandler handler){
		//due to bt_unclosable_bug, this is called after the client quits the connection
		System.out.println("handler removed "+handler);
		this.handler.remove(handler);
	}
	
	public void broadCast(String s){
		for (BTHandler h:this.handler){
			h.send(s.getBytes());
		}
	}
}

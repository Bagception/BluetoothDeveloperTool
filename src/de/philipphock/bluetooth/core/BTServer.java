package de.philipphock.bluetooth.core;

import java.io.IOException;
import java.util.Vector;

import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
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
	}

	@Override
	public void run() {
		serverState.notifyAllListener(BTServerStateObservable.SERVER_LISTENING);
		
		

		try {
			streamConnectionNotifier = (StreamConnectionNotifier) Connector
					.open("btspp://localhost:" + btservice.getServiceUUID()
							+ ";name=" + btservice.getServiceName());

			while (active) {
				try {
					serverState.notifyAllListener(BTServerStateObservable.SERVER_WAITING);
					con = (StreamConnection) streamConnectionNotifier.acceptAndOpen();
					serverState.notifyAllListener(BTServerStateObservable.SERVER_ACCEPT);
					BTHandler handler = handlerFactory.createHandler(this);
					this.handler.add(handler);
					handler.init(con);
					// threadPool.execute(handler); //for multiple connection
					// handles
					// handler.run();

					handler.run();
					
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			serverState.notifyAllListener(BTServerStateObservable.SERVER_STOPPED);
		} catch (IOException e1) {
			serverState.notifyAllListener(BTServerStateObservable.SERVER_ERROR, e1);
			return;
		}

	}

	public void handlerShutdown(BTHandler handler){
		System.out.println("handler removed "+handler);
		this.handler.remove(handler);
	}
	
	public void broadCast(String s){
		for (BTHandler h:this.handler){
			h.send(s.getBytes());
		}
	}
}

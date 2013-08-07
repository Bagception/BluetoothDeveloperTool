package de.philipphock.bluetooth.core;

import java.io.IOException;

import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

import de.philipphock.bluetooth.core.mvc.BTServerState;
import de.philipphock.bluetooth.exceptions.ServerAlreadyStartedException;
import de.philipphock.bluetooth.service.BluetoothService;

public class BTServer implements Runnable {

	private final BTServerState serverState;
	// private ArrayBlockingQueue<Runnable> queue;

	private boolean active = true;
	private StreamConnection con;

	private final BluetoothService btservice;

	// private ThreadPoolExecutor threadPool;
	private boolean alreadyStarted = false;

	private BTHandler handler;

	public BTServer(BTHandler handler, BluetoothService service) {
		this.handler = handler;
		this.btservice = service;
		this.serverState = new BTServerState();

	}

	public BTServerState getBTServerState() {
		return serverState;
	}

	public void init() throws IOException, ServerAlreadyStartedException {
		if (alreadyStarted) {
			throw new ServerAlreadyStartedException();
		}
		// queue = new ArrayBlockingQueue<Runnable>(10);
		// threadPool = new ThreadPoolExecutor(1,1,10,TimeUnit.SECONDS,queue);
		LocalDevice.getLocalDevice().setDiscoverable(DiscoveryAgent.GIAC);
		alreadyStarted = true;

	}

	public void listen() {

		Thread listenThread = new Thread(this);
		listenThread.start();
	}

	public void stop() {
		// threadPool.shutdown();
	}

	@Override
	public void run() {
		StreamConnectionNotifier service;

		try {
			service = (StreamConnectionNotifier) Connector
					.open("btspp://localhost:" + btservice.getServiceUUID()
							+ ";name=" + btservice.getServiceName());

			serverState.notifyAllListener(BTServerState.SERVER_LISTENING);

			while (active) {
				try {
					con = (StreamConnection) service.acceptAndOpen();
					handler.init(con);
					// threadPool.execute(handler); //for multiple connection
					// handles
					// handler.run();

					handler.run();

				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			serverState.notifyAllListener(BTServerState.SERVER_STOPPED);
		} catch (IOException e1) {
			serverState.notifyAllListener(BTServerState.SERVER_ERROR, e1);
			return;
		}

	}

}

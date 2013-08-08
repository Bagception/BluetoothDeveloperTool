package de.philipphock.bluetooth.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.io.StreamConnection;



public abstract class BTHandler implements Runnable{
	protected StreamConnection con;
	protected boolean listening=true;
	private InputStream is;
	private OutputStream os;
	private static int instances=0;
	protected final BTServer server;
	public BTHandler(BTServer server){
		this.server = server;
		
	}
	public void init(StreamConnection con) throws IOException {
		this.con=con;
		this.is=con.openInputStream();
		this.os=con.openOutputStream();
		
 		
		
	}
	
	protected abstract void onShutdown();
	protected abstract void onInit();
	protected abstract void recv(byte[] b,int len);
	
	@Override
	public void run() {
		init();
		while (listening){
			byte[] recv= new byte[1024];
        	try {
        		int cnt = is.read(recv); 
				if (cnt==-1){
					listening=false;
					break;
				}
			this.recv(recv,cnt);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		shutdown();
		
	}
	
	private synchronized void init(){
		instances++;
		onInit();
	}
	
	private synchronized void shutdown(){
		instances--;
		
		if (instances<1){
			//Main.control.stateServerListening();
		}
		server.handlerShutdown(this);
		onShutdown();
	}
	
	

	public void send(byte[] b){
        try {
			os.write(b);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
	
	public synchronized void stop(){
		listening=false;
		try {
			
			is.close();
			con.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	
}

package de.philipphock.bluetooth.core.mvc;


import de.philipphock.bluetooth.core.BTHandler;



public  class BTObservableHandler extends BTHandler  {
	
	private final BTObservableHandlerHelper observable;
	
	public BTObservableHandler()  {
		this.observable = new BTObservableHandlerHelper();
	}
	
	public BTObservableHandlerHelper getObservable(){
		return observable;
	}
	
	@Override
	public void recv(byte[] b,int cnt) {
		String s = new String(b,0,cnt);
		observable.onNotify(this, 0, s);
	}
	
	
	public void sendString(String s) {
		send(s.getBytes());		
	}



}




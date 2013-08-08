package de.philipphock.bluetooth.coreImpl;


import de.philipphock.bluetooth.core.BTHandler;
import de.philipphock.bluetooth.core.BTServer;
import de.philipphock.bluetooth.core.mvc.observable.BTObservableHandlerHelper;



public  class BTObservableHandler extends BTHandler  {
	
	private final BTObservableHandlerHelper observable;
	
	public BTObservableHandler(BTServer server)  {
		super(server);
		this.observable = new BTObservableHandlerHelper();
	}
	
	public BTObservableHandlerHelper getObservable(){
		return observable;
	}
	
	@Override
	public void recv(byte[] b,int cnt) {
		String s = new String(b,0,cnt);
		observable.notifyAllListener(BTObservableHandlerHelper.RECV,s);
		
	}
	
	
	public void sendString(String s) {
		send(s.getBytes());		
	}

	
	@Override
	protected void onShutdown() {
		System.out.println("handler onShutdown");
		observable.notifyAllListener(BTObservableHandlerHelper.CONNECTION_LOST);
		observable.removeAllListeners();
		
	}
	
	@Override
	protected void onInit() {
		observable.notifyAllListener(BTObservableHandlerHelper.CONNECTION_ESTABLISEHD);
	}

}




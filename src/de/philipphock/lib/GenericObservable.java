package de.philipphock.lib;

import java.util.Vector;



public abstract class GenericObservable<L> {

	protected final Vector<L> listeners = new Vector<>();
	
	public void addListener(L listener){
		listeners.add(listener);
	}
	public void removeListener(L listener){
		listeners.remove(listener);
	}
	
	public void removeAllListeners(){
		listeners.clear();
	}
	
	public synchronized void notifyAllListener(int event,Object o){
		for(L l:listeners){
			this.onNotify(l,event,o);
		}
	}

	public synchronized void notifyAllListener(int event){
		notifyAllListener(event,null);
	}

	protected abstract void onNotify(L listener,int event, Object o);
		



}

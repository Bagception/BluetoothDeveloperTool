package de.philipphock.lib;

import java.io.IOException;
import java.io.InputStream;

public class WakeableInputStream extends InputStream{

	@Override
	public int read() throws IOException {
		synchronized (this) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}
	
	public void wakeup(){
		synchronized (this) {
			notifyAll();
		}
	}

}

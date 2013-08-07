package de.philipphock.bluetooth.main;

import de.philipphock.bluetooth.ui.BTServerUI;

public class Main {

	
	public static void main(String[] args) {
		Main.bootstrap();
	}
	
	
	public static void bootstrap(){
		new BTServerUI();
	}

}

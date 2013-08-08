package de.philipphock.bluetooth.main;

import de.philipphock.bluetooth.controller.BTServerController;

public class Main {

	
	public static void main(String[] args) {
		Main.bootstrap();
	}
	
	
	public static void bootstrap(){
		new BTServerController();
	}

}

package de.philipphock.bluetooth.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

import de.philipphock.bluetooth.service.BluetoothService;
import de.philipphock.bluetooth.service.BluetoothServiceEntity;

public class BluetoothService_TextLoader {
	private static final String SERVICE_DIR = System.getProperty("user.dir") + File.separator + "res" + File.separator + "BluetoothServices" + File.separator;
	
	
	public ArrayList<BluetoothService> loadServices(){
		ArrayList<BluetoothService> ret = new ArrayList<>();
		
		File bt_services = new File(SERVICE_DIR);
		String[] bt_services_filenames = bt_services.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".bts");
			}
		});
		
		
		for(String s:bt_services_filenames){		
			try {
				BufferedReader br = new BufferedReader(new FileReader(SERVICE_DIR+s));
				String uuid = br.readLine();
				br.close();
				BluetoothServiceEntity retI = new BluetoothServiceEntity(s,uuid);
				ret.add(retI);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		return ret;
		
	}
	
	


}

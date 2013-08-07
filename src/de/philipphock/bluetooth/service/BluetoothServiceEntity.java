package de.philipphock.bluetooth.service;

public class BluetoothServiceEntity implements BluetoothService{

	private final String name;
	private final String uuid;
	
	public BluetoothServiceEntity(String name, String uuid) {
		this.name = name;
		this.uuid = uuid;
	}
	
	@Override
	public String getServiceName() {
		return this.name;
	}

	@Override
	public String getServiceUUID() {
		return this.uuid;
	}
	
	@Override
	public String toString() {
		
		return name + " - " + uuid;
	}

}

package org.eclipse.californium.actinium.jscoap;

import java.util.HashMap;

public class HardwareResource {

	HashMap<String, Object> prop;
	
	String name = null;
	String manufacturer = null;
	String model = null;
	int id;
	boolean state;
	
	public void setProp(String key, Object value)
	{
			prop.put(key, value);	
	}
	
	public Object getProp(String key)
	{
		return prop.get(key);
	}
	
	public HardwareResource(int id)
	{
		this.id =id;
		prop=new HashMap<String, Object>();
	}

	public HardwareResource(String name, String manufacturer, String model, int id) {
		this.name = name;
		this.manufacturer = manufacturer;
		this.model = model;
		this.id = id;
		prop=new HashMap<String, Object>();
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

}

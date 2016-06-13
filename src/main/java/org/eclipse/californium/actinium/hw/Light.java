package org.eclipse.californium.actinium.hw;

import org.eclipse.californium.actinium.hw.resources.*;

public class Light {

	DeviceManufacturer r1;
	DeviceModel r2;

	public Light() {
		r1 = new DeviceManufacturer("Samsung");
		r2 = new DeviceModel("Candle");
	}

	public void display()
	{
		System.out.println("Lightprint");
	}

}

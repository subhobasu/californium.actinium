package org.eclipse.californium.actinium.jscoap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.json.*;
import java.util.Scanner;

public class PhysicalAPI {

	static HashMap<String, ArrayList<String>> light1_def = new HashMap<String, ArrayList<String>>();
	static HashMap<String, ArrayList<String>> light2_def = new HashMap<String, ArrayList<String>>();
	static HashMap<String, ArrayList<String>> selected;

	public static void def()
	{
		ArrayList<String> l = new ArrayList<String>();
		l.add(0, "setColor");;
		light1_def.put("setColor", l);
		//l.clear();
		ArrayList<String> l2 = new ArrayList<String>();
		l2.add(0, "setColor2");
		l2.add(1, "dummy");
		light2_def.put("setColor", l2);
	}

	Object n;
	//String s = "newLight";
	String function_name = null;
	String learn = null;
	Class<?> c;
	LinkedList<HardwareResource> hardwareResources;

	public PhysicalAPI(String s) throws ClassNotFoundException, FileNotFoundException, JSONException {
		
		//JSON dictionary
		String json_str = null;
		//JSONObject json_obj = new JSONObject(new FileReader("/home/subho/Subho/Work/Actinium/californium.actinium/src/main/java/org/eclipse/californium/actinium/jscoap/dictionary.json"));
		JSONObject json_obj = new JSONObject(new Scanner(new File("/home/subho/Subho/Work/Actinium/californium.actinium/src/main/java/org/eclipse/californium/actinium/jscoap/dictionary.json")).useDelimiter("\\Z").next());
		try {
			//json_str = (String)json_obj.getString("name");
			JSONObject obj1=json_obj.getJSONObject("def1");
			//System.out.println("Classname:"+obj1.getString("class"));
			JSONArray arr = obj1.getJSONArray("functions");
			if(arr.getJSONObject(0).has("setColor"))
			{//System.out.println("FunctionName:"+arr.getJSONObject(0).getString("setColor"));
			//json_obj.append("addr", "asdf");
			//System.out.println(json_obj.length());
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(json_str);
		
		
		def();
		paramColor = new Class[1];
		paramColor[0] = Color.class;
		Random random = new Random();
		int r=random.nextInt(2);
		if (r==0)
			{n = new newLight(); System.out.println("Random:0");}
		else if(r==1)
			{n = new newLight2(); System.out.println("Random:1");}
/*		
		if(s.equalsIgnoreCase("light1"))
			n = new newLight();
		else if(s.equalsIgnoreCase("light2"))
			n = new newLight2();
*/
		for(int counter=1; counter<=json_obj.length(); counter++)
		{
			System.out.println("Counter:"+counter);
			String ss = json_obj.getJSONObject("def"+counter).getString("class");
			System.out.println(ss);
			//System.out.println(Class.forName(ss).getClass());
			if(/*n instanceof newLight*/Class.forName("org.eclipse.californium.actinium.jscoap."+ss).isInstance(n))
			{
				c = Class.forName("org.eclipse.californium.actinium.jscoap."+json_obj.getJSONObject("def"+counter).getString("class"));
				//c=newLight.class;
				paramColor = new Class[1];
				paramColor[0] = Color.class;
				function_name = json_obj.getJSONObject("def"+counter).getJSONArray("functions").getJSONObject(0).getString("setColor");
				System.out.println("Function_name:"+function_name);
				break;
			}
		}
	
/*		
		if (n instanceof newLight)
			learn="light1";
		else if(n instanceof newLight2)
			learn="light2";
		switch (learn) {
		case "light1":
			//n = new newLight();
			c = newLight.class;
			selected = light1_def;
			paramColor = new Class[1];
			paramColor[0] = Color.class;
			break;
		case "light2":
			//n = new newLight2();
			c = newLight2.class;
			selected = light2_def;
			paramColor = new Class[1];
			paramColor[0] = Color.class;
			//paramColor[1] = String.class;
			break;
		}
*/		
		// n = new newLight();
		hardwareResources = new LinkedList<HardwareResource>();
	}

	Class[] paramColor;

	public void setColor(String color) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Method method;
		
		//int size = selected.get("setColor").size();
		//Object[] rest = new Object[size];
		//for(int counter=1; counter<size; counter++)
		//{
			//rest[counter-1]=selected.get("setColor").get(counter);
		//}
		
		switch (color) {
		case "BLUE": // (newLight.class.cast(n)).setColor(Color.BLUE);
			//method = c.getDeclaredMethod(selected.get("setColor").get(0), paramColor);
			method = c.getDeclaredMethod(function_name, paramColor);
			//method.invoke(c.cast(n), Color.BLUE, rest);
			method.invoke(c.cast(n), Color.BLUE);
			break;
		case "RED": // (newLight.class.cast(n)).setColor(Color.RED);
			method = c.getDeclaredMethod(function_name, paramColor);
			method.invoke(c.cast(n), Color.RED);
			break;
		case "GREEN": // (newLight.class.cast(n)).setColor(Color.GREEN);
			method = c.getDeclaredMethod(function_name, paramColor);
			method.invoke(c.cast(n), Color.GREEN);
			break;
		case "WHITE": // (newLight.class.cast(n)).setColor(Color.WHITE);
			method = c.getDeclaredMethod(function_name, paramColor);
			method.invoke(c.cast(n), Color.WHITE);
			break;
		}
	}

	public String getColor() {
		return (newLight.class.cast(n)).getColor();
	}

	// API func
	public void switchOnOff(int id, boolean state) throws Exception {
		int index = findHardwareResource(id);
		if (index == -1) {
			throw new Exception("Hardware not found");
		} else {
			hardwareResources.get(index).setState(state);
		}
	}

	// API func
	public void setHardwareValue(int id, String key, Object value) {
		HardwareResource h = getHardwareResource(id);

		h.setProp(key, value);

	}

	// API func
	public Object getHardwareValue(int id, String key) {
		HardwareResource h = getHardwareResource(id);
		return h.getProp(key);
	}

	// API func
	public void addHardwareResource(HardwareResource h) {
		hardwareResources.add(h);
	}

	public HardwareResource getHardwareResource(int id) {
		int index = findHardwareResource(id);
		if (index == -1) {
			return null;
		} else {
			return hardwareResources.get(index);
		}
	}

	public int findHardwareResource(int id) {
		for (int l = 0; l < hardwareResources.size(); l++) {
			if (hardwareResources.get(l).getId() == id)
				return l;
		}
		return -1;
	}

}

//load("nashorn:mozilla_compat.js");
//importPackage(java.awt);
//var light = new LightGUI();
//light.ctrl();
//var Class1 = Java.type("Class1");
//var class1 = new Class1();

var res = new JavaScriptResource("light");

var state = true;

var phy = new PhysicalAPI("light1");
phy.setColor("BLUE");

var hwManufacturer = new HardwareResource(10);
phy.addHardwareResource(hwManufacturer);
phy.setHardwareValue(10, "Name", "Manufacturer");
phy.setHardwareValue(10,"Manufacturer", "Samsung");


var hwModel = new HardwareResource(9);
phy.addHardwareResource(hwModel);
phy.setHardwareValue(9, "Name", "Model");
phy.setHardwareValue(9,"Model", "Candle");


var color = new HardwareResource(8);
phy.addHardwareResource(color);
phy.setHardwareValue(8, "Name", "Color");
phy.setHardwareValue(8, "Color", "GREEN");


var brightness = new HardwareResource(7);
phy.addHardwareResource(brightness);
phy.setHardwareValue(8, "Name", "Brightness");
phy.setHardwareValue(8, "Brightness", 60);




////////////////////////////////////////

var hw = new HardwareResource("pipe", "TMT", "X1", "11");
phy.addHardwareResource(hw);
phy.setHardwareValue(11,"length", 100);
phy.setHardwareValue(11,"cross-section", 10);


//var color = new Color(10,10,10);


phy.switchOnOff(11, true);

res.onget = function(request){  //on-off switch
	print('Hi');
        var v = new Light();
	v.display();
	//request.respond(69, "blue_light:"+state);
	toggleSwitch();
	print(state);
	//phy.setColor("WHITE");
	request.respond(69, phy.getColor());
	
};

res.onpost = function(request) //decrease brightness by 5 each time...
{
	phy.setHardwareValue(8, "Brightness", phy.getHardwareValue(8, "Brightness")-5);
	request.respond(69, "Brightness level: "+phy.getHardwareValue(8, "Brightness"));
	print(phy.getHardwareValue(8, "Brightness"));
	print(request.getRequestText());
	phy.setColor(request.getRequestText());
	
};


function toggleSwitch()
{
  if(state == true)
     state = false;
  else
     state = true;
  phy.switchOnOff(11, state);
}

app.root.add(res);

// a handler for GET requests to "/"
app.root.onget = function(request) {
// that returns CoAP's "2.05 Content" with payload
		request.respond(2.05, "Hello world");
	};

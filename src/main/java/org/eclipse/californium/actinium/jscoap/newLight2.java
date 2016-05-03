//import DeviceFrame;
//import DevicePanel;

package org.eclipse.californium.actinium.jscoap;

import javax.swing.*;
import java.awt.*;

public class newLight2 extends light{

	private static DevicePanel led;
	private static Color color = Color.white;
	
	public newLight2()
	{
		                led = new DevicePanel(getClass().getResourceAsStream("bulb2.png"), 240, 400) {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(super.img, 0, 0, getWidth(), getHeight(), this);

                        if (/*PowerRelay.getRelay()*/ true) {

                                Graphics2D g2 = (Graphics2D) g;

                                Color[] gradient = { new Color(color.getRed(), color.getGreen(), color.getBlue(), 240), new Color(color.getRed(), color.getGreen(), color.getBlue(), 200), new Color(color.getRed(), color.getGreen(), color.getBlue(), 0) };
                                float[] fraction = { 0.0f, 0.5f, 1.0f };
                                RadialGradientPaint p = new RadialGradientPaint(120, 120, 120, fraction, gradient);
                                g2.setPaint(p);
                                g2.fillOval(0, 0, 240, 240);
                        }
                    }

                };

		new DeviceFrame(led).setVisible(true);


	}

	public static void setColor2(Color c) {
                color = c;
                led.repaint();
                //System.out.println(dummy);
        }

	public String getColor2()
	{
		return color.toString();
	}

	/*public static void main(String []args){
		newLight n = new newLight();
		n.setColor(Color.RED);
		for(int i=0; i<=10000; i++)
		{
		}
		n.setColor(Color.BLUE);
		
	}*/
}

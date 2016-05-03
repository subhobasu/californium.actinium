import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class LightGUI extends JFrame{
	
	JPanel panel;
	
	public LightGUI()
	{
		//setLayout(new FlowLayout());
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBackground(Color.RED);
		add(panel);
		
	}
	
	public static void main(String[] args) {
		ctrl();
	}
	
	public static void ctrl()
	{
		LightGUI gui = new LightGUI();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(200, 150);
		gui.setVisible(true);
		gui.setTitle("LightGUI");
	}

}

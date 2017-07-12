import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JTextField;


public class ServerStarter {

	public static void main(String[] args) {

		new ServerStarter();
	}

	public ServerStarter()
	{
		JTextField t1;
		JLabel l1;
		JButton b1;
		JFrame Frame1;
		Frame1=new JFrame("Start Server");
		Frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame1.setBounds(500, 200, 400, 300);
		Frame1.getContentPane().setBackground(Color.decode("#07A8D4"));
		Frame1.setVisible(true);
		Frame1.repaint();
		//
		t1=new JTextField("");
		t1.setFocusable(true);
		t1.setBounds(10, 10, 100, 25);
		Frame1.add(t1);
		l1=new JLabel("Server Port");
		l1.setBounds(120, 10, 100, 25);
		Frame1.add(l1);	
		b1=new JButton("Start Server");
		b1.setBounds(100, 100,150, 25);
		Frame1.add(b1);
		t1.requestFocus();
		Frame1.repaint();

		b1.addActionListener(new ActionListener() 
		{
			//@Override
			public void actionPerformed(ActionEvent e) 
			{
				int i1=Integer.parseInt(t1.getText());
				try {
					Frame1.setVisible(false);
					new Server(i1);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});


	}

}

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ClientStarter {

	public static void main(String[] args) {
		new ClientStarter();
	}

	public ClientStarter() {
		JTextField t1, t2;
		JLabel l1, l2;
		JButton b2, b3;
		JFrame Frame;
		Frame = new JFrame("Start Client");
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.setBounds(500, 200, 400, 300);
		Frame.setLayout(null);
		Frame.getContentPane().setBackground(Color.decode("#07A8D4"));
		Frame.setVisible(true);
		t1 = new JTextField("localhost");
		t2 = new JTextField("");
		
		t1.setBounds(120, 10, 100, 25);
		t2.setBounds(120, 40, 100, 25);
		Frame.add(t1);
		Frame.add(t2);
		t2.requestFocus();
		l1 = new JLabel("Client Name");
		l2 = new JLabel("Client Port");
		l1.setBounds(10, 10, 100, 25);
		l2.setBounds(10, 40, 100, 25);
		Frame.add(l1);
		Frame.add(l2);
		b2 = new JButton("Start Client");
		b3 = new JButton("Browse File");
		b2.setBounds(10, 70, 100, 25);
		b3.setBounds(10, 160, 100, 25);
		Frame.add(b2);
		// Frame.add(b3);

		Frame.repaint();

		b2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String s1 = t1.getText();
				int i1 = Integer.parseInt(t2.getText());
				try {
					Frame.setVisible(false);
					new Client(s1, i1);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}

}

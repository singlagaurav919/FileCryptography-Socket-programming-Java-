import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Server{

	JFrame frame1;
	JButton b4,b5;
	ServerSocket MySock;
	Socket Sock;
	InputStream is;
	OutputStream dos;
	String key;
	public Server(int i1) 

	{

		try{

			frame1=new JFrame();
			frame1.setBounds(500, 200, 400, 300);
			frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame1.getContentPane().setBackground(Color.decode("#07A8D4"));
			frame1.setLayout(null);
			//frame1.setDefaultLookAndFeelDecorated(true);
			frame1.setVisible(true);

			b4=new JButton("Stop Server");
			b4.setBounds(120, 130, 150, 25);

			b5=new JButton("Decrypt File");
			b5.setBounds(120, 100, 150, 25);

			JLabel l1=new JLabel("Enter key");
			l1.setBounds(10, 10, 100, 25);
			JTextField t1=new JTextField("dsproject");
			t1.setBounds(120, 10, 100, 25);

			@SuppressWarnings("unused")
			JButton b=new JButton("Choose File");

			frame1.add(l1);
			frame1.add(t1);
			frame1.add(b4);
			frame1.add(b5);
			//frame1.setVisible(true);
			//frame1.repaint();
			JOptionPane.showMessageDialog(null, "Server Started\nRun Client");
			MySock=new ServerSocket(i1);//opening server socket
			Sock=MySock.accept();//listening to client enabled i.e.establishing a connection

			is=Sock.getInputStream();//listening from client	

			dos=new FileOutputStream("decrypted.txt");//writing received input to file
			frame1.setVisible(true);		
		    frame1.repaint();

			b4.addActionListener(new ActionListener() 
			{
				//@Override
				public void actionPerformed(ActionEvent e) 
				{

					try {

						//is.close();
						//dos.close();
						Sock.close();//closing connection but not server socket
						MySock.close();
						JOptionPane.showMessageDialog(null, "Server Stopped");
						System.exit(0);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});

			b5.addActionListener(new ActionListener() 
			{
				//@Override
				public void actionPerformed(ActionEvent e) 
				{
					try {
						key=t1.getText();
						DESKeySpec dks=new DESKeySpec(key.getBytes());
						SecretKeyFactory skf=SecretKeyFactory.getInstance("DES");
						SecretKey desKey = skf.generateSecret(dks);
						Cipher cipher=Cipher.getInstance("DES");

						cipher.init(Cipher.DECRYPT_MODE, desKey);
						@SuppressWarnings("resource")
						CipherOutputStream cos=new CipherOutputStream(dos,cipher);


						byte[] bytes = new byte[16*1024];
						int count;//copying stream in java
						while ((count = is.read(bytes)) > 0) {
							cos.write(bytes, 0, count);
						}

						JOptionPane.showMessageDialog(null, "Filed Decrypted and saved");

					} catch (InvalidKeyException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvalidKeySpecException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchPaddingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});

		}
		catch(Exception e){
			e.printStackTrace();

		}

	}
	
	public static void main(String[] args) 
	{

	}


}


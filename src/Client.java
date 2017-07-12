import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Client {

	File files;
	String key;
	public Client(String s1, int i1) 
	{
	
		
		//fc.setBounds(10, 10, 490, 490);
		JFrame frame2=new JFrame("Send File");
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setBounds(500, 300, 300,200);

		frame2.getContentPane().setBackground(Color.decode("#07A8D4"));
		frame2.setLayout(null);
		JLabel l1=new JLabel("Enter key");
		l1.setBounds(10, 20, 80, 25);
		JTextField t1=new JTextField("dsproject");
		t1.setBounds(100, 20, 100, 25);
		JButton b=new JButton("Choose File");
		b.setBounds(100, 87, 100, 25);
		JButton  b1=new JButton("Encrypt");
		b1.setBounds(100, 120, 100, 25);
			
		frame2.add(b);
		frame2.add(b1);
		frame2.add(t1);
		frame2.add(l1);
		frame2.setVisible(true);
		//frame2.add(b1);
		//frame2.add(fc);
		try
		{

			Socket Sock1=new Socket(s1,i1);//opening client socket
			JOptionPane.showMessageDialog(null,"Client Started");			
		
			b.addActionListener(new ActionListener() 
			{

				public void actionPerformed(ActionEvent e) 
				{

					//*****Choosing file to send******//
					
					JFileChooser fc=new JFileChooser();
					//fc.showOpenDialog(null);
					int result = fc.showOpenDialog(frame2);
					
					if (result == JFileChooser.APPROVE_OPTION) 
					{
						files=fc.getSelectedFile();
					}

				}
			});

			b1.addActionListener(new ActionListener() 
			{
				//@Override
				public void actionPerformed(ActionEvent e) 
				{
					try {
						
					    
						//creating a pathway for sending to server
						@SuppressWarnings("resource")
						InputStream is=new FileInputStream(files);
						@SuppressWarnings("resource")
						FileOutputStream fos=new FileOutputStream("encrypted.txt");
						key=t1.getText();
						
						//encryption
						DESKeySpec dks=new DESKeySpec(key.getBytes());
						SecretKeyFactory skf=SecretKeyFactory.getInstance("DES");
						SecretKey desKey = skf.generateSecret(dks);
						Cipher cipher=Cipher.getInstance("DES");

						cipher.init(Cipher.ENCRYPT_MODE, desKey);
						@SuppressWarnings("resource")
						CipherInputStream cis=new CipherInputStream(is,cipher);

						byte[] bytes = new byte[16*1024];
						int numBytes;
						while((numBytes = cis.read(bytes))!=-1)
						{
							fos.write(bytes, 0, numBytes);
						}
						//*******Encryption Done*******//Encrypted.txt


						//*****reading encrypted file******//
						is=new FileInputStream("encrypted.txt");
						
						OutputStream dos=Sock1.getOutputStream();
						//*****Sending encrypted file to server*****//
						byte[] bytes1 = new byte[16 * 1024];
						int count;
						while ((count = is.read(bytes1)) > 0) 
						{
							dos.write(bytes1, 0, count);
						}
						JOptionPane.showMessageDialog(null, "file Sent");
						
						
						//Closing Socket
						Sock1.close();
						frame2.setVisible(false);
						
					} catch (InvalidKeyException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
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

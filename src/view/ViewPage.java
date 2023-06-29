package view;

import javax.swing.*;

import controller.Add_data;
import controller.Delete;
import controller.History;
import controller.History2;
import controller.Search;
import controller.Sell;
import controller.Show;
import controller.Update;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ViewPage {

	public static void main(String[] args) {
	
		
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("The UNO PET SHOP");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			// Set the size of the JFrame
			// frame.setSize(400, 300);
			frame.setBounds(150, 100, 1600, 900);

			// Create a JPanel with a background image
			JPanel panel = new JPanel() {
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					Image image = new ImageIcon("E:\\petshop images\\ttt.jpg").getImage();
					g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
				}
			};

			panel.setLayout(null); // important to put
			///////////////////////////////////////////////////////////
			
			Add_data addd= new Add_data();
			
			JButton ad = new JButton("Add data");
			ad.setFont(new Font("Algerian", Font.BOLD, 18));
			ad.setBounds(120, 120, 150, 40);
			
		
			ad.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
				
					addd.add();
			
					
					
				}
			});

			
			
			
			////////////////////////////////////////////////////////////
             Show sw=new Show();
			JButton sh = new JButton("Show data");
			sh.setFont(new Font("Algerian", Font.BOLD, 18));
			sh.setBounds(300, 120, 150, 40);

			sh.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
                sw.sho();
				}
			});
			////////////////////////////////////////////////////////////
			 Update up1=new Update();
			JButton up = new JButton("Update data");
			up.setFont(new Font("Algerian", Font.BOLD, 18));
			up.setBounds(480, 120, 180, 40);

			up.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
           up1.update();
				}
			});
			////////////////////////////////////////////////////////////
			Delete d=new Delete();
			JButton del = new JButton("Delete data");
			del.setFont(new Font("Algerian", Font.BOLD, 18));
			del.setBounds(690, 120, 170, 40);

			del.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
                     d.del();
				}
			});
			////////////////////////////////////////////////////////////
			Search sc=new Search();
			JButton search = new JButton("Search data");
			search.setFont(new Font("Algerian", Font.BOLD, 18));
			search.setBounds(1090, 120, 170, 40);

			search.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
             sc.search();
               
				}
			});
			///////////////////////////////////////////////////////////
			Sell s=new Sell();
			JButton sell = new JButton("Sell data");
			sell.setFont(new Font("Algerian", Font.BOLD, 18));
			sell.setBounds(1290, 120, 170, 40);

			sell.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
             s.sel();
               
				}
			});
			
			///////////////////////////////////////////////////////////
             History h=new History();
			
			JButton hi = new JButton("Item History");
			hi.setFont(new Font("Algerian", Font.BOLD, 18));
			hi.setBounds(120, 220, 180, 40);
			
		
			hi.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
				
					h.his();
			
					
					
				}
			});
			///////////////////////////////////////////////////////////
			 History2 h1=new History2();
				
				JButton hi1 = new JButton("Sell History");
				hi1.setFont(new Font("Algerian", Font.BOLD, 18));
				hi1.setBounds(320, 220, 180, 40);
				
			
				hi1.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
					
						try {
							h1.his2();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				
						
						
					}
				});
			///////////////////////////////////////////////////////////
			QrCode cq=new QrCode();
    		JButton qr = new JButton("QR Code");
			qr.setFont(new Font("Algerian", Font.BOLD, 18));
			qr.setBounds(890, 120, 170, 40);
			
			qr.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					cq.qrcod();
				}
			});
			panel.add(ad);
		    panel.add(qr);
			panel.add(sh);
			panel.add(up);
			panel.add(del);
			panel.add(search);
			panel.add(sell);
			panel.add(hi);
			panel.add(hi1);
		
			frame.add(panel);
			frame.setVisible(true);
		});
	}

	
}
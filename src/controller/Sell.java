package controller;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Sell{
public static void sel() {
	
	SwingUtilities.invokeLater(() -> {

		JFrame frame3 = new JFrame("SELL PETS");
	//	frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	frame6.setSize(500, 800);
		frame3.setBounds(350, 200, 950, 900);
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image image = new ImageIcon("E:\\petshop images\\pp.jpg").getImage();
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

			}
		};

		panel.setLayout(null);
		
		
		JLabel dateLabell = new JLabel();
        dateLabell.setFont(new Font("Algerian", Font.BOLD, 25));
      //  dateLabell.setBounds(550, 300, 300, 50);
        panel.add(dateLabell);

        // Create a Timer to update the date label every second
        Timer timer = new Timer(1000, (e) -> {
            // Get the current date
            Date currentDate = new Date();

            // Create a date formatter to format the date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // Format the current date
            String formattedDate = dateFormat.format(currentDate);

            // Update the date label
            dateLabell.setText(formattedDate);
        });

        // Start the timer
        timer.start();

        JLabel c = new JLabel("CUSTOMER NAME");
		c.setFont(new Font("Algerian", Font.BOLD, 22));
		
		c.setBounds(60, 10, 300, 40);
		JTextField t = new JTextField();
		t.setFont(new Font("Algerian", Font.BOLD, 17));
		t.setBounds(60, 50, 200, 40);

        
		JLabel l1 = new JLabel("PRODUCT NAME");
		l1.setFont(new Font("Algerian", Font.BOLD, 22));
		
		l1.setBounds(60, 90, 300, 40);
		JTextField t1 = new JTextField();
		t1.setFont(new Font("Algerian", Font.BOLD, 17));
		t1.setBounds(50, 130, 300, 40);

		JLabel l2 = new JLabel("PRODUCT QUANTITY");
		l2.setFont(new Font("Algerian", Font.BOLD, 22));
		
		l2.setBounds(60, 170, 300, 40);
		JTextField t2 = new JTextField();
		t2.setFont(new Font("Algerian", Font.BOLD, 17));
		t2.setBounds(50, 210, 300, 40);

		JLabel l3 = new JLabel("PER PRODUCT RATE");
		l3.setFont(new Font("Algerian", Font.BOLD, 22));
		

		l3.setBounds(60, 250, 300, 40);
		JTextField t3 = new JTextField();
		t3.setFont(new Font("Algerian", Font.BOLD, 17));
		t3.setBounds(50, 280, 300, 40);

		/*
		 * JTextField t4 = new JTextField(); t4.setFont(new
		 * Font("Algerian", Font.BOLD, 17)); t4.setBounds(50, 360,
		 * 300, 40);
		 */

		JButton totalb = new JButton("TOTAL PRICE");
		totalb.setFont(new Font("Algerian", Font.BOLD, 22));
		totalb.setBounds(60, 350, 200, 40);
		
		JTextField t4 = new JTextField();
		t4.setFont(new Font("Algerian", Font.BOLD, 17));
		t4.setBounds(300, 350, 300, 40);

		

		t4.setFont(new Font("Algerian", Font.BOLD, 20));

		totalb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int productq = Integer.parseInt(t2.getText());
				int rate = Integer.parseInt(t3.getText());
				t4.setText("TOTAL AMT:" + (productq * rate));

			}
		});

		JButton sell = new JButton("SELL");
		sell.setFont(new Font("Algerian", Font.BOLD, 22));
		sell.setBounds(50, 400, 200, 40);

		sell.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {


				String coname=t.getText();
				String name = t1.getText();

				int productq = Integer.parseInt(t2.getText());
				int rate = Integer.parseInt(t3.getText());
				String date =dateLabell.getText();
				

				// int Price = Integer.parseInt(t4.getText());
		     //   int totalQuantity = productq + (newQuantity * rate);

				Connection con = null;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/petshop", "root",
							"abc123");
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				Statement st = null;
				try {
					st = con.createStatement();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
////////////////////////////////////////////////////////////////
				
				
				try {
          //          String query = "INSERT INTO inv (productname, productQunatity, Perproductrate, Totalprice,cname) VALUES (?, ?, ?, ?)";

					int a = st.executeUpdate("Insert into  pet22 values('" +coname + "','" + name + "',"  +productq + "," +  rate + ",'"+ date+"')");
					
					/*
					PreparedStatement ps = con.prepareStatement("INSERT INTO inv2 (cname) VALUES (?)");
					
					ps.setString(1, coname);

					//ps.setString(6, coname);

					int a = ps.executeUpdate();*/


				}catch(SQLException e1){
				    JOptionPane.showMessageDialog(sell, "ERROR: " + e1.getMessage());

					JOptionPane.showMessageDialog(sell, "DATA NOT INSERTED");
					
				} catch(NumberFormatException f){
					
				    JOptionPane.showMessageDialog(sell, "ERROR: " + f.getMessage());
					JOptionPane.showMessageDialog(sell, "DATA NOT INSERTED");

					
				}
				
				//////////////////////////////////////////////////////
				 int currentQuantity = 0;
                 try {
                     ResultSet rs = st.executeQuery("SELECT proQty FROM pet1 WHERE petbreedName = '" + name + "'");
                     if (rs.next()) {
                         currentQuantity = rs.getInt("proQty");
                     }
                 } catch (SQLException e1) {
                     e1.printStackTrace();
                 }

                 // Calculate the updated quantity
                 int updatedQuantity = currentQuantity - productq;
                 //////////////////////////////////////////////////////////
               /*  int currentrate = 0;
                 try {
                     ResultSet rs = st.executeQuery("SELECT productQunatity FROM inv WHERE productname = '" + name + "'");
                     if (rs.next()) {
                         currentrate = rs.getInt("Perproductrate");
                     }
                 } catch (SQLException e1) {
                     e1.printStackTrace();
                 }

                 // Calculate the updated quantity
                 int updatedrate = currentrate + rate;
*/
                 ///old table name

				
				try {
					int b = st.executeUpdate("UPDATE pet1 SET proQty = " +  updatedQuantity 
							+ "  WHERE petbreedName = '" + name + "'");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					int d = st.executeUpdate("UPDATE pet1 SET price = " + rate
							+ "  WHERE petbreedName = '" + name + "'");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					int c = st.executeUpdate("UPDATE pet1 SET total = " + updatedQuantity  * rate
							+ "  WHERE petbreedName = '" + name + "'");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					int d = st.executeUpdate("UPDATE pet1 SET date = '" + date
							+ "'  WHERE petbreedName = '" + name + "'");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				

				JOptionPane.showMessageDialog(sell, "PRODUCT SOLD");

			}
		});

		JButton clear = new JButton("CLEAR");
		clear.setFont(new Font("Algerian", Font.BOLD, 22));
		clear.setBounds(300, 400, 200, 40);
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				t1.setText("");
				t2.setText("");
				t3.setText("");
				t.setText("");
				
			}
		});
        panel.add(c);
        panel.add(t);
		panel.add(t1);
		panel.add(t2);
	
		panel.add(t3);
		panel.add(t4);
	

		panel.add(l1);
		panel.add(l2);
		panel.add(l3);
		panel.add(totalb);

		panel.add(sell);
		panel.add(clear);
	//	frame3.setSize(500, 800);
	
		frame3.add(panel);

		frame3.setVisible(true);
	});



}
}

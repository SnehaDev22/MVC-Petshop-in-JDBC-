package view;

import javax.swing.*;
import java.awt.*;

public class QrCode {

    public static void qrcod() {
        SwingUtilities.invokeLater(() -> {
        	
            JFrame frame3 = new JFrame("QR CODE");
          //  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		
            frame3.setBounds(350, 200, 700, 900);

            // Set the size of the JFrame
         //   frame.setSize(400, 300);

            // Create a JPanel with a background image
            JPanel panel3 = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Image image = new ImageIcon("E:\\petshop images\\qr.jpeg").getImage();
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                }
            };

            // Set the layout manager for the panel
            panel3.setLayout(null);

          
    		
            

         
            // Add the panel to the frame
            frame3.add(panel3);

            // Set the frame visible
            frame3.setVisible(true);
        });
    }
}
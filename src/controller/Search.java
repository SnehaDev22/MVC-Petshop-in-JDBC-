 package controller;

import java.awt.Color;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import controller.Show.ButtonEditor;
import controller.Show.ButtonRenderer;
 

public class Search{
	
	public static void update() {
		SwingUtilities.invokeLater(() -> {

			
			JFrame frame2 = new JFrame("Update Product");

			frame2.setBounds(450, 400, 950, 600);
			JPanel panel1 = new JPanel() {
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					Image image = new ImageIcon("E:\\petshop images\\gg.jpg").getImage();
					g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

				}
			};

			panel1.setLayout(null);
			
			////////////////////////////////////////////////////////////////////////////
			
			JLabel dateLabel = new JLabel();
	        dateLabel.setFont(new Font("Algerian", Font.BOLD, 25));
	        panel1.add(dateLabel);

	        // Create a Timer to update the date label every second
	        Timer timer = new Timer(1000, (e) -> 
	        {
	            // Get the current date
	            Date currentDate = new Date();

	            // Create a date formatter to format the date
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	            // Format the current date
	            String formattedDate = dateFormat.format(currentDate);

	            // Update the date label
	            dateLabel.setText(formattedDate);
	        });

	        // Start the timer
	        timer.start();
			
			//////////////////////////////////////////////////////////////////////////////////
			//JFrame frame2 = new JFrame("ADD PRODUCT");

	        JLabel l1 = new JLabel("Pet Breed Name :");
			l1.setFont(new Font("Algerian", Font.BOLD, 22));
			l1.setBounds(100, 50, 300, 40);
			JTextField t1 = new JTextField();
			t1.setFont(new Font("Algerian", Font.BOLD, 22));
			t1.setBounds(100, 90, 300, 40);
			
			
			JLabel l7 = new JLabel("Color :");
			l7.setFont(new Font("Algerian", Font.BOLD, 22));
			l7.setBounds(100, 130, 300, 40);
			JTextField t7 = new JTextField();
			t7.setFont(new Font("Algerian", Font.BOLD, 22));
			t7.setBounds(100, 170, 300, 40);
			 

			JLabel l2 = new JLabel("PRODUCT QUANTITY :");
			l2.setFont(new Font("Algerian", Font.BOLD, 22));
			l2.setBounds(100, 220, 300, 40);
			
			JTextField t2 = new JTextField();
			t2.setFont(new Font("Algerian", Font.BOLD, 17));
			t2.setBounds(100, 250, 300, 40);
			

			JLabel l3 = new JLabel("PER PRODUCT RATE :");
			l3.setFont(new Font("Algerian", Font.BOLD, 22));
			l3.setBounds(100, 290, 300, 40);
			JTextField t3 = new JTextField();
			t3.setFont(new Font("Algerian", Font.BOLD, 17));
			t3.setBounds(100, 330, 300, 40);
			
			

			

			JButton totalb = new JButton("TOTAL PRICE :");
			totalb.setFont(new Font("Algerian", Font.BOLD, 22));
			totalb.setBounds(100, 390,  300, 40);

			JTextField lb1 = new JTextField();
			lb1.setBounds(450, 390, 300, 40);
			lb1.setFont(new Font("Algerian", Font.BOLD, 25));
			lb1.setForeground(Color.MAGENTA);
			
			
			  

			// Existing code...////////////////////////////////////////////////////////////////////////////////////////////

			
			///// adding price automatically
			totalb.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			        int productq = Integer.parseInt(t2.getText());
			        int rate = Integer.parseInt(t3.getText());
			        lb1.setText("TOTAL AMT :" + (productq * rate));
			    }
			});

			KeyListener keyListener = new KeyAdapter() {   //
			    @Override
			    public void keyReleased(KeyEvent e) {
			        calculateTotalPrice();
			    }

			    private void calculateTotalPrice() {
			        String text1 = t2.getText();
			        String text2 = t3.getText();

			        try {
			            int number1 = Integer.parseInt(text1);
			            int number2 = Integer.parseInt(text2);

			            int sum = number1 * number2;

			            lb1.setText("TOTAL AMT:" + sum);
			        } catch (NumberFormatException ex) {
			            lb1.setText("Invalid DATA!!!!!");
			        }
			    }
			};

			t2.addKeyListener(keyListener);
			t3.addKeyListener(keyListener);

			// Existing code...////////////////////////////////////////////////////////////////////////////////


			JButton update = new JButton("Update");
			update.setFont(new Font("Algerian", Font.BOLD, 22));
			update.setBounds(500, 450, 200, 40);

			update.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					String name = t1.getText();
					int productq = Integer.parseInt(t2.getText());
					int rate = Integer.parseInt(t3.getText());
					String date=dateLabel.getText();
					String color=t7.getText();
					// int Price = Integer.parseInt(t4.getText());
					Connection con = null;
					try {
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/petshop", "root","abc123");
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

					
					try {
						int d = st.executeUpdate("UPDATE petshop.pet1 SET color = '" + color
								+ "'  WHERE petbreedName = '" + name + "'");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						int b = st.executeUpdate("UPDATE petshop.pet1 SET proQty = " + productq
								+ "  WHERE petbreedName = '" + name + "'");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					try {
						int d = st.executeUpdate("UPDATE petshop.pet1 SET price = " + rate
								+ "  WHERE petbreedName = '" + name + "'");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					try {
						int c = st.executeUpdate("UPDATE petshop.pet1 SET total = " + productq * rate
								+ "  WHERE petbreedName = '" + name + "'");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					try {
						int d = st.executeUpdate("UPDATE petshop.pet1 SET date = '" + date
								+ "'  WHERE petbreedName = '" + name + "'");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					JOptionPane.showMessageDialog(update, "DATA -Updated");

				}
			});
			JButton clear = new JButton("clear");
			clear.setFont(new Font("Algerian", Font.BOLD, 22));
			clear.setBounds(700, 450, 200, 40);
			clear.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					t1.setText("");
					t2.setText("");
					t3.setText("");
				    t7.setText("");
				}
			});

			panel1.add(t1);
			panel1.add(t2);
			panel1.add(lb1);
			panel1.add(t3);
			panel1.add(l1);
			panel1.add(l2);
			panel1.add(l3);
			panel1.add(totalb);
			panel1.add(t7);
			panel1.add(l7);

			panel1.add(update);
			panel1.add(clear);
	     	panel1.setSize(500, 800);
		   
			frame2.add(panel1);

			frame2.setVisible(true);
			
		});
		}

///////////////////////////


private static void deleteProduct(Object name) {
    
    Connection connection = null;
    try {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/petshop", "root", "abc123");
        Statement statement = connection.createStatement();
        String sql = "DELETE FROM petshop.pet1 WHERE petbreedName = '" + name + "'";
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
///////////////////////////////////////////////////////////////////////////////////////
private static void updateProduct(Object name) {
	
	update();
	
    System.out.println("Update product: " + name);
}
//////////////////////////////////////////////////////////////////////////////////
static class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer(String text) {
        setText(text);
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setBackground(table.getSelectionBackground());
        } else {
            setBackground(table.getBackground());
        }
        return this;
    }
}
////////////////////////////////////////////////
static class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private String label;
    private boolean isPushed;

    public ButtonEditor(String text) {
        super(new JTextField());
        setClickCountToStart(1);
        button = new JButton(text);
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            JTable table = (JTable) button.getParent();
            int modelRow = table.convertRowIndexToModel(table.getEditingRow());
            int modelColumn = table.convertColumnIndexToModel(table.getEditingColumn());
            Object productId = table.getModel().getValueAt(modelRow, 0);
            String columnName = table.getModel().getColumnName(modelColumn);

            if (columnName.equals("Delete")) {
                int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this entry?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    deleteProduct(productId);
                    ((DefaultTableModel) table.getModel()).removeRow(modelRow);
                }
            } else if (columnName.equals("Update")) {
                updateProduct(productId);
            }
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
private static DefaultTableModel buildTableModel(ResultSet resultSet) throws SQLException {
    DefaultTableModel tableModel = new DefaultTableModel();
    
    
    
    ResultSetMetaData metaData = resultSet.getMetaData();
    int columnCount = metaData.getColumnCount();
    for (int i = 1; i <= columnCount; i++) {
        tableModel.addColumn(metaData.getColumnName(i));
    }

    while (resultSet.next()) {
        Object[] row = new Object[columnCount];
        for (int i = 0; i < columnCount; i++) {
            row[i] = resultSet.getObject(i + 1);
        }
        tableModel.addRow(row);
    }
    return tableModel;
}




	//////////////////////////////////////////////////////////////////////////////
public static void search() {
	
	SwingUtilities.invokeLater(() -> {

		JFrame frame6 = new JFrame("SEARCH DATA");
		
		frame6.setBounds(150, 200, 900, 600);
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image image = new ImageIcon("E:\\petshop images\\er.jpg").getImage();
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

			}
		};

		panel.setLayout(null);
		
		//''''''''''''
		//JFrame frame2 = new JFrame("ADD PRODUCT");

	//	JFrame frame6 = new JFrame("DELETE PRODUCT");

		JLabel l1 = new JLabel("PRODUCT NAME");
		l1.setFont(new Font("Algerian", Font.BOLD, 22));
		l1.setForeground(Color.black);
		l1.setBounds(300, 50, 300, 40);

		JTextField t1 = new JTextField();
		t1.setFont(new Font("Algerian", Font.BOLD, 17));
		t1.setBounds(300, 90, 300, 40);

		JButton search = new JButton("SEARCH");
		search.setFont(new Font("Algerian", Font.BOLD, 22));
		search.setBounds(300, 150, 200, 40);
		
		
		
		
		
		

		JLabel lb1 = new JLabel();
		lb1.setBounds(10, 200, 350, 200);
		lb1.setForeground(Color.RED);
		lb1.setFont(new Font("Algerian", Font.BOLD, 22));

		JLabel lb2 = new JLabel();
		lb2.setBounds(10, 250, 350, 200);
		lb2.setForeground(Color.RED);
		lb2.setFont(new Font("Algerian", Font.BOLD, 22));

		JLabel lb3 = new JLabel();
		lb3.setBounds(10, 300, 300, 200);
		lb3.setForeground(Color.RED);
		lb3.setFont(new Font("Algerian", Font.BOLD, 22));

		JLabel lb4 = new JLabel();
		lb4.setBounds(10, 350, 350, 200);
		lb4.setForeground(Color.RED);
		lb4.setFont(new Font("Algerian", Font.BOLD, 22));

		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					boolean found = false;
					String name = t1.getText();
					Connection con = null;
					try {
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/petshop", "root","abc123");
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

					
					
					
					Connection connection = null;
					try {
						connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/petshop", "root", "abc123");
					} catch (SQLException e22) {
						e22.printStackTrace();
					}
					// Create a statement to execute SQL queries
					Statement statement = null;
					try {
						statement = connection.createStatement();
					} catch (SQLException e33) {
						e33.printStackTrace();
					}
					// Execute a SELECT query to get data from the database
					String sql = "SELECT *FROM petshop.pet1 WHERE petbreedName like  '" + name + "%'";
					 ResultSet resultSet = null;
				        try {
				            resultSet = statement.executeQuery(sql);
				        } catch (SQLException e21) {
				            e21.printStackTrace();
				        }
				      
				        JFrame f = new JFrame("SHOW DATA");
						
				        
				        JTable table = null;
				        try {
				            table = new JTable(buildTableModel(resultSet));
				        } catch (SQLException e22) {
				            e22.printStackTrace();
				        }
				        table.setFont(new Font("Algerian", Font.BOLD, 14));

				        // Add delete and update button columns to the table model
				        DefaultTableModel model = (DefaultTableModel) table.getModel();
				        model.addColumn("Delete");
				        model.addColumn("Update");

				        // Create button column renderers and editors
				        TableColumn deleteColumn = table.getColumnModel().getColumn(model.getColumnCount() - 2);
				        deleteColumn.setCellRenderer(new ButtonRenderer("Delete"));
				        deleteColumn.setCellEditor(new ButtonEditor("Delete"));

				        TableColumn updateColumn = table.getColumnModel().getColumn(model.getColumnCount() - 1);
				        updateColumn.setCellRenderer(new ButtonRenderer("Update"));
				        updateColumn.setCellEditor(new ButtonEditor("Update"));

				        f.add(new JScrollPane(table));
				        f.pack();
				       f.setBounds(500, 750, 800, 200);
				        f.setVisible(true);
					
					
					if (!found) {
						//JOptionPane.showMessageDialog(search, "DATA NOT FOUND");
					}

				} catch (Exception p) {
					System.out.println("Enter the no. :" + p);

				}
				t1.setText("");
				// TODO Auto-generated method stub

			}
		});
		
		panel.add(search);
		panel.add(l1);
		panel.add(t1);
		panel.add(lb1);
		panel.add(lb2);
		panel.add(lb3);
		panel.add(lb4);

		
		frame6.add(panel);

		frame6.setVisible(true);
	});

}}
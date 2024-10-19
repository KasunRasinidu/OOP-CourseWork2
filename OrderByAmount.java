import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.table.*;

class OrderByAmount extends JFrame{
	private JButton Back;
	
	private DefaultTableModel dtm;
	private JTable OrderTable;
	
	private double [] tem_amt=new double[Shop.or.length];
	private String[] tem_size=new String[Shop.or.length];
	private String[] tem_id=new String[Shop.or.length];
	private String[] tem_no=new String[Shop.or.length];
	private int[] tem_qty=new int[Shop.or.length];
	private String[] tem_status=new String[Shop.or .length];
	
	 
	OrderByAmount(){
		setSize(600,520);
		setTitle("Order by Amount");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//Back
		
		JPanel NorthPan=new JPanel(new FlowLayout(FlowLayout.LEFT,20,20));
		Back=new JButton("Back");
		Back.setFont(new Font("Ariel",1,15));
		Back.setHorizontalAlignment(JButton.CENTER);
		Back.setBackground(Color.RED);
		Back.setForeground(Color.WHITE);
		
		NorthPan.add(Back);
		Back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dispose();
				new Reports().setVisible(true);
				 				 
			}
		});
		add("North",NorthPan);
		
		String [] colNames={"Order ID","Customer ID","Size","QTY","Amount","Status"};
		dtm =new DefaultTableModel(colNames,0);
		
		OrderTable=new JTable(dtm);
		OrderTable.setRowHeight(30);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		OrderTable.setDefaultRenderer(Object.class, centerRenderer);
		
		JScrollPane TablePane=new JScrollPane(OrderTable);
		JPanel temx=new JPanel(new GridLayout(1,1,100,100));
		temx.add(TablePane);
		add("Center",temx);
		
		JLabel j1=new JLabel("         ");
		add("East",j1);
		JLabel j2=new JLabel("         ");
		add("West",j2);
		JLabel j3=new JLabel("         ");
		j3.setFont(new Font("Ariel",1,15));
		add("South",j3);
		OrderTable. getColumnModel(). getColumn(0). setPreferredWidth(100);
		OrderTable. getColumnModel(). getColumn(1). setPreferredWidth(100);
		OrderTable. getColumnModel(). getColumn(2). setPreferredWidth(30);
		OrderTable. getColumnModel(). getColumn(3). setPreferredWidth(30);
		OrderTable. getColumnModel(). getColumn(5). setPreferredWidth(100);
		
		//Copy Values
		for (int i = 0; i<tem_amt.length; i++){
			tem_amt[i]=Shop.or[i].amt;
			tem_size[i]=Shop.or[i].size;
			tem_id[i]=Shop.or[i].orderid;
			tem_no[i]=Shop.or[i].phoneno;
			tem_qty[i]=Shop.or[i].qty;
			tem_status[i]=Shop.or[i].status==0?"PROCESSING":Shop.or[i].status==1?"DELIVERING":"DELIVERED";
		}
		
		Shop.setDecOrder(tem_amt,tem_id,tem_no,tem_size,tem_qty,tem_status);
		
		for (int i = 0; i <Shop.or.length; i++){
			Object[] rowData={tem_id[i],tem_no[i],tem_size[i],tem_qty[i],String.format("%12.2f",tem_amt[i]),tem_status[i]};
			dtm.addRow(rowData);
		}
	}
}

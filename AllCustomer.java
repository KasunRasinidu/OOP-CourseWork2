import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.table.*;

class AllCustomer extends JFrame{
	private JButton Back;
	private DefaultTableModel dtm;
	private JTable ViewCustomerTable;
	private int[] tem_qty;
	private double[]tem_amt;
	private String[] no_dup;
	private String [] size_tem=Shop.copy(Shop.size_con);
	
	AllCustomer(){
		setSize(600,520);
		setTitle("All Customers");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//Back
		
		JPanel NorthPan=new JPanel(new FlowLayout(FlowLayout.LEFT));
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
		
		String [] colNames={"Customer ID","XS","S","M","L","XL","XXL","Amount"};
		dtm =new DefaultTableModel(colNames,0);
		
		ViewCustomerTable=new JTable(dtm);
		ViewCustomerTable.setRowHeight(40);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		ViewCustomerTable.setDefaultRenderer(Object.class, centerRenderer);
		
		JScrollPane TablePane=new JScrollPane(ViewCustomerTable);
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
		
		String[] no_dup=new String[0];
		for (int i = 0; i <Shop.or.length; i++){
			if(!Shop.check(no_dup,Shop.or[i].phoneno)){
				no_dup=Shop.append(no_dup,Shop.or[i].phoneno);
			}
		}
		//System.out.println("Here");
		tem_amt=new double[no_dup.length];
		
		for (int i = 0; i <no_dup.length; i++){
			int [] is=Shop.selectGroupPhoneno(no_dup[i]);				
			for (int j = 0; j <is.length; j++){
				tem_amt[i]+=Shop.or[is[j]].amt;
			}
		}
		ViewCustomerTable.getColumnModel(). getColumn(0). setPreferredWidth(100);
		ViewCustomerTable.getColumnModel(). getColumn(1). setPreferredWidth(20);
		ViewCustomerTable.getColumnModel(). getColumn(2). setPreferredWidth(20);
		ViewCustomerTable.getColumnModel(). getColumn(3). setPreferredWidth(20);
		ViewCustomerTable.getColumnModel(). getColumn(4). setPreferredWidth(20);
		ViewCustomerTable.getColumnModel(). getColumn(5). setPreferredWidth(20);
		ViewCustomerTable.getColumnModel(). getColumn(6). setPreferredWidth(20);
		ViewCustomerTable.getColumnModel(). getColumn(7). setPreferredWidth(100);
		
		
		//System.out.println("Here");
		
		for (int i = 0; i <no_dup.length; i++){
			int[] copy_qty=new int[size_tem.length];
			int [] is=Shop.selectGroupPhoneno(no_dup[i]);
			//System.out.println("Here start");
			for(int j=0;j<is.length;j++){
				for(int x=0; x<copy_qty.length; x++){
					
					if(Shop.or[is[j]].size.equals(size_tem[x])){
						copy_qty[x]+=Shop.or[is[j]].qty;
						//System.out.println("Here in");	
					}
				}	
				//System.out.println(Arrays.toString(copy_qty));
			}
			//System.out.println("Here add");
			Object[] rowData={no_dup[i],copy_qty[0],copy_qty[1],copy_qty[2],copy_qty[3],copy_qty[4],copy_qty[5],String.format("%12.2f",tem_amt[i])};
			dtm.addRow(rowData);
		}
	}
}

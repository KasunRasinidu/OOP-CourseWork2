import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.table.*;

class ProductsByQty extends JFrame{
	private JButton Back;
	
	private DefaultTableModel dtm;
	private JTable ProductTable;
	private double [] tem_amt=new double[Shop.size_con.length];
	private int[] tem_qty=new int[Shop.size_con.length];
	private String [] size_tem=Shop.copy(Shop.size_con);
	private double tot=0;
	
	ProductsByQty(){
		
		setSize(500,520);
		setTitle("Products by Qty");
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
		
		JLabel j1=new JLabel("                    ");
		add("East",j1);
		JLabel j2=new JLabel("                    ");
		add("West",j2);
		JLabel j3=new JLabel("         ");
		j3.setFont(new Font("Ariel",1,68));
		add("South",j3);
		
		String [] colNames={"Size","QTY","Amount"};
		dtm =new DefaultTableModel(colNames,0);
		
		ProductTable=new JTable(dtm);
		ProductTable.setRowHeight(50);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		ProductTable.setDefaultRenderer(Object.class, centerRenderer);
		
		JScrollPane TablePane=new JScrollPane(ProductTable);
		JPanel temx=new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
		temx.add(TablePane);
		add("Center",TablePane);
		
		for(int j=0;j<Shop.or.length;j++){
			for(int x=0; x<size_tem.length; x++){	
				if(Shop.or[j].size.equals(size_tem[x])){
					tot+=Shop.or[j].amt;
					tem_amt[x]+=Shop.or[j].amt;
					tem_qty[x]+=Shop.or[j].qty;
				}
			}	
		}
		Shop.setDecOrder(tem_qty,size_tem,tem_amt);
		
		for (int i = 0; i <6; i++){
			
			Object[] rowData={size_tem[i],tem_qty[i],String.format("%12.2f",tem_amt[i])};
			dtm.addRow(rowData);
		}
	}
	
	public static void main(String args[]){
		new ProductsByQty().setVisible(true);
	}
}

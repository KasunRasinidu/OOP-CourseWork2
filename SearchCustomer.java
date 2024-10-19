import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.table.*;
	

class SearchCustomer extends JFrame{
	private JButton Back;
	private JButton Search;
	private JTextField Customer_ID;
	private JLabel CustomerID; 
	private int index;
	private JTable CustomerTable;
	private DefaultTableModel dtm;
	private JLabel total;
	private JLabel tot_print;
	private double [] tem_amt=new double[Shop.size_con.length];
	private int[] tem_qty=new int[Shop.size_con.length];
	private String [] size_tem=Shop.copy(Shop.size_con);
	private double tot=0;
	private int count=1;
	
	
	SearchCustomer(){
		setSize(570,521);
		setTitle("Search Customer");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//back button
		JPanel NorthPanel=new JPanel(new GridLayout(2,1));
		JPanel tem1 =new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
		Back=new JButton("Back");
		Back.setFont(new Font("Ariel",1,15));
		Back.setBackground(Color.RED);
		Back.setForeground(Color.WHITE);
		tem1.add(Back);
		
		NorthPanel.add(tem1);
		Back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dispose();
				new Home().setVisible(true);
				 				 
			}
		});
		
		JPanel tem3=new JPanel(new GridLayout(1,3));
		JPanel temp_panel=new JPanel(new FlowLayout(FlowLayout.LEFT,40,15));
		CustomerID=new JLabel("Enter Customer ID :");
		CustomerID.setFont(new Font("Ariel",1,15));
		CustomerID.setHorizontalAlignment(JLabel.LEFT);
		temp_panel.add(CustomerID);
		tem3.add(temp_panel);
		
		JPanel tem_panel1=new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
		Customer_ID=new JTextField(10);
		Customer_ID.setFont(new Font("Ariel",1,20));
		Customer_ID.setPreferredSize(new Dimension(200,30));
		tem_panel1.add(Customer_ID);
		tem3.add(tem_panel1);
		
		JPanel searchpan=new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
		Search =new JButton("Search");
		Search.setFont(new Font("Ariel",1,15));
		Search.setBackground(new Color(72,201,176));
		Search.setForeground(Color.WHITE);
		searchpan.add(Search);
		tem3.add(searchpan);
		
		NorthPanel.add(tem3);
		add("North",NorthPanel);
		
		String [] colNames={"Size","QTY","Amount"};
		dtm =new DefaultTableModel(colNames,0);
		//dtm.setSize(100,100);
		CustomerTable=new JTable(dtm);
		CustomerTable.setRowHeight(50);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		CustomerTable.setDefaultRenderer(Object.class, centerRenderer);
		
		JScrollPane TablePane=new JScrollPane(CustomerTable);
		JPanel temx=new JPanel(new FlowLayout(FlowLayout.CENTER,10,0));
		temx.add(TablePane);
		add("Center",TablePane);
		
		
		JLabel j1=new JLabel("                               ");
		add("East",j1);
		JLabel j2=new JLabel("                               ");
		add("West",j2);
		
		for (int i = 0; i <6; i++){
			
			Object[] rowData={size_tem[i],tem_qty[i],String.format("%8.2f",tem_amt[i])};
			dtm.addRow(rowData);
		}
		
		CustomerTable.getColumnModel(). getColumn(0). setPreferredWidth(30);
		CustomerTable.getColumnModel(). getColumn(1). setPreferredWidth(30);
		
		Search.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				 index=Shop.selectphoneno(Customer_ID.getText());
				 if(count==1){
					if(index!=-1){
						int[] is=Shop.selectGroupPhoneno(Shop.or[index].phoneno);
						for(int j=0;j<is.length;j++){
							for(int x=0; x<size_tem.length; x++){	
								if(Shop.or[is[j]].size.equals(size_tem[x])){
									tot+=Shop.or[is[j]].amt;
									tem_amt[x]+=Shop.or[is[j]].amt;
									tem_qty[x]+=Shop.or[is[j]].qty;
								}
							}	
						}
						Shop.setDecOrder(tem_amt,tem_qty,size_tem);
						//System.out.println(Arrays.toString(tem_amt));
						dtm.setRowCount(0);
						for (int i = 0; i <6; i++){
							Object[] rowData={size_tem[i],tem_qty[i],String.format("%8.2f",tem_amt[i])};
							dtm.addRow(rowData);
						}
						total.setText("Total      :                                                                      "+Double.toString(tot));
						count++;
					}else{
						JOptionPane.showMessageDialog(null,"Invalid Customer ID Try Agian...","Error",0);
						dispose();
						new SearchCustomer().setVisible(true);
					}
					
				}
				 
			}
		});
		
		Customer_ID.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				 index=Shop.selectphoneno(Customer_ID.getText());
				 if(count==1){
					if(index!=-1){
						int[] is=Shop.selectGroupPhoneno(Shop.or[index].phoneno);
						for(int j=0;j<is.length;j++){
							for(int x=0; x<size_tem.length; x++){	
								if(Shop.or[is[j]].size.equals(size_tem[x])){
									tot+=Shop.or[is[j]].amt;
									tem_amt[x]+=Shop.or[is[j]].amt;
									tem_qty[x]+=Shop.or[is[j]].qty;
								}
							}	
						}
						Shop.setDecOrder(tem_amt,tem_qty,size_tem);
						//System.out.println(Arrays.toString(tem_amt));
						dtm.setRowCount(0);
						for (int i = 0; i <6; i++){
							Object[] rowData={size_tem[i],tem_qty[i],String.format("%8.2f",tem_amt[i])};
							dtm.addRow(rowData);
						}
						total.setText("Total      :                                                                      "+Double.toString(tot));
						count++;
					}else{
						JOptionPane.showMessageDialog(null,"Invalid Customer ID Try Agian...","Error",0);
						dispose();
						new SearchCustomer().setVisible(true);
					}
					
				}
				 
			}
		});
		
		JPanel temp2=new JPanel(new GridLayout(1,1));
		JPanel temp_panel2=new JPanel(new FlowLayout(FlowLayout.LEFT,80,20));
		total=new JLabel("           Total      :                                                   "+Double.toString(tot));
		total.setFont(new Font("Ariel",1,14));
		total.setHorizontalAlignment(JLabel.CENTER);
		temp_panel2.add(total);
		temp2.add(temp_panel2);
		
		
		add("South",temp2);
		
	}
	public static void main(String args[]){
		new SearchCustomer().setVisible(true);
	}
}

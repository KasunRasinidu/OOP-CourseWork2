 import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
	


class Shop{
	private static int ID=9;
	public static Order[] or=new Order[8];//()
	static String [] size_con={"XS","S","M","L","XL","XXL"};
	static double [] price_con={600,800,900,1000,1100,1200};
	
	public static Order[] extend(){
		Order[] temp=new Order[or.length+1];
		for (int i = 0; i <or.length; i++){
			temp[i]=or[i];
		}
		//System.out.println("Here");
		return temp;
	}
	
	
	public static int select(String s){
		for (int i=0;i<size_con.length;i++){
			if(size_con[i].equals(s)){
				return i;
			}	
		}return -1;
	}
	
	public static int selectphoneno(String s){
		for (int i=0;i<or.length;i++){
			if(or[i].phoneno.equals(s)){
				return i;
				
			}	
		}return -1;
	}
	
	public static int selectOrderID(String s){
		for (int i=0;i<or.length;i++){
			if(or[i].orderid.equals(s)){
				//System.out.println("Here");
				return i;
				
			}	
		}return -1;
	}
	
	public static Order[] delete(int x){
		Order[] temp=new Order[or.length-1];
		int index=0;
		for(int i=0;i<or.length;i++){
			if(i!=x){
				temp[index]=or[i];
				index++;
			}
		}
		return temp;
	}
	
	public static String getOrderID(){
		String id=String.format("%4s%05d","ODR#",(ID));
		return id;
	}
	
	public static int getID(){
		return ID;
	}
	
	public static void extendOrderID(){
		ID++;
	}
	
	public static String[] copy(String[] ar){
		String[] temp=new String[ar.length];
		for (int j = 0; j <ar.length; j++){
			temp[j]=ar[j];
		}return temp;
	}
	
	public static int[]selectGroupPhoneno(String x){
		int count=0,k=0;
		for (int i=0; i<or.length;i++){
			if(or[i].phoneno.equals(x)){
				count++;
			}
		}
		int[] is=new int[count];
		for(int i=0;i<or.length;i++){
			if(or[i].phoneno.equals(x)){
				is[k]=i;
				k++;
			}
		}return is;
	}
	
	public static void setDecOrder(double[] ar,int[] br,String[] cr){
		for (int i = 0; i <ar.length ; i++){
			for (int j = ar.length-1; j>i ; j--){
				if(ar[i]<ar[j]){
					//System.out.println(i);
					double tem1=ar[i];
					ar[i]=ar[j];
					ar[j]=tem1;
					int tem2=br[i];
					br[i]=br[j];
					br[j]=tem2;
					String tem3=cr[i];
					cr[i]=cr[j];
					cr[j]=tem3;
				}
			}
		}
	}
	
	public static void setDecOrder(int[] ar,String[] cr,double[] br){
		for (int i = 0; i <ar.length ; i++){
			for (int j = 0; j <ar.length ; j++){
				if(ar[i]>ar[j]){
					//System.out.println(i);
					int tem2=ar[i];
					ar[i]=ar[j];
					ar[j]=tem2;
					String tem3=cr[i];
					cr[i]=cr[j];
					cr[j]=tem3;
					double tem1=br[i];
					br[i]=br[j];
					br[j]=tem1;
				}
			}
		}
	}
	
	public static void setDecOrder(double[] amt,String[] soid,String[] no,String []size,int[] qty,String[] stat){
		for (int i = 0; i <amt.length ; i++){
			for (int j = amt.length-1; j>i ; j--){
				if(amt[i]<amt[j]){
					//System.out.println(i);
					double tem1=amt[i];
					amt[i]=amt[j];
					amt[j]=tem1;
					String tem2=soid[i];
					soid[i]=soid[j];
					soid[j]=tem2;
					String tem3=no[i];
					no[i]=no[j];
					no[j]=tem3;
					String tem4=size[i];
					size[i]=size[j];
					size[j]=tem4;
					int tem5=qty[i];
					qty[i]=qty[j];
					qty[j]=tem5;
					String tem6=stat[i];
					stat[i]=stat[j];
					stat[j]=tem6;
				}
			}
		}
	}
	
	public static boolean check(String [] ar,String x){	
		for (int i = 0; i <ar.length; i++){
			if(ar[i].equals(x)){
				return true;
			}
		}return false;
				
	}
	
	public static String[] append(String[] ar,String x){
		String[] tem=new String[ar.length+1];
		int i=0;
		for(i=0;i<ar.length;i++){
			tem[i]=ar[i];
		}
		tem[i]=x;
		return tem;
	}
	
	
	public static void main(String args[]){
		int [] oid={1,2,3,4,5,6,7,8};
		String [] soid={"ODR#00001","ODR#00002","ODR#00003","ODR#00004","ODR#00005","ODR#00006","ODR#00007","ODR#00008"};
		String [] no={"0776198410","0703859852","0769854321","0776198410","0703859852","0775544336","0703859852","0712002200"};
		int [] qty={3,4,1,3,6,2,1,3};
		String [] size={"XS","XL","XXL","M","M","S","XXL","M"};
		int [] status={0,1,2,1,0,2,0,2};
		double[] amts={1800,4400,1200,2700,5400,1600,1200,2700};
		for (int i = 0; i < or.length; i++){
			or[i]=new Order(soid[i],no[i],qty[i],size[i],status[i],amts[i]);
		}
		new Home().setVisible(true);
	}
}

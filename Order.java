class Order{
	String orderid;
	String phoneno;
	int qty;
	String size;
	int status;
	double amt;
	
	public Order(String orderid,String phoneno,int qty,String size,int status,double amt){
		this.orderid=orderid;
		this.phoneno=phoneno;
		this.qty=qty;
		this.size=size;
		this.status=status;
		this.amt=amt;
	}
}

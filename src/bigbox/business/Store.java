package bigbox.business;

public class Store extends Facility {

	private String storeNbr;
	private String divisionNbr;
	private double sales;
	
	// default constructor
	public Store()
	{
		super();
		storeNbr = "";
		divisionNbr = "";
		sales = 0.0;
	}
	
	public Store(int inId, String inDivisionNbr, String inStoreNbr, double inSales, String inName, String inAddress, String inCity, String inState, String inZip)
	{
		super(inId, inName, inAddress, inCity, inState, inZip);
		storeNbr = inStoreNbr;
		divisionNbr = inDivisionNbr;
		sales = inSales;
	}

	public String getStoreNbr() {
		return storeNbr;
	}

	public void setStoreNbr(String storeNbr) {
		this.storeNbr = storeNbr;
	}

	public String getDivisionNbr() {
		return divisionNbr;
	}

	public void setDivisionNbr(String divisionNbr) {
		this.divisionNbr = divisionNbr;
	}

	public double getSales() {
		return sales;
	}

	public void setSales(double sales) {
		this.sales = sales;
	}
	
	public String toString()
	{
		return "[Store: store#=" + getStoreNbr() + ", div#=" + getDivisionNbr() + ", sales=" + getSales() + "]\n" + super.toString();
	}
}

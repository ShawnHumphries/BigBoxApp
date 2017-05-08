package bigbox.business;

import java.util.ArrayList;

public class Store extends Facility {

	private int divisionID;
	private String storeNbr;
	private ArrayList<StoreSales> sales;
	
	// default constructor
	public Store()
	{
		super();
		divisionID = 0;
		storeNbr = "";
		sales = new ArrayList<StoreSales>();
	}
	
	public Store(int idIn, int divisionIDIn, String storeNbrIn, String nameIn, String addressIn, String cityIn, String stateIn, String zipIn)
	{
		super(idIn, nameIn, addressIn, cityIn, stateIn, zipIn);
		divisionID = divisionIDIn;
		storeNbr = storeNbrIn;
	}

	public Store(int divisionIDIn, String storeNbrIn, String nameIn, String addressIn, String cityIn, String stateIn, String zipIn) 
	{
		super(-1, nameIn, addressIn, cityIn, stateIn, zipIn);
		divisionID = divisionIDIn;
		storeNbr = storeNbrIn;
	}

	public int getDivisionID() {
		return divisionID;
	}

	public void setDivisionID(int divisionID) {
		this.divisionID = divisionID;
	}

	public String getStoreNbr() {
		return storeNbr;
	}

	public void setStoreNbr(String storeNbr) {
		this.storeNbr = storeNbr;
	}

	public ArrayList<StoreSales> getSales() {
		return sales;
	}

	public void setSales(ArrayList<StoreSales> sales) {
		this.sales = sales;
	}

	public String toString()
	{
		return "[Store: store#=" + getStoreNbr() + "]\n" + super.toString();
	}
	
}

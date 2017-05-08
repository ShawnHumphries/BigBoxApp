package bigbox.business;

public class StoreSales {

	private int ID;
	private int storeID;
	private int year;
	private int week;
	private double sales;
	
	// default constructor
	public StoreSales()
	{
		year = 0;
		week = 0;
		sales = 0.0;
	}

	public StoreSales(int yearIn, int weekIn, double salesIn)
	{
		year = yearIn;
		week = weekIn;
		sales = salesIn;
	}

	public StoreSales(int IDin, int storeIDIn, int yearIn, int weekIn, double salesIn)
	{
		ID = IDin;
		storeID = storeIDIn;
		year = yearIn;
		week = weekIn;
		sales = salesIn;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getStoreID() {
		return storeID;
	}

	public void setStoreID(int storeID) {
		this.storeID = storeID;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public double getSales() {
		return sales;
	}

	public void setSales(double sales) {
		this.sales = sales;
	}
	
}


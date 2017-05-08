package bigbox.business;

public class Division extends Facility {

	private String divisionNbr;

	// default constructor
	public Division()
	{
		super();
		divisionNbr = "";
	}
	
	public Division(String divisionNbr)
	{
		this.divisionNbr = divisionNbr;
	}

	public String getDivisionNbr() {
		return divisionNbr;
	}

	public void setDivisionNbr(String divisionNbr) {
		this.divisionNbr = divisionNbr;
	}
	
}

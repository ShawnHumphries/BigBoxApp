package bigbox.business;

public class Facility {

	private int id;
	private String name;
	private String address;
	private String city;
	private String state;
	private String zip;
	
	// default constructor
	public Facility()
	{
		id = 0;
		name = "";
		address = "";
		city = "";
		state = "";
		zip = "";
	}
	
	public Facility(int inId, String inName, String inAddress, String inCity, String inState, String inZip)
	{
		id = inId;
		name = inName;
		address = inAddress;
		city = inCity;
		state = inState;
		zip = inZip;
	}

	public int getId() {
		return id;
	}

	// id of -1 indicates that this is a new facility and the id needs to be determined
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public String toString()
	{
		return "[Facility: id=" + getId() + ", name=" + getName() + ", address=" + getAddress() + ", city=" + getCity() + ", state=" + getState() + ", zip=" + getZip() + "]";
	}
}

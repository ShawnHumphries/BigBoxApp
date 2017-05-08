import java.util.Scanner;

public class BigBoxApp {

	public static void main(String[] args) {

		System.out.println("Welcome to the Big Box Application");
		System.out.println("");
		
		Scanner sc = new Scanner(System.in);
        // Prompt the user for divisions and stores until they no longer want to continue
        String choice = "y";
        while (!choice.equalsIgnoreCase("n"))
        {
        	// Prompt the user for the division number
        	String division = Validator.getStringNumeric(sc, "Enter a division #: ", 3);
        	// Prompt the user for the store number
        	String store = Validator.getStringNumeric(sc, "Enter a store #: ", 5);
        	System.out.println("");
        	// Lookup the division and store in the "DB"
        	Store st = StoreDB.getStoreByDivisionStore(division, store);
        	if (st == null)
        	{
        		System.out.println("No store found for division " + division + " and storeNbr " + store + ".");
        	}
        	else
        	{
        		// Print out information regarding the store and the facility
        		System.out.println("Store found for division " + division + " and storeNbr " + store + ".");
        		System.out.println("[Store: store#=" + st.getStoreNbr() + ", div#=" + st.getDivisionNbr() + ", sales=" + st.getSales() + "]");
        		System.out.println("[Facility: id=" + st.getId() + ", name=" + st.getName() + ", address=" + st.getAddress() + ", city=" + st.getCity() + ", state=" + st.getState() + ", zip=" + st.getZip() + "]");
        		System.out.println("");
        	}
        	// Ask the user if they want to continue
        	choice = Validator.validateContinue(sc);
        	System.out.println();
        }
        System.out.println("Thanks for using the BigBoxApp.  Bye!");
	}

}

package bigbox.presentation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import bigbox.business.Division;
import bigbox.business.Store;
import bigbox.business.StoreSales;
import bigbox.db.DAOFactory;
import bigbox.db.division.DivisionDAO;
import bigbox.db.store.StoreDAO;
import bigbox.db.storesales.StoreSalesDAO;
import bigbox.util.DBUtil;
import bigbox.util.StringUtil;
import bigbox.util.Validator;

public class BigBoxApp {

	private static Scanner sc = new Scanner(System.in);
	private static DivisionDAO divisionDAO = DAOFactory.getDivisionDAO();
	private static StoreDAO storeDAO = DAOFactory.getStoreDAO();
	private static StoreSalesDAO storeSalesDAO = DAOFactory.getStoreSalesDAO();
	// Keep two caches of divisions - one by divisionID and value (Division) and one by divisionNbr and value (Division)
	private static HashMap<Integer,Division> divisionIDMap = new HashMap<Integer,Division>();
	private static HashMap<String,Division> divisionNbrMap = new HashMap<String,Division>();

	public static void main(String[] args) {

		String command = "";
		
		// Display a welcome message
		System.out.println("Welcome to the Big Box application\n");
		
		// Get the divisions and populate the divisions hash maps
		populateDivisionsMap();
		
		while (!command.equalsIgnoreCase("exit"))
		{
			displayMenu();
			// Get the command from the user
			command = Validator.getCommand(sc, "Enter a command: ");
			System.out.println();
			// Perform the command 
			switch (command)
			{
			case "list":
				displayAllStores();
				break;
			case "div":
				displayStoreByDivision();
				break;
			case "add":
				addStore();
				break;
			case "del":
				deleteStore();
				break;
			case "sales":
				command = handleSalesFunctions();
				break;
			case "help":
				break;
			case "exit":
				break;
			}
		}
		sc.close();
	}

	/*
	 * Handle the various sales functions.
	 */
	private static String handleSalesFunctions()
	{
		String command = "";
		
		while (!command.equalsIgnoreCase("back") && !command.equalsIgnoreCase("exit"))
		{
			displaySalesMenu();
			// Get the command from the user
			command = Validator.getCommand(sc, "Enter a command: ");
			System.out.println();
			// Perform the command 
			switch (command)
			{
			case "div":
				displaySalesByDivision();
				break;
			case "store":
				displaySalesByStore();
				break;
			case "year":
				displaySalesByStoreAndYear();
				break;
			case "week":
				displaySalesByWeek();
				break;
			case "allsales":
				displayAllSalesByStore();
				break;
			case "help":
				break;
			case "back":
				break;
			case "exit":
				break;
			}
		}
		return command;
	}

	/*
	 * Display a command menu.
	 */
	private static void displayMenu()
	{
		System.out.println("COMMAND MENU");
		System.out.println("list\t- List all stores");
		System.out.println("div\t- List all stores for a division");
		System.out.println("add\t- Add a store");
		System.out.println("del\t- Delete a store");
		System.out.println("sales\t- Get store sales");
		System.out.println("help\t- Show this menu");
		System.out.println("exit\t- Exit this application");
		System.out.println();
	}

	/*
	 * Display a sales menu.
	 */
	private static void displaySalesMenu()
	{
		System.out.println("SALES MENU");
		System.out.println("div\t\t- Get the total sales for an entire division");
		System.out.println("store\t\t- Get the total sales for a particular store");
		System.out.println("year\t\t- Get the total sales for a particular store, for a particular year");
		System.out.println("week\t\t- Get the total sales for a particular year, for a particular week");
		System.out.println("allsales\t- Display all sales for a particular store");
		System.out.println("help\t\t- Show this menu");
		System.out.println("back\t\t- Go back to the previous menu");
		System.out.println("exit\t\t- Exit this application");
		System.out.println();
	}
	
	/*
	 * Get all the stores from the database and display them on the screen.
	 * Use the divisionsIDMap to get the division number for each store.
	 */
	private static void displayAllStores()
	{
		ArrayList<Store> stores = storeDAO.getAllStores();
		if (stores.size() > 0)
		{
			System.out.println("Division #  Store #   Name                     Address                  City        State  Zip");
			for (Store store : stores)
			{
				Division division = divisionIDMap.get(store.getDivisionID());
				System.out.print(StringUtil.padWithSpaces(division.getDivisionNbr(), 12) + StringUtil.padWithSpaces(store.getStoreNbr(), 10));
				System.out.print(StringUtil.padWithSpaces(store.getName(), 25) + StringUtil.padWithSpaces(store.getAddress(), 25));
				System.out.print(StringUtil.padWithSpaces(store.getCity(), 12) + StringUtil.padWithSpaces(store.getState(), 7));
				System.out.println(StringUtil.padWithSpaces(store.getZip(), 10));
			}
		}
		else
			System.out.println("Big Box App does not have any stores.");
		System.out.println();
	}
	
	/*
	 * Get all the stores from the database for the given division and display them on the screen.
	 * Use the divisionsIDMap to get the division number for each store.
	 */
	private static void displayStoreByDivision()
	{
		String divisionNbr = Validator.getString(sc, "Enter a division: ", DivisionDAO.DIVISION_LENGTH);
		ArrayList<Store> storesByDiv = storeDAO.getAllStoresByDivision(divisionNbr);
		System.out.println();
		if (storesByDiv.size() > 0)
		{
			System.out.println("Division #  Store #   Name                     Address                  City        State  Zip");
			for (Store store : storesByDiv)
			{
				Division division = divisionIDMap.get(store.getDivisionID());
				System.out.print(StringUtil.padWithSpaces(division.getDivisionNbr(), 12) + StringUtil.padWithSpaces(store.getStoreNbr(), 10));
				System.out.print(StringUtil.padWithSpaces(store.getName(), 25) + StringUtil.padWithSpaces(store.getAddress(), 25));
				System.out.print(StringUtil.padWithSpaces(store.getCity(), 12) + StringUtil.padWithSpaces(store.getState(), 7));
				System.out.println(StringUtil.padWithSpaces(store.getZip(), 10));
			}
		}
		else
			System.out.println("No stores found for Division = " + divisionNbr);
		System.out.println();
	}
	
	/*
	 * Add a store to the database.
	 * Use the divisionsNbrMap verify the division is valid and to get the division ID for the store.
	 */
	private static void addStore()
	{
		String divisionNbr = Validator.getString(sc, "Enter the division: ", DivisionDAO.DIVISION_LENGTH);
		Division division = divisionNbrMap.get(divisionNbr);
		if (division == null)
		{
			System.out.println("\nCannot add store information - the division " + divisionNbr + " is invalid\n");
			return;
		}
		int divisionID = division.getId();
		String storeNbr = Validator.getString(sc, "Enter the store number: ", StoreDAO.STORE_LENGTH);
		String storeName = Validator.getString(sc, "Enter the store's name: ");
		String storeAddr = Validator.getString(sc, "Enter the store's address: ");
		String storeCity = Validator.getString(sc, "Enter the store's city: ");
		String storeState = Validator.getString(sc, "Enter the store's state: ");
		String storeZip = Validator.getString(sc, "Enter the store's zip code: ");
		Store newStore = new Store(divisionID, storeNbr, storeName, storeAddr, storeCity, storeState, storeZip);
		if (storeDAO.addStore(newStore))
			System.out.println("\nStore number " + storeNbr + " has been added.\n");
		else
			System.out.println("\nStore number " + storeNbr + " was not added successfully.\n");
	}
	
	/*
	 * Delete a store from the database.
	 * Use the divisionsNbrMap to verify the division is valid.
	 */
	private static void deleteStore()
	{
		String divisionNbr = Validator.getString(sc, "Enter the division: ", DivisionDAO.DIVISION_LENGTH);
		Division division = divisionNbrMap.get(divisionNbr);
		if (division == null)
		{
			System.out.println("\nCannot delete store information - the division " + divisionNbr + " is invalid\n");
			return;
		}
		String storeNbr = Validator.getString(sc, "Enter the store number: ", StoreDAO.STORE_LENGTH);
		Store store = storeDAO.getStoreByDivisionAndStoreNumber(divisionNbr, storeNbr);
		if (store != null)
			// Delete this store
			if (storeDAO.deleteStore(store))
				System.out.println("\nStore number " + storeNbr + " has been deleted successfully.\n");
			else
				System.out.println("\nStore number " + storeNbr + " was not deleted successfully.\n");
		else
			System.out.println("\nStore number " + storeNbr + " was not deleted successfully.\n");
	}

	/*
	 * Display sales information for a given division.
	 * Use the divisionsNbrMap to verify the division is valid.
	 */
	private static void displaySalesByDivision()
	{
		String divisionNbr = Validator.getString(sc, "Enter the division: ", DivisionDAO.DIVISION_LENGTH);
		Division division = divisionNbrMap.get(divisionNbr);
		if (division == null)
		{
			System.out.println("\nCannot retrieve sales information - the division " + divisionNbr + " is invalid\n");
			return;
		}
		double sales = storeSalesDAO.getStoreSales(divisionNbr);
		if (sales >= 0)
			System.out.println("\nTotal sales for Division Number " + divisionNbr + " is " + StringUtil.getFormattedSales(sales));
		else
			System.out.println("\nUnable to retrieve sales for Division Number " + divisionNbr);
		System.out.println();
	}

	/*
	 * Display sales information for a given store.
	 * Use the divisionsNbrMap to verify the division is valid.
	 */
	private static void displaySalesByStore()
	{
		String divisionNbr = Validator.getString(sc, "Enter the division: ", DivisionDAO.DIVISION_LENGTH);
		Division division = divisionNbrMap.get(divisionNbr);
		if (division == null)
		{
			System.out.println("\nCannot retrieve sales information - the division " + divisionNbr + " is invalid\n");
			return;
		}
		String storeNbr = Validator.getString(sc, "Enter the store number: ", StoreDAO.STORE_LENGTH);
		double sales = storeSalesDAO.getStoreSales(divisionNbr, storeNbr);
		if (sales >= 0)
			System.out.println("\nTotal sales for Store Number " + storeNbr + " is " + StringUtil.getFormattedSales(sales));
		else
			System.out.println("\nCannot retrieve sales information for store " + storeNbr);
		System.out.println();
	}

	/*
	 * Display sales information on a store for a given year.
	 * Use the divisionsNbrMap to verify the division is valid.
	 */
	private static void displaySalesByStoreAndYear()
	{
		String divisionNbr = Validator.getString(sc, "Enter the division: ", DivisionDAO.DIVISION_LENGTH);
		Division division = divisionNbrMap.get(divisionNbr);
		if (division == null)
		{
			System.out.println("\nCannot retrieve sales information - the division " + divisionNbr + " is invalid\n");
			return;
		}
		String storeNbr = Validator.getString(sc, "Enter the store number: ", StoreDAO.STORE_LENGTH);
		int year = Validator.getInt(sc, "Enter the year: ");
		double sales = storeSalesDAO.getStoreSales(divisionNbr, storeNbr, year);
		if (sales >= 0)
			System.out.println("\nTotal sales for Store Number " + storeNbr + " and " + year + " is " + StringUtil.getFormattedSales(sales));
		else
			System.out.println("\nCannot retrieve sales information for store " + storeNbr + " and year " + year);
		System.out.println();
	}
	
	/*
	 * Display sales information for a given week.
	 */
	private static void displaySalesByWeek()
	{
		int year = Validator.getInt(sc, "Enter the year: ");
		int week = Validator.getInt(sc, "Enter the week: ", 0, 53);
		double sales = storeSalesDAO.getStoreSales(year, week);
		if (sales >= 0)
			System.out.println("\nTotal sales for Year " + year + " and Week " + week + " is " + StringUtil.getFormattedSales(sales));
		else
			System.out.println("\nCannot retrieve sales information for Year " + year + " and Week " + week);
		System.out.println();
	}
	
	/*
	 * Display all sales information for a given store.
	 * Use the divisionsNbrMap to verify the division is valid.
	 */
	private static void displayAllSalesByStore()
	{
		String divisionNbr = Validator.getString(sc, "Enter the division: ", DivisionDAO.DIVISION_LENGTH);
		Division division = divisionNbrMap.get(divisionNbr);
		if (division == null)
		{
			System.out.println("\nCannot retrieve sales information - the division " + divisionNbr + " is invalid\n");
			return;
		}
		String storeNbr = Validator.getString(sc, "Enter the store number: ", StoreDAO.STORE_LENGTH);
		ArrayList<StoreSales> storeSales = storeSalesDAO.getAllStoreSalesByStore(divisionNbr, storeNbr);
		if (storeSales.size() > 0)
		{
			System.out.println("\nYear\tWeek\tSales");
			for (StoreSales ss : storeSales)
			{
				System.out.println(ss.getYear() + "\t" + ss.getWeek() + "   \t"+ StringUtil.getFormattedSales(ss.getSales()));
			}
		}
		else
			System.out.println("\nStore " + storeNbr + " does not have any sales.");
		System.out.println();
		
	}
	
	/*
	 * 	Populate both Division HashMaps from all divisions in the database
	 */
	private static void populateDivisionsMap()
	{
		ArrayList<Division> divisions = divisionDAO.getAllDivisions();
		for (Division d : divisions)
		{
			divisionIDMap.put(d.getId(), d);
			divisionNbrMap.put(d.getDivisionNbr(), d);
		}
	}
}

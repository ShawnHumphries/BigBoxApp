package bigbox.presentation;

import java.util.ArrayList;
import java.util.Scanner;

import bigbox.business.Store;
import bigbox.db.DAOFactory;
import bigbox.db.StoreDAO;
import bigbox.util.Validator;

public class BigBoxApp {

	private static Scanner sc = new Scanner(System.in);
	private static StoreDAO storeDAO = DAOFactory.getStoreDAO();

	public static void main(String[] args) {

		String command = "";
		
		// Display a welcome message
		System.out.println("Welcome to the Big Box application\n");

		
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
			case "help":
				break;
			case "exit":
				break;
			}
		}
		sc.close();
	}

	private static void displayMenu()
	{
		System.out.println("COMMAND MENU");
		System.out.println("list\t- List all stores");
		System.out.println("div\t- List all stores for a division");
		System.out.println("add\t- Add a store");
		System.out.println("del\t- Delete a store");
		System.out.println("help\t- Show this menu");
		System.out.println("exit\t- Exit this application");
		System.out.println();
	}
	
	private static void displayAllStores()
	{
		ArrayList<Store> stores = storeDAO.getAllStores();
		if (stores.size() > 0)
		{
			for (Store store : stores)
			{
				System.out.println(store.toString());
			}
		}
		else
			System.out.println("Big Box App does not have any stores.");
		System.out.println();
	}
	
	private static void displayStoreByDivision()
	{
		String divisionNbr = Validator.getString(sc, "Enter a division: ", StoreDAO.DIV_LENGTH);
		ArrayList<Store> storesByDiv = storeDAO.getAllStoresByDivision(divisionNbr);
		System.out.println();
		if (storesByDiv.size() > 0)
		{
			for (Store store : storesByDiv)
			{
				System.out.println(store.toString());
			}
		}
		else
			System.out.println("No stores found for Division = " + divisionNbr);
		System.out.println();
	}
	
	private static void addStore()
	{
		String divisionNbr = Validator.getString(sc, "Enter the division: ", StoreDAO.DIV_LENGTH);
		String storeNbr = Validator.getString(sc, "Enter the store number: ", StoreDAO.STORE_LENGTH);
		String storeName = Validator.getString(sc, "Enter the store's name: ");
		String storeAddr = Validator.getString(sc, "Enter the store's address: ");
		String storeCity = Validator.getString(sc, "Enter the store's city: ");
		String storeState = Validator.getString(sc, "Enter the store's state: ");
		String storeZip = Validator.getString(sc, "Enter the store's zip code: ");
		double storeSales = Validator.getDouble(sc, "Enter the store's sales: ");
		// create a new Store with an ID of -1 to indicate the store is a new facility and needs a new ID assigned
		Store newStore = new Store(-1, divisionNbr, storeNbr, storeSales, storeName, storeAddr, storeCity, storeState, storeZip);
		if (storeDAO.addStore(newStore))
			System.out.println("Store number " + storeNbr + " has been added.\n");
		else
			System.out.println("Store number " + storeNbr + " was not added successfully.\n");
	}
	
	private static void deleteStore()
	{
		String divisionNbr = Validator.getString(sc, "Enter the division: ", StoreDAO.DIV_LENGTH);
		String storeNbr = Validator.getString(sc, "Enter the store number: ", StoreDAO.STORE_LENGTH);
		Store store = storeDAO.getStoreByDivisionAndStoreNumber(divisionNbr, storeNbr);
		if (store != null)
			// Delete this store
			if (storeDAO.deleteStore(store))
				System.out.println("Store number " + storeNbr + " has been deleted successfully.\n");
			else
				System.out.println("Store number " + storeNbr + " was not deleted successfully.\n");
		else
			System.out.println("Store number " + storeNbr + " was not deleted successfully.\n");
	}
}

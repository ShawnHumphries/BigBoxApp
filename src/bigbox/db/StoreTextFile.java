package bigbox.db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import bigbox.business.Store;

public class StoreTextFile implements StoreDAO {

	private ArrayList<Store> stores = null;
	private Path storesPath = null;
	private File storesFile = null;
	private final String FIELD_SEP = "\t";

	public StoreTextFile()
	{
		storesPath = Paths.get("stores.txt");
		storesFile = storesPath.toFile();
		stores = getAllStores();
	}

	public ArrayList<Store> getAllStores()
	{
		// if the stores file has already been read, don't read it agai
		if (stores != null)
			return stores;
		
		stores = new ArrayList<>();
		if (Files.exists(storesPath))	// prevent the FileNotFoundException
		{
			try (BufferedReader in = new BufferedReader(new FileReader(storesFile)))
			{
				// read all stores 'stored' in the file into the array list
				String line = in.readLine();
				while (line != null)
				{
					String[] storeData = line.split(FIELD_SEP);
					int id = Integer.parseInt(storeData[0]);
					String divisionNbr = storeData[1];
					String storeNbr = storeData[2];
					double sales = Double.parseDouble(storeData[3]);
					String name = storeData[4];
					String address = storeData[5];
					String city = storeData[6];
					String state = storeData[7];
					String zip = storeData[8];
					
					Store store = new Store(id, divisionNbr, storeNbr, sales, name, address, city, state, zip);
					stores.add(store);
					
					line = in.readLine();
				}
			}
			catch (IOException ioe)
			{
				System.out.println(ioe);
				return null;
			}
		}
		return stores;
	}
	
	private boolean saveStores()
	{
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(storesFile))))
		{
			// write all stores in the array list to the stores.txt file
			for (Store store : stores)
			{
				out.print(store.getId() + FIELD_SEP);
				out.print(store.getDivisionNbr() + FIELD_SEP);
				out.print(store.getStoreNbr() + FIELD_SEP);
				out.print(store.getSales() + FIELD_SEP);
				out.print(store.getName() + FIELD_SEP);
				out.print(store.getAddress() + FIELD_SEP);
				out.print(store.getCity() + FIELD_SEP);
				out.print(store.getState() + FIELD_SEP);
				out.println(store.getZip() + FIELD_SEP);
			}
		}
		catch (IOException ioe)
		{
			System.out.println(ioe);
			return false;
		}
		return true;
	}
	
	@Override
	public Store getStoreByDivisionAndStoreNumber(String inDiv, String inStoreNbr)
	{
		for (Store store : stores)
		{
			String divisionNbr = store.getDivisionNbr();
			String storeNbr = store.getStoreNbr();
			if (divisionNbr.equals(inDiv) && storeNbr.equals(inStoreNbr))
				return store;
		}
		return null;
	}

	@Override
	public ArrayList<Store> getAllStoresByDivision(String inDiv)
	{
		ArrayList<Store> storesByDiv = new ArrayList<>();
		
		for (Store store : stores)
		{
			String divisionNbr = store.getDivisionNbr();
			if (divisionNbr.equals(inDiv))
			{
				storesByDiv.add(store);
			}
		}
		return storesByDiv;
	}

	@Override
	public boolean addStore(Store inStore)
	{
		if (inStore.getId() == -1)
		{
			// the id needs to be determined
			inStore.setId(getNextId());
		}
		if (stores.add(inStore))
			return saveStores();
		else
			return false;
	}

	@Override
	public boolean deleteStore(Store inStore)
	{
		if (stores.remove(inStore))
			return saveStores();
		else
			return false;
	}
	
	private int getNextId()
	{
		int maxID = 1;
		for (Store store : stores)
		{
			maxID = Math.max(maxID, store.getId());
		}
		return maxID+1;
	}
}

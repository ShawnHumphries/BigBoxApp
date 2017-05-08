package bigbox.db;

import java.util.ArrayList;

import bigbox.business.Store;

public class StoreArray implements StoreDAO {

	private ArrayList<Store> stores = null;
	
	public StoreArray()
	{
		stores = getAllStores();
	}
	
	public ArrayList<Store> getAllStores()
	{
		if (stores == null)
		{
			// Create a new list of stores and populate some data
			stores = new ArrayList<>();
			stores.add(new Store(1,"001","00011",25000.00,"Mason BigBox","5711 Fields Ertel Rd.", "Mason", "OH","45249"));
			stores.add(new Store(2,"001","00255",27500.00,"Downtown BigBox","9330 Main St.", "Cincinnati", "OH","45202"));
			stores.add(new Store(3,"001","00172",32555.55,"Goshen BigBox","6777 Goshen Rd.", "Goshen", "OH","45122"));
			stores.add(new Store(4,"001","00075",21425.37,"Bridgetown BigBox","3888 Race Rd.", "Cincinnati", "OH","45211"));
			stores.add(new Store(5,"111","00001",14432.77,"Louisville BigBox","1111 Washington St.", "Louisville", "KY","40206"));
			stores.add(new Store(6,"111","00044",17555.11,"Lawrenceburg BigBox","8000 Liberty St.", "Louisville", "KY","40204"));
			return stores;
			
		}
		else
			return stores;
		
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
		return stores.add(inStore);
	}

	@Override
	public boolean deleteStore(Store inStore)
	{
		return stores.remove(inStore);
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

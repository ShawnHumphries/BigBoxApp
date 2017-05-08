package bigbox.db;

import bigbox.db.division.DivisionDAO;
import bigbox.db.division.DivisionDB;
import bigbox.db.store.StoreDAO;
import bigbox.db.store.StoreDB;
import bigbox.db.storesales.StoreSalesDAO;
import bigbox.db.storesales.StoreSalesDB;

public class DAOFactory {

	public static DivisionDAO getDivisionDAO()
	{
		DivisionDAO dDAO = new DivisionDB();
		return dDAO;
	}
	
	public static StoreDAO getStoreDAO()
	{
		StoreDAO sDAO = new StoreDB();
		return sDAO;
	}
	
	public static StoreSalesDAO getStoreSalesDAO()
	{
		StoreSalesDAO ssDAO = new StoreSalesDB();
		return ssDAO;
	}
}

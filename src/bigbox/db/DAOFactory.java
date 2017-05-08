package bigbox.db;

public class DAOFactory {

	public static StoreDAO getStoreDAO()
	{
		StoreDAO sDAO = new StoreDB();
		return sDAO;
	}
}

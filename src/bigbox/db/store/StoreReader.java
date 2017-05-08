package bigbox.db.store;

import java.util.ArrayList;

import bigbox.business.Store;

public interface StoreReader {

	Store getStoreByDivisionAndStoreNumber(String inDivNbr, String inStoreNbr);
	ArrayList<Store> getAllStores();
	ArrayList<Store> getAllStoresByDivision(String inDivNbr);
}

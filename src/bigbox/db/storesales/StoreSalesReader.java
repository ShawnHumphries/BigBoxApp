package bigbox.db.storesales;

import java.util.ArrayList;

import bigbox.business.StoreSales;

public interface StoreSalesReader {

	double getStoreSales(String inDivNbr);
	double getStoreSales(String inDivNbr, String inStoreNbr);
	double getStoreSales(String inDivNbr, String inStoreNbr, int inYear);
	double getStoreSales(int inYear, int inWeek);
	
	ArrayList<StoreSales> getAllStoreSalesByStore(String inDivNbr, String inStoreNbr);
}

package bigbox.db.store;

import bigbox.business.Store;

public interface StoreWriter {

	boolean addStore(Store inStore);
	boolean deleteStore(Store inStore);
}

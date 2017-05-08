

public class StoreDB {
	
	public static Store getStoreByDivisionStore(String inDiv, String inStore) {
		Store store=null;
		if (inDiv.equals("001")) {
			if (inStore.equals("00011")) {
				store = new Store(1,"001","00011",25000.00,"Mason BigBox","5711 Fields Ertel Rd.", "Mason", "OH","45249");
			}
			else if (inStore.equals("00255")) {
				store = new Store(2,"001","00255",27500.00,"Downtown BigBox","9330 Main St.", "Cincinnati", "OH","45202");
			}
			else if (inStore.equals("00172")) {
				store = new Store(3,"001","00172",32555.55,"Goshen BigBox","6777 Goshen Rd.", "Goshen", "OH","45122");
			}
			else if (inStore.equals("00075")) {
				store = new Store(4,"001","00075",21425.37,"Bridgetown BigBox","3888 Race Rd.", "Cincinnati", "OH","45211");
			}
		}
		else if (inDiv.equals("111")) {
			if (inStore.equals("00001")) {
				store = new Store(5,"111","00001",14432.77,"Louisville BigBox","1111 Washington St.", "Louisville", "KY","40206");
			}
			else if (inStore.equals("00044")) {
				store = new Store(6,"111","00044",17555.11,"Lawrenceburg BigBox","8000 Liberty St.", "Louisville", "KY","40204");
			}
		}
		return store;
	}

}

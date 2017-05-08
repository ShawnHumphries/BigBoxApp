package bigbox.db.store;

import java.sql.*;
import java.util.ArrayList;

import bigbox.business.Store;
import bigbox.util.DBUtil;

public class StoreDB implements StoreDAO {

	private ArrayList<Store> stores = null;

	@Override
	public Store getStoreByDivisionAndStoreNumber(String inDivNbr, String inStoreNbr)
	{
		String sql = "SELECT s.ID, s.DivisionID, s.StoreNbr, s.Name, s.Address, s.City, s.State, s.Zip "
				+ "from divisions d, stores s "
				+ "where s.DivisionID = d.ID "
				+ "and d.divisionNbr = ? "
				+ "and s.StoreNbr = ?";
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql))
        {
        	ps.setString(1, inDivNbr);
        	ps.setString(2, inStoreNbr);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                return getStoreFromRow(rs);
            }
            else
            {
            	rs.close();
            	return null;
            }
        }
        catch (SQLException e)
        {
        	System.out.println(e);
        	return null;
        }
	}

	@Override
	public ArrayList<Store> getAllStores()
	{
		stores = new ArrayList<>();
		String sql = "SELECT s.ID, s.DivisionID, s.StoreNbr, s.Name, s.Address, s.City, s.State, s.Zip " 
				+ "from divisions d, stores s "
				+ "where d.ID = s.DivisionID "
				+ "order by d.divisionNbr, s.StoreNbr";
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery())
        {
        	while (rs.next())
        	{
        		Store s = getStoreFromRow(rs);
        		stores.add(s);
        	}
        }
        catch (SQLException e)
        {
        	System.out.println(e);
        	return null;
        }
		return stores;
	}

	@Override
	public ArrayList<Store> getAllStoresByDivision(String inDivNbr)
	{
		stores = new ArrayList<>();
		String sql = "SELECT s.ID, s.DivisionID, s.StoreNbr, s.Name, s.Address, s.City, s.State, s.Zip "
				+ "from divisions d, stores s "
				+ "where s.DivisionID = d.ID "
				+ "and d.DivisionNbr = ? "
				+ "order by s.StoreNbr";
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql))
        {
        	ps.setString(1, inDivNbr);
            ResultSet rs = ps.executeQuery();
        	while (rs.next())
        	{
        		Store s = getStoreFromRow(rs);
        		stores.add(s);
        	}
        }
        catch (SQLException e)
        {
        	System.out.println(e);
        	return null;
        }
		return stores;
	}

	@Override
	public boolean addStore(Store inStore)
	{
		String sql = "INSERT INTO Stores (DivisionID, StoreNbr, Name, Address, City, State, Zip) VALUES (?, ?, ?, ?, ?, ?, ?)";
		Connection connection = DBUtil.getConnection();
		try (PreparedStatement ps = connection.prepareStatement(sql))
		{
			ps.setInt(1, inStore.getDivisionID());
			ps.setString(2, inStore.getStoreNbr());
			ps.setString(3, inStore.getName());	
			ps.setString(4, inStore.getAddress());
			ps.setString(5, inStore.getCity());
			ps.setString(6, inStore.getState());
			ps.setString(7, inStore.getZip());
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println(e);
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteStore(Store inStore)
	{
		String sql = "DELETE FROM stores WHERE DivisionId = ? AND StoreNbr = ?";
		Connection connection = DBUtil.getConnection();
		try (PreparedStatement ps = connection.prepareStatement(sql))
		{
			ps.setInt(1, inStore.getDivisionID());
			ps.setString(2, inStore.getStoreNbr());
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println(e);
			return false;
		}
		return true;
	}
	
	private static Store getStoreFromRow(ResultSet rs) throws SQLException
	{
        Store store = new Store();

        store.setId(rs.getInt("ID"));
        store.setDivisionID(rs.getInt("DivisionID"));
        store.setStoreNbr(rs.getString("StoreNbr"));
        store.setName(rs.getString("Name"));
        store.setAddress(rs.getString("Address"));
        store.setCity(rs.getString("City"));
        store.setState(rs.getString("State"));
        store.setZip(rs.getString("Zip"));
    	
    	return store;
	}
}

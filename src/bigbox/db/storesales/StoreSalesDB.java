package bigbox.db.storesales;

import java.sql.*;
import java.util.ArrayList;

import bigbox.business.StoreSales;
import bigbox.util.DBUtil;

public class StoreSalesDB implements StoreSalesDAO {

	private ArrayList<StoreSales> storeSales = null;

	// Return the store sales for a particular division
	@Override
	public double getStoreSales(String inDivNbr) {
		
		String sql = "SELECT DivisionNbr, sum(Sales) as Sales "
				+ "from divisions d, stores s, store_sales ss "
				+ "where s.ID = ss.StoreID "
				+ "and d.ID = s.DivisionID "
				+ "and d.DivisionNbr = ? "
				+ "group by (d.DivisionNbr)";
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql))
        {
        	ps.setString(1, inDivNbr);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
            	return rs.getDouble("Sales");
            }
            else
            {
            	rs.close();
            	return -1;
            }
        }
        catch (SQLException e)
        {
        	System.out.println(e);
        	return -1;
        }
	}

	@Override
	// Return the store sales for a particular division and store
	public double getStoreSales(String inDivNbr, String inStoreNbr) {
		
		String sql = "SELECT DivisionNbr, StoreNbr, sum(Sales) as Sales "
				+ "from divisions d, stores s, store_sales ss "
				+ "where s.ID = ss.StoreID "
				+ "and d.ID = s.DivisionID "
				+ "and d.DivisionNbr = ? "
				+ "and s.StoreNbr = ? "
				+ "group by (ss.StoreID)";
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql))
        {
        	ps.setString(1, inDivNbr);
        	ps.setString(2, inStoreNbr);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
            	return rs.getDouble("Sales");
            }
            else
            {
            	rs.close();
            	return -1;
            }
        }
        catch (SQLException e)
        {
        	System.out.println(e);
        	return -1;
        }
	}

	// Return the store sales for a particular division and store, for a particular year
	@Override
	public double getStoreSales(String inDivNbr, String inStoreNbr, int inYear) {
		
		String sql = "SELECT DivisionNbr, StoreNbr, sum(Sales) as Sales "
				+ "from divisions d, stores s, store_sales ss "
				+ "where s.ID = ss.StoreID "
				+ "and d.ID = s.DivisionID "
				+ "and d.DivisionNbr = ? "
				+ "and s.StoreNbr = ? "
				+ "and ss.Year = ? "
				+ "group by (ss.StoreID)";
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql))
        {
        	ps.setString(1, inDivNbr);
        	ps.setString(2, inStoreNbr);
        	ps.setInt(3, inYear);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
            	return rs.getDouble("Sales");
            }
            else
            {
            	rs.close();
            	return -1;
            }
        }
        catch (SQLException e)
        {
        	System.out.println(e);
        	return -1;
        }
	}

	@Override
	public double getStoreSales(int inYear, int inWeek) {

		String sql = "SELECT year, week, Sum(Sales) as Sales "
				+ "from store_sales "
				+ "where year = ? "
				+ "and week = ?";
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql))
        {
        	ps.setInt(1, inYear);
        	ps.setInt(2, inWeek);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
            	return rs.getDouble("Sales");
            }
            else
            {
            	rs.close();
            	return -1;
            }
        }
        catch (SQLException e)
        {
        	System.out.println(e);
        	return -1;
        }
	}

	@Override
	public ArrayList<StoreSales> getAllStoreSalesByStore(String inDivNbr, String inStoreNbr) {

		storeSales = new ArrayList<>();
		String sql = "SELECT ss.ID, ss.StoreID, ss.Year, ss.Week, ss.Sales "
				+ "FROM stores s, store_sales ss, divisions d "
				+ "WHERE s.ID = ss.StoreID "
				+ "AND s.DivisionID = d.ID "
				+ "AND d.DivisionNbr = ? "
				+ "AND s.StoreNbr = ?";
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql))
        {
        	ps.setString(1, inDivNbr);
        	ps.setString(2, inStoreNbr);
            ResultSet rs = ps.executeQuery();
        	while (rs.next())
        	{
        		StoreSales ss = getStoreSalesFromRow(rs);
        		storeSales.add(ss);
        	}
        }
        catch (SQLException e)
        {
        	System.out.println(e);
        	return null;
        }
		return storeSales;
	}

	private StoreSales getStoreSalesFromRow(ResultSet rs) throws SQLException {

		StoreSales storeSales = new StoreSales();

        storeSales.setID(rs.getInt("ID"));
        storeSales.setStoreID(rs.getInt("StoreID"));
        storeSales.setYear(rs.getInt("Year"));
        storeSales.setWeek(rs.getInt("Week"));
        storeSales.setSales(rs.getDouble("Sales"));
    	
    	return storeSales;
	}

}

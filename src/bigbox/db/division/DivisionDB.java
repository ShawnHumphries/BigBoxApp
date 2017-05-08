package bigbox.db.division;

import java.sql.*;
import java.util.ArrayList;

import bigbox.business.Division;
import bigbox.util.DBUtil;

public class DivisionDB implements DivisionDAO {

	private ArrayList<Division> divisions = null;

	@Override
	public ArrayList<Division> getAllDivisions() {

		divisions = new ArrayList<>();
		String sql = "SELECT * FROM divisions";
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery())
        {
        	while (rs.next())
        	{
        		Division d = getDivisionFromRow(rs);
        		divisions.add(d);
        	}
        }
        catch (SQLException e)
        {
        	System.out.println(e);
        	return null;
        }
		return divisions;
	}

	@Override
	public Division getDivisionByID(int divisionID) {

		String sql = "SELECT * FROM divisions WHERE ID = ?";
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql))
        {
        	ps.setInt(1, divisionID);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                return getDivisionFromRow(rs);
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
	public Division getDivisionByNumber(String divisionNbr) {

		String sql = "SELECT * FROM divisions WHERE DivisionNbr = ?";
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql))
        {
        	ps.setString(1, divisionNbr);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                return getDivisionFromRow(rs);
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

	private static Division getDivisionFromRow(ResultSet rs) throws SQLException
	{
		Division division = new Division();

		division.setId(rs.getInt("ID"));
        division.setDivisionNbr(rs.getString("DivisionNbr"));
        division.setName(rs.getString("Name"));
        division.setAddress(rs.getString("Address"));
        division.setCity(rs.getString("City"));
        division.setState(rs.getString("State"));
        division.setZip(rs.getString("Zip"));
    	
    	return division;
	}

}

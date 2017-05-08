package bigbox.db.division;

import java.util.ArrayList;

import bigbox.business.Division;

public interface DivisionReader {

	ArrayList<Division> getAllDivisions();
	Division getDivisionByID(int divisionID);
	Division getDivisionByNumber(String divisionNbr);
}

package bigbox.util;

import java.text.NumberFormat;

public class StringUtil {

	public static String padWithSpaces(String s, int length)
    {
        if (s.length() < length)
        {
            StringBuilder sb = new StringBuilder(s);
            while(sb.length() < length)
            {
                sb.append(" ");
            }
            return sb.toString();
        }
        else
        {
            return s.substring(0, length);
        }
    }

	public static String getFormattedSales(double sales)
	{
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(sales);
	}
	
}

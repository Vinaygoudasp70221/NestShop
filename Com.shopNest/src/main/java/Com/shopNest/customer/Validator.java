 package Com.shopNest.customer;

import Com.shopNest.dbhandler.DataFetcher;

public class Validator 
{
	public static Boolean isValid(String usn,String psw)
	{
		
		String fpsw = DataFetcher.featchPassword(usn);
		if(fpsw.equals(psw))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}

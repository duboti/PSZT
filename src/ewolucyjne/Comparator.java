package ewolucyjne;


public class Comparator implements java.util.Comparator<Osobnik> {
	public int compare(Osobnik os1, Osobnik os2)
	{
		if ( os1.pobierzWartosc() != os2.pobierzWartosc() ) 
		{
			if ( os1.pobierzWartosc() > os2.pobierzWartosc() ) 
			{
				return 1;
			}
			else
				return -1;
		}
		else
			return 0;
	}
}

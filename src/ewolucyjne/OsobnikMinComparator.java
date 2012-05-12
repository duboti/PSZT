package ewolucyjne;

import java.util.Comparator;


public class OsobnikMinComparator implements Comparator<Osobnik> {
	public int compare(Osobnik osobnik1, Osobnik osobnik2)
	{
		if (osobnik1.pobierzWartosc() > osobnik2.pobierzWartosc())
		{
			return 1;
		}
		else if (osobnik1.pobierzWartosc() < osobnik2.pobierzWartosc())
		{
			return -1;
		}
		else 
			return 0;
	}
}

package ewolucyjne;

import java.util.Comparator;


public class OsobnikComparator implements Comparator<Osobnik> {
	public int compare(Osobnik osobnik1, Osobnik osobnik2)
	{
		return (int) (osobnik1.pobierzWartosc() - osobnik2.pobierzWartosc());
	}
}

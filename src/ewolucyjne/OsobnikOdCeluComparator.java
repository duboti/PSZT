package ewolucyjne;

import java.util.Comparator;

/**
 * Komparator odleg³oœci elementu od celu
 *
 */
public class OsobnikOdCeluComparator implements Comparator<Osobnik> {
	private float cel;
	
	public OsobnikOdCeluComparator(float cel) {
		super();
		this.cel = cel;
	}

	public int compare(Osobnik osobnik1, Osobnik osobnik2)
	{
		if (Math.abs(this.cel-osobnik1.pobierzWartosc()) > Math.abs(this.cel-osobnik2.pobierzWartosc()))
		{
			return 1;
		}
		else if (Math.abs(this.cel-osobnik1.pobierzWartosc()) < Math.abs(this.cel-osobnik2.pobierzWartosc()))
		{
			return -1;
		}
		else 
			return 0;
	}
}

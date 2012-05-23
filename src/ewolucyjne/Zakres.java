/**
 * 
 */
package ewolucyjne;

/**
 * @author Daniel
 *
 */
public class Zakres {
	
	private Double begin;
	private Double end;
	
	public Zakres ( Double minX , Double maxX )
	{
		this.begin = minX;
		this.end = maxX;
	}
	
	public Double poczatek()
	{
		return this.begin;
	}
	
	public Double koniec()
	{
		return this.end;
	}
	
	public boolean czyNalezy( Float in )
	{
		return (in >= this.begin && in <= this.end);
	}
}

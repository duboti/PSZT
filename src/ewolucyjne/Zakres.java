/**
 * 
 */
package ewolucyjne;

/**
 * @author Daniel
 *
 */
public class Zakres {
	
	private Float begin;
	private Float end;
	
	public Zakres ( Float begin , Float end )
	{
		this.begin = begin;
		this.end = end;
	}
	
	public Float poczatek()
	{
		return this.begin;
	}
	
	public Float koniec()
	{
		return this.end;
	}
	
	public boolean czyNalezy( Float in )
	{
		return (in >= this.begin && in <= this.end);
	}
}

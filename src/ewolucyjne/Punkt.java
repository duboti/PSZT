package ewolucyjne;

import java.util.ArrayList;

public class Punkt implements Osobnik {

	private float wspX;
	private float wspY;
	private float sigma;
	private float warFun;
	
	Punkt( float wspX , float wspY , float sigma , FunkcjaPrzystosowania func )
	{
		this.wspX = wspX;
		this.wspY = wspY;
		this.sigma = sigma;
		
		ArrayList<Float> tmp = new ArrayList<Float>();
		tmp.add(wspX);
		tmp.add(wspY);
		this.warFun = func.Oblicz(tmp);
	}
	
	@Override
	public float pobierzWartosc() 
	{
		return warFun;
	}

	@Override
	public float pobierzParametr(int n) 
	{
		if( n == 0 )
			return wspX;
		else
			{
				if( n == 1 )
					return wspY;
				else
					return 0;
			}
	}

	@Override
	public float pobierzSigme(int n) 
	{
		if( n == 0 )
			return sigma;
		else
			return 0;
	}

	@Override
	public void mutuj() {
		// TODO
		
	}

	
}

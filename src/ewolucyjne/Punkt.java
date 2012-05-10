package ewolucyjne;

import java.util.ArrayList;
import java.util.Random;

public class Punkt implements Osobnik {

	private float wspX;
	private float wspY;
	private float sigmaX;
	private float sigmaY;
	private float warFun;
	private FunkcjaPrzystosowania func;
	Punkt( )
	{}
	
	@Override
	public void inicjowanie( ArrayList<Float> parametry, ArrayList<Float> sigmy , FunkcjaPrzystosowania fun)
	{
		this.wspX = parametry.get(0);
		this.wspY = parametry.get(1);
		this.sigmaX = sigmy.get(0);
		this.sigmaY = sigmy.get(1);
		
		ArrayList<Float> tmp = new ArrayList<Float>();
		tmp.add(wspX);
		tmp.add(wspY);
		this.warFun = fun.Oblicz(tmp);
		this.func = fun;
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
			return sigmaX;
		else
			{
				if( n == 1 )
					return sigmaY;
				else
					return 0;
			}
	}

	@Override
	public void mutuj() {
			Random gen = new Random();
			double N = gen.nextGaussian();
			double t1 = 1 / 2;
			double t = 1 / Math.sqrt(2 * Math.sqrt(2));
			sigmaX *= Math.exp(t1*N + t*gen.nextGaussian());
			sigmaY *= Math.exp(t1*N + t*gen.nextGaussian());
			wspX += sigmaX * gen.nextGaussian();
			wspY += sigmaY * gen.nextGaussian();
			ArrayList<Float> tmp = new ArrayList<Float>();
			tmp.add(wspX);
			tmp.add(wspY);
			this.warFun = this.func.Oblicz(tmp);
	}

	@Override
	public int pobierzIloscParametrow() 
	{
		return 2;
	}
	
	
	public String toString()
	{
		String napis;
		napis = " f="+warFun+" x="+wspX+" y="+wspY+"\n";
		return napis;
	}
	
}

package ewolucyjne;

import java.util.ArrayList;
import java.util.Random;

public class Punkt implements Osobnik {

	private double wspX;
	private double wspY;
	private double sigmaX;
	private double sigmaY;
	private double warFun;
	private FunkcjaPrzystosowania func;
	Punkt( )
	{}
	
	@Override
	public void inicjowanie( ArrayList<Double> parametry, ArrayList<Double> sigmy , FunkcjaPrzystosowania fun)
	{
		this.wspX = parametry.get(0);
		this.wspY = parametry.get(1);
		this.sigmaX = sigmy.get(0);
		this.sigmaY = sigmy.get(1);
		
		ArrayList<Double> tmp = new ArrayList<Double>();
		tmp.add(wspX);
		tmp.add(wspY);
		this.warFun = fun.Oblicz(tmp);
		this.func = fun;
	}
	@Override
	public double pobierzWartosc() 
	{
		return warFun;
	}

	@Override
	public double pobierzParametr(int n) 
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
	public double pobierzSigme(int n) 
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
			ArrayList<Double> tmp = new ArrayList<Double>();
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
		napis = "f="+warFun+"\tx="+wspX+"\ty="+wspY+"\n";
		return napis;
	}
	
}

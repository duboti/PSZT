package ewolucyjne;

import java.util.*;

public class Algorytm 
{
	private int mi;
	private int lambda;
	private int rodzajAlgorytmu;
	private float wspolczynnikInterpolacji;
	private ArrayList<Zakres> zakres;
	private ArrayList<Float> sigmy;
	
	public ArrayList<Osobnik> populacja;
	//private ArrayList<Osobnik> potomkowie;
	
	Algorytm (int mi, int lambda, int rodzajAlgorytmu, float wspolczynnikInterpolacji, 
			ArrayList<Zakres> zakres, ArrayList<Float> sigmy)
	{
		this.mi = mi;
		this.lambda = lambda;
		this.rodzajAlgorytmu = rodzajAlgorytmu;
		this.wspolczynnikInterpolacji = wspolczynnikInterpolacji;
		this.zakres = zakres;
		this.sigmy = sigmy;
		
		for (int i=0; i<this.mi ; i++)
		{
			populacja.add(this.stworzOsobnika(zakres, this.sigmy));
			
		}
		
	}
	
	Osobnik stworzPotomka(Osobnik mama, Osobnik tata) 
	{
		
		return null;
	}
	
	Osobnik stworzOsobnika(ArrayList<Zakres> zakresy, ArrayList<Float> sigmy) 
	{
		return null;
	}
	
	ArrayList<Osobnik> stworzNastepnePokolenie() 
	{
		
		return null;
	} 
	
	void Selekcja(ArrayList<Osobnik> potomkowie) 
	{
		if (rodzajAlgorytmu==0)
		{
			populacja.addAll(potomkowie);
			Collections.sort(populacja, new OsobnikComparator());
			for (int i=mi+lambda ; i>mi ; i-- )
			{
				populacja.remove(i);
			}
		}
	}
	
	boolean warunekStopu() 
	{
		return false;
	}
	
	Osobnik wybierzNajlepszego() 
	{
		return null;
	}
	
	
}

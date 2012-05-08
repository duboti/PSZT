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
	String typOsobnika = "Punkt";
	
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
			populacja.add(this.stworzOsobnika(typOsobnika, zakres, this.sigmy));
			
		}
		
	}
	
	Osobnik stworzPotomka(Osobnik mama, Osobnik tata) 
	{
		
		return null;
	}
	
	Osobnik stworzOsobnika(String typOsobnika, ArrayList<Zakres> zakresy, ArrayList<Float> sigmy) 
	{
		Osobnik nowyOsobnik = null;
		@SuppressWarnings("rawtypes")
		Class cl = null;
		try 
		{
			cl = Class.forName(typOsobnika);
		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println("Nie znaleziono klasy o nazwie "+typOsobnika);
			e.printStackTrace();
			return null;
		}
		try 
		{
			nowyOsobnik = (Osobnik)cl.newInstance();
		} 
		catch (InstantiationException e) 
		{
			System.out.println("Nie mo¿na stworzyæ klasy o nazwie "+typOsobnika);
			e.printStackTrace();
			return null;
		} 
		catch (IllegalAccessException e) 
		{
			System.out.println("Brak dostêpu do klasy o nazwie "+typOsobnika);
			e.printStackTrace();
			return null;
		}
		//TODO ustawiæ parametry osobnika takie jak sigmy itp.
		return nowyOsobnik;
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

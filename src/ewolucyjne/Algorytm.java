package ewolucyjne;

import java.util.*;


//TODO: "private int etapAlgorytmu" jest zle ustawiony, albo go usunac albo go porawic
//TODO: dokonczyc funkjce mutujPopulacje
public class Algorytm 
{
	private int mi;
	private int lambda;
	private int rodzajAlgorytmu;
	private float wspolczynnikInterpolacji;
	private ArrayList<Zakres> zakres;
//	private ArrayList<Float> sigmy;
	private int nrIteracji;
	private float najlepszyWynik;
	private int etapAlgorytmu;				//w planowo ma to byc enum
	private int etapBezPoprawy;
	private int maxIteracji;
	private float dokladnosc;
	String typOsobnika;
	private int maxBezPoprawy;
	FunkcjaPrzystosowania funkcja;
	
	public ArrayList<Osobnik> populacja;
	
	//private ArrayList<Osobnik> potomkowie;
	
	Algorytm (String typOsobnika, int mi, int lambda, int rodzajAlgorytmu, int maxIteracji, float dokladnosc, float wspolczynnikInterpolacji, 
			ArrayList<Zakres> zakres, ArrayList<Float> sigmy, FunkcjaPrzystosowania funkcja)
	{
		this.typOsobnika = typOsobnika;
		this.mi = mi;
		this.lambda = lambda;
		this.rodzajAlgorytmu = rodzajAlgorytmu;
		this.wspolczynnikInterpolacji = wspolczynnikInterpolacji;
		this.zakres = zakres;
//		this.sigmy = sigmy;
		this.nrIteracji = 0;
		this.etapAlgorytmu = 0;		
		this.etapBezPoprawy = 0;
		this.maxIteracji = maxIteracji;
		this.maxBezPoprawy = 5;
		this.dokladnosc = dokladnosc;
		this.funkcja = funkcja;
		
		Random generator = new Random(); 
		for (int i=0; i<this.mi ; i++)
		{
			ArrayList<Float> parametry = new ArrayList<Float>();
			for(int j = 0 ; j < this.zakres.size(); j++ )
				parametry.add( ( (this.zakres.get(j)).koniec() - (this.zakres.get(j)).poczatek() )*generator.nextFloat() + (this.zakres.get(j)).poczatek());
			populacja.add(this.stworzOsobnika(parametry, sigmy));	
		}
		Collections.sort(populacja, new OsobnikComparator());
		
		najlepszyWynik = populacja.get(0).pobierzWartosc();
	}
	
	Osobnik stworzPotomka(Osobnik mama, Osobnik tata) 
	{
		Osobnik potomek = null;
		ArrayList<Float> parametry = new ArrayList<Float>();
		ArrayList<Float> sigmy = new ArrayList<Float>();
		
		if (mama.pobierzWartosc() > tata.pobierzWartosc())
		{
			for (int i=0 ; i<mama.pobierzIloscParametrow() ; i++)
			{
				parametry.add(i, mama.pobierzParametr(i)*wspolczynnikInterpolacji+
						tata.pobierzParametr(i)*(1-wspolczynnikInterpolacji));
				sigmy.add(i, mama.pobierzSigme(i)*wspolczynnikInterpolacji+
						tata.pobierzSigme(i)*(1-wspolczynnikInterpolacji));
			}
		}
		else
		{
			for (int i=0 ; i<mama.pobierzIloscParametrow() ; i++)
			{
				parametry.add(i, tata.pobierzParametr(i)*wspolczynnikInterpolacji+
						mama.pobierzParametr(i)*(1-wspolczynnikInterpolacji));
				sigmy.add(i, tata.pobierzSigme(i)*wspolczynnikInterpolacji+
						mama.pobierzSigme(i)*(1-wspolczynnikInterpolacji));
			}
		}
		potomek = stworzOsobnika(parametry,sigmy);
		potomek.mutuj();
		return potomek;
	}
	
	Osobnik stworzOsobnika(ArrayList<Float> parametry, ArrayList<Float> sigmy) 
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
		nowyOsobnik.inicjowanie(parametry, sigmy, funkcja);
		return nowyOsobnik;
	}
	
	ArrayList<Osobnik> stworzNastepnePokolenie() 
	{
		if (etapAlgorytmu==1)	
		{
			ArrayList<Osobnik> nowePokolenie = new ArrayList<Osobnik>();
			Random generator = new Random(); 
			
			for (int i=0; i<lambda ; i++)
			{
				nowePokolenie.add(stworzPotomka(populacja.get(generator.nextInt(mi)), 
						populacja.get(generator.nextInt(mi))));	
			}
			
			etapAlgorytmu = 2;
				
			return nowePokolenie;
		}
		else
		{
			return null;
		}
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
		nrIteracji++;
		
		/**
		 * W tym miejscu jest warunek który okresla minimalizacje badz maksymalizacje
		 */
		if ( ( Math.abs( najlepszyWynik )- Math.abs(populacja.get(0).pobierzWartosc()) ) > dokladnosc)
		{
			etapBezPoprawy = 0;
			najlepszyWynik = populacja.get(0).pobierzWartosc();
		}
		else
		{
			etapBezPoprawy++;
		}
		
		etapAlgorytmu = 1;
	}
	
	ArrayList<Osobnik> pobierzPopulacje()
	{
		return this.populacja;
	}
	
	int pobierzNumerIteracji()
	{
		return this.nrIteracji;
	}
	
	void mutujPopulacje(ArrayList<Osobnik> potomkowie)
	{
		//TODO: 
		/*Dla algorytmu u+L : Losowanie do 5% osobnikow z populacji i wywolywanie metody mutuj*/
		/*Dla algorytmu u,L : Losowanie do 5% osobnikow z ArrayList<Osobnik> potomkowie i wywolywanie metody mutuj*/
	}
	
	boolean warunekStopu() 
	{
		return (nrIteracji>=maxIteracji || etapBezPoprawy>=maxBezPoprawy);
	}
	
	Osobnik wybierzNajlepszego() 
	{
		return populacja.get(0);
	}
	
	
}

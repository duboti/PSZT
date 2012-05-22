package ewolucyjne;

import java.util.*;

public class Algorytm 
{
	private int mi;
	private int lambda;
	private int rodzajAlgorytmu;	//0-mi+lambda, 1-mi,lambda
	private int rodzajWyboru;		//0-mi najlepszych, 1-mi losowych
	private int rodzajOptymalizacji;	//0-minimalizacja, 1-maxymalizacja, 2-konkretna wartoœæ
	private float celOptymalizacji;
	private float wspolczynnikInterpolacji;
	private int nrIteracji;
	private float najlepszyWynik;
	// etapy algorytmu 1 - czeka na stworzenie nowego pokolenia
	// etapy algorytmu 2 - czeka na mutacje populacji
	// etapy algorytmu 3 - czeka na selekcje nastêpnej populacji
	private int etapAlgorytmu;				//powinien byc enum
	private int etapBezPoprawy;
	private int maxIteracji;
	private float dokladnosc;
	String typOsobnika;
	private int maxBezPoprawy;
	FunkcjaPrzystosowania funkcja;
	private boolean stop;
	float procentMutacji;
	
	private ArrayList<Osobnik> populacja;
	
	
	
	Algorytm (String typOsobnika, int mi, int lambda, int rodzajAlgorytmu, int rodzajWyboru, int rodzajOptymalizacji, 
			float celOptymalizacji, int maxIteracji, int maxBezPoprawy, float dokladnosc, float procentMutacji,
			float wspolczynnikInterpolacji, ArrayList<Zakres> zakres, ArrayList<Float> sigmy, FunkcjaPrzystosowania funkcja)
	{
		this.typOsobnika = typOsobnika;
		this.mi = mi;
		this.lambda = lambda;
		this.rodzajAlgorytmu = rodzajAlgorytmu;
		this.rodzajWyboru = rodzajWyboru;
		this.rodzajOptymalizacji = rodzajOptymalizacji;
		this.celOptymalizacji = celOptymalizacji;
		this.wspolczynnikInterpolacji = wspolczynnikInterpolacji;
		this.maxIteracji = maxIteracji;
		this.maxBezPoprawy = maxBezPoprawy;
		this.dokladnosc = dokladnosc;
		this.funkcja = funkcja;
		this.procentMutacji = procentMutacji;
		
		this.nrIteracji = 0;
		this.etapAlgorytmu = 1;		
		this.etapBezPoprawy = 0;
		populacja = new ArrayList<Osobnik>();
		this.stop = false;
		
		
		Random generator = new Random(); 
		for (int i=0; i<this.mi ; i++)
		{
			ArrayList<Float> parametry = new ArrayList<Float>();
			for(int j = 0 ; j < zakres.size(); j++ )
				parametry.add( ( (zakres.get(j)).koniec() - (zakres.get(j)).poczatek() )*
											generator.nextFloat() + (zakres.get(j)).poczatek());
			populacja.add(this.stworzOsobnika(parametry, sigmy));	
		}
		Collections.sort(populacja, new OsobnikMinComparator());
		
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
	
	/**
	 * metoda zwraca nowo stworzone pokolenie gdy algorytm by³ w etapie 1 i pzrechodzi w etap 2
	 * w przeciwnym przypadku zwraca nulla
	 * @return ArrayList<Osobnik>
	 */
	ArrayList<Osobnik> stworzNastepnePokolenie() 
	{
		if (stop)
		{
			return null;
		}
		else if (etapAlgorytmu==1)	
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
	
	/**
	 * Wybiera spoœród starej populacji i nowego pokolenia now¹ populacje 
	 * w zaleznoœci od rodzaju algorytmu
	 * @param potomkowie
	 */
	void selekcja(ArrayList<Osobnik> potomkowie) 
	{
		if (stop)
		{	
		}
		else if (etapAlgorytmu==3)
		{
			if (rodzajAlgorytmu == 0)
			{
				populacja.addAll(potomkowie);
				wybierzOsobnikiNowejPopulacji();
				if (rodzajOptymalizacji==0)
				{
					Collections.sort(populacja, new OsobnikMinComparator());
				}
				else if (rodzajOptymalizacji==1)
				{
					Collections.sort(populacja, new OsobnikMaxComparator());
				}
			}
			else if (rodzajAlgorytmu == 1)
			{
				populacja.clear();
				populacja.addAll(potomkowie);
				wybierzOsobnikiNowejPopulacji();
				if (rodzajOptymalizacji==0)
				{
					Collections.sort(populacja, new OsobnikMinComparator());
				}
				else if (rodzajOptymalizacji==1)
				{
					Collections.sort(populacja, new OsobnikMaxComparator());
				}
			}
			else
				return;
			
			nrIteracji++;
			
			sprawdzPoprawe();
			
			czyStop();
			
			etapAlgorytmu = 1;
		}	
		
	}

	/**
	 * Wybiera now¹ populacje posiadaj¹c¹ mi osobników
	 * gdy rodzajWyboru == 0 jest to mi najlepszych
	 * gdy rodzaj wyboru == 1 jest to mi losowych
	 * w pozosta³ych przypadkach populacja jest po prostu ucinana do mi osobników
	 */
	private void wybierzOsobnikiNowejPopulacji() 
	{
		if (rodzajWyboru==0)
		{
			if (rodzajOptymalizacji==0)
			{
				Collections.sort(populacja, new OsobnikMinComparator());
			}
			else if (rodzajOptymalizacji==1)
			{
				Collections.sort(populacja, new OsobnikMaxComparator());
			}
			else if (rodzajOptymalizacji==2)
			{
				Collections.sort(populacja, new OsobnikOdCeluComparator(this.celOptymalizacji));
			}
		}
		else if (rodzajWyboru==1)
		{
			Collections.shuffle(populacja);
		}
		for (int i=populacja.size()-1 ; i>=mi ; i-- )
			{
				populacja.remove(i);
			}
	}
	
	/**
	 * W tym miejscu jest warunek który okresla minimalizacje badz maksymalizacje
	 * obecnie minimalizacja
	 */
	private void sprawdzPoprawe() 
	{
		if (rodzajOptymalizacji==0)
		{
			if ( (najlepszyWynik - populacja.get(0).pobierzWartosc() ) > dokladnosc)
			{
				etapBezPoprawy = 0;
			}
			else
			{
				etapBezPoprawy++;
			}
		}
		else if (rodzajOptymalizacji==1)
		{
			if ( (najlepszyWynik - populacja.get(0).pobierzWartosc() ) < dokladnosc)
			{
				etapBezPoprawy = 0;
			}
			else
			{
				etapBezPoprawy++;
			}
		}else if (rodzajOptymalizacji==2)
		{
			if ( Math.abs(celOptymalizacji - populacja.get(0).pobierzWartosc() ) < dokladnosc)
			{
				etapBezPoprawy = maxBezPoprawy;
			} 
			else
			{
				etapBezPoprawy = 0;
			}
		}
		najlepszyWynik = populacja.get(0).pobierzWartosc();
	}
	
	ArrayList<Osobnik> pobierzPopulacje()
	{
		return this.populacja;
	}
	
	int pobierzNumerIteracji()
	{
		return this.nrIteracji;
	}
	
	/**
	 * Dla algorytmu u+L : Losowanie osobnikow z populacji i wywolywanie metody mutuj
	 * Dla algorytmu u,L : Losowanie osobnikow z ArrayList<Osobnik> potomkowie i wywolywanie metody mutuj
	 * @param potomkowie
	 */
	void mutujPopulacje(ArrayList<Osobnik> potomkowie)
	{	
		if (stop)
		{
		}
		else if (etapAlgorytmu == 2)
		{
			Random generator = new Random();
			if (rodzajAlgorytmu==0)
			{
				for (Osobnik os: populacja)
				{
					if (generator.nextFloat() < procentMutacji)
						populacja.get(populacja.indexOf(os)).mutuj();
				}
			} else if(rodzajAlgorytmu==1)
			{
				for (Osobnik os: potomkowie)
				{
					if (generator.nextFloat() < procentMutacji)
						potomkowie.get(potomkowie.indexOf(os)).mutuj();
				}
			}
			etapAlgorytmu = 3;
		}
	}
	
	/**
	 * Zwraca prawdê gdy zosta³ spe³niony i algorytm powinien byc zatrzymany
	 * w przeciwnym przypadku fa³sz
	 * @return 
	 */
	boolean warunekStopu() 
	{
		return this.stop;
	}
	
	
	void czyStop() 
	{
		stop = (nrIteracji>=maxIteracji || etapBezPoprawy>=maxBezPoprawy);
	}
	
	
	/**
	 * Zwraca osobnika posiadaj¹cego najlepsz¹ wartoœc funkcji przystosowania z populacji
	 * @return
	 */
	Osobnik wybierzNajlepszego() 
	{
		return populacja.get(0);
	}
	
	int getEtapAlgorytmu()
	{
		return this.etapAlgorytmu;
	}
	
	public float getCelOptymalizacji() {
		return celOptymalizacji;
	}

}

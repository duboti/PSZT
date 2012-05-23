package ewolucyjne;

import java.util.*;

public class Algorytm 
{
	private int mi;
	private int lambda;
	/**
	 * 0-mi+lambda, 
	 * 1-mi,lambda
	 */
	private int rodzajAlgorytmu;
	/**
	 * 0-mi najlepszych 
	 * 1-mi losowych
	 */
	private int rodzajWyboru;
	/**
	 * 0-minimalizacja, 
	 * 1-maxymalizacja, 
	 * 2-konkretna warto��
	 */
	private int rodzajOptymalizacji;	
	private double celOptymalizacji;
	private double wspolczynnikInterpolacji;
	private int nrIteracji;
	private double najlepszyWynik;
	/**
	 *  etapy algorytmu 1 - czeka na stworzenie nowego pokolenia
	 *  etapy algorytmu 2 - czeka na mutacje populacji
	 *  etapy algorytmu 3 - czeka na selekcje nast�pnej populacji
	 */
	private int etapAlgorytmu;
	private int etapBezPoprawy;
	private int maxIteracji;
	private double dokladnosc;
	String typOsobnika;
	private int maxBezPoprawy;
	FunkcjaPrzystosowania funkcja;
	private boolean stop;
	double procentMutacji;
	
	private ArrayList<Osobnik> populacja;
	
	
	
	Algorytm (String typOsobnika, int mi, int lambda, int rodzajAlgorytmu, int rodzajWyboru, int rodzajOptymalizacji, 
			double celOptymalizacji, int maxIteracji, int maxBezPoprawy, double dokladnosc, double procentMutacji,
			double wspolczynnikInterpolacji, ArrayList<Zakres> zakres, ArrayList<Double> sigmy, FunkcjaPrzystosowania funkcja)
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
			ArrayList<Double> parametry = new ArrayList<Double>();
			for(int j = 0 ; j < zakres.size(); j++ )
				parametry.add( ( (zakres.get(j)).koniec() - (zakres.get(j)).poczatek() )*
											generator.nextDouble() + (zakres.get(j)).poczatek());
			populacja.add(this.stworzOsobnika(parametry, sigmy));	
		}
		Collections.sort(populacja, new OsobnikMinComparator());
		
		najlepszyWynik = populacja.get(0).pobierzWartosc();
	}
	
	/**
	 * Tworzy potomka na podstawie dw�ch osobnik�w
	 * TODO: sprawdzi� czy uzywa odpowiedniego comparatora w zalezno�ci od rodzajuOptymalizacji
	 * @param mama
	 * @param tata
	 * @return Osobnik
	 */
	Osobnik stworzPotomka(Osobnik mama, Osobnik tata) 
	{
		Osobnik potomek = null;
		ArrayList<Double> parametry = new ArrayList<Double>();
		ArrayList<Double> sigmy = new ArrayList<Double>();
		Comparator<Osobnik> komparator=null;
		if (rodzajOptymalizacji==0)
		{
			komparator = new OsobnikMinComparator();
		}
		else if (rodzajOptymalizacji==1)
		{
			komparator = new OsobnikMaxComparator();
		} 
		else if (rodzajOptymalizacji==2)
		{
			komparator = new OsobnikOdCeluComparator(this.celOptymalizacji);
		}
		
		if (komparator.compare(mama,tata)>1)
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
	
	/**
	 * Tworzy osobnika kt�rego argumenty b�dza w pzredzia�ach parametr�w oraz b�dzie posiada� dane sigmy
	 * @param parametry
	 * @param sigmy
	 * @return Osobnik
	 */
	Osobnik stworzOsobnika(ArrayList<Double> parametry, ArrayList<Double> sigmy) 
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
			System.out.println("Nie mo�na stworzy� klasy o nazwie "+typOsobnika);
			e.printStackTrace();
			return null;
		} 
		catch (IllegalAccessException e) 
		{
			System.out.println("Brak dost�pu do klasy o nazwie "+typOsobnika);
			e.printStackTrace();
			return null;
		}
		nowyOsobnik.inicjowanie(parametry, sigmy, funkcja);
		return nowyOsobnik;
	}
	
	/**
	 * metoda zwraca nowo stworzone pokolenie gdy algorytm by� w etapie 1 i pzrechodzi w etap 2
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
	 * Wybiera spo�r�d starej populacji i nowego pokolenia now� populacje 
	 * w zalezno�ci od rodzaju algorytmu
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
				else if (rodzajOptymalizacji==2)
				{
					Collections.sort(populacja, new OsobnikOdCeluComparator(this.celOptymalizacji));
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
				else if (rodzajOptymalizacji==2)
				{
					Collections.sort(populacja, new OsobnikOdCeluComparator(this.celOptymalizacji));
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
	 * Wybiera now� populacje posiadaj�c� mi osobnik�w
	 * gdy rodzajWyboru == 0 jest to mi najlepszych
	 * gdy rodzaj wyboru == 1 jest to mi losowych
	 * w pozosta�ych przypadkach populacja jest po prostu ucinana do mi osobnik�w
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
	 * W tym miejscu jest warunek kt�ry okresla minimalizacje, maksymalizacje lub d��enie do wybranej warto�ci
	 * W przypadku braku poprawy zwi�kszana jest znienna etapBezPoprawy
	 * W pzreciwnym przypadku ta zmienna jest zerowana
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
					if (generator.nextDouble() < procentMutacji)
						populacja.get(populacja.indexOf(os)).mutuj();
				}
			} else if(rodzajAlgorytmu==1)
			{
				for (Osobnik os: potomkowie)
				{
					if (generator.nextDouble() < procentMutacji)
						potomkowie.get(potomkowie.indexOf(os)).mutuj();
				}
			}
			etapAlgorytmu = 3;
		}
	}
	
	/**
	 * Zwraca prawd� gdy zosta� spe�niony i algorytm powinien byc zatrzymany
	 * w przeciwnym przypadku fa�sz
	 * @return boolean
	 */
	boolean warunekStopu() 
	{
		return this.stop;
	}
	
	/**
	 * Sprawdza czy zosta� spelniony warunek stopu
	 * oraz ustawia warto�c zmiennej stop
	 */
	void czyStop() 
	{
		stop = (nrIteracji>=maxIteracji || etapBezPoprawy>=maxBezPoprawy);
	}
	
	
	/**
	 * Zwraca osobnika posiadaj�cego najlepsz� warto�c funkcji przystosowania z populacji
	 * @return Osobnik
	 */
	Osobnik wybierzNajlepszego() 
	{
		return populacja.get(0);
	}
	
	int getEtapAlgorytmu()
	{
		return this.etapAlgorytmu;
	}
	
	public double getCelOptymalizacji() {
		return celOptymalizacji;
	}

	ArrayList<Osobnik> pobierzPopulacje()
	{
		return this.populacja;
	}
	
	int pobierzNumerIteracji()
	{
		return this.nrIteracji;
	}
}

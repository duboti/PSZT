package ewolucyjne;

import java.util.ArrayList;

public class Kontroler extends Thread {

	private Algorytm ewolucyjny;
	private boolean pracuj;
	private boolean zakonczony;
	private Widok widok;
	
	
	Kontroler ( Widok widok )
	{
		this.widok = widok;
	}	
	/**
	 * Metoda tworzy obiekt klasy Algorytm
	 * @param 
	 * @return Potwierdzenie utworzenia obiektu. Metoda zwraca false, kiedy któryś z parametrów jest niepoprawny.
	 */

	public boolean inicjowanie( ArrayList < String > in )
	{
		Integer mi, lambda, maxIteracji;
		Float minX, minY, maxX, maxY, sigmaX, sigmaY, epsilon, wspInterpolacji;
		try
		{
			mi = Integer.parseInt(in.get(0));
			lambda = Integer.parseInt(in.get(1));
			minX = Float.parseFloat(in.get(2));
			minY = Float.parseFloat(in.get(3));
			maxX = Float.parseFloat(in.get(4));
			maxY = Float.parseFloat(in.get(5));
			sigmaX = Float.parseFloat(in.get(6));
			sigmaY = Float.parseFloat(in.get(7));
			epsilon = Float.parseFloat(in.get(8));
			maxIteracji = Integer.parseInt(in.get(9));
			wspInterpolacji = Float.parseFloat(in.get(10));
		}
		catch (NumberFormatException e)
		{
			//e.printStackTrace();
			widok.dodajNapis("Błąd: Co najmniej 1 z pól nie zawiera odpowiedniej liczby. Kod błędu:");
			widok.dodajNapis(e.getMessage());
			return false;
		}
		if(mi == null || mi <= 0)
			return false;
	
		if(lambda == null || lambda<=mi)
			return false;
		
		if(minX == null)
			return false;
		
		if(minY == null)
			return false;
		
		if(maxX == null || maxX < minX)
			return false;
		
		if(maxY == null || maxY < minY)
			return false;

		ArrayList<Zakres> zakres = new ArrayList<Zakres>();
		zakres.add(new Zakres (minX,maxX));
		zakres.add(new Zakres (minY,maxY));		
		
		if(sigmaX == null || sigmaX > 1 || sigmaX < 0 )
			return false;
		
		if(sigmaY == null || sigmaY > 1 || sigmaY < 0)
			return false;
		
		ArrayList<Float> sigmy = new ArrayList<Float>();
		sigmy.add(sigmaX);
		sigmy.add(sigmaY);

		if(epsilon == null || epsilon < 0)
			return false;

		if(maxIteracji == null || maxIteracji <=0 )
			return false;

		if(wspInterpolacji == null || wspInterpolacji >= 1 || wspInterpolacji <= 0)
			return false;
		
		int algorytm;
		if(in.get(11).compareTo("μ + λ")==0)
			algorytm = 0;
		else
			algorytm = 1;
		int rodzajWyboru=0;
		int rodzajOptymalizacji=2;
		float celOptymalizacji=0;
		int maxBezPoprawy=10;
		float procentMutacji=0.05f;
		this.ewolucyjny = new Algorytm("ewolucyjne.Punkt",mi,lambda,algorytm,rodzajWyboru,rodzajOptymalizacji,celOptymalizacji,maxIteracji,maxBezPoprawy,epsilon,procentMutacji, wspInterpolacji, zakres,sigmy,new FunkcjaRosenbrocka());
		this.pracuj = true;
		this.zakonczony = false;
		this.widok.dodajNapis(this.statystykiAlgorytmu());
		return true;
	}
	/**
	 * Metoda zatrzymuje działanie algorytmu kiedy pracuje w trybie automatycznym.
	 */
	public void przerwij( )
	{
		this.pracuj = false;
	}
	/*
	public boolean czyPracuje()
	{
		return this.pracuj;
	}
	
	public boolean czyZakonczyl()
	{
		return this.zakonczony;
	}*/
	
	public void uruchom( )
	{
		if(this.zakonczony)
			return;
		if(this.pracuj)
			this.start();
		else
			this.pracuj = true;
	}
	
	public String statystykiAlgorytmu()
	{
		String napis;
		ArrayList<Osobnik> tmp = this.ewolucyjny.pobierzPopulacje();
		napis = "Numer iteracji: " + this.ewolucyjny.pobierzNumerIteracji() + "\n";
		int iloscWyswietlnych;
		if (tmp.size() > 14)
			iloscWyswietlnych = 14;
		else
			iloscWyswietlnych = tmp.size();
		for(int i = 0; i < iloscWyswietlnych ; i++)
			napis+= tmp.get(i).toString();
		return napis;
	}
	
	/**
	 * Metoda wykonuje jedną iteracje algorytmu.
	 * @return Obiekt po przejsciu iteracji.
	 */
	public void krok( )
	{
		if(this.zakonczony)
		{
			widok.koniecAlgorytmu();
			widok.dodajNapis("Algorytm zakończył się poprawnie.\nZnalezione minimum to:\n"
					+ewolucyjny.pobierzPopulacje().get(0).toString());
		}
		else
		{
			ArrayList<Osobnik> pokolenie = this.ewolucyjny.stworzNastepnePokolenie();
			this.ewolucyjny.mutujPopulacje(pokolenie);
			this.ewolucyjny.selekcja(pokolenie);
			zakonczony = this.ewolucyjny.warunekStopu();
			this.widok.dodajNapis(this.statystykiAlgorytmu());
		}
	}
	
	/**
	 * Metoda uruchamia algorytm w trybie automatycznym.
	 * @return Obiekt po przerwaniu lub zakończeniu działania.
	 */
	public void run()
	{
		if(this.zakonczony)
			return;
		do{
			
			ArrayList<Osobnik> pokolenie = this.ewolucyjny.stworzNastepnePokolenie();
			this.ewolucyjny.mutujPopulacje(pokolenie);
			this.ewolucyjny.selekcja(pokolenie);
			this.zakonczony=this.ewolucyjny.warunekStopu();
			this.widok.dodajNapis(statystykiAlgorytmu());
			
			while(!this.pracuj && !this.zakonczony)
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
		}while(!this.zakonczony);
		widok.koniecAlgorytmu();
		widok.dodajNapis("Algorytm zakończył się poprawnie.\nZnalezione minimum to:\n"
				+ewolucyjny.pobierzPopulacje().get(0).toString());
		return;
	}
}

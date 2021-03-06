package ewolucyjne;

import java.util.ArrayList;

public class Kontroler extends Thread {

	private Algorytm ewolucyjny;
	private boolean pracuj;
	private boolean zakonczony;
	private Widok widok;
	private int iloscWyswietlnych;
	
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
		Integer mi, lambda, maxIteracji, rodzajWyboru, rodzajOptymalizacji, maxBezPoprawy;
		Double minX, minY, maxX, maxY, sigmaX, sigmaY, epsilon, wspInterpolacji, celOptymalizacji, procentMutacji;
		try
		{
			mi = Integer.parseInt(in.get(0));
			lambda = Integer.parseInt(in.get(1));
			minX = Double.parseDouble(in.get(2));
			minY = Double.parseDouble(in.get(3));
			maxX = Double.parseDouble(in.get(4));
			maxY = Double.parseDouble(in.get(5));
			sigmaX = Double.parseDouble(in.get(6));
			sigmaY = Double.parseDouble(in.get(7));
			epsilon = Double.parseDouble(in.get(8));
			maxIteracji = Integer.parseInt(in.get(9));
			wspInterpolacji = Double.parseDouble(in.get(10));	
			celOptymalizacji = Double.parseDouble(in.get(14));
			procentMutacji = Double.parseDouble(in.get(15));
			maxBezPoprawy = Integer.parseInt(in.get(16));
			iloscWyswietlnych = Integer.parseInt(in.get(17));	
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
		
		ArrayList<Double> sigmy = new ArrayList<Double>();
		sigmy.add(sigmaX);
		sigmy.add(sigmaY);

		if(epsilon == null || epsilon < 0)
			return false;

		if(maxIteracji == null || maxIteracji <=0 )
			return false;

		if(wspInterpolacji == null || wspInterpolacji >= 1 || wspInterpolacji <= 0)
			return false;
		
		int algorytm;
		if(in.get(11).equals("μ + λ"))
			algorytm = 0;
		else if(in.get(11).equals("μ, λ"))
			algorytm = 1;
		else
			return false;
		
		
		if(in.get(12).equals("μ najlepszych"))
			rodzajWyboru = 0;
		else if(in.get(12).equals("μ losowych"))
			rodzajWyboru = 1;
		else
			return false;
		
		if(in.get(13).equals("minimalizacja"))
			rodzajOptymalizacji = 0;
		else if(in.get(13).equals("maksymalizacja"))
			rodzajOptymalizacji= 1;
		else if(in.get(13).equals("do wartości"))
			rodzajOptymalizacji = 2;
		else
			return false;
		
		this.ewolucyjny = new Algorytm("ewolucyjne.Punkt",mi,lambda,algorytm,rodzajWyboru,rodzajOptymalizacji,
				celOptymalizacji,maxIteracji,maxBezPoprawy,epsilon,procentMutacji, wspInterpolacji,
				zakres,sigmy,new FunkcjaRosenbrocka());
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
		String napis = "";
		if (iloscWyswietlnych==0)
		{
			return napis;
		} 
		else
		{
			ArrayList<Osobnik> tmp = this.ewolucyjny.pobierzPopulacje();
			napis = "Numer iteracji: " + this.ewolucyjny.pobierzNumerIteracji() + "\n";
			
			if (tmp.size() < iloscWyswietlnych)
				iloscWyswietlnych = tmp.size();
			for(int i = 0; i < iloscWyswietlnych ; i++)
				napis+= tmp.get(i).toString();
			return napis;
		}
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
					+ewolucyjny.pobierzPopulacje().get(0).toString()+"\n");
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
				} catch (InterruptedException e) 
				{
					e.printStackTrace();
				}	
		}while(!this.zakonczony);
		widok.koniecAlgorytmu();
		widok.dodajNapis("Algorytm zakończył się poprawnie. Po "+ewolucyjny.pobierzNumerIteracji()+" iteracjach.\nZnalezione minimum to:\n"
				+ewolucyjny.pobierzPopulacje().get(0).toString()+"\n");
		return;
	}
}

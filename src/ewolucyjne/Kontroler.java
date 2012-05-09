package ewolucyjne;

import java.util.ArrayList;


public class Kontroler extends Thread {

	private Algorytm ewolucyjny;
	private boolean pracuj;
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
		
		Integer mi = Integer.getInteger(in.get(0));
		if(mi == null || mi <= 0)
			return false;
		
		Integer lambda = Integer.getInteger(in.get(1));
		if(lambda == null || lambda<=mi)
			return false;
		
		Float minX = Float.parseFloat(in.get(2));
		if(minX == null)
			return false;
		
		Float minY = Float.parseFloat(in.get(3));
		if(minY == null)
			return false;
		
		Float maxX = Float.parseFloat(in.get(4));
		if(maxX == null || maxX < minX)
			return false;
		
		Float maxY = Float.parseFloat(in.get(5));
		if(maxY == null || maxY < minY)
			return false;
		
		ArrayList<Zakres> zakres = new ArrayList<Zakres>();
		zakres.add(new Zakres (minX,maxX));
		zakres.add(new Zakres (minY,maxY));		
		
		Float sigmaX = Float.parseFloat(in.get(6));
		if(sigmaX == null || sigmaX > 1 || sigmaX < 0 )
			return false;
		
		Float sigmaY = Float.parseFloat(in.get(7));
		if(sigmaY == null || sigmaY > 1 || sigmaY < 0)
			return false;
		
		ArrayList<Float> sigmy = new ArrayList<Float>();
		sigmy.add(sigmaX);
		sigmy.add(sigmaY);
		
		Float epsilon = Float.parseFloat(in.get(8));
		if(epsilon == null || epsilon < 0)
			return false;
				
		Integer maxIteracji = Integer.getInteger(in.get(9));
		if(maxIteracji == null || maxIteracji <=0 )
			return false;
		
		Float wspInterpolacji = Float.parseFloat(in.get(10));
		if(wspInterpolacji == null || wspInterpolacji >= 1 || wspInterpolacji <= 0)
			return false;
		int algorytm;
		if(in.get(11).compareTo("μ + λ")==0)
			algorytm = 0;
		else
			algorytm = 1;
		
		this.ewolucyjny = new Algorytm("Punkt",mi,lambda,algorytm,maxIteracji,epsilon,wspInterpolacji,zakres,sigmy,new FunkcjaRosenbrocka());
		this.pracuj = true;
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
	
	public String statystykiAlgorytmu()
	{
		String napis;
		ArrayList<Osobnik> tmp = this.ewolucyjny.pobierzPopulacje();
		napis = "Numer iteracji: " + this.ewolucyjny.pobierzNumerIteracji() + "\n";
		for(int i = 0; i < 10 ; i++)
			napis+= tmp.get(i).toString();
		return napis;
		//return this.ewolucyjny.toString();
	}
	/**
	 * Metoda wykonuje jedną iteracje algorytmu.
	 * @return Obiekt po przejsciu iteracji.
	 */
	public void krok( )
	{
		if(!this.pracuj)
			return;
		ArrayList<Osobnik> pokolenie = this.ewolucyjny.stworzNastepnePokolenie();
		this.ewolucyjny.mutujPopulacje(pokolenie);
		this.ewolucyjny.Selekcja(pokolenie);
		pracuj = !this.ewolucyjny.warunekStopu();
		this.widok.dodajNapis(this.statystykiAlgorytmu());
		return;
	}
	
	/**
	 * Metoda uruchamia algorytm w trybie automatycznym.
	 * @return Obiekt po przerwaniu lub zakończeniu działania.
	 */
	
	public void run()
	{
		if(!this.pracuj)
			return;
		do{
			
			ArrayList<Osobnik> pokolenie = this.ewolucyjny.stworzNastepnePokolenie();
			this.ewolucyjny.mutujPopulacje(pokolenie);
			this.ewolucyjny.Selekcja(pokolenie);
			this.widok.dodajNapis(statystykiAlgorytmu());
			
		}while(!this.ewolucyjny.warunekStopu() && this.pracuj);
		
		return;
	}
}

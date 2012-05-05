package ewolucyjne;

public class Kontroler {

	private Algorytm ewolucyjny;
	private boolean pracuj;
	
	/**
	 * Metoda tworzy obiekt klasy Algorytm
	 * @param 
	 * @return Potwierdzenie utworzenia obiektu. Metoda zwraca false, kiedy kt�ry� z parametr�w jest niepoprawny.
	 */
	public boolean inicjalizuj(  )
	{
		this.pracuj = true;
		return false;
	}
	
	/**
	 * Metoda zatrzymuje dzia�anie algorytmu kiedy pracuje w trybie automatycznym.
	 */
	public void stop( )
	{
		pracuj = false;
	}
	
	/**
	 * Metoda wykonuje jedn� iteracje algorytmu.
	 * @return Obiekt po przejsciu iteracji.
	 */
	public Algorytm krok( )
	{
		//TODO
		return ewolucyjny;
	}
	
	/**
	 * Metoda uruchamia algorytm w trybie automatycznym.
	 * @return Obiekt po przerwaniu lub zako�czeniu dzia�ania.
	 */
	
	public Algorytm automatycznie()
	{
		while(pracuj)
		{
			//TODO
		}
		return ewolucyjny;
	}
}

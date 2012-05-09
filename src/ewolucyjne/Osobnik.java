package ewolucyjne;

import java.util.ArrayList;

//TODO: sprawdzenie czy mo�na zwraca� co� takiego jak NAN
public interface Osobnik 
{
	void inicjowanie( ArrayList<Float> parametry, ArrayList<Float> sigmy , FunkcjaPrzystosowania fun);
	/**
	 * pobierz warto�� funkcji przystosowania
	 * @return
	 */
	float pobierzWartosc();
	
	/**
	 * pobierz parametr x_n
	 * @param n nr zmiennej
	 * @return 
	 */
	float pobierzParametr(int n);
	
	/**
	 * pobierz parametr sigma_n
	 * @param n nr zmiennej sigma
	 * @return 
	 */
	float pobierzSigme(int n);
	
	/**
	 * Metoda mutuje danego osobnika
	 * 
	 */
	void mutuj();

	/**
	 *  
	 * @return ile osobnik posiada parametr�w
	 */
	int pobierzIloscParametrow();
	
	
}

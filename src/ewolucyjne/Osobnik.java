package ewolucyjne;

import java.util.ArrayList;

//TODO: sprawdzenie czy mo¿na zwracaæ coœ takiego jak NAN
public interface Osobnik 
{
	void inicjowanie( ArrayList<Double> parametry, ArrayList<Double> sigmy , FunkcjaPrzystosowania fun);
	/**
	 * pobierz wartoœæ funkcji przystosowania
	 * @return
	 */
	double pobierzWartosc();
	
	/**
	 * pobierz parametr x_n
	 * @param n nr zmiennej
	 * @return 
	 */
	double pobierzParametr(int n);
	
	/**
	 * pobierz parametr sigma_n
	 * @param n nr zmiennej sigma
	 * @return 
	 */
	double pobierzSigme(int n);
	
	/**
	 * Metoda mutuje danego osobnika
	 * 
	 */
	void mutuj(ArrayList<Zakres> zakres);

	/**
	 *  
	 * @return ile osobnik posiada parametrów
	 */
	int pobierzIloscParametrow();
	
	
}

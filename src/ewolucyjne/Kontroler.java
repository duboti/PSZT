package ewolucyjne;

import java.util.ArrayList;

import javax.swing.*;

public class Kontroler extends Thread {

	private Algorytm ewolucyjny;
	private JEditorPane konsola;
	private boolean pracuj;
	
	Kontroler (JEditorPane kon)
	{
		konsola = kon;
	}
	
	/**
	 * Metoda tworzy obiekt klasy Algorytm
	 * @param 
	 * @return Potwierdzenie utworzenia obiektu. Metoda zwraca false, kiedy któryś z parametrów jest niepoprawny.
	 */
	public boolean inicjowanie( ArrayList < String > in )
	{
		Integer mi = Integer.parseInt(in.get(0));
		if(mi == null)
			return false;

		Integer lambda = Integer.parseInt(in.get(1));
		if(lambda == null)
			return false;
		
		Float minX = Float.parseFloat(in.get(2));
		if(minX == null)
			return false;
		
		Float minY = Float.parseFloat(in.get(3));
		if(minY == null)
			return false;
		
		Float maxX = Float.parseFloat(in.get(4));
		if(maxX == null)
			return false;
		
		Float maxY = Float.parseFloat(in.get(5));
		if(maxY == null)
			return false;

		ArrayList<Zakres> zakres = new ArrayList<Zakres>();
		zakres.add(new Zakres (minX,maxX));
		zakres.add(new Zakres (minY,maxY));		
		
		Float sigmaX = Float.parseFloat(in.get(6));
		if(sigmaX == null)
			return false;
		
		Float sigmaY = Float.parseFloat(in.get(7));
		if(sigmaY == null)
			return false;
		
		ArrayList<Float> sigmy = new ArrayList<Float>();
		sigmy.add(sigmaX);
		sigmy.add(sigmaY);
		
		Float epsilon = Float.parseFloat(in.get(8));
		if(epsilon == null)
			return false;
				
		Integer maxIteracji = Integer.parseInt(in.get(9));
		if(maxIteracji == null)
			return false;
		
		Float wspInterpolacji = Float.parseFloat(in.get(10));
		if(wspInterpolacji == null)
			return false;
		int algorytm;
		if(in.get(11).compareTo("μ + λ")==0)
			algorytm = 0;
		else
			algorytm = 1;
		
		//ewolucyjny = new Algorytm("Punkt",mi,lambda,algorytm,maxIteracji,epsilon,wspInterpolacji,zakres,sigmy,new FunkcjaRosenbrocka());
		this.pracuj = true;
		konsola.setText(konsola.getText()+"Pomyślnie zainicjowano.\n");
		return true;
	}
	
	/**
	 * Metoda zatrzymuje działanie algorytmu kiedy pracuje w trybie automatycznym.
	 */
	public void przerwij( )
	{
		pracuj = false;
	}
	
	/**
	 * Metoda wykonuje jedną iteracje algorytmu.
	 * @return Obiekt po przejsciu iteracji.
	 */
	public Algorytm krok( )
	{
		//TODO
		return ewolucyjny;
	}
	
	/**
	 * Metoda uruchamia algorytm w trybie automatycznym.
	 * @return Obiekt po przerwaniu lub zakończeniu działania.
	 */
	
	public void run()
	{
		while(pracuj)
		{
			//TODO
		}
		return;
	}
}

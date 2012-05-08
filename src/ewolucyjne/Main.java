package ewolucyjne;

import javax.swing.*;

public class Main 
{
	public static void main(String[] args)
	{
		//Kontroler kontroler = new Kontroler();
		//Widok widok = new Widok (kontroler);
		SwingUtilities.invokeLater(new Runnable() {
		      public void run() 
		      {
		    	  new Widok ();
		      }
		});
	}
}

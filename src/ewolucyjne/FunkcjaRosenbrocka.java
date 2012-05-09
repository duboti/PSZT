package ewolucyjne;

import java.util.ArrayList;

public class FunkcjaRosenbrocka implements FunkcjaPrzystosowania 
{

	@Override
	public float Oblicz(ArrayList<Float> lista) 
	{
		if( lista.size()!= 2)
			return 0;		
		return (1-lista.get(0))*(1-lista.get(0)) + 100* (lista.get(1)-lista.get(0)*lista.get(0))*(lista.get(1)-lista.get(0)*lista.get(0));
	}

}

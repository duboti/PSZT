package ewolucyjne;

import java.util.*;

public class Algorytm 
{
	private int mi;
	private int lambda;
	private float maxX;//array
	private float minX;//array
	private float maxY;//array
	private float minY;//array
	private int rodzajAlgorytmu;
	public ArrayList<Osobnik> populacja;
	private float wspolczynnikInterpolacji;
	private float sigmaX;//array
	private float sigmaY;//array
	//private ArrayList<Osobnik> potomkowie;
	
	Algorytm (int mi, int lambda, int rodzajAlgorytmu, float wspolczynnikInterpolacji, 
			ArrayLista<Zakres> zakres, ArrayLista<Float> Sigmy)
	{
		this.mi = mi;
	}
	
	Osobnik stworzPotomka(Osobnik mama, Osobnik tata) 
	{
		return Osobnik;
	}
	
	ArrayList<Osobnik> stworzPokolenie(); 
	
	ArrayList<Osobnik> Selekcja(ArrayList<Osobnik> potomkowie);
	
	boolean warunekStopu();
	
	Osobnik wybierzNajlepszego();
	
	
}

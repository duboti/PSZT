package ewolucyjne;

import java.util.*;

import javax.swing.*;

import layout.*;

/**
 * Klasa odpowiedzialna za stworzenie panelu z modyfikowalnymi parametrami.
 * @author Daniel Pogrebniak
 */
class ParametryPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JTextField mi2;
	private JTextField lambda2;
	private JTextField minX2;
	private JTextField minY2;
	private JTextField maxX2;
	private JTextField maxY2;
	private JTextField sigmaX2;
	private JTextField sigmaY2;
	private JTextField epsilon2;
	private JTextField maxIteracji2;
	private JTextField wspolczynnikInterpolacji2;
	private JComboBox rodzajAlgorytmu2;
	private JComboBox rodzajWyboru2;
	private JComboBox rodzajOptymalizacji2;
	private JTextField celOptymalizacji2;
	private JTextField procentMutacji2;
	private JTextField maxBezpoprawy2;
	private JTextField ileWyswietlanych2;
	
	public ParametryPanel() 
	{
		double size[][] =
	        {{0.20, 0.10, 0.20, 0.10, 0.20, 0.20},
	         {30, 30, 30, 30, 30, 30}};
		TableLayout lay = new TableLayout(size);
	    setLayout(lay);
	    
	    JLabel mi1 = new JLabel("μ: ");
		JLabel lambda1 = new JLabel("λ: ");
		JLabel minX1 = new JLabel("min x: ");
		JLabel minY1 = new JLabel("min y: ");
		JLabel maxX1 = new JLabel("max x: ");
		JLabel maxY1 = new JLabel("max y: ");
		JLabel sigmaX1 = new JLabel("σ x: ");
		JLabel sigmaY1 = new JLabel("σ y: ");
		JLabel epsilon1 = new JLabel("ε: ");
		JLabel maxIteracji1 = new JLabel("max iteracji: ");
		JLabel wspolczynnikInterpolacji1 = new JLabel("wsp.interpolacji: ");
		JLabel rodzajAlgorytmu1 = new JLabel("rodzaj algorytmu: ");
		JLabel rodzajWyboru1 = new JLabel("rodzaj wyboru: ");
		JLabel rodzajOptymalizacji1 = new JLabel("rodzaj optymalizacji: ");
		JLabel celOptymalizacji1 = new JLabel("cel optymalizacji: ");
		JLabel procentMutacji1 = new JLabel("procent mutacji: ");
		JLabel maxBezpoprawy1 = new JLabel("max bez poprawy: ");
		JLabel ileWyswietlanych1 = new JLabel("ile wyswietlanych: ");
		
		mi2 = new JTextField("100", 5);
		lambda2 = new JTextField("200", 5);
		minX2 = new JTextField("-2.0", 5);
		minY2 = new JTextField("-2.0", 5);
		maxX2 = new JTextField("2.0", 5);
		maxY2 = new JTextField("2.0", 5);
		sigmaX2 = new JTextField("0.5", 5);
		sigmaY2 = new JTextField("0.5", 5);
		epsilon2 = new JTextField("0.01", 5);
		maxIteracji2 = new JTextField("10000", 5);
		wspolczynnikInterpolacji2 = new JTextField("0.5", 5);
		rodzajAlgorytmu2 = new JComboBox(new String[] {"μ + λ","μ, λ"});
		rodzajWyboru2 = new JComboBox(new String[] {"μ najlepszych","μ losowych"});
		rodzajOptymalizacji2 = new JComboBox(new String[] {"minimalizacja","maksymalizacja","do wartości"});
		celOptymalizacji2 = new JTextField("0", 5);
		procentMutacji2 = new JTextField("0.05", 5);
		maxBezpoprawy2 = new JTextField("10", 5);
		ileWyswietlanych2 = new JTextField("3", 5);
		
		add(mi1, "0 0 r c");
		add(mi2, "1 0 c c");
		add(lambda1, "2 0 r c");
		add(lambda2, "3 0 c c");
		add(minX1, "0 1 r c");
		add(minX2, "1 1 c c");
		add(minY1, "2 1 r c");
		add(minY2, "3 1 c c");
		add(maxX1, "0 2 r c");
		add(maxX2, "1 2 c c");
		add(maxY1, "2 2 r c");
		add(maxY2, "3 2 c c");
		add(sigmaX1, "0 3 r c");
		add(sigmaX2, "1 3 c c");
		add(sigmaY1, "2 3 r c");
		add(sigmaY2, "3 3 c c");
		add(epsilon1, "0 4 r c");
		add(epsilon2, "1 4 l c");
		add(maxIteracji1, "2 4 r c");
		add(maxIteracji2, "3 4 l c");
		add(wspolczynnikInterpolacji1, "0 5 r c");
		add(wspolczynnikInterpolacji2, "1 5 l c");
		add(rodzajAlgorytmu1, "2 5 r c");
		add(rodzajAlgorytmu2, "3 5 l c");
		add(rodzajWyboru1, "4 0 r c");
		add(rodzajWyboru2, "5 0 l c");
		add(rodzajOptymalizacji1, "4 1 r c");
		add(rodzajOptymalizacji2, "5 1 l c");
		add(celOptymalizacji1, "4 2 r c");
		add(celOptymalizacji2, "5 2 l c");
		add(procentMutacji1, "4 3 r c");
		add(procentMutacji2, "5 3 l c");
		add(maxBezpoprawy1, "4 4 r c");
		add(maxBezpoprawy2, "5 4 l c");
		add(ileWyswietlanych1, "4 5 r c");
		add(ileWyswietlanych2, "5 5 l c");
	}
	
	/**
	 * Metoda tworzy ArrayListe wszystkich paramertów.
	 * @return ArrayLista wszystkich paramertów zapisanych jako Stringi 
	 */
	ArrayList<String> Inicjuj ()
	{
		ArrayList<String> parametry = new ArrayList<String>();
		parametry.add(mi2.getText());
		parametry.add(lambda2.getText());
		parametry.add(minX2.getText());
		parametry.add(minY2.getText());
		parametry.add(maxX2.getText());
		parametry.add(maxY2.getText());
		parametry.add(sigmaX2.getText());
		parametry.add(sigmaY2.getText());
		parametry.add(epsilon2.getText());
		parametry.add(maxIteracji2.getText());
		parametry.add(wspolczynnikInterpolacji2.getText());
		parametry.add((String)rodzajAlgorytmu2.getSelectedItem());
		parametry.add((String)rodzajWyboru2.getSelectedItem());
		parametry.add((String)rodzajOptymalizacji2.getSelectedItem());
		parametry.add(celOptymalizacji2.getText());
		parametry.add(procentMutacji2.getText());
		parametry.add(maxBezpoprawy2.getText());
		parametry.add(ileWyswietlanych2.getText());
		return parametry;
	}
	
	
	/**
	 * Zablokowanie możliwości edycji parametrów.
	 */
	void zablokujParametry ()
	{
		mi2.setEnabled(false);
		lambda2.setEnabled(false);
		minX2.setEnabled(false);
		minY2.setEnabled(false);
		maxX2.setEnabled(false);
		maxY2.setEnabled(false);
		sigmaX2.setEnabled(false);
		sigmaY2.setEnabled(false);
		epsilon2.setEnabled(false);
		maxIteracji2.setEnabled(false);
		wspolczynnikInterpolacji2.setEnabled(false);
		rodzajAlgorytmu2.setEnabled(false);
		rodzajWyboru2.setEnabled(false);
		rodzajOptymalizacji2.setEnabled(false);
		celOptymalizacji2.setEnabled(false);
		procentMutacji2.setEnabled(false);
		maxBezpoprawy2.setEnabled(false);
		ileWyswietlanych2.setEnabled(false);
	}
	
	/**
	 * Odblokowanie możliwości edycji parametrów.
	 */	
	void odblokujParametry ()
	{
		mi2.setEnabled(true);
		lambda2.setEnabled(true);
		minX2.setEnabled(true);
		minY2.setEnabled(true);
		maxX2.setEnabled(true);
		maxY2.setEnabled(true);
		sigmaX2.setEnabled(true);
		sigmaY2.setEnabled(true);
		epsilon2.setEnabled(true);
		maxIteracji2.setEnabled(true);
		wspolczynnikInterpolacji2.setEnabled(true);
		rodzajAlgorytmu2.setEnabled(true);
		rodzajWyboru2.setEnabled(true);
		rodzajOptymalizacji2.setEnabled(true);
		celOptymalizacji2.setEnabled(true);
		procentMutacji2.setEnabled(true);
		maxBezpoprawy2.setEnabled(true);
		ileWyswietlanych2.setEnabled(true);
	}
}


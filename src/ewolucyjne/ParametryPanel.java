package ewolucyjne;

import java.util.*;

import javax.swing.*;

import layout.*;

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
	
	public ParametryPanel() 
	{
		double size[][] =
	        {{0.25, 0.25, 0.25, 0.25},
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
		JLabel wspolczynnikInterpolacji1 = new JLabel("współczynnik interpolacji: ");
		JLabel rodzajAlgorytmu1 = new JLabel("rodzaj algorytmu: ");
		
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
		add(epsilon2, "1 4 c c");
		add(maxIteracji1, "2 4 r c");
		add(maxIteracji2, "3 4 c c");
		add(wspolczynnikInterpolacji1, "0 5 r c");
		add(wspolczynnikInterpolacji2, "1 5 c c");
		add(rodzajAlgorytmu1, "2 5 r c");
		add(rodzajAlgorytmu2, "3 5 c c");
	}
	
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
		return parametry;
	}
	
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
	}
	
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
	}
}


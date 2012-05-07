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
	private JTextField wspolczynnikInterpolacji2;
	private JComboBox rodzajAlgorytmu2;
	
	public ParametryPanel() 
	{
		double size[][] =
	        {{0.25, 0.25, 0.25, 0.25},
	         {30, 30, 30, 30}};
		TableLayout lay = new TableLayout(size);
	    setLayout(lay);
	    
	    JLabel mi1 = new JLabel("μ: ");
		JLabel lambda1 = new JLabel("λ: ");
		JLabel minX1 = new JLabel("min x: ");
		JLabel minY1 = new JLabel("min y: ");
		JLabel maxX1 = new JLabel("max x: ");
		JLabel maxY1 = new JLabel("max y: ");
		JLabel wspolczynnikInterpolacji1 = new JLabel("współczynnik interpolacji: ");
		JLabel rodzajAlgorytmu1 = new JLabel("rodzaj algorytmu: ");
		
		mi2 = new JTextField("100", 5);
		lambda2 = new JTextField("0.5", 5);
		minX2 = new JTextField("-2.0", 5);
		minY2 = new JTextField("-2.0", 5);
		maxX2 = new JTextField("2.0", 5);
		maxY2 = new JTextField("2.0", 5);
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
		add(wspolczynnikInterpolacji1, "0 3 r c");
		add(wspolczynnikInterpolacji2, "1 3 c c");
		add(rodzajAlgorytmu1, "2 3 r c");
		add(rodzajAlgorytmu2, "3 3 c c");
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
		parametry.add(wspolczynnikInterpolacji2.getText());
		//if (rodzajAlgorytmu2.getSelectedItem())
		parametry.add((String)rodzajAlgorytmu2.getSelectedItem());
		return parametry;
	}
}


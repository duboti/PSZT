package ewolucyjne;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class Widok extends JFrame
{
	private static final long serialVersionUID = 1L;
	Kontroler kontroler;
	JEditorPane konsola;
	ArrayList<String> parametry;
	
	//Widok (Kontroler kontr)
	Widok ()
	{
		super("Algorytm ewolucyjny");
		//kontroler = kontr;
		konsola = new JEditorPane();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		/*
		TableLayout lay = new TableLayout(cols, rows);
	    lay.setHGap(7);  // odstępy pomiędzy komórkami
	    lay.setVGap(5);
	    setLayout(lay);
	    */
		
		JLabel param = new JLabel("<html>Funkcja:  f(x,y) = (1 - x)<sup>2</sup> + 100 (y - x<sup>2</sup>)<sup>2</sup> <br><br>Parametry algorytmu:<br></html>");
		add(param);
		
		JLabel mi1 = new JLabel("μ:");
		JLabel lambda1 = new JLabel("λ:");
		JLabel minX1 = new JLabel("min x:");
		JLabel minY1 = new JLabel("min y:");
		JLabel maxX1 = new JLabel("max x:");
		JLabel maxY1 = new JLabel("max y:");
		JLabel wspolczynnikInterpolacji1 = new JLabel("wspolczynnik interpolacji:");
		JLabel rodzajAlgorytmu1 = new JLabel("rodzaj algorytmu:");
		
		JTextField mi2 = new JTextField("100", 5);
		JTextField lambda2 = new JTextField("0.5", 5);
		JTextField minX2 = new JTextField("-2.0", 5);
		JTextField minY2 = new JTextField("-2.0", 5);
		JTextField maxX2 = new JTextField("2.0", 5);
		JTextField maxY2 = new JTextField("2.0", 5);
		JTextField wspolczynnikInterpolacji2 = new JTextField("0.5", 5);
		JComboBox rodzajAlgorytmu2 = new JComboBox(new String[] {"μ + λ","μ, λ"});
		
		add(mi1);
		add(mi2);
		add(lambda1);
		add(lambda2);
		add(minX1);
		add(minX2);
		add(minY1);
		add(minY2);
		add(maxX1);
		add(maxX2);
		add(maxY1);
		add(maxY2);
		add(wspolczynnikInterpolacji1);
		add(wspolczynnikInterpolacji2);
		add(rodzajAlgorytmu1);
		add(rodzajAlgorytmu2);
		
		JButton przycisk1 = new JButton("Inicjuj");
		JButton przycisk2 = new JButton("1 krok algorytmu");
		JButton przycisk3 = new JButton("Znajdź rozwiązanie");
		JButton przycisk4 = new JButton("Zatrzymaj rozwiązywanie");
		JButton przycisk5 = new JButton("Wyczyść konsole");
		/*przycisk1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Inicjuj();				
			}
		});*/
		przycisk5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				konsola.setText("");			
			}
		});
		add(przycisk1);
		add(przycisk2);
		add(przycisk3);
		add(przycisk4);
		add(przycisk5);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(500, 300));
		konsola.setEditable(false);
		scrollPane.setViewportView(konsola);
		add(scrollPane);
		setPreferredSize(new Dimension(600, 500));
		pack();
		setVisible(true);
	}
}

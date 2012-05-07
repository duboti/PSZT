package ewolucyjne;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class Widok extends JFrame
{
	private static final long serialVersionUID = 1L;
	ParametryPanel parametryPanel;
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
		
		JLabel param = new JLabel("<html><center>Funkcja:  f(x,y) = (1 - x)<sup>2</sup> +" +
				" 100 (y - x<sup>2</sup>)<sup>2</sup> <br><br>Parametry algorytmu:<br></center></html>");
		add(param);
		
		parametryPanel = new ParametryPanel();
		add(parametryPanel);
		
		JButton przycisk1 = new JButton("Inicjuj");
		JButton przycisk2 = new JButton("1 krok algorytmu");
		JButton przycisk3 = new JButton("Znajdź rozwiązanie");
		JButton przycisk4 = new JButton("Zatrzymaj rozwiązywanie");
		JButton przycisk5 = new JButton("Wyczyść konsole");
		
		przycisk1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				parametry = parametryPanel.Inicjuj();	
				konsola.setText(parametry.toString());
			}
		});
		
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
		
		setPreferredSize(new Dimension(650, 650));
		pack();
		setVisible(true);
	}
}


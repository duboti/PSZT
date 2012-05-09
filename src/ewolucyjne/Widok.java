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
	JButton przycisk1;
	JButton przycisk2;
	JButton przycisk3;
	JButton przycisk4;
	JButton przycisk5;
	
	Widok ()
	{
		super("Algorytm ewolucyjny");
		konsola = new JEditorPane();
		kontroler = new Kontroler(konsola);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		JLabel param = new JLabel("<html><center>Funkcja:  f(x,y) = (1 - x)<sup>2</sup> +" +
				" 100 (y - x<sup>2</sup>)<sup>2</sup> <br><br>Parametry algorytmu:<br></center></html>");
		add(param);
		
		parametryPanel = new ParametryPanel();
		add(parametryPanel);
		
		przycisk1 = new JButton("Inicjuj");
		przycisk2 = new JButton("1 krok algorytmu");
		przycisk3 = new JButton("Znajdź rozwiązanie");
		przycisk4 = new JButton("Zatrzymaj rozwiązywanie");
		przycisk5 = new JButton("Wyczyść konsole");
		
		przycisk2.setEnabled(false);
		przycisk3.setEnabled(false);
		przycisk4.setEnabled(false);
		
		przycisk1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				parametry = parametryPanel.Inicjuj();	
				if (kontroler.inicjowanie(parametry))
				{
					przycisk2.setEnabled(true);
					przycisk3.setEnabled(true);
				}
				else
					konsola.setText(konsola.getText()+"Wysąpił błąd podczas inicjacji.\n");
			}
		});
		
		przycisk2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				kontroler.krok();			
			}
		});
		
		przycisk3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				kontroler.start();
				przycisk2.setEnabled(false);
				przycisk3.setEnabled(false);
				przycisk4.setEnabled(true);
			}
		});
		
		przycisk4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				kontroler.przerwij();
				przycisk2.setEnabled(true);
				przycisk3.setEnabled(true);
				przycisk4.setEnabled(false);
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
		
		setPreferredSize(new Dimension(650, 670));
		pack();
		setVisible(true);
	}
	
	void koniecAlgorytmu ()
	{
		przycisk2.setEnabled(false);
		przycisk3.setEnabled(false);
		przycisk4.setEnabled(false);
	}
}


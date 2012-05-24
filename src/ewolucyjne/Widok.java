package ewolucyjne;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

/**
 * Klasa odpowiedzialna za stworzenie i obsługę okna programu.
 * @author Daniel Pogrebniak
 */
public class Widok extends JFrame
{
	private static final long serialVersionUID = 1L;
	ParametryPanel parametryPanel;
	Kontroler kontroler;
	JEditorPane konsola;
	ArrayList<String> parametry;
	Widok widok;
	JButton przycisk1;
	JButton przycisk2;
	JButton przycisk3;
	JButton przycisk4;
	JButton przycisk5;
	JButton przycisk6;
	
	Widok ()
	{
		super("Algorytm ewolucyjny");
		konsola = new JEditorPane();
		kontroler = null;
		widok = this;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		JLabel funkcja = new JLabel("<html><center>Funkcja:  f(x,y) = (1 - x)<sup>2</sup> +" +
				" 100 (y - x<sup>2</sup>)<sup>2</sup> <br><br>Parametry algorytmu:<br></center></html>");
		add(funkcja);
		
		parametryPanel = new ParametryPanel();
		add(parametryPanel);
		
		przycisk1 = new JButton("Inicjuj");
		przycisk2 = new JButton("1 krok algorytmu");
		przycisk3 = new JButton("Znajdź rozwiązanie");
		przycisk4 = new JButton("Zatrzymaj rozwiązywanie");
		przycisk5 = new JButton("Rozpocznij od nowa");
		przycisk6 = new JButton("Wyczyść konsole");
		
		przycisk2.setEnabled(false);
		przycisk3.setEnabled(false);
		przycisk4.setEnabled(false);
		przycisk5.setEnabled(false);
		
		przycisk1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				parametry = parametryPanel.Inicjuj();	
				kontroler = new Kontroler(widok);
				if (kontroler.inicjowanie(parametry))
				{
					przycisk1.setEnabled(false);
					przycisk2.setEnabled(true);
					przycisk3.setEnabled(true);
					przycisk5.setEnabled(true);
					parametryPanel.zablokujParametry();
				}
				else
					konsola.setText(konsola.getText()+"Wystąpił błąd podczas inicjacji.\n");
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
				kontroler.uruchom();
				przycisk2.setEnabled(false);
				przycisk3.setEnabled(false);
				przycisk4.setEnabled(true);
				przycisk5.setEnabled(false);
			}
		});
		
		przycisk4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				kontroler.przerwij();
				przycisk2.setEnabled(true);
				przycisk3.setEnabled(true);
				przycisk4.setEnabled(false);
				przycisk5.setEnabled(true);
			}
		});
		
		przycisk5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				przycisk1.setEnabled(true);
				przycisk2.setEnabled(false);
				przycisk3.setEnabled(false);
				przycisk4.setEnabled(false);
				przycisk5.setEnabled(false);
				parametryPanel.odblokujParametry();
				kontroler = null;
			}
		});
		
		przycisk6.addActionListener(new ActionListener() {
			
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
		add(przycisk6);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(550, 300));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		konsola.setEditable(false);
		scrollPane.setViewportView(konsola);
		add(scrollPane);
		
		setPreferredSize(new Dimension(650, 670));
		pack();
		setVisible(true);
	}
	
	/**
	 * Metoda zmienia dostępność przycisków po zakończeniu algorytmu.
	 */
	void koniecAlgorytmu ()
	{
		przycisk2.setEnabled(false);
		przycisk3.setEnabled(false);
		przycisk4.setEnabled(false);
		przycisk5.setEnabled(true);
	}
	
	/**
	 * Metoda dopisuje na górze konsoli nowy napis. 
	 * @param napis, który ma zostać dopisany
	 */
	public void dodajNapis (String napis)
	{
		konsola.setText(napis+konsola.getText());
	}
}


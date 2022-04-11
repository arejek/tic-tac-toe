package main;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PlayerFrame extends JFrame implements ActionListener {
	
	public String signOfPlayer; //znak gracza, deklarowany przy 1st uzyciu funkcji paintFrame()
	public String signOfOpponent;
	
	public JButton jb1 = new JButton();
	public JButton jb2 = new JButton();
	public JButton jb3 = new JButton();
	public JButton jb4 = new JButton();
	public JButton jb5 = new JButton();
	public JButton jb6 = new JButton();
	public JButton jb7 = new JButton();
	public JButton jb8 = new JButton();
	public JButton jb9 = new JButton();
	JButton[] tabOfB = {jb1, jb2, jb3, jb4, jb5, jb6, jb7, jb8, jb9};
	
	public int tabOfClicked[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};
	public boolean moveMade = false;
	public int lastChange;
	
	public JFrame jf;
	public JPanel boardPanel;
	public JPanel messagePanel;
	public JTextArea textArea;
	
	public void paintFrame(String sign1, String sign2) {
		
		signOfPlayer = sign1;
		signOfOpponent = sign2;
		
		jf = new JFrame("Gracz " + signOfPlayer);
		jf.setSize(300, 600);
		
		//Creating boardPanel
		boardPanel = new JPanel();
		boardPanel.setLayout(new GridLayout(3,3));
		
		for(int i=0; i<9; i++) {
			boardPanel.add(tabOfB[i]);
			tabOfB[i].addActionListener(this);
			}
		
		//Creating messagePanel
		messagePanel = new JPanel();
		messagePanel.setSize(250,250);
		messagePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		textArea = new JTextArea();
		textArea.setSize(250, 250);
		textArea.setEditable(false);
		
		messagePanel.add(textArea);
		
		//Back to JFrame
		jf.add(boardPanel);
		jf.add(messagePanel);
		jf.setLayout(new BoxLayout(jf.getContentPane(),BoxLayout.Y_AXIS));
		jf.setVisible(true);
		jf.setLocationRelativeTo(null);
	    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
	}
	
	public void actionPerformed(ActionEvent e) {
		 
		if(e.getSource() == jb1) { makeSigned(jb1); makeClicked(jb1); }	
    	if(e.getSource() == jb2) { makeSigned(jb2); makeClicked(jb2); }	
        if(e.getSource() == jb3) { makeSigned(jb3); makeClicked(jb3); }	
        if(e.getSource() == jb4) { makeSigned(jb4); makeClicked(jb4); }	
        if(e.getSource() == jb5) { makeSigned(jb5); makeClicked(jb5); }	
        if(e.getSource() == jb6) { makeSigned(jb6); makeClicked(jb6); }	
        if(e.getSource() == jb7) { makeSigned(jb7); makeClicked(jb7); }	
        if(e.getSource() == jb8) { makeSigned(jb8); makeClicked(jb8); }	
        if(e.getSource() == jb9) { makeSigned(jb9); makeClicked(jb9); }	
        
	}
	
	public void makeSigned(JButton b) {
		 b.setEnabled(false);
		 b.setText(signOfPlayer);
		 disableButtons();
		 moveMade = true;
	}
	
	public boolean wasMoveMade() {
	 return moveMade;
	}
	
	//funkcja zapisujaca ktorych przyciskow funkcja enableButtons() ma nie uruchamiac z powrotem
	public void makeClicked(JButton b) {
		if(b == jb1) { tabOfClicked[0] = 1; lastChange = 0; } 
		if(b == jb2) { tabOfClicked[1] = 1; lastChange = 1; }
		if(b == jb3) { tabOfClicked[2] = 1; lastChange = 2; }
		if(b == jb4) { tabOfClicked[3] = 1; lastChange = 3; }
		if(b == jb5) { tabOfClicked[4] = 1; lastChange = 4; }
		if(b == jb6) { tabOfClicked[5] = 1; lastChange = 5; }
		if(b == jb7) { tabOfClicked[6] = 1; lastChange = 6; }
		if(b == jb8) { tabOfClicked[7] = 1; lastChange = 7; }
		if(b == jb9) { tabOfClicked[8] = 1; lastChange = 8; }
	}
	
	//przekazuje informacje o zmianach wprowadzonych przez gracza
	public int tellChange() {		
	 return lastChange;
	}
	
	//wylacza guziki (other players turn)
	public void disableButtons() {
		for(int i=0; i<9; i++) {
			tabOfB[i].setEnabled(false);;
			}
	}
	
	//wlacza guziki, oprocz juz kliknietych
	public void enableButtons() {
		for(int i=0; i<9; i++) {
			if(tabOfClicked[i] != 1) tabOfB[i].setEnabled(true);
			}
	}
	
	public void updateButtons(int field) {
		if(field == 0) { jb1.setText(signOfOpponent); jb1.setEnabled(false); tabOfClicked[0] = 1;}
		if(field == 1) { jb2.setText(signOfOpponent); jb2.setEnabled(false); tabOfClicked[1] = 1;}
		if(field == 2) { jb3.setText(signOfOpponent); jb3.setEnabled(false); tabOfClicked[2] = 1;}
		if(field == 3) { jb4.setText(signOfOpponent); jb4.setEnabled(false); tabOfClicked[3] = 1;}
		if(field == 4) { jb5.setText(signOfOpponent); jb5.setEnabled(false); tabOfClicked[4] = 1;}
		if(field == 5) { jb6.setText(signOfOpponent); jb6.setEnabled(false); tabOfClicked[5] = 1;}
		if(field == 6) { jb7.setText(signOfOpponent); jb7.setEnabled(false); tabOfClicked[6] = 1;}
		if(field == 7) { jb8.setText(signOfOpponent); jb8.setEnabled(false); tabOfClicked[7] = 1;}
		if(field == 8) { jb9.setText(signOfOpponent); jb9.setEnabled(false); tabOfClicked[8] = 1;}	
	}
	
	//jeszcze raz wyswietla frame - chodzi o modyfikacje boarda przez drugiego gracza
	public void repaintFrame() {
		jf.repaint();
	}
	
	//drukuje Stringa pod plansza z gra
	public void displayMessage(String str) {
		textArea.append(str);
	}
	
	public void yourTurn() {
		enableButtons();
		repaintFrame();
	}
	
	public void oppTurn() {
		disableButtons();
		moveMade = false;
	}
	
	public void finishGame() {
		disableButtons();
	}
	
}

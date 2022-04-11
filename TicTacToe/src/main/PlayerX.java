package main;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class PlayerX {
	
	//-----------------------------------------------------------------------------------------
	//Funkcje obslugujace przesylanie obiektu GameBoard pomiedzy serwerem i klientem
	//serwer = PlayerX, klient = PlayerO
	
	public static void sendGameBoard(Socket sock, GameBoard gb) throws IOException {
		OutputStream outputStream = sock.getOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		objectOutputStream.writeObject(gb);
	}
	
	public static GameBoard getGameBoard(Socket sock) throws IOException, ClassNotFoundException {
		InputStream inputStream = sock.getInputStream();
		ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
		GameBoard gb  = (GameBoard) objectInputStream.readObject();
	 return gb;
	}
	//-----------------------------------------------------------------------------------------
	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		//PlayerFrame wyswietla plansze (9 przyciskow) i komunikaty o przebiegu gry
		PlayerFrame frameForX = new PlayerFrame();
		frameForX.paintFrame("X","O");
		frameForX.disableButtons();
		frameForX.displayMessage("Waiting for player O to connect");
		
		ServerSocket ss = new ServerSocket(7777);
		Socket sock = ss.accept();
		
		if(sock.isConnected()) frameForX.displayMessage("\nPlayer O connected");
		
		//GameBoard to obiekt ktory przekazuja sobie gracze
		//(Zmieniaja go podczas swojej tury, po czym przesylaja z powrotem do drugiego gracza)
		GameBoard gb = new GameBoard();
		gb.setBoard();
		
		while(true) {
			frameForX.displayMessage("\nIt's Your turn!");
			
			while(!frameForX.wasMoveMade()){
				frameForX.yourTurn();
				}
			
			gb.changeBoard(frameForX.lastChange, "X");
			sendGameBoard(sock,gb);
			if(gb.isFinished()) break; 
			
			frameForX.oppTurn(); //opponent's turn
			frameForX.displayMessage("\nNow wait, it's O's turn");
			
			gb = getGameBoard(sock);
			frameForX.updateButtons(gb.lastPlayerChange);
			if(gb.isFinished()) break; 
				
		}
		frameForX.finishGame();
		if(gb.isFinished()) frameForX.displayMessage("\nGAME FINISHED");
		
		sock.close();
		ss.close();	
			
	}
}

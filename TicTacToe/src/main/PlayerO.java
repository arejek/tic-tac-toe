package main;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class PlayerO {
	
	//-----------------------------------------------------------------------------------------
	//Funkcje obslugujace przesylanie obiektu GameBoard pomiedzy serwerem i klientem (graczami)
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
		
		Socket sock = new Socket("localhost",7777);
		
		PlayerFrame frameForO = new PlayerFrame();
		frameForO.paintFrame("O","X");
		frameForO.displayMessage("You are connected with Player X");
		frameForO.disableButtons();
		
		GameBoard gb;
		
		while(true) {
			
			gb = getGameBoard(sock);
			
			frameForO.updateButtons(gb.lastPlayerChange);
			if(gb.isFinished()) break;
			
			frameForO.displayMessage("\nIt's Your turn!");
			
			while(!frameForO.wasMoveMade()){
				frameForO.yourTurn();
				}
			
			frameForO.oppTurn(); //opponent's turn
			frameForO.displayMessage("\nNow wait, it's X's turn");
			
			gb.changeBoard(frameForO.lastChange, "O");
			sendGameBoard(sock,gb);
			if(gb.isFinished()) break;
			
		}
		frameForO.finishGame();
		if(gb.isFinished()) frameForO.displayMessage("\nGAME FINISHED");
			
		sock.close();	
	}
	
}
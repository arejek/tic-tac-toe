package main;

import java.io.Serializable;

public class GameBoard implements Serializable {
	
	public String[] board = new String[9];
	public int lastPlayerChange;
	
	public void setBoard() {
		for(int i=0; i<9; i++) {
			board[i] = null;
		}
	}
	
	public boolean isWinner() {
		
		return (   board[0] != null && board[0].equals(board[1]) && board[0].equals(board[2])
				|| board[3] != null && board[3].equals(board[4]) && board[3].equals(board[5])
				|| board[6] != null && board[6].equals(board[7]) && board[6].equals(board[8])
				|| board[0] != null && board[0].equals(board[4]) && board[0].equals(board[8])
				|| board[2] != null && board[2].equals(board[4]) && board[2].equals(board[6])
				|| board[0] != null && board[0].equals(board[3]) && board[0].equals(board[6])
				|| board[1] != null && board[1].equals(board[4]) && board[1].equals(board[7])
				|| board[2] != null && board[2].equals(board[5]) && board[2].equals(board[8]));
		
		/*Kolejno
		 * 
		 * xxx --- ---
		 * --- xxx ---
		 * --- --- xxx
		 * 
		 * x-- -x- --x
		 * --x -x- x--
		 * 
		 * x-- x-- x--
		 * -x- -x- -x-
		 * --x --x --x
		 * 
		*/
		
	}
	
	public boolean isFull() {
		return ( board[0] != null && board[1] != null && board[2] != null 
			  && board[3] != null && board[4] != null && board[5] != null
			  && board[6] != null && board[7] != null && board[8] != null);
	}
	
	public boolean isDraw() {
	 return isFull() && !isWinner();
	}
	
	public boolean isFinished() {
	 return isWinner() || isDraw();
	}
	
	public void changeBoard(int field, String sign) {
		board[field] = sign;
		lastPlayerChange = field;
	}
	
}
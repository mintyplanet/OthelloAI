package othelloAI;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import othelloAI.Board.Piece;

public class Game implements Observer{
	/**
	 * @param args
	 */
	int DIMENSION = 8;
	
	private final Board board;
	private final GUI gui;
	private final AI ai;
	private final Piece aiPlayer;

	public Game(){
		Piece[] players = {Piece.BLACK,Piece.WHITE};
		Piece p = (Piece) JOptionPane.showInputDialog(null, "White plays first.", "Choose your colour", 
				 1, null, players, Piece.WHITE);
		aiPlayer = Board.opponent(Piece.WHITE);
		board = new Board(DIMENSION, p);
		gui = new SwingUI(board, this);
		ai = new AI(board, Board.opponent(p));
		ai.addObserver(this);
		
		advanceGame();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (board.move((Point)arg)){
		board.rotatePlayer();
		advanceGame();			
		}
	}
	private void advanceGame() {
		gui.draw();	
		//gui.togglePause();
		if (board.availablePositions().isEmpty()){
			board.rotatePlayer();
			gui.showMessage("No place to move. Pass");
		}
		if (board.isGameOver()){
			gui.showMessage("Game over. " + board.winner() + " is the winner!");
		}
		else if(board.getCurrentPlayer() == aiPlayer){
			new Thread(ai).start();
		}
		
	}

	public static void main(String[] args) {
		new Game();
	}	
}

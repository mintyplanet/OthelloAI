package othelloAI;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import javax.swing.plaf.SliderUI;

import othelloAI.Board.Piece;

public class Game implements Observer{
	/**
	 * @param args
	 */
	int DIMENSION = 8;
	
	private final Board board;
	private final UI gui;
	private final AI ai;
	private Thread aiThread;
	
	public Game(){
		board = new Board(DIMENSION, Piece.WHITE);//Who makes the first move. Prompt the user for this
		gui = new UI(board, this);
		ai = new AI(board, Piece.BLACK);
		ai.addObserver(this);
		
		aiThread = new Thread(ai);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (board.move((Point)arg)){
			advanceGame();			
		}
	}
	private void advanceGame() {
		board.rotatePlayer();
		gui.draw();	
		gui.togglePause();
		if (board.isGameOver()){
			gui.setMessage("Game over. " + board.winner() + " is the winner!");
		}
		else if(board.getCurrentPlayer() == Piece.BLACK){ //TODO if AI
			//ai.makeMove();
			aiThread.start();
		}
		else{aiThread = new Thread(ai);}
		
	}

	public static void main(String[] args) {
		new Game();
	}	
}

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
	private final Board board;
	private final UI gui;
	private Piece currentPlayer;
	
	public Game(){
		currentPlayer = Piece.WHITE; //Who makes the first move. Prompt the user for this
		board = new Board(8);
		gui = new UI(board, this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (board.move((Point)arg, currentPlayer)){
			advanceGame();
			gui.draw();
		}
	}
	private void advanceGame() {
		currentPlayer = Board.opponent(currentPlayer);
		//gui.togglePause();
		if (board.isGameOver()){
			System.out.println(board.winner());
		}
		else if(currentPlayer == Piece.BLACK){ //TODO if AI
			//waitasec();advanceGame();//ai.makeMove();			
		}
		
	}

	private static void waitasec() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Board.Piece getCurrentPlayer(){return currentPlayer;}
	
	public static void main(String[] args) {
		new Game();
	}	
}

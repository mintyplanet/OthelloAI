package othelloAI;
import static java.lang.Math.*;

import java.awt.Point;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.Observable;

public class AI extends Observable implements Runnable {

	private final Board.Piece aiPiece;
	private final Board board;
	//private final HashMap<Board, Integer> memoize;

	int MAXDEPTH = 10;

	public AI(Board b, Board.Piece piece){
		board = b;
		aiPiece = piece; //Minimizing player
		//memoize = new HashMap<Board, Integer>();
	}

	private int minimaxAB(Board b, int depth, int alpha, int beta) {
		if (depth <= 0 || b.isGameOver()) {
			return b.heuristicWinning();
		}

		if (b.getCurrentPlayer() == aiPiece) {
			for (Point p : b.availablePositions()) {
				Board gamestate = new Board(b);
				gamestate.move(p);
				gamestate.rotatePlayer();
				alpha = max(alpha, minimaxAB(gamestate, depth - 1, alpha, beta));
				if (beta <= alpha)
					break;
			}return alpha;
		} else {
			for (Point p : b.availablePositions()) {
				Board gamestate = new Board(b);
				gamestate.move(p);
				gamestate.rotatePlayer();
				alpha = min(beta, minimaxAB(gamestate, depth - 1, alpha, beta));
				if (beta <= alpha)
					break;
			}
		}return beta;
	}


	public void makeMoveAB(){
		ArrayList<Point> avail = board.availablePositions();
		int smallest = java.lang.Integer.MAX_VALUE;
		int smallestIndex = 0;
		for (int i=0; i<avail.size(); i++){
			Board gamestate = new Board(board);
			gamestate.move(avail.get(i));
			gamestate.rotatePlayer();
			int score = minimaxAB(gamestate, MAXDEPTH, java.lang.Integer.MIN_VALUE, java.lang.Integer.MAX_VALUE);
			if (smallest>score){
				smallest = score;
				smallestIndex = i;
			}
		}

		setChanged();
		notifyObservers(avail.get(smallestIndex));
	}

	public void makeMove(){
		ArrayList<Point> avail = board.availablePositions();
		int smallest = java.lang.Integer.MAX_VALUE;
		int smallestIndex = 0;
		for (int i=0; i<avail.size(); i++){
			Board gamestate = new Board(board);
			gamestate.move(avail.get(i));
			//gamestate.rotatePlayer();
			int score = minimax(gamestate, MAXDEPTH);

			System.out.print("smallest:"+smallest);
			System.out.println("minimax:"+score);
			if (smallest>score){
				System.out.println("HA!");
				smallest = score;
				smallestIndex = i;
			}
		}

		setChanged();
		notifyObservers(avail.get(smallestIndex));
	}

	private int playerSign(Board.Piece player){
		if(player == aiPiece) return -1;
		return 1;
	}

	private int minimax(Board b, int depth){
		if(depth<=0 || b.isGameOver()){
			return  b.heuristicWinning();
		}
		/*if(memoize.containsKey(b)){
			System.out.print("MEMO!");
			return memoize.get(board);
		}*/

		int alpha = playerSign(b.getCurrentPlayer()) * java.lang.Integer.MAX_VALUE;

		for(Point p: b.availablePositions()){
			Board gamestate = new Board(b);
			gamestate.move(p);
			gamestate.rotatePlayer();
			int score = minimax(gamestate, depth -1);

			alpha = (aiPiece==b.getCurrentPlayer()) ?max(alpha, score):min(alpha,score);
		}
		//memoize.put(b, alpha);
		//System.out.println("minimax:"+alpha);
		return alpha;
	}

	@Override
	public void run() {
		makeMoveAB();
	}

}

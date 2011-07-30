package othelloAI;
import static java.lang.Math.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

public class AI extends Observable implements Runnable {
	
	private final Board.Piece aiPiece;
	private final Board board;
	//private final HashMap<Board, Integer> memoize;
	
	int MAXDEPTH = 5;

	public AI(Board b, Board.Piece piece){
		board = b;
		aiPiece = piece; //Minimizing player
		//memoize = new HashMap<Board, Integer>();
	}
	
	
	public void makeMove(){
		ArrayList<Point> avail = board.availablePositions();
		int smallest = -10000;
		int smallestIndex = 0;
		for (int i=0; i<avail.size(); i++){
			Board gamestate = new Board(board);
			gamestate.move(avail.get(i));
			gamestate.rotatePlayer();
			int score = minimax(gamestate, MAXDEPTH);
			if (smallest>score){
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
		if(depth<=0){
			return  b.heuristicWinning();
		}
		/*if(memoize.containsKey(b)){
			System.out.print("MEMO!");
			return memoize.get(board);
		}*/
		
		int alpha = playerSign(b.getCurrentPlayer()) * 1000;

		for(Point p: b.availablePositions()){
			Board gamestate = new Board(b);
			gamestate.move(p);
			gamestate.rotatePlayer();
			int score = minimax(gamestate, depth -1);

			alpha = (aiPiece==b.getCurrentPlayer()) ?max(alpha, score):min(alpha,score);
		}
		//memoize.put(b, alpha);
		return alpha;
	}

	@Override
	public void run() {
		makeMove();
	}

}

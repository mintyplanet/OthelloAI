package othelloAI;

import java.util.ArrayList;
import java.util.Observable;
import java.awt.Point;

public class Board{
	
	public static enum Piece{
		BLACK,
		WHITE,
		EMPTY
	}
	
	private int dimension;	
	private Piece[] board;
		
	private static final int[][] directions = 
	{
		{-1,1},{0,1},{1,1},
		{-1,0},		 {1,0},
		{-1,-1},{0,-1},{1,-1}
	};

	public Board(int dimension){
		this.dimension = dimension;
		board = new Piece[dimension * dimension];
		//Initial Position
		java.util.Arrays.fill(board, Piece.EMPTY);		
		setPosition(dimension/2 -1, dimension/2 -1, Piece.WHITE);
		setPosition(dimension/2, dimension/2, Piece.WHITE);
		setPosition(dimension/2 -1, dimension/2, Piece.BLACK);
		setPosition(dimension/2, dimension/2 -1, Piece.BLACK);
	}

	private ArrayList<Point> flippablePieces(int x,int y, Piece player){
		ArrayList<Point> flippable = new ArrayList<Point>();
		for(int i=0; i<8; i++){			
			flippable.addAll(listFlippableOneDirection(new Point(x,y), directions[i], player));
		}
		return flippable;
	}

	public Piece[] getBoard() {return board;}

	public int getDimension() {return dimension;}
	
	public Piece getPosition(int x, int y)/* throws Exception*/{
		//		if( x<0 | x>dimension | y<0 | y>dimension){
		//			throw new Exception("Out of bounds.");
		//			}
		return board[x * dimension + y];
	}


	public Piece getPosition(Point pos) {
		return getPosition(pos.x, pos.y);
	}
	
	
	public boolean isGameOver(){
		for(Piece p: board){
			if (p == Piece.EMPTY){
				return false;
			}
		}
		return true;
	}
	
	private boolean isValidPosition(int x, int y){
		if( x<0 || x>dimension || y<0 || y>dimension){
			return false;
		}
		return true;
	}
	private boolean isValidPosition(Point p){
		return isValidPosition(p.x, p.y);
	}
	private ArrayList<Point> listFlippableOneDirection(Point pos, int[] dir, Piece player) {
		pos.translate(dir[0], dir[1]);
		ArrayList<Point> flip = new ArrayList<Point>();
		while (isValidPosition(pos)) {
			Board.Piece p = getPosition(pos);
			if (p == opponent(player)) {
				flip.add(new Point(pos));
				pos.translate(dir[0], dir[1]);
			} else if (p == player && !flip.isEmpty()) {
				break;
			} else {
				flip.clear();
				break;
			}
		}
		return flip;
	}

	static Piece opponent(Piece p){
		if(p == Piece.WHITE){return Piece.BLACK;}
		else if(p == Piece.BLACK){return Piece.WHITE;}
		else return p;
	}

	public boolean move(int x, int y, Piece player){
		ArrayList<Point> fp = flippablePieces(x, y, player);
		if (!fp.isEmpty()){
			setPosition(x,y,player);
			for(Point pos: fp){
				setPosition(pos, player);
			}
			return true;
		}
		return false;
	}
	public boolean move(Point point, Piece player) {
		return move(point.x, point.y, player);
	}
	public void setPosition(int x, int y, Piece p){
		board[x * dimension + y] = p;
	}
	public void setPosition(Point pos, Piece p) {
		setPosition(pos.x, pos.y, p);		
	}


	public String toString(){
		StringBuilder sb = new StringBuilder("-------------------------\n");
		for(int x = 0; x < dimension; x++){
			sb.append("|");
			for(int y = 0; y < dimension; y++){
				if(getPosition(x,y) != null){
					sb.append(getPosition(x,y).toString() + " |");}
				else{sb.append("  |");}
			}sb.append("\n-------------------------\n");
		}
		return sb.toString();
	}

	public Piece winner() {
		// TODO Auto-generated method stub
		return null;
	}
}
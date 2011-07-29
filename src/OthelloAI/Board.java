package OthelloAI;

import java.util.ArrayList;

//import static java.util.Arrays.fill;

public class Board {
	private static final int[][] directions = 
	{
		{-1,1},{0,1},{1,1},
		{-1,0},		 {1,0},
		{-1,-1},{0,-1},{1,-1}
	};

	public static enum Piece{
		BLACK,
		WHITE,
		EMPTY
	};
		
	private int dimension;
	private Piece[] board;
	private boolean playerTurn = true;
	private Piece player;

	public Board(int dimension){
		this.dimension = dimension;
		board = new Piece[dimension * dimension];
		this.player = Piece.WHITE;
		//Initial Position
		java.util.Arrays.fill(board, Piece.EMPTY);		
		setPosition(dimension/2 -1, dimension/2 -1, Piece.WHITE);
		setPosition(dimension/2, dimension/2, Piece.WHITE);
		setPosition(dimension/2 -1, dimension/2, Piece.BLACK);
		setPosition(dimension/2, dimension/2 -1, Piece.BLACK);
	}

	public Piece getPosition(int x, int y)/* throws Exception*/{
		//		if( x<0 | x>dimension | y<0 | y>dimension){
		//			throw new Exception("Out of bounds.");
		//			}
		return board[x * dimension + y];
	}

	public void setPosition(int x, int y, Piece p){
		board[x * dimension + y] = p;
	}

	public void Move(int x, int y){
		ArrayList<ArrayList<Boolean>> fp = flippablePieces(x, y);
		if (!fp.isEmpty()){
			for(ArrayList<Boolean> direction: fp){
				for 
			}
		}

	}

	/*public String toString(){
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
	}*/
	
	
	private ArrayList<Boolean> fb(int[] origin, int[] dir) {
		int[] pos = { origin[0] + dir[0], origin[1] + dir[1] };
		ArrayList<Boolean> flip = new ArrayList<Boolean>();
		while (isValidPosition(pos[0], pos[1])) {
			Board.Piece p = getPosition(pos[0], pos[1]);
			if (p == opponent(player)) {
				flip.add(true);
				pos[0] += dir[0];
				pos[1] += dir[1];
			} else if (p == player && !flip.isEmpty()) {
				break;
			} else {
				flip.clear();
				break;
			}
		}
		return flip;
	}
	private ArrayList<ArrayList<Boolean>> flippablePieces(int x,int y){
		ArrayList<ArrayList<Boolean>> flippable = new ArrayList<ArrayList<Boolean>>();
		final int[] origin = {x,y};
		for(int i=0; i<8; i++){			
			flippable.add(fb(origin, directions[i]));
		}
		return flippable;
	}
	private boolean isValidPosition(int x, int y){
		if( x<0 || x>dimension || y<0 || y>dimension){
			return false;
		}
		return true;
	}

	private Piece opponent(Piece p){
		if(p == Piece.WHITE){return Piece.BLACK;}
		else if(p == Piece.BLACK){return Piece.WHITE;}
		else return p;
	}
	public Piece[] getBoard() {return board;}
	public int getDimension() {return dimension;}
	public boolean isPlayersTurn() {return playerTurn;}
}
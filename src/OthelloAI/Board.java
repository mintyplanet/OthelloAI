package OthelloAI;

import java.util.ArrayList;

//import static java.util.Arrays.fill;

public class Board {
	private static int[][] directions = 
	{
		{-1,1},{0,1},{1,1},
		{-1,0},		 {1,0},
		{-1,-1},{0,-1},{1,-1}
	};
	                                    
	                                    
	public static enum Piece{
		BLACK {
			public String toString(){
				return "B";}},
		WHITE {
			public String toString(){
				return "W";}},
		EMPTY {
			public String toString(){
				return "";}}
	}
	private int dimension;
	private Piece[] board;
	private boolean humanTurn = true;
	
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
	
	public Piece getPosition(int x, int y){
		return board[x * dimension + y];
	}
	
	public void setPosition(int x, int y, Piece p){
		board[x * dimension + y] = p;
	}
	
	public void Move(int x, int y){
		Piece player = isHumansTurn()?Piece.WHITE:Piece.BLACK;
		
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
	private ArrayList<boolean[]> fb(int[] origin, int[] dir){
			
		}
	private boolean[][] flippablePieces(int x,int y){
		boolean[][] flippable = new boolean[8][];
		final int[] origin = {x,y};
		for(int i=0; i<8; i++){
			flippable[i] = (boolean[])fb(origin, directions[i]).toArray();
		}
		return flippable;
	}
	
	public Piece[] getBoard() {return board;}
	public int getDimension() {return dimension;}
	public boolean isHumansTurn() {return humanTurn;}
}
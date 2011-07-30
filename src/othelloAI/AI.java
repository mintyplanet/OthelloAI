package othelloAI;
import static java.util.Arrays.copyOf;

public class AI implements Runnable{
	
	private Board.Piece aiPiece;

	public AI(Board.Piece piece){
		aiPiece = piece;
	}

	private int minimax(Board b, int depth){
			if(depth<=0){
				return - b.heuristicWinning();
			}
			
	/*
			if depth <= 0 then
		      -- positive values are good for the maximizing player
		      -- negative values are good for the minimizing player
		      return objective_value(node)
		   end
		   -- maximizing player is (+1)
		   -- minimizing player is (-1)
		   local alpha = -node.player * INFINITY
		 
		   local child = next_child(node,nil)
		   while child = ~nil do
		      local score = minimax(child,depth-1)
		      alpha = node.player==1 and math.max(alpha,score) or math.min(alpha,score)
		      child = next_child(node,child)
		   end
		 
		   return alpha
	*/
			}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}

import java.util.*;
public class test{
	 public static final int MAX_DEPTH = 4;
	public static double minimax(Board board, int depth, char mColor){
    
  	if(depth == 0 | board.isGoal()){
      if(mColor == Board.BLACK){
        mColor = Board.WHITE;
      }
      else{
        mColor = Board.BLACK;
      }
  		return Strategy.offensiveGiven(board, mColor);

  	}
  	// max player
  	if(mColor == Board.WHITE){

  		double bestValue = (double)Integer.MIN_VALUE;
    
  		for (Board it_board : board.getSuccessors(mColor)) {
  			double max = minimax(it_board, depth-1, Board.BLACK);
  	   bestValue = Math.max(bestValue, max);

  		}
  		return bestValue;

  	}
  	// min player
  	else {

  	   double bestValue = (double)Integer.MAX_VALUE;
  
  		for (Board it_board : board.getSuccessors(mColor)) {

  			double max = minimax(it_board, depth-1, Board.WHITE); // change to next state
  			bestValue = Math.min(bestValue, max);

  		}

  		return bestValue;

  	}

  }

  public static Board getState(Board board){
    Board retval = new Board();
    List<Board> firstLevel = new ArrayList<Board>();
    firstLevel = board.getSuccessors(Board.WHITE);
    double bestValue = (double)Integer.MIN_VALUE;
    for(Board curr : firstLevel){
      double v = minimax(curr, MAX_DEPTH - 1, Board.BLACK);
      curr.value = v;
      if(v > bestValue){
        retval = new Board(curr); 
      }
    }
    return retval;
    }
}
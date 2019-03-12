import java.lang.Math;

class Strategy{

	public static double defensiveGiven(Board board, char mColor){
		return 2 * board.numRem(mColor) + Math.random();
	}

	public static double offensiveGiven(Board board, char oColor){
		return 2*(30 - board.numRem(oColor)) + Math.random();
	}

	public static double defensive(Board board, char mColor){
		return -1.0;
	}

	public static double offensive(Board board, char oColor){
		return -1.0;
	}

	// public static int chooseStrategy(String myStrategy, Board, ){

	// }




}
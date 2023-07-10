
public class Piece {
	Board myBoard;
	Square mySquare;
	Player myPlayer;
	boolean isFinished = false;

	public Piece(Board board, Player player) {
		myBoard = board;
		myPlayer = player;
		
		mySquare = myBoard.getStartCell();
		
	}
	
	public void move(String udlr) {
		for (int i = 0; i < udlr.length(); i++) {
			
			if (udlr.charAt(i) == mySquare.getDirectionTo("prev")) {
				mySquare = mySquare.getPrevSquare();
				
			} else if (udlr.charAt(i) == mySquare.getDirectionTo("next")){
				mySquare = mySquare.getNextSquare();
			}
			// check finish
			if (mySquare.index == myBoard.getTotalSquareNum() - 1) {
				isFinished = true;
				
				break;
			}
		}
		
	}
	
	public boolean getIsFinished() {
		return isFinished;
	}
	
	public int getMyLocation() {
		return mySquare.index;
	}
}

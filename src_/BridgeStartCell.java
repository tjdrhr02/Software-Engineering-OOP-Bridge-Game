
public class BridgeStartCell extends Square{
	
	public BridgeStartCell(int i, Board board, String info) {
		index = i;
		
		myBoard = board;
		
		infoAry = info.split(" ");
	}
	
	public char getDirectionTo(String prev_or_next) {
		char directionTo = ' ';
		
		switch (prev_or_next){
			case "prev": 
				directionTo = infoAry[1].charAt(0);
				
				break;
			case "next":
				if (isCross) {
					directionTo = 'R';
				} else directionTo = infoAry[2].charAt(0);
				
				break;
		}
		
		return directionTo;
	}
	
	
	public Square getNextSquare() {
		if (isCross) {
			isCross = false;
			return myBoard.getSquare(getCrossDestinationForward());
		} else return myBoard.getSquare(index + 1);
	}
	
	
}

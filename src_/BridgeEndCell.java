
public class BridgeEndCell extends Square{
	
	public BridgeEndCell(int i, Board board, String info) {
		index = i;
		
		myBoard = board;
		
		infoAry = info.split(" ");
	}
	
	public char getDirectionTo(String prev_or_next) {
		char directionTo = ' ';
		
		switch (prev_or_next){
			case "prev": 
				if (isCross) {
					directionTo = 'L';
				} else directionTo = infoAry[1].charAt(0);
				
				break;
				
			case "next":
				directionTo = infoAry[2].charAt(0);
				
				break;
		}
		
		return directionTo;
	}
	
	
	public Square getPrevSquare() {
		if (isCross) {
			isCross = false;
			return myBoard.getSquare(getCrossDestinationBackward());
		} else return myBoard.getSquare(index - 1);
	}
	
	
}
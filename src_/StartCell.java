
public class StartCell extends Square{
	
	public StartCell(int i, Board board, String info) {
		index = i;
		
		myBoard = board;
		
		infoAry = info.split(" ");
	}
	
	public char getDirectionTo(String prev_or_next) {
		char directionTo = ' ';
		
		switch (prev_or_next){

			case "prev": 
				directionTo = ' ';
				
				break;
			case "next":
				directionTo = infoAry[1].charAt(0);
				
				break;
		}
		
		return directionTo;
	}
	
	
	public Square getNextSquare() {
		return myBoard.getSquare(index + 1);
	}
	
}
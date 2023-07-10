

public class Square {
	protected int index;
	protected Board myBoard;
	protected String[] infoAry;
	protected boolean isCross = false;
	
	public Square() {
	}

	public Square(int i, Board board, String info) {
		index = i;
		
		myBoard = board;
		
		infoAry = info.split(" ");
	}
	
	public String getMapInfo(int i) {
		return infoAry[i];
	}
	
	public char getDirectionTo(String prev_or_next) {
		char directionTo = ' ';
		
		switch (prev_or_next){
			case "prev": 
				directionTo = infoAry[1].charAt(0);
				
				break;
			case "next":
				directionTo = infoAry[2].charAt(0);
				
				break;
		}
		
		return directionTo;
	}
	
	public Square getPrevSquare() {
		return myBoard.getSquare(index - 1);
	}
	
	public Square getNextSquare() {
		return myBoard.getSquare(index + 1);
	}
	
	public boolean getIsCross() {
		return isCross;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIsCross(boolean wantCross) {
		if (wantCross) {
			isCross = true;
		} else isCross = false;
	}
	
	public int getCrossDestinationForward() {
		int i = 0;
		
		while (myBoard.getSquare(index + i).infoAry[2].equals(myBoard.getSquare(index + i + 1).infoAry[2])){
			i++;
		}
		
		int crossDestination = index + (i + 1) * 2 + 2;
		
		return crossDestination;
	}
	
	public int getCrossDestinationBackward() {
		int i = 0;
		
		while (myBoard.getSquare(index - i).infoAry[1].equals(myBoard.getSquare(index - i - 1).infoAry[1])) {
			i++;
		}
		
		int crossDestination = index - (i + 1) * 2 - 2;
		
		return crossDestination;
	}

}

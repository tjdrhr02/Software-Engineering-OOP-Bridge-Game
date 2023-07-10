import java.util.List;


public class Board {
	private int totalSquareNum;
	private Square[] squares;
	
	public Board(List<String> squareInfos) {
		totalSquareNum = squareInfos.size();
		
		squares = new Square[totalSquareNum];
		squares[0] = new StartCell(0, this, squareInfos.get(0));
		for (int i = 1; i < totalSquareNum; i++) {
			switch (squareInfos.get(i).charAt(0)) {					
				case 'B':
					squares[i] = new BridgeStartCell(i, this, squareInfos.get(i));
					break;
					
				case 'b':
					squares[i] = new BridgeEndCell(i, this, squareInfos.get(i));
					break;
					
				case 'P':
					squares[i] = new PhilipsDriverCell(i, this, squareInfos.get(i));
					break;
					
				case 'H':
					squares[i] = new HammerCell(i, this, squareInfos.get(i));
					break;
					
				case 'S':
					squares[i] = new SawCell(i, this, squareInfos.get(i));
					break;
					
				default:
					squares[i] = new Square(i, this, squareInfos.get(i));
			}
		}
	}
	
	public Square getSquare(int i) {
		return squares[i];
	}
	
	public Square getStartCell() {
		return squares[0];
	}
	
	public int getTotalSquareNum() {
		return totalSquareNum;
	}
	
	public boolean testDirection(Square mySquare, String udlr, boolean isBackConstraint) {
		// my square's index
		int mySquareIndex = mySquare.getIndex();
		
		for (int i = 0; i < udlr.length(); i++) {
			// last square don't care
			if (mySquareIndex != totalSquareNum - 1) {
				if (isBackConstraint) {
					if (udlr.charAt(i) != squares[mySquareIndex].getDirectionTo("next"))
						return false;
					
				} else {
					
					// no prev, no next -> not correctness in board
					if (udlr.charAt(i) != squares[mySquareIndex].getDirectionTo("prev")
							&& udlr.charAt(i) != squares[mySquareIndex].getDirectionTo("next")) {
						return false;
						
					}
					
				}
			}
			
			if (mySquareIndex == totalSquareNum - 1) {
				break;
			} else {
				// ok prev
				if (udlr.charAt(i) == squares[mySquareIndex].getDirectionTo("prev")) {
					if (squares[mySquareIndex].getIsCross() && udlr.charAt(i) == 'L') {
						mySquareIndex = mySquare.getCrossDestinationBackward();
						
						squares[mySquareIndex].setIsCross(false);
					} else mySquareIndex--;
				// ok next
				} else if (udlr.charAt(i) == squares[mySquareIndex].getDirectionTo("next")) {
					if (squares[mySquareIndex].getIsCross() && udlr.charAt(i) == 'R') {
						mySquareIndex = mySquare.getCrossDestinationForward();
						
						squares[mySquareIndex].setIsCross(false);
					} else mySquareIndex++;
				}	
			}
		}
		
		return true;
	}
}

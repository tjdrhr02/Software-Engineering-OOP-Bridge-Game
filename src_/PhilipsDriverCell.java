
public class PhilipsDriverCell extends Square {

	public PhilipsDriverCell(int i, Board board, String info) {
		index = i;
		
		myBoard = board;
		
		infoAry = info.split(" ");
	}
}

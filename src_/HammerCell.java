
public class HammerCell extends Square {

	public HammerCell(int i, Board board, String info) {
		index = i;
		
		myBoard = board;
		
		infoAry = info.split(" ");
	}
}

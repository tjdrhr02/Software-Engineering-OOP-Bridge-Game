import java.util.ArrayList;

public class Player {
	private Piece piece;
	private int score = 0;
	private ArrayList<BridgeCard> myBridgeCards = new ArrayList<BridgeCard>();
	private ArrayList<Card> myScoreCards = new ArrayList<Card>();
	private int numHammerCard, numSawCard, numPhilipsDriverCard = 0;
	
	public Player(Board board) {
		piece = new Piece(board, this);
	}
	
	public void movePiece(String udlr) {
		piece.move(udlr);
		
		if (piece.mySquare instanceof HammerCell) {
			myScoreCards.add(new HammerCard());
			numHammerCard++;
		}
		else if (piece.mySquare instanceof SawCell) {
			myScoreCards.add(new SawCard());
			numSawCard++;
		}
		else if (piece.mySquare instanceof PhilipsDriverCell) {
			myScoreCards.add(new PhilipsDriverCard());
			numPhilipsDriverCard++;
		}
	}
	
	public Square getMySquare() {
		return piece.mySquare;
	}

	public boolean isFinished() {
		if (piece.getIsFinished()) {
			return true;
		} else return false;
		
	}
	
	public void setScore(int s) {
		score = s;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getNumHC() {
		return numHammerCard;
	}
	
	public int getNumSC() {
		return numSawCard;
	}
	
	public int getNumPDC() {
		return numPhilipsDriverCard;
	}

	public int getNumBC() {
		return myBridgeCards.size();
	}
	
	public int calcScoreCard() {
		int n = 0;
		
		for(int i = 0; i < myScoreCards.size(); i++) {
			n += myScoreCards.get(i).getCardScore();
		}
		
		return n;
	}
	
	public void removeBC() {
		myBridgeCards.remove(0);
	}
	
	public void addBC() {
		myBridgeCards.add(new BridgeCard());
	}
	
	
}

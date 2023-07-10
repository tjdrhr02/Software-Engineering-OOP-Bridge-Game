
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Model {
	private int totalPlayerNum = 0;
	private Board board;
	private Player[] players;
	private Die die = new Die();
	private List<String> squareInfos;
	
	private int turn = 0;
	private int faceValue;
	private int winnerPlayerScore;
	
	ArrayList<Integer> finishScoreAL = new ArrayList<Integer>(Arrays.asList(7, 3, 1));
	
	public Model(List<String> squareInfos) {
		this.squareInfos = squareInfos;
		board = new Board(squareInfos);
	}
	
	public String getSquareInfo(int i){
		return squareInfos.get(i);
	}
	
	public boolean DoTestDirection(Square mySquare, String udlr, boolean isBackConstraint) {
		return board.testDirection(mySquare, udlr, isBackConstraint);
	}
	
	public int getFinishScoreAL() {
		int n = finishScoreAL.get(0);
		finishScoreAL.remove(0);
		
		return n;
	}
	
	public void setPlayers(int num) {
		setTotalPlayerNum(num);

		players = new Player[num];
		for (int i = 0; i < num; i++) {
			players[i] = new Player(board); 
		}
		
	}
	
	public Player[] getPlayers() {
		return players;
	}
	
	public Player getPlayer(int i) {
		return players[i];
	}
	
	public Player getNowPlayer() {
		return players[turn];
	}

	public int getTotalPlayerNum() {
		return totalPlayerNum;
	}

	public void setTotalPlayerNum(int totalPlayerNum) {
		this.totalPlayerNum = totalPlayerNum;
	}
	
	public void goNextTurn() {
		turn = (turn + 1) % totalPlayerNum;
		
		while (players[turn].isFinished()) {
			turn = (turn + 1) % totalPlayerNum;
		}
	}
	
	public int getTurn() {
		return turn;
	}
	
	public void rollDie() {
		faceValue = die.getFace();
	}
	
	public int getFaceValue() {
		return faceValue;
	}
	
	public int getMoveCount() {
		int bridgeHandicap = players[turn].getNumBC();
		
		int moveCount = faceValue - bridgeHandicap;
		
		if (moveCount < 0) {
			moveCount = 0;
		}
		
		return moveCount;
	}
	
	public boolean isGameSet() {
		int onBoardPlayer = 0;
		
		for (int i = 0; i < totalPlayerNum; i++) {
			if (!players[i].isFinished()) {
				onBoardPlayer++;
			}	
		}
		
		if (onBoardPlayer == 1) {			
			return true;
		}
		return false;
	}
	
	public boolean isSomeoneFinished() {
		int onBoardPlayer = 0;
		
		for (int i = 0; i < totalPlayerNum; i++) {
			if (players[i].isFinished())
				onBoardPlayer++;
		}
		
		if (onBoardPlayer > 0)			
			return true;
		
		return false;
	}
	
	public void calcWinner() {
		// score °è»ê
		for (int i = 0; i < players.length; i++) {
			int finalScore = players[i].getScore() + players[i].calcScoreCard();
			
			players[i].setScore(finalScore);
		}
		
		int[] scores = new int[players.length];
		for (int i = 0; i < players.length; i++) {
			scores[i] = players[i].getScore();
		}
		
		int tempIndex = 0;
		winnerPlayerScore = scores[0];
		
		for (int i = 0; i < players.length; i++) {
			if (scores[i] > scores[tempIndex]) {
				tempIndex = i;
				
				winnerPlayerScore = scores[i];
			}
		}
	}
	
	public int getWinnerPlayerScore() {
		return winnerPlayerScore;
	}
	
	public int getTotalSquareNum() {
		return board.getTotalSquareNum();
	}
	
	public Square getSquare(int i) {
		return board.getSquare(i);
	}
	
}

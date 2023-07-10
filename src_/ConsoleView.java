import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleView {
	private Scanner sc = new Scanner(System.in);
	private int totalPlayerNum;
	private boolean isRoll;
	private boolean isCross;
	private String udlr;
	
	public ConsoleView() {
	}
	
	public void playerNumPhase() {
		System.out.print("How many players? : ");
		totalPlayerNum = sc.nextInt();
	}
	
	public int getTotalPlayerNum() {
		return totalPlayerNum;
	}
	
	public void gamePhase(Model model) {
		System.out.printf("turn %d\n", model.getTurn() + 1);
		System.out.printf("now your piece is on index %d\n", model.getNowPlayer().getMySquare().getIndex());
		System.out.printf("your BC:%d, HC:%d, SC:%d, PDC:%d\n", model.getNowPlayer().getNumBC(), model.getNowPlayer().getNumHC(), model.getNowPlayer().getNumSC(), model.getNowPlayer().getNumPDC());
		System.out.print("roll die or stay? (r/s) : ");
		String strValue = sc.next();
		
		if (strValue.equals("r")) {
			isRoll = true;
		} else isRoll = false;
	}
	
	public boolean getIsRoll() {
		return isRoll;
	}
	
	public void crossPhase(Model model) {
		System.out.printf("die's faceValue : %d, your move count : %d\n", model.getFaceValue(), model.getMoveCount());
		System.out.print("cross or not? (c/n) : ");
		String strValue = sc.next();
		if (strValue.equals("c")) {
			isCross = true;
		} else isCross = false;
	}
	
	public boolean getIsCross() {
		return isCross;
	}
	
	public void udlrPhase(Model model) {
		System.out.printf("die's faceValue : %d, your move count : %d\n", model.getFaceValue(), model.getMoveCount());
		System.out.print("enter udlr : ");
		udlr = sc.next();
		
	}
	
	public String getUdlr() {
		return udlr;
	}
	
	public void endPhase(Model model) {
		ArrayList<Integer> winners = new ArrayList<Integer>();
		for(int i = 0; i < model.getTotalPlayerNum(); i++) {
			System.out.printf("player %d's score : %d\n", i+1, model.getPlayer(i).getScore());
			if(model.getPlayer(i).getScore() == model.getWinnerPlayerScore()) {
				winners.add(i);
			}
		}
		System.out.println("Congratulation !!");
		for(int i = 0; i < winners.size(); i++) {
			System.out.printf("winner is player %d !! \n", winners.get(i));
		}
	}
	
	
}


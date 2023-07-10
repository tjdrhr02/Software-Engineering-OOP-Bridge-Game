
public class ConsoleController {
	private Model model;
	private ConsoleView view;
	
	public ConsoleController(Model model, ConsoleView view) {
		this.model = model;
		this.view = view;
		
		initAction();
	}
	
	public void initAction() {
		// get/set
		view.playerNumPhase();
		
		model.setPlayers(view.getTotalPlayerNum());
		
		view.gamePhase(model);
		if (view.getIsRoll()) {
			rollAction();
		} else {
			stayAction();
		}
	}
	
	public void gameAction() {
		view.gamePhase(model);
		if (view.getIsRoll()) {
			rollAction();
		} else {
			stayAction();
		}
	}
	
	public void stayAction() {
		Player nowPlayer = model.getNowPlayer();
		
		if (nowPlayer.getNumBC() > 0)
			nowPlayer.removeBC();
		
		// next turn
		model.goNextTurn();
		
		view.gamePhase(model);
		if (view.getIsRoll()) {
			rollAction();
		} else {
			stayAction();
		}
	}
	
	public void rollAction() {
		Player nowPlayer = model.getNowPlayer();
		// roll die
		model.rollDie();
		
		if (nowPlayer.getMySquare() instanceof BridgeStartCell) {
			view.crossPhase(model);
			if (view.getIsCross()) {
				crossAction();
			} else {
				XAction();
			}
		} else if (nowPlayer.getMySquare() instanceof BridgeEndCell) {
			view.crossPhase(model);
			if (view.getIsCross()) {
				crossAction();
			} else {
				XAction();
			}
		} else {
			view.udlrPhase(model);
			udlrAction();
		}	
	}
	
	public void crossAction() {
		Player nowPlayer = model.getNowPlayer();
		nowPlayer.getMySquare().setIsCross(true);
		
		view.udlrPhase(model);
		udlrAction();
	}
	
	public void XAction() {
		view.udlrPhase(model);
		udlrAction();
	}
	
	public void udlrAction() {
		// get/set
		String udlr = view.getUdlr().toUpperCase();
		Player nowPlayer = model.getNowPlayer();
		
		if (udlr.length() != model.getMoveCount() ||
			!(model.DoTestDirection(nowPlayer.getMySquare(), udlr, model.isSomeoneFinished()))) {
			System.out.println("invalid move! enter again...");
			view.udlrPhase(model);
			udlrAction();
		}
		
		if (nowPlayer.getMySquare().getIsCross())
			nowPlayer.addBC();
					
		nowPlayer.movePiece(udlr);
		
		if (nowPlayer.isFinished()) 
			nowPlayer.setScore(model.getFinishScoreAL());
		
		
		if (model.isGameSet()) {
			model.calcWinner();
			
			view.endPhase(model);
			
		} else {
			model.goNextTurn();

			gameAction();
		}
	}
	
}


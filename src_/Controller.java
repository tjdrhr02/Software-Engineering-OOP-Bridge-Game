import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
	private Model model;
	private View view;
	
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		
		GameStartActionListener();
		OKActionListener();
		RollActionListener();
		StayActionListener();
		CrossActionListener();
		XActionListener();
		MoveActionListener();
	}
	
	public void GameStartActionListener() {
		view.ActionGameStart(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				view.modify_Init();
				view.update();
			}
		});
	}
	
	public void OKActionListener() {
		view.ActionOK(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// get/set
				int num = view.getTotalPlayerNum();
				if (num < 2 || num > 4) return;
				
				model.setPlayers(num);
				
				// panel change
				view.changeTo_GamePanel(model);
				// update
				view.update();
			}
		});
	}
	
	// 3
	public void RollActionListener() {
		view.ActionRoll(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Player nowPlayer = model.getNowPlayer();
				
				// roll die
				model.rollDie();
				
				if (nowPlayer.getMySquare() instanceof BridgeStartCell) {
					// panel change
					view.changeTo_CrossPanel(model);
					// update
					view.update();
				} else if (nowPlayer.getMySquare() instanceof BridgeEndCell) {
					// panel change
					view.changeTo_CrossPanel(model);
					// update
					view.update();
				} else {
					// panel change
					view.changeTo_UDLRPanel(model);
					// update
					view.update();
				}			
			}
		});
	}
	
	// 4
	public void StayActionListener() {
		view.ActionStay(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// get/set
				Player nowPlayer = model.getNowPlayer();
				
				if (nowPlayer.getNumBC() > 0)
					nowPlayer.removeBC();
				
				// next turn
				model.goNextTurn();
				// panel change
				view.changeTo_RollDiePanel(model);
				// update
				view.update();
			}
		});
	}
	
	// 5
	public void CrossActionListener() {
		view.ActionCross(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// get/set
				Player nowPlayer = model.getNowPlayer();
				
				nowPlayer.getMySquare().setIsCross(true);
				// panel change
				view.changeTo_UDLRPanel(model);
				// update
				view.update();
			}
		});
	}
	
	// 6
	public void XActionListener() {
		view.ActionX(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				// panel change
				view.changeTo_UDLRPanel(model);
				// update
				view.update();
			}
		});
	}
	
	// 7
	public void MoveActionListener() {
		view.ActionMove(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// get/set
				String udlr = view.getUDLR();
				Player nowPlayer = model.getNowPlayer();
				
				if (udlr.length() != model.getMoveCount() ||
					!(model.DoTestDirection(nowPlayer.getMySquare(), udlr, model.isSomeoneFinished()))) 
					return;
				
				if (nowPlayer.getMySquare().getIsCross())
					nowPlayer.addBC();
							
				nowPlayer.movePiece(udlr);
				
				if (nowPlayer.isFinished()) 
					nowPlayer.setScore(model.getFinishScoreAL());
				
				
				if (model.isGameSet()) {
					model.calcWinner();
					
					// panel change
					view.GameSetPanel(model);
					// update
					view.update();
					
				} else {
					model.goNextTurn();

					// panel change
					view.changeTo_RollDiePanel(model);
					// update
					view.update();
				}
			}
		});
	}
	
}

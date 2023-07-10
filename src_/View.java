

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class View extends JFrame {
	private static final long serialVersionUID = 1L;
	private final String S_TITLE = "Bridge Game";

	private JPanel jp_init;
	private JPanel jp_game;
	private JPanel jp_end;
	
	private JButton jbtn_gamePlay;

	private JLabel jl_QuestionPlayerNum = new JLabel("how many players?");
	private JTextField jtf_totalPlayerNum = new JTextField(10);
	private JButton jbtn_okTotalPlayerNum = new JButton("ok");
	
	private JButton jbtn_roll 			= new JButton("roll");
	private JButton jbtn_stay 			= new JButton("stay");
	private JButton jbtn_cross 			= new JButton("cross");
	private JButton jbtn_x 			= new JButton("x");
	
	private JTextField jtf_udlr 	= new JTextField(10);
	private JButton jbtn_move			= new JButton("move");

	private ImageIcon[] dieImages = new ImageIcon[6];
	
	private JLabel[] jl_bridgeNums;
	private JLabel[] jl_sawNums;
	private JLabel[] jl_hammerNums;
	private JLabel[] jl_driverNums;
	
	private JLabel jl_backConstraint = new JLabel("You cannot move BACK from now on");

	
	public View() {
		initComps();
		addComps();
		initWnd();
	}
	
	private void initComps(){
		jp_init = new JPanel();

		dieImages[0] = new ImageIcon("die1.jpg");
		dieImages[1] = new ImageIcon("die2.jpg");
		dieImages[2] = new ImageIcon("die3.jpg");
		dieImages[3] = new ImageIcon("die4.jpg");
		dieImages[4] = new ImageIcon("die5.jpg");
		dieImages[5] = new ImageIcon("die6.jpg");
		
		jbtn_gamePlay = new JButton("Game Play");
		
	}
	
	private void addComps(){
		jp_init.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		jp_init.add(jbtn_gamePlay);
		add(jp_init);
		
	}
	
	private void initWnd(){
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);
		//setSize(1500, 1000);
		setTitle(S_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void modify_Init() {
		jp_init.remove(jbtn_gamePlay);
		
		jp_init.add(jl_QuestionPlayerNum);
		jp_init.add(jtf_totalPlayerNum);
		jp_init.add(jbtn_okTotalPlayerNum);
	}
	
	public void changeTo_GamePanel(Model model) {
		remove(jp_init);
		
		jp_game	= new GamePanel(model);
		jp_game.setLayout(null);
		
		int y = 0;
		jl_bridgeNums = new JLabel[model.getTotalPlayerNum()];
		jl_sawNums = new JLabel[model.getTotalPlayerNum()];
		jl_hammerNums = new JLabel[model.getTotalPlayerNum()];
		jl_driverNums = new JLabel[model.getTotalPlayerNum()];
		
		
		for (int i = 0; i < model.getTotalPlayerNum(); i++) {
			
			jl_bridgeNums[i] = new JLabel(String.valueOf(model.getPlayer(i).getNumBC()));
			jl_bridgeNums[i].setBounds(this.getWidth() - 375, 150 + y, 50, 50);
			jp_game.add(jl_bridgeNums[i]);
			jl_sawNums[i] = new JLabel(String.valueOf(model.getPlayer(i).getNumSC()));
			jl_sawNums[i].setBounds(this.getWidth() - 275, 150 + y, 50, 50);
			jp_game.add(jl_sawNums[i]);
			jl_hammerNums[i] = new JLabel(String.valueOf(model.getPlayer(i).getNumHC()));
			jl_hammerNums[i].setBounds(this.getWidth() - 175, 150 + y, 50, 50);
			jp_game.add(jl_hammerNums[i]);
			jl_driverNums[i] = new JLabel(String.valueOf(model.getPlayer(i).getNumPDC()));
			jl_driverNums[i].setBounds(this.getWidth() - 75, 150 + y, 50, 50);
			jp_game.add(jl_driverNums[i]);
			
			y += 100;
		}
		
		jbtn_roll.setBounds(this.getWidth() - 500, this.getHeight() - 100, 200, 50);
		jbtn_stay.setBounds(this.getWidth() - 250, this.getHeight() - 100, 200, 50);
		
		jp_game.add(jbtn_roll);
		jp_game.add(jbtn_stay);
		
		add(jp_game);
		
	}
	
	public void changeTo_RollDiePanel(Model model) {	
		jp_game.removeAll();
		
		int y = 0;
		jl_bridgeNums = new JLabel[model.getTotalPlayerNum()];
		jl_sawNums = new JLabel[model.getTotalPlayerNum()];
		jl_hammerNums = new JLabel[model.getTotalPlayerNum()];
		jl_driverNums = new JLabel[model.getTotalPlayerNum()];
		
		
		for (int i = 0; i < model.getTotalPlayerNum(); i++) {
			
			jl_bridgeNums[i] = new JLabel(String.valueOf(model.getPlayer(i).getNumBC()));
			jl_bridgeNums[i].setBounds(this.getWidth() - 375, 150 + y, 50, 50);
			jp_game.add(jl_bridgeNums[i]);
			jl_sawNums[i] = new JLabel(String.valueOf(model.getPlayer(i).getNumSC()));
			jl_sawNums[i].setBounds(this.getWidth() - 275, 150 + y, 50, 50);
			jp_game.add(jl_sawNums[i]);
			jl_hammerNums[i] = new JLabel(String.valueOf(model.getPlayer(i).getNumHC()));
			jl_hammerNums[i].setBounds(this.getWidth() - 175, 150 + y, 50, 50);
			jp_game.add(jl_hammerNums[i]);
			jl_driverNums[i] = new JLabel(String.valueOf(model.getPlayer(i).getNumPDC()));
			jl_driverNums[i].setBounds(this.getWidth() - 75, 150 + y, 50, 50);
			jp_game.add(jl_driverNums[i]);
			
			y += 100;
		}
				
		// add
		jbtn_roll.setBounds(this.getWidth() - 500, this.getHeight() - 100, 200, 50);
		jbtn_stay.setBounds(this.getWidth() - 250, this.getHeight() - 100, 200, 50);
		jp_game.add(jbtn_roll);
		jp_game.add(jbtn_stay);
	}
	
	public void changeTo_CrossPanel(Model model) {
		jp_game.remove(jbtn_roll);
		jp_game.remove(jbtn_stay);
		
		JLabel jl_die = new JLabel(dieImages[model.getFaceValue() - 1]);
		
		jbtn_cross.setBounds(this.getWidth() - 500, this.getHeight() - 100, 200, 50);
		jbtn_x.setBounds(this.getWidth() - 250, this.getHeight() - 100, 200, 50);
		jl_die.setBounds(this.getWidth() - 500, this.getHeight() - 250, 100, 100);
		
		jp_game.add(jl_die);
		jp_game.add(jbtn_cross);
		jp_game.add(jbtn_x);
		
	}
	
	public void changeTo_UDLRPanel(Model model) {
		jp_game.removeAll();
		
		int y = 0;
		jl_bridgeNums = new JLabel[model.getTotalPlayerNum()];
		jl_sawNums = new JLabel[model.getTotalPlayerNum()];
		jl_hammerNums = new JLabel[model.getTotalPlayerNum()];
		jl_driverNums = new JLabel[model.getTotalPlayerNum()];
		
		for (int i = 0; i < model.getTotalPlayerNum(); i++) {
			jl_bridgeNums[i] = new JLabel(String.valueOf(model.getPlayer(i).getNumBC()));
			jl_bridgeNums[i].setBounds(this.getWidth() - 375, 150 + y, 50, 50);
			jp_game.add(jl_bridgeNums[i]);
			jl_sawNums[i] = new JLabel(String.valueOf(model.getPlayer(i).getNumSC()));
			jl_sawNums[i].setBounds(this.getWidth() - 275, 150 + y, 50, 50);
			jp_game.add(jl_sawNums[i]);
			jl_hammerNums[i] = new JLabel(String.valueOf(model.getPlayer(i).getNumHC()));
			jl_hammerNums[i].setBounds(this.getWidth() - 175, 150 + y, 50, 50);
			jp_game.add(jl_hammerNums[i]);
			jl_driverNums[i] = new JLabel(String.valueOf(model.getPlayer(i).getNumPDC()));
			jl_driverNums[i].setBounds(this.getWidth() - 75, 150 + y, 50, 50);
			jp_game.add(jl_driverNums[i]);
			
			y += 100;
		}
		
		JLabel jl_moveCount = new JLabel(String.valueOf(model.getMoveCount()));
		jl_moveCount.setBounds(this.getWidth() - 250, this.getHeight() - 250, 50, 50);
		jp_game.add(jl_moveCount);
		
		if (model.isSomeoneFinished()) {
			jl_backConstraint.setBounds(this.getWidth() - 250, this.getHeight() - 200, 300, 50);
			jp_game.add(jl_backConstraint);
		}
		
		JLabel jl_die = new JLabel(dieImages[model.getFaceValue() - 1]);
		// add
		jtf_udlr.setBounds(this.getWidth() - 500, this.getHeight() - 100, 200, 50);
		jbtn_move.setBounds(this.getWidth() - 250, this.getHeight() - 100, 200, 50);
		jl_die.setBounds(this.getWidth() - 500, this.getHeight() - 250, 100, 100);
		
		jp_game.add(jl_die);
		jp_game.add(jtf_udlr);
		jp_game.add(jbtn_move);
		
	}
	
	public void GameSetPanel(Model model) {
		remove(jp_game);
		
		jp_end = new EndPanel(model);
		jp_end.setLayout(null);
		
		JLabel jl_end = new JLabel("The End");
		
		// score
		JLabel[] jl_scores = new JLabel[model.getTotalPlayerNum()];		
		int y = 0;
		for (int i = 0; i < model.getTotalPlayerNum(); i++) {
			
			jl_scores[i] = new JLabel(String.valueOf(model.getPlayer(i).getScore()));
			jl_scores[i].setBounds(this.getWidth() / 2 + 25, 500 + y, 50, 50);
			jp_end.add(jl_scores[i]);
			
			y += 100;
		}
		
		// add
		jl_end.setBounds(this.getWidth() / 2, 200, 200, 50);
		jp_end.add(jl_end);
		
		add(jp_end);
		
	}
	
	// action listener
	public void ActionGameStart(ActionListener listener) {
		jbtn_gamePlay.addActionListener(listener);
	}
	
	public void ActionOK(ActionListener listener) {
		jbtn_okTotalPlayerNum.addActionListener(listener);
	}
	
	public void ActionRoll(ActionListener listener) {
		jbtn_roll.addActionListener(listener);
	}
	
	public void ActionStay(ActionListener listener) {
		jbtn_stay.addActionListener(listener);
	}
	
	public void ActionCross(ActionListener listener) {
		jbtn_cross.addActionListener(listener);
	}
	
	public void ActionX(ActionListener listener) {
		jbtn_x.addActionListener(listener);
	}
	
	public void ActionMove(ActionListener listener) {
		jbtn_move.addActionListener(listener);
	}
	
	
	public void update() {
		revalidate();
		repaint();
	}
	
	public int getTotalPlayerNum() {
		return Integer.parseInt(jtf_totalPlayerNum.getText());
	}
	
	public String getUDLR() {
		return jtf_udlr.getText().toUpperCase();
	}


}
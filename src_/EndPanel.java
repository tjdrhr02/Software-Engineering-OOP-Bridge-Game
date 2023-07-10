import java.awt.Graphics;
import javax.swing.*;

public class EndPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private Model model;
	private ImageIcon[] pieceImages;
	private ImageIcon crownImage = new ImageIcon("crown.jpg");
	
	public EndPanel(Model model) {
		this.model = model;
		
		pieceImages = new ImageIcon[4];
		
		pieceImages[0] = new ImageIcon("1.jpg");
		pieceImages[1] = new ImageIcon("2.jpg");
		pieceImages[2] = new ImageIcon("3.jpg");
		pieceImages[3] = new ImageIcon("4.jpg");
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		int x = this.getWidth() / 2 - 75;
		int y = 500;
		
		for (int i = 0; i < model.getTotalPlayerNum(); i++) {
			g.drawImage(pieceImages[i].getImage(), 
					x, y, 
					50, 50,
					this);
			
			y += 100;
		}
		
		// crown
		x -= 100;
		y = 500;
		
		for (int i = 0; i < model.getTotalPlayerNum(); i++) {
			Player[] players = model.getPlayers();
			
			if (players[i].getScore() == model.getWinnerPlayerScore()) {
				g.drawImage(crownImage.getImage(), 
						x, y + (i * 100), 
						50, 50,
						this);
			}
		}
	}
}


import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;

public class GamePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private ImageIcon[] mapImages;
	private ImageIcon bridgeImage = new ImageIcon("bridge.jpg");
	private ImageIcon[] pieceImages;
	private ImageIcon[] cardImages;
	private Model model;
	
	public GamePanel(Model model) {
		this.model = model;
		
		mapImages = new ImageIcon[model.getTotalSquareNum()];
		
		mapImages[0] = new ImageIcon("start.png");
		mapImages[model.getTotalSquareNum() - 1] = new ImageIcon("end.png");
		
		for (int i = 1; i < model.getTotalSquareNum() - 1; i++) {
			switch (model.getSquareInfo(i).charAt(0)) {
			case 'B':
				mapImages[i] = new ImageIcon("B.png");
				break;
				
			case 'b':
				mapImages[i] = new ImageIcon("square.jpg");
				break;
				
			case 'P':
				mapImages[i] = new ImageIcon("driver.png");
				break;
				
			case 'H':
				mapImages[i] = new ImageIcon("hammer.png");
				break;
				
			case 'S':
				mapImages[i] = new ImageIcon("saw.jpg");
				break;
				
			default:
				mapImages[i] = new ImageIcon("square.jpg");
			}
		}
		

		pieceImages = new ImageIcon[4];
		
		pieceImages[0] = new ImageIcon("1.jpg");
		pieceImages[1] = new ImageIcon("2.jpg");
		pieceImages[2] = new ImageIcon("3.jpg");
		pieceImages[3] = new ImageIcon("4.jpg");
		
		
		cardImages = new ImageIcon[4];
		cardImages[0] = new ImageIcon("bridge.jpg");
		cardImages[1] = new ImageIcon("saw.jpg");
		cardImages[2] = new ImageIcon("hammer.png");
		cardImages[3] = new ImageIcon("driver.png");
		
	}
	
	
	public void paint(Graphics g) {
		super.paint(g);
		
		Player[] players = model.getPlayers();
		
		// show map
		int x = 0;
		int y = 200;
		
		g.drawImage(mapImages[0].getImage(), 
				x, y, 
				50, 50,
				this);
		
		x += 50;
		
		g.drawImage(mapImages[1].getImage(), 
				x, y, 
				50, 50,
				this);
				
		for (int i = 1; i < model.getTotalSquareNum() - 1; i++) {
			
			if (model.getSquareInfo(i).charAt(0) == 'B') {
				g.drawImage(bridgeImage.getImage(), 
						x + 50, y, 
						50, 50,
						this);
			}
			
			switch (model.getSquare(i).getMapInfo(2)) {
			case "R":
				x += 50;
				break;
				
			case "U":
				y -= 50;
				break;
				
			case "D":
				y += 50;
				break;
			}
			
			g.drawImage(mapImages[i + 1].getImage(), 
					x, y, 
					50, 50,
					this);
		}
		
		// show pieces
		for (int i = 0; i < model.getTotalPlayerNum(); i++) {
			switch (i) {
			case 0:
				x = 0;
				y = 200;
				break;
				
			case 1:
				x = 25;
				y = 200;
				
				break;
				
			case 2:
				x = 0;
				y = 225;
				
				break;
				
			case 3:
				x = 25;
				y = 225;
				
				break;
			}
			
			for (int j = 0; j < players[i].getMySquare().getIndex(); j++) {
				
				if (j == 0) {
					x += 50;
				} else {
					switch (model.getSquare(j).getMapInfo(2)) {
					case "R":
						x += 50;
						break;
						
					case "U":
						y -= 50;
						break;
						
					case "D":
						y += 50;
						break;
					}
				}
				
			}
			
			g.drawImage(pieceImages[i].getImage(), 
					x, y, 
					25, 25,
					this);
		}
		
		// show status
		x = this.getWidth() - 500;
		y = 150;
		
		for (int i = 0; i < model.getTotalPlayerNum(); i++) {
			g.drawImage(pieceImages[i].getImage(), 
					x, y, 
					50, 50,
					this);
			
			y += 100;
		}
		
		// turn
		g.setColor(Color.GREEN);
		g.fillOval( 
				this.getWidth() - 550, 170 + (model.getTurn() * 100), 
				10, 10
				);
		
		// finished players
		x = this.getWidth() - 550;
		y = 170;
		
		ImageIcon finishedImage = new ImageIcon("finished.jpg");
		for (int i = 0; i < model.getTotalPlayerNum(); i++) {
			if (players[i].isFinished()) {
				g.drawImage(finishedImage.getImage(), 
						x, y, 
						30, 30,
						this);
			}
			
			y += 100;
		}
		
		// cards
		x = this.getWidth() - 500 + 100;
		y = 50;
		
		for (int i = 0; i < 4; i++) {
			g.drawImage(cardImages[i].getImage(), 
					x, y, 
					50, 50,
					this);
			
			x += 100;
		}
	}
}

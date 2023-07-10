import java.util.Random;

public class Die {
	public int getFace() {
		Random random = new Random();
		int faceValue = 1 + random.nextInt(6);
				
		return faceValue;
	}
}

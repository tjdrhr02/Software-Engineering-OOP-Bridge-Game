import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		List<String> squareInfos;
		
		squareInfos = Files.readAllLines(Paths.get("default.map"));
		
		
		System.out.print("Do you want to play in GUI? (Y/N) : ");
		String strValue = sc.next();
		if (strValue.equals("y") || strValue.equals("Y")) {
			View view = new View();
			Model model = new Model(squareInfos);
			new Controller(model, view);
		} else {
			ConsoleView view = new ConsoleView();
			Model model = new Model(squareInfos);
			new ConsoleController(model, view);
			
		}
		
	}
	
}

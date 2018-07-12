import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Classified {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		try {

			Scanner read = new Scanner(new File("Data.txt"));
			boolean affermative = true;
			int count = 3;
			while (affermative || count == 0) {
				System.out.println("Enter Command : ");
//				String userInput = getUserInput(input);
//				if (userInput.equals("exit")) {
//					affermative = false;
//				} else {
//					if (userInput.equalsIgnoreCase("add")
//							|| userInput.equalsIgnoreCase("eliminate")
//							|| userInput.equalsIgnoreCase("read")
//							|| userInput.equalsIgnoreCase("edit")) {
//
//					} else {
//						System.out.println("Invalid Command");
//						count--;
//					}
//				}
			}
			System.out.println("You have successfully exited : Please close the window");
		} catch (FileNotFoundException e) {
			System.out.println("The Requested file cannot be found");
		}
	}

	public static String getUserInput(Scanner input) {
		String userInput = input.nextLine();
		return userInput.toLowerCase();
	}
}

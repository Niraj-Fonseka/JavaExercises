import java.util.Scanner;


public class UserInput {
	
	private Scanner input = new Scanner(System.in);
	private String userInput;
	
	public  void setUserInput(){
		System.out.println("dbg getting input ");
		userInput = input.nextLine();
	}
	
	public  String getUserInput(){
		return userInput;
	}
	
}

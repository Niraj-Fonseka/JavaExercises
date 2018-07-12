import java.util.Scanner;

public class Terminal {
	 private String userInput;
	 private Integer userInputAsInteger = 3;
	 Scanner input = new Scanner(System.in);
	
	 
//------------------------------------------IO-----------------------
	public  void setUserInput(){
		this.userInput = input.nextLine();
	}
	
	
	
	public String getUserInput(){
		return userInput.toUpperCase();
	}
	
	public void setUserInputAsInteger(){
		this.userInputAsInteger = input.nextInt();
	}
	
	public Integer getUserInputAsInteger(){
		return userInputAsInteger;
	}
	
//-------------------------------------------End IO---------------------

	
	
}

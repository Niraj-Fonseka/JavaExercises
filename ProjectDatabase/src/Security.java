
public class Security {
	String password;
	String username;
	String newUser;
	UserInput input = new UserInput();
	
	public void runSecurityCheck(){
		getUserName();
		getPassWord();
		showData();
	}
	
	public void checkingForaAccount(){
		
	}
	public void getUserName(){
		System.out.println("Please Enter your username : ");
		input.setUserInput();
		this.username = input.getUserInput();
	}

	public void getPassWord(){
		System.out.println("Please Enter your password : ");
		input.setUserInput();
		this.password = input.getUserInput();
	}
	
	public void showData(){
		System.out.printf("This is the password %s , This is the username %s ", password,username);
	}
}

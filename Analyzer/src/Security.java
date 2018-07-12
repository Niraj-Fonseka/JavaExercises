import java.util.Scanner;

public class Security {
	private String password;
	private String passwordInFile;
	private Integer attempts;
	Scanner input = new Scanner(System.in);
	private boolean userAttemptsStatus;

	public Integer getUserAttempts() {
		return attempts;
	}

	public void setUserAttemptStatus(int attempts) {
		System.out.println();
		System.out.println("Attempts in the setUserAttemptStatus method " + attempts );
		System.out.println("if attempts = 3 return FALSE , if attempts < 3 return TRUE");
		if(attempts == 3){
			this.userAttemptsStatus = false;
		}else{
			this.userAttemptsStatus = true;
		}
	}
	
	public boolean getUserAttemptStatus(){
		return userAttemptsStatus;
	}

	public void setUserAttempts(Integer attempts) {
		this.attempts = attempts;
	}

	public String getUserPasswordFromFile() {
		return passwordInFile = "nsf123";
	}

	public void setUserPassword() {
		this.password = input.nextLine();
	}

	public String getUserPassword() {
		return password;
	}

}

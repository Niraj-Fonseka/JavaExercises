import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;


public class MainFrame {

	public static final String[] commands = { "SHOWALL", "DELETEALL","DELETERECORD", "FIND", "ADD", "H", "EXIT" };
	static boolean run = true;
	static Terminal term = new Terminal();
	static Security secq = new Security();
	boolean attemptsCheck = true;

	public static void main(String[] args) throws Exception {
	
				mainProgram();
		
	}
	
//	public static boolean mainPasswordMethod(int attempts){
//		boolean result = false;
//		System.out.println(" result in main paswwrod method : " + result);
//		System.out.println("Quit when true");
//		while(!result ){
//			result = access();
//			if(!result){
//				System.out.println("Please try again");
//				attempts = attempts+1;
//				secq.setUserAttempts(attempts);
//			}
//			if(secq.getUserAttempts() == 3){
//				System.out.println("You have run out of attempts");
//				secq.setUserAttemptStatus(3);
//			}
//			System.out.println("User Attempt count " + secq.getUserAttempts());
//			System.out.println("User Attempt Status " + secq.getUserAttemptStatus());
//
//		}
//		System.out.println("Quitting mainPassword Method");
//		return result;
//	}

//	public static boolean access () { 
//		promptTheUserForPassword();
//		boolean access = false;
//		System.out.println("DEBU : This is the password in file " + secq.getUserPasswordFromFile());
//		if(secq.getUserPassword().equals(secq.getUserPasswordFromFile())){
//			System.out.println("Your password was correct ! ");
//			System.out.println();
//			System.out.println("ACCESS GRANTED !");
//			access = true;
//			System.out.println("Setting access variable = " + access);
//
//		}else{
//			System.out.println("Your password was incorrect ! "); 
//		}
//		System.out.println("returning in access method " + access);
//		return access;
//	}

//	private static void promptTheUserForPassword() {
//		System.out.println("Please enter your password ! ");
//		secq.setUserPassword();
//	}
//	
	public static void welcomeScreen() throws InterruptedException{
		System.out.println("MAINFRAME : ");
		System.out.print("ON \n");
		System.out.println("STARTING....");
		System.out.println("SHOWING COMMANDS ... \n");
		help();
	}

	public static void mainProgram() throws Exception {
		Terminal term = new Terminal();
		welcomeScreen();
	
		while (MainFrame.run) {
			System.out.println("Enter your command :");
			term.setUserInput();
			boolean found = false;
			for (int i = 0; i < commands.length; i++) {
				if (term.getUserInput().equals(commands[i])) {
					commandWordDetected(commands[i]);
					found = true;
				}
			}
			if (!found) {
				System.out.println("Invalid Command typle H for help");
			}
		}
	}

	public static void commandWordDetected(String command) throws Exception {
		if (command.equals("SHOWALL")) {
			printData();
		}
		if (command.equals("EXIT")) {
			System.out.println(" NOTE : Need to run the encryption program ");
			System.out.println(" Encryption successful ");
			MainFrame.run = false;
		}
		if (command.equals("H")) {
			help();
		}
		if(command.equals("FIND")){
			find();
		}
		if(command.equals("ADD")){
			addRecord();
		}
		if(command.equals("DELETERECORD")){
			deleteRecord();
		}
	}

	
//----------------------------------------------------------DELETERECORD---------------------------------------//
	public static void deleteRecord(){
		System.out.println("Enter a keyword to find a record to delete");
		HashMap<Integer,String>map = new <Integer,String>HashMap();
		term.setUserInput();
		System.out.println();
		searchForDeletion(term.getUserInput(),map);
		printResults(map);
		System.out.println("Enter the index number of the record that you want to delete ");
		term.setUserInputAsInteger();
		boolean isValidIndex = isValidIndex(term.getUserInputAsInteger(),map);
		if(isValidIndex){
			deleteRecordFromFile(term.getUserInputAsInteger(),map);
		}else{
			System.out.println("You entered an invalid index : Returning to main screen");
		}
		
	}
	
	public static boolean isValidIndex(int index ,HashMap<Integer,String>map){
		boolean indexExist = false;
		for (Entry<Integer, String> entry : map.entrySet()) {
		    Integer key = entry.getKey();
		   if(key == index){
			   indexExist = true;
		   }
		}
		return indexExist;
	}

	public static void deleteRecordFromFile(int index,HashMap<Integer,String>map){
		deleteRecordFromFileHelper(index,map);
	}
	
	public static void deleteRecordFromFileHelper(int index , HashMap<Integer,String>map){
		Scanner sc = createScanner(false);
		int currentLine = 0;
		while(sc.hasNext()){
			currentLine = currentLine + 1;
			String line = sc.nextLine();
			if(currentLine == index){
				System.out.println();
				deleteLine(line,index+1);
				System.out.println("Deletion succuessfull BETA");
			}
		}
	}
	
	public static void deleteLine(String lineToDelete , int indexToDelete){
		
		File currentFile = new File("data.csv");
		File newFile = new File("data2.csv");
		try{
		BufferedWriter writer = new BufferedWriter(new FileWriter("data2.csv"));
		Scanner sc = createScanner(true);
		int currentIndex = 0;
		while(sc.hasNextLine()){
			String currentLine = sc.nextLine();
			currentIndex = currentIndex + 1;
			if(currentIndex != indexToDelete){
				System.out.println("DEBUG : Writing This " + currentLine + "to this file ");
				writer.write(currentLine + "\n");
			}else{
				System.out.println("DEBUG : SKIPPING THIS " + currentLine);
			}
		}
		writer.flush();
		writer.close();
		newFile.renameTo(currentFile);
		System.out.println(newFile.getName());
		}catch(Exception e){
			System.out.println("Something went wrong");
		}
	}
	public static void searchForDeletion(String keyword,HashMap<Integer,String>map){
		Scanner sc = createScanner(false); 
		int lineNumber = 0;
		while(sc.hasNextLine()){
			String line = sc.nextLine().toUpperCase();
			lineNumber = lineNumber +1;
			if(line.contains(keyword)){
				map.put(lineNumber, line);
			}
		}
		
	}
	
	public static void printResults(HashMap<Integer,String>map){
		for (Entry<Integer, String> entry : map.entrySet()) {
		    Integer key = entry.getKey();
		    String value = entry.getValue();
		    System.out.println("Index : " + key + " -- " + value);
		}
	
	}
//----------------------------------------------------------ENDDELETERECORD-----------------------------------//
	
	
//----------------------------------------------------------ADDRECORD------------------------------------------//
	public static void addRecord(){
		System.out.println("---------------Add a new Record------------");
		System.out.println();
		String newRecord = "";
		try {
			FileWriter  fw = new FileWriter("data.csv",true);
			BufferedWriter bw = new BufferedWriter(fw);
			System.out.println("Please wait...");
			Thread.sleep(1800);
			newRecord = newRecord();
			bw.write(newRecord+"\n");
			bw.close();
			System.out.println();
			System.out.println("New Record : " + newRecord);
			System.out.println("------------New Record Added--------");
		} catch (IOException | InterruptedException e) {
			System.out.println("Error : Something Went Wrong");
		}
		
	}
	
	public static String newRecord(){
		String newRecord = "";
		newRecord += getInputFromUser("Date")+",";
		newRecord += getInputFromUser("Name")+",";
		newRecord += getInputFromUser("PhoneNumber")+",";
		newRecord += getInputFromUser("Points")+",";
		newRecord += getInputFromUser("Description");
		return newRecord;
	}
	
	public static String editDescription(String text){
		String newText = "";
		if(text.length() <= 40){
			return text;
		}else{
			int count = text.length() / 40; 
			for( int i = 0 ; i < count ; i++){
				newText += text.substring((i*40)+1, 40 * (i+1)) + "\n";
			}
			newText += text.substring(count * 40);
		}
		
		return newText;
	}
	
	
	public static String getInputFromUser(String field){
		System.out.println("Enter your input for " + field);
		System.out.println("If you dont have an input type N/A");
		term.setUserInput();
		String userInput = term.getUserInput().trim();
		if(userInput.equals("")){
			userInput = "N/A";
		}
		if(field.equals("Description")){
			userInput = editDescription(userInput);
		}
		System.out.println();
		
		return userInput;
	}
	
//----------------------------------------------------------END ADDRECORD--------------------------------------//
	
	
//----------------------------------------------------------SEARCH--------------------------------------------//
	public static void find() throws Exception{
		System.out.println("What kind of search do you want to do ? Advanced or Normal");
		System.out.println("Type A for Advanced , N for Normal");
		MainFrame.term.setUserInput();
		if(MainFrame.term.getUserInput().equals("A")){
			System.out.println("launching advanced search method");
			advancedSearch();
			System.out.println();
		}else if(MainFrame.term.getUserInput().equals("N")) {
			System.out.println("Enter a keyword of what you are looking for : ");
			term.setUserInput();
			System.out.println("----Results----");
			normalSearch(term.getUserInput().trim());
			System.out.println();
		}else {
			System.out.println("Your input isnt vaild , directing back to the main screen");
		}
		
	}
	
	public static void advancedSearch() throws Exception{
		System.out.println("----Select a field----");
		System.out.println(" --Available fields-- ");
		getFields(term.getUserInput());
		System.out.println();
		System.out.println("Select a field -- : ");
		term.setUserInput();
		boolean fieldExist = doesTheFieldExist(term.getUserInput().toUpperCase().trim());
		System.out.println(fieldExist);
		if(!fieldExist){
			System.out.println("The Field name you entered doesnt exist - returning to the main screen");
		}

	}
	
	public static String getUserAdvancedSearchKeyword(){
		System.out.println("Please enter the keyword that you want to search look for :");
		term.setUserInput();
		return term.getUserInput();
	}
	
	public static boolean doesTheFieldExist(String userField){
		boolean exist = false;
		if(userField.equals("DATE")){
			userFieldDetected("DATE", 0);
			exist = true;
		}
		if(userField.equals("NAME")){
			userFieldDetected("NAME", 1);
			exist = true;
		}
		
		if(userField.equals("PHONENUMBER")){
			userFieldDetected("PHONENUMBER", 2);
			exist = true;

		}

		if(userField.equals("POINTS")){
			userFieldDetected("POINTS", 3);
			exist = true;

		}	
		
		if(userField.equals("DESCRIPTION")){
			userFieldDetected("DESCRIPTION", 4);
			exist = true;
		}
		return exist;
	}
	
	public static void userFieldDetected(String userField , int fieldsToSkip){
		Scanner sc = createScanner(false);
		String userKeyword = getUserAdvancedSearchKeyword();
		boolean atLeastOneRecordExist = false;
		int lineNumber = 0;
		while(sc.hasNextLine()){
			skipFields(sc,fieldsToSkip);
			String line = sc.nextLine().substring(1).toUpperCase();
			lineNumber = lineNumber +1;
			Scanner lineScan = new Scanner(line);
			lineScan.useDelimiter(",");
			String getField = lineScan.next().toUpperCase();
			boolean checkFieldData = false;
			checkFieldData = checkFieldinData(getField,userKeyword,line);
			if(checkFieldData){
				getRecordFromData(lineNumber);
				atLeastOneRecordExist = true;
			}
			
		}
		if(!atLeastOneRecordExist){
			System.out.println("There are no records that contains " + userKeyword);
		}
	}
	
	public static void getRecordFromData(int lineNumber){
		Scanner sc = createScanner(false);
		int currentLine = 0;
		while(sc.hasNextLine()){
			currentLine = currentLine + 1;
			String line = sc.nextLine();
			if(lineNumber == currentLine){
				System.out.println(line);
			}
		}
		
	}
	public static boolean checkFieldinData(String dataField , String userField , String dataRecord){
		boolean exist = false;
		if(dataField.equals(userField)){
			exist  = true;
		}
		return exist;
	}
	
	
	public static void skipFields(Scanner sc , int fieldsToSkip){
		for(int i = 0 ; i < fieldsToSkip ; i++){
			sc.next();
		}
	}

	public static void getFields (String userField) throws Exception {
		Scanner sc = new Scanner ( new File ("data.csv"));
		String fields = sc.nextLine().toUpperCase();
		System.out.println(fields);
	}
	
	public static void normalSearch(String keyword){
		System.out.println("Searching ......");
		Scanner sc = createScanner(false); 
		while(sc.hasNextLine()){
			String line = sc.nextLine().toUpperCase();
			if(line.contains(keyword)){
				System.out.println(line);
			}
		}
	}
//-------------------------------------------------------------------- END SEARCH ---------------------------//
	
	public static void help() {
		
		System.out.println("\n----------------Available Commands----------------\n");
		for (int i = 0; i < commands.length; i++) {
			System.out.println(commands[i]);
		}
		System.out.println();
		System.out.println("------------------------------------------------------");
	}

	public static void printData() {
		Scanner sc = createScanner(false);
		while (sc.hasNext()) {
			System.out.print(sc.next() + " ");
		}
		System.out.println();
	}

	
	//--------------------UTILITIES------------------------------//
	public static Scanner createScanner(boolean needFields) {
		try {
			Scanner sc = new Scanner(new File("data.csv"));
			if(!needFields){
			sc.nextLine();
			}
			sc.useDelimiter(",");
			return sc;
		} catch (Exception e) {
			System.out.println("Scanner cannot be created ! ");
			return null;
		}
	}

}

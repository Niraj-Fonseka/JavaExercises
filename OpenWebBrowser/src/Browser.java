import java.awt.Desktop;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;



public class Browser {
	public static void main(String[] args) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Scanner input = new Scanner(System.in);
		System.out.println("Set your time in HH:MM format ");
		String setTime = input.nextLine();
		setTime.trim();
		String url1 = "http://www.cnn.com/go/";
		boolean timeReached = true;

		while (timeReached) {
			Date date = new Date();

			String time = dateFormat.format(date);
			time = time.substring(11, 16);
			if (time.equals(setTime)) {
				timeReached = false;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();

			try {
				desktop.browse(new URI(url1));

			} catch (Exception e) {
				System.out.println("Oh Dangg ! it crashed. ");
			}

		} else {
			Runtime runtime = Runtime.getRuntime();
			try {
				runtime.exec("xdg-open " + url1);
			} catch (Exception e) {
				System.out.println("Oh dangg it crashed again");
			}
		}

	}
}

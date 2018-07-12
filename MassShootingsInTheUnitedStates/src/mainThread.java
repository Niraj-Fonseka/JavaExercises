import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class mainThread {
	public static void main(String[] args) {

		try {
			Scanner sc = new Scanner(new File("c.csv"));
			// Skipping the topics
			sc.nextLine();
			sc.nextLine();
			ArrayList<String> location = new ArrayList<String>(150);
			ArrayList<Integer> fatalities = new ArrayList<Integer>(150);
			ArrayList<String> legal = new ArrayList<String>(150);
			ArrayList<String> illness = new ArrayList<String>(150);
			getData(sc, location, fatalities, legal, illness);
			runThread(location, fatalities, legal, illness);
		} catch (FileNotFoundException e) {
			System.out.println("The File is not found");
		}

	}

	public static void runThread(final ArrayList<String> location,
			final ArrayList<Integer> fatalities, ArrayList<String> legal,
			ArrayList<String> illness) {
		// Creating the gui
		DrawingPanel draw = new DrawingPanel(800, 650);
		draw.setBackground(Color.BLACK);
		final Graphics g = draw.getGraphics();

		Thread locationsThread = new Thread(new Runnable() {

			@Override
			public void run() {
				printLocations(location, g);
			}

		});

		Thread fatalitiesThread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				printFatalities(fatalities, g);
			}

		});
		locationsThread.start();
		fatalitiesThread.start();

		try {
			locationsThread.join();
			fatalitiesThread.join();
		} catch (Exception e) {
			System.out.println("Error in thread");
		}
		makeGraph(fatalities, g);
		cleanArea(g);
	}

	// Clean area
	public static void cleanArea(Graphics g) {
		g.setColor(Color.GRAY);
		/*
		 * Boundary coordinates top left (x=10,y=10) top right (x = 400 , y = 10
		 * ) bottom left ( x = 10 , y = 650 ) bottom right ( x = 400 , y = 650)
		 */
		Random rand = new Random();
		for (int i = 1; i < 20; i++) {
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// making graph
	public static void makeGraph(ArrayList<Integer> list, Graphics g) {
		g.setColor(Color.WHITE);
		g.drawLine(50, 50, 50, 350);
		g.drawLine(50, 350, 350, 350);

	}

	// -----printing the fatalitiles ---//
	public static void printFatalities(ArrayList<Integer> Alist, Graphics g) {
		g.setColor(Color.WHITE);
		Random rand = new Random();
		int totalFatal = 0;
		String number = "";
		for (int i = 0; i < Alist.size(); i++) {
			totalFatal += Alist.get(i);
			number += Alist.get(i);
			g.drawString(number, 40 + (rand.nextInt(100) + 1),
					540 + (rand.nextInt(100) + 1));
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			number = "";
		}
		g.drawString("FATALITIES = " + totalFatal, 50, 520);

	}

	// -----printing the locations-------//
	public static void printLocations(ArrayList<String> Alist, Graphics g) {
		g.setColor(Color.WHITE);
		Random rand = new Random();
		for (int i = 0; i < Alist.size(); i++) {
			g.drawString(Alist.get(i), 430 + (rand.nextInt(75) + 1),
					0 + (i * 10));
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void getData(Scanner sc, ArrayList<String> location,
			ArrayList<Integer> fatalities, ArrayList<String> legal,
			ArrayList<String> illness) {

		while (sc.hasNextLine()) {
			String dataLine = sc.nextLine();
			Scanner textScan = new Scanner(dataLine);
			textScan.useDelimiter(",");
			location.add(textScan.next());
			textScan.next();
			fatalities.add(textScan.nextInt());
			textScan.next();
			textScan.next();
			legal.add(textScan.next());
			illness.add(textScan.next());
		}

	}

}

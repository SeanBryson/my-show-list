package com.cognixia.jump.myshowlist;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
	private DAOClass db = new DAOClass();
	private int sessionID;
	
	public void welcomeMenu() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome to myShowList!");
		while(true){
			try {
				System.out.println("Enter an option:");
				System.out.println("[1] Login");
				System.out.println("[2] Register");
				System.out.println("[3] Exit Program");
				
				int userInput = sc.nextInt();
				switch (userInput) {
				case 1:
					loginMenu(sc);
					break;
				case 2:
					System.out.println("Call registerMenu()"); //TODO
					break;
				case 3:
					System.out.println("Exiting program");
					System.exit(0);
					break;
				}
			} 
			catch (java.util.InputMismatchException e) {
				System.out.println("Invalid option");
				sc.nextLine();
	        }
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void loginMenu(Scanner sc) {
		sc.nextLine();	
		while(true){
			try {
				System.out.println("Enter your username, or hit [Enter] to go back:");
				String username=sc.nextLine();
				if(username.isEmpty()) {
					return;
				}
				System.out.println("Enter your password:");
				String password=sc.nextLine();
				
				User user = login(username, password);
				// If login was successful, log the user in
				if(user != null) {
					sessionID = user.getUser_id();
					userMenu(sc);
				}
				else {
					System.out.println("Invalid username/password");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private User login(String username, String password) {
		User user = db.getUserByUsername(username);
		// If login credentials are valid, return the user
		if(user != null && password.equals(user.getPassword())) {
			return user;
		}
		// Else, return null
		return null;
	}
	
	private void userMenu(Scanner sc) {
		// TODO print the user's progress trackers
		List<Tracker> trackers = db.getAllUserTrackers(sessionID);
		System.out.format("%33s%10s%10s%14s", "Show", "Episodes",
				"Seasons", "Status");
		for(Tracker t : trackers) {
			System.out.println();
			System.out.format("%33s%10d%10d%14s", db.getShowById(t.getShowID()), t.getCurrentEpisode(),
					t.getCurrentSeason(), db.getStatus(t.getStatusID()));
//			System.out.print(db.getShowById(t.getShowID()));
//			System.out.print("Episode: " + t.getCurrentEpisode());
//			System.out.print("Season: " + t.getCurrentSeason());
//			System.out.print("Status: " + db.getStatus(t.getStatusID()));
		}
		
		while(true){
			try {
				System.out.println();
				System.out.println("Enter an option:");
				System.out.println("[1] Update a tracker");
				System.out.println("[2] Add a tracker");
				System.out.println("[3] Delete a tracker");
				
				int userInput=sc.nextInt();
				switch (userInput) {
				case 1:
					//TODO
					break;
				case 2:
					//TODO
					break;
				case 3:
					//TODO
					break;
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}

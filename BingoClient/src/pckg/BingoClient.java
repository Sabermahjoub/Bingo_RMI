package pckg;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BingoClient {

	public BingoClient() {
	}
	
	static void all_my_scores(List<Integer> list) {
		if (list.isEmpty()) System.out.println("You haven't played BINGO yet!");
		else {
			System.out.println("\\n\\n&&&&&&& My Scores &&&&&&&");
			int round=0;
			for(Integer elt : list) {
				System.out.println("Game : " + round + " Score : "+elt);
			}
		}
		System.out.println("\n\n");
	}
	
	public static void main (String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		
		try {
			System.setProperty("java.security.policy", "./clientPolicy.policy");
			if (System.getSecurityManager() == null) {
	            System.setSecurityManager(new SecurityManager());
	        }
			IBingo stub = (IBingo) Naming.lookup("rmi://localhost:1099/bingo");
	        Scanner scanner = new Scanner(System.in);	
	        
	        System.out.print("Type your id !");
            int num = scanner.nextInt();
            int choice = -1;
            int round = 0;
            Random random = new Random();
            int guess = -1;
            int dice_value = -1;
            int score=0;
            List<Integer> allowedNumbers; // Reinitialized each game

            
            while (choice != 5) {
	            // Afficher le menu des options
            	System.out.println("******************************");
            	System.out.println("*            Menu            *");
            	System.out.println("******************************");
            	System.out.println("*  1. play BINGO !           *");
            	System.out.println("*  2. My best score          *");
            	System.out.println("*  3. The best score !       *");
            	System.out.println("*  4. My scores !            *");
            	System.out.println("*  5. Quit game              *");
            	System.out.println("******************************");
            	System.out.print(" Type your choice: ");

	            choice = scanner.nextInt();
	            
	            if(choice == 1) {
	                allowedNumbers = Collections.synchronizedList(new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)));
	            	System.out.println("Round : "+round);
	            	
	            	for(int i = 1; i<=10;i++) {
	            		System.out.println("-- Guess n°"+i+ " --");
	            		
	            		guess = scanner.nextInt();
	            		
	            		try {
	            			while(!allowedNumbers.contains(dice_value)) dice_value = stub.roll();
	            			allowedNumbers.remove(Integer.valueOf(dice_value));
		            		System.out.println("The roll's result is : "+dice_value);
		            		if(guess == dice_value) {
		            			System.out.println("GOOD JOB ! Correct guess !");
		            			score++;
		            		}
		            		else System.out.println("Wrong guess !");
	            		}
	            		catch(Exception e) {
	            			e.printStackTrace();
	            		}
	            		
	            		
	            	}
	            	
	            	try {	            		
	            		stub.add_round_result(num,score);
	            		System.out.println("You score for round " +round+" is : "+score+"/10");
	            	}
	            	catch(Exception e) {
	            		e.printStackTrace();
	            	}
	            	
	            	
	            }
	            if (choice == 2) {
	            	int my_best_score = stub.my_best_score(num);
	            	System.out.println("Your best score is : "+my_best_score);
	            }
	            if (choice == 3) {
	            	int best_score = stub.best_score();
	            	System.out.println("All players best score is : "+best_score);
	            }
	            if (choice == 4) {
	            	List<Integer> list = stub.all_my_scores(num);
	            	all_my_scores(list);
	            }
            
           
            }
            System.out.println("See you next time <3");

		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}

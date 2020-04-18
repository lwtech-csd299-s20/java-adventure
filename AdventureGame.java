import java.util.Scanner;

public class AdventureGame {
	
	private static Scanner scanner = new Scanner(System.in);
    
	private static int playerHP;
	private static String playerName;
	private static String playerWeapon;
	private static int choice;
	private static int monsterHP;
	private static int silverRing;
		
	public static void main(String[] args) {
		initializePlayer(); 	
		approachTown();
	}
	
	private static void initializePlayer() {
		playerHP = 10;
		monsterHP = 15;
		playerWeapon = "Knife";

		System.out.println("Your HP: "+ playerHP);
		System.out.println("Your Weapon: "+ playerWeapon);

		System.out.println("Please enter your name:");
		playerName = scanner.nextLine();
		System.out.println("Hello " + playerName + ", let's start the game!");	
	}	
	
	private static void approachTown() {
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("You are at the gate of the town.");
		System.out.println("A guard is standing in front of you.");
		System.out.println("");
		System.out.println("What do you want to do?");
		System.out.println("");
		System.out.println("1: Talk to the guard");
		System.out.println("2: Attack the guard");
		System.out.println("3: Leave");
		System.out.println("\n------------------------------------------------------------------\n");
		choice = scanner.nextInt();
		
		switch (choice) {
            case 1:
                if (silverRing == 1) {
                    winGame();
                } else {
                    System.out.println("Guard: Hello there, stranger. So your name is " + playerName + "? \nSorry but we cannot let stranger enter our town.");
                    scanner.nextLine();
                    approachTown();
                }
                break;
			
		    case 2: 
                playerHP = playerHP-1;
                System.out.println("Guard: Hey don't be stupid.\n\nThe guard hit you so hard and you gave up.\n(You receive 1 damage)\n");
                System.out.println("Your HP: " + playerHP);
                scanner.nextLine();
                approachTown();
                break;

		    case 3:
                approachCrossroads();
                break;

		    default:
                approachTown();
                break;
		}
	}
	
	private static void approachCrossroads() {
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("You are at a crossroad. If you go south, you will go back to the town.\n\n");
		System.out.println("1: Go north");
		System.out.println("2: Go east");
		System.out.println("3: Go south");
		System.out.println("4: Go west");
		System.out.println("\n------------------------------------------------------------------\n");
		choice = scanner.nextInt();
        
        switch (choice) {
            case 1:
                approachRiver();
                break;
            case 2:
                approachForest();
                break;
            case 3:
                approachTown();
                break;
            case 4:
                approachGoblin();
                break;
            default:
                approachCrossroads();
                break;
        }
	}
	
	private static void approachRiver() {
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("There is a river. You drink the water and rest at the riverside.");
		System.out.println("Your HP is recovered.");
		playerHP = playerHP + 1;
		System.out.println("Your HP: " + playerHP);
		System.out.println("\n\n1: Go back to the crossroad");
		System.out.println("\n------------------------------------------------------------------\n");
		choice = scanner.nextInt();
        
        switch (choice) {
            case 1:
                approachCrossroads();
                break;
            default:
                approachRiver();
                break;
		}
	}
	
	private static void approachForest() {
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("You walked into a forest and found a Long Sword!");
		playerWeapon = "Long Sword";
		System.out.println("Your Weapon: "+ playerWeapon);
		System.out.println("\n\n1: Go back to the crossroad");
		System.out.println("\n------------------------------------------------------------------\n");
		choice = scanner.nextInt();
		
		switch (choice) {
            case 1:
                approachCrossroads();
                break;
            default:
                approachForest();
                break;
		}
	}
	
	private static void approachGoblin() {
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("You encounter a goblin!\n");
		System.out.println("1: Fight");
		System.out.println("2: Run");
		System.out.println("\n------------------------------------------------------------------\n");
		
		choice = scanner.nextInt();
		
        switch (choice) {
            case 1:
                fight();
                break;
		    case 2:
                approachCrossroads();
                break;
            default:
                approachGoblin();
                break;
		}
	}
	
	private static void fight() {
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("Your HP: "+ playerHP);
		System.out.println("Monster HP: " + monsterHP);
		System.out.println("\n1: Attack");
		System.out.println("2: Run");
		System.out.println("\n------------------------------------------------------------------\n");
		choice = scanner.nextInt();
        
        switch (choice) {
            case 1:
                attack();
                break;
            case 2:
                approachCrossroads();
                break;
            default:
                fight();
                break;
		}
	}
	
	private static void attack() {
		int playerDamage = 0;
		if (playerWeapon.equals("Knife")) {
			playerDamage = new java.util.Random().nextInt(5); 
		} else if (playerWeapon.equals("Long Sword")) {
			playerDamage = new java.util.Random().nextInt(8); 
		}
		System.out.println("You attacked the monster and gave " + playerDamage + " damage!");
		
		monsterHP = monsterHP - playerDamage;
		System.out.println("Monster HP: " + monsterHP);
		
		if (monsterHP < 1) {
            winFight();
        } else if (monsterHP > 0) {
			int monsterDamage = new java.util.Random().nextInt(4);
			System.out.println("The monster attacked you and gave " + monsterDamage + " damage!");
			playerHP = playerHP - monsterDamage;
			System.out.println("Player HP: " + playerHP);
			if (playerHP < 1) {
                die();
            } else if (playerHP > 0) {
				fight();
			}
		}
	}
	
	private static void die() {
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("You are dead!!!");
		System.out.println("\n\nGAME OVER");
		System.out.println("\n------------------------------------------------------------------\n");
	}
	
	private static void winFight() {
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("You killed the monster!");
		System.out.println("The monster dropped a ring!");
		silverRing = 1;
		System.out.println("You obtaind a silver ring!\n\n");
		System.out.println("1: Go east");
		System.out.println("\n------------------------------------------------------------------\n");
		choice = scanner.nextInt();

        switch (choice) {
            case 1:
                approachCrossroads();
                break;
            default:
                winFight();
                break;
		}
	}
	
	private static void winGame() {
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("Guard: Oh you killed that goblin!?? Great!");
		System.out.println("Guard: It seems you are a trustworthy guy. Welcome to our town!");
		System.out.println("\n\n           THE END                    ");
		System.out.println("\n------------------------------------------------------------------\n");
    }
    
}
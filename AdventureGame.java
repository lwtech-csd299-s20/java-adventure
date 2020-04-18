import java.util.*;

public class AdventureGame {
	
	private static Scanner scanner = new Scanner(System.in);
    
	private static int goblinHitPoints;

	private static int playerHitPoints;
	private static String playerName;
	private static String playerWeapon;
	private static boolean playerHasSilverRing;

	public static void main(String[] args) {
		initializePlayer();
		approachTown();
	}
	
	private static void initializePlayer() {
		goblinHitPoints = 15;

        playerHitPoints = 10;
        playerWeapon = "Knife";
        playerHasSilverRing = false;

		System.out.println("Please enter your name:");
		playerName = scanner.nextLine();
		System.out.println("Hello " + playerName + ", let's start the game!");	

        System.out.println("Your HP: "+ playerHitPoints);
		System.out.println("Your Weapon: "+ playerWeapon);
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
		int choice = scanner.nextInt();
		
		switch (choice) {
            case 1:
                if (playerHasSilverRing) {
                    winGame();
                } else {
                    System.out.println("Guard: Hello there, stranger. So your name is " + playerName + "? \nSorry but we cannot let stranger enter our town.");
                    scanner.nextLine();
                    approachTown();
                }
                break;
			
		    case 2: 
                playerHitPoints--;
                System.out.println("Guard: Hey don't be stupid.\n\nThe guard hit you so hard and you gave up.\n(You receive 1 damage)\n");
                System.out.println("Your HP: " + playerHitPoints);
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
		int choice = scanner.nextInt();
        
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
		playerHitPoints++;
		System.out.println("Your HP: " + playerHitPoints);
		System.out.println("\n\n1: Go back to the crossroad");
		System.out.println("\n------------------------------------------------------------------\n");
		int choice = scanner.nextInt();
        
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
		int choice = scanner.nextInt();
		
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
		int choice = scanner.nextInt();
		
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
		System.out.println("Your HP: "+ playerHitPoints);
		System.out.println("Monster HP: " + goblinHitPoints);
		System.out.println("\n1: Attack");
		System.out.println("2: Run");
		System.out.println("\n------------------------------------------------------------------\n");
		int choice = scanner.nextInt();
        
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
        int maxDamage;
        int playerDamage;
        
        switch (playerWeapon) {
            case "Knife":
                maxDamage = 5;
                break;
            case "Long Sword":
                maxDamage = 8;
                break;
            default:
                maxDamage = 0;
                break;
        }
		playerDamage = new Random().nextInt(maxDamage); 

        System.out.println("You attacked the goblin and gave " + playerDamage + " damage!");
		goblinHitPoints -= playerDamage;
		System.out.println("Goblin HP: " + goblinHitPoints);
		
		if (goblinHitPoints < 1) {
            winFight();
        } else if (goblinHitPoints > 0) {
			int monsterDamage = new java.util.Random().nextInt(4);
			System.out.println("The monster attacked you and gave " + monsterDamage + " damage!");
			playerHitPoints = playerHitPoints - monsterDamage;
			System.out.println("Player HP: " + playerHitPoints);
			if (playerHitPoints < 1) {
                die();
            } else if (playerHitPoints > 0) {
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
		playerHasSilverRing = true;
		System.out.println("You obtaind a silver ring!\n\n");
		System.out.println("1: Go east");
		System.out.println("\n------------------------------------------------------------------\n");
		int choice = scanner.nextInt();

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
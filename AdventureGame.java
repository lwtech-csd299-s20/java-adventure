import java.util.*;

public class AdventureGame {
	
	private static Scanner scanner = new Scanner(System.in);
    
	private static int goblinHitPoints;

	private static int playerHitPoints;
	private static String playerName;
	private static String playerWeapon;
    private static boolean playerHasSilverRing;
    private static int playerLocation;

    private static final int IN_TOWN = 0;
    private static final int TOWN_GATE = 1;
    private static final int CROSSROADS = 2;
    private static final int FOREST = 3;
    private static final int RIVER = 4;
    private static final int OPEN_ROAD = 5;


	public static void main(String[] args) {
        initializePlayer();
        while (playerHitPoints > 0 && playerLocation != IN_TOWN) {
            takeTurn();
        }
        if (playerHitPoints > 0) {
            System.out.println("Guard: Oh you killed that goblin!?? Great!");
            System.out.println("Guard: It seems you are a trustworthy guy. Welcome to our town!");
            System.out.println();
            System.out.println("           THE END(?)");
            System.out.println();
        } else {
            die();
        }
	}
	
	private static void initializePlayer() {
        playerHitPoints = 10;
        playerWeapon = "Knife";
        playerHasSilverRing = false;
        playerLocation = CROSSROADS;

		System.out.print("Please enter your name: ");
		playerName = scanner.nextLine();
		System.out.println("Hello " + playerName + ", let's start the game!");	

    }
    
    private static void takeTurn() {

		System.out.println("------------------------------------------------------------------");
        System.out.print("Your Status>> Health: "+ playerHitPoints);
        System.out.print("  Weapon: "+ playerWeapon);
        System.out.print("  Inventory: [");
        if (playerHasSilverRing) System.out.print("Silver Ring");
        System.out.println("]");
		System.out.println("------------------------------------------------------------------");

        switch (playerLocation) {
            case TOWN_GATE:
                approachTown();
                break;

            case CROSSROADS:
                approachCrossroads();
                break;
                
            case FOREST:
                approachForest();
                break;
            
            case RIVER:
                approachRiver();
                break;
            
            case OPEN_ROAD:
                approachGoblin();
                break;

            default:
                break;
        }
    }
	
	private static void approachTown() {
		System.out.println("You are at the gate of the town.");
		System.out.println("A guard is standing in front of you.");
		System.out.println("1: Talk to the guard");
		System.out.println("2: Attack the guard");
		System.out.println("3: Leave");
		int choice = scanner.nextInt();
		
		switch (choice) {
            case 1:
                if (playerHasSilverRing) {
                    playerLocation = IN_TOWN;
                } else {
                    System.out.println("Guard: Hello there, stranger. So your name is " + playerName + "? Sorry but we cannot let stranger enter our town.");
                    scanner.nextLine();
                    playerLocation = TOWN_GATE;
                }
                break;
			
		    case 2: 
                playerHitPoints--;
                System.out.println("Guard: Hey don't be stupid.The guard hit you so hard and you gave up. (You receive 1 damage)");
                playerLocation = TOWN_GATE;
                break;

		    case 3:
                playerLocation = CROSSROADS;
                break;

		    default:
                playerLocation = TOWN_GATE;
                break;
		}
	}
	
	private static void approachCrossroads() {
		System.out.println("You are at a crossroad. If you go south, you will go back to the town.");
		System.out.println("1: Go north");
		System.out.println("2: Go east");
		System.out.println("3: Go south");
		System.out.println("4: Go west");
		int choice = scanner.nextInt();
        
        switch (choice) {
            case 1:
                playerLocation = RIVER;
                break;
            case 2:
                playerLocation = FOREST;
                break;
            case 3:
                playerLocation = TOWN_GATE;
                break;
            case 4:
                playerLocation = OPEN_ROAD;
                break;
            default:
                playerLocation = CROSSROADS;
                break;
        }
	}
	
	private static void approachRiver() {
        playerHitPoints++;
        
		System.out.println("You are at a river. You drink the water and rest at the riverside.");
		System.out.println("Your HP is recovered.");
		System.out.println("Your HP: " + playerHitPoints);
        System.out.println("1: Go back to the crossroads");
        System.out.println("2: Stay at the river");
		int choice = scanner.nextInt();
        
        switch (choice) {
            case 1:
                playerLocation = CROSSROADS;
                break;
            case 2:
                playerLocation = RIVER;
                break;
            default:
                playerLocation = RIVER;
                break;
		}
	}
	
	private static void approachForest() {
		playerWeapon = "Long Sword";

		System.out.println("You walked into a forest and found a Long Sword!");
		System.out.println("Your Weapon: "+ playerWeapon);
		System.out.println("1: Go back to the crossroad");
		int choice = scanner.nextInt();
		
		switch (choice) {
            case 1:
                playerLocation = CROSSROADS;
                break;
            default:
                playerLocation = FOREST;
                break;
		}
	}
	
	private static void approachGoblin() {
        goblinHitPoints = 15;

		System.out.println("You encounter a goblin!");
		System.out.println("1: Fight");
		System.out.println("2: Run");
		int choice = scanner.nextInt();
		
        switch (choice) {
            case 1:
                fight();
                break;
		    case 2:
                playerLocation = CROSSROADS;
                break;
            default:
                playerLocation = OPEN_ROAD;
                break;
		}
	}
	
	private static void fight() {
        while (playerLocation == OPEN_ROAD && playerHitPoints > 0) {
            System.out.print("Your HP: "+ playerHitPoints);
            System.out.println(" Goblin HP: " + goblinHitPoints);
            System.out.println("1: Attack");
            System.out.println("2: Run");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    attack();
                    if (goblinHitPoints < 1) {
                        playerHasSilverRing = true;
                        System.out.println("You killed the monster!");
                        System.out.println("The monster dropped a ring!");
                        System.out.println("You obtaind a silver ring!");
                        playerLocation = CROSSROADS;
                    }
                    break;
                case 2:
                    playerLocation = CROSSROADS;
                    break;
            }
        }
	}
	
	private static void attack() {
        int maxDamage;
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
		int playerDamage = new Random().nextInt(maxDamage); 

        System.out.println("You attacked the goblin and gave " + playerDamage + " damage!");
		goblinHitPoints -= playerDamage;
		System.out.println("Goblin HP: " + goblinHitPoints);
		
        if (goblinHitPoints > 0) {
			int monsterDamage = new java.util.Random().nextInt(4);
			System.out.println("The monster attacked you and gave " + monsterDamage + " damage!");
			playerHitPoints = playerHitPoints - monsterDamage;
			System.out.println("Player HP: " + playerHitPoints);
		}
	}
	
	private static void die() {
		System.out.println("You are dead!!!");
		System.out.println("GAME OVER!");
	}
		
}
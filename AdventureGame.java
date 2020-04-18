import java.util.Scanner;

public class AdventureGame {
	
	Scanner scanner = new Scanner(System.in);
    
	int playerHP;
	String playerName;
	String playerWeapon;
	int choice;
	int monsterHP;
	int silverRing;
		
	public static void main(String[] args) {
		AdventureGame dublin = new AdventureGame();
		dublin.playerSetUp(); 	
		dublin.townGate();
	}
	
	public void playerSetUp() {
		playerHP = 10;
		monsterHP = 15;
		playerWeapon = "Knife";

		System.out.println("Your HP: "+ playerHP);
		System.out.println("Your Weapon: "+ playerWeapon);

		System.out.println("Please enter your name:");
		playerName = scanner.nextLine();
		System.out.println("Hello " + playerName + ", let's start the game!");	
	}	
	
	public void townGate() {
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
                    ending();
                } else {
                    System.out.println("Guard: Hello there, stranger. So your name is " + playerName + "? \nSorry but we cannot let stranger enter our town.");
                    scanner.nextLine();
                    townGate();
                }
                break;
			
		    case 2: 
                playerHP = playerHP-1;
                System.out.println("Guard: Hey don't be stupid.\n\nThe guard hit you so hard and you gave up.\n(You receive 1 damage)\n");
                System.out.println("Your HP: " + playerHP);
                scanner.nextLine();
                townGate();
                break;

		    case 3:
                crossRoad();
                break;

		    default:
                townGate();
                break;
		}
	}
	
	public void crossRoad() {
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
                north();
                break;
            case 2:
                east();
                break;
            case 3:
                townGate();
                break;
            case 4:
                west();
                break;
            default:
                crossRoad();
                break;
        }
	}
	
	public void north() {
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
                crossRoad();
                break;
            default:
                north();
                break;
		}
	}
	
	public void east() {
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("You walked into a forest and found a Long Sword!");
		playerWeapon = "Long Sword";
		System.out.println("Your Weapon: "+ playerWeapon);
		System.out.println("\n\n1: Go back to the crossroad");
		System.out.println("\n------------------------------------------------------------------\n");
		choice = scanner.nextInt();
		
		switch (choice) {
            case 1:
                crossRoad();
                break;
            default:
                east();
                break;
		}
	}
	
	public void west() {
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
                crossRoad();
                break;
            default:
                west();
                break;
		}
	}
	
	public void fight() {
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
                crossRoad();
                break;
            default:
                fight();
                break;
		}
	}
	
	public void attack() {
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
            win();
        } else if (monsterHP > 0) {
			int monsterDamage = new java.util.Random().nextInt(4);
			System.out.println("The monster attacked you and gave " + monsterDamage + " damage!");
			playerHP = playerHP - monsterDamage;
			System.out.println("Player HP: " + playerHP);
			if (playerHP < 1) {
                dead();
            } else if (playerHP > 0) {
				fight();
			}
		}
	}
	
	public void dead() {
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("You are dead!!!");
		System.out.println("\n\nGAME OVER");
		System.out.println("\n------------------------------------------------------------------\n");
	}
	
	public void win() {
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
                crossRoad();
                break;
            default:
                win();
                break;
		}
	}
	
	public void ending() {
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("Guard: Oh you killed that goblin!?? Great!");
		System.out.println("Guard: It seems you are a trustworthy guy. Welcome to our town!");
		System.out.println("\n\n           THE END                    ");
		System.out.println("\n------------------------------------------------------------------\n");
    }
    
}
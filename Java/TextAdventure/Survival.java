import java.util.*;


public class Survival {


	public static void main(String[] args) {
		instantiateWorld();
		GameState.currentLocation = GameState.world[0][0];
		Scanner input = new Scanner(System.in);
		Processor pr = new Processor();
		String command = "";
		System.out.println("Please enter your name: ");
		command = input.nextLine();
		GameState.name = command;
		welcomeMessage();

		//GameState.printAllLocations();

		while (!command.equals("quit")) {
			command = input.nextLine();
			//processes the command and returns output
			String output = pr.parseCommand(command);
			System.out.println();
			System.out.println(output);
			System.out.println();
			if (GameState.won) {
				String choice = "";
				while(true) {
					System.out.print("You have won the game! I hope you've had as much fun as I have. \n Replay? (Yes/No): ");
					choice = input.nextLine();
					choice = choice.toLowerCase();
					if (choice.equals("no")) {
						command = "quit";
						break;
					}
					else if (choice.equals("yes")) {
						instantiateWorld();
						GameState.won = false;
						welcomeMessage();
						GameState.currentLocation = GameState.world[0][0];
						GameState.weapon = null;
						GameState.health = 100;
						GameState.outfit = null;
						GameState.damage = 10;
						GameState.isDead = false;
						GameState.pirateClothes = false;
						GameState.medievalArmor = false;
						GameState.inventory.clear();
						break;
					}
				}
			}
			if (GameState.lost) {
				String choice = "";
				while(true) {
					System.out.print("You have lost the game! I hope you've had as much fun as I have. \n Replay? (Yes/No): ");
					choice = input.nextLine();
					choice = choice.toLowerCase();
					if (choice.equals("no")) {
						command = "quit";
						break;
					}
					else if (choice.equals("yes")) {
						instantiateWorld();
						GameState.won = false;
						welcomeMessage();
						GameState.currentLocation = GameState.world[0][0];
						GameState.weapon = null;
						GameState.health = 100;
						GameState.outfit = null;
						GameState.damage = 10;
						GameState.isDead = false;
						GameState.pirateClothes = false;
						GameState.medievalArmor = false;
						GameState.inventory.clear();
						break;
					}
				}
			}
		}
	}

	public static void welcomeMessage() {
		System.out.println();
		System.out.println("Welcome to Survival " + GameState.name + "!");
		System.out.println();
		System.out.println("You wake up on the beach not remembering what has happened. The island appears to be pretty small, with a compound in the middle. Since it appears to be morning time, And you are better than Bear Grills, the sun tells you that you are in the north west part of the island. Type help for info on commands.");
		System.out.println();
		System.out.println(GameState.currentLocation.getDescription());
		System.out.println();

	}
	public static void instantiateWorld() {
		Descriptions desc = new Descriptions();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) { 
				GameState.world[i][j] = new Location(desc.getDesc(i,j),i,j);
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) { 
				GameState.world[i][j].setDirections();
			}
		}
	}
	public static void printDesc(Location l) {
		System.out.println(l.getDescription());

	}
}
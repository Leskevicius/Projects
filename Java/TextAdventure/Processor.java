import java.util.*;

public class Processor {
	String output = "";
	int countRememberPast = 0;
	int pastMessage = 0;

	public Processor() {
	}

	public String parseCommand(String command) {
		command = command.toLowerCase();
		String[] words = command.split(" ");
		//determine what to do
		Action action = determineAction(words);
		//update game
		updateGameState(action);
		//get output
		String output = action.getOutput();
		return output;
	}

	public Action determineAction(String[] words) {
		Action newAction = new Action();
		try{
			//Your Character remembers what happened
			if (countRememberPast > 2) {
				countRememberPast = 0;
				if (pastMessage >= 2) {
					countRememberPast = -999;
					pastMessage = 0;
				}
				System.out.println(GameState.past[pastMessage]);
				pastMessage++;
			}
			if (words[0].equals("help")) {
				newAction.setOutput(Descriptions.help);
			}
			if (words[0].equals("cheats")) {
				newAction.cheats(words);
			}
			if (words[0].equals("use")) {
				newAction.use(words);
			}
			if (words[0].equals("stats")) {
				newAction.printStatistics();
			}
			if (words[0].equals("unequip")) {
				newAction.unEquip(words);
			}

			if (words[0].equals("equip")) {
				newAction.equip(words);
			}

			if (words[0].equals("combine")) {
				newAction.combine(words);
			}

			if (words[0].equals("hit")) {
				hit(newAction,words);
			}

			if (words[0].equals("drop")) {
				drop(newAction,words);
			}

			if (words[0].equals("inventory")) {
				newAction.printInventory();
			}
			if (words[0].equals("examine")) {
				examine(newAction,words);
			}
			if (words[0].equals("talk") && words[1].equals("to")) {
				talkTo(newAction,words);
			}
			//quit
			if (words[0].equals("quit")) {
				newAction.setOutput("exiting...");
			}
			//movement
			if (words[0].equals("go")) {
				movement(newAction, words);
			}
			//pick up
			if (words[0].equals("get")) {
				pickUp(newAction, words);
			}
			//descriptions
			if (words[0].equals("location")) {
				newAction.printCoords();
			}
			if (words[0].equals("look")) {
				newAction.getDescription();
			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
			newAction.wrongInput();
		}
		return newAction;
	}
	public void drop(Action newAction, String[] words) {
		if (words[1].equals("water") && words[2].equals("bottle")) {
			newAction.drop("water bottle");
		}
		if (words[1].equals("poisoned") && words[2].equals("liqour")) {
			newAction.drop("poisoned liqour");
		}
		if (GameState.inventory.contains(words[1])) {
			newAction.drop(words[1]);
		}
	}

	public void hit(Action newAction, String[] words) {
		if (words[1].equals("pirate") && words[2].equals("kremlin") && GameState.currentLocation.locationContains("pirate kremlin")) {
			newAction.hit("pirate kremlin");
		}
		else if (GameState.currentLocation.locationContains(words[1])) {
			newAction.hit(words[1]);
		}
	}

	public void talkTo(Action newAction, String[] words) {
		if (GameState.currentLocation.locationContains(words[2])) {
			newAction.talkTo(words);
		}
	}

	public void examine(Action newAction, String[] words) {
		if (words[1].equals("lord") && words[2].equals("kremlin") && GameState.currentLocation.locationContains("lord kremlin")) {
			newAction.examine("lord kremlin");
		}
		else if (words[1].equals("poisoned") && words[2].equals("liqour")) {
			if (GameState.currentLocation.locationContains("poisoned liqour")) {
				newAction.examine("poisoned liqour");
			}
			else {
				newAction.examine("poisoned liqour");
			}
		}
		else if (words[1].equals("water") && words[2].equals("bottle")) {
				if (GameState.currentLocation.locationContains("water bottle")) {
				newAction.examine("water bottle");
			}
			else {
				newAction.examine("water bottle");
			}
		}
		else if (words[1].equals("apple") && words[2].equals("tree") && GameState.currentLocation.locationContains("apple tree")) {
			newAction.examine("apple tree");
		}
		else if (words[1].equals("blue") && words[2].equals("car") && GameState.currentLocation.locationContains("blue car")) {
			newAction.examine("blue car");
		}
		else if (words[1].equals("red") && words[2].equals("car") && GameState.currentLocation.locationContains("blue car")) {
			newAction.examine("red car");
		}
		else if (GameState.currentLocation.locationContains(words[1])) {
			newAction.examine(words);
		}
		else if (GameState.inventory.contains(words[1])) {
			newAction.examine(words);
		}
	}

	public void updateGameState(Action action) {
		GameState.currentLocation = action.getLocation();
	}

	public void pickUp(Action newAction, String[] words) {
		if (words[1].equals("pirate") && words[2].equals("outfit")) {
			if (GameState.currentLocation.locationContains("pirate outfit")) {
				newAction.addToInventory("pirate outfit");
			}
		}
		else if (words[1].equals("water") && words[2].equals("bottle")) {
			if (GameState.currentLocation.locationContains("water bottle")) {
				newAction.addToInventory("water bottle");
			}
		}
		else if (GameState.currentLocation.locationContains(words[1])) {
			newAction.addToInventory(words[1]);
		}
 		else {
			newAction.wrongInput();
		}

	}
	public void movement(Action newAction, String[] words) {
		if (words[1].equals("north")) {
			if (GameState.currentLocation.getX() == 1 && GameState.currentLocation.getY() == 2) {
				newAction.compoundWall();
			}
			else if (GameState.currentLocation.getY() == 1 && GameState.currentLocation.getX() == 1) {
				newAction.compoundWallInside();
			}
			else if (GameState.currentLocation.getY() == 1 && GameState.currentLocation.getX() == 2) {
				newAction.compoundWallInside();
			}
			else if (GameState.currentLocation.getX() == 2 && GameState.currentLocation.getY() == 3) {
				if (!GameState.pirateClothes) {
					newAction.noPirateClothes("north");
				}
				else {
					newAction.setNewLocation(GameState.getCurrentLocation().move("north"));
					countRememberPast++;
				}
			}
			else if (GameState.currentLocation.getX() == 2 && GameState.currentLocation.getY() == 2) {
				if (!GameState.pirateClothes) {
					newAction.noPirateClothes("north");
				}
				else {
					newAction.setNewLocation(GameState.getCurrentLocation().move("north"));
					countRememberPast++;
				}
			}
			else {
				newAction.setNewLocation(GameState.getCurrentLocation().move("north"));
				countRememberPast++;
			}
		}
		else if (words[1].equals("south")) {
			if (GameState.currentLocation.getY() == 0 && GameState.currentLocation.getX() == 1) {
				newAction.compoundWall();
			}
			else if (GameState.currentLocation.getY() == 0 && GameState.currentLocation.getX() == 2) {
				newAction.compoundWall();
			}
			else if (GameState.currentLocation.getX() == 3 && GameState.currentLocation.getY() == 0) {
				if (!GameState.pirateClothes) {
					newAction.noPirateClothes("south");
				}
				else {
					newAction.setNewLocation(GameState.getCurrentLocation().move("south"));
					countRememberPast++;
				}
			}
			else if (GameState.currentLocation.getX() == 3 && GameState.currentLocation.getY() == 1) {
				if (!GameState.pirateClothes) {
					newAction.noPirateClothes("south");
				}
				else {
					newAction.setNewLocation(GameState.getCurrentLocation().move("south"));
					countRememberPast++;
				}
			}
			else if (GameState.currentLocation.getX() == 3 && GameState.currentLocation.getY() == 2) {
				if (!GameState.pirateClothes) {
					newAction.noPirateClothes("south");
				}
				else {
					newAction.setNewLocation(GameState.getCurrentLocation().move("south"));
					countRememberPast++;
				}
			}
			else {
				newAction.setNewLocation(GameState.getCurrentLocation().move("south"));
				countRememberPast++;
			}
		}
		else if (words[1].equals("west")) {
			if (GameState.currentLocation.getX() == 3 && GameState.currentLocation.getY() == 1) {
				newAction.compoundWall();
			}
			else if (GameState.currentLocation.getX() == 3 && GameState.currentLocation.getY() == 2) {
				newAction.compoundWall();
			}
			else if (GameState.currentLocation.getX() == 2 && GameState.currentLocation.getY() == 3) {
				newAction.compoundWall();
			}
			else if (GameState.currentLocation.getY() == 1 && GameState.currentLocation.getX() == 1) {
				newAction.compoundWallInside();
			}
			else if (GameState.currentLocation.getX() == 3 && GameState.currentLocation.getY() == 3) {
				if (!GameState.pirateClothes) {
					newAction.noPirateClothes("west");
				}
				else {
					newAction.setNewLocation(GameState.getCurrentLocation().move("west"));
					countRememberPast++;
				}
			}
			else if (GameState.currentLocation.getX() == 1 && GameState.currentLocation.getY() == 2) {
				if (!GameState.pirateClothes) {
					newAction.noPirateClothes("west");
				}
				else {
					newAction.setNewLocation(GameState.getCurrentLocation().move("west"));
					countRememberPast++;
				}
			}
			else {
				newAction.setNewLocation(GameState.getCurrentLocation().move("west"));
				countRememberPast++;
			}
		}
		else if (words[1].equals("east")) {
			if (GameState.currentLocation.getX() == 0 && GameState.currentLocation.getY() == 1) {
				newAction.compoundWall();
			}
			else if (GameState.currentLocation.getX() == 1 && GameState.currentLocation.getY() == 2) {
				newAction.compoundWall();
			}
			else if (GameState.currentLocation.getX() == 1 && GameState.currentLocation.getY() == 3) {
				newAction.compoundWall();
			}
			else if (GameState.currentLocation.getX() == 2 && GameState.currentLocation.getY() == 1) {
				newAction.compoundWallInside();
			}
			else if (GameState.currentLocation.getX() == 2 && GameState.currentLocation.getY() == 2) {
				newAction.compoundWallInside();
			}
			else if (GameState.currentLocation.getX() == 1 && GameState.currentLocation.getY() == 3) {
				newAction.compoundWall();
			}
			else if (GameState.currentLocation.getX() == 2 && GameState.currentLocation.getY() == 0) {
				if (!GameState.pirateClothes) {
					newAction.noPirateClothes("east");
				}
				else {
					newAction.setNewLocation(GameState.getCurrentLocation().move("east"));
					countRememberPast++;
				}
			}
			else {
				newAction.setNewLocation(GameState.getCurrentLocation().move("east"));
				countRememberPast++;
			}
		}
	}
}

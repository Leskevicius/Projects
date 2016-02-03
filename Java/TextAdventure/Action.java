import java.util.*;

public class Action {
	
	//location stuff
	private Location newLocation;
	private Location currentLocation;
	
	//inventory
	private Inventory newInventory;
	
	//output string
	private String output;
	
	//boolean for deciding output
	private boolean changedLocation = false;
	private boolean printCoords = false;
	private boolean requestDescription = false;
	private boolean overrideOutput = false;
	private boolean updateInventory = false;
	private boolean printInventory = false;
	private boolean examined = false;
	private boolean talked = false;
	private boolean hit = false;
	private boolean dropped = false;
	private boolean equipped = false;
	private boolean combined = false;
	private boolean unequipped = false;	
	private boolean used = false;
	private boolean noPass = false;

	public Action() {
		newLocation = GameState.currentLocation;
	}

	public void use(String[] words) {
		if (GameState.inventory.contains(words[1])) {
			GameState.inventory.get(words[1]).use(words[1]);
			output = GameState.inventory.get(words[1]).getUsedMessage();
			GameState.inventory.remove(GameState.inventory.get(words[1]));
			used = true;
		}
		else if (words[1].equals("poisoned") && words[2].equals("liqour")) {
			GameState.inventory.get("poisoned liqour").use("poisoned liqour");
			output = GameState.inventory.get("poisoned liqour").getUsedMessage();
			GameState.inventory.remove(GameState.inventory.get("poisoned liqour"));
			used = true;
		}
		else if (words[1].equals("water") && words[2].equals("bottle")) {
			GameState.inventory.get("water bottle").use("water bottle");
			output = GameState.inventory.get("water bottle").getUsedMessage();
			GameState.inventory.remove(GameState.inventory.get("water bottle"));
			used = true;
		}
	}

	public void cheats(String[] words) {
		if (words[1].equals("iddqd")) {
			GameState.iddqd = true;
		}
	}

	public void printStatistics() {
		overrideOutput = true;
		String weapon = " ";
		String outfit = " ";
		if (GameState.weapon == null) {
			weapon = "none";
		}
		else {
			weapon = GameState.weapon.getName();
		}
		if (GameState.outfit == null) {
			outfit = "none";
		}
		else {
			outfit = GameState.outfit.getName();
		}

		output = "Health: " + GameState.health + "\nWeapon: " + weapon + "\nOutfit: " + outfit + "\nDamage: " + GameState.damage;
	}

	public void printInventory() {
		printInventory = true;
	}

	public void equip(String[] words) {
		if (GameState.inventory.contains(words[1])) {
			if (GameState.inventory.get(words[1]).isEquippable()) {
				if (GameState.inventory.get(words[1]).isWeapon()) {
					if (GameState.weapon == null) {
						GameState.weapon = GameState.inventory.get(words[1]);
						GameState.damage = GameState.weapon.getDamage();
						GameState.inventory.remove(new Item(words[1]));
						equipped = true;
						output = "You've equipped: " + GameState.weapon.getName() + "   Your current damage: " + GameState.weapon.getDamage();
					}
					else {
						overrideOutput = true;
						output = "You must first unequip your weapon";
					}
				}
				else if (GameState.inventory.get(words[1]).isOutfit()) {
					if (GameState.outfit == null) {
						GameState.outfit = GameState.inventory.get(words[1]);
						GameState.inventory.remove(new Item(words[1]));
						equipped = true;
						output = "You've equipped: " + GameState.outfit.getName();
						if (words[1].equals("armor")) {
							GameState.medievalArmor = true;
						}
					}	
					else {
						overrideOutput = true;
						output = "You must first unequip your outfit";
					}	
				}
			}
		}
		else if (words[1].equals("pirate") && words[2].equals("outfit")) {
			if (GameState.inventory.contains("pirate outfit")) {
				GameState.outfit = GameState.inventory.get("pirate outfit");
				GameState.pirateClothes = true;
				GameState.inventory.remove(new Item("pirate outfit"));
				equipped = true;
				output = "You've equipped: pirate outfit    You should be able to enter the compound while wearings this outfit.";
			}
		}
	}

	public void unEquip(String words[]) {
		if (GameState.weapon.getName().equals(words[1])) {
			GameState.damage = 10;
			GameState.inventory.add(new Item(words[1]));
			output = "You've unequipped: " + GameState.weapon.getName();
			GameState.weapon = null;
			unequipped = true;
		}
		else if (words[1].equals("pirate") && words[2].equals("outfit")) {
			if (GameState.outfit.getName().equals("pirate outfit")) {
				GameState.inventory.add(new Item("pirate outfit"));
				GameState.pirateClothes = false;
				GameState.outfit = null;
				output = "You've unequipped: pirate outfit. You will not be able to enter the compound.";
				unequipped = true;
			}
		}
		else if (GameState.outfit.getName().equals(words[1])) {
			GameState.inventory.add(new Item(words[1]));
			output = "You've unequipped: " + GameState.outfit.getName();
			GameState.outfit = null;
			unequipped = true;
			if (words[1].equals("armor")) {
				GameState.medievalArmor = false;
			}
		}

	}

	public void combine(String[] words) {
		if (words[1].equals("poison") && words[2].equals("liqour") || words[2].equals("poison") && words[1].equals("liqour")) {
			if (GameState.inventory.contains("poison") && GameState.inventory.contains("liqour")) {
				GameState.inventory.remove(new Item("poison"));
				GameState.inventory.remove(new Item("liqour"));
				GameState.inventory.add(new Item("poisoned liqour"));
				output = "You've got poisoned liqour!";
				combined = true;
			}
		} 
		else {
			overrideOutput = true;
			output = "Can't combine these.";
		}
	}

	public void drop(String name) {
		Item itemToDrop = new Item(name);
		GameState.inventory.remove(itemToDrop);
		GameState.currentLocation.addItem(itemToDrop);
		output = "You've dropped " + itemToDrop.getName() + ".";
		dropped = true;
	}

	public void hit(String[] words) {
		List<NPC> npcs = GameState.currentLocation.getNPCs();
		for (int i = 0; i < npcs.size(); i++) {
			if (npcs.get(i).getName().equals(words[1])) {
				if (npcs.get(i).isDead()) {
					output = "Hes already dead.";
				}
				else {
					npcs.get(i).hit(GameState.damage);
					if (!npcs.get(i).isDead()) {
						int damage = npcs.get(i).getDamage();
						if (GameState.medievalArmor) {
							damage = damage/2;
						}
						output = npcs.get(i).getHitReply() + "\n\n" + npcs.get(i).getName() + " has hit you for " + damage;
						GameState.hit(damage);
					}
					else {
						output = "Hes dead.";
					}
				}				
				hit = true;
			}
		}
		GameState.currentLocation.resetContainFlags();
	}

	public void hit(String name) {
		List<NPC> npcs = GameState.currentLocation.getNPCs();		
		for (int i = 0; i < npcs.size(); i++) {
			if (npcs.get(i).getName().equals(name)) {
				if (npcs.get(i).isDead()) {
					output = "Hes already dead.";
				}
				else {
					npcs.get(i).hit(GameState.damage);
					if (!npcs.get(i).isDead()) {
						int damage = npcs.get(i).getDamage();
						if (GameState.medievalArmor) {
							damage = damage/2;
						}
						output = npcs.get(i).getHitReply() + "\n\n" + npcs.get(i).getName() + " has hit you for " + damage;
						GameState.hit(damage);
					}
					else {
						output = "Hes dead.";
					}
				}
				hit = true;
			}
		}
		GameState.currentLocation.resetContainFlags();
	}

	public void talkTo(String[] words) {
		List<NPC> npcs = GameState.currentLocation.getNPCs();		
		for (int i = 0; i < npcs.size(); i++) {
			if (npcs.get(i).getName().equals(words[2])) {
				if (npcs.get(i).getName().equals("dimitri")) {
					if (GameState.pirateClothes) {
						output = npcs.get(i).getReply(1);
					}
					else if (GameState.inventory.contains("engine part") && GameState.inventory.contains("keys")) {
						output = npcs.get(i).getReply(2);
						GameState.won = true;
					}
					else if (GameState.inventory.contains("engine part") && !GameState.inventory.contains("keys")) {
						output = npcs.get(i).getReply(4);	
					}
					else if (!GameState.inventory.contains("engine part") && GameState.inventory.contains("keys")) {
						output = npcs.get(i).getReply(3);	
					}
 					else {
						output = npcs.get(i).getReply(0);
					}
				}
				else if (npcs.get(i).getName().equals("pirate")) {
					if (GameState.inventory.contains("poisoned liqour")) {
						output = npcs.get(i).getReply(2);
						npcs.get(i).setDead();
						GameState.inventory.remove(new Item("poisoned liqour"));
					}
					else if (GameState.pirateClothes) {
						output = npcs.get(i).getReply(1);
					}
					else {
						output = npcs.get(i).getReply(0);
					}
				}
				else if (npcs.get(i).getName().equals("sean")) {
					if (npcs.get(i).getHealth() < 100) {
						output = npcs.get(i).getReply(1);
					}
					else {
						output = npcs.get(i).getReply(0);
					}
				}
				else {
					output = npcs.get(i).getReply(0);
				}
				talked = true;
			}
		}
		GameState.currentLocation.resetContainFlags();

	}

	public void examine(String[] words) {
		if (GameState.currentLocation.containsNPC()) {			
			List<NPC> npcs = GameState.currentLocation.getNPCs();
			for (int i = 0; i < npcs.size(); i++) {
				if (npcs.get(i).getName().equals(words[1])) {
					output = npcs.get(i).getDescription();
					examined = true;
				}
			}
		}
		else if (GameState.currentLocation.containsSight()) {			
			List<Sight> sights = GameState.currentLocation.getSights();
			for (int i = 0; i < sights.size(); i++) {
				if (sights.get(i).getName().equals(words[1])) {
					output = sights.get(i).getDescription();
					examined = true;
					sights.get(i).examined();
				}
			}
		}
		else if (GameState.currentLocation.containsItem()) {			
			Map<String,Item> items = GameState.currentLocation.getItems();
			output = items.get(words[1]).getDescription();
			examined = true;		
		}
		else if (GameState.inventory.contains(words[1])) {			
			if  (words[1].equals("map")) {
				output = GameState.inventory.get(words[1]).getDescription() + GameState.currentLocation.getLocationCoords() + ".";
				examined = true;
			}
			else {
				output = GameState.inventory.get(words[1]).getDescription();
				examined = true;		
			}
		}
		GameState.currentLocation.resetContainFlags();
	}
	//exceptions for two word stuff
	public void examine(String name) {
		if (GameState.currentLocation.containsNPC()) {			
			List<NPC> npcs = GameState.currentLocation.getNPCs();
			for (int i = 0; i < npcs.size(); i++) {
				if (npcs.get(i).getName().equals(name)) {
					output = npcs.get(i).getDescription();
					examined = true;
				}
			}
			
		}
		else if (GameState.currentLocation.containsSight()) {			
			List<Sight> sights = GameState.currentLocation.getSights();
			for (int i = 0; i < sights.size(); i++) {
				if (sights.get(i).getName().equals(name)) {
					output = sights.get(i).getDescription();
					examined = true;
					sights.get(i).examined();
				}
			}
		}
		else if (GameState.currentLocation.containsItem()) {			
			Map<String,Item> items = GameState.currentLocation.getItems();
			output = items.get(name).getDescription();
			examined = true;		
		}
		else if (GameState.inventory.contains(name)) {			
			output = GameState.inventory.get(name).getDescription();
			examined = true;		
		}
		GameState.currentLocation.resetContainFlags();
	}

	public void noPirateClothes(String direction) {
		List<NPC> npcs = GameState.currentLocation.getNPCs();
		for (int i = 0; i < npcs.size(); i++) {
			if (npcs.get(i).getName().equals("pirate")) {
				if (!npcs.get(i).isDead()) {
					output = "The pirate will not let you pass. He tells you to turn around.";
					noPass = true;
				}
				else {
					setNewLocation(GameState.getCurrentLocation().move("east"));
				}
			}
		}
	}

	public void addToInventory(String itemName) {
		updateInventory = true;
		GameState.inventory.add(itemName);
	}

	public void compoundWall() {
		if (GameState.currentLocation.getX() == 1 && GameState.currentLocation.getY() == 2) {
			output = "Can't enter the compound from this side because the door seems to be locked.";
		}
		else {
			output = "Can't enter the compound from this side.";
		}
		overrideOutput = true;
	}
	public void compoundWallInside() {
		output = "Can't exit the compound from this side.";
		overrideOutput = true;
	}

	public Location getLocation() {
		return newLocation;
	}
	public void getDescription() {
		requestDescription = true;
	}

	public void setNewLocation(Location newLoc) {
		newLocation = newLoc;
		changedLocation = true;
	}

	public void printCoords() {
		printCoords = true;
	}

	public void setOutput(String out) {
		output = out;
		overrideOutput = true;
	}

	public String getOutput() {
		if (changedLocation) {
			output = GameState.currentLocation.getDescription();
			return output;
		}
		if (requestDescription) {
			if (GameState.currentLocation.getItems().isEmpty()) {
				output = GameState.currentLocation.getDescription();
			}
			else {
				output = GameState.currentLocation.getDescription() + "\n\nHere on the floor lies:\n\n";
				for (String key : GameState.currentLocation.getItems().keySet()) {
					output = output + "-" + key + "\n";
				}
			}
			return output;
		}
		else if (printCoords) {
			output = GameState.currentLocation.getLocationCoords();
			return output;
		}
		else if (overrideOutput) {
			return output;
		}
		else if (updateInventory) {
			output = "You've picked up " + GameState.inventory.getLastItemName() + ".";
			return output;
		}
		else if (examined) {
			return output;
		}
		else if (printInventory) {
			output = GameState.inventory.getPrintOut();
			return output;
		}
		else if (hit) {
			return output;
		}
		else if (talked) {
			return output;
		}
		else if (dropped) {
			return output;
		}
		else if (equipped) {
			return output;
		}
		else if (unequipped) {
			return output;
		}
		else if (combined) {
			return output;
		}
		else if (used) {
			return output;
		}
		else if (noPass) {
			return output;
		}
		else {
			output = "Wrong input. Type commands for help.";
			return output;
		}
	}
	public void wrongInput() {
		output = "wrong input.";
		overrideOutput = true;
	}
}
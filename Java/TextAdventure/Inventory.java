import java.util.*;
public class Inventory {

	private String lastItem = "";
	private HashMap<String,Item> currentItems;

	public Inventory(){
		currentItems = new HashMap<String,Item>();
	}

	public boolean contains(String itemName) {
		GameState.currentLocation.resetContainFlags();
		return currentItems.containsKey(itemName);
	}

	public void clear() {
		currentItems.clear();
	}

	public boolean isEmpty() {
		return currentItems.isEmpty();
	}

	public void add(String itemName) {
		currentItems.put(itemName,GameState.currentLocation.pickUp(itemName));
		lastItem = itemName;
	}
	public void add(Item item) {
		currentItems.put(item.getName(),item);
	}
	
	public Item get(String itemName) {
		return currentItems.get(itemName);
	}

	public HashMap<String,Item> getHashInventory() {
		return currentItems;
	}
	
	public void remove(Item item) {
		currentItems.remove(item.getName());
	}

	public String getLastItemName() {
		return lastItem;
	}

	public String getPrintOut() {
		String output = "Your inventory contains: \n";
		for (String key : currentItems.keySet()) {
			output = output + "\n-" + key;
		}
		return output;
	}
	public String getPrintOutWhenDead() {
		String output = " He dropped: \n";
		for (String key : currentItems.keySet()) {
			output = output + "\n-" + key;
		}
		return output;
	}

	public String getPrintOutWhenExamined(String name) {
		if (name.equals("apple tree")) {
			String output = " On it there is: \n";
			for (String key : currentItems.keySet()) {
				output = output + "\n-" + key;
			}
			return output;
		}
		if (name.equals("chest")) {
			String output = " After opening it you find: \n";
			for (String key : currentItems.keySet()) {
				output = output + "\n-" + key;
			}
			return output;
		}
		else {
			String output = " It contains: \n";
			for (String key : currentItems.keySet()) {
				output = output + "\n-" + key;
			}
			return output;

		}
	}
}
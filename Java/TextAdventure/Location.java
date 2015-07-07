import java.util.*;

public class Location {
	private Map<String,String> examineOptions; 
	private String description;
	private Location newLocation;
	private Location locationNorth;
	private Location locationSouth;
	private Location locationWest;
	private Location locationEast;
	private int xCoord;
	private int yCoord;
	private Map<String,Item> itemsInArea;
	private List<NPC> npcs;
	private List<Sight> sights;
	private boolean containsItem = false;
	private boolean containsNPC = false;
	private boolean containsSight = false;

	public Location(String desc, int x, int y) {
		description = desc;
		xCoord = x;
		yCoord = y;
		loadContent();
	}
	public void loadContent() {
		loadCurrentMapContent(xCoord, yCoord);

	}
	public boolean locationContains(String itemName) {
		boolean contains = false;
		if (itemsInArea.containsKey(itemName)) {
			contains = true;
			containsItem = true;
		}
		else if (!npcs.isEmpty()) {
			for (int i = 0; i < npcs.size(); i++) {
				if (npcs.get(i).getName().equals(itemName)) {
					contains = true;
					containsNPC = true;
				}
			}
		}
		else if (!sights.isEmpty()) {
			for (int i = 0; i < sights.size(); i++) {
				if (sights.get(i).getName().equals(itemName)) {
					contains = true;
					containsSight = true;
				}
			}
		}
		return contains;
	}

	public void resetContainFlags() {
		containsItem = false;
		containsSight = false;
		containsNPC = false; 
	}
	public boolean containsItem() {
		return containsItem;
	}
	public boolean containsSight() {
		return containsSight;
	}
	public boolean containsNPC() {
		return containsNPC;
	}

	public Item pickUp(String itemName) {
		if (itemName.equals("apple") || itemName.equals("berries")) {

		}
		else {
			itemsInArea.remove(itemName);
			resetContainFlags();
		}
		return new Item(itemName);
	}

	public List<NPC> getNPCs() {
		return npcs;
	}

	public List<Sight> getSights() {
		return sights;
	}
	public Map<String,Item> getItems() {
		return itemsInArea;
	}
	public void addItems(HashMap<String,Item> s) {
		itemsInArea.putAll(s);
	}
	public void addItem(Item newItem) {
		itemsInArea.put(newItem.getName(),newItem);
	}

	public void loadCurrentMapContent(int x, int y) {
		itemsInArea = new HashMap<String,Item>();
		npcs = new ArrayList<NPC>();
		sights = new ArrayList<Sight>();
		if (x == 0 && y == 0) {
			itemsInArea.put("water bottle",new Item("water bottle"));
			sights.add(new Sight("shack"));
			sights.get(sights.size()-1).addItem(new Item("poison"));
			sights.add(new Sight("bushes"));
			sights.get(sights.size()-1).addItem(new Item("berries"));
		}
		if (x == 0 && y == 1) {
			npcs.add(new NPC("tyler"));
		}
		if (x == 0 && y == 2) {
			itemsInArea.put("apples", new Item("apples"));
			sights.add(new Sight("apple tree"));
			sights.get(sights.size()-1).addItem(new Item("apples"));
			sights.add(new Sight("shed"));
			sights.get(sights.size()-1).addItem(new Item("robe"));

		}
		if (x == 0 && y == 3) {
			npcs.add(new NPC("dimitri"));
		}
		if (x == 1 && y == 0) {
			npcs.add(new NPC("man"));
		}
		if (x == 1 && y == 1) {
			npcs.add(new NPC("lord kremlin"));
		}
		if (x == 1 && y == 2) {
			sights.add(new Sight("blue car"));
			sights.get(sights.size()-1).addItem(new Item("water bottle"));
			sights.get(sights.size()-1).addItem(new Item("liqour"));
			sights.get(sights.size()-1).addItem(new Item("batton"));
			sights.add(new Sight("red car"));
			sights.get(sights.size()-1).addItem(new Item("map"));

		}
		if (x == 1 && y == 3) {
			sights.add(new Sight("forest"));
			sights.add(new Sight("chest"));
			sights.get(sights.size()-1).addItem(new Item("sword"));
			sights.get(sights.size()-1).addItem(new Item("armor"));

		}
		if (x == 2 && y == 0) {
			npcs.add(new NPC("pirate"));
			sights.add(new Sight("forest"));
		}
		if (x == 2 && y == 1) {
			npcs.add(new NPC("pirate"));
		}
		if (x == 2 && y == 2) {
			npcs.add(new NPC("pirate"));
		}
		if (x == 2 && y == 3) {
			npcs.add(new NPC("pirate"));
		}
		if (x == 3 && y == 0) {
			npcs.add(new NPC("pirate"));
			sights.add(new Sight("forest"));
			sights.add(new Sight("abandoned house"));
			npcs.add(new NPC("sean"));
		}
		if (x == 3 && y == 1) {
			npcs.add(new NPC("pirate"));
			sights.add(new Sight("forest"));

		}
		if (x == 3 && y == 2) {
			npcs.add(new NPC("pirate"));
		}
		if (x == 3 && y == 3) {
			npcs.add(new NPC("pirate"));
		}


	}

	public String getLocationCoords() {
		String x = "[" + xCoord + "," + yCoord + "]";
		return x;	
	}

	public int getX() {
		return xCoord;
	}
	
	public int getY() {
		return yCoord;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDesciption(String s) {
		description = s;
	}

	public Location move(String s) {
		if (s.equals("north")) {
			return locationNorth;
		}
		else if (s.equals("south")) {
			return locationSouth;
		}
		else if (s.equals("west")) {
			return locationWest;
		}
		else if (s.equals("east")) {
			return locationEast;
		}
		else {
			return GameState.currentLocation;
		}
	}

	public void setDirections() {
		if (yCoord >= 0 && yCoord < 3) {
			locationSouth = GameState.world[xCoord][yCoord+1];
		}
		else {
			locationSouth = GameState.world[xCoord][yCoord];
		}
		if (yCoord >= 1 && yCoord < 4) {
			locationNorth = GameState.world[xCoord][yCoord-1];
		}
		else {
			locationNorth = GameState.world[xCoord][yCoord];
		}
		if (xCoord >= 1 && xCoord < 4) {
			locationWest = GameState.world[xCoord-1][yCoord];
		}
		else {
			locationWest = GameState.world[xCoord][yCoord];
		}
		if (xCoord >= 0 && xCoord < 3) {
			locationEast = GameState.world[xCoord+1][yCoord];
		}
		else {
			locationEast = GameState.world[xCoord][yCoord];
		}
	}
}
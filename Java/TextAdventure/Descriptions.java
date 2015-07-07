import java.util.*;

public class Descriptions {
	public String[][] descriptions = new String[4][4];
	public Map<String,String> items;
	public Map<String,String> sights;


	public Descriptions(){
		initializeDescriptions();
	}

	public void initializeDescriptions() {
		
		//map descriptions
		descriptions[0][0] = "There is an ocean to the north and west of you. As you look around, you see a road leading east and south, a shack, and some bushes.";
		descriptions[0][1] = "There is an ocean to the west of you. As you look around, you see a road leading south and north, a compound to the east of you with a flag on it, and a drunk guy named Tyler.";
		descriptions[0][2] = "There is an ocean to the west of you. As you look around, you see a road leading north and east, an apple tree, a shed, ";
		descriptions[0][3] = "There is an ocean to the west and south of you. As you look around, a house and a local named Dimitri standing by it and a boat on the shore.";
		descriptions[1][0] = "There is an ocean to the north of you. As you look around, you see a road leading east and west, some trees to the east and a man laying by the road.";
		descriptions[1][1] = "You are in the basement of the compound. It's pretty dark and moist here. As you look around, you see a door to the south, a bookbag that resembles the one Dimitri asked you about";
		descriptions[1][2] = "There is a wall of the compound to the east and south. The wall to the south has a door. As you look around, you see A parking lot with two cars, blue and a red one.";
		descriptions[1][3] = "You find yourself in a forest with a fence to the east of you.";
		descriptions[2][0] = "There is an ocean to the north of you and a wall of a compound to the south. You are surrounded by the forest and a pirate standing on the road a head.";
		descriptions[2][1] = "2,1 ";
		descriptions[2][2] = "You are in a hall leading to the main room up north. A group of pirates are staring you down, and you are worried that they might know who you are.";
		descriptions[2][3] = "You are in a courtyard enclosed by fences with an enterance to the compound to the north. As you look around you see a parking lot with pick-up truck equipped with .50 cal. Two pirates are watching the truck.";
		descriptions[3][0] = "There is an ocean to the east and north of you. You are in a forest and there is a road leading west and south of you. As you look around, you see an abandoned house by the road, an elderly man by the shore named Sean, and a pirate.";
		descriptions[3][1] = "There is an ocean to the east of you and compound to the west. You are in a forest and there is a road leading north and south of you. There is a pirate guarding the road ahead and an abandoned boat on the shore";
		descriptions[3][2] = "There is an ocean to the east of you and compound to the west. You are exiting the forest. As you look around, you see a pirate patrolling the enterance to the compound.";
		descriptions[3][3] = "There is an ocean to the east and south of you. There is a road leading north and west into the compound. There is a pirate sleeping by the enterance to the compound.";
		
		//memory messages
		GameState.past[0] = "\nYour memory starts to clear up. You recall being on an expensive boat...at night time.\n";
		GameState.past[1] = "\nYou remember working as a waiter at a luxurious party on a boat. Then you heard something...but not sure what.\n";
		GameState.past[2] = "\nYou start to feel endangered as you remember the sound you were hearing at the party was gun shots. Your boat was boarded by some Pirates.\n";
		//item descriptions/
		items = new HashMap<String,String>();
		items.put("apples","Apple is ripe and ready to eat!");
		items.put("pirate outfit","A Pirate Outfit. This might be useful for me to get off this island!");
		items.put("bag","This bag contains everything needed to use the boat and get off the island with Dimitri.");
		items.put("liqour","40 proof Vodka. Just what I need to bribe some pirates!");
		items.put("poison","I could combine this poison with some liqour in order to kill some pirates.");
		items.put("water bottle","Fresh water. Just what I need to feel better.");
		items.put("berries","Fresh berries. This will make me healtier.");
		items.put("applepie","Fresh applepie made my Dimitri.");
		items.put("liqour","Clear liqour with russian letters on it. After closer inspection, you get warm memories of vodka...down your throat.");
		items.put("poison","Poisonous Mushroom powder.");
		items.put("axe","An axe in pristene condition. I should equip this to have a better chance against the pirates.");
		items.put("poisoned liqour","Poisoned liqour I can offer to some pirates.");
		items.put("hands","Better put them back on.");
		items.put("robe","Cheap robe, like the ones you find in a hotel bathroom."); 
		items.put("sword","Sword from the Medieval times. This will do some damage.");
		items.put("armor","Nice set of medieval armor. This will reduce the damage taken by a half.");
		

		items.put("bandage","A bandage that I can use to heal myself.");
		items.put("engine part","An engine part that can be used to fix Dimitri's boat.");
		items.put("batton","A police batton that seems to have been used quite some time... pretty useless but better than bare hands.");
		items.put("map","A map of the Island that seems to be divided into four by four squares, top left being [0,0]. After examining your surroundings, you decide that you are at ");


		//sights
		sights = new HashMap<String,String>();
		sights.put("shack","An old run down shack.");
		sights.put("bushes","Nice looking bushes with berries on them.");
		sights.put("compound","A compound with high walls. Built to keep people out.");
		sights.put("house","Pretty small house, extremely dirty.");
		sights.put("car","A run down car.");
		sights.put("apple tree","Healthy looking apple tree. Not many apples are left though.");
		sights.put("blue car","A run down car. After closer inspection it appears to be a very old police car.");
		sights.put("red car","Well kept car, with windows down. Must be the island leaders car.");
		sights.put("forest","Pretty dense forest, nothing to see here besides that hidden chest.");
		sights.put("chest","A hidden chest that pirates havent discovered on their own little island.");
		sights.put("boat","Dimitri's boat. It requires an engine part and keys that pirates have stolen. If I can somehow get fix this boat me and Dimitri can both leave!");
		
		sights.put("abandoned boat","This boat appears to be abandoned but the engine checks out. I wonder if this part will work for Dimitri");
		sights.put("flag","I dont know what flag that is...but it is not welcoming.");
		sights.put("apple tree","A nice healthy looking apple tree. Good source of food!");
		sights.put("shed","A shed resembling one in ordinary farms.");
		
		


	}
	public String getDesc(int i, int j){
		return descriptions[i][j];
	}
	public String getDescItem(String itemName) {	
		return items.get(itemName);
	}

	public String getSightDesc(String name) {
		return sights.get(name);
	}
}
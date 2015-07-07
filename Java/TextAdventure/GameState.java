public class GameState {
	
	//player stuff
	public static String name;
	public static int health = 100;
	public static boolean pirateClothes = false;
	public static boolean medievalArmor = false;
	public static int damage = 10;
	public static Item weapon;
	public static Item outfit;
	public static boolean isDead = false;

	public static boolean iddqd = false;

	//location stuff
	public static Location[][] world = new Location[4][4];
	public static Location currentLocation;
	
	//inventory stuff
	public static Inventory inventory = new Inventory();
	
	//regaining memory messages
	public static String[] past = new String[3]; 

	//item pick up booleans
	public static boolean win = false;
	public static boolean lost = false;

	

	



	//location stuff
	
	public static void hit(int i) {
		if (iddqd) {}
		else {	
			health = health - i;
			if (health <= 0) {
				isDead = true;
				lost = true;
			}
		}
	}
	public static void checkHP() {
		if (health > 100) {
			health = 100;
		}
	}



	public static Location getCurrentLocation() {
		return currentLocation;
	}
	public static void setCurrentLocation(Location loc) {
		currentLocation = loc;
	}
	public static void printAllLocations() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				Location l = GameState.world[i][j];
				System.out.println(l.getX());
				System.out.println(l.getY());
			}
		}
	}
}
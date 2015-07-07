public class NPC {
	private int health;
	private String name;
	private Inventory inventory = new Inventory();
	private String description;
	private String deadDescription;
	private String hitReply;
	private String[] reply = new String[5];
	private boolean dead = false;
	private int damage;

	public NPC(String npcName) {
		name = npcName;
		loadProperties();
	}
	public void addItem(Item item) {
		inventory.add(item);
	}
	public boolean isDead() {
		return dead;
	}
	public void setDead() {
		dead = true;
		health = 0;
	}
	public int getHealth() {
		return health;
	}
	public void hit(int i) {
		health = health - i;
		if (health <= 0) {
			health = 0;
			dead = true;
		}
	}
	public String getName() {
		return name;
	}
	public int getDamage() {
		return damage;
	}
	public String getDescription() {
		if (dead) {
			if (!inventory.isEmpty()) {
				GameState.currentLocation.addItems(inventory.getHashInventory());
				String deadDescriptionV2 = deadDescription + inventory.getPrintOutWhenDead();				
				inventory.clear();
				return deadDescriptionV2;
			}
			else {
				return deadDescription;
			}
		}
		else {
			return description;
		}
	}
	public String getReply(int i) {
		return reply[i];
	}

	public String getHitReply() {
		return hitReply;
	}

	public void loadProperties() {
		if (name.equals("dimitri")) {
			hitReply = "Why are you attacking me! We are on the same side...dont let this island get to you like it did to me...";
			deadDescription = "impossible!";
			health = 1000;
			damage = 15;
			description = "Dimitri is a local that has been trapped on this island. His ultimate goal is to disappear.";
			reply[0] = "Hello, my name is Dimitri. Ive become a local here after being trapped for so long...I cannot remember. I would have left long time ago if I could start my boat...The pirates took an engine part and the keys from me. If we can get our hands on them, we're gone! Now the pirates wont let you get close to the compound, we need to somehow obtain their outfit... Figure out how to get it, put it on and come back to me!";
			reply[1] = "Now that you have the outfit, you must find engine part and your way into the compound. When you enter the compound, you will find yourself in a hall..from my sources, the basement should be in the following room to west of it. That is where they are keeping the bag.";
			reply[2] = "Beautiful! Lets get out of here!";
			reply[3] = "We still need an engine part. Check the abandoned ship on the east side of the island";
			reply[4] = "We still need the keys. Infiltrate the compound!";
			inventory.add(new Item("liqour"));
		}
		else if (name.equals("pirate")) {
			hitReply = "You will regret this! Arghhhh!";
			damage = 15;
			health = 100;
			description = "Local pirate. All he cares about is money and liqour.";
			reply[0] = "The pirate tells you to turn around and get going unless you have some money or liqour. Then he threatens you to knock you out.";
			reply[1] = "Hey...I forgot what your name is. As long as you mind your own business we are cool.";
			reply[2] = "Hey! Thats some powerful vodka You've got there. Give me a shot! *takes a shot* Wow...It is sooo stro...";
			inventory.add(new Item("pirate outfit"));
			deadDescription = "This pirate appears to be dead. Still warm to the touch. He dropped: ";
		}
		else if (name.equals("lord kremlin")) {
			hitReply = "No one leaves this island alive. Prepare for my wrath!";
			deadDescription = "Here lies the leader to the Pirates!";
			damage = 30;
			health = 150;
			description = "Leader of the pirates. His favorite hobby is to kill.";
			reply[0] = "I'm going to kill you!";
		}
		else if (name.equals("tyler")) {
			hitReply = "Whaaaaaaaaaaaaa! Why God!....";
			deadDescription = "He is out cold. Probably best for him...He should sobber up.";
			damage = 0;
			health = 10;
			description = "A bum that seems to be too drunk to even talk. I wonder how he got here.";
			reply[0] = "there is hope...ther...ahh this vodka.";
			inventory.add(new Item("liqour"));
		}
		else if (name.equals("man")) {
			hitReply = "*wakes up* Ahhh! Why would you do that!";
			deadDescription = "He is laying on the ground unconscious. Maybe its for the better.";
			damage = 5;
			health = 20;
			description = "He is out cold and has an axe attached to his back.";
			reply[0] = "ZZZzzzzZZzzz";
			inventory.add(new Item("axe"));
		}
		else if (name.equals("sean")) {
			hitReply = "No one hits the All Mighty!";
			deadDescription = "I will haunt you for this.";
			damage = 99;
			health = 500;
			description = "Leader of the leaders. Whatever that means.";
			reply[0] = "Hello youngster. If you kill me I will teach you the power of Sean.";
			reply[1] = "Type cheats iddqd for god mode. Good old days...";
		}
		else {
			description = "jesus";
		}
	}
}
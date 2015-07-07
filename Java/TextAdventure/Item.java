public class Item {
	private String description;
	private String itemName;
	private boolean equippable = false;
	private boolean usable = false;
	private boolean combinable = false;
	private boolean weapon = false;
	private int damage; 
	private boolean outfit = false;
	private String usedMessage;

	public Item(String name) {
		itemName = name;
		setProperties();
	}
	public String getUsedMessage() {
		return usedMessage;
	}
	public boolean isOutfit() {
		return outfit;
	}
	public boolean usable() {
		return usable;
	}
	public boolean isEquippable() {
		boolean temp = equippable;
		return temp;	
	}
	public boolean isWeapon() {
		return weapon;
	}
	public String getName() {
		return itemName;
	}
	public int getDamage() {
		return damage;
	}
	public String getDescription() {
		return description;
	}
	public void use(String name) {
		if (name.equals("apples")) {
			GameState.health = GameState.health + 50;
		}
		if (name.equals("liqour")) {
			GameState.damage = GameState.damage + 20;
		}
		if (name.equals("water bottle")) {
			GameState.health = GameState.health + 35;
		}
		if (name.equals("berries")) {
			GameState.health = GameState.health + 10;
		}
		if (name.equals("poison")) {
			GameState.health = GameState.health - 100;
		}
		if (name.equals("poisoned liqour")) {
			GameState.health = GameState.health -100;
		}
		if (name.equals("applepie")) {
			GameState.health = GameState.health + 55;
		}
		GameState.checkHP();
	}
	public void setProperties() {
		Descriptions desc = new Descriptions();
		if (itemName.equals("apples")) {
			usable = true;
			description = desc.getDescItem(itemName);
			usedMessage = "You have eaten an apple. It was delicious and your HP increased by 50";
		}
		if (itemName.equals("armor")) {
			equippable = true;
			outfit = true;
			description = desc.getDescItem(itemName);
		}
		if (itemName.equals("pirate outfit")) {
			equippable = true;
			outfit = true;
			description = desc.getDescItem(itemName);
		}
		if (itemName.equals("robe")) {
			equippable = true;
			outfit = true;
			description = desc.getDescItem(itemName);
		}
		if (itemName.equals("bag")) {
			description = desc.getDescItem(itemName);
			
		}
		if (itemName.equals("bookbag")) {
			description = desc.getDescItem(itemName);
		}
		if (itemName.equals("liqour")) {
			description = desc.getDescItem(itemName);
			combinable = true;
			usable = true;
			usedMessage = "You feel a little drunk. Probably should've used it on the pirates";
		}
		if (itemName.equals("water bottle")) {
			description = desc.getDescItem(itemName);
			usable = true;
			usedMessage = "Nothing like fresh water. HP increased by 35";
		}
		if (itemName.equals("berries")) {
			description = desc.getDescItem(itemName);
			usable = true;
			usedMessage = "Fresh berries. One if I could hold more than 1 at a time... stupid developer! HP increased by 10";
		}
		if (itemName.equals("poison")) {
			description = desc.getDescItem(itemName);
			usable = true;
			combinable = true;
			usedMessage = "I shouldnt hav";
		}
		if (itemName.equals("appliepie")) {
			description = desc.getDescItem(itemName);
			usable = true;
			usedMessage = "One of the best appliepies I have tasted. Hp increased by 55";
		}
		if (itemName.equals("poisoned liqour")) {
			description = desc.getDescItem(itemName);
			usable = true;
			usedMessage = "I shouldnt hav";
		}
		if (itemName.equals("map")) {
			description = desc.getDescItem(itemName);
		}


		if (itemName.equals("axe")) {
			description = desc.getDescItem(itemName);
			equippable = true;
			damage = 40;
			weapon = true;
		}
		if (itemName.equals("hands")) {
			description = desc.getDescItem(itemName);
			equippable = true;
			damage = 10;
			weapon = true;
		}
		if (itemName.equals("sword")) {
			description = desc.getDescItem(itemName);
			equippable = true;
			damage = 75;
			weapon = true;
		}
		if (itemName.equals("batton")) {
			description = desc.getDescItem(itemName);
			equippable = true;
			damage = 25;
			weapon = true;
		}

	}
}
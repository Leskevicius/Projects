public class Sight {
	private String description;
	private String name;
	private boolean examined = false;
	private Inventory inventory;

	public Sight(String name1) {
		inventory = new Inventory();
		name = name1;
		loadDesc();
	}

	public void loadDesc() {
		Descriptions desc = new Descriptions();
		description = desc.getSightDesc(name);
	}

	public void setDescription(String desc) {
		description = desc;
	}

	public String getDescription() {
		if (!examined) {
			if (!inventory.isEmpty()) {
				GameState.currentLocation.addItems(inventory.getHashInventory());
				String descriptionV2 = description + inventory.getPrintOutWhenExamined(name);				
				inventory.clear();
				return descriptionV2;
			}
			else {
				return description;
			}
		}
		else {
			return description;
		}
	}

	public String getName() {
		return name;
	}

	public void examined() {
		examined = true;
	}

	public void addItem(Item item) {
		inventory.add(item);
	}

}
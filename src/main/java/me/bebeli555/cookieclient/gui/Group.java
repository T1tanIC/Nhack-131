package me.bebeli555.cookieclient.gui;

public enum Group {
	BOTS("AI", 828, 60, "The bots"),
	GUI("ClickGui", 622, 60, "Stuff about the GUI and HUD"),
	GAMES("MiniGames", 725, 60, "Fun games to play"),
	COMBAT("Fight", 4, 60, "Combat modules for pvp and stuff"),
	EXPLOITS("Glitch", 107, 60, "Useful exploit modules"),
	MISC("Miscullanious", 210, 60, "Other modules"),
	MOVEMENT("Speed", 313, 60, "Movement modules"),
	RENDER("Seeing", 416, 60, "Client-sided render modules"),
	WORLD("Earth", 519, 60, "Some other modules that like do something");
	
	public String name;
	public int x, y;
	public String[] description;
	Group(String name, int x, int y, String... description) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.description = description;
	}
}

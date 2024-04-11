package me.bebeli555.cookieclient.gui;

public enum Group {
	BOTS("botting", 828, 60, "The bots"),
	GUI("clickgui", 622, 60, "Stuff about the GUI and HUD"),
	GAMES("minis", 725, 60, "Fun games to play"),
	COMBAT("combat", 4, 60, "Combat modules for pvp and stuff"),
	EXPLOITS("exploit", 107, 60, "Useful exploit modules"),
	MISC("misc", 210, 60, "Other modules"),
	MOVEMENT("movement", 313, 60, "Movement modules"),
	RENDER("Seeing", 416, 60, "Client-sided render modules"),
	WORLD("earthattack", 519, 60, "Some other modules that like do something"),
	GAMEMECHANICS("gamemechanics",700,70,"gamming");

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

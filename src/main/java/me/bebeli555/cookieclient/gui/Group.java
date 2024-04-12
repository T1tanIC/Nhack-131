package me.bebeli555.cookieclient.gui;

public enum Group {
	BOTS("Botting", 828, 60, "the bots"),
	GUI("Clickgui", 622, 60, "stuff about the gui and hud"),
	GAMES("Minis", 725, 60, "fun games to play"),
	COMBAT("Combat", 4, 60, "combat modules for pvp and stuff"),
	EXPLOITS("Exploit", 107, 60, "useful exploit modules"),
	MISC("Misc", 210, 60, "ther modules"),
	MOVEMENT("Movement", 313, 60, "Movement modules"),
	RENDER("Seeing", 416, 60, "client-sided render modules"),
	WORLD("WorldSploit", 519, 60, "some other modules that like do something"),
	GAMEMECHANICS("GameMechanics",700,70,"gamming");

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

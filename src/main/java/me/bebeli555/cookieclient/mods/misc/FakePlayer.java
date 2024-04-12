package me.bebeli555.cookieclient.mods.misc;

import java.util.UUID;

import com.mojang.authlib.GameProfile;

import me.bebeli555.cookieclient.Mod;
import me.bebeli555.cookieclient.gui.Group;
import net.minecraft.client.entity.EntityOtherPlayerMP;

public class FakePlayer extends Mod {
	public static EntityOtherPlayerMP fakePlayer;
	
	public FakePlayer() {
		super(Group.MISC, "FakePlayer", "Creates a fakeplayer at ur location");
	}
	
	@Override
	public boolean onEnabled() {
		if (mc.player == null) {
			disable();
            return false;
		}
		
		fakePlayer = new EntityOtherPlayerMP(mc.world, new GameProfile(UUID.fromString("a664dace-cd47-458a-84fc-9661ef70d4a0","34rthq04k3"));
		fakePlayer.copyLocationAndAnglesFrom(mc.player);
		fakePlayer.rotationYawHead = mc.player.rotationYawHead;
		mc.world.addEntityToWorld(-100, fakePlayer);
        return false;
    }
	
	@Override
	public void onDisabled() {
		if (mc.world != null && fakePlayer != null) {
			mc.world.removeEntity(fakePlayer);
		}
	}
}

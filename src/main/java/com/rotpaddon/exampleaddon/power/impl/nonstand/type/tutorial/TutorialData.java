package com.rotpaddon.exampleaddon.power.impl.nonstand.type.tutorial;

import com.github.standobyte.jojo.power.impl.nonstand.TypeSpecificData;
import com.rotpaddon.exampleaddon.network.AddonPackets;
import com.rotpaddon.exampleaddon.network.server.TutorialDataPacket;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;

public class TutorialData extends TypeSpecificData {
    public boolean isArmored;
    
    public void setIsArmored(boolean isArmored) {
        LivingEntity user = power.getUser();
        this.isArmored = isArmored;
        if (!user.level.isClientSide()) {
        	 AddonPackets.sendToClientsTrackingAndSelf(new TutorialDataPacket(user.getId(), isArmored), user);
        }
    }

    @Override
    public CompoundNBT writeNBT() {
        CompoundNBT nbt = new CompoundNBT(); // Better to save data, like states or some other values.
        nbt.putBoolean("isArmored", isArmored);
        return nbt;
    }

    @Override
    public void readNBT(CompoundNBT nbt) {
    	this.isArmored = nbt.getBoolean("isArmored");
    }

    @Override
    public void syncWithUserOnly(ServerPlayerEntity user) { // Syncs with user (with packets)
		AddonPackets.sendToClientsTrackingAndSelf(new TutorialDataPacket(user.getId(), isArmored), user);
    }

    @Override
    public void syncWithTrackingOrUser(LivingEntity user, ServerPlayerEntity entity) {
   	 	AddonPackets.sendToClientsTrackingAndSelf(new TutorialDataPacket(user.getId(), isArmored), user);
    }
}

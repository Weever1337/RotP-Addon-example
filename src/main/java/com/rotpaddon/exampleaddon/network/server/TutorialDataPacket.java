package com.rotpaddon.exampleaddon.network.server;

import java.util.function.Supplier;

import com.github.standobyte.jojo.client.ClientUtil;
import com.github.standobyte.jojo.network.packets.IModPacketHandler;
import com.github.standobyte.jojo.power.impl.nonstand.INonStandPower;
import com.rotpaddon.exampleaddon.init.InitPowers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class TutorialDataPacket {
    public final int entityId;
    public final boolean isArmored;

    public TutorialDataPacket(int entityId, boolean isArmored) {
        this.entityId = entityId;
        this.isArmored = isArmored;
    }

    public static class Handler implements IModPacketHandler<TutorialDataPacket> {
        @Override
        public void encode(TutorialDataPacket msg, PacketBuffer buf) {
            buf.writeInt(msg.entityId);
            buf.writeBoolean(msg.isArmored);
        }

        @Override
        public TutorialDataPacket decode(PacketBuffer buf) {
            int entityId = buf.readInt();
            boolean isArmored = buf.readBoolean();
            return new TutorialDataPacket(entityId, isArmored);
        }

        @Override
        public void handle(TutorialDataPacket msg, Supplier<NetworkEvent.Context> ctx) {
            Entity entity = ClientUtil.getEntityById(msg.entityId);
            if (entity instanceof LivingEntity) {
            	INonStandPower.getNonStandPowerOptional((LivingEntity) entity).ifPresent(power -> {
	                power.getTypeSpecificData(InitPowers.TUTORIAL.get()).ifPresent(data -> {
	                    data.setIsArmored(msg.isArmored);
	                });
            	});
            }
        }

        @Override
        public Class<TutorialDataPacket> getPacketClass() {
            return TutorialDataPacket.class;
        }
    }
}

package com.rotpaddon.exampleaddon.network.server

import com.github.standobyte.jojo.client.ClientUtil
import com.github.standobyte.jojo.network.packets.IModPacketHandler
import com.github.standobyte.jojo.power.impl.nonstand.INonStandPower
import com.rotpaddon.exampleaddon.init.InitPowers
import com.rotpaddon.exampleaddon.power.impl.nonstand.type.tutorial.TutorialData
import com.rotpaddon.exampleaddon.power.impl.nonstand.type.tutorial.TutorialPowerType
import net.minecraft.entity.LivingEntity
import net.minecraft.network.PacketBuffer
import net.minecraftforge.fml.network.NetworkEvent
import java.util.function.Supplier

class TutorialDataPacket(val entityId: Int, val isArmored: Boolean) {

    class Handler : IModPacketHandler<TutorialDataPacket> {
        override fun encode(msg: TutorialDataPacket, buf: PacketBuffer) {
            buf.writeInt(msg.entityId)
            buf.writeBoolean(msg.isArmored)
        }

        override fun decode(buf: PacketBuffer): TutorialDataPacket {
            val entityId = buf.readInt()
            val isArmored = buf.readBoolean()
            return TutorialDataPacket(entityId, isArmored)
        }

        override fun handle(msg: TutorialDataPacket, ctx: Supplier<NetworkEvent.Context>) {
            val entity = ClientUtil.getEntityById(msg.entityId)
            if (entity is LivingEntity) {
                INonStandPower.getNonStandPowerOptional(entity).ifPresent { power ->
                    power.getTypeSpecificData<TutorialPowerType, TutorialData>(InitPowers.TUTORIAL.get()).ifPresent { data ->
                        data.setIsArmored(msg.isArmored)
                    }
                }
            }
        }

        override fun getPacketClass(): Class<TutorialDataPacket> {
            return TutorialDataPacket::class.java
        }
    }
}
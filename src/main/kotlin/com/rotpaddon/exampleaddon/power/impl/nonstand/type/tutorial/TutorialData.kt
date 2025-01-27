package com.rotpaddon.exampleaddon.power.impl.nonstand.type.tutorial

import com.github.standobyte.jojo.power.impl.nonstand.TypeSpecificData
import com.rotpaddon.exampleaddon.network.AddonPackets
import com.rotpaddon.exampleaddon.network.server.TutorialDataPacket
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.nbt.CompoundNBT

class TutorialData : TypeSpecificData() {
    var isArmored: Boolean = false

    fun setIsArmored(isArmored: Boolean) {
        val user = power.user
        this.isArmored = isArmored
        if (!user.level.isClientSide) {
            AddonPackets.sendToClientsTrackingAndSelf(TutorialDataPacket(user.id, isArmored), user)
        }
    }

    override fun writeNBT(): CompoundNBT {
        val nbt = CompoundNBT()
        nbt.putBoolean("isArmored", isArmored)
        return nbt
    }

    override fun readNBT(nbt: CompoundNBT) {
        this.isArmored = nbt.getBoolean("isArmored")
    }

    override fun syncWithUserOnly(user: ServerPlayerEntity) {
        AddonPackets.sendToClientsTrackingAndSelf(TutorialDataPacket(user.id, isArmored), user)
    }

    override fun syncWithTrackingOrUser(user: LivingEntity, entity: ServerPlayerEntity) {
        AddonPackets.sendToClientsTrackingAndSelf(TutorialDataPacket(user.id, isArmored), user)
    }
}
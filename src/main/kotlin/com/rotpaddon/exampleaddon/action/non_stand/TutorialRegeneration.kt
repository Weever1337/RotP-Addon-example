package com.rotpaddon.exampleaddon.action.non_stand

import com.github.standobyte.jojo.action.ActionConditionResult
import com.github.standobyte.jojo.action.ActionTarget
import com.github.standobyte.jojo.action.non_stand.NonStandAction
import com.github.standobyte.jojo.power.impl.nonstand.INonStandPower
import net.minecraft.entity.LivingEntity
import net.minecraft.world.World

class TutorialRegeneration(builder: NonStandAction.Builder?) : TutorialAction(builder as Builder) {
    override fun checkSpecificConditions(user: LivingEntity, power: INonStandPower, target: ActionTarget): ActionConditionResult {
        return if (user.health > 0 && power.energy >= 10 && user.health < user.maxHealth) {
            ActionConditionResult.POSITIVE
        } else {
            ActionConditionResult.NEGATIVE
        }
    }

    override fun holdTick(
        world: World,
        user: LivingEntity,
        power: INonStandPower,
        ticksHeld: Int,
        target: ActionTarget,
        requirementsFulfilled: Boolean
    ) {
        if (!world.isClientSide) {
            user.health = user.health + 0.1f
        }
    }
}
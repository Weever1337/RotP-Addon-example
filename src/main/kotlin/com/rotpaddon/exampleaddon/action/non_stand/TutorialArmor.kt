package com.rotpaddon.exampleaddon.action.non_stand

import com.github.standobyte.jojo.action.ActionConditionResult
import com.github.standobyte.jojo.action.ActionTarget
import com.github.standobyte.jojo.action.non_stand.NonStandAction
import com.github.standobyte.jojo.power.impl.nonstand.INonStandPower
import com.rotpaddon.exampleaddon.init.InitPowers
import com.rotpaddon.exampleaddon.power.impl.nonstand.type.tutorial.TutorialData
import com.rotpaddon.exampleaddon.power.impl.nonstand.type.tutorial.TutorialPowerType
import net.minecraft.entity.LivingEntity
import net.minecraft.world.World
import java.util.concurrent.atomic.AtomicBoolean

class TutorialArmor(builder: NonStandAction.Builder?) : TutorialAction(builder as Builder) {
    override fun perform(world: World, user: LivingEntity, power: INonStandPower, target: ActionTarget) {
        if (!world.isClientSide) {
            power.getTypeSpecificData<TutorialPowerType, TutorialData>(InitPowers.TUTORIAL.get()).ifPresent { data ->
                data.isArmored = !data.isArmored
            }
        }
    }

    override fun greenSelection(power: INonStandPower, conditionCheck: ActionConditionResult): Boolean {
        val returnValue = AtomicBoolean()
        power.getTypeSpecificData<TutorialPowerType, TutorialData>(InitPowers.TUTORIAL.get()).ifPresent { data ->
            returnValue.set(data.isArmored)
        }
        return returnValue.get()
    }
}
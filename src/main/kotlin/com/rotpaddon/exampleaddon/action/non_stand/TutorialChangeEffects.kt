package com.rotpaddon.exampleaddon.action.non_stand

import com.github.standobyte.jojo.action.ActionTarget
import com.github.standobyte.jojo.action.non_stand.NonStandAction
import com.github.standobyte.jojo.init.ModStatusEffects
import com.github.standobyte.jojo.power.impl.nonstand.INonStandPower
import net.minecraft.entity.LivingEntity
import net.minecraft.potion.EffectInstance
import net.minecraft.potion.Effects
import net.minecraft.world.World

class TutorialChangeEffects(builder: NonStandAction.Builder) : TutorialAction(builder as Builder) {
    override fun perform(world: World, user: LivingEntity, power: INonStandPower, target: ActionTarget) {
        val effect = if (isShiftVariation) Effects.NIGHT_VISION else ModStatusEffects.FULL_INVISIBILITY.get()
        if (user.hasEffect(effect)) {
            user.removeEffect(effect)
        } else {
            user.addEffect(EffectInstance(effect, Int.MAX_VALUE, Int.MAX_VALUE, false, false, true))
        }
    }
}
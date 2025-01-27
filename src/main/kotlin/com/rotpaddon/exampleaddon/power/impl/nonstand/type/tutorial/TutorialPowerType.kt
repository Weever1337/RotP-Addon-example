package com.rotpaddon.exampleaddon.power.impl.nonstand.type.tutorial

import com.github.standobyte.jojo.power.impl.nonstand.INonStandPower
import com.github.standobyte.jojo.power.impl.nonstand.type.NonStandPowerType
import com.github.standobyte.jojo.power.impl.stand.IStandPower
import com.rotpaddon.exampleaddon.action.non_stand.TutorialAction
import net.minecraft.entity.LivingEntity
import net.minecraft.potion.EffectInstance
import net.minecraft.potion.Effects

class TutorialPowerType(
    startingAttacks: Array<TutorialAction>,
    startingAbilities: Array<TutorialAction>,
    defaultMMB: TutorialAction
) : NonStandPowerType<TutorialData>(
    startingAttacks,
    startingAbilities,
    defaultMMB,
    ::TutorialData
) {
    constructor(
        startingAttacks: Array<TutorialAction>,
        startingAbilities: Array<TutorialAction>
    ) : this(startingAttacks,startingAbilities,startingAttacks[0])

    override fun tickEnergy(power: INonStandPower): Float {
        return if (power.energy < power.maxEnergy) {
            power.energy + 1f
        } else {
            power.energy
        }
    }

    override fun getMaxEnergy(power: INonStandPower): Float {
        return 1000f
    }

    override fun isReplaceableWith(newType: NonStandPowerType<*>): Boolean {
        return true
    }

    override fun keepOnDeath(power: INonStandPower): Boolean {
        return true
    }

    override fun tickUser(entity: LivingEntity, power: INonStandPower) {
        if (!entity.level.isClientSide) {
            power.getTypeSpecificData(this).ifPresent { data ->
                if (data.isArmored) {
                    entity.addEffect(EffectInstance(Effects.DAMAGE_RESISTANCE, 25, 255, false, false, true))
                    entity.addEffect(EffectInstance(Effects.FIRE_RESISTANCE, 25, 255, false, false, true))
                    power.energy = power.energy - 1f
                }
            }
        }
    }

    override fun getTargetResolveMultiplier(power: INonStandPower, attackingStand: IStandPower): Float {
        return 0f
    }

    override fun isLeapUnlocked(power: INonStandPower): Boolean {
        return true
    }

    override fun getLeapStrength(power: INonStandPower): Float {
        return 4f
    }

    override fun getLeapCooldownPeriod(): Int {
        return 20
    }

    override fun getLeapEnergyCost(): Float {
        return 100f
    }
}
package com.rotpaddon.exampleaddon.power.impl.nonstand.type.tutorial;

import com.github.standobyte.jojo.power.impl.nonstand.INonStandPower;
import com.github.standobyte.jojo.power.impl.nonstand.type.NonStandPowerType;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.rotpaddon.exampleaddon.action.non_stand.TutorialAction;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class TutorialPowerType extends NonStandPowerType<TutorialData> {
    public TutorialPowerType(TutorialAction[] startingAttacks, TutorialAction[] startingAbilities) {
        super(startingAttacks, startingAbilities, startingAttacks[0], TutorialData::new);
    }

    public TutorialPowerType(TutorialAction[] startingAttacks, TutorialAction[] startingAbilities, TutorialAction defaultMMB) {
        super(startingAttacks, startingAbilities, defaultMMB, TutorialData::new);
    }

    @Override
    public float tickEnergy(INonStandPower power) {
        if (power.getEnergy() < power.getMaxEnergy()) {
            return power.getEnergy() + 1f;
        }
        return power.getEnergy();
    }

    @Override
    public float getMaxEnergy(INonStandPower power) {
        return 1000;
    }

    @Override
    public boolean isReplaceableWith(NonStandPowerType<?> newType) {
        return true;
    }

    @Override
    public boolean keepOnDeath(INonStandPower power) {
        return true;
    }

    @Override
    public void tickUser(LivingEntity entity, INonStandPower power) {
    	if (!entity.level.isClientSide()) {
    		power.getTypeSpecificData(this).ifPresent(data -> {
    			if (data.isArmored) {
    				entity.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 25, 255, false, false, true));
    				entity.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 25, 255, false, false, true));
    				power.setEnergy(power.getEnergy() - 1f);
    			}
    		});
    	}
    }

    @Override
    public float getTargetResolveMultiplier(INonStandPower power, IStandPower attackingStand) {
        return 0;
    }

    @Override
    public boolean isLeapUnlocked(INonStandPower power) {
        return true;
    }

    @Override
    public float getLeapStrength(INonStandPower power) {
        return 4;
    }

    @Override
    public int getLeapCooldownPeriod() {
        return 20;
    }

    @Override
    public float getLeapEnergyCost() {
        return 100;
    }
}

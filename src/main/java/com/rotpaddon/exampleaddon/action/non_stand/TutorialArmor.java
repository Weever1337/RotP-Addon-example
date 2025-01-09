package com.rotpaddon.exampleaddon.action.non_stand;

import java.util.concurrent.atomic.AtomicBoolean;

import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.power.impl.nonstand.INonStandPower;
import com.rotpaddon.exampleaddon.init.InitPowers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class TutorialArmor extends TutorialAction {
    public TutorialArmor(Builder builder) {
        super(builder);
    }

    @Override
    protected void perform(World world, LivingEntity user, INonStandPower power, ActionTarget target) {
        if (!world.isClientSide()) {
    		power.getTypeSpecificData(InitPowers.TUTORIAL.get()).ifPresent(data -> {
				data.setIsArmored(!data.isArmored);
    		});
        }
    }
    
    @Override
    public boolean greenSelection(INonStandPower power, ActionConditionResult conditionCheck) {
    	AtomicBoolean returnValue = new AtomicBoolean();
		power.getTypeSpecificData(InitPowers.TUTORIAL.get()).ifPresent(data -> {
			returnValue.set(data.isArmored);
		});
		return returnValue.get();
    }
}

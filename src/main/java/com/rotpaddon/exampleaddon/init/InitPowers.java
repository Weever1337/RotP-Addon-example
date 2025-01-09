package com.rotpaddon.exampleaddon.init;

import com.github.standobyte.jojo.action.Action;
import com.github.standobyte.jojo.power.impl.nonstand.type.NonStandPowerType;
import com.rotpaddon.exampleaddon.AddonMain;
import com.rotpaddon.exampleaddon.action.non_stand.TutorialAction;
import com.rotpaddon.exampleaddon.action.non_stand.TutorialArmor;
import com.rotpaddon.exampleaddon.action.non_stand.TutorialChangeEffects;
import com.rotpaddon.exampleaddon.action.non_stand.TutorialRegeneration;
import com.rotpaddon.exampleaddon.power.impl.nonstand.type.tutorial.TutorialPowerType;

import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class InitPowers {
    @SuppressWarnings("unchecked")
    public static final DeferredRegister<Action<?>> ACTIONS = DeferredRegister.create(
            (Class<Action<?>>) ((Class<?>) Action.class), AddonMain.MOD_ID);
    @SuppressWarnings("unchecked")
    public static final DeferredRegister<NonStandPowerType<?>> POWERS = DeferredRegister.create(
            (Class<NonStandPowerType<?>>) ((Class<?>) NonStandPowerType.class), AddonMain.MOD_ID);
    
 // ======================================== Example Power ========================================
    
    
    // Create all the abilities here...
    public static final RegistryObject<TutorialAction> INVISIBLE = ACTIONS.register("tutorial_invisible",
            () -> new TutorialChangeEffects(
                    new TutorialAction.Builder().energyCost(15)
            )
    );

    public static final RegistryObject<TutorialAction> NIGHT_VISION = ACTIONS.register("tutorial_night_vision",
            () -> new TutorialChangeEffects(
                    new TutorialAction.Builder().energyCost(15).shiftVariationOf(INVISIBLE)
            )
    );

    public static final RegistryObject<TutorialAction> REGENERATION = ACTIONS.register("tutorial_regeneration",
            () -> new TutorialRegeneration(new TutorialAction.Builder().holdEnergyCost(10))
    );

    public static final RegistryObject<TutorialAction> ARMOR = ACTIONS.register("tutorial_armor",
            () -> new TutorialArmor(new TutorialAction.Builder().cooldown(100).energyCost(125))
    );

    public static final RegistryObject<TutorialPowerType> TUTORIAL = POWERS.register("tutorial",
            () -> new TutorialPowerType(
                    new TutorialAction[]{ // attacks
                            ARMOR.get()
//                            BARRAGE.get(),
                    },
                    new TutorialAction[]{ // abilities
                            INVISIBLE.get(),
                            REGENERATION.get(),
                    },
                    REGENERATION.get() // default MMB
            ).withColor(0xb9faca));
    

    
    // ======================================== ??? ========================================
    
    
    
}

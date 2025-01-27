package com.rotpaddon.exampleaddon.init

import com.github.standobyte.jojo.action.Action
import com.github.standobyte.jojo.power.impl.nonstand.type.NonStandPowerType
import com.rotpaddon.exampleaddon.AddonMain
import com.rotpaddon.exampleaddon.action.non_stand.TutorialAction
import com.rotpaddon.exampleaddon.action.non_stand.TutorialArmor
import com.rotpaddon.exampleaddon.action.non_stand.TutorialChangeEffects
import com.rotpaddon.exampleaddon.action.non_stand.TutorialRegeneration
import com.rotpaddon.exampleaddon.power.impl.nonstand.type.tutorial.TutorialPowerType
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.registries.DeferredRegister

object InitPowers {
    @Suppress("UNCHECKED_CAST")
    val ACTIONS: DeferredRegister<Action<*>> = DeferredRegister.create(
        Action::class.java,
        AddonMain.MOD_ID
    )

    @Suppress("UNCHECKED_CAST")
    val POWERS: DeferredRegister<NonStandPowerType<*>> = DeferredRegister.create(
        NonStandPowerType::class.java,
        AddonMain.MOD_ID
    )

    // ======================================== Example Power ========================================

    // Create all the abilities here...
    val INVISIBLE: RegistryObject<TutorialAction> = ACTIONS.register("tutorial_invisible") {
        TutorialChangeEffects(
            TutorialAction.Builder().energyCost(15f)
        )
    }

    val NIGHT_VISION: RegistryObject<TutorialAction> = ACTIONS.register("tutorial_night_vision") {
        TutorialChangeEffects(
            TutorialAction.Builder().energyCost(15f).shiftVariationOf(INVISIBLE)
        )
    }

    val REGENERATION: RegistryObject<TutorialAction> = ACTIONS.register("tutorial_regeneration") {
        TutorialRegeneration(TutorialAction.Builder().holdEnergyCost(10f))
    }

    val ARMOR: RegistryObject<TutorialAction> = ACTIONS.register("tutorial_armor") {
        TutorialArmor(TutorialAction.Builder().cooldown(100).energyCost(125f))
    }

    val TUTORIAL: RegistryObject<TutorialPowerType> = POWERS.register("tutorial") {
        TutorialPowerType(
            arrayOf( // attacks
                ARMOR.get(),
            ),
            arrayOf( // abilities
                INVISIBLE.get(),
                REGENERATION.get(),
            ),
            REGENERATION.get() // default MMB
        ).withColor(0xb9faca)
    }
}
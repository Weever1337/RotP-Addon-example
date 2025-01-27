package com.rotpaddon.exampleaddon

import com.rotpaddon.exampleaddon.init.InitPowers
import com.rotpaddon.exampleaddon.init.InitSounds
import com.rotpaddon.exampleaddon.network.AddonPackets
import net.minecraftforge.fml.common.Mod
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import thedarkcolour.kotlinforforge.KotlinModLoadingContext


@Mod(AddonMain.MOD_ID)
class AddonMain {
    init {
        InitSounds.SOUNDS.register(KotlinModLoadingContext.get().getKEventBus());
        InitPowers.ACTIONS.register(KotlinModLoadingContext.get().getKEventBus());
        InitPowers.POWERS.register(KotlinModLoadingContext.get().getKEventBus());
        AddonPackets.init();
    }

    companion object {
        const val MOD_ID = "myrotpaddon"
        const val MODNAME = "Rotp-Addon-Example"
        @JvmField val LOGGER: Logger = LoggerFactory.getLogger(MODNAME)
    }
}
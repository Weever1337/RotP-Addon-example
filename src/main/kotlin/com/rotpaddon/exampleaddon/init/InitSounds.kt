package com.rotpaddon.exampleaddon.init

import com.rotpaddon.exampleaddon.AddonMain
import net.minecraft.util.SoundEvent
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries

object InitSounds {
    val SOUNDS: DeferredRegister<SoundEvent> = DeferredRegister.create(
        ForgeRegistries.SOUND_EVENTS,
        AddonMain.MOD_ID
    ) // TODO sounds.json

    // Yes, you can use sounds, but not an ost.

    /*
    val EXAMPLE_STAND_SUMMON_VOICELINE: RegistryObject<SoundEvent> = SOUNDS.register("example_stand_summon_voiceline") {
        SoundEvent(ResourceLocation(AddonMain.MOD_ID, "example_stand_summon_voiceline"))
    }

    val EXAMPLE_STAND_SUMMON_SOUND: Supplier<SoundEvent> = ModSounds.STAND_SUMMON_DEFAULT

    val EXAMPLE_STAND_UNSUMMON_SOUND: Supplier<SoundEvent> = ModSounds.STAND_UNSUMMON_DEFAULT

    val EXAMPLE_STAND_PUNCH_LIGHT: Supplier<SoundEvent> = ModSounds.STAND_PUNCH_LIGHT

    val EXAMPLE_STAND_PUNCH_HEAVY: Supplier<SoundEvent> = ModSounds.STAND_PUNCH_HEAVY

    val EXAMPLE_STAND_PUNCH_BARRAGE: Supplier<SoundEvent> = ModSounds.STAND_PUNCH_LIGHT

    val EXAMPLE_STAND_THROW_PICKAXE: Supplier<SoundEvent> = ModSounds.STAND_PUNCH_LIGHT

    val EXAMPLE_STAND_OST = OstSoundList(
            ResourceLocation(AddonMain.MOD_ID, "example_stand_ost"), SOUNDS)
     */
}
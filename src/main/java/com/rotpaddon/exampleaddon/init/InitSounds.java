package com.rotpaddon.exampleaddon.init;

import com.rotpaddon.exampleaddon.AddonMain;

import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(
            ForgeRegistries.SOUND_EVENTS, AddonMain.MOD_ID); // TODO sounds.json
    
    // Yes, you can use sounds, but not an ost. 
    
//    public static final RegistryObject<SoundEvent> EXAMPLE_STAND_SUMMON_VOICELINE = SOUNDS.register("example_stand_summon_voiceline", 
//            () -> new SoundEvent(new ResourceLocation(AddonMain.MOD_ID, "example_stand_summon_voiceline")));
//
//    public static final Supplier<SoundEvent> EXAMPLE_STAND_SUMMON_SOUND = ModSounds.STAND_SUMMON_DEFAULT;
//    
//    public static final Supplier<SoundEvent> EXAMPLE_STAND_UNSUMMON_SOUND = ModSounds.STAND_UNSUMMON_DEFAULT;
//    
//    public static final Supplier<SoundEvent> EXAMPLE_STAND_PUNCH_LIGHT = ModSounds.STAND_PUNCH_LIGHT;
//    
//    public static final Supplier<SoundEvent> EXAMPLE_STAND_PUNCH_HEAVY = ModSounds.STAND_PUNCH_HEAVY;
//    
//    public static final Supplier<SoundEvent> EXAMPLE_STAND_PUNCH_BARRAGE = ModSounds.STAND_PUNCH_LIGHT;
//    
//    public static final Supplier<SoundEvent> EXAMPLE_STAND_THROW_PICKAXE = ModSounds.STAND_PUNCH_LIGHT;
//    
//    public static final OstSoundList EXAMPLE_STAND_OST = new OstSoundList(
//            new ResourceLocation(AddonMain.MOD_ID, "example_stand_ost"), SOUNDS);
}

package io.github.plainsvillager.anticreeper;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class AntiCreeper implements ModInitializer {

    public static final String MOD_ID = "anticreeper";

    public static final GameRules.Key<GameRules.BooleanRule> DO_CREEPERS_EXPLOSION_DESTROY = GameRuleRegistry.register("anticreeper_doCreepersExplosionDestroy", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(true));

    public static final GameRules.Key<GameRules.IntRule> FUSE_TIME = GameRuleRegistry.register("anticreeper_explosion_fuse_time", GameRules.Category.MOBS, GameRuleFactory.createIntRule(30));

    @Override
    public void onInitialize() {

    }
}

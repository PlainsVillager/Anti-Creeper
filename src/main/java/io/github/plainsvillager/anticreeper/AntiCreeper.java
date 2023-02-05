package io.github.plainsvillager.anticreeper;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
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
    public static World world;
    public static final GameRules.Key<GameRules.BooleanRule> DO_CREEPERS_EXPLOSION_DESTROY =
        GameRuleRegistry.register("doCreepersExplosionDestroy", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(true));


    @Override
    public void onInitialize() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            if(server.getWorld(RegistryKey.of(RegistryKeys.WORLD, new Identifier("dimension"))) != null){
                world = server.getWorld(RegistryKey.of(RegistryKeys.WORLD, new Identifier("dimension")));
            }
        });
    }
}

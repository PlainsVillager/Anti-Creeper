package io.github.plainsvillager.anticreeper;

import io.github.plainsvillager.anticreeper.mixin.ICreeperEntityFuseMixin;
import net.fabricmc.api.ModInitializer;

public class AntiCreeper implements ModInitializer {

    public static final String MOD_ID = "anticreeper";

    @Override
    public void onInitialize() {
        ICreeperEntityFuseMixin.setFuseTime(100);
    }
}

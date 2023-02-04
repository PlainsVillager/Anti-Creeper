package io.github.plainsvillager.anticreeper.mixin;

import net.minecraft.entity.mob.CreeperEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CreeperEntity.class)
public class ICreeperEntityFuseMixin {

    /**
     * @author
     * @reason
     */
    @Accessor("fuseTime")
    @Mutable
    public static void setFuseTime(int fuseTime){};
}

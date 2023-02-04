package io.github.plainsvillager.anticreeper.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin extends LivingEntity {

    public CreeperEntityMixin(EntityType<? extends CreeperEntity> entityType, World world) {
        super(entityType, world);
    }


    /**
     * @author PlainsVillager
     * @reason L
     */
    @Overwrite
    public void explode() {
        if (!this.world.isClient) {
            this.dead = true;
            this.world.createExplosion(this, this.getX(), this.getY(), this.getZ(), 6.0F, World.ExplosionSourceType.NONE);
            this.discard();
        }
    }
}

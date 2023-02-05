package io.github.plainsvillager.anticreeper.mixin;

import io.github.plainsvillager.anticreeper.AntiCreeper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.github.plainsvillager.anticreeper.AntiCreeper.DO_CREEPERS_EXPLOSION_DESTROY;

@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin extends LivingEntity {

    @Shadow
    private int fuseTime;

    private Explosion gameRule;

    public CreeperEntityMixin(EntityType<? extends CreeperEntity> entityType, World world) {
        super(entityType, world);
    }


    /**
     * @author PlainsVillager
     * @reason
     */
    @Overwrite
    public void explode() {
        if (!this.world.isClient) {
            this.dead = true;
            if (!this.world.getGameRules().getBoolean(DO_CREEPERS_EXPLOSION_DESTROY)) {
                this.world.createExplosion(this, this.getX(), this.getY(), this.getZ(), 6.0F, World.ExplosionSourceType.NONE);
            } else {
                this.world.createExplosion(this, this.getX(), this.getY(), this.getZ(), 6.0F, World.ExplosionSourceType.TNT); // here ain't any wrong!
            }
            this.discard();
        }
    }

    /**
     * this make the pre explosion time became longer
     *
     * @param ci
     * @author PlainsVillager
     */
    @Inject(at = @At("TAIL"), method = "<init>")
    public void injectTo(CallbackInfo ci) {
        fuseTime = 60;
    }

}

package io.github.plainsvillager.anti_creeper.mixin;

import io.github.plainsvillager.anti_creeper.AntiCreeper;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;
import java.util.Iterator;

import static io.github.plainsvillager.anti_creeper.AntiCreeper.DO_CREEPERS_EXPLOSION_DESTROY;

@Mixin(CreeperEntity.class)
@SuppressWarnings("UNUSED")
public abstract class CreeperEntityMixin extends LivingEntity {

    @Shadow
    private int fuseTime;

    @Shadow
    private int explosionRadius;

    @Shadow @Final private static TrackedData<Boolean> CHARGED;

    public CreeperEntityMixin(EntityType<? extends CreeperEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "explode", at = @At("HEAD"), cancellable = true)
    private void explode(CallbackInfo ci) {
        ci.cancel();
        if(!this.getWorld().isClient()) {
            float f = this.dataTracker.get(CHARGED) ? 2.0F : 1.0F;
            this.dead = true;
            if (!this.getWorld().getGameRules().getBoolean(DO_CREEPERS_EXPLOSION_DESTROY)) {
                this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionRadius * f, World.ExplosionSourceType.NONE);
            } else {
                this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionRadius * f, World.ExplosionSourceType.TNT); // here ain't any wrong!
            }
            this.discard();

            // spawn effect cloud
            Collection<StatusEffectInstance> collection = this.getStatusEffects();
            if (!collection.isEmpty()) {
                AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(this.getWorld(), this.getX(), this.getY(), this.getZ());
                areaEffectCloudEntity.setRadius(2.5F);
                areaEffectCloudEntity.setRadiusOnUse(-0.5F);
                areaEffectCloudEntity.setWaitTime(10);
                areaEffectCloudEntity.setDuration(areaEffectCloudEntity.getDuration() / 2);
                areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float)areaEffectCloudEntity.getDuration());
                Iterator var3 = collection.iterator();

                while(var3.hasNext()) {
                    StatusEffectInstance statusEffectInstance = (StatusEffectInstance)var3.next();
                    areaEffectCloudEntity.addEffect(new StatusEffectInstance(statusEffectInstance));
                }

                this.getWorld().spawnEntity(areaEffectCloudEntity);
            }
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
        fuseTime = this.getWorld().getGameRules().getInt(AntiCreeper.FUSE_TIME);
    }

}

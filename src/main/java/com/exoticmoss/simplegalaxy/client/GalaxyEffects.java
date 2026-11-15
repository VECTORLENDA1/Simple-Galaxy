package com.exoticmoss.simplegalaxy.client;

import com.exoticmoss.simplegalaxy.SimpleGalaxy;
import com.exoticmoss.simplegalaxy.worldgen.ModDimensions;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterDimensionSpecialEffectsEvent;

//How the sky of the Galaxy dimension looks: eternal starry night, no clouds, fog that keeps the biome colour
public class GalaxyEffects extends DimensionSpecialEffects {
    public GalaxyEffects() {
        super(Float.NaN, true, SkyType.NORMAL, false, false);
    }

    @Override
    public Vec3 getBrightnessDependentFogColor(Vec3 fogColor, float brightness) {
        return fogColor; //not darkened at night, otherwise the nebula colours would turn black
    }

    @Override
    public boolean isFoggyAt(int x, int y) {
        return false;
    }

    @EventBusSubscriber(modid = SimpleGalaxy.MODID, value = Dist.CLIENT)
    public static class Registration {
        @SubscribeEvent
        public static void registerEffects(RegisterDimensionSpecialEffectsEvent event) {
            event.register(ModDimensions.GALAXY_ID, new GalaxyEffects());
        }
    }
}

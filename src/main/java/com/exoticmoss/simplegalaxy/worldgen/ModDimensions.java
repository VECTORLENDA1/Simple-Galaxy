package com.exoticmoss.simplegalaxy.worldgen;

import com.exoticmoss.simplegalaxy.SimpleGalaxy;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

//Keys of the Galaxy dimension. The dimension itself is defined in data/simplegalaxy/dimension(_type)/galaxy.json
//and its terrain in data/simplegalaxy/worldgen/noise_settings/galaxy.json
public class ModDimensions {
    public static final ResourceLocation GALAXY_ID = SimpleGalaxy.id("galaxy");
    public static final ResourceKey<Level> GALAXY_LEVEL = ResourceKey.create(Registries.DIMENSION, GALAXY_ID);
    public static final ResourceKey<DimensionType> GALAXY_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, GALAXY_ID);
}

package com.exoticmoss.simplegalaxy.worldgen;

import com.exoticmoss.simplegalaxy.SimpleGalaxy;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.AmbientParticleSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

//The biomes of the Galaxy dimension. Which biome goes where is decided in data/simplegalaxy/dimension/galaxy.json
public class ModBiomes {
    public static final ResourceKey<Biome> STARLIT_PLAINS = key("starlit_plains");
    public static final ResourceKey<Biome> NEBULA_FIELDS = key("nebula_fields");

    public static void bootstrap(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> features = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carvers = context.lookup(Registries.CONFIGURED_CARVER);

        //Dark blue sky, the "normal" biome
        context.register(STARLIT_PLAINS, biome(ores(features, carvers, false), 0x05061A, 0x0B0D2E, false));
        //Purple sky, the only place where nebulite generates
        context.register(NEBULA_FIELDS, biome(ores(features, carvers, true), 0x1A0630, 0x2A0A45, true));
    }

    //IMPORTANT: features shared by several biomes must always be added in the same order
    private static BiomeGenerationSettings.Builder ores(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers, boolean nebulite) {
        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(features, carvers);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.COSMIC_IRON_ORE);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.COSMIC_GOLD_ORE);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.COSMIC_DIAMOND_ORE);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.COSMIC_EMERALD_ORE);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.STARDUST_ORE);
        if (nebulite) {
            generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.NEBULITE_ORE);
        }
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.EMPYRIUM_ORE);
        return generation;
    }

    private static Biome biome(BiomeGenerationSettings.Builder generation, int skyColor, int fogColor, boolean nebulaParticles) {
        //Placeholder inhabitants until the mod has its own entities
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 10, 1, 2));

        BiomeSpecialEffects.Builder effects = new BiomeSpecialEffects.Builder()
                .skyColor(skyColor)
                .fogColor(fogColor)
                .waterColor(0x3F76E4)
                .waterFogColor(0x050533);
        if (nebulaParticles) {
            effects.ambientParticle(new AmbientParticleSettings(ParticleTypes.PORTAL, 0.004f));
        } else {
            effects.ambientParticle(new AmbientParticleSettings(ParticleTypes.END_ROD, 0.0006f));
        }

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(0.5f)
                .downfall(0f)
                .specialEffects(effects.build())
                .mobSpawnSettings(spawns.build())
                .generationSettings(generation.build())
                .build();
    }

    private static ResourceKey<Biome> key(String name) {
        return ResourceKey.create(Registries.BIOME, SimpleGalaxy.id(name));
    }
}

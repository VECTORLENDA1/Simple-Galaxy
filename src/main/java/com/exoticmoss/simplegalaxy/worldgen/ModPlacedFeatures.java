package com.exoticmoss.simplegalaxy.worldgen;

import com.exoticmoss.simplegalaxy.SimpleGalaxy;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

//Where each ore generates: how many veins per chunk and between which heights
public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> COSMIC_IRON_ORE = key("cosmic_iron_ore");
    public static final ResourceKey<PlacedFeature> COSMIC_GOLD_ORE = key("cosmic_gold_ore");
    public static final ResourceKey<PlacedFeature> COSMIC_DIAMOND_ORE = key("cosmic_diamond_ore");
    public static final ResourceKey<PlacedFeature> COSMIC_EMERALD_ORE = key("cosmic_emerald_ore");
    public static final ResourceKey<PlacedFeature> STARDUST_ORE = key("stardust_ore");
    public static final ResourceKey<PlacedFeature> NEBULITE_ORE = key("nebulite_ore");
    public static final ResourceKey<PlacedFeature> EMPYRIUM_ORE = key("empyrium_ore");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> features = context.lookup(Registries.CONFIGURED_FEATURE);

        //triangle = most common in the middle of the range, uniform = same chance at every height
        context.register(COSMIC_IRON_ORE, new PlacedFeature(features.getOrThrow(ModConfiguredFeatures.COSMIC_IRON_ORE),
                ore(14, HeightRangePlacement.triangle(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(96)))));
        context.register(COSMIC_GOLD_ORE, new PlacedFeature(features.getOrThrow(ModConfiguredFeatures.COSMIC_GOLD_ORE),
                ore(6, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(32)))));
        context.register(COSMIC_DIAMOND_ORE, new PlacedFeature(features.getOrThrow(ModConfiguredFeatures.COSMIC_DIAMOND_ORE),
                ore(5, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));
        context.register(COSMIC_EMERALD_ORE, new PlacedFeature(features.getOrThrow(ModConfiguredFeatures.COSMIC_EMERALD_ORE),
                ore(4, HeightRangePlacement.uniform(VerticalAnchor.absolute(48), VerticalAnchor.absolute(160)))));
        context.register(STARDUST_ORE, new PlacedFeature(features.getOrThrow(ModConfiguredFeatures.STARDUST_ORE),
                ore(12, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(160)))));
        context.register(NEBULITE_ORE, new PlacedFeature(features.getOrThrow(ModConfiguredFeatures.NEBULITE_ORE),
                ore(7, HeightRangePlacement.triangle(VerticalAnchor.absolute(-48), VerticalAnchor.absolute(48)))));
        context.register(EMPYRIUM_ORE, new PlacedFeature(features.getOrThrow(ModConfiguredFeatures.EMPYRIUM_ORE),
                ore(2, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(-32)))));
    }

    private static List<PlacementModifier> ore(int veinsPerChunk, PlacementModifier height) {
        return List.of(CountPlacement.of(veinsPerChunk), InSquarePlacement.spread(), height, BiomeFilter.biome());
    }

    private static ResourceKey<PlacedFeature> key(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, SimpleGalaxy.id(name));
    }
}

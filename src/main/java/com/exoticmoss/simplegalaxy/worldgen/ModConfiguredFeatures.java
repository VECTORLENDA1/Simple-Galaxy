package com.exoticmoss.simplegalaxy.worldgen;

import com.exoticmoss.simplegalaxy.SimpleGalaxy;
import com.exoticmoss.simplegalaxy.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.function.Supplier;

//What each ore vein looks like: which block, vein size, and how much of it is hidden when touching air
public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> COSMIC_IRON_ORE = key("cosmic_iron_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COSMIC_GOLD_ORE = key("cosmic_gold_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COSMIC_DIAMOND_ORE = key("cosmic_diamond_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COSMIC_EMERALD_ORE = key("cosmic_emerald_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STARDUST_ORE = key("stardust_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NEBULITE_ORE = key("nebulite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> EMPYRIUM_ORE = key("empyrium_ore");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        ore(context, COSMIC_IRON_ORE, ModBlocks.COSMIC_IRON_ORE, 10, 0f);
        ore(context, COSMIC_GOLD_ORE, ModBlocks.COSMIC_GOLD_ORE, 9, 0f);
        ore(context, COSMIC_DIAMOND_ORE, ModBlocks.COSMIC_DIAMOND_ORE, 7, 0.3f);
        ore(context, COSMIC_EMERALD_ORE, ModBlocks.COSMIC_EMERALD_ORE, 4, 0f);
        ore(context, STARDUST_ORE, ModBlocks.STARDUST_ORE, 8, 0f);
        ore(context, NEBULITE_ORE, ModBlocks.NEBULITE_ORE, 6, 0.2f);
        ore(context, EMPYRIUM_ORE, ModBlocks.EMPYRIUM_ORE, 3, 0.7f);
    }

    private static void ore(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key,
                            Supplier<? extends Block> ore, int veinSize, float discardChanceOnAirExposure) {
        RuleTest cosmicStone = new BlockMatchTest(ModBlocks.COSMIC_STONE.get());
        context.register(key, new ConfiguredFeature<>(Feature.ORE,
                new OreConfiguration(cosmicStone, ore.get().defaultBlockState(), veinSize, discardChanceOnAirExposure)));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> key(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, SimpleGalaxy.id(name));
    }
}

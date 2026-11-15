package com.exoticmoss.simplegalaxy.datagen;

import com.exoticmoss.simplegalaxy.SimpleGalaxy;
import com.exoticmoss.simplegalaxy.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, SimpleGalaxy.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.COSMIC_STONE.get())
                .add(ModBlocks.COSMIC_IRON_ORE.get())
                .add(ModBlocks.COSMIC_GOLD_ORE.get())
                .add(ModBlocks.COSMIC_DIAMOND_ORE.get())
                .add(ModBlocks.COSMIC_EMERALD_ORE.get())
                .add(ModBlocks.STARDUST_ORE.get())
                .add(ModBlocks.NEBULITE_ORE.get())
                .add(ModBlocks.EMPYRIUM_ORE.get())
                .add(ModBlocks.RAW_NEBULITE_BLOCK.get())
                .add(ModBlocks.NEBULITE_BLOCK.get())
                .add(ModBlocks.EMPYRIUM_BLOCK.get());

        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.ASTRAL_REGOLITH.get());

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.COSMIC_IRON_ORE.get())
                .add(ModBlocks.STARDUST_ORE.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.COSMIC_GOLD_ORE.get())
                .add(ModBlocks.COSMIC_DIAMOND_ORE.get())
                .add(ModBlocks.COSMIC_EMERALD_ORE.get())
                .add(ModBlocks.NEBULITE_ORE.get())
                .add(ModBlocks.RAW_NEBULITE_BLOCK.get())
                .add(ModBlocks.NEBULITE_BLOCK.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.EMPYRIUM_ORE.get())
                .add(ModBlocks.EMPYRIUM_BLOCK.get());

        tag(Tags.Blocks.ORES)
                .add(ModBlocks.COSMIC_IRON_ORE.get())
                .add(ModBlocks.COSMIC_GOLD_ORE.get())
                .add(ModBlocks.COSMIC_DIAMOND_ORE.get())
                .add(ModBlocks.COSMIC_EMERALD_ORE.get())
                .add(ModBlocks.STARDUST_ORE.get())
                .add(ModBlocks.NEBULITE_ORE.get())
                .add(ModBlocks.EMPYRIUM_ORE.get());
        tag(BlockTags.IRON_ORES).add(ModBlocks.COSMIC_IRON_ORE.get());
        tag(BlockTags.GOLD_ORES).add(ModBlocks.COSMIC_GOLD_ORE.get());
        tag(BlockTags.DIAMOND_ORES).add(ModBlocks.COSMIC_DIAMOND_ORE.get());
        tag(BlockTags.EMERALD_ORES).add(ModBlocks.COSMIC_EMERALD_ORE.get());

        tag(BlockTags.BEACON_BASE_BLOCKS)
                .add(ModBlocks.NEBULITE_BLOCK.get())
                .add(ModBlocks.EMPYRIUM_BLOCK.get());
    }
}

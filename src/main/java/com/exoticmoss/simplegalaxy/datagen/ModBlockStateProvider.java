package com.exoticmoss.simplegalaxy.datagen;

import com.exoticmoss.simplegalaxy.SimpleGalaxy;
import com.exoticmoss.simplegalaxy.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

//Every block of the mod is a simple cube with the same texture on all sides (textures/block/<name>.png)
public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SimpleGalaxy.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (Holder<Block> block : ModBlocks.BLOCKS.getEntries()) {
            simpleBlockWithItem(block.value(), cubeAll(block.value()));
        }
    }
}

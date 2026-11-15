package com.exoticmoss.simplegalaxy.datagen;

import com.exoticmoss.simplegalaxy.SimpleGalaxy;
import com.exoticmoss.simplegalaxy.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SimpleGalaxy.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.STARDUST.get());
        basicItem(ModItems.RAW_NEBULITE.get());
        basicItem(ModItems.NEBULITE.get());
        basicItem(ModItems.EMPYRIUM.get());
        basicItem(ModItems.ASTRAL_COMPASS.get());
    }
}

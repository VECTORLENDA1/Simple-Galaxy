package com.exoticmoss.simplegalaxy;

import com.exoticmoss.simplegalaxy.block.ModBlocks;
import com.exoticmoss.simplegalaxy.item.ModCreativeModTabs;
import com.exoticmoss.simplegalaxy.item.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(SimpleGalaxy.MODID)
public class SimpleGalaxy {
    public static final String MODID = "simplegalaxy";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SimpleGalaxy(IEventBus modEventBus, ModContainer modContainer) {
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModCreativeModTabs.register(modEventBus);
    }

    //Shortcut for "simplegalaxy:<path>"
    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}

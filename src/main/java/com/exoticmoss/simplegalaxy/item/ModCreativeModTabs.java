package com.exoticmoss.simplegalaxy.item;

import com.exoticmoss.simplegalaxy.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.exoticmoss.simplegalaxy.SimpleGalaxy.MODID;


//Creates the mod's tab in the creative inventory, with all its items
public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SIMPLE_GALAXY = CREATIVE_MODE_TABS.register("simplegalaxy",
            () -> CreativeModeTab.builder()
            .title(Component.translatable("creativetab.simplegalaxy"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ModItems.EMPYRIUM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {

                    //ITEMS//
                    output.accept(ModItems.ASTRAL_COMPASS.get());
                    output.accept(ModItems.STARDUST.get());
                    output.accept(ModItems.RAW_NEBULITE.get());
                    output.accept(ModItems.NEBULITE.get());
                    output.accept(ModItems.EMPYRIUM.get());

                    //BLOCKS//
                    output.accept(ModBlocks.COSMIC_STONE.get());
                    output.accept(ModBlocks.ASTRAL_REGOLITH.get());
                    output.accept(ModBlocks.COSMIC_IRON_ORE.get());
                    output.accept(ModBlocks.COSMIC_GOLD_ORE.get());
                    output.accept(ModBlocks.COSMIC_DIAMOND_ORE.get());
                    output.accept(ModBlocks.COSMIC_EMERALD_ORE.get());
                    output.accept(ModBlocks.STARDUST_ORE.get());
                    output.accept(ModBlocks.NEBULITE_ORE.get());
                    output.accept(ModBlocks.EMPYRIUM_ORE.get());
                    output.accept(ModBlocks.RAW_NEBULITE_BLOCK.get());
                    output.accept(ModBlocks.NEBULITE_BLOCK.get());
                    output.accept(ModBlocks.EMPYRIUM_BLOCK.get());
            }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}

package com.exoticmoss.simplegalaxy.item;

import com.exoticmoss.simplegalaxy.item.custom.AstralCompassItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.exoticmoss.simplegalaxy.SimpleGalaxy.MODID;


public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    //MATERIALS//
    public static final DeferredItem<Item> STARDUST = ITEMS.registerSimpleItem("stardust");
    public static final DeferredItem<Item> RAW_NEBULITE = ITEMS.registerSimpleItem("raw_nebulite");
    public static final DeferredItem<Item> NEBULITE = ITEMS.registerSimpleItem("nebulite");
    public static final DeferredItem<Item> EMPYRIUM = ITEMS.registerSimpleItem("empyrium",
            new Item.Properties().rarity(Rarity.RARE).fireResistant());

    //TOOLS//
    public static final DeferredItem<AstralCompassItem> ASTRAL_COMPASS = ITEMS.registerItem("astral_compass",
            AstralCompassItem::new, new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

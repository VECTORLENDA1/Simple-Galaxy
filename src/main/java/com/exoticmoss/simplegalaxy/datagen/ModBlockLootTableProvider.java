package com.exoticmoss.simplegalaxy.datagen;

import com.exoticmoss.simplegalaxy.block.ModBlocks;
import com.exoticmoss.simplegalaxy.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

//What each block drops when mined
public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.COSMIC_STONE.get());
        dropSelf(ModBlocks.ASTRAL_REGOLITH.get());
        dropSelf(ModBlocks.RAW_NEBULITE_BLOCK.get());
        dropSelf(ModBlocks.NEBULITE_BLOCK.get());
        dropSelf(ModBlocks.EMPYRIUM_BLOCK.get());

        //The remakes are richer than the Overworld ores: 2-3 raw metal, 1-2 gems (Fortune also works)
        add(ModBlocks.COSMIC_IRON_ORE.get(), block -> multipleOreDrops(block, Items.RAW_IRON, 2, 3));
        add(ModBlocks.COSMIC_GOLD_ORE.get(), block -> multipleOreDrops(block, Items.RAW_GOLD, 2, 3));
        add(ModBlocks.COSMIC_DIAMOND_ORE.get(), block -> multipleOreDrops(block, Items.DIAMOND, 1, 2));
        add(ModBlocks.COSMIC_EMERALD_ORE.get(), block -> multipleOreDrops(block, Items.EMERALD, 1, 2));

        add(ModBlocks.STARDUST_ORE.get(), block -> multipleOreDrops(block, ModItems.STARDUST.get(), 2, 5));
        add(ModBlocks.NEBULITE_ORE.get(), block -> createOreDrop(block, ModItems.RAW_NEBULITE.get()));
        add(ModBlocks.EMPYRIUM_ORE.get(), block -> createOreDrop(block, ModItems.EMPYRIUM.get()));
    }

    //Drops between min and max items (more with Fortune), or the ore itself with Silk Touch
    private LootTable.Builder multipleOreDrops(Block block, Item item, float min, float max) {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return createSilkTouchDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(item)
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                .apply(ApplyBonusCount.addOreBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}

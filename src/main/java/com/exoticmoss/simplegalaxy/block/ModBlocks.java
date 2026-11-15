package com.exoticmoss.simplegalaxy.block;

import com.exoticmoss.simplegalaxy.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

import static com.exoticmoss.simplegalaxy.SimpleGalaxy.MODID;


public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);

    //TERRAIN OF THE GALAXY DIMENSION//
    public static final DeferredBlock<Block> COSMIC_STONE = registerBlockItem("cosmic_stone", Block::new,
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE)
                    .strength(2f, 6).requiresCorrectToolForDrops().sound(SoundType.STONE));
    public static final DeferredBlock<Block> ASTRAL_REGOLITH = registerBlockItem("astral_regolith", Block::new,
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE)
                    .strength(0.6f).sound(SoundType.SAND));

    //REMAKES OF THE VANILLA ORES (found in cosmic stone, richer than the Overworld ones)//
    public static final DeferredBlock<Block> COSMIC_IRON_ORE = registerBlockItem("cosmic_iron_ore", Block::new,
            ModBlocks::cosmicOre);
    public static final DeferredBlock<Block> COSMIC_GOLD_ORE = registerBlockItem("cosmic_gold_ore", Block::new,
            ModBlocks::cosmicOre);
    public static final DeferredBlock<Block> COSMIC_DIAMOND_ORE = registerBlockItem("cosmic_diamond_ore", p -> new DropExperienceBlock(UniformInt.of(3, 7), p),
            ModBlocks::cosmicOre);
    public static final DeferredBlock<Block> COSMIC_EMERALD_ORE = registerBlockItem("cosmic_emerald_ore", p -> new DropExperienceBlock(UniformInt.of(3, 7), p),
            ModBlocks::cosmicOre);

    //NEW ORES//
    public static final DeferredBlock<Block> STARDUST_ORE = registerBlockItem("stardust_ore", p -> new DropExperienceBlock(UniformInt.of(1, 4), p),
            () -> cosmicOre().lightLevel(state -> 4));
    public static final DeferredBlock<Block> NEBULITE_ORE = registerBlockItem("nebulite_ore", Block::new,
            ModBlocks::cosmicOre);
    public static final DeferredBlock<Block> EMPYRIUM_ORE = registerBlockItem("empyrium_ore", p -> new DropExperienceBlock(UniformInt.of(6, 12), p),
            () -> cosmicOre().strength(5f, 9).lightLevel(state -> 7));

    //STORAGE BLOCKS//
    public static final DeferredBlock<Block> RAW_NEBULITE_BLOCK = registerBlockItem("raw_nebulite_block", Block::new,
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_MAGENTA)
                    .strength(5f, 6).requiresCorrectToolForDrops().sound(SoundType.STONE));
    public static final DeferredBlock<Block> NEBULITE_BLOCK = registerBlockItem("nebulite_block", Block::new,
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_MAGENTA)
                    .strength(5f, 6).requiresCorrectToolForDrops().sound(SoundType.METAL));
    public static final DeferredBlock<Block> EMPYRIUM_BLOCK = registerBlockItem("empyrium_block", Block::new,
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.GOLD)
                    .strength(6f, 9).requiresCorrectToolForDrops().sound(SoundType.AMETHYST).lightLevel(state -> 10));


    //Properties shared by every ore that generates in cosmic stone
    private static BlockBehaviour.Properties cosmicOre() {
        return BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE)
                .strength(3f, 6).requiresCorrectToolForDrops().sound(SoundType.STONE);
    }

    //Registers a block together with its item
    private static <T extends Block> DeferredBlock<T> registerBlockItem(String name, Function<BlockBehaviour.Properties, T> factory,
                                                                       Supplier<BlockBehaviour.Properties> properties) {
        DeferredBlock<T> block = BLOCKS.register(name, () -> factory.apply(properties.get()));
        ModItems.ITEMS.registerSimpleBlockItem(name, block);
        return block;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}

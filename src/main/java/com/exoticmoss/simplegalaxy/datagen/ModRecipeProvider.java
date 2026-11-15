package com.exoticmoss.simplegalaxy.datagen;

import com.exoticmoss.simplegalaxy.SimpleGalaxy;
import com.exoticmoss.simplegalaxy.block.ModBlocks;
import com.exoticmoss.simplegalaxy.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        //The way into the Galaxy dimension
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.ASTRAL_COMPASS.get())
                .pattern(" A ")
                .pattern("ACA")
                .pattern(" E ")
                .define('A', Items.AMETHYST_SHARD)
                .define('C', Items.COMPASS)
                .define('E', Items.ENDER_EYE)
                .unlockedBy("has_ender_eye", has(Items.ENDER_EYE))
                .save(output);

        //Furnace and blast furnace
        smeltAndBlast(output, ModBlocks.COSMIC_IRON_ORE, Items.IRON_INGOT, 0.7f, "cosmic_iron_ore");
        smeltAndBlast(output, ModBlocks.COSMIC_GOLD_ORE, Items.GOLD_INGOT, 1.0f, "cosmic_gold_ore");
        smeltAndBlast(output, ModBlocks.COSMIC_DIAMOND_ORE, Items.DIAMOND, 1.0f, "cosmic_diamond_ore");
        smeltAndBlast(output, ModBlocks.COSMIC_EMERALD_ORE, Items.EMERALD, 1.0f, "cosmic_emerald_ore");
        smeltAndBlast(output, ModBlocks.STARDUST_ORE, ModItems.STARDUST, 0.5f, "stardust_ore");
        smeltAndBlast(output, ModBlocks.NEBULITE_ORE, ModItems.NEBULITE, 1.0f, "nebulite_ore");
        smeltAndBlast(output, ModItems.RAW_NEBULITE, ModItems.NEBULITE, 1.0f, "raw_nebulite");
        smeltAndBlast(output, ModBlocks.EMPYRIUM_ORE, ModItems.EMPYRIUM, 2.0f, "empyrium_ore");

        //9 items <-> 1 block
        storageBlock(output, ModItems.RAW_NEBULITE, ModBlocks.RAW_NEBULITE_BLOCK, "raw_nebulite");
        storageBlock(output, ModItems.NEBULITE, ModBlocks.NEBULITE_BLOCK, "nebulite");
        storageBlock(output, ModItems.EMPYRIUM, ModBlocks.EMPYRIUM_BLOCK, "empyrium");
    }

    private static void smeltAndBlast(RecipeOutput output, ItemLike input, ItemLike result, float experience, String inputName) {
        String resultName = getItemName(result);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), RecipeCategory.MISC, result, experience, 200)
                .group(resultName)
                .unlockedBy("has_" + inputName, has(input))
                .save(output, SimpleGalaxy.id(resultName + "_from_smelting_" + inputName));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(input), RecipeCategory.MISC, result, experience, 100)
                .group(resultName)
                .unlockedBy("has_" + inputName, has(input))
                .save(output, SimpleGalaxy.id(resultName + "_from_blasting_" + inputName));
    }

    private static void storageBlock(RecipeOutput output, ItemLike item, ItemLike block, String itemName) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', item)
                .unlockedBy("has_" + itemName, has(item))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, item, 9)
                .requires(block)
                .unlockedBy("has_" + itemName + "_block", has(block))
                .save(output, SimpleGalaxy.id(itemName + "_from_block"));
    }
}

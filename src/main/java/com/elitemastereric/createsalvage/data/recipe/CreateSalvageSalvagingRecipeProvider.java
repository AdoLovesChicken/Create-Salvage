package com.elitemastereric.createsalvage.data.recipe;

import com.elitemastereric.createsalvage.CreateSalvage;
import com.elitemastereric.createsalvage.ModItems;
import com.elitemastereric.createsalvage.SalvagingRecipeTypes;
import com.elitemastereric.createsalvage.api.data.recipe.SalvagingRecipeGen;
import com.elitemastereric.createsalvage.content.salvaging.SalvagingRecipeParams;
import com.simibubi.create.AllItems;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unused")
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public final class CreateSalvageSalvagingRecipeProvider extends SalvagingRecipeGen {
    /**
     * SALVAGE MATERIALS
     */
    public static final SalvagingRecipeParams.SalvagingMaterial MATERIAL_IRON = new SalvagingRecipeParams.SalvagingMaterial(Items.IRON_INGOT, Items.IRON_NUGGET, 9);
    public static final SalvagingRecipeParams.SalvagingMaterial MATERIAL_COPPER = new SalvagingRecipeParams.SalvagingMaterial(Items.COPPER_INGOT, AllItems.COPPER_NUGGET.get(), 9);
    public static final SalvagingRecipeParams.SalvagingMaterial MATERIAL_GOLD = new SalvagingRecipeParams.SalvagingMaterial(Items.GOLD_INGOT, Items.GOLD_NUGGET, 9);
    public static final SalvagingRecipeParams.SalvagingMaterial MATERIAL_DIAMOND = new SalvagingRecipeParams.SalvagingMaterial(Items.DIAMOND, ModItems.DIAMOND_NUGGET.get(), 9);
    public static final SalvagingRecipeParams.SalvagingMaterial MATERIAL_NETHERITE = new SalvagingRecipeParams.SalvagingMaterial(Items.NETHERITE_INGOT, Items.NETHERITE_SCRAP, 4);
    public static final SalvagingRecipeParams.SalvagingMaterial MATERIAL_CARDBOARD = new SalvagingRecipeParams.SalvagingMaterial(AllItems.CARDBOARD.get());
    public static final SalvagingRecipeParams.SalvagingMaterial MATERIAL_CHAIN = new SalvagingRecipeParams.SalvagingMaterial(Blocks.CHAIN.asItem());

    /**
     * SALVAGE RECIPES
     */
    GeneratedRecipe IRON_HELMET = heated(Items.IRON_HELMET, MATERIAL_IRON, 5);
    GeneratedRecipe IRON_CHESTPLATE = heated(Items.IRON_CHESTPLATE, MATERIAL_IRON, 8);
    GeneratedRecipe IRON_LEGGINGS = heated(Items.IRON_LEGGINGS, MATERIAL_IRON, 7);
    GeneratedRecipe IRON_BOOTS = heated(Items.IRON_BOOTS, MATERIAL_IRON, 4);
    GeneratedRecipe IRON_PICKAXE = heated(Items.IRON_PICKAXE, MATERIAL_IRON, 3);
    GeneratedRecipe IRON_AXE = heated(Items.IRON_AXE, MATERIAL_IRON, 3);
    GeneratedRecipe IRON_SHOVEL = heated(Items.IRON_SHOVEL, MATERIAL_IRON, 1);
    GeneratedRecipe IRON_HOE = heated(Items.IRON_HOE, MATERIAL_IRON, 2);
    GeneratedRecipe IRON_SWORD = heated(Items.IRON_SWORD, MATERIAL_IRON, 2);

    GeneratedRecipe IRON_HELMET_SUPERHEATED = superheated(Items.IRON_HELMET, MATERIAL_IRON, 5, 1F);
    GeneratedRecipe IRON_CHESTPLATE_SUPERHEATED = superheated(Items.IRON_CHESTPLATE, MATERIAL_IRON, 8, 1F);
    GeneratedRecipe IRON_LEGGINGS_SUPERHEATED = superheated(Items.IRON_LEGGINGS, MATERIAL_IRON, 7, 1F);
    GeneratedRecipe IRON_BOOTS_SUPERHEATED = superheated(Items.IRON_BOOTS, MATERIAL_IRON, 4, 1F);
    GeneratedRecipe IRON_PICKAXE_SUPERHEATED = superheated(Items.IRON_PICKAXE, MATERIAL_IRON, 3, 1F);
    GeneratedRecipe IRON_AXE_SUPERHEATED = superheated(Items.IRON_AXE, MATERIAL_IRON, 3, 1F);
    GeneratedRecipe IRON_SHOVEL_SUPERHEATED = superheated(Items.IRON_SHOVEL, MATERIAL_IRON, 1, 1F);
    GeneratedRecipe IRON_HOE_SUPERHEATED = superheated(Items.IRON_HOE, MATERIAL_IRON, 2, 1F);
    GeneratedRecipe IRON_SWORD_SUPERHEATED = superheated(Items.IRON_SWORD, MATERIAL_IRON, 2, 1F);

    GeneratedRecipe CHAINMAIL_HELMET = heated(Items.CHAINMAIL_HELMET, MATERIAL_CHAIN, 5);
    GeneratedRecipe CHAINMAIL_CHESTPLATE = heated(Items.CHAINMAIL_CHESTPLATE, MATERIAL_CHAIN, 8);
    GeneratedRecipe CHAINMAIL_LEGGINGS = heated(Items.CHAINMAIL_LEGGINGS, MATERIAL_CHAIN, 7);
    GeneratedRecipe CHAINMAIL_BOOTS = heated(Items.CHAINMAIL_BOOTS, MATERIAL_CHAIN, 4);

    GeneratedRecipe CHAINMAIL_HELMET_SUPERHEATED = superheated(Items.CHAINMAIL_HELMET, MATERIAL_CHAIN, 5, 1F);
    GeneratedRecipe CHAINMAIL_CHESTPLATE_SUPERHEATED = superheated(Items.CHAINMAIL_CHESTPLATE, MATERIAL_CHAIN, 8, 1F);
    GeneratedRecipe CHAINMAIL_LEGGINGS_SUPERHEATED = superheated(Items.CHAINMAIL_LEGGINGS, MATERIAL_CHAIN, 7, 1F);
    GeneratedRecipe CHAINMAIL_BOOTS_SUPERHEATED = superheated(Items.CHAINMAIL_BOOTS, MATERIAL_CHAIN, 4, 1F);

    GeneratedRecipe GOLDEN_HELMET = heated(Items.GOLDEN_HELMET, MATERIAL_GOLD, 5);
    GeneratedRecipe GOLDEN_CHESTPLATE = heated(Items.GOLDEN_CHESTPLATE, MATERIAL_GOLD, 8);
    GeneratedRecipe GOLDEN_LEGGINGS = heated(Items.GOLDEN_LEGGINGS, MATERIAL_GOLD, 7);
    GeneratedRecipe GOLDEN_BOOTS = heated(Items.GOLDEN_BOOTS, MATERIAL_GOLD, 4);
    GeneratedRecipe GOLDEN_PICKAXE = heated(Items.GOLDEN_PICKAXE, MATERIAL_GOLD, 3);
    GeneratedRecipe GOLDEN_AXE = heated(Items.GOLDEN_AXE, MATERIAL_GOLD, 3);
    GeneratedRecipe GOLDEN_SHOVEL = heated(Items.GOLDEN_SHOVEL, MATERIAL_GOLD, 1);
    GeneratedRecipe GOLDEN_HOE = heated(Items.GOLDEN_HOE, MATERIAL_GOLD, 2);
    GeneratedRecipe GOLDEN_SWORD = heated(Items.GOLDEN_SWORD, MATERIAL_GOLD, 2);

    GeneratedRecipe GOLDEN_HELMET_SUPERHEATED = superheated(Items.GOLDEN_HELMET, MATERIAL_GOLD, 5, 1F);
    GeneratedRecipe GOLDEN_CHESTPLATE_SUPERHEATED = superheated(Items.GOLDEN_CHESTPLATE, MATERIAL_GOLD, 8, 1F);
    GeneratedRecipe GOLDEN_LEGGINGS_SUPERHEATED = superheated(Items.GOLDEN_LEGGINGS, MATERIAL_GOLD, 7, 1F);
    GeneratedRecipe GOLDEN_BOOTS_SUPERHEATED = superheated(Items.GOLDEN_BOOTS, MATERIAL_GOLD, 4, 1F);
    GeneratedRecipe GOLDEN_PICKAXE_SUPERHEATED = superheated(Items.GOLDEN_PICKAXE, MATERIAL_GOLD, 3, 1F);
    GeneratedRecipe GOLDEN_AXE_SUPERHEATED = superheated(Items.GOLDEN_AXE, MATERIAL_GOLD, 3, 1F);
    GeneratedRecipe GOLDEN_SHOVEL_SUPERHEATED = superheated(Items.GOLDEN_SHOVEL, MATERIAL_GOLD, 1, 1F);
    GeneratedRecipe GOLDEN_HOE_SUPERHEATED = superheated(Items.GOLDEN_HOE, MATERIAL_GOLD, 2, 1F);
    GeneratedRecipe GOLDEN_SWORD_SUPERHEATED = superheated(Items.GOLDEN_SWORD, MATERIAL_GOLD, 2, 1F);

    GeneratedRecipe DIAMOND_HELMET = superheated(Items.DIAMOND_HELMET, MATERIAL_DIAMOND, 5);
    GeneratedRecipe DIAMOND_CHESTPLATE = superheated(Items.DIAMOND_CHESTPLATE, MATERIAL_DIAMOND, 8);
    GeneratedRecipe DIAMOND_LEGGINGS = superheated(Items.DIAMOND_LEGGINGS, MATERIAL_DIAMOND, 7);
    GeneratedRecipe DIAMOND_BOOTS = superheated(Items.DIAMOND_BOOTS, MATERIAL_DIAMOND, 4);
    GeneratedRecipe DIAMOND_PICKAXE = superheated(Items.DIAMOND_PICKAXE, MATERIAL_DIAMOND, 3);
    GeneratedRecipe DIAMOND_AXE = superheated(Items.DIAMOND_AXE, MATERIAL_DIAMOND, 3);
    GeneratedRecipe DIAMOND_SHOVEL = superheated(Items.DIAMOND_SHOVEL, MATERIAL_DIAMOND, 1);
    GeneratedRecipe DIAMOND_HOE = superheated(Items.DIAMOND_HOE, MATERIAL_DIAMOND, 2);
    GeneratedRecipe DIAMOND_SWORD = superheated(Items.DIAMOND_SWORD, MATERIAL_DIAMOND, 2);

    GeneratedRecipe NETHERITE_HELMET = superheated(Items.NETHERITE_HELMET, MATERIAL_NETHERITE, 1);
    GeneratedRecipe NETHERITE_CHESTPLATE = superheated(Items.NETHERITE_CHESTPLATE, MATERIAL_NETHERITE, 1);
    GeneratedRecipe NETHERITE_LEGGINGS = superheated(Items.NETHERITE_LEGGINGS, MATERIAL_NETHERITE, 1);
    GeneratedRecipe NETHERITE_BOOTS = superheated(Items.NETHERITE_BOOTS, MATERIAL_NETHERITE, 1);
    GeneratedRecipe NETHERITE_PICKAXE = superheated(Items.NETHERITE_PICKAXE, MATERIAL_NETHERITE, 1);
    GeneratedRecipe NETHERITE_AXE = superheated(Items.NETHERITE_AXE, MATERIAL_NETHERITE, 1);
    GeneratedRecipe NETHERITE_SHOVEL = superheated(Items.NETHERITE_SHOVEL, MATERIAL_NETHERITE, 1);
    GeneratedRecipe NETHERITE_HOE = superheated(Items.NETHERITE_HOE, MATERIAL_NETHERITE, 1);
    GeneratedRecipe NETHERITE_SWORD = superheated(Items.NETHERITE_SWORD, MATERIAL_NETHERITE, 1);

    // Create Mod
    GeneratedRecipe CARDBOARD_HELMET = heated(AllItems.CARDBOARD_HELMET.get(), MATERIAL_CARDBOARD, 5);
    GeneratedRecipe CARDBOARD_HELMET_SUPERHEATED = superheated(AllItems.CARDBOARD_HELMET.get(), MATERIAL_CARDBOARD, 5, 1F);
    GeneratedRecipe CARDBOARD_CHESTPLATE = heated(AllItems.CARDBOARD_CHESTPLATE.get(), MATERIAL_CARDBOARD, 8);
    GeneratedRecipe CARDBOARD_CHESTPLATE_SUPERHEATED = superheated(AllItems.CARDBOARD_CHESTPLATE.get(), MATERIAL_CARDBOARD, 8, 1F);
    GeneratedRecipe CARDBOARD_LEGGINGS = heated(AllItems.CARDBOARD_LEGGINGS.get(), MATERIAL_CARDBOARD, 7);
    GeneratedRecipe CARDBOARD_LEGGINGS_SUPERHEATED = superheated(AllItems.CARDBOARD_LEGGINGS.get(), MATERIAL_CARDBOARD, 7, 1F);
    GeneratedRecipe CARDBOARD_BOOTS = heated(AllItems.CARDBOARD_BOOTS.get(), MATERIAL_CARDBOARD, 4);
    GeneratedRecipe CARDBOARD_BOOTS_SUPERHEATED = superheated(AllItems.CARDBOARD_BOOTS.get(), MATERIAL_CARDBOARD, 4, 1F);
    GeneratedRecipe COPPER_DIVING_HELMET = heated(AllItems.COPPER_DIVING_HELMET.get(), MATERIAL_COPPER, 5);
    GeneratedRecipe COPPER_DIVING_HELMET_SUPERHEATED = superheated(AllItems.COPPER_DIVING_HELMET.get(), MATERIAL_COPPER, 5, 1F);
    GeneratedRecipe COPPER_BACKTANK = heated(AllItems.COPPER_BACKTANK.get(), MATERIAL_COPPER, 12);
    GeneratedRecipe COPPER_BACKTANK_SUPERHEATED = superheated(AllItems.COPPER_BACKTANK.get(), MATERIAL_COPPER, 12, 1F);
    GeneratedRecipe COPPER_DIVING_BOOTS = heated(AllItems.COPPER_DIVING_BOOTS.get(), MATERIAL_COPPER, 4);
    GeneratedRecipe COPPER_DIVING_BOOTS_SUPERHEATED = superheated(AllItems.COPPER_DIVING_BOOTS.get(), MATERIAL_COPPER, 4, 1F);
    GeneratedRecipe NETHERITE_DIVING_HELMET = superheated(AllItems.NETHERITE_DIVING_HELMET.get(), MATERIAL_NETHERITE, 1);
    GeneratedRecipe NETHERITE_BACKTANK = superheated(AllItems.NETHERITE_BACKTANK.get(), MATERIAL_NETHERITE, 1);
    GeneratedRecipe NETHERITE_DIVING_BOOTS = superheated(AllItems.NETHERITE_DIVING_BOOTS.get(), MATERIAL_NETHERITE, 1);

    public CreateSalvageSalvagingRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, CreateSalvage.MOD_ID);
    }

    @Override
    protected IRecipeTypeInfo getRecipeType() {
        return SalvagingRecipeTypes.SALVAGING;
    }
}

package com.elitemastereric.createsalvage.data.recipe;

import com.elitemastereric.createsalvage.CreateSalvage;
import com.elitemastereric.createsalvage.ModItems;
import com.elitemastereric.createsalvage.SalvagingRecipeTypes;
import com.elitemastereric.createsalvage.api.data.recipe.SalvagingRecipeGen;
import com.elitemastereric.createsalvage.content.salvaging.SalvagingRecipeParams;
import com.simibubi.create.AllItems;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

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

    public CreateSalvageSalvagingRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, CreateSalvage.MOD_ID);
    }

    private GeneratedRecipe heated(Item ingredient, SalvagingRecipeParams.SalvagingMaterial material, int value, float ratio) {
        return create(() -> ingredient, HeatCondition.HEATED, b -> b.material(material).amount(value * ratio));
    }

    private GeneratedRecipe heated(Item ingredient, SalvagingRecipeParams.SalvagingMaterial material, int value) {
        return heated(ingredient, material, value, 0.5F);
    }

    private GeneratedRecipe superheated(Item ingredient, SalvagingRecipeParams.SalvagingMaterial material, int value, float ratio) {
        return create(() -> ingredient, HeatCondition.SUPERHEATED, b -> b.material(material).amount(value * ratio));
    }

    private GeneratedRecipe superheated(Item ingredient, SalvagingRecipeParams.SalvagingMaterial material, int value) {
        return superheated(ingredient, material, value, 0.5F);
    }

    @Override
    protected IRecipeTypeInfo getRecipeType() {
        return SalvagingRecipeTypes.SALVAGING;
    }
}

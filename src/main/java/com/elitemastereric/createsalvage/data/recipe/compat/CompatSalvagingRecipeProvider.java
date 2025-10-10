package com.elitemastereric.createsalvage.data.recipe.compat;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherItems;
import com.elitemastereric.createsalvage.SalvagingRecipeTypes;
import com.elitemastereric.createsalvage.api.data.recipe.SalvagingRecipeGen;
import com.elitemastereric.createsalvage.content.salvaging.SalvagingRecipeParams;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import io.github.razordevs.deep_aether.init.DAItems;
import io.github.razordevs.deep_aether.item.gear.skyjade.SkyjadeArmorItem;
import net.mcreator.leafscopperbackport.LeafscopperbackportMod;
import net.mcreator.leafscopperbackport.init.LeafscopperbackportModItems;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.zjjohn121110.aethersdelight.registry.AethersDelightItems;
import vectorwing.farmersdelight.common.registry.ModItems;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.elitemastereric.createsalvage.data.recipe.CreateSalvageSalvagingRecipeProvider.*;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public final class CompatSalvagingRecipeProvider {
    public static void generate(List<RecipeProvider> generators, PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        generators.add(new LeafsCopperBackport(output, registries));
        generators.add(new TheAether(output, registries));
        generators.add(new DeepAether(output, registries));
        generators.add(new FarmersDelight(output, registries));
        generators.add(new AethersDelight(output, registries));
    }
}

@SuppressWarnings("unused")
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
final class LeafsCopperBackport extends SalvagingRecipeGen {
    GeneratedRecipe COPPER_HELMET = heated(LeafscopperbackportModItems.COPPER_ARMOR_HELMET.get(), MATERIAL_COPPER, 5);
    GeneratedRecipe COPPER_CHESTPLATE = heated(LeafscopperbackportModItems.COPPER_ARMOR_CHESTPLATE.get(), MATERIAL_COPPER, 8);
    GeneratedRecipe COPPER_LEGGINGS = heated(LeafscopperbackportModItems.COPPER_ARMOR_LEGGINGS.get(), MATERIAL_COPPER, 7);
    GeneratedRecipe COPPER_BOOTS = heated(LeafscopperbackportModItems.COPPER_ARMOR_BOOTS.get(), MATERIAL_COPPER, 4);
    GeneratedRecipe COPPER_PICKAXE = heated(LeafscopperbackportModItems.COPPER_PICKAXE.get(), MATERIAL_COPPER, 3);
    GeneratedRecipe COPPER_AXE = heated(LeafscopperbackportModItems.COPPER_AXE.get(), MATERIAL_COPPER, 3);
    GeneratedRecipe COPPER_SHOVEL = heated(LeafscopperbackportModItems.COPPER_SHOVEL.get(), MATERIAL_COPPER, 1);
    GeneratedRecipe COPPER_HOE = heated(LeafscopperbackportModItems.COPPER_HOE.get(), MATERIAL_COPPER, 2);
    GeneratedRecipe COPPER_SWORD = heated(LeafscopperbackportModItems.COPPER_SWORD.get(), MATERIAL_COPPER, 2);

    GeneratedRecipe COPPER_HELMET_SUPERHEATED = superheated(LeafscopperbackportModItems.COPPER_ARMOR_HELMET.get(), MATERIAL_COPPER, 5, 1F);
    GeneratedRecipe COPPER_CHESTPLATE_SUPERHEATED = superheated(LeafscopperbackportModItems.COPPER_ARMOR_CHESTPLATE.get(), MATERIAL_COPPER, 8, 1F);
    GeneratedRecipe COPPER_LEGGINGS_SUPERHEATED = superheated(LeafscopperbackportModItems.COPPER_ARMOR_LEGGINGS.get(), MATERIAL_COPPER, 7, 1F);
    GeneratedRecipe COPPER_BOOTS_SUPERHEATED = superheated(LeafscopperbackportModItems.COPPER_ARMOR_BOOTS.get(), MATERIAL_COPPER, 4, 1F);
    GeneratedRecipe COPPER_PICKAXE_SUPERHEATED = superheated(LeafscopperbackportModItems.COPPER_PICKAXE.get(), MATERIAL_COPPER, 3, 1F);
    GeneratedRecipe COPPER_AXE_SUPERHEATED = superheated(LeafscopperbackportModItems.COPPER_AXE.get(), MATERIAL_COPPER, 3, 1F);
    GeneratedRecipe COPPER_SHOVEL_SUPERHEATED = superheated(LeafscopperbackportModItems.COPPER_SHOVEL.get(), MATERIAL_COPPER, 1, 1F);
    GeneratedRecipe COPPER_HOE_SUPERHEATED = superheated(LeafscopperbackportModItems.COPPER_HOE.get(), MATERIAL_COPPER, 2, 1F);
    GeneratedRecipe COPPER_SWORD_SUPERHEATED = superheated(LeafscopperbackportModItems.COPPER_SWORD.get(), MATERIAL_COPPER, 2, 1F);

    public LeafsCopperBackport(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, LeafscopperbackportMod.MODID);
    }

    @Override
    protected IRecipeTypeInfo getRecipeType() {
        return SalvagingRecipeTypes.SALVAGING;
    }
}

@SuppressWarnings("unused")
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
final class TheAether extends SalvagingRecipeGen {
    public static final SalvagingRecipeParams.SalvagingMaterial MATERIAL_ZANITE = new SalvagingRecipeParams.SalvagingMaterial(AetherItems.ZANITE_GEMSTONE.get());
    public static final SalvagingRecipeParams.SalvagingMaterial MATERIAL_GRAVITITE = new SalvagingRecipeParams.SalvagingMaterial(AetherBlocks.ENCHANTED_GRAVITITE.asItem());

    GeneratedRecipe ZANITE_HELMET = heated(AetherItems.ZANITE_HELMET.get(), MATERIAL_ZANITE, 5);
    GeneratedRecipe ZANITE_CHESTPLATE = heated(AetherItems.ZANITE_CHESTPLATE.get(), MATERIAL_ZANITE, 8);
    GeneratedRecipe ZANITE_LEGGINGS = heated(AetherItems.ZANITE_LEGGINGS.get(), MATERIAL_ZANITE, 7);
    GeneratedRecipe ZANITE_BOOTS = heated(AetherItems.ZANITE_BOOTS.get(), MATERIAL_ZANITE, 4);
    GeneratedRecipe ZANITE_PICKAXE = heated(AetherItems.ZANITE_PICKAXE.get(), MATERIAL_ZANITE, 3);
    GeneratedRecipe ZANITE_AXE = heated(AetherItems.ZANITE_AXE.get(), MATERIAL_ZANITE, 3);
    GeneratedRecipe ZANITE_SHOVEL = heated(AetherItems.ZANITE_SHOVEL.get(), MATERIAL_ZANITE, 1);
    GeneratedRecipe ZANITE_HOE = heated(AetherItems.ZANITE_HOE.get(), MATERIAL_ZANITE, 2);
    GeneratedRecipe ZANITE_SWORD = heated(AetherItems.ZANITE_SWORD.get(), MATERIAL_ZANITE, 2);

    GeneratedRecipe ZANITE_HELMET_SUPERHEATED = superheated(AetherItems.ZANITE_HELMET.get(), MATERIAL_ZANITE, 5, 1F);
    GeneratedRecipe ZANITE_CHESTPLATE_SUPERHEATED = superheated(AetherItems.ZANITE_CHESTPLATE.get(), MATERIAL_ZANITE, 8, 1F);
    GeneratedRecipe ZANITE_LEGGINGS_SUPERHEATED = superheated(AetherItems.ZANITE_LEGGINGS.get(), MATERIAL_ZANITE, 7, 1F);
    GeneratedRecipe ZANITE_BOOTS_SUPERHEATED = superheated(AetherItems.ZANITE_BOOTS.get(), MATERIAL_ZANITE, 4, 1F);
    GeneratedRecipe ZANITE_PICKAXE_SUPERHEATED = superheated(AetherItems.ZANITE_PICKAXE.get(), MATERIAL_ZANITE, 3, 1F);
    GeneratedRecipe ZANITE_AXE_SUPERHEATED = superheated(AetherItems.ZANITE_AXE.get(), MATERIAL_ZANITE, 3, 1F);
    GeneratedRecipe ZANITE_SHOVEL_SUPERHEATED = superheated(AetherItems.ZANITE_SHOVEL.get(), MATERIAL_ZANITE, 1, 1F);
    GeneratedRecipe ZANITE_HOE_SUPERHEATED = superheated(AetherItems.ZANITE_HOE.get(), MATERIAL_ZANITE, 2, 1F);
    GeneratedRecipe ZANITE_SWORD_SUPERHEATED = superheated(AetherItems.ZANITE_SWORD.get(), MATERIAL_ZANITE, 2, 1F);

    GeneratedRecipe GRAVITITE_HELMET = superheated(AetherItems.GRAVITITE_HELMET.get(), MATERIAL_GRAVITITE, 5);
    GeneratedRecipe GRAVITITE_CHESTPLATE = superheated(AetherItems.GRAVITITE_CHESTPLATE.get(), MATERIAL_GRAVITITE, 8);
    GeneratedRecipe GRAVITITE_LEGGINGS = superheated(AetherItems.GRAVITITE_LEGGINGS.get(), MATERIAL_GRAVITITE, 7);
    GeneratedRecipe GRAVITITE_BOOTS = superheated(AetherItems.GRAVITITE_BOOTS.get(), MATERIAL_GRAVITITE, 4);
    GeneratedRecipe GRAVITITE_PICKAXE = superheated(AetherItems.GRAVITITE_PICKAXE.get(), MATERIAL_GRAVITITE, 3);
    GeneratedRecipe GRAVITITE_AXE = superheated(AetherItems.GRAVITITE_AXE.get(), MATERIAL_GRAVITITE, 3);
    GeneratedRecipe GRAVITITE_SHOVEL = superheated(AetherItems.GRAVITITE_SHOVEL.get(), MATERIAL_GRAVITITE, 1);
    GeneratedRecipe GRAVITITE_HOE = superheated(AetherItems.GRAVITITE_HOE.get(), MATERIAL_GRAVITITE, 2);
    GeneratedRecipe GRAVITITE_SWORD = superheated(AetherItems.GRAVITITE_SWORD.get(), MATERIAL_GRAVITITE, 2);

    public TheAether(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, Aether.MODID);
    }

    @Override
    protected IRecipeTypeInfo getRecipeType() {
        return SalvagingRecipeTypes.SALVAGING;
    }
}

@SuppressWarnings("unused")
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
final class DeepAether extends SalvagingRecipeGen {
    public static final SalvagingRecipeParams.SalvagingMaterial MATERIAL_SKYJADE = new SalvagingRecipeParams.SalvagingMaterial(DAItems.SKYJADE.get());
    public static final SalvagingRecipeParams.SalvagingMaterial MATERIAL_STRATUS = new SalvagingRecipeParams.SalvagingMaterial(DAItems.STRATUS_INGOT.asItem());

    GeneratedRecipe SKYJADE_HELMET = heated(DAItems.SKYJADE_HELMET.get(), MATERIAL_SKYJADE, 5);
    GeneratedRecipe SKYJADE_CHESTPLATE = heated(DAItems.SKYJADE_CHESTPLATE.get(), MATERIAL_SKYJADE, 8);
    GeneratedRecipe SKYJADE_LEGGINGS = heated(DAItems.SKYJADE_LEGGINGS.get(), MATERIAL_SKYJADE, 7);
    GeneratedRecipe SKYJADE_BOOTS = heated(DAItems.SKYJADE_BOOTS.get(), MATERIAL_SKYJADE, 4);
    GeneratedRecipe SKYJADE_PICKAXE = heated(DAItems.SKYJADE_TOOLS_PICKAXE.get(), MATERIAL_SKYJADE, 3);
    GeneratedRecipe SKYJADE_AXE = heated(DAItems.SKYJADE_TOOLS_AXE.get(), MATERIAL_SKYJADE, 3);
    GeneratedRecipe SKYJADE_SHOVEL = heated(DAItems.SKYJADE_TOOLS_SHOVEL.get(), MATERIAL_SKYJADE, 1);
    GeneratedRecipe SKYJADE_HOE = heated(DAItems.SKYJADE_TOOLS_HOE.get(), MATERIAL_SKYJADE, 2);
    GeneratedRecipe SKYJADE_SWORD = heated(DAItems.SKYJADE_TOOLS_SWORD.get(), MATERIAL_SKYJADE, 2);

    GeneratedRecipe SKYJADE_HELMET_SUPERHEATED = superheated(DAItems.SKYJADE_HELMET.get(), MATERIAL_SKYJADE, 5, 1F);
    GeneratedRecipe SKYJADE_CHESTPLATE_SUPERHEATED = superheated(DAItems.SKYJADE_CHESTPLATE.get(), MATERIAL_SKYJADE, 8, 1F);
    GeneratedRecipe SKYJADE_LEGGINGS_SUPERHEATED = superheated(DAItems.SKYJADE_LEGGINGS.get(), MATERIAL_SKYJADE, 7, 1F);
    GeneratedRecipe SKYJADE_BOOTS_SUPERHEATED = superheated(DAItems.SKYJADE_BOOTS.get(), MATERIAL_SKYJADE, 4, 1F);
    GeneratedRecipe SKYJADE_PICKAXE_SUPERHEATED = superheated(DAItems.SKYJADE_TOOLS_PICKAXE.get(), MATERIAL_SKYJADE, 3, 1F);
    GeneratedRecipe SKYJADE_AXE_SUPERHEATED = superheated(DAItems.SKYJADE_TOOLS_AXE.get(), MATERIAL_SKYJADE, 3, 1F);
    GeneratedRecipe SKYJADE_SHOVEL_SUPERHEATED = superheated(DAItems.SKYJADE_TOOLS_SHOVEL.get(), MATERIAL_SKYJADE, 1, 1F);
    GeneratedRecipe SKYJADE_HOE_SUPERHEATED = superheated(DAItems.SKYJADE_TOOLS_HOE.get(), MATERIAL_SKYJADE, 2, 1F);
    GeneratedRecipe SKYJADE_SWORD_SUPERHEATED = superheated(DAItems.SKYJADE_TOOLS_SWORD.get(), MATERIAL_SKYJADE, 2, 1F);

    GeneratedRecipe STRATUS_HELMET = superheated(DAItems.STRATUS_HELMET.get(), MATERIAL_STRATUS, 1, 1F);
    GeneratedRecipe STRATUS_CHESTPLATE = superheated(DAItems.STRATUS_CHESTPLATE.get(), MATERIAL_STRATUS, 1, 1F);
    GeneratedRecipe STRATUS_LEGGINGS = superheated(DAItems.STRATUS_LEGGINGS.get(), MATERIAL_STRATUS, 1, 1F);
    GeneratedRecipe STRATUS_BOOTS = superheated(DAItems.STRATUS_BOOTS.get(), MATERIAL_STRATUS, 1, 1F);
    GeneratedRecipe STRATUS_PICKAXE = superheated(DAItems.STRATUS_PICKAXE.get(), MATERIAL_STRATUS, 1, 1F);
    GeneratedRecipe STRATUS_AXE = superheated(DAItems.STRATUS_AXE.get(), MATERIAL_STRATUS, 1, 1F);
    GeneratedRecipe STRATUS_SHOVEL = superheated(DAItems.STRATUS_SHOVEL.get(), MATERIAL_STRATUS, 1, 1F);
    GeneratedRecipe STRATUS_HOE = superheated(DAItems.STRATUS_HOE.get(), MATERIAL_STRATUS, 1, 1F);
    GeneratedRecipe STRATUS_SWORD = superheated(DAItems.STRATUS_SWORD.get(), MATERIAL_STRATUS, 1, 1F);

    public DeepAether(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, io.github.razordevs.deep_aether.DeepAether.MODID);
    }

    @Override
    protected IRecipeTypeInfo getRecipeType() {
        return SalvagingRecipeTypes.SALVAGING;
    }
}

@SuppressWarnings("unused")
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
final class FarmersDelight extends SalvagingRecipeGen {
    GeneratedRecipe IRON_KNIFE = heated(ModItems.IRON_KNIFE.get(), MATERIAL_IRON, 1);
    GeneratedRecipe IRON_KNIFE_SUPERHEATED = superheated(ModItems.IRON_KNIFE.get(), MATERIAL_IRON, 1, 1F);
    GeneratedRecipe GOLDEN_KNIFE = heated(ModItems.GOLDEN_KNIFE.get(), MATERIAL_GOLD, 1);
    GeneratedRecipe GOLDEN_KNIFE_SUPERHEATED = superheated(ModItems.GOLDEN_KNIFE.get(), MATERIAL_GOLD, 1, 1F);
    GeneratedRecipe DIAMOND_KNIFE = superheated(ModItems.DIAMOND_KNIFE.get(), MATERIAL_DIAMOND, 1);
    GeneratedRecipe NETHERITE_KNIFE = superheated(ModItems.NETHERITE_KNIFE.get(), MATERIAL_NETHERITE, 1);

    public FarmersDelight(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, vectorwing.farmersdelight.FarmersDelight.MODID);
    }

    @Override
    protected IRecipeTypeInfo getRecipeType() {
        return SalvagingRecipeTypes.SALVAGING;
    }
}

@SuppressWarnings("unused")
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
final class AethersDelight extends SalvagingRecipeGen {
    GeneratedRecipe ZANITE_KNIFE = heated(AethersDelightItems.ZANITE_KNIFE.get(), TheAether.MATERIAL_ZANITE, 1);
    GeneratedRecipe ZANITE_KNIFE_SUPERHEATED = superheated(AethersDelightItems.ZANITE_KNIFE.get(), TheAether.MATERIAL_ZANITE, 1, 1F);
    GeneratedRecipe GRAVITITE_KNIFE = superheated(AethersDelightItems.GRAVITITE_KNIFE.get(), TheAether.MATERIAL_GRAVITITE, 1);

    public AethersDelight(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, net.zjjohn121110.aethersdelight.AethersDelight.MODID);
    }

    @Override
    protected IRecipeTypeInfo getRecipeType() {
        return SalvagingRecipeTypes.SALVAGING;
    }
}
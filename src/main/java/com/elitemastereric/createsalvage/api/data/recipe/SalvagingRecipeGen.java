package com.elitemastereric.createsalvage.api.data.recipe;

import com.elitemastereric.createsalvage.CreateSalvage;
import com.elitemastereric.createsalvage.content.salvaging.SalvagingRecipe;
import com.elitemastereric.createsalvage.content.salvaging.SalvagingRecipeParams;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import net.createmod.catnip.registry.RegisteredObjectsHelper;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * The base class for Salvaging recipe data generation.
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public abstract class SalvagingRecipeGen extends RecipeProvider {
    protected final String modid;
    protected final List<GeneratedRecipe> recipes = new ArrayList<>();

    public SalvagingRecipeGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, String modId) {
        super(output, registries);
        this.modid = modId;
    }

    /**
     * Quickly create a Salvaging recipe.
     * Provide the input ingredient, the Salvaging Material, its value, and the salvaging efficiency.
     * @param ingredient The ingredient item to melt into material.
     * @param material The material to melt the ingredient item into.
     * @param value The quantity of material to produce, in ingots.
     * @param ratio The efficiency of the salvage process. Heating Iron gives 0.5F, Super-heating Iron gives 1.0F
     * @return The resulting recipe.
     */
    @SuppressWarnings("SameParameterValue")
    protected GeneratedRecipe heated(Item ingredient, SalvagingRecipeParams.SalvagingMaterial material, int value, float ratio) {
        return create(() -> ingredient, HeatCondition.HEATED, b -> b.material(material).amount(value * ratio));
    }

    /**
     * Quickly create a Salvaging recipe.
     * Provide the input ingredient, the Salvaging Material, its value. Salvaging efficiency defaults to 50%.
     * @param ingredient The ingredient item to melt into material.
     * @param material The material to melt the ingredient item into.
     * @param value The quantity of material to produce, in ingots.
     * @return The resulting recipe.
     */
    protected GeneratedRecipe heated(Item ingredient, SalvagingRecipeParams.SalvagingMaterial material, int value) {
        return heated(ingredient, material, value, 0.5F);
    }

    /**
     * Quickly create a Salvaging recipe.
     * Provide the input ingredient, the Salvaging Material, its value, and the salvaging efficiency.
     * @param ingredient The ingredient item to melt into material.
     * @param material The material to melt the ingredient item into.
     * @param value The quantity of material to produce, in ingots.
     * @param ratio The efficiency of the salvage process. Heating Iron gives 0.5F, Super-heating Iron gives 1.0F
     * @return The resulting recipe.
     */
    protected GeneratedRecipe superheated(Item ingredient, SalvagingRecipeParams.SalvagingMaterial material, int value, float ratio) {
        return create(() -> ingredient, HeatCondition.SUPERHEATED, b -> b.material(material).amount(value * ratio));
    }

    /**
     * Quickly create a Salvaging recipe.
     * Provide the input ingredient, the Salvaging Material, its value. Salvaging efficiency defaults to 50%.
     * @param ingredient The ingredient item to melt into material.
     * @param material The material to melt the ingredient item into.
     * @param value The quantity of material to produce, in ingots.
     * @return The resulting recipe.
     */
    protected GeneratedRecipe superheated(Item ingredient, SalvagingRecipeParams.SalvagingMaterial material, int value) {
        return superheated(ingredient, material, value, 0.5F);
    }

    /**
     * Create a salvaging recipe.
     * @param namespace The namespace of the recipe, defaults to the mod ID of the generator.
     * @param ingredient The item to melt down.
     * @param transform A function which utilizes the builder to create a salvaging recipe. Make sure to provide the required heat, and the output material/amount.
     */
    protected GeneratedRecipe create(String namespace, Supplier<ItemLike> ingredient, HeatCondition requiredHeat, UnaryOperator<SalvagingRecipe.Builder> transform) {
        GeneratedRecipe generatedRecipe = c -> {
            ItemLike itemLike = ingredient.get();

            // <item-name>_<heat-level> should produce a sufficiently unique key.
            String recipePath = RegisteredObjectsHelper.getKeyOrThrow(itemLike.asItem()).getPath();
            switch (requiredHeat) {
                case HEATED:
                    recipePath += "_heated";
                    break;
                case SUPERHEATED:
                    recipePath += "_superheated";
                    break;
            }
            ResourceLocation recipeId = ResourceLocation.fromNamespaceAndPath(namespace, recipePath);

            // This recipe is for a different mod, therefore it should only load if that mod is installed.
            if (!Objects.equals(modid, CreateSalvage.MOD_ID)) {
                c = c.withConditions(new ModLoadedCondition(modid));
            }

            transform.apply(getBuilder(recipeId).ingredient(itemLike).requiresHeat(requiredHeat)).build(c);
        };
        recipes.add(generatedRecipe);
        return generatedRecipe;
    }

    /**
     * Create a salvaging recipe.
     * @param ingredient The item to melt down.
     * @param transform A function which utilizes the builder to create a salvaging recipe. Make sure to provide the required heat, and the output material/amount.
     */
    protected GeneratedRecipe create(Supplier<ItemLike> ingredient, HeatCondition requiredHeat, UnaryOperator<SalvagingRecipe.Builder> transform) {
        return create(this.modid, ingredient, requiredHeat, transform);
    }

    protected SalvagingRecipe.Serializer getSerializer() {
        return getRecipeType().getSerializer();
    }

    protected abstract IRecipeTypeInfo getRecipeType();

    protected SalvagingRecipe.Builder getBuilder(ResourceLocation id) {
        return new SalvagingRecipe.Builder(getSerializer().factory(), id);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        recipes.forEach(c -> c.register(recipeOutput));
    }

    /**
     * Gets a display name for this recipe generator.
     * It is recommended to override this for a prettier name, however that is not required.
     */
    @Override
    public String getName() {
        return modid + "'s Salvaging recipes";
    }

    @FunctionalInterface
    public interface GeneratedRecipe {
        void register(RecipeOutput recipeOutput);
    }
}

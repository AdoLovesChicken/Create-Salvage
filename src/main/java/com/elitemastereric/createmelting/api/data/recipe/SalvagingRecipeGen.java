package com.elitemastereric.createmelting.api.data.recipe;

import com.elitemastereric.createmelting.content.salvaging.SalvagingRecipe;
import com.simibubi.create.Create;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import net.createmod.catnip.registry.RegisteredObjectsHelper;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
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

    public SalvagingRecipeGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, String defaultNamespace) {
        super(output, registries);
        this.modid = defaultNamespace;
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

            // <itemname>_<heatlevel> should produce a sufficiently unique key.
            String recipePath = RegisteredObjectsHelper.getKeyOrThrow(itemLike.asItem()).getPath();
            switch (requiredHeat) {
                case HEATED:
                    recipePath += "_heated";
                case SUPERHEATED:
                    recipePath += "_superheated";
            }

            ResourceLocation recipeId = ResourceLocation.fromNamespaceAndPath(namespace, recipePath);
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
        Create.LOGGER.info("{} registered {} recipe{}", getName(), recipes.size(), recipes.size() == 1 ? "" : "s");
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

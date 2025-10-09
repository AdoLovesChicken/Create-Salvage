package com.elitemastereric.createmelting.data.recipe;

import com.elitemastereric.createmelting.CreateMelting;
import com.elitemastereric.createmelting.ModItems;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CreateMeltingRecipeProvider extends RecipeProvider {
    static final List<RecipeProvider> GENERATORS = new ArrayList<>();

    public CreateMeltingRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DIAMOND_NUGGET, 9)
                .requires(Items.DIAMOND)
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(CreateMelting.MODID, "diamond_nuggets_from_diamond"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.DIAMOND)
                .requires(ModItems.DIAMOND_NUGGET, 9)
                .unlockedBy("has_diamond_nugget", has(ModItems.DIAMOND_NUGGET))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(CreateMelting.MODID, "diamond_from_diamond_nuggets"));
    }

    public static void registerProcessingRecipes(DataGenerator gen, PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        GENERATORS.add(new CreateMeltingSalvagingRecipeProvider(output, registries));

        gen.addProvider(true, new DataProvider() {
            @Override
            public String getName() {
                return "Create: Melting's Recipes";
            }

            @Override
            public CompletableFuture<?> run(CachedOutput dc) {
                return CompletableFuture.allOf(GENERATORS.stream()
                        .map(gen -> gen.run(dc))
                        .toArray(CompletableFuture[]::new));
            }
        });
    }
}

package com.elitemastereric.createsalvage.content.salvaging;

import com.elitemastereric.createsalvage.SalvagingRecipeTypes;
import com.elitemastereric.createsalvage.CreateSalvage;
import com.google.common.base.Joiner;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.simibubi.create.Create;
import com.simibubi.create.content.processing.basin.BasinBlockEntity;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock.HeatLevel;
import com.simibubi.create.content.processing.recipe.*;
import com.simibubi.create.foundation.recipe.DummyCraftingContainer;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import net.createmod.catnip.data.Iterate;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class SalvagingRecipe implements Recipe<RecipeInput>  {
    protected SalvagingRecipeParams params;

    public SalvagingRecipe(SalvagingRecipeParams params) {
        this.params = params;
    }

    public static boolean apply(BasinBlockEntity basin, SalvagingRecipe recipe) {
        return apply(basin, recipe, false);
    }

    public static boolean apply(BasinBlockEntity basin, SalvagingRecipe recipe, boolean test) {
        Level level = basin.getLevel();
        if (level == null) return false;

        IItemHandler availableItemSlots = level.getCapability(Capabilities.ItemHandler.BLOCK, basin.getBlockPos(), null);
        if (availableItemSlots == null) return false;

        HeatLevel heat = BasinBlockEntity.getHeatLevelOf(basin.getLevel()
                .getBlockState(basin.getBlockPos()
                        .below(1)));
        if (!recipe.getRequiredHeat().testBlazeBurner(heat))  return false;

        List<ItemStack> recipeOutputItems = new ArrayList<>();
        Ingredient ingredient = recipe.getIngredient();

        // simulate will be false first, then true. This lets us run a second time to "do it for real."
        for (boolean simulate : Iterate.trueAndFalse) {
            if (!simulate && test)
                return true;

            int[] extractedItemsFromSlot = new int[availableItemSlots.getSlots()];
            @Nullable ItemStack extractedItem = null;
            for (int slot = 0; slot < availableItemSlots.getSlots(); slot++) {
                if (simulate && availableItemSlots.getStackInSlot(slot)
                        .getCount() <= extractedItemsFromSlot[slot]) {
                    continue;
                }

                // Fake extract.
                ItemStack itemToTest = availableItemSlots.extractItem(slot, 1, true);
                // Check if the ingredient in the slot doesn't match.
                if (!ingredient.test(itemToTest)) {
                    continue;
                }

                // Real extract.
                if (!simulate) {
                    extractedItem = availableItemSlots.extractItem(slot, 1, false);
                } else {
                    extractedItem = itemToTest;
                }
                extractedItemsFromSlot[slot]++;
            }

            // If we failed to get an ItemStack for the ingredient, fail.
            if (extractedItem == null) return false;

            if (simulate) {
                CraftingInput remainderInput = new DummyCraftingContainer(availableItemSlots, extractedItemsFromSlot)
                        .asCraftInput();

                float durability = (float) (extractedItem.getMaxDamage() - extractedItem.getDamageValue()) / extractedItem.getMaxDamage();
                CreateSalvage.LOGGER.info("Simulating crafting with durability: {}", durability);
                recipeOutputItems.addAll(recipe.buildResults(durability));

                for (ItemStack stack : recipe.getRemainingItems(remainderInput))
                    if (!stack.isEmpty())
                        recipeOutputItems.add(stack);
            }

            // Return false if the Basin doesn't have room for the outputs.
            if (!basin.acceptOutputs(recipeOutputItems, new ArrayList<>(), simulate))
                return false;
        }

        return true;
    }

    public HeatCondition getRequiredHeat() {
        return params.requiredHeat;
    }

    @Override
    public boolean matches(RecipeInput recipeInput, Level level) {
        return false;
    }

    @Override
    public ItemStack assemble(RecipeInput recipeInput, HolderLookup.Provider provider) {
        return getResultItem(provider);
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return false;
    }

    public Ingredient getIngredient() {
        return params.ingredient;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.of(getIngredient());
    }

    public List<ProcessingOutput> getResults(float durability) {
        return params.resultMaterial().calculate(params.resultAmount() * durability);
    }

    public List<ProcessingOutput> getResults() {
        return getResults(1F);
    }

    public List<ItemStack> buildResults(float durability) {
        List<ProcessingOutput> resultsToBuild = getResults(durability);

        List<ItemStack> results = new ArrayList<>();
        for (ProcessingOutput output : resultsToBuild) {
            ItemStack stack = output.rollOutput();
            if (!stack.isEmpty())
                results.add(stack);
        }
        return results;
    }

    public List<ItemStack> buildResults() {
        return buildResults(1F);
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        // We have to just use the first item (which is just the number of ingots).
        return getResults().isEmpty() ? ItemStack.EMPTY
                : getResults().getFirst()
                .getStack();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SalvagingRecipeTypes.SALVAGING.getSerializer();
    }

    public List<String> validate() {
        List<String> errors = new ArrayList<>();

        if (params.resultAmount <= 0) {
            errors.add("Recipe has a result amount of 0 items, use a positive decimal value.");
        }

        if (params.resultMaterial.nugget != null && params.resultMaterial.ratio <= 0) {
            errors.add("Recipe has an invalid nugget:ingot ratio, use a positive integer value.");
        }

        return errors;
    }

    public SalvagingRecipeParams getSalvagingParams() {
        return params;
    }

    @Override
    public RecipeType<?> getType() {
        return SalvagingRecipeTypes.SALVAGING.getType();
    }

    public IRecipeTypeInfo getTypeInfo() {
        return SalvagingRecipeTypes.SALVAGING;
    }

    @FunctionalInterface
    public interface Factory {
        SalvagingRecipe create(SalvagingRecipeParams params);
    }

    @SuppressWarnings("unused")
    public static class Builder {
        protected ResourceLocation recipeId;
        protected Factory factory;
        protected SalvagingRecipeParams params;
        protected List<ICondition> recipeConditions;

        public Builder(Factory factory, ResourceLocation recipeId) {
            this.recipeId = recipeId;
            this.factory = factory;
            this.params = createParams();
            this.recipeConditions = new ArrayList<>();
        }

        protected SalvagingRecipeParams createParams() {
            return new SalvagingRecipeParams();
        }

        public Builder self() {
            return this;
        }

        /**
         * Specify the input tool, weapon, or armor piece for the recipe.
         * @return The builder, for chaining calls.
         */
        public Builder ingredient(ItemLike itemLike) {
            params.ingredient = Ingredient.of(itemLike);
            return self();
        }

        /**
         * Specify the input tool, weapon, or armor piece for the recipe.
         * @param ingot The ingot material.
         * @param nugget The nugget material.
         * @param ratio The number of nuggets in one ingot.
         * @return The builder, for chaining calls.
         */
        public Builder material(Item ingot, @Nullable Item nugget, int ratio) {
            params.resultMaterial = new SalvagingRecipeParams.SalvagingMaterial(ingot, nugget, ratio);
            return self();
        }

        /**
         * Specify the input tool, weapon, or armor piece for the recipe.
         * @param material The tool material.
         * @return The builder, for chaining calls.
         */
        public Builder material(SalvagingRecipeParams.SalvagingMaterial material) {
            params.resultMaterial = material;
            return self();
        }

        /**
         * Specify the required heat for the Blaze Burner, either HEATED or SUPERHEATED.
         * @param condition The heat required for this recipe.
         * @return The builder, for chaining calls.
         */
        public Builder requiresHeat(HeatCondition condition) {
            params.requiredHeat = condition;
            return self();
        }

        /**
         * Specify the amount of material for the recipe to output.
         * @param amount The amount of material. Will be rounded to ingots and nuggets.
         * @return The builder, for chaining calls.
         */
        public Builder amount(float amount) {
            params.resultAmount = amount;
            return self();
        }

        public SalvagingRecipe build() {
            return factory.create(params);
        }

        public void build(RecipeOutput consumer) {
            SalvagingRecipe recipe = build();
            IRecipeTypeInfo recipeType = recipe.getTypeInfo();
            ResourceLocation typeId = recipeType.getId();
            ResourceLocation id = recipeId.withPrefix(typeId.getPath() + "/");
            var errors = recipe.validate();
            if (!errors.isEmpty()) {
                errors.add(recipe.getClass().getSimpleName() + "with id " + id + " failed validation:");
                Create.LOGGER.warn(Joiner.on('\n').join(errors));
            }
            consumer.accept(id, recipe, null, recipeConditions.toArray(new ICondition[0]));
        }

    }

    public static class Serializer implements RecipeSerializer<SalvagingRecipe> {
        private final Factory factory;
        private final MapCodec<SalvagingRecipe> codec;
        private final StreamCodec<RegistryFriendlyByteBuf, SalvagingRecipe> streamCodec;

        public Serializer(Factory factory) {
            this.factory = factory;
            this.codec = SalvagingRecipeParams.CODEC
                    .xmap(factory::create, SalvagingRecipe::getSalvagingParams)
                    .validate(this::validate);
            this.streamCodec = SalvagingRecipeParams.STREAM_CODEC.map(factory::create, SalvagingRecipe::getSalvagingParams);
        }

        @Override
        public MapCodec<SalvagingRecipe> codec() {
            return codec;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, SalvagingRecipe> streamCodec() {
            return streamCodec;
        }

        public com.mojang.serialization.DataResult<SalvagingRecipe> validate(SalvagingRecipe recipe) {
            var errors = recipe.validate();
            if (errors.isEmpty())
                return DataResult.success(recipe);
            errors.add("SalvagingRecipe failed validation:");
            return DataResult.error(() -> Joiner.on('\n').join(errors), recipe);
        }

        public Factory factory() {
            return factory;
        }
    }
}

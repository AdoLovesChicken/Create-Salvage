package com.elitemastereric.createsalvage.content.salvaging;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.simibubi.create.content.kinetics.deployer.ItemApplicationRecipeParams;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeParams;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * The parameters for the ingredients of a Salvaging processing recipe.
 * <a href="https://github.com/Creators-of-Create/Create/pull/7945">Custom Recipe Params</a>
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class SalvagingRecipeParams {
    public static MapCodec<SalvagingRecipeParams> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Ingredient.CODEC.fieldOf("ingredient")
                    .forGetter(SalvagingRecipeParams::ingredient),
            HeatCondition.CODEC.optionalFieldOf("heat_requirement", HeatCondition.HEATED)
                    .forGetter(SalvagingRecipeParams::requiredHeat),
            SalvagingMaterial.CODEC.fieldOf("result_material")
                    .forGetter(SalvagingRecipeParams::resultMaterial),
            Codec.FLOAT.fieldOf("result_amount")
                    .forGetter(SalvagingRecipeParams::resultAmount))
            .apply(instance, SalvagingRecipeParams::new));

    public static StreamCodec<RegistryFriendlyByteBuf, SalvagingRecipeParams> STREAM_CODEC = StreamCodec
            .composite(
                    Ingredient.CONTENTS_STREAM_CODEC, SalvagingRecipeParams::ingredient,
                    HeatCondition.STREAM_CODEC, SalvagingRecipeParams::requiredHeat,
                    SalvagingMaterial.STREAM_CODEC, SalvagingRecipeParams::resultMaterial,
                    ByteBufCodecs.FLOAT, SalvagingRecipeParams::resultAmount,
                    SalvagingRecipeParams::new);

    protected Ingredient ingredient;
    protected HeatCondition requiredHeat;
    protected SalvagingMaterial resultMaterial;
    protected float resultAmount;

    protected SalvagingRecipeParams() {}

    protected SalvagingRecipeParams(Ingredient ingredient, HeatCondition requiredHeat, SalvagingMaterial resultMaterial, float resultAmount) {
        this.ingredient = ingredient;
        this.requiredHeat = requiredHeat;
        this.resultMaterial = resultMaterial;
        this.resultAmount = resultAmount;
    }

    protected final Ingredient ingredient() {
        return ingredient;
    }

    protected final HeatCondition requiredHeat() {
        return requiredHeat;
    }

    protected final SalvagingMaterial resultMaterial() {
        return resultMaterial;
    }

    protected final float resultAmount() {
        return resultAmount;
    }

    protected final NonNullList<ProcessingOutput> results() {
         return resultMaterial.calculate(resultAmount);
    }

    protected ProcessingRecipeParams toBasinParams() {
        return new SalvagingProcessingRecipeParams(ingredient, requiredHeat, results());
    }

    static class SalvagingProcessingRecipeParams extends ItemApplicationRecipeParams {
        public SalvagingProcessingRecipeParams(Ingredient ingredient, HeatCondition requiredHeat, NonNullList<ProcessingOutput> results) {
            super();
            this.ingredients = NonNullList.of(ingredient);
            this.requiredHeat = requiredHeat;
            this.results = results;
        }
    }

    public static class SalvagingMaterial {
        public static MapCodec<SalvagingMaterial> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                BuiltInRegistries.ITEM.byNameCodec().fieldOf("ingot")
                        .forGetter(SalvagingMaterial::ingot),
                BuiltInRegistries.ITEM.byNameCodec().optionalFieldOf("nugget", null)
                        .forGetter(SalvagingMaterial::nugget),
                Codec.INT.optionalFieldOf("ratio", 0)
                        .forGetter(SalvagingMaterial::ratio))
                .apply(instance, SalvagingMaterial::new));

        public static StreamCodec<RegistryFriendlyByteBuf, SalvagingMaterial> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.registry(Registries.ITEM), i -> i.ingot,
                ByteBufCodecs.registry(Registries.ITEM), i -> i.nugget,
                ByteBufCodecs.INT, i -> i.ratio,
                SalvagingMaterial::new
        );

        @NotNull
        protected Item ingot;

        @Nullable
        protected Item nugget;

        protected int ratio;

        public SalvagingMaterial(Item ingot, @Nullable Item nugget, int ratio) {
            this.ingot = ingot;
            this.nugget = nugget;
            this.ratio = ratio;
        }

        public Item ingot() {
            return ingot;
        }

        public @Nullable Item nugget() {
            return nugget;
        }

        public int ratio() {
            return ratio;
        }

        /**
         * @param amount The amount of ingots and fractional ingots to produce.
         * @return A list of item stacks containing the corresponding quantity of ingots and nuggets.
         */
        public NonNullList<ProcessingOutput> calculate(float amount) {
            NonNullList<ProcessingOutput> results = NonNullList.create();

            int ingotAmount = (int) Math.floor(amount);
            if (ingotAmount > 0) results.add(new ProcessingOutput(ingot, ingotAmount, 1F));

            if (nugget != null) {
                float remainder = amount - ingotAmount;
                int nuggetAmount = (int) Math.floor(remainder * ratio);
                if (nuggetAmount > 0) results.add(new ProcessingOutput(nugget, nuggetAmount, 1F));
            }

            return results;
        }
    }
}

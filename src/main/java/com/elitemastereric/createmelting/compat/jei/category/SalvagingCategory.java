package com.elitemastereric.createmelting.compat.jei.category;

import com.elitemastereric.createmelting.content.salvaging.SalvagingRecipe;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.compat.jei.category.CreateRecipeCategory;
import com.simibubi.create.compat.jei.category.animations.AnimatedBlazeBurner;
import com.simibubi.create.compat.jei.category.animations.AnimatedMixer;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import com.simibubi.create.foundation.gui.AllGuiTextures;
import com.simibubi.create.foundation.item.ItemHelper;
import com.simibubi.create.foundation.utility.CreateLang;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.createmod.catnip.data.Pair;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.crafting.Ingredient;
import org.apache.commons.lang3.mutable.MutableInt;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class SalvagingCategory extends CreateRecipeCategory<SalvagingRecipe> {
    private final AnimatedMixer mixer = new AnimatedMixer();
    private final AnimatedBlazeBurner heater = new AnimatedBlazeBurner();

    public SalvagingCategory(Info<SalvagingRecipe> info) {
        super(info);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, SalvagingRecipe recipe, IFocusGroup focuses) {
        List<Pair<Ingredient, MutableInt>> condensedIngredients = ItemHelper.condenseIngredients(recipe.getIngredients());

        builder
                .addSlot(RecipeIngredientRole.INPUT, 17, 51)
                .setBackground(getRenderedSlot(), -1, -1)
                .addIngredients(recipe.getIngredient());

        int size = recipe.getResults().size();
        int i = 0;

        for (ProcessingOutput result : recipe.getResults()) {
            int xPosition = 142 - (size % 2 != 0 && i == size - 1 ? 0 : i % 2 == 0 ? 10 : -9);
            int yPosition = -19 * (i / 2) + 51;

            builder
                    .addSlot(RecipeIngredientRole.OUTPUT, xPosition, yPosition)
                    .setBackground(getRenderedSlot(result), -1, -1)
                    .addItemStack(result.getStack());
            i++;
        }

        HeatCondition requiredHeat = recipe.getRequiredHeat();
        if (!requiredHeat.testBlazeBurner(BlazeBurnerBlock.HeatLevel.NONE)) {
            builder
                    .addSlot(RecipeIngredientRole.RENDER_ONLY, 134, 81)
                    .addItemStack(AllBlocks.BLAZE_BURNER.asStack());
        }
        if (!requiredHeat.testBlazeBurner(BlazeBurnerBlock.HeatLevel.KINDLED)) {
            builder
                    .addSlot(RecipeIngredientRole.CATALYST, 153, 81)
                    .addItemStack(AllItems.BLAZE_CAKE.asStack());
        }
    }

    @Override
    public void draw(SalvagingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics graphics, double mouseX, double mouseY) {
        HeatCondition requiredHeat = recipe.getRequiredHeat();

        boolean noHeat = requiredHeat == HeatCondition.NONE;

        int vRows = (1 + recipe.getResults().size()) / 2;

        if (vRows <= 2)
            AllGuiTextures.JEI_DOWN_ARROW.render(graphics, 136, -19 * (vRows - 1) + 32);

        AllGuiTextures shadow = noHeat ? AllGuiTextures.JEI_SHADOW : AllGuiTextures.JEI_LIGHT;
        shadow.render(graphics, 81, 58 + (noHeat ? 10 : 30));

        AllGuiTextures heatBar = noHeat ? AllGuiTextures.JEI_NO_HEAT_BAR : AllGuiTextures.JEI_HEAT_BAR;
        heatBar.render(graphics, 4, 80);
        graphics.drawString(Minecraft.getInstance().font, CreateLang.translateDirect(requiredHeat.getTranslationKey()), 9,
                86, requiredHeat.getColor(), false);

        if (requiredHeat != HeatCondition.NONE)
            heater.withHeat(requiredHeat.visualizeAsBlazeBurner())
                    .draw(graphics, getBackground().getWidth() / 2 + 3, 55);
        mixer.draw(graphics, getBackground().getWidth() / 2 + 3, 34);
    }

}

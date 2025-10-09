package com.elitemastereric.createmelting.mixin;

import com.elitemastereric.createmelting.CreateMelting;
import com.elitemastereric.createmelting.content.salvaging.SalvagingRecipe;
import com.simibubi.create.content.processing.basin.BasinBlockEntity;
import com.simibubi.create.content.processing.basin.BasinRecipe;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.item.crafting.Recipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@Mixin(BasinRecipe.class)
public class BasinRecipeMixin {
    @Inject(method = "apply(Lcom/simibubi/create/content/processing/basin/BasinBlockEntity;Lnet/minecraft/world/item/crafting/Recipe;Z)Z", at = @At("HEAD"), cancellable = true)
    private static void onApply(BasinBlockEntity basin, Recipe<?> recipe, boolean test, CallbackInfoReturnable<Boolean> cir) {
        if (recipe instanceof SalvagingRecipe salvagingRecipe) {
            CreateMelting.LOGGER.info("Injecting into Basin recipe handler...");
            boolean result = SalvagingRecipe.apply(basin, salvagingRecipe, test);

            cir.cancel();
            cir.setReturnValue(result);
        }
    }
}



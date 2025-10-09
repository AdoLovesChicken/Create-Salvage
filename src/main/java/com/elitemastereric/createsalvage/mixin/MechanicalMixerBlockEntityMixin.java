package com.elitemastereric.createsalvage.mixin;

import com.elitemastereric.createsalvage.SalvagingRecipeTypes;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerBlockEntity;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@Mixin(MechanicalMixerBlockEntity.class)
public class MechanicalMixerBlockEntityMixin {
    @Inject(method = "matchStaticFilters", at = @At("RETURN"), cancellable = true)
    private void onMatchStaticFilters(RecipeHolder<? extends Recipe<?>> recipe, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue() == true) return;

        Recipe<?> r = recipe.value();
        if (r.getType() == SalvagingRecipeTypes.SALVAGING.getType()) {
            cir.setReturnValue(true);
        }
    }
}

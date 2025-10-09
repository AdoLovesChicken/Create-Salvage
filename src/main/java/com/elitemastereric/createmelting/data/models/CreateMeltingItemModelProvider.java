package com.elitemastereric.createmelting.data.models;

import com.elitemastereric.createmelting.CreateMelting;
import com.elitemastereric.createmelting.ModItems;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CreateMeltingItemModelProvider extends ItemModelProvider {
    public CreateMeltingItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CreateMelting.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.DIAMOND_NUGGET.get());
    }
}

package com.elitemastereric.createsalvage.data.models;

import com.elitemastereric.createsalvage.ModItems;
import com.elitemastereric.createsalvage.CreateSalvage;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CreateSalvageItemModelProvider extends ItemModelProvider {
    public CreateSalvageItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CreateSalvage.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.DIAMOND_NUGGET.get());
    }
}

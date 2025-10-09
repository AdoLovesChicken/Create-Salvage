package com.elitemastereric.createsalvage.data.tags;

import com.elitemastereric.createsalvage.CreateSalvage;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.CompletableFuture;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CreateSalvageBlockTagProvider extends BlockTagsProvider {
    public CreateSalvageBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper helper) {
        super(output, lookupProvider, CreateSalvage.MOD_ID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // No blocks :P
    }

    @Override
    public String getName() {
        return "Create: Salvage Block Tags";
    }
}
package com.elitemastereric.createmelting.data.tags;

import com.elitemastereric.createmelting.CreateMelting;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.CompletableFuture;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CreateMeltingBlockTagProvider extends BlockTagsProvider {
    public CreateMeltingBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper helper) {
        super(output, lookupProvider, CreateMelting.MODID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // No blocks :P
    }

    @Override
    public String getName() {
        return "Create: Melting Block Tags";
    }
}
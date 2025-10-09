package com.elitemastereric.createsalvage.data.tags;

import com.elitemastereric.createsalvage.ModItems;
import com.elitemastereric.createsalvage.ModTags;
import com.elitemastereric.createsalvage.CreateSalvage;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.CompletableFuture;

import net.neoforged.neoforge.common.Tags;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CreateSalvageItemTagProvider extends ItemTagsProvider {
    public CreateSalvageItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                        CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, CreateSalvage.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(Tags.Items.NUGGETS)
                .add(ModItems.DIAMOND_NUGGET.get());

        tag(ModTags.Items.DIAMOND_NUGGETS)
                .add(ModItems.DIAMOND_NUGGET.get());
    }

    @Override
    public String getName() {
        return "Create: Salvage Item Tags";
    }
}

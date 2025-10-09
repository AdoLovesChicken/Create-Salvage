package com.elitemastereric.createmelting.data.tags;

import com.elitemastereric.createmelting.CreateMelting;
import com.elitemastereric.createmelting.ModItems;
import com.elitemastereric.createmelting.ModTags;
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
public class CreateMeltingItemTagProvider extends ItemTagsProvider {
    public CreateMeltingItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                        CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, CreateMelting.MODID, existingFileHelper);
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
        return "Create: Melting Item Tags";
    }
}

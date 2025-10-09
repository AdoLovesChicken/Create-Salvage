package com.elitemastereric.createmelting.data;

import com.elitemastereric.createmelting.CreateMelting;
import com.elitemastereric.createmelting.data.models.CreateMeltingItemModelProvider;
import com.elitemastereric.createmelting.data.recipe.CreateMeltingRecipeProvider;
import com.elitemastereric.createmelting.data.tags.CreateMeltingBlockTagProvider;
import com.elitemastereric.createmelting.data.tags.CreateMeltingItemTagProvider;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.CompletableFuture;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(CreateMelting.MODID)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = CreateMelting.MODID)
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CreateMeltingDatagen {
    @SubscribeEvent
    public static void onDataGen(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        // Some client setup code
        CreateMelting.LOGGER.info("Initializing Create: Melting Salvage for data generation...");

        // Tags
        CreateMeltingBlockTagProvider blockTagProvider = new CreateMeltingBlockTagProvider(output, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTagProvider);
        CreateMeltingItemTagProvider itemTagProvider = new CreateMeltingItemTagProvider(output, lookupProvider, blockTagProvider.contentsGetter(), existingFileHelper);
        generator.addProvider(event.includeServer(), itemTagProvider);

        // Recipes
        generator.addProvider(event.includeServer(), new CreateMeltingRecipeProvider(output, lookupProvider));
        CreateMeltingRecipeProvider.registerProcessingRecipes(generator, output, lookupProvider);

        // Models
        generator.addProvider(event.includeClient(), new CreateMeltingItemModelProvider(output, existingFileHelper));
    }
}

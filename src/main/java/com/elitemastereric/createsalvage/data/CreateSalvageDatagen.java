package com.elitemastereric.createsalvage.data;

import com.elitemastereric.createsalvage.CreateSalvage;
import com.elitemastereric.createsalvage.data.models.CreateSalvageItemModelProvider;
import com.elitemastereric.createsalvage.data.recipe.CreateSalvageRecipeProvider;
import com.elitemastereric.createsalvage.data.tags.CreateSalvageBlockTagProvider;
import com.elitemastereric.createsalvage.data.tags.CreateSalvageItemTagProvider;
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
@Mod(CreateSalvage.MOD_ID)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = com.elitemastereric.createsalvage.CreateSalvage.MOD_ID)
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CreateSalvageDatagen {
    @SubscribeEvent
    public static void onDataGen(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        // Some client setup code
        CreateSalvage.LOGGER.info("Initializing Create: Salvage for data generation...");

        // Tags
        CreateSalvageBlockTagProvider blockTagProvider = new CreateSalvageBlockTagProvider(output, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTagProvider);
        CreateSalvageItemTagProvider itemTagProvider = new CreateSalvageItemTagProvider(output, lookupProvider, blockTagProvider.contentsGetter(), existingFileHelper);
        generator.addProvider(event.includeServer(), itemTagProvider);

        // Recipes
        generator.addProvider(event.includeServer(), new CreateSalvageRecipeProvider(output, lookupProvider));
        CreateSalvageRecipeProvider.registerProcessingRecipes(generator, output, lookupProvider);

        // Models
        generator.addProvider(event.includeClient(), new CreateSalvageItemModelProvider(output, existingFileHelper));
    }
}

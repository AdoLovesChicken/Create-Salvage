package com.elitemastereric.createmelting;

import net.minecraft.MethodsReturnNonnullByDefault;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import javax.annotation.ParametersAreNonnullByDefault;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(CreateMelting.MODID)
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CreateMelting {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "create_melting";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public CreateMelting(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for mod loading
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::onBuildCreativeTab);

        // Register ourselves for server and other game events we are interested in.
        NeoForge.EVENT_BUS.register(this);
        ModItems.register(modEventBus);
        SalvagingRecipeTypes.register(modEventBus);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("Initializing Create: Melting Salvage...");
    }

    private void onBuildCreativeTab(BuildCreativeModeTabContentsEvent event) {
        ModItems.buildCreativeTab(event);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("Initializing Create: Melting Salvage on the server...");
    }
}

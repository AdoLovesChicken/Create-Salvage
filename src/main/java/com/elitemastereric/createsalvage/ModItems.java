package com.elitemastereric.createsalvage;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    // Create a Deferred Register to hold Items which will all be registered under the "create_melting" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreateSalvage.MOD_ID);

    public static final DeferredItem<Item> DIAMOND_NUGGET = ITEMS.registerSimpleItem("diamond_nugget", new Item.Properties());

    public static void register(IEventBus modEventBus) {
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
    }

    public static void buildCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(DIAMOND_NUGGET);
        }
    }
}
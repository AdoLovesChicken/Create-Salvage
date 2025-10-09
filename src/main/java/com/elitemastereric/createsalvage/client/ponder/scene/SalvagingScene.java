package com.elitemastereric.createsalvage.client.ponder.scene;

import com.elitemastereric.createsalvage.CreateSalvage;
import com.simibubi.create.AllItems;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerBlockEntity;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.PonderPalette;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import net.neoforged.fml.loading.LoadingModList;

import static com.elitemastereric.createsalvage.client.ponder.scene.SecretScene.secretScene;

public class SalvagingScene {
    static final RandomSource RANDOM = RandomSource.create();

    static final float SECRET_ODDS = 1/8F;

    static final Item[] SALVAGE_INPUTS = {
            Items.IRON_PICKAXE,
            Items.IRON_AXE,
            Items.IRON_SHOVEL,
            Items.IRON_SWORD,
            Items.IRON_HOE,
            Items.IRON_HELMET,
            Items.IRON_CHESTPLATE,
            Items.IRON_LEGGINGS,
            Items.IRON_BOOTS,

            Items.GOLDEN_PICKAXE,
            Items.GOLDEN_AXE,
            Items.GOLDEN_SHOVEL,
            Items.GOLDEN_SWORD,
            Items.GOLDEN_HOE,
            Items.GOLDEN_HELMET,
            Items.GOLDEN_CHESTPLATE,
            Items.GOLDEN_LEGGINGS,
            Items.GOLDEN_BOOTS,
    };

    static final Item[] SALVAGE_OUTPUTS = {
            Items.IRON_INGOT,
            Items.IRON_INGOT,
            Items.IRON_INGOT,
            Items.IRON_INGOT,
            Items.IRON_INGOT,
            Items.IRON_INGOT,
            Items.IRON_INGOT,
            Items.IRON_INGOT,
            Items.IRON_INGOT,

            Items.GOLD_INGOT,
            Items.GOLD_INGOT,
            Items.GOLD_INGOT,
            Items.GOLD_INGOT,
            Items.GOLD_INGOT,
            Items.GOLD_INGOT,
            Items.GOLD_INGOT,
            Items.GOLD_INGOT,
            Items.GOLD_INGOT,
    };

    static final Item[] SUPERHEATED_SALVAGE_INPUTS = {
            Items.DIAMOND_PICKAXE,
            Items.DIAMOND_AXE,
            Items.DIAMOND_SHOVEL,
            Items.DIAMOND_SWORD,
            Items.DIAMOND_HOE,
            Items.DIAMOND_HELMET,
            Items.DIAMOND_CHESTPLATE,
            Items.DIAMOND_LEGGINGS,
            Items.DIAMOND_BOOTS,

            Items.NETHERITE_PICKAXE,
            Items.NETHERITE_AXE,
            Items.NETHERITE_SHOVEL,
            Items.NETHERITE_SWORD,
            Items.NETHERITE_HOE,
            Items.NETHERITE_HELMET,
            Items.NETHERITE_CHESTPLATE,
            Items.NETHERITE_LEGGINGS,
            Items.NETHERITE_BOOTS,
    };

    static final Item[] SUPERHEATED_SALVAGE_OUTPUTS = {
            Items.DIAMOND,
            Items.DIAMOND,
            Items.DIAMOND,
            Items.DIAMOND,
            Items.DIAMOND,
            Items.DIAMOND,
            Items.DIAMOND,
            Items.DIAMOND,
            Items.DIAMOND,

            Items.NETHERITE_SCRAP,
            Items.NETHERITE_SCRAP,
            Items.NETHERITE_SCRAP,
            Items.NETHERITE_SCRAP,
            Items.NETHERITE_SCRAP,
            Items.NETHERITE_SCRAP,
            Items.NETHERITE_SCRAP,
            Items.NETHERITE_SCRAP,
            Items.NETHERITE_SCRAP,
    };

    public static void mainScene(SceneBuilder builder, SceneBuildingUtil util) {
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);

        CreateSalvage.LOGGER.info("Building Salvaging Ponder scene...");

        final int BASE_PLATE_WIDTH = 5;
        final int ROTATION_SPEED = 16;

        // Choose salvage inputs/outputs randomly for extra flavor.
        int salvageIndex = RANDOM.nextInt(SALVAGE_INPUTS.length);
        int superheatedSalvageIndex = RANDOM.nextInt(SUPERHEATED_SALVAGE_INPUTS.length);
        ItemStack inputItem = new ItemStack(SALVAGE_INPUTS[salvageIndex]);
        ItemStack inputItem2 = new ItemStack(SUPERHEATED_SALVAGE_INPUTS[superheatedSalvageIndex]);
        ItemStack outputItem = new ItemStack(SALVAGE_OUTPUTS[salvageIndex]);
        ItemStack outputItem2 = new ItemStack(SUPERHEATED_SALVAGE_OUTPUTS[superheatedSalvageIndex]);

        scene.title("salvaging.main", "Introduction to Salvaging");
        scene.configureBasePlate(0, 0, BASE_PLATE_WIDTH);
        scene.scaleSceneView(1.0F); // Zoom level: 1.0x
        scene.showBasePlate();
        // Wait 0.5 seconds before per
        scene.idle(10);

        // NOTE: idle() is a BLOCKING instruction, and other functions will execute immediately.
        // For example, calling showText() and then more instructions will show the text while those instructions execute.

        // Use attachKeyFrame() to set a bookmarked point players can jump to.
        // North is top-right.

        // Compute important landmarks.
        BlockPos basinPos = util.grid().at(2, 2, 2);
        Vec3 basinTopPos = util.vector().topOf(basinPos);
        BlockPos blazeBurnerPos = basinPos.below();
        Vec3 blazeBurnerTopPos = util.vector().topOf(blazeBurnerPos);
        Vec3 blazeBurnerBottomPos = util.vector().topOf(blazeBurnerPos.below());
        BlockPos mixerPos = basinPos.above(2);
        Vec3 mixerTopPos = util.vector().topOf(mixerPos);

        BlockPos beltStart = util.grid().at(4, 1, 1);
        BlockPos beltEnd = util.grid().at(0, 1, 1);
        BlockPos beltOutput = util.grid().at(2, 1, 1);
        Vec3 beltOutputTop = util.vector().topOf(beltOutput);

        BlockPos gearsRodStart = util.grid().at(4, 1, 2);
        BlockPos gearsRodEnd = util.grid().at(4, 1, 5);
        BlockPos outsideGear = util.grid().at(3, 0, 5);
        BlockPos horizontalGear = util.grid().at(3, 1, 4);
        BlockPos verticalRodStart = util.grid().at(2, 1, 3);
        BlockPos verticalRodEnd = util.grid().at(2, 4, 3);

        // STEP 1: Display basin only
        scene.world().showSection(util.select().position(basinPos), Direction.UP);

        scene.overlay().showText(80)
                .pointAt(basinTopPos)
                .placeNearTarget()
                .attachKeyFrame()
                .sharedText(lang("main.text_1"));
        scene.idle(80);

        // STEP 2: Add rotation to mixer
        scene.world().showSection(util.select().fromTo(gearsRodStart, gearsRodEnd), Direction.NORTH);
        scene.world().setKineticSpeed(util.select().fromTo(gearsRodStart, gearsRodEnd), ROTATION_SPEED * 2);
        scene.idle(5);
        scene.world().showSection(util.select().position(outsideGear), Direction.NORTH);
        scene.world().setKineticSpeed(util.select().position(outsideGear), -ROTATION_SPEED);
        scene.idle(5);
        scene.world().showSection(util.select().position(horizontalGear), Direction.DOWN);
        scene.world().setKineticSpeed(util.select().position(horizontalGear), ROTATION_SPEED);
        scene.idle(5);
        scene.world().showSection(util.select().fromTo(verticalRodStart, verticalRodEnd), Direction.DOWN);
        scene.world().setKineticSpeed(util.select().fromTo(verticalRodStart, verticalRodEnd), -ROTATION_SPEED * 2);
        scene.idle(5);

        // STEP 3: Show mixer
        scene.world().showSection(util.select().position(mixerPos), Direction.SOUTH);
        scene.overlay().showText(80)
                .pointAt(mixerTopPos)
                .placeNearTarget()
                .sharedText(lang("main.text_2"));
        scene.world().setKineticSpeed(util.select().position(mixerPos), ROTATION_SPEED * 2);
        scene.idle(5);

        // STEP 4: Show Blaze Burner
        scene.world().showSection(util.select().position(blazeBurnerPos), Direction.WEST);
        scene.overlay().showText(80)
                .pointAt(blazeBurnerTopPos)
                .placeNearTarget()
                .attachKeyFrame()
                .sharedText(lang("main.text_3"));
        scene.idle(10);
        scene.world().modifyBlock(blazeBurnerPos, s -> s.setValue(BlazeBurnerBlock.HEAT_LEVEL, BlazeBurnerBlock.HeatLevel.KINDLED), false);

        scene.idle(80);

        // STEP 5: Show belt, and prompt to add item.
        scene.world().showSection(util.select().fromTo(beltStart, beltEnd), Direction.SOUTH);
        scene.world().setKineticSpeed(util.select().fromTo(beltStart, beltEnd), ROTATION_SPEED * 2);
        scene.overlay().showText(80)
                .pointAt(basinTopPos)
                .placeNearTarget()
                .sharedText(lang("main.text_4"));
        scene.idle(80);

        // STEP 6: Add the item and mix.
        scene.overlay().showControls(util.vector().topOf(basinPos), Pointing.LEFT, 30).withItem(inputItem);
        scene.idle(30);
        Class<MechanicalMixerBlockEntity> type = MechanicalMixerBlockEntity.class;
        scene.world().modifyBlockEntity(mixerPos, type, MechanicalMixerBlockEntity::startProcessingBasin);
        scene.world().createItemOnBeltLike(basinPos, Direction.UP, inputItem);
        scene.idle(80);

        // STEP 7: Mixing is done.
        scene.idle(4);
        scene.world().removeItemsFromBelt(basinPos);
        scene.world().createItemOnBelt(beltOutput, Direction.UP, outputItem);
        scene.overlay().showText(120)
                .pointAt(beltOutputTop)
                .placeNearTarget()
                .attachKeyFrame()
                .sharedText(lang("main.text_5"));

        scene.idle(120);

        // STEP 8: Clarify damaged tools.
        scene.overlay().showText(80)
                .independent()
                .attachKeyFrame()
                .sharedText(lang("main.text_6"));
        scene.idle(80);

        if (isSecretSceneAvailable() && RANDOM.nextFloat() < SECRET_ODDS) {
            // As long as we don't actually call a function from this class
            // while the necessary mods aren't installed, we're fine.
            SecretScene.secretScene(builder, scene, util);
            return;
        };

        // STEP 9: Superheat the Blaze Burner.
        scene.idle(20);
        scene.rotateCameraY(-30);
        scene.overlay().showControls(blazeBurnerBottomPos, Pointing.LEFT, 30).rightClick()
                .withItem(AllItems.BLAZE_CAKE.asStack());
        scene.idle(7);
        scene.world().modifyBlock(blazeBurnerPos, s -> s.setValue(BlazeBurnerBlock.HEAT_LEVEL, BlazeBurnerBlock.HeatLevel.SEETHING), false);
        scene.idle(20);

        scene.overlay().showText(80)
                .attachKeyFrame()
                .colored(PonderPalette.MEDIUM)
                .sharedText(lang("main.text_7"))
                .pointAt(util.vector().blockSurface(blazeBurnerPos, Direction.WEST))
                .placeNearTarget();
        scene.idle(90);

        // STEP 10: Salvage a high level tool.
        scene.rotateCameraY(30);
        scene.overlay().showText(80)
                .pointAt(basinTopPos)
                .placeNearTarget()
                .attachKeyFrame()
                .sharedText(lang("main.text_8"));
        scene.idle(80);

        // STEP 11: Insert the item.
        scene.overlay().showControls(util.vector().topOf(basinPos), Pointing.LEFT, 30).withItem(inputItem2);
        scene.idle(30);
        scene.world().modifyBlockEntity(mixerPos, type, MechanicalMixerBlockEntity::startProcessingBasin);
        scene.world().createItemOnBeltLike(basinPos, Direction.UP, inputItem2);
        scene.idle(80);

        // STEP 12: Mixing is done.
        scene.idle(4);
        scene.world().removeItemsFromBelt(basinPos);
        scene.world().createItemOnBelt(beltOutput, Direction.UP, outputItem2);
        scene.overlay().showText(120)
                .pointAt(beltOutputTop)
                .placeNearTarget()
                .attachKeyFrame()
                .sharedText(lang("main.text_9"));
        scene.idle(120);
    }

    static boolean isSecretSceneAvailable() {
        return LoadingModList.get().getModFileById("goblintraders") != null;
    }

    static ResourceLocation lang(String key) {
        return ResourceLocation.fromNamespaceAndPath(CreateSalvage.MOD_ID, "salvaging." + key);
    }
}


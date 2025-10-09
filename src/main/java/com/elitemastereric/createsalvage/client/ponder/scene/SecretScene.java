package com.elitemastereric.createsalvage.client.ponder.scene;

import com.elitemastereric.createsalvage.CreateSalvage;
import com.simibubi.create.AllItems;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

public class SecretScene {
    static void secretScene(SceneBuilder ignoredBuilder, CreateSceneBuilder scene, SceneBuildingUtil util) {
        Vec3 goblinPos = util.vector().of(3, 1.0, 3.5);
        Vec3 goblinUIPos = util.vector().of(3, 1.5, 3.5);

        // Compute important landmarks.
        BlockPos basinPos = util.grid().at(2, 2, 2);
        BlockPos blazeBurnerPos = basinPos.below();
        Vec3 blazeBurnerBottomPos = util.vector().topOf(blazeBurnerPos.below());
        BlockPos mixerPos = basinPos.above(2);

        BlockPos beltStart = util.grid().at(4, 1, 1);
        BlockPos beltEnd = util.grid().at(0, 1, 1);

        BlockPos gearsRodStart = util.grid().at(4, 1, 2);
        BlockPos gearsRodEnd = util.grid().at(4, 1, 5);
        BlockPos outsideGear = util.grid().at(3, 0, 5);
        BlockPos horizontalGear = util.grid().at(3, 1, 4);
        BlockPos verticalRodStart = util.grid().at(2, 1, 3);
        BlockPos verticalRodEnd = util.grid().at(2, 4, 3);

        scene.world().createEntity(w -> {
            com.mrcrayfish.goblintraders.entity.GoblinTrader entity = com.mrcrayfish.goblintraders.core.ModEntities.GOBLIN_TRADER.get().create(w);
            if (entity == null) return null;

            entity.setPos(goblinPos.x, goblinPos.y, goblinPos.z);
            entity.xo = goblinPos.x;
            entity.yo = goblinPos.y;
            entity.zo = goblinPos.z;

            entity.walkAnimation.update(-entity.walkAnimation.position(), 1);
            entity.walkAnimation.setSpeed(1);

            entity.yRotO = 90;
            entity.setYRot(90);
            entity.yHeadRotO = 90;
            entity.yHeadRot = 90;

            return entity;
        });

        scene.idle(20);
        scene.rotateCameraY(-30);
        scene.overlay().showControls(blazeBurnerBottomPos, Pointing.LEFT, 30).rightClick()
                .withItem(AllItems.BLAZE_CAKE.asStack());
        scene.idle(7);
        scene.world().modifyBlock(blazeBurnerPos, s -> s.setValue(BlazeBurnerBlock.HEAT_LEVEL, BlazeBurnerBlock.HeatLevel.SEETHING), false);
        scene.idle(20);

        scene.overlay().showText(80)
                .attachKeyFrame()
                .sharedText(lang("secret.text_7"))
                .independent();
        scene.idle(80);

        scene.overlay().showText(80)
                .sharedText(lang("secret.text_8"))
                .pointAt(goblinUIPos)
                .placeNearTarget();
        scene.idle(80);

        scene.overlay().showText(80)
                .sharedText(lang("secret.text_9"))
                .independent();
        scene.idle(80);

        // Hide scene elements.
        scene.world().hideSection(util.select().fromTo(beltStart, beltEnd), Direction.NORTH);
        scene.idle(5);
        scene.world().hideSection(util.select().position(blazeBurnerPos), Direction.EAST);
        scene.idle(5);
        scene.world().hideSection(util.select().position(mixerPos), Direction.NORTH);
        scene.idle(5);
        scene.world().hideSection(util.select().position(basinPos), Direction.UP);
        scene.idle(5);
        scene.world().hideSection(util.select().fromTo(verticalRodStart, verticalRodEnd), Direction.UP);
        scene.idle(5);
        scene.world().hideSection(util.select().fromTo(gearsRodStart, gearsRodEnd), Direction.SOUTH);
        scene.idle(5);
        scene.world().hideSection(util.select().position(outsideGear), Direction.SOUTH);
        scene.idle(5);
        scene.world().hideSection(util.select().position(horizontalGear), Direction.UP);
        scene.idle(5);

        scene.overlay().showText(80)
                .sharedText(lang("secret.text_10"))
                .pointAt(goblinUIPos)
                .independent();
        scene.idle(80);

        scene.overlay().showText(120)
                .sharedText(lang("secret.text_11"))
                .independent();
        scene.idle(120);

        scene.idle(60);

        scene.overlay().showText(80)
                .sharedText(lang("secret.text_12"))
                .independent();
        scene.idle(80);

        scene.overlay().showText(120)
                .sharedText(lang("secret.text_13"))
                .independent();
        scene.idle(120);

        scene.overlay().showText(120)
                .sharedText(lang("secret.text_14"))
                .independent();
        scene.idle(120);

        scene.overlay().showText(120)
                .sharedText(lang("secret.text_15"))
                .independent();
        scene.idle(120);

        scene.overlay().showText(120)
                .sharedText(lang("secret.text_16"))
                .independent();
        scene.idle(120);

        scene.overlay().showText(120)
                .sharedText(lang("secret.text_17"))
                .independent();
        scene.idle(120);
    }

    static ResourceLocation lang(String key) {
        return ResourceLocation.fromNamespaceAndPath(CreateSalvage.MOD_ID, "salvaging." + key);
    }
}

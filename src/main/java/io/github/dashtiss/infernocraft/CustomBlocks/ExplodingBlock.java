package io.github.dashtiss.infernocraft.CustomBlocks;

import io.github.dashtiss.infernocraft.effects.ExplosionEffect;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExplodingBlock extends Block {

    private static final Logger LOGGER = LogManager.getLogger();

    public ExplodingBlock(Settings settings) {
        super(settings);
    }




    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        LOGGER.info("ExplodingBlock used by player with item: " + player.getActiveItem());
        if (player.isInSneakingPose())
            triggerExplosionEffect(world, pos);
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (player.isInSneakingPose())
            triggerExplosionEffect(world, pos);
        super.onBreak(world, pos, state, player);
    }

    private void triggerExplosionEffect(World world, BlockPos pos) {
        final int explosionPower = 120; // Explosion power
        final int CHAIN_RADIUS = 2; // Radius to check for chain reactions


        ExplosionEffect.spawnExplosion(world, pos.toCenterPos());

        // Trigger nearby ExplodingBlocks
        for (BlockPos nearbyPos : BlockPos.iterate(
                pos.add(-CHAIN_RADIUS, -CHAIN_RADIUS, -CHAIN_RADIUS),
                pos.add(CHAIN_RADIUS, CHAIN_RADIUS, CHAIN_RADIUS))) {

            if (nearbyPos.equals(pos)) continue; // skip self

            BlockState state = world.getBlockState(nearbyPos);
            if (state.getBlock() instanceof ExplodingBlock) {
                // Remove first to avoid infinite recursion
                world.removeBlock(nearbyPos, false);
                ((ExplodingBlock) state.getBlock()).explodeBlock(world, nearbyPos);
            }
        }

        if (world instanceof ServerWorld serverWorld) {
            Explosion explosion = new Explosion(world, null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                    explosionPower, false, Explosion.DestructionType.DESTROY);
            explosion.collectBlocksAndDamageEntities();
            explosion.affectWorld(false);
            // serverWorld.removeBlock(pos, false);
        }
    }

    public void explodeBlock(World world, BlockPos pos) {
        triggerExplosionEffect(world, pos);
    }
}

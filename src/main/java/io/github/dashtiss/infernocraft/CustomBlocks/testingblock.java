package io.github.dashtiss.infernocraft.CustomBlocks;

import io.github.dashtiss.infernocraft.effects.ExplosionEffect;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class testingblock extends Block {

    public testingblock(Settings settings) {
        super(settings);
    }


    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        ExplosionEffect.spawnExplosion(world, pos.toCenterPos());
        world.removeBlock(pos, false);
        // super.onPlaced(world, pos, state, placer, itemStack);
    }
}

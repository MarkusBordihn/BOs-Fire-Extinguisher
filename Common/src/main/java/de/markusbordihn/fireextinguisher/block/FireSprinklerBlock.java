/*
 * Copyright 2023 Markus Bordihn
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package de.markusbordihn.fireextinguisher.block;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FireSprinklerBlock extends AbstractFireAlarmSignalBlock {

  public static final String NAME = "fire_sprinkler";
  protected static final VoxelShape UP_AABB = Block.box(7, 13.325, 7, 9, 16, 9);
  private static final Random random = new Random();

  public FireSprinklerBlock(Properties properties) {
    super(properties);
  }

  public static void stopFire(ServerLevel serverLevel, BlockPos targetBlockPos) {
    Iterable<BlockPos> blockPositions =
        BlockPos.withinManhattan(
            targetBlockPos.below(),
            COMMON.fireSprinklerRadiusX.get(),
            COMMON.fireSprinklerRadiusY.get(),
            COMMON.fireSprinklerRadiusZ.get());
    for (BlockPos blockPos : blockPositions) {
      BlockState blockState = serverLevel.getBlockState(blockPos);

      // Remove fire block
      if (blockState.is(Blocks.FIRE)) {
        serverLevel.removeBlock(blockPos, false);
        serverLevel.playSound(
            null, blockPos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0f, 1.0f);
      } else if (blockState.is(Blocks.CAMPFIRE)
          && blockState.getBlock() instanceof CampfireBlock
          && Boolean.TRUE.equals(blockState.getValue(CampfireBlock.LIT))) {
        serverLevel.setBlockAndUpdate(blockPos, blockState.setValue(CampfireBlock.LIT, false));
      }
    }
  }

  @Override
  @Nullable
  public BlockState getStateForPlacement(BlockPlaceContext context) {
    BlockPos blockPos = context.getClickedPos();
    Level level = context.getLevel();
    // Allow only to place the block on the ceiling
    if (context.getClickedFace() != Direction.DOWN) {
      return null;
    }

    // Make sure that the block is not placed below an air block.
    if (blockPos.getY() < level.getMaxBuildHeight() - 1
        && !level.getBlockState(blockPos.above()).isAir()) {
      return this.defaultBlockState()
          .setValue(FACE, AttachFace.CEILING)
          .setValue(FACING, context.getHorizontalDirection());
    }

    return null;
  }

  @Override
  public VoxelShape getShape(
      BlockState blockState,
      BlockGetter blockGetter,
      BlockPos blockPos,
      CollisionContext collisionContext) {
    return UP_AABB;
  }

  @Override
  public void animateTick(
      BlockState blockState, Level level, BlockPos blockPos, RandomSource random) {
    boolean isPowered = blockState.getValue(POWERED);
    if (isPowered) {
      double x = blockPos.getX();
      double y = blockPos.getY();
      double z = blockPos.getZ();

      // Cloud particles to simulate fire extinguisher effect
      for (int i = 0; i < 8; i++) {
        y -= FireSprinklerBlock.random.nextDouble(1);
        if (y == blockPos.getY() - 2) {
          for (int j = 0; j < 4; j++) {
            level.addParticle(
                ParticleTypes.CLOUD,
                x + 0.5,
                y + 0.5,
                z + 0.5,
                FireSprinklerBlock.random.nextDouble(-0.2, 0.2),
                0,
                0);
            level.addParticle(
                ParticleTypes.CLOUD,
                x + 0.5,
                y + 0.5,
                z + 0.5,
                0,
                0,
                FireSprinklerBlock.random.nextDouble(-0.2, 0.2));
            level.addParticle(
                ParticleTypes.CLOUD,
                x + 0.5,
                y + 0.5,
                z + 0.5,
                FireSprinklerBlock.random.nextDouble(-0.2, 0.2),
                0,
                FireSprinklerBlock.random.nextDouble(-0.2, 0.1));
          }
        } else if (y <= blockPos.getY() - 3) {
          for (int j = 0; j < 8; j++) {
            level.addParticle(
                ParticleTypes.CLOUD,
                x + 0.5,
                y + 0.5,
                z + 0.5,
                FireSprinklerBlock.random.nextDouble(-0.5, 0.5),
                0,
                0);
            level.addParticle(
                ParticleTypes.CLOUD,
                x + 0.5,
                y + 0.5,
                z + 0.5,
                0,
                0,
                FireSprinklerBlock.random.nextDouble(-0.5, 0.5));
            level.addParticle(
                ParticleTypes.CLOUD,
                x + 0.5,
                y + 0.5,
                z + 0.5,
                FireSprinklerBlock.random.nextDouble(-0.5, 0.5),
                0,
                FireSprinklerBlock.random.nextDouble(-0.5, 0.5));
          }
        }
        level.addParticle(ParticleTypes.CLOUD, x + 0.5, y + 0.5, z + 0.5, 0, 0, 0);
      }
    }
  }

  @Override
  protected void actionTick(
      BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource random) {
    stopFire(serverLevel, blockPos);
  }
}

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

import de.markusbordihn.fireextinguisher.config.FireExtinguisherConfig;
import de.markusbordihn.fireextinguisher.utils.FireDetection;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FireSprinklerBlock extends AbstractFireAlarmSignalBlock {

  public static final String ID = "fire_sprinkler";

  protected static final VoxelShape UP_AABB = Block.box(7, 13.325, 7, 9, 16, 9);

  public FireSprinklerBlock(Properties properties) {
    super(properties);
  }

  public static void stopFire(ServerLevel serverLevel, BlockPos targetBlockPos) {
    int extinguished =
        FireDetection.extinguishWithin(
            serverLevel,
            targetBlockPos,
            FireExtinguisherConfig.fireSprinklerRadiusX,
            FireExtinguisherConfig.fireSprinklerRadiusY,
            FireExtinguisherConfig.fireSprinklerRadiusZ);
    if (extinguished > 0) {
      serverLevel.playSound(
          null, targetBlockPos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0f, 1.0f);
    }
  }

  private static void addSpreadParticles(
      Level level, double x, double y, double z, double spread, int count, RandomSource random) {
    for (int i = 0; i < count; i++) {
      level.addParticle(ParticleTypes.CLOUD, x, y, z, randomSpread(random, spread), 0, 0);
      level.addParticle(ParticleTypes.CLOUD, x, y, z, 0, 0, randomSpread(random, spread));
      level.addParticle(
          ParticleTypes.CLOUD,
          x,
          y,
          z,
          randomSpread(random, spread),
          0,
          randomSpread(random, spread));
    }
  }

  private static double randomSpread(RandomSource random, double spread) {
    return (random.nextDouble() * 2 - 1) * spread;
  }

  @Override
  public BlockState getStateForPlacement(BlockPlaceContext context) {
    BlockPos blockPos = context.getClickedPos();
    Level level = context.getLevel();
    if (context.getClickedFace() != Direction.DOWN) {
      return null;
    }

    // Make sure that the block is not placed below an air block.
    if (blockPos.getY() < level.getMaxY() - 1 && !level.getBlockState(blockPos.above()).isAir()) {
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
    if (!blockState.getValue(POWERED)) {
      return;
    }

    double x = blockPos.getX() + 0.5;
    double y = blockPos.getY();
    double z = blockPos.getZ() + 0.5;
    for (int i = 0; i < 8; i++) {
      y -= random.nextDouble();
      if (y <= blockPos.getY() - 3) {
        addSpreadParticles(level, x, y + 0.5, z, 0.5, 8, random);
      } else if (y <= blockPos.getY() - 2) {
        addSpreadParticles(level, x, y + 0.5, z, 0.2, 4, random);
      }
      level.addParticle(ParticleTypes.CLOUD, x, y + 0.5, z, 0, 0, 0);
    }
  }

  @Override
  protected void actionTick(
      BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource random) {
    stopFire(serverLevel, blockPos);
  }
}

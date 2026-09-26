/*
 * Copyright 2026 Markus Bordihn
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

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FireAlarmLightBlock extends AbstractFireAlarmSignalBlock {

  public static final String ID = "fire_alarm_light";
  protected static final DustParticleOptions ALARM_PARTICLE =
      new DustParticleOptions(0xFF2619, 1.5F);
  protected static final VoxelShape NORTH_AABB = Block.box(4, 4, 10, 12, 12, 16);
  protected static final VoxelShape EAST_AABB = Block.box(0, 4, 4, 6, 12, 12);
  protected static final VoxelShape SOUTH_AABB = Block.box(4, 4, 0, 12, 12, 6);
  protected static final VoxelShape WEST_AABB = Block.box(10, 4, 4, 16, 12, 12);
  protected static final VoxelShape UP_AABB = Block.box(4, 10, 4, 12, 16, 12);
  protected static final VoxelShape DOWN_AABB = Block.box(4, 0, 4, 12, 6, 12);

  public FireAlarmLightBlock(BlockBehaviour.Properties properties) {
    super(properties);
  }

  public static int getLightEmission(BlockState blockState) {
    return Boolean.TRUE.equals(blockState.getValue(POWERED)) ? 15 : 0;
  }

  @Override
  protected boolean emitsRedstoneSignal() {
    return true;
  }

  @Override
  public VoxelShape getShape(
      BlockState blockState,
      BlockGetter blockGetter,
      BlockPos blockPos,
      CollisionContext collisionContext) {
    return switch (blockState.getValue(FACE)) {
      case FLOOR -> DOWN_AABB;
      case WALL ->
          switch (blockState.getValue(FACING)) {
            case EAST -> EAST_AABB;
            case WEST -> WEST_AABB;
            case SOUTH -> SOUTH_AABB;
            default -> NORTH_AABB;
          };
      default -> UP_AABB;
    };
  }

  @Override
  public void animateTick(
      BlockState blockState, Level level, BlockPos blockPos, RandomSource random) {
    if (!blockState.getValue(POWERED) || random.nextBoolean()) {
      return;
    }

    level.addParticle(
        ALARM_PARTICLE,
        blockPos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.8,
        blockPos.getY() + 0.5 + (random.nextDouble() - 0.5) * 0.8,
        blockPos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.8,
        0.0,
        0.0,
        0.0);
  }
}

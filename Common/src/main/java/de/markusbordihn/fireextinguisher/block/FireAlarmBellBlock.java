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

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FireAlarmBellBlock extends AbstractFireAlarmSignalBlock {

  public static final String NAME = "fire_alarm_bell";
  protected static final ResourceLocation SOUND_EVENT =
      new ResourceLocation("fire_extinguisher:fire_alarm_bell");
  protected static final VoxelShape NORTH_AABB = Block.box(2, 2, 10.5, 14, 14, 16);
  protected static final VoxelShape EAST_AABB = Block.box(0, 2, 2, 5.5, 14, 14);
  protected static final VoxelShape SOUTH_AABB = Block.box(2, 2, 0, 14, 14, 5.5);
  protected static final VoxelShape WEST_AABB = Block.box(10.5, 2, 2, 16, 14, 14);
  protected static final VoxelShape UP_AABB = Block.box(2, 10.5, 2, 14, 16, 14);
  protected static final VoxelShape DOWN_AABB = Block.box(2, 0, 2, 14, 5.5, 14);

  public FireAlarmBellBlock(BlockBehaviour.Properties properties) {
    super(properties);
  }

  @Override
  public VoxelShape getShape(
      BlockState blockState,
      BlockGetter blockGetter,
      BlockPos blockPos,
      CollisionContext collisionContext) {
    return switch (blockState.getValue(FACE)) {
      case FLOOR -> DOWN_AABB;
      case WALL -> switch (blockState.getValue(FACING)) {
        case EAST -> EAST_AABB;
        case WEST -> WEST_AABB;
        case SOUTH -> SOUTH_AABB;
        default -> NORTH_AABB;
      };
      default -> UP_AABB;
    };
  }

  @Override
  public void poweredSoundTick(
      BlockState blockState,
      ServerLevel serverLevel,
      BlockPos blockPos,
      boolean isPowered,
      RandomSource random) {
    if (isPowered) {
      playPoweredSound(SOUND_EVENT, serverLevel, blockPos);
    }
  }
}

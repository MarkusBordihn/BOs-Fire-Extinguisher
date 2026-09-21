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

import de.markusbordihn.fireextinguisher.alarm.FireAlarmNetwork;
import de.markusbordihn.fireextinguisher.config.FireExtinguisherConfig;
import de.markusbordihn.fireextinguisher.utils.FireDetection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FireAlarmSmokeDetectorBlock extends AbstractFireAlarmSignalBlock {

  public static final String NAME = "fire_alarm_smoke_detector";
  public static final BooleanProperty DISARMED = BlockStateProperties.DISARMED;
  protected static final ResourceLocation SOUND_EVENT =
      new ResourceLocation("fire_extinguisher:fire_alarm_smoke_detector");
  protected static final VoxelShape NORTH_AABB = Block.box(5, 5, 14, 11, 11, 16);
  protected static final VoxelShape EAST_AABB = Block.box(0, 5, 5, 2, 11, 11);
  protected static final VoxelShape SOUTH_AABB = Block.box(5, 5, 0, 11, 11, 2);
  protected static final VoxelShape WEST_AABB = Block.box(14, 5, 5, 16, 11, 11);
  protected static final VoxelShape UP_AABB = Block.box(5, 14, 5, 11, 16, 11);
  protected static final VoxelShape DOWN_AABB = Block.box(5, 0, 5, 11, 2, 11);

  public FireAlarmSmokeDetectorBlock(Properties properties) {
    super(properties);
    this.registerDefaultState(this.defaultBlockState().setValue(DISARMED, true));
  }

  public static int getLightEmission(BlockState blockState) {
    if (Boolean.TRUE.equals(blockState.getValue(POWERED))) {
      return 12;
    } else if (!Boolean.TRUE.equals(blockState.getValue(DISARMED))) {
      return 6;
    } else {
      return 2;
    }
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
  public boolean isAlarmTarget() {
    return false;
  }

  @Override
  protected boolean ticksWhileIdle() {
    return true;
  }

  @Override
  protected boolean hasActiveSignal(
      ServerLevel serverLevel, BlockPos blockPos, BlockState blockState) {
    return blockState.getValue(POWERED);
  }

  @Override
  public void neighborChanged(
      BlockState blockState,
      Level level,
      BlockPos blockPos,
      Block block,
      BlockPos unused,
      boolean unused2) {}

  @Override
  protected void checkConditionTick(
      BlockState blockState,
      ServerLevel serverLevel,
      BlockPos blockPos,
      boolean isPowered,
      RandomSource random) {
    BlockState armedState = blockState;
    if (Boolean.TRUE.equals(blockState.getValue(DISARMED))) {
      armedState = blockState.setValue(DISARMED, false);
      serverLevel.setBlock(blockPos, armedState, 3);
    }

    boolean detectedFire =
        FireDetection.hasFireWithin(
            serverLevel,
            blockPos.above(),
            FireExtinguisherConfig.smokeDetectorRadiusX,
            FireExtinguisherConfig.smokeDetectorRadiusY,
            FireExtinguisherConfig.smokeDetectorRadiusZ);
    this.setPowered(serverLevel, blockPos, armedState, detectedFire);
  }

  @Override
  protected void onPowered(ServerLevel serverLevel, BlockPos blockPos, BlockState blockState) {
    this.updateNeighbours(serverLevel, blockPos, blockState);
    FireAlarmNetwork.activateSource(serverLevel, blockPos);
  }

  @Override
  protected void onUnpowered(ServerLevel serverLevel, BlockPos blockPos, BlockState blockState) {
    this.updateNeighbours(serverLevel, blockPos, blockState);
    FireAlarmNetwork.deactivateSource(serverLevel, blockPos);
  }

  @Override
  public void onRemove(
      BlockState blockState,
      Level level,
      BlockPos blockPos,
      BlockState newBlockState,
      boolean isMoving) {
    if (!blockState.is(newBlockState.getBlock())) {
      if (Boolean.TRUE.equals(blockState.getValue(POWERED))) {
        this.updateNeighbours(level, blockPos, blockState);
        if (level instanceof ServerLevel serverLevel) {
          FireAlarmNetwork.deactivateSource(serverLevel, blockPos);
        }
      }
      super.onRemove(blockState, level, blockPos, newBlockState, isMoving);
    }
  }

  @Override
  public boolean isSignalSource(BlockState blockState) {
    return true;
  }

  @Override
  public int getSignal(
      BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
    return Boolean.TRUE.equals(blockState.getValue(POWERED) && !blockState.getValue(DISARMED))
        ? 15
        : 0;
  }

  @Override
  public int getDirectSignal(
      BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
    return Boolean.TRUE.equals(blockState.getValue(POWERED) && !blockState.getValue(DISARMED))
            && getConnectedDirection(blockState) == direction
        ? 15
        : 0;
  }

  @Override
  public boolean isRandomlyTicking(BlockState blockState) {
    return true;
  }

  @Override
  public void poweredSoundTick(
      BlockState blockState,
      ServerLevel serverLevel,
      BlockPos blockPos,
      boolean isPowered,
      RandomSource random) {
    if (blockState.getValue(POWERED)) {
      playPoweredSound(SOUND_EVENT, serverLevel, blockPos);
    }
  }

  @Override
  protected void createBlockStateDefinition(
      StateDefinition.Builder<Block, BlockState> stateDefinition) {
    super.createBlockStateDefinition(stateDefinition);
    stateDefinition.add(DISARMED);
  }
}

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

import com.mojang.serialization.MapCodec;
import de.markusbordihn.fireextinguisher.alarm.FireAlarmNetwork;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.redstone.Orientation;

public abstract class AbstractFireAlarmSignalBlock extends FaceAttachedHorizontalDirectionalBlock {

  public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
  public static final int ACTION_TICK_INTERVAL = 80;
  protected static final int SIGNAL_TICK_DELAY = 4;

  protected AbstractFireAlarmSignalBlock(BlockBehaviour.Properties properties) {
    super(properties);
    this.registerDefaultState(
        this.stateDefinition
            .any()
            .setValue(FACING, Direction.NORTH)
            .setValue(POWERED, false)
            .setValue(FACE, AttachFace.WALL));
  }

  public static int getLightEmission(BlockState blockState) {
    return Boolean.TRUE.equals(blockState.getValue(POWERED)) ? 15 : 2;
  }

  private static boolean isAlarmNetworkOutput(BlockState blockState) {
    return blockState.getBlock() instanceof AbstractFireAlarmSignalBlock alarmBlock
        && alarmBlock.emitsRedstoneSignal();
  }

  @Override
  protected MapCodec<? extends FaceAttachedHorizontalDirectionalBlock> codec() {
    return null;
  }

  public boolean isAlarmTarget() {
    return true;
  }

  protected boolean ticksWhileIdle() {
    return false;
  }

  protected boolean hasActiveSignal(ServerLevel serverLevel, BlockPos blockPos, BlockState state) {
    return this.hasRedstoneSignal(serverLevel, blockPos, state)
        || FireAlarmNetwork.isCoveredByActivePanel(serverLevel, blockPos);
  }

  protected boolean emitsRedstoneSignal() {
    return false;
  }

  protected boolean hasRedstoneSignal(
      ServerLevel serverLevel, BlockPos blockPos, BlockState blockState) {
    if (!this.emitsRedstoneSignal()) {
      return serverLevel.hasNeighborSignal(blockPos);
    }

    Direction supportDirection = getConnectedDirection(blockState).getOpposite();
    for (Direction direction : Direction.values()) {
      if (direction == supportDirection) {
        continue;
      }

      BlockPos neighbourPos = blockPos.relative(direction);
      if (isAlarmNetworkOutput(serverLevel.getBlockState(neighbourPos))) {
        continue;
      }

      if (serverLevel.getSignal(neighbourPos, direction) > 0) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isSignalSource(BlockState blockState) {
    return this.emitsRedstoneSignal();
  }

  @Override
  public int getSignal(
      BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
    return this.emitsRedstoneSignal() && blockState.getValue(POWERED) ? 15 : 0;
  }

  @Override
  public int getDirectSignal(
      BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
    return this.emitsRedstoneSignal()
            && blockState.getValue(POWERED)
            && getConnectedDirection(blockState) == direction
        ? 15
        : 0;
  }

  protected void updateNeighbours(Level level, BlockPos blockPos, BlockState blockState) {
    level.updateNeighborsAt(blockPos, this);
    level.updateNeighborsAt(
        blockPos.relative(getConnectedDirection(blockState).getOpposite()), this);
  }

  protected void onPowered(ServerLevel serverLevel, BlockPos blockPos, BlockState blockState) {}

  protected void onUnpowered(ServerLevel serverLevel, BlockPos blockPos, BlockState blockState) {}

  protected void actionTick(
      BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource random) {}

  protected void poweredSoundTick(
      BlockState blockState,
      ServerLevel serverLevel,
      BlockPos blockPos,
      boolean isPowered,
      RandomSource random) {}

  protected void checkConditionTick(
      BlockState blockState,
      ServerLevel serverLevel,
      BlockPos blockPos,
      boolean isPowered,
      RandomSource random) {}

  protected void playPoweredSound(
      Identifier resourceLocation, ServerLevel serverLevel, BlockPos blockPos) {
    BuiltInRegistries.SOUND_EVENT
        .getOptional(resourceLocation)
        .ifPresent(
            soundEvent ->
                serverLevel.playSound(null, blockPos, soundEvent, SoundSource.BLOCKS, 0.8F, 1.0F));
  }

  public boolean isPowered(BlockState blockState) {
    return blockState.hasProperty(POWERED) && blockState.getValue(POWERED);
  }

  public void setPowered(
      ServerLevel serverLevel, BlockPos blockPos, BlockState blockState, boolean powered) {
    if (blockState.getValue(POWERED) == powered) {
      return;
    }

    BlockState updatedState = blockState.setValue(POWERED, powered);
    serverLevel.setBlock(blockPos, updatedState, 3);
    if (powered) {
      this.onPowered(serverLevel, blockPos, updatedState);
      serverLevel.scheduleTick(blockPos, this, SIGNAL_TICK_DELAY);
    } else {
      this.onUnpowered(serverLevel, blockPos, updatedState);
    }
    if (this.emitsRedstoneSignal()) {
      this.updateNeighbours(serverLevel, blockPos, updatedState);
    }
  }

  public void reevaluate(ServerLevel serverLevel, BlockPos blockPos) {
    BlockState blockState = serverLevel.getBlockState(blockPos);
    if (blockState.is(this)) {
      this.releaseWithoutSignal(serverLevel, blockPos, blockState);
    }
  }

  private void releaseWithoutSignal(
      ServerLevel serverLevel, BlockPos blockPos, BlockState blockState) {
    if (blockState.getValue(POWERED) && !this.hasActiveSignal(serverLevel, blockPos, blockState)) {
      this.setPowered(serverLevel, blockPos, blockState, false);
    }
  }

  @Override
  public void neighborChanged(
      BlockState blockState,
      Level level,
      BlockPos blockPos,
      Block block,
      Orientation unused,
      boolean unused2) {
    if (!(level instanceof ServerLevel serverLevel)) {
      return;
    }

    if (!blockState.getValue(POWERED)
        && this.hasRedstoneSignal(serverLevel, blockPos, blockState)) {
      this.setPowered(serverLevel, blockPos, blockState, true);
    } else {
      this.releaseWithoutSignal(serverLevel, blockPos, blockState);
    }
  }

  @Override
  public void onPlace(
      BlockState blockState,
      Level level,
      BlockPos blockPos,
      BlockState oldState,
      boolean isMoving) {
    if (!level.isClientSide() && !blockState.is(oldState.getBlock())) {
      level.scheduleTick(blockPos, this, SIGNAL_TICK_DELAY);
    }
    super.onPlace(blockState, level, blockPos, oldState, isMoving);
  }

  @Override
  public void affectNeighborsAfterRemoval(
      BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, boolean movedByPiston) {
    if (this.emitsRedstoneSignal() && blockState.getValue(POWERED)) {
      this.updateNeighbours(serverLevel, blockPos, blockState);
    }
    super.affectNeighborsAfterRemoval(blockState, serverLevel, blockPos, movedByPiston);
  }

  @Override
  protected void createBlockStateDefinition(
      StateDefinition.Builder<Block, BlockState> stateDefinition) {
    stateDefinition.add(FACE, FACING, POWERED);
  }

  @Override
  public void tick(
      BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource random) {
    boolean isPowered = blockState.getValue(POWERED);
    boolean hasActiveSignal = this.hasActiveSignal(serverLevel, blockPos, blockState);
    if (isPowered != hasActiveSignal) {
      this.setPowered(serverLevel, blockPos, blockState, hasActiveSignal);
      if (!hasActiveSignal && this.ticksWhileIdle()) {
        serverLevel.scheduleTick(blockPos, this, ACTION_TICK_INTERVAL);
      }
      return;
    }

    if (isPowered) {
      this.actionTick(blockState, serverLevel, blockPos, random);
    }
    this.poweredSoundTick(blockState, serverLevel, blockPos, isPowered, random);
    this.checkConditionTick(blockState, serverLevel, blockPos, isPowered, random);
    if (isPowered || this.ticksWhileIdle()) {
      serverLevel.scheduleTick(blockPos, this, ACTION_TICK_INTERVAL);
    }
  }

  @Override
  public void randomTick(
      BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource random) {
    this.tick(blockState, serverLevel, blockPos, random);
  }
}

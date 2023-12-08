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

import de.markusbordihn.fireextinguisher.config.CommonConfig;
import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public abstract class AbstractFireAlarmSignalBlock extends FaceAttachedHorizontalDirectionalBlock {

  public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
  public static final int ACTION_TICK_INTERVAL = 80;
  protected static final CommonConfig.Config COMMON = CommonConfig.COMMON;

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

  protected void actionTick(
      BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, Random random) {}

  protected void poweredSoundTick(
      BlockState blockState,
      ServerLevel serverLevel,
      BlockPos blockPos,
      boolean isPowered,
      Random random) {}

  protected void checkConditionTick(
      BlockState blockState,
      ServerLevel serverLevel,
      BlockPos blockPos,
      boolean isPowered,
      Random random) {}

  protected void playPoweredSound(
      ResourceLocation resourceLocation, ServerLevel serverLevel, BlockPos blockPos) {
    Registry.SOUND_EVENT
        .getOptional(resourceLocation)
        .ifPresent(
            soundEvent ->
                serverLevel.playSound(null, blockPos, soundEvent, SoundSource.BLOCKS, 0.8F, 1.0F));
  }

  @Override
  public void neighborChanged(
      BlockState blockState,
      Level level,
      BlockPos blockPos,
      Block block,
      BlockPos unused,
      boolean unused2) {
    if (!level.isClientSide) {
      boolean isPowered = blockState.getValue(POWERED);
      if (isPowered != level.hasNeighborSignal(blockPos) && !isPowered) {
        level.setBlock(blockPos, blockState.cycle(POWERED), 2);
      }
      level.scheduleTick(blockPos, this, 4);
    }
  }

  @Override
  protected void createBlockStateDefinition(
      StateDefinition.Builder<Block, BlockState> stateDefinition) {
    stateDefinition.add(FACE, FACING, POWERED);
  }

  @Override
  public void tick(
      BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, Random random) {

    // Update block state, if block is powered and no neighbor signal is available.
    if (Boolean.TRUE.equals(blockState.getValue(POWERED))
        && !serverLevel.hasNeighborSignal(blockPos)) {
      serverLevel.setBlock(blockPos, blockState.cycle(POWERED), 2);
      return;
    }

    // Execute action tick, powered sound tick and check condition tick.
    boolean isPowered = blockState.getValue(POWERED);
    if (isPowered) {
      this.actionTick(blockState, serverLevel, blockPos, random);
    }
    this.poweredSoundTick(blockState, serverLevel, blockPos, isPowered, random);
    this.checkConditionTick(blockState, serverLevel, blockPos, isPowered, random);
    serverLevel.scheduleTick(blockPos, this, ACTION_TICK_INTERVAL);
  }
}

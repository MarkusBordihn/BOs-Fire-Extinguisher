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

import de.markusbordihn.fireextinguisher.Constants;
import de.markusbordihn.fireextinguisher.alarm.FireAlarmNetwork;
import de.markusbordihn.fireextinguisher.config.FireExtinguisherConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FireAlarmControlPanelBlock extends AbstractFireAlarmSignalBlock {

  public static final String ID = "fire_alarm_control_panel";
  protected static final VoxelShape NORTH_AABB = Block.box(3, 2, 13, 13, 14, 16);
  protected static final VoxelShape EAST_AABB = Block.box(0, 2, 3, 3, 14, 13);
  protected static final VoxelShape SOUTH_AABB = Block.box(3, 2, 0, 13, 14, 3);
  protected static final VoxelShape WEST_AABB = Block.box(13, 2, 3, 16, 14, 13);

  public FireAlarmControlPanelBlock(BlockBehaviour.Properties properties) {
    super(properties);
  }

  public static int getLightEmission(BlockState blockState) {
    return Boolean.TRUE.equals(blockState.getValue(POWERED)) ? 7 : 0;
  }

  @Override
  public boolean isAlarmTarget() {
    return false;
  }

  @Override
  public BlockState getStateForPlacement(BlockPlaceContext context) {
    Direction clickedFace = context.getClickedFace();
    if (!clickedFace.getAxis().isHorizontal()) {
      return null;
    }

    BlockState blockState =
        this.defaultBlockState().setValue(FACE, AttachFace.WALL).setValue(FACING, clickedFace);
    return blockState.canSurvive(context.getLevel(), context.getClickedPos()) ? blockState : null;
  }

  @Override
  public VoxelShape getShape(
      BlockState blockState,
      BlockGetter blockGetter,
      BlockPos blockPos,
      CollisionContext collisionContext) {
    return switch (blockState.getValue(FACING)) {
      case EAST -> EAST_AABB;
      case WEST -> WEST_AABB;
      case SOUTH -> SOUTH_AABB;
      default -> NORTH_AABB;
    };
  }

  @Override
  protected boolean emitsRedstoneSignal() {
    return true;
  }

  @Override
  protected boolean hasActiveSignal(
      ServerLevel serverLevel, BlockPos blockPos, BlockState blockState) {
    return this.hasRedstoneSignal(serverLevel, blockPos, blockState)
        || FireAlarmNetwork.hasActiveSourceWithin(serverLevel, blockPos)
        || (FireExtinguisherConfig.fireAlarmControlPanelLatching && blockState.getValue(POWERED));
  }

  @Override
  protected void onPowered(ServerLevel serverLevel, BlockPos blockPos, BlockState blockState) {
    FireAlarmNetwork.activatePanel(serverLevel, blockPos);
    this.powerTargets(serverLevel, blockPos);
  }

  @Override
  protected void onUnpowered(ServerLevel serverLevel, BlockPos blockPos, BlockState blockState) {
    FireAlarmNetwork.deactivatePanel(serverLevel, blockPos);
    this.releaseTargets(serverLevel, blockPos);
  }

  @Override
  protected void actionTick(
      BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource random) {
    if (FireAlarmNetwork.activatePanel(serverLevel, blockPos)) {
      this.powerTargets(serverLevel, blockPos);
    }
  }

  private void powerTargets(ServerLevel serverLevel, BlockPos panelPos) {
    for (BlockPos targetPos : FireAlarmNetwork.findSignalBlocksAround(serverLevel, panelPos)) {
      BlockState targetState = serverLevel.getBlockState(targetPos);
      if (targetState.getBlock() instanceof AbstractFireAlarmSignalBlock target
          && target.isAlarmTarget()
          && !target.isPowered(targetState)) {
        target.setPowered(serverLevel, targetPos, targetState, true);
      }
    }
  }

  private void releaseTargets(ServerLevel serverLevel, BlockPos panelPos) {
    for (BlockPos targetPos : FireAlarmNetwork.findSignalBlocksAround(serverLevel, panelPos)) {
      BlockState targetState = serverLevel.getBlockState(targetPos);
      if (targetState.getBlock() instanceof AbstractFireAlarmSignalBlock target
          && target.isAlarmTarget()
          && target.isPowered(targetState)
          && !target.hasActiveSignal(serverLevel, targetPos, targetState)) {
        target.setPowered(serverLevel, targetPos, targetState, false);
      }
    }
  }

  @Override
  public InteractionResult useWithoutItem(
      BlockState blockState,
      Level level,
      BlockPos blockPos,
      Player player,
      BlockHitResult blockHitResult) {
    if (level instanceof ServerLevel serverLevel) {
      if (blockState.getValue(POWERED)) {
        this.setPowered(serverLevel, blockPos, blockState, false);
        FireAlarmNetwork.releaseSwitchesAround(serverLevel, blockPos);
        serverLevel.scheduleTick(blockPos, this, ACTION_TICK_INTERVAL);
        serverLevel.playSound(
            null, blockPos, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundSource.BLOCKS, 0.5F, 0.8F);
        player.sendOverlayMessage(Component.translatable(Constants.TEXT_PREFIX + ID + "_reset"));
      } else {
        serverLevel.playSound(
            null, blockPos, SoundEvents.STONE_BUTTON_CLICK_ON, SoundSource.BLOCKS, 0.5F, 1.0F);
        player.sendOverlayMessage(Component.translatable(Constants.TEXT_PREFIX + ID + "_idle"));
      }
    }
    return InteractionResult.SUCCESS;
  }

  @Override
  public void affectNeighborsAfterRemoval(
      BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, boolean movedByPiston) {
    if (blockState.getValue(POWERED)) {
      this.onUnpowered(serverLevel, blockPos, blockState);
    }
    super.affectNeighborsAfterRemoval(blockState, serverLevel, blockPos, movedByPiston);
  }
}

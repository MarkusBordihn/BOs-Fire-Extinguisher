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
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FireAlarmSwitchBlock extends FaceAttachedHorizontalDirectionalBlock {

  public static final String NAME = "fire_alarm_switch";
  public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
  protected static final VoxelShape NORTH_AABB = Block.box(4, 3, 13, 12, 13, 16);
  protected static final VoxelShape EAST_AABB = Block.box(0, 3, 4, 3, 13, 12);
  protected static final VoxelShape SOUTH_AABB = Block.box(4, 3, 0, 12, 13, 3);
  protected static final VoxelShape WEST_AABB = Block.box(13, 3, 4, 16, 13, 12);
  protected static final VoxelShape UP_AABB_Z = Block.box(4, 13, 3, 12, 16, 13);
  protected static final VoxelShape UP_AABB_X = Block.box(3, 13, 4, 13, 16, 12);
  protected static final VoxelShape DOWN_AABB_Z = Block.box(4, 0, 3, 12, 3, 13);
  protected static final VoxelShape DOWN_AABB_X = Block.box(3, 0, 4, 13, 3, 12);

  public FireAlarmSwitchBlock(Properties properties) {
    super(properties);
    this.registerDefaultState(
        this.stateDefinition
            .any()
            .setValue(FACING, Direction.NORTH)
            .setValue(POWERED, false)
            .setValue(FACE, AttachFace.WALL));
  }

  private static void makeParticle(
      BlockState blockState, LevelAccessor levelAccessor, BlockPos blockPos, float particleTicks) {
    Direction facingBlockState = blockState.getValue(FACING).getOpposite();
    Direction connectedBlockState = getConnectedDirection(blockState).getOpposite();
    double posX =
        blockPos.getX()
            + 0.5
            + 0.1 * facingBlockState.getStepX()
            + 0.2 * connectedBlockState.getStepX();
    double posY =
        blockPos.getY()
            + 0.5
            + 0.1 * facingBlockState.getStepY()
            + 0.2 * connectedBlockState.getStepY();
    double posZ =
        blockPos.getZ()
            + 0.5
            + 0.1 * facingBlockState.getStepZ()
            + 0.2 * connectedBlockState.getStepZ();
    levelAccessor.addParticle(
        new DustParticleOptions(DustParticleOptions.REDSTONE_PARTICLE_COLOR, particleTicks),
        posX,
        posY,
        posZ,
        0.0,
        0.0,
        0.0);
  }

  @Override
  public VoxelShape getShape(
      BlockState blockState,
      BlockGetter blockGetter,
      BlockPos blockPos,
      CollisionContext collisionContext) {
    return switch (blockState.getValue(FACE)) {
      case FLOOR -> switch (blockState.getValue(FACING).getAxis()) {
        case X -> DOWN_AABB_X;
        default -> DOWN_AABB_Z;
      };
      case WALL -> switch (blockState.getValue(FACING)) {
        case EAST -> EAST_AABB;
        case WEST -> WEST_AABB;
        case SOUTH -> SOUTH_AABB;
        default -> NORTH_AABB;
      };
      default -> switch (blockState.getValue(FACING).getAxis()) {
        case X -> UP_AABB_X;
        default -> UP_AABB_Z;
      };
    };
  }

  @Override
  public InteractionResult use(
      BlockState blockState,
      Level level,
      BlockPos blockPos,
      Player player,
      InteractionHand interactionHand,
      BlockHitResult blockHitResult) {
    if (level.isClientSide) {
      BlockState powerBlockState = blockState.cycle(POWERED);
      if (Boolean.TRUE.equals(powerBlockState.getValue(POWERED))) {
        makeParticle(powerBlockState, level, blockPos, 1.0F);
      }
      return InteractionResult.SUCCESS;
    } else {
      BlockState powerBlockState = this.pull(blockState, level, blockPos);
      float poweredValue = Boolean.TRUE.equals(powerBlockState.getValue(POWERED)) ? 0.6F : 0.5F;
      if (Boolean.TRUE.equals(powerBlockState.getValue(POWERED))) {
        level.playSound(null, blockPos, SoundEvents.GLASS_BREAK, SoundSource.BLOCKS, 0.3F, 0.6F);
      }
      level.playSound(
          null, blockPos, SoundEvents.LEVER_CLICK, SoundSource.BLOCKS, 0.3F, poweredValue);
      level.gameEvent(
          player,
          Boolean.TRUE.equals(powerBlockState.getValue(POWERED))
              ? GameEvent.BLOCK_SWITCH
              : GameEvent.BLOCK_UNSWITCH,
          blockPos);
      return InteractionResult.CONSUME;
    }
  }

  public BlockState pull(BlockState blockState, Level level, BlockPos blockPos) {
    blockState = blockState.cycle(POWERED);
    level.setBlock(blockPos, blockState, 3);
    this.updateNeighbours(blockState, level, blockPos);
    return blockState;
  }

  @Override
  public void animateTick(BlockState blockState, Level level, BlockPos blockPos, Random random) {
    if (Boolean.TRUE.equals(blockState.getValue(POWERED)) && random.nextFloat() < 0.25F) {
      makeParticle(blockState, level, blockPos, 0.5F);
    }
  }

  @Override
  public void onRemove(
      BlockState blockState,
      Level level,
      BlockPos blockPos,
      BlockState formerBlockState,
      boolean removed) {
    if (!removed && !blockState.is(blockState.getBlock())) {
      if (Boolean.TRUE.equals(blockState.getValue(POWERED))) {
        this.updateNeighbours(blockState, level, blockPos);
      }
      super.onRemove(blockState, level, blockPos, formerBlockState, removed);
    }
  }

  @Override
  public int getSignal(
      BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
    return Boolean.TRUE.equals(blockState.getValue(POWERED)) ? 15 : 0;
  }

  @Override
  public int getDirectSignal(
      BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
    return Boolean.TRUE.equals(blockState.getValue(POWERED))
            && getConnectedDirection(blockState) == direction
        ? 15
        : 0;
  }

  @Override
  public boolean isSignalSource(BlockState blockState) {
    return true;
  }

  private void updateNeighbours(BlockState blockState, Level level, BlockPos blockPos) {
    level.updateNeighborsAt(blockPos, this);
    level.updateNeighborsAt(
        blockPos.relative(getConnectedDirection(blockState).getOpposite()), this);
  }

  @Override
  protected void createBlockStateDefinition(
      StateDefinition.Builder<Block, BlockState> stateDefinition) {
    stateDefinition.add(FACE, FACING, POWERED);
  }
}

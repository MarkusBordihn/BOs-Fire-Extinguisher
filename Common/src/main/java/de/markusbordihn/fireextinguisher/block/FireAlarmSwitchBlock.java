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
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
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
  public static final String NAME_EU = "fire_alarm_switch_eu";
  public static final String NAME_JP = "fire_alarm_switch_jp";
  public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
  private final VoxelShape northShape;
  private final VoxelShape eastShape;
  private final VoxelShape southShape;
  private final VoxelShape westShape;
  private final VoxelShape ceilingShapeZ;
  private final VoxelShape ceilingShapeX;
  private final VoxelShape floorShapeZ;
  private final VoxelShape floorShapeX;

  public FireAlarmSwitchBlock(Properties properties) {
    this(properties, 8, 10, 3);
  }

  public FireAlarmSwitchBlock(Properties properties, double width, double height, double depth) {
    super(properties);
    double minX = (16 - width) / 2;
    double maxX = (16 + width) / 2;
    double minY = (16 - height) / 2;
    double maxY = (16 + height) / 2;
    this.northShape = Block.box(minX, minY, 16 - depth, maxX, maxY, 16);
    this.eastShape = Block.box(0, minY, minX, depth, maxY, maxX);
    this.southShape = Block.box(minX, minY, 0, maxX, maxY, depth);
    this.westShape = Block.box(16 - depth, minY, minX, 16, maxY, maxX);
    this.ceilingShapeZ = Block.box(minX, 16 - depth, minY, maxX, 16, maxY);
    this.ceilingShapeX = Block.box(minY, 16 - depth, minX, maxY, 16, maxX);
    this.floorShapeZ = Block.box(minX, 0, minY, maxX, depth, maxY);
    this.floorShapeX = Block.box(minY, 0, minX, maxY, depth, maxX);
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
  protected MapCodec<? extends FaceAttachedHorizontalDirectionalBlock> codec() {
    return null;
  }

  @Override
  public VoxelShape getShape(
      BlockState blockState,
      BlockGetter blockGetter,
      BlockPos blockPos,
      CollisionContext collisionContext) {
    return switch (blockState.getValue(FACE)) {
      case FLOOR ->
          switch (blockState.getValue(FACING).getAxis()) {
            case X -> this.floorShapeX;
            default -> this.floorShapeZ;
          };
      case WALL ->
          switch (blockState.getValue(FACING)) {
            case EAST -> this.eastShape;
            case WEST -> this.westShape;
            case SOUTH -> this.southShape;
            default -> this.northShape;
          };
      default ->
          switch (blockState.getValue(FACING).getAxis()) {
            case X -> this.ceilingShapeX;
            default -> this.ceilingShapeZ;
          };
    };
  }

  @Override
  public InteractionResult useWithoutItem(
      BlockState blockState,
      Level level,
      BlockPos blockPos,
      Player player,
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
              ? GameEvent.BLOCK_ACTIVATE
              : GameEvent.BLOCK_DEACTIVATE,
          blockPos);
      return InteractionResult.CONSUME;
    }
  }

  public BlockState pull(BlockState blockState, Level level, BlockPos blockPos) {
    blockState = blockState.cycle(POWERED);
    level.setBlock(blockPos, blockState, 3);
    this.updateNeighbours(blockState, level, blockPos);
    if (level instanceof ServerLevel serverLevel) {
      if (blockState.getValue(POWERED)) {
        FireAlarmNetwork.activateSource(serverLevel, blockPos);
        serverLevel.scheduleTick(blockPos, this, AbstractFireAlarmSignalBlock.ACTION_TICK_INTERVAL);
      } else {
        FireAlarmNetwork.deactivateSource(serverLevel, blockPos);
      }
    }
    return blockState;
  }

  public void release(ServerLevel serverLevel, BlockPos blockPos, BlockState blockState) {
    if (!blockState.getValue(POWERED)) {
      return;
    }

    this.pull(blockState, serverLevel, blockPos);
    serverLevel.playSound(null, blockPos, SoundEvents.LEVER_CLICK, SoundSource.BLOCKS, 0.3F, 0.5F);
    serverLevel.gameEvent(null, GameEvent.BLOCK_DEACTIVATE, blockPos);
  }

  @Override
  public void tick(
      BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource random) {
    if (blockState.getValue(POWERED)) {
      FireAlarmNetwork.activateSource(serverLevel, blockPos);
      serverLevel.scheduleTick(blockPos, this, AbstractFireAlarmSignalBlock.ACTION_TICK_INTERVAL);
    }
  }

  @Override
  public void onPlace(
      BlockState blockState,
      Level level,
      BlockPos blockPos,
      BlockState oldState,
      boolean isMoving) {
    if (!level.isClientSide
        && !blockState.is(oldState.getBlock())
        && blockState.getValue(POWERED)) {
      level.scheduleTick(blockPos, this, AbstractFireAlarmSignalBlock.SIGNAL_TICK_DELAY);
    }
    super.onPlace(blockState, level, blockPos, oldState, isMoving);
  }

  @Override
  public void animateTick(
      BlockState blockState, Level level, BlockPos blockPos, RandomSource random) {
    if (blockState.getValue(POWERED) && random.nextFloat() < 0.25F) {
      makeParticle(blockState, level, blockPos, 0.5F);
    }
  }

  @Override
  public void onRemove(
      BlockState blockState,
      Level level,
      BlockPos blockPos,
      BlockState newBlockState,
      boolean isMoving) {
    if (!blockState.is(newBlockState.getBlock())) {
      if (blockState.getValue(POWERED)) {
        this.updateNeighbours(blockState, level, blockPos);
        if (level instanceof ServerLevel serverLevel) {
          FireAlarmNetwork.deactivateSource(serverLevel, blockPos);
        }
      }
      super.onRemove(blockState, level, blockPos, newBlockState, isMoving);
    }
  }

  @Override
  public int getSignal(
      BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
    return blockState.getValue(POWERED) ? 15 : 0;
  }

  @Override
  public int getDirectSignal(
      BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
    return blockState.getValue(POWERED) && getConnectedDirection(blockState) == direction ? 15 : 0;
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

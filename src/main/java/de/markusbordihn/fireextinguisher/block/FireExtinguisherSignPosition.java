/**
 * Copyright 2022 Markus Bordihn
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

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FireExtinguisherSignPosition extends Block {

  public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

  protected static final VoxelShape SHAPE_NORTH_AABB = Block.box(0D, 4D, 14.75D, 16D, 12D, 16D);
  protected static final VoxelShape SHAPE_EAST_AABB = Block.box(0D, 4D, 0D, 1.25D, 12D, 16D);
  protected static final VoxelShape SHAPE_SOUTH_AABB = Block.box(0D, 4D, 0D, 16D, 12D, 1.25D);
  protected static final VoxelShape SHAPE_WEST_AABB = Block.box(14.75D, 4D, 0D, 16D, 12D, 16D);

  public FireExtinguisherSignPosition(Properties properties) {
    super(properties.lightLevel(blockState -> 10));
    this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
  }

  @Override
  public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos,
      CollisionContext collisionContext) {
    Direction facing = blockState.getValue(FireExtinguisherSignPosition.FACING);
    switch (facing) {
      case NORTH:
        return SHAPE_NORTH_AABB;
      case EAST:
        return SHAPE_EAST_AABB;
      case SOUTH:
        return SHAPE_SOUTH_AABB;
      case WEST:
        return SHAPE_WEST_AABB;
      default:
        return SHAPE_NORTH_AABB;
    }
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockState) {
    blockState.add(FACING);
  }

  @Override
  @Nullable
  public BlockState getStateForPlacement(BlockPlaceContext context) {
    return this.defaultBlockState().setValue(FACING,
        context.getHorizontalDirection().getOpposite());
  }

}

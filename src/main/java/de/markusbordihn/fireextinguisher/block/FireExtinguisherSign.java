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

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class FireExtinguisherSign extends Block {

  public static final String NAME = "fire_extinguisher_sign";

  public static final DirectionProperty FACING = HorizontalBlock.FACING;

  protected static final VoxelShape SHAPE_NORTH_AABB = Block.box(0D, 0D, 14.75D, 16D, 16D, 16D);
  protected static final VoxelShape SHAPE_EAST_AABB = Block.box(0D, 0D, 0D, 1.25D, 16D, 16D);
  protected static final VoxelShape SHAPE_SOUTH_AABB = Block.box(0D, 0D, 0D, 16D, 16D, 1.25D);
  protected static final VoxelShape SHAPE_WEST_AABB = Block.box(14.75D, 0D, 0D, 16D, 16D, 16D);

  public FireExtinguisherSign(Properties properties) {
    super(properties.lightLevel(blockState -> 10));
    this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
  }

  @Override
  public VoxelShape getShape(BlockState blockState, IBlockReader worldIn, BlockPos blockPos,
      ISelectionContext context) {
    Direction facing = blockState.getValue(FireExtinguisherSign.FACING);
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
  protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> blockState) {
    blockState.add(FACING);
  }

  @Override
  @Nullable
  public BlockState getStateForPlacement(BlockItemUseContext context) {
    return this.defaultBlockState().setValue(FACING,
        context.getHorizontalDirection().getOpposite());
  }

}

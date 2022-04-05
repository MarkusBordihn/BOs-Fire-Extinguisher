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
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class ExitSign extends Block {

  public static final String NAME = "exit_sign";

  // Defines if we need to rotate the Object based on the click position and player pov
  public static final DirectionProperty FACING = HorizontalBlock.FACING;
  public static final EnumProperty<AttachFace> ATTACH_FACE = BlockStateProperties.ATTACH_FACE;

  // We need a VoxelShape for each side to cover all faces and possibilities
  protected static final VoxelShape CEILING_NORTH_SOUTH_AABB = Block.box(0D, 4D, 7D, 16D, 12D, 9D);
  protected static final VoxelShape CEILING_EAST_WEST_AABB = Block.box(7D, 4, 0D, 9D, 12D, 16D);

  protected static final VoxelShape FLOOR_NORTH_SOUTH_AABB = Block.box(0D, 0D, 7D, 16D, 8D, 9D);
  protected static final VoxelShape FLOOR_EAST_WEST_AABB = Block.box(7D, 0D, 0D, 9D, 8D, 16D);

  protected static final VoxelShape SHAPE_NORTH_AABB = Block.box(0D, 4D, 13.75D, 16D, 12D, 16D);
  protected static final VoxelShape SHAPE_EAST_AABB = Block.box(0D, 4D, 0D, 2.25D, 12D, 16D);
  protected static final VoxelShape SHAPE_SOUTH_AABB = Block.box(0D, 4D, 0D, 16D, 12D, 2.25D);
  protected static final VoxelShape SHAPE_WEST_AABB = Block.box(13.75D, 4D, 0D, 16D, 12D, 16D);

  public ExitSign(Properties properties) {
    super(properties.lightLevel(blockState -> 15));
    this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH)
        .setValue(ATTACH_FACE, AttachFace.FLOOR));
  }

  @Override
  public VoxelShape getShape(BlockState blockState, IBlockReader worldIn, BlockPos blockPos,
      ISelectionContext context) {
    AttachFace attachFace = blockState.getValue(ATTACH_FACE);
    Direction facing = blockState.getValue(FireExtinguisherSignPosition.FACING);

    // Handle ceiling positions
    if (attachFace == AttachFace.CEILING) {
      if (facing == Direction.NORTH || facing == Direction.SOUTH) {
        return CEILING_NORTH_SOUTH_AABB;
      } else if (facing == Direction.EAST || facing == Direction.WEST) {
        return CEILING_EAST_WEST_AABB;
      }
    }

    // Handle floor positions
    if (attachFace == AttachFace.FLOOR) {
      if (facing == Direction.NORTH || facing == Direction.SOUTH) {
        return FLOOR_NORTH_SOUTH_AABB;
      } else if (facing == Direction.EAST || facing == Direction.WEST) {
        return FLOOR_EAST_WEST_AABB;
      }
    }

    // Handle wall positions
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
    blockState.add(ATTACH_FACE, FACING);
  }

  @Override
  @Nullable
  public BlockState getStateForPlacement(BlockItemUseContext context) {
    Direction direction = context.getClickedFace();
    BlockState blockState =
        context.getLevel().getBlockState(context.getClickedPos().relative(direction.getOpposite()));
    Direction faceDirection =
        blockState.is(this) && blockState.getValue(FACING) == direction ? direction.getOpposite()
            : direction;

    // Calculate attach face based on clicked face and relative click location.
    AttachFace attachFace = AttachFace.FLOOR;
    if (faceDirection == Direction.DOWN) {
      attachFace = AttachFace.CEILING;
    } else if (faceDirection != Direction.UP) {
      Double relativeClickLocation = context.getClickLocation().y % 1;
      if (relativeClickLocation < 0.2) {
        attachFace = AttachFace.FLOOR;
      } else if (relativeClickLocation > 0.8) {
        attachFace = AttachFace.CEILING;
      } else {
        attachFace = AttachFace.WALL;
      }
    }

    return this.defaultBlockState().setValue(ATTACH_FACE, attachFace).setValue(FACING,
        context.getHorizontalDirection().getOpposite());
  }

}

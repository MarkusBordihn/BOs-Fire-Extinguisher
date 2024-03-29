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

import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class ExitSign extends Block {

  public static final String NAME = "exit_sign";

  // Defines if we need to rotate the Object based on the click position and player pov
  public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
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
    this.registerDefaultState(
        this.stateDefinition
            .any()
            .setValue(FACING, Direction.NORTH)
            .setValue(ATTACH_FACE, AttachFace.FLOOR));
  }

  @NotNull
  private static AttachFace getAttachFace(BlockPlaceContext context, Direction faceDirection) {
    AttachFace attachFace = AttachFace.FLOOR;
    if (faceDirection == Direction.DOWN) {
      attachFace = AttachFace.CEILING;
    } else if (faceDirection != Direction.UP) {
      double relativeClickLocation = context.getClickLocation().y % 1;
      if (relativeClickLocation < 0.2) {
        attachFace = AttachFace.FLOOR;
      } else if (relativeClickLocation > 0.8) {
        attachFace = AttachFace.CEILING;
      } else {
        attachFace = AttachFace.WALL;
      }
    }
    return attachFace;
  }

  @Override
  public VoxelShape getShape(
      BlockState blockState,
      BlockGetter blockGetter,
      BlockPos blockPos,
      CollisionContext collisionContext) {
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
    return switch (facing) {
      case EAST -> SHAPE_EAST_AABB;
      case SOUTH -> SHAPE_SOUTH_AABB;
      case WEST -> SHAPE_WEST_AABB;
      default -> SHAPE_NORTH_AABB;
    };
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockState) {
    blockState.add(ATTACH_FACE, FACING);
  }

  @Override
  @Nullable
  public BlockState getStateForPlacement(BlockPlaceContext context) {
    Direction direction = context.getClickedFace();
    BlockState blockState =
        context.getLevel().getBlockState(context.getClickedPos().relative(direction.getOpposite()));
    Direction faceDirection =
        blockState.is(this) && blockState.getValue(FACING) == direction
            ? direction.getOpposite()
            : direction;

    // Calculate attach face based on clicked face and relative click location.
    AttachFace attachFace = getAttachFace(context, faceDirection);
    return this.defaultBlockState()
        .setValue(ATTACH_FACE, attachFace)
        .setValue(FACING, context.getHorizontalDirection().getOpposite());
  }
}

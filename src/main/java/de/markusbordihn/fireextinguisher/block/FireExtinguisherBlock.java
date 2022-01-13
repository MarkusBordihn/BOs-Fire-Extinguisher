
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

public class FireExtinguisherBlock extends Block {

  public static final String NAME = "fire_extinguisher";

  public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

  protected static final VoxelShape SHAPE = Block.box(5.5D, 0.0D, 5.5D, 10.5D, 9.0D, 10.5D);

  public FireExtinguisherBlock(Properties properties) {
    super(properties);
    this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
  }

  @Override
  public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos,
      CollisionContext collisionContext) {
    return SHAPE;
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

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

import com.mojang.serialization.MapCodec;
import de.markusbordihn.fireextinguisher.Constants;
import de.markusbordihn.fireextinguisher.blockitem.FireExtinguisherBlockItem;
import de.markusbordihn.fireextinguisher.config.FireExtinguisherConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FireHydrantBlock extends HorizontalDirectionalBlock {

  public static final MapCodec<FireHydrantBlock> CODEC = simpleCodec(FireHydrantBlock::new);
  public static final String ID = "fire_hydrant";
  public static final int REFILL_COOLDOWN_TICKS = 20;
  protected static final VoxelShape SHAPE = Block.box(4, 0, 4, 12, 14, 12);

  public FireHydrantBlock(BlockBehaviour.Properties properties) {
    super(properties);
    this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }

  @Override
  public BlockState getStateForPlacement(BlockPlaceContext context) {
    return this.defaultBlockState()
        .setValue(FACING, context.getHorizontalDirection().getOpposite());
  }

  @Override
  protected void createBlockStateDefinition(
      StateDefinition.Builder<Block, BlockState> stateDefinition) {
    stateDefinition.add(FACING);
  }

  @Override
  public VoxelShape getShape(
      BlockState blockState,
      BlockGetter blockGetter,
      BlockPos blockPos,
      CollisionContext collisionContext) {
    return SHAPE;
  }

  @Override
  protected boolean isPathfindable(BlockState blockState, PathComputationType pathComputationType) {
    return false;
  }

  @Override
  protected InteractionResult useItemOn(
      ItemStack itemStack,
      BlockState blockState,
      Level level,
      BlockPos blockPos,
      Player player,
      InteractionHand interactionHand,
      BlockHitResult blockHitResult) {
    if (!FireExtinguisherConfig.fireHydrantRefillEnabled
        || !(itemStack.getItem() instanceof FireExtinguisherBlockItem)) {
      return InteractionResult.PASS;
    }

    if (level instanceof ServerLevel serverLevel) {
      if (itemStack.isDamaged()) {
        this.refill(serverLevel, blockPos, player, itemStack);
      } else {
        player.displayClientMessage(
            Component.translatable(Constants.TEXT_PREFIX + ID + "_full"), true);
      }
    }
    return InteractionResult.SUCCESS;
  }

  private void refill(
      ServerLevel serverLevel, BlockPos blockPos, Player player, ItemStack itemStack) {
    itemStack.setDamageValue(0);
    serverLevel.playSound(null, blockPos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
    serverLevel.sendParticles(
        ParticleTypes.SPLASH,
        blockPos.getX() + 0.5,
        blockPos.getY() + 1.0,
        blockPos.getZ() + 0.5,
        12,
        0.3,
        0.2,
        0.3,
        0.1);
    player.getCooldowns().addCooldown(itemStack, REFILL_COOLDOWN_TICKS);
    player.displayClientMessage(
        Component.translatable(Constants.TEXT_PREFIX + ID + "_refilled"), true);
  }
}

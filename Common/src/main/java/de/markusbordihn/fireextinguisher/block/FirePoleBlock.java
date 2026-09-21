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

import de.markusbordihn.fireextinguisher.config.FireExtinguisherConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FirePoleBlock extends Block {

  public static final String NAME = "fire_pole";
  protected static final VoxelShape SHAPE = Block.box(6, 0, 6, 10, 16, 10);
  private static final double CENTERING_FACTOR = 0.2;

  public FirePoleBlock(BlockBehaviour.Properties properties) {
    super(properties);
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
  public VoxelShape getCollisionShape(
      BlockState blockState,
      BlockGetter blockGetter,
      BlockPos blockPos,
      CollisionContext collisionContext) {
    return Shapes.empty();
  }

  @Override
  protected boolean isPathfindable(BlockState blockState, PathComputationType pathComputationType) {
    return false;
  }

  @Override
  public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity) {
    if (!(entity instanceof LivingEntity livingEntity) || entity.onGround()) {
      return;
    }

    Vec3 deltaMovement = entity.getDeltaMovement();
    double pullX = (blockPos.getX() + 0.5 - entity.getX()) * CENTERING_FACTOR;
    double pullZ = (blockPos.getZ() + 0.5 - entity.getZ()) * CENTERING_FACTOR;
    double maxSlidePerTick = FireExtinguisherConfig.firePoleSlideSpeed / 20.0;
    double slideY =
        livingEntity.isShiftKeyDown()
            ? 0.0
            : Math.min(0.0, Math.max(deltaMovement.y, -maxSlidePerTick));
    entity.setDeltaMovement(pullX, slideY, pullZ);
    entity.resetFallDistance();
  }
}

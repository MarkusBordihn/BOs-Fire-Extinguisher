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

package de.markusbordihn.fireextinguisher.utils;

import de.markusbordihn.fireextinguisher.Constants;
import de.markusbordihn.fireextinguisher.config.FireExtinguisherConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;

public class FireDetection {

  public static final TagKey<Block> EXTINGUISHABLE =
      TagKey.create(Registries.BLOCK, new ResourceLocation(Constants.MOD_ID, "extinguishable"));

  private FireDetection() {}

  public static boolean isFireBlock(BlockState blockState) {
    if (blockState.isAir()) {
      return false;
    }

    if (blockState.is(BlockTags.FIRE)
        || blockState.getBlock() instanceof BaseFireBlock
        || blockState.is(EXTINGUISHABLE)) {
      return true;
    }
    if (FireExtinguisherConfig.extinguishableBlocks.isEmpty()) {
      return false;
    }
    ResourceLocation blockId = BuiltInRegistries.BLOCK.getKey(blockState.getBlock());
    return FireExtinguisherConfig.extinguishableBlocks.contains(blockId.toString());
  }

  public static boolean isLitCampfire(BlockState blockState) {
    return CampfireBlock.isLitCampfire(blockState);
  }

  public static boolean extinguish(Level level, BlockPos blockPos, BlockState blockState) {
    if (isLitCampfire(blockState)) {
      return level.setBlock(blockPos, blockState.setValue(CampfireBlock.LIT, false), 3);
    }
    if (isFireBlock(blockState)) {
      return level.removeBlock(blockPos, false);
    }
    return false;
  }

  public static int extinguishWithin(
      Level level, BlockPos center, int radiusX, int radiusY, int radiusZ) {
    int extinguished = 0;
    for (BlockPos blockPos : BlockPos.withinManhattan(center, radiusX, radiusY, radiusZ)) {
      if (extinguish(level, blockPos, level.getBlockState(blockPos))) {
        extinguished++;
      }
    }
    return extinguished;
  }

  public static boolean hasFireWithin(
      Level level, BlockPos center, int radiusX, int radiusY, int radiusZ) {
    for (BlockPos blockPos : BlockPos.withinManhattan(center, radiusX, radiusY, radiusZ)) {
      if (isFireBlock(level.getBlockState(blockPos))) {
        return true;
      }
    }
    return false;
  }
}

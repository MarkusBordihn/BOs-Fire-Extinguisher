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

package de.markusbordihn.fireextinguisher.gametest;

import de.markusbordihn.fireextinguisher.block.AbstractFireAlarmSignalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.properties.AttachFace;

public class FireSprinklerTestHelper {

  private static final BlockPos CEILING_POS = new BlockPos(1, 3, 1);
  private static final BlockPos SPRINKLER_POS = new BlockPos(1, 2, 1);
  private static final BlockPos REDSTONE_POS = new BlockPos(2, 2, 1);
  private static final BlockPos FIRE_POS = new BlockPos(1, 1, 1);
  private static final BlockPos OFFSET_FIRE_POS = new BlockPos(0, 1, 0);

  private FireSprinklerTestHelper() {}

  public static void testExtinguishesFireAtHorizontalOffset(
      GameTestHelper helper, Block fireSprinkler) {
    helper.setBlock(CEILING_POS, Blocks.STONE);
    helper.setBlock(OFFSET_FIRE_POS.below(), Blocks.NETHERRACK);
    helper.setBlock(
        SPRINKLER_POS,
        fireSprinkler
            .defaultBlockState()
            .setValue(FaceAttachedHorizontalDirectionalBlock.FACE, AttachFace.CEILING));
    helper.setBlock(OFFSET_FIRE_POS, Blocks.FIRE);
    helper.assertBlockPresent(Blocks.FIRE, OFFSET_FIRE_POS);
    helper.setBlock(REDSTONE_POS, Blocks.REDSTONE_BLOCK);
    helper.succeedWhen(
        () -> {
          helper.assertBlockProperty(SPRINKLER_POS, AbstractFireAlarmSignalBlock.POWERED, true);
          helper.assertBlockNotPresent(Blocks.FIRE, OFFSET_FIRE_POS);
        });
  }

  public static void testExtinguishesFireBelow(GameTestHelper helper, Block fireSprinkler) {
    helper.setBlock(CEILING_POS, Blocks.STONE);
    helper.setBlock(FIRE_POS.below(), Blocks.NETHERRACK);
    helper.setBlock(
        SPRINKLER_POS,
        fireSprinkler
            .defaultBlockState()
            .setValue(FaceAttachedHorizontalDirectionalBlock.FACE, AttachFace.CEILING));
    helper.setBlock(FIRE_POS, Blocks.FIRE);
    helper.assertBlockPresent(Blocks.FIRE, FIRE_POS);
    helper.setBlock(REDSTONE_POS, Blocks.REDSTONE_BLOCK);
    helper.succeedWhen(
        () -> {
          helper.assertBlockProperty(SPRINKLER_POS, AbstractFireAlarmSignalBlock.POWERED, true);
          helper.assertBlockNotPresent(Blocks.FIRE, FIRE_POS);
        });
  }
}

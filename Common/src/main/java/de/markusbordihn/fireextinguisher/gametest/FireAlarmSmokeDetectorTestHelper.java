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

public class FireAlarmSmokeDetectorTestHelper {

  public static final int TIMEOUT_TICKS = 200;
  private static final BlockPos DETECTOR_POS = new BlockPos(1, 1, 1);
  private static final BlockPos FIRE_POS = new BlockPos(1, 1, 0);

  private FireAlarmSmokeDetectorTestHelper() {}

  public static void testDetectsFire(GameTestHelper helper, Block smokeDetector) {
    placeDetectorAndFire(helper, smokeDetector);
    helper
        .startSequence()
        .thenExecuteAfter(20, () -> assertPowered(helper, true))
        .thenExecuteAfter(160, () -> assertPowered(helper, true))
        .thenSucceed();
  }

  public static void testReleasesAfterFireIsGone(GameTestHelper helper, Block smokeDetector) {
    placeDetectorAndFire(helper, smokeDetector);
    helper
        .startSequence()
        .thenExecuteAfter(20, () -> assertPowered(helper, true))
        .thenExecuteAfter(10, () -> helper.setBlock(FIRE_POS, Blocks.AIR))
        .thenExecuteAfter(160, () -> assertPowered(helper, false))
        .thenSucceed();
  }

  public static void testDetectsFirePlacedBeforeDetector(
      GameTestHelper helper, Block smokeDetector) {
    helper.setBlock(DETECTOR_POS.below(), Blocks.STONE);
    helper.setBlock(FIRE_POS.below(), Blocks.NETHERRACK);
    helper.setBlock(FIRE_POS, Blocks.FIRE);
    helper.assertBlockPresent(Blocks.FIRE, FIRE_POS);
    helper.setBlock(
        DETECTOR_POS,
        smokeDetector
            .defaultBlockState()
            .setValue(FaceAttachedHorizontalDirectionalBlock.FACE, AttachFace.FLOOR));
    helper
        .startSequence()
        .thenExecuteAfter(20, () -> assertPowered(helper, true))
        .thenExecuteAfter(160, () -> assertPowered(helper, true))
        .thenSucceed();
  }

  private static void placeDetectorAndFire(GameTestHelper helper, Block smokeDetector) {
    helper.setBlock(DETECTOR_POS.below(), Blocks.STONE);
    helper.setBlock(FIRE_POS.below(), Blocks.NETHERRACK);
    helper.setBlock(
        DETECTOR_POS,
        smokeDetector
            .defaultBlockState()
            .setValue(FaceAttachedHorizontalDirectionalBlock.FACE, AttachFace.FLOOR));
    helper.setBlock(FIRE_POS, Blocks.FIRE);
    helper.assertBlockPresent(Blocks.FIRE, FIRE_POS);
  }

  private static void assertPowered(GameTestHelper helper, boolean powered) {
    helper.assertBlockProperty(DETECTOR_POS, AbstractFireAlarmSignalBlock.POWERED, powered);
  }
}

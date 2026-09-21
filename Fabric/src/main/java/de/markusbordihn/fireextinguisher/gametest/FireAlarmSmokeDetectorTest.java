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

import de.markusbordihn.fireextinguisher.block.FireAlarmBlocks;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;

@SuppressWarnings("unused")
public class FireAlarmSmokeDetectorTest {

  @GameTest(
      template = "fire_extinguisher:gametest.3x3x3",
      timeoutTicks = FireAlarmSmokeDetectorTestHelper.TIMEOUT_TICKS)
  public void testDetectsFire(GameTestHelper helper) {
    FireAlarmSmokeDetectorTestHelper.testDetectsFire(
        helper, FireAlarmBlocks.FIRE_ALARM_SMOKE_DETECTOR);
  }

  @GameTest(
      template = "fire_extinguisher:gametest.3x3x3",
      timeoutTicks = FireAlarmSmokeDetectorTestHelper.TIMEOUT_TICKS)
  public void testSilentDetectsFire(GameTestHelper helper) {
    FireAlarmSmokeDetectorTestHelper.testDetectsFire(
        helper, FireAlarmBlocks.FIRE_ALARM_SMOKE_DETECTOR_SILENT);
  }

  @GameTest(
      template = "fire_extinguisher:gametest.3x3x3",
      timeoutTicks = FireAlarmSmokeDetectorTestHelper.TIMEOUT_TICKS)
  public void testReleasesAfterFireIsGone(GameTestHelper helper) {
    FireAlarmSmokeDetectorTestHelper.testReleasesAfterFireIsGone(
        helper, FireAlarmBlocks.FIRE_ALARM_SMOKE_DETECTOR);
  }

  @GameTest(
      template = "fire_extinguisher:gametest.3x3x3",
      timeoutTicks = FireAlarmSmokeDetectorTestHelper.TIMEOUT_TICKS)
  public void testDetectsFirePlacedBeforeDetector(GameTestHelper helper) {
    FireAlarmSmokeDetectorTestHelper.testDetectsFirePlacedBeforeDetector(
        helper, FireAlarmBlocks.FIRE_ALARM_SMOKE_DETECTOR);
  }
}

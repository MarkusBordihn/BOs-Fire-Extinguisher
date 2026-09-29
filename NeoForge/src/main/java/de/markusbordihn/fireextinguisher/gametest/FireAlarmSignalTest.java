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

package de.markusbordihn.fireextinguisher.gametest;

import de.markusbordihn.fireextinguisher.Constants;
import de.markusbordihn.fireextinguisher.block.ModBlocks;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.neoforged.neoforge.gametest.GameTestHolder;
import net.neoforged.neoforge.gametest.PrefixGameTestTemplate;

@SuppressWarnings("unused")
@PrefixGameTestTemplate(value = false)
@GameTestHolder(Constants.MOD_ID)
public class FireAlarmSignalTest {

  @GameTest(template = "gametest.3x3x3", timeoutTicks = FireAlarmSignalTestHelper.TIMEOUT_TICKS)
  public void testBellFollowsRedstoneSignal(GameTestHelper helper) {
    FireAlarmSignalTestHelper.testFollowsRedstoneSignal(helper, ModBlocks.FIRE_ALARM_BELL.get());
  }

  @GameTest(template = "gametest.3x3x3", timeoutTicks = FireAlarmSignalTestHelper.TIMEOUT_TICKS)
  public void testSirenFollowsRedstoneSignal(GameTestHelper helper) {
    FireAlarmSignalTestHelper.testFollowsRedstoneSignal(helper, ModBlocks.FIRE_ALARM_SIREN.get());
  }

  @GameTest(template = "gametest.3x3x3", timeoutTicks = FireAlarmSignalTestHelper.TIMEOUT_TICKS)
  public void testLightFollowsRedstoneSignal(GameTestHelper helper) {
    FireAlarmSignalTestHelper.testFollowsRedstoneSignal(helper, ModBlocks.FIRE_ALARM_LIGHT.get());
  }

  @GameTest(template = "gametest.3x3x3", timeoutTicks = FireAlarmSignalTestHelper.TIMEOUT_TICKS)
  public void testLightForwardsRedstoneSignal(GameTestHelper helper) {
    FireAlarmSignalTestHelper.testForwardsRedstoneSignal(helper, ModBlocks.FIRE_ALARM_LIGHT.get());
  }

  @GameTest(template = "gametest.3x3x3", timeoutTicks = FireAlarmSignalTestHelper.TIMEOUT_TICKS)
  public void testSwitchTogglesRedstoneSignal(GameTestHelper helper) {
    FireAlarmSignalTestHelper.testSwitchTogglesRedstoneSignal(
        helper, ModBlocks.FIRE_ALARM_SWITCH.get());
  }

  @GameTest(template = "gametest.3x3x3", timeoutTicks = FireAlarmSignalTestHelper.TIMEOUT_TICKS)
  public void testSwitchEuTogglesRedstoneSignal(GameTestHelper helper) {
    FireAlarmSignalTestHelper.testSwitchTogglesRedstoneSignal(
        helper, ModBlocks.FIRE_ALARM_SWITCH_EU.get());
  }

  @GameTest(template = "gametest.3x3x3", timeoutTicks = FireAlarmSignalTestHelper.TIMEOUT_TICKS)
  public void testSwitchJpTogglesRedstoneSignal(GameTestHelper helper) {
    FireAlarmSignalTestHelper.testSwitchTogglesRedstoneSignal(
        helper, ModBlocks.FIRE_ALARM_SWITCH_JP.get());
  }
}

/*
 * Copyright 2024 Markus Bordihn
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

import de.markusbordihn.fireextinguisher.block.ExitSignBlocks;
import de.markusbordihn.fireextinguisher.block.FireAlarmBlocks;
import de.markusbordihn.fireextinguisher.block.FireExtinguisherBlocks;
import de.markusbordihn.fireextinguisher.block.FireExtinguisherSignBlocks;
import net.fabricmc.fabric.api.gametest.v1.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;

@SuppressWarnings("unused")
public class ModBlocksTest {

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testFireExtinguisherBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, FireExtinguisherBlocks.FIRE_EXTINGUISHER);
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testFireExtinguisherCopperBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, FireExtinguisherBlocks.FIRE_EXTINGUISHER_COPPER);
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testFireSprinklerBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, FireAlarmBlocks.FIRE_SPRINKLER);
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testFireAlarmSwitchBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, FireAlarmBlocks.FIRE_ALARM_SWITCH);
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testFireAlarmBellBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, FireAlarmBlocks.FIRE_ALARM_BELL);
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testFireAlarmSirenBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, FireAlarmBlocks.FIRE_ALARM_SIREN);
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testFireAlarmSmokeDetectorBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, FireAlarmBlocks.FIRE_ALARM_SMOKE_DETECTOR);
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testFireAlarmSmokeDetectorSilentBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, FireAlarmBlocks.FIRE_ALARM_SMOKE_DETECTOR_SILENT);
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testFireExtinguisherSignBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, FireExtinguisherSignBlocks.FIRE_EXTINGUISHER_SIGN);
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testFireExtinguisherSignLeftBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(
        helper, FireExtinguisherSignBlocks.FIRE_EXTINGUISHER_SIGN_LEFT);
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testFireExtinguisherSignRightBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(
        helper, FireExtinguisherSignBlocks.FIRE_EXTINGUISHER_SIGN_RIGHT);
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testExitSignBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, ExitSignBlocks.EXIT_SIGN);
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testExitSignLeftBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, ExitSignBlocks.EXIT_SIGN_LEFT);
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testExitSignLeftDownBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, ExitSignBlocks.EXIT_SIGN_LEFT_DOWN);
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testExitSignLeftUpBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, ExitSignBlocks.EXIT_SIGN_LEFT_UP);
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testExitSignRightBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, ExitSignBlocks.EXIT_SIGN_RIGHT);
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testExitSignRightDownBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, ExitSignBlocks.EXIT_SIGN_RIGHT_DOWN);
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testExitSignRightUpBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, ExitSignBlocks.EXIT_SIGN_RIGHT_UP);
    helper.succeed();
  }
}

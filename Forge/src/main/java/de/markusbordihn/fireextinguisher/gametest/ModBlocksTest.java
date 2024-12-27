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

import de.markusbordihn.fireextinguisher.Constants;
import de.markusbordihn.fireextinguisher.block.ModBlocks;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraftforge.gametest.GameTestHolder;
import net.minecraftforge.gametest.PrefixGameTestTemplate;

@SuppressWarnings("unused")
@PrefixGameTestTemplate(value = false)
@GameTestHolder(Constants.MOD_ID)
public class ModBlocksTest {

  @GameTest(template = "gametest.1x1x1")
  public void testFireExtinguisherBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, ModBlocks.FIRE_EXTINGUISHER.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testFireExtinguisherCopperBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, ModBlocks.FIRE_EXTINGUISHER_COPPER.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testFireSprinklerBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, ModBlocks.FIRE_SPRINKLER.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testFireAlarmSwitchBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, ModBlocks.FIRE_ALARM_SWITCH.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testFireAlarmBellBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, ModBlocks.FIRE_ALARM_BELL.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testFireAlarmSirenBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, ModBlocks.FIRE_ALARM_SIREN.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testFireAlarmSmokeDetectorBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, ModBlocks.FIRE_ALARM_SMOKE_DETECTOR.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testFireAlarmSmokeDetectorSilentBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, ModBlocks.FIRE_ALARM_SMOKE_DETECTOR_SILENT.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testFireExtinguisherSignBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, ModBlocks.FIRE_EXTINGUISHER_SIGN.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testFireExtinguisherSignLeftBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, ModBlocks.FIRE_EXTINGUISHER_SIGN_LEFT.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testFireExtinguisherSignRightBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, ModBlocks.FIRE_EXTINGUISHER_SIGN_RIGHT.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testExitSignBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, ModBlocks.EXIT_SIGN.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testExitSignLeftBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, ModBlocks.EXIT_SIGN_LEFT.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testExitSignLeftDownBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, ModBlocks.EXIT_SIGN_LEFT_DOWN.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testExitSignLeftUpBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, ModBlocks.EXIT_SIGN_LEFT_UP.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testExitSignRightBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, ModBlocks.EXIT_SIGN_RIGHT.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testExitSignRightDownBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, ModBlocks.EXIT_SIGN_RIGHT_DOWN.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testExitSignRightUpBlock(GameTestHelper helper) {
    ModBlocksTestHelper.testModBlock(helper, ModBlocks.EXIT_SIGN_RIGHT_UP.get());
    helper.succeed();
  }
}

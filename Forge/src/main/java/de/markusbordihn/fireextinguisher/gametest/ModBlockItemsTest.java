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

import de.markusbordihn.fireextinguisher.item.ModBlockItems;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraftforge.gametest.GameTest;

@SuppressWarnings("unused")
public class ModBlockItemsTest {

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testFireExtinguisherItem(GameTestHelper helper) {
    ModItemsTestHelper.testModItem(helper, ModBlockItems.FIRE_EXTINGUISHER.get());
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testFireExtinguisherCopperItem(GameTestHelper helper) {
    ModItemsTestHelper.testModItem(helper, ModBlockItems.FIRE_EXTINGUISHER_COPPER.get());
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testFireSprinklerItem(GameTestHelper helper) {
    ModItemsTestHelper.testModItem(helper, ModBlockItems.FIRE_SPRINKLER.get());
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testFireAlarmSwitchItem(GameTestHelper helper) {
    ModItemsTestHelper.testModItem(helper, ModBlockItems.FIRE_ALARM_SWITCH.get());
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testFireAlarmBellItem(GameTestHelper helper) {
    ModItemsTestHelper.testModItem(helper, ModBlockItems.FIRE_ALARM_BELL.get());
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testFireAlarmSirenItem(GameTestHelper helper) {
    ModItemsTestHelper.testModItem(helper, ModBlockItems.FIRE_ALARM_SIREN.get());
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testFireAlarmSmokeDetectorItem(GameTestHelper helper) {
    ModItemsTestHelper.testModItem(helper, ModBlockItems.FIRE_ALARM_SMOKE_DETECTOR.get());
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testFireAlarmSmokeDetectorSilentItem(GameTestHelper helper) {
    ModItemsTestHelper.testModItem(helper, ModBlockItems.FIRE_ALARM_SMOKE_DETECTOR_SILENT.get());
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testFireExtinguisherSignItem(GameTestHelper helper) {
    ModItemsTestHelper.testModItem(helper, ModBlockItems.FIRE_EXTINGUISHER_SIGN.get());
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testFireExtinguisherSignLeftItem(GameTestHelper helper) {
    ModItemsTestHelper.testModItem(helper, ModBlockItems.FIRE_EXTINGUISHER_SIGN_LEFT.get());
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testFireExtinguisherSignRightItem(GameTestHelper helper) {
    ModItemsTestHelper.testModItem(helper, ModBlockItems.FIRE_EXTINGUISHER_SIGN_RIGHT.get());
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testExitSignItem(GameTestHelper helper) {
    ModItemsTestHelper.testModItem(helper, ModBlockItems.EXIT_SIGN.get());
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testExitSignLeftItem(GameTestHelper helper) {
    ModItemsTestHelper.testModItem(helper, ModBlockItems.EXIT_SIGN_LEFT.get());
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testExitSignLeftDownItem(GameTestHelper helper) {
    ModItemsTestHelper.testModItem(helper, ModBlockItems.EXIT_SIGN_LEFT_DOWN.get());
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testExitSignLeftUpItem(GameTestHelper helper) {
    ModItemsTestHelper.testModItem(helper, ModBlockItems.EXIT_SIGN_LEFT_UP.get());
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testExitSignRightItem(GameTestHelper helper) {
    ModItemsTestHelper.testModItem(helper, ModBlockItems.EXIT_SIGN_RIGHT.get());
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testExitSignRightDownItem(GameTestHelper helper) {
    ModItemsTestHelper.testModItem(helper, ModBlockItems.EXIT_SIGN_RIGHT_DOWN.get());
    helper.succeed();
  }

  @GameTest(structure = "fire_extinguisher:gametest.1x1x1")
  public void testExitSignRightUpItem(GameTestHelper helper) {
    ModItemsTestHelper.testModItem(helper, ModBlockItems.EXIT_SIGN_RIGHT_UP.get());
    helper.succeed();
  }
}

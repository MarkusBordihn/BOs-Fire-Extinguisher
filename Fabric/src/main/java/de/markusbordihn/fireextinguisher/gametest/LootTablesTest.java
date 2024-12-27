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
import de.markusbordihn.fireextinguisher.item.ModBlockItems;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;

@SuppressWarnings("unused")
public class LootTablesTest {

  @GameTest(template = "fire_extinguisher:gametest.1x1x1")
  public void testFireExtinguisherDrop(GameTestHelper helper) {
    LootTableTestHelper.testBlockDropItem(
        helper, FireExtinguisherBlocks.FIRE_EXTINGUISHER, ModBlockItems.FIRE_EXTINGUISHER);
    helper.succeed();
  }

  @GameTest(template = "fire_extinguisher:gametest.1x1x1")
  public void testFireExtinguisherCopperDrop(GameTestHelper helper) {
    LootTableTestHelper.testBlockDropItem(
        helper,
        FireExtinguisherBlocks.FIRE_EXTINGUISHER_COPPER,
        ModBlockItems.FIRE_EXTINGUISHER_COPPER);
    helper.succeed();
  }

  @GameTest(template = "fire_extinguisher:gametest.1x1x1")
  public void testFireSprinklerDrop(GameTestHelper helper) {
    LootTableTestHelper.testBlockDropItem(
        helper, FireAlarmBlocks.FIRE_SPRINKLER, ModBlockItems.FIRE_SPRINKLER);
    helper.succeed();
  }

  @GameTest(template = "fire_extinguisher:gametest.1x1x1")
  public void testFireAlarmSwitchDrop(GameTestHelper helper) {
    LootTableTestHelper.testBlockDropItem(
        helper, FireAlarmBlocks.FIRE_ALARM_SWITCH, ModBlockItems.FIRE_ALARM_SWITCH);
    helper.succeed();
  }

  @GameTest(template = "fire_extinguisher:gametest.1x1x1")
  public void testFireAlarmBellDrop(GameTestHelper helper) {
    LootTableTestHelper.testBlockDropItem(
        helper, FireAlarmBlocks.FIRE_ALARM_BELL, ModBlockItems.FIRE_ALARM_BELL);
    helper.succeed();
  }

  @GameTest(template = "fire_extinguisher:gametest.1x1x1")
  public void testFireAlarmSirenDrop(GameTestHelper helper) {
    LootTableTestHelper.testBlockDropItem(
        helper, FireAlarmBlocks.FIRE_ALARM_SIREN, ModBlockItems.FIRE_ALARM_SIREN);
    helper.succeed();
  }

  @GameTest(template = "fire_extinguisher:gametest.1x1x1")
  public void testFireAlarmSmokeDetectorDrop(GameTestHelper helper) {
    LootTableTestHelper.testBlockDropItem(
        helper, FireAlarmBlocks.FIRE_ALARM_SMOKE_DETECTOR, ModBlockItems.FIRE_ALARM_SMOKE_DETECTOR);
    helper.succeed();
  }

  @GameTest(template = "fire_extinguisher:gametest.1x1x1")
  public void testFireAlarmSmokeDetectorSilentDrop(GameTestHelper helper) {
    LootTableTestHelper.testBlockDropItem(
        helper,
        FireAlarmBlocks.FIRE_ALARM_SMOKE_DETECTOR_SILENT,
        ModBlockItems.FIRE_ALARM_SMOKE_DETECTOR_SILENT);
    helper.succeed();
  }

  @GameTest(template = "fire_extinguisher:gametest.1x1x1")
  public void testFireExtinguisherSignDrop(GameTestHelper helper) {
    LootTableTestHelper.testAxeBlockDropItem(
        helper,
        FireExtinguisherSignBlocks.FIRE_EXTINGUISHER_SIGN,
        ModBlockItems.FIRE_EXTINGUISHER_SIGN);
    helper.succeed();
  }

  @GameTest(template = "fire_extinguisher:gametest.1x1x1")
  public void testFireExtinguisherSignLeftDrop(GameTestHelper helper) {
    LootTableTestHelper.testAxeBlockDropItem(
        helper,
        FireExtinguisherSignBlocks.FIRE_EXTINGUISHER_SIGN_LEFT,
        ModBlockItems.FIRE_EXTINGUISHER_SIGN_LEFT);
    helper.succeed();
  }

  @GameTest(template = "fire_extinguisher:gametest.1x1x1")
  public void testFireExtinguisherSignRightDrop(GameTestHelper helper) {
    LootTableTestHelper.testAxeBlockDropItem(
        helper,
        FireExtinguisherSignBlocks.FIRE_EXTINGUISHER_SIGN_RIGHT,
        ModBlockItems.FIRE_EXTINGUISHER_SIGN_RIGHT);
    helper.succeed();
  }

  @GameTest(template = "fire_extinguisher:gametest.1x1x1")
  public void testExitSignDrop(GameTestHelper helper) {
    LootTableTestHelper.testAxeBlockDropItem(
        helper, ExitSignBlocks.EXIT_SIGN, ModBlockItems.EXIT_SIGN);
    helper.succeed();
  }

  @GameTest(template = "fire_extinguisher:gametest.1x1x1")
  public void testExitSignLeftDrop(GameTestHelper helper) {
    LootTableTestHelper.testAxeBlockDropItem(
        helper, ExitSignBlocks.EXIT_SIGN_LEFT, ModBlockItems.EXIT_SIGN_LEFT);
    helper.succeed();
  }

  @GameTest(template = "fire_extinguisher:gametest.1x1x1")
  public void testExitSignLeftDownDrop(GameTestHelper helper) {
    LootTableTestHelper.testAxeBlockDropItem(
        helper, ExitSignBlocks.EXIT_SIGN_LEFT_DOWN, ModBlockItems.EXIT_SIGN_LEFT_DOWN);
    helper.succeed();
  }

  @GameTest(template = "fire_extinguisher:gametest.1x1x1")
  public void testExitSignLeftUpDrop(GameTestHelper helper) {
    LootTableTestHelper.testAxeBlockDropItem(
        helper, ExitSignBlocks.EXIT_SIGN_LEFT_UP, ModBlockItems.EXIT_SIGN_LEFT_UP);
    helper.succeed();
  }

  @GameTest(template = "fire_extinguisher:gametest.1x1x1")
  public void testExitSignRightDrop(GameTestHelper helper) {
    LootTableTestHelper.testAxeBlockDropItem(
        helper, ExitSignBlocks.EXIT_SIGN_RIGHT, ModBlockItems.EXIT_SIGN_RIGHT);
    helper.succeed();
  }

  @GameTest(template = "fire_extinguisher:gametest.1x1x1")
  public void testExitSignRightDownDrop(GameTestHelper helper) {
    LootTableTestHelper.testAxeBlockDropItem(
        helper, ExitSignBlocks.EXIT_SIGN_RIGHT_DOWN, ModBlockItems.EXIT_SIGN_RIGHT_DOWN);
    helper.succeed();
  }

  @GameTest(template = "fire_extinguisher:gametest.1x1x1")
  public void testExitSignRightUpDrop(GameTestHelper helper) {
    LootTableTestHelper.testAxeBlockDropItem(
        helper, ExitSignBlocks.EXIT_SIGN_RIGHT_UP, ModBlockItems.EXIT_SIGN_RIGHT_UP);
    helper.succeed();
  }
}

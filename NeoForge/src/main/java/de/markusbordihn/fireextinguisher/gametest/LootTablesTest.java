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
import de.markusbordihn.fireextinguisher.item.ModBlockItems;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.neoforged.neoforge.gametest.GameTestHolder;
import net.neoforged.neoforge.gametest.PrefixGameTestTemplate;

@SuppressWarnings("unused")
@PrefixGameTestTemplate(value = false)
@GameTestHolder(Constants.MOD_ID)
public class LootTablesTest {

  @GameTest(template = "gametest.1x1x1")
  public void testFireExtinguisherDrop(GameTestHelper helper) {
    LootTableTestHelper.testBlockDropItem(
        helper, ModBlocks.FIRE_EXTINGUISHER.get(), ModBlockItems.FIRE_EXTINGUISHER.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testFireExtinguisherCopperDrop(GameTestHelper helper) {
    LootTableTestHelper.testBlockDropItem(
        helper,
        ModBlocks.FIRE_EXTINGUISHER_COPPER.get(),
        ModBlockItems.FIRE_EXTINGUISHER_COPPER.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testFireSprinklerDrop(GameTestHelper helper) {
    LootTableTestHelper.testBlockDropItem(
        helper, ModBlocks.FIRE_SPRINKLER.get(), ModBlockItems.FIRE_SPRINKLER.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testFireAlarmSwitchDrop(GameTestHelper helper) {
    LootTableTestHelper.testBlockDropItem(
        helper, ModBlocks.FIRE_ALARM_SWITCH.get(), ModBlockItems.FIRE_ALARM_SWITCH.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testFireAlarmBellDrop(GameTestHelper helper) {
    LootTableTestHelper.testBlockDropItem(
        helper, ModBlocks.FIRE_ALARM_BELL.get(), ModBlockItems.FIRE_ALARM_BELL.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testFireAlarmSirenDrop(GameTestHelper helper) {
    LootTableTestHelper.testBlockDropItem(
        helper, ModBlocks.FIRE_ALARM_SIREN.get(), ModBlockItems.FIRE_ALARM_SIREN.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testFireAlarmSmokeDetectorDrop(GameTestHelper helper) {
    LootTableTestHelper.testBlockDropItem(
        helper,
        ModBlocks.FIRE_ALARM_SMOKE_DETECTOR.get(),
        ModBlockItems.FIRE_ALARM_SMOKE_DETECTOR.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testFireAlarmSmokeDetectorSilentDrop(GameTestHelper helper) {
    LootTableTestHelper.testBlockDropItem(
        helper,
        ModBlocks.FIRE_ALARM_SMOKE_DETECTOR_SILENT.get(),
        ModBlockItems.FIRE_ALARM_SMOKE_DETECTOR_SILENT.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testFireExtinguisherSignDrop(GameTestHelper helper) {
    LootTableTestHelper.testAxeBlockDropItem(
        helper, ModBlocks.FIRE_EXTINGUISHER_SIGN.get(), ModBlockItems.FIRE_EXTINGUISHER_SIGN.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testFireExtinguisherSignLeftDrop(GameTestHelper helper) {
    LootTableTestHelper.testAxeBlockDropItem(
        helper,
        ModBlocks.FIRE_EXTINGUISHER_SIGN_LEFT.get(),
        ModBlockItems.FIRE_EXTINGUISHER_SIGN_LEFT.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testFireExtinguisherSignRightDrop(GameTestHelper helper) {
    LootTableTestHelper.testAxeBlockDropItem(
        helper,
        ModBlocks.FIRE_EXTINGUISHER_SIGN_RIGHT.get(),
        ModBlockItems.FIRE_EXTINGUISHER_SIGN_RIGHT.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testExitSignDrop(GameTestHelper helper) {
    LootTableTestHelper.testAxeBlockDropItem(
        helper, ModBlocks.EXIT_SIGN.get(), ModBlockItems.EXIT_SIGN.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testExitSignLeftDrop(GameTestHelper helper) {
    LootTableTestHelper.testAxeBlockDropItem(
        helper, ModBlocks.EXIT_SIGN_LEFT.get(), ModBlockItems.EXIT_SIGN_LEFT.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testExitSignLeftDownDrop(GameTestHelper helper) {
    LootTableTestHelper.testAxeBlockDropItem(
        helper, ModBlocks.EXIT_SIGN_LEFT_DOWN.get(), ModBlockItems.EXIT_SIGN_LEFT_DOWN.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testExitSignLeftUpDrop(GameTestHelper helper) {
    LootTableTestHelper.testAxeBlockDropItem(
        helper, ModBlocks.EXIT_SIGN_LEFT_UP.get(), ModBlockItems.EXIT_SIGN_LEFT_UP.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testExitSignRightDrop(GameTestHelper helper) {
    LootTableTestHelper.testAxeBlockDropItem(
        helper, ModBlocks.EXIT_SIGN_RIGHT.get(), ModBlockItems.EXIT_SIGN_RIGHT.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testExitSignRightDownDrop(GameTestHelper helper) {
    LootTableTestHelper.testAxeBlockDropItem(
        helper, ModBlocks.EXIT_SIGN_RIGHT_DOWN.get(), ModBlockItems.EXIT_SIGN_RIGHT_DOWN.get());
    helper.succeed();
  }

  @GameTest(template = "gametest.1x1x1")
  public void testExitSignRightUpDrop(GameTestHelper helper) {
    LootTableTestHelper.testAxeBlockDropItem(
        helper, ModBlocks.EXIT_SIGN_RIGHT_UP.get(), ModBlockItems.EXIT_SIGN_RIGHT_UP.get());
    helper.succeed();
  }
}

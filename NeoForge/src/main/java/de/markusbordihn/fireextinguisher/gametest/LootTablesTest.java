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
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.resources.ResourceLocation;

@SuppressWarnings("unused")
public class LootTablesTest {

  static {
    Registry.register(
        BuiltInRegistries.TEST_FUNCTION,
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "test_fire_extinguisher_drop"),
        LootTablesTest::testFireExtinguisherDrop);
    Registry.register(
        BuiltInRegistries.TEST_FUNCTION,
        ResourceLocation.fromNamespaceAndPath(
            Constants.MOD_ID, "test_fire_extinguisher_copper_drop"),
        LootTablesTest::testFireExtinguisherCopperDrop);
    Registry.register(
        BuiltInRegistries.TEST_FUNCTION,
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "test_fire_sprinkler_drop"),
        LootTablesTest::testFireSprinklerDrop);
    Registry.register(
        BuiltInRegistries.TEST_FUNCTION,
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "test_fire_alarm_switch_drop"),
        LootTablesTest::testFireAlarmSwitchDrop);
    Registry.register(
        BuiltInRegistries.TEST_FUNCTION,
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "test_fire_alarm_bell_drop"),
        LootTablesTest::testFireAlarmBellDrop);
    Registry.register(
        BuiltInRegistries.TEST_FUNCTION,
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "test_fire_alarm_siren_drop"),
        LootTablesTest::testFireAlarmSirenDrop);
    Registry.register(
        BuiltInRegistries.TEST_FUNCTION,
        ResourceLocation.fromNamespaceAndPath(
            Constants.MOD_ID, "test_fire_alarm_smoke_detector_drop"),
        LootTablesTest::testFireAlarmSmokeDetectorDrop);
    Registry.register(
        BuiltInRegistries.TEST_FUNCTION,
        ResourceLocation.fromNamespaceAndPath(
            Constants.MOD_ID, "test_fire_alarm_smoke_detector_silent_drop"),
        LootTablesTest::testFireAlarmSmokeDetectorSilentDrop);
    Registry.register(
        BuiltInRegistries.TEST_FUNCTION,
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "test_fire_extinguisher_sign_drop"),
        LootTablesTest::testFireExtinguisherSignDrop);
    Registry.register(
        BuiltInRegistries.TEST_FUNCTION,
        ResourceLocation.fromNamespaceAndPath(
            Constants.MOD_ID, "test_fire_extinguisher_sign_left_drop"),
        LootTablesTest::testFireExtinguisherSignLeftDrop);
    Registry.register(
        BuiltInRegistries.TEST_FUNCTION,
        ResourceLocation.fromNamespaceAndPath(
            Constants.MOD_ID, "test_fire_extinguisher_sign_right_drop"),
        LootTablesTest::testFireExtinguisherSignRightDrop);
    Registry.register(
        BuiltInRegistries.TEST_FUNCTION,
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "test_exit_sign_drop"),
        LootTablesTest::testExitSignDrop);
    Registry.register(
        BuiltInRegistries.TEST_FUNCTION,
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "test_exit_sign_left_drop"),
        LootTablesTest::testExitSignLeftDrop);
    Registry.register(
        BuiltInRegistries.TEST_FUNCTION,
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "test_exit_sign_left_down_drop"),
        LootTablesTest::testExitSignLeftDownDrop);
    Registry.register(
        BuiltInRegistries.TEST_FUNCTION,
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "test_exit_sign_left_up_drop"),
        LootTablesTest::testExitSignLeftUpDrop);
    Registry.register(
        BuiltInRegistries.TEST_FUNCTION,
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "test_exit_sign_right_drop"),
        LootTablesTest::testExitSignRightDrop);
    Registry.register(
        BuiltInRegistries.TEST_FUNCTION,
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "test_exit_sign_right_down_drop"),
        LootTablesTest::testExitSignRightDownDrop);
    Registry.register(
        BuiltInRegistries.TEST_FUNCTION,
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "test_exit_sign_right_up_drop"),
        LootTablesTest::testExitSignRightUpDrop);
  }

  public static void testFireExtinguisherDrop(GameTestHelper helper) {
    LootTableTestHelper.testBlockDropItem(
        helper, ModBlocks.FIRE_EXTINGUISHER.get(), ModBlockItems.FIRE_EXTINGUISHER.get());
    helper.succeed();
  }

  public static void testFireExtinguisherCopperDrop(GameTestHelper helper) {
    LootTableTestHelper.testBlockDropItem(
        helper,
        ModBlocks.FIRE_EXTINGUISHER_COPPER.get(),
        ModBlockItems.FIRE_EXTINGUISHER_COPPER.get());
    helper.succeed();
  }

  public static void testFireSprinklerDrop(GameTestHelper helper) {
    LootTableTestHelper.testBlockDropItem(
        helper, ModBlocks.FIRE_SPRINKLER.get(), ModBlockItems.FIRE_SPRINKLER.get());
    helper.succeed();
  }

  public static void testFireAlarmSwitchDrop(GameTestHelper helper) {
    LootTableTestHelper.testBlockDropItem(
        helper, ModBlocks.FIRE_ALARM_SWITCH.get(), ModBlockItems.FIRE_ALARM_SWITCH.get());
    helper.succeed();
  }

  public static void testFireAlarmBellDrop(GameTestHelper helper) {
    LootTableTestHelper.testBlockDropItem(
        helper, ModBlocks.FIRE_ALARM_BELL.get(), ModBlockItems.FIRE_ALARM_BELL.get());
    helper.succeed();
  }

  public static void testFireAlarmSirenDrop(GameTestHelper helper) {
    LootTableTestHelper.testBlockDropItem(
        helper, ModBlocks.FIRE_ALARM_SIREN.get(), ModBlockItems.FIRE_ALARM_SIREN.get());
    helper.succeed();
  }

  public static void testFireAlarmSmokeDetectorDrop(GameTestHelper helper) {
    LootTableTestHelper.testBlockDropItem(
        helper,
        ModBlocks.FIRE_ALARM_SMOKE_DETECTOR.get(),
        ModBlockItems.FIRE_ALARM_SMOKE_DETECTOR.get());
    helper.succeed();
  }

  public static void testFireAlarmSmokeDetectorSilentDrop(GameTestHelper helper) {
    LootTableTestHelper.testBlockDropItem(
        helper,
        ModBlocks.FIRE_ALARM_SMOKE_DETECTOR_SILENT.get(),
        ModBlockItems.FIRE_ALARM_SMOKE_DETECTOR_SILENT.get());
    helper.succeed();
  }

  public static void testFireExtinguisherSignDrop(GameTestHelper helper) {
    LootTableTestHelper.testAxeBlockDropItem(
        helper, ModBlocks.FIRE_EXTINGUISHER_SIGN.get(), ModBlockItems.FIRE_EXTINGUISHER_SIGN.get());
    helper.succeed();
  }

  public static void testFireExtinguisherSignLeftDrop(GameTestHelper helper) {
    LootTableTestHelper.testAxeBlockDropItem(
        helper,
        ModBlocks.FIRE_EXTINGUISHER_SIGN_LEFT.get(),
        ModBlockItems.FIRE_EXTINGUISHER_SIGN_LEFT.get());
    helper.succeed();
  }

  public static void testFireExtinguisherSignRightDrop(GameTestHelper helper) {
    LootTableTestHelper.testAxeBlockDropItem(
        helper,
        ModBlocks.FIRE_EXTINGUISHER_SIGN_RIGHT.get(),
        ModBlockItems.FIRE_EXTINGUISHER_SIGN_RIGHT.get());
    helper.succeed();
  }

  public static void testExitSignDrop(GameTestHelper helper) {
    LootTableTestHelper.testAxeBlockDropItem(
        helper, ModBlocks.EXIT_SIGN.get(), ModBlockItems.EXIT_SIGN.get());
    helper.succeed();
  }

  public static void testExitSignLeftDrop(GameTestHelper helper) {
    LootTableTestHelper.testAxeBlockDropItem(
        helper, ModBlocks.EXIT_SIGN_LEFT.get(), ModBlockItems.EXIT_SIGN_LEFT.get());
    helper.succeed();
  }

  public static void testExitSignLeftDownDrop(GameTestHelper helper) {
    LootTableTestHelper.testAxeBlockDropItem(
        helper, ModBlocks.EXIT_SIGN_LEFT_DOWN.get(), ModBlockItems.EXIT_SIGN_LEFT_DOWN.get());
    helper.succeed();
  }

  public static void testExitSignLeftUpDrop(GameTestHelper helper) {
    LootTableTestHelper.testAxeBlockDropItem(
        helper, ModBlocks.EXIT_SIGN_LEFT_UP.get(), ModBlockItems.EXIT_SIGN_LEFT_UP.get());
    helper.succeed();
  }

  public static void testExitSignRightDrop(GameTestHelper helper) {
    LootTableTestHelper.testAxeBlockDropItem(
        helper, ModBlocks.EXIT_SIGN_RIGHT.get(), ModBlockItems.EXIT_SIGN_RIGHT.get());
    helper.succeed();
  }

  public static void testExitSignRightDownDrop(GameTestHelper helper) {
    LootTableTestHelper.testAxeBlockDropItem(
        helper, ModBlocks.EXIT_SIGN_RIGHT_DOWN.get(), ModBlockItems.EXIT_SIGN_RIGHT_DOWN.get());
    helper.succeed();
  }

  public static void testExitSignRightUpDrop(GameTestHelper helper) {
    LootTableTestHelper.testAxeBlockDropItem(
        helper, ModBlocks.EXIT_SIGN_RIGHT_UP.get(), ModBlockItems.EXIT_SIGN_RIGHT_UP.get());
    helper.succeed();
  }
}

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

import de.markusbordihn.fireextinguisher.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.gametest.framework.FunctionGameTestInstance;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.gametest.framework.TestData;
import net.minecraft.gametest.framework.TestEnvironmentDefinition;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.event.RegisterGameTestsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * NeoForge has no annotation based game test discovery, so every test method is registered here and
 * paired with the structure it should run in.
 */
@EventBusSubscriber
public final class ModGameTests {

  private static final DeferredRegister<Consumer<GameTestHelper>> TEST_FUNCTIONS =
      DeferredRegister.create(BuiltInRegistries.TEST_FUNCTION, Constants.MOD_ID);
  private static final List<TestEntry> TEST_ENTRIES = new ArrayList<>();
  private static final int DEFAULT_MAX_TICKS = 100;
  private static final Identifier STRUCTURE_1X1X1 =
      Identifier.fromNamespaceAndPath(Constants.MOD_ID, "gametest.1x1x1");
  private static final Identifier STRUCTURE_3X3X3 =
      Identifier.fromNamespaceAndPath(Constants.MOD_ID, "gametest.3x3x3");

  private static final FireAlarmControlPanelTest FIRE_ALARM_CONTROL_PANEL_TESTS =
      new FireAlarmControlPanelTest();
  private static final FireAlarmSmokeDetectorTest FIRE_ALARM_SMOKE_DETECTOR_TESTS =
      new FireAlarmSmokeDetectorTest();
  private static final FireArmorTest FIRE_ARMOR_TESTS = new FireArmorTest();
  private static final FireDetectionTest FIRE_DETECTION_TESTS = new FireDetectionTest();
  private static final FireHydrantTest FIRE_HYDRANT_TESTS = new FireHydrantTest();
  private static final FirePoleTest FIRE_POLE_TESTS = new FirePoleTest();
  private static final FireSprinklerTest FIRE_SPRINKLER_TESTS = new FireSprinklerTest();
  private static final LootTablesTest LOOT_TABLES_TESTS = new LootTablesTest();
  private static final ModBlockItemsTest MOD_BLOCK_ITEMS_TESTS = new ModBlockItemsTest();
  private static final ModBlocksTest MOD_BLOCKS_TESTS = new ModBlocksTest();
  private static final ModItemsTest MOD_ITEMS_TESTS = new ModItemsTest();
  private static final SmokeTest SMOKE_TESTS = new SmokeTest();

  static {
    register(
        "fire_alarm_control_panel_redstone_powers_alarms_in_range",
        FIRE_ALARM_CONTROL_PANEL_TESTS::testRedstonePowersAlarmsInRange,
        STRUCTURE_3X3X3,
        FireAlarmControlPanelTestHelper.TIMEOUT_TICKS);
    register(
        "fire_alarm_control_panel_smoke_detector_triggers_panel_without_wiring",
        FIRE_ALARM_CONTROL_PANEL_TESTS::testSmokeDetectorTriggersPanelWithoutWiring,
        STRUCTURE_3X3X3,
        FireAlarmControlPanelTestHelper.TIMEOUT_TICKS);
    register(
        "fire_alarm_control_panel_does_not_power_alarms_out_of_range",
        FIRE_ALARM_CONTROL_PANEL_TESTS::testDoesNotPowerAlarmsOutOfRange,
        STRUCTURE_3X3X3,
        FireAlarmControlPanelTestHelper.TIMEOUT_TICKS);
    register(
        "fire_alarm_smoke_detector_detects_fire",
        FIRE_ALARM_SMOKE_DETECTOR_TESTS::testDetectsFire,
        STRUCTURE_3X3X3,
        FireAlarmSmokeDetectorTestHelper.TIMEOUT_TICKS);
    register(
        "fire_alarm_smoke_detector_silent_detects_fire",
        FIRE_ALARM_SMOKE_DETECTOR_TESTS::testSilentDetectsFire,
        STRUCTURE_3X3X3,
        FireAlarmSmokeDetectorTestHelper.TIMEOUT_TICKS);
    register(
        "fire_alarm_smoke_detector_releases_after_fire_is_gone",
        FIRE_ALARM_SMOKE_DETECTOR_TESTS::testReleasesAfterFireIsGone,
        STRUCTURE_3X3X3,
        FireAlarmSmokeDetectorTestHelper.TIMEOUT_TICKS);
    register(
        "fire_alarm_smoke_detector_detects_fire_placed_before_detector",
        FIRE_ALARM_SMOKE_DETECTOR_TESTS::testDetectsFirePlacedBeforeDetector,
        STRUCTURE_3X3X3,
        FireAlarmSmokeDetectorTestHelper.TIMEOUT_TICKS);
    register(
        "fire_armor_worn_pieces_are_counted",
        FIRE_ARMOR_TESTS::testWornPiecesAreCounted,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "fire_armor_light_armor_pieces_are_counted",
        FIRE_ARMOR_TESTS::testLightArmorPiecesAreCounted,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "fire_armor_other_material_is_not_counted",
        FIRE_ARMOR_TESTS::testOtherMaterialIsNotCounted,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "fire_armor_protection_duration_stacks_per_worn_piece",
        FIRE_ARMOR_TESTS::testProtectionDurationStacksPerWornPiece,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "fire_detection_extinguish_fire",
        FIRE_DETECTION_TESTS::testExtinguishFire,
        STRUCTURE_3X3X3,
        DEFAULT_MAX_TICKS);
    register(
        "fire_detection_extinguish_soul_fire",
        FIRE_DETECTION_TESTS::testExtinguishSoulFire,
        STRUCTURE_3X3X3,
        DEFAULT_MAX_TICKS);
    register(
        "fire_detection_extinguish_soul_campfire",
        FIRE_DETECTION_TESTS::testExtinguishSoulCampfire,
        STRUCTURE_3X3X3,
        DEFAULT_MAX_TICKS);
    register(
        "fire_detection_lit_campfire_is_no_fire_source",
        FIRE_DETECTION_TESTS::testLitCampfireIsNoFireSource,
        STRUCTURE_3X3X3,
        DEFAULT_MAX_TICKS);
    register(
        "fire_hydrant_refills_used_fire_extinguisher",
        FIRE_HYDRANT_TESTS::testRefillsUsedFireExtinguisher,
        STRUCTURE_3X3X3,
        DEFAULT_MAX_TICKS);
    register(
        "fire_hydrant_ignores_other_items",
        FIRE_HYDRANT_TESTS::testIgnoresOtherItems,
        STRUCTURE_3X3X3,
        DEFAULT_MAX_TICKS);
    register(
        "fire_pole_slides_down_without_fall_damage",
        FIRE_POLE_TESTS::testSlidesDownWithoutFallDamage,
        STRUCTURE_3X3X3,
        FirePoleTestHelper.TIMEOUT_TICKS);
    register(
        "fire_sprinkler_extinguishes_fire_below",
        FIRE_SPRINKLER_TESTS::testExtinguishesFireBelow,
        STRUCTURE_3X3X3,
        DEFAULT_MAX_TICKS);
    register(
        "fire_sprinkler_extinguishes_fire_at_horizontal_offset",
        FIRE_SPRINKLER_TESTS::testExtinguishesFireAtHorizontalOffset,
        STRUCTURE_3X3X3,
        DEFAULT_MAX_TICKS);
    register(
        "loot_tables_fire_extinguisher_drop",
        LOOT_TABLES_TESTS::testFireExtinguisherDrop,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "loot_tables_fire_extinguisher_copper_drop",
        LOOT_TABLES_TESTS::testFireExtinguisherCopperDrop,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "loot_tables_fire_sprinkler_drop",
        LOOT_TABLES_TESTS::testFireSprinklerDrop,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "loot_tables_fire_alarm_switch_drop",
        LOOT_TABLES_TESTS::testFireAlarmSwitchDrop,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "loot_tables_fire_alarm_bell_drop",
        LOOT_TABLES_TESTS::testFireAlarmBellDrop,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "loot_tables_fire_alarm_siren_drop",
        LOOT_TABLES_TESTS::testFireAlarmSirenDrop,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "loot_tables_fire_alarm_smoke_detector_drop",
        LOOT_TABLES_TESTS::testFireAlarmSmokeDetectorDrop,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "loot_tables_fire_alarm_smoke_detector_silent_drop",
        LOOT_TABLES_TESTS::testFireAlarmSmokeDetectorSilentDrop,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "loot_tables_fire_extinguisher_sign_drop",
        LOOT_TABLES_TESTS::testFireExtinguisherSignDrop,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "loot_tables_fire_extinguisher_sign_left_drop",
        LOOT_TABLES_TESTS::testFireExtinguisherSignLeftDrop,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "loot_tables_fire_extinguisher_sign_right_drop",
        LOOT_TABLES_TESTS::testFireExtinguisherSignRightDrop,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "loot_tables_exit_sign_drop",
        LOOT_TABLES_TESTS::testExitSignDrop,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "loot_tables_exit_sign_left_drop",
        LOOT_TABLES_TESTS::testExitSignLeftDrop,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "loot_tables_exit_sign_left_down_drop",
        LOOT_TABLES_TESTS::testExitSignLeftDownDrop,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "loot_tables_exit_sign_left_up_drop",
        LOOT_TABLES_TESTS::testExitSignLeftUpDrop,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "loot_tables_exit_sign_right_drop",
        LOOT_TABLES_TESTS::testExitSignRightDrop,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "loot_tables_exit_sign_right_down_drop",
        LOOT_TABLES_TESTS::testExitSignRightDownDrop,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "loot_tables_exit_sign_right_up_drop",
        LOOT_TABLES_TESTS::testExitSignRightUpDrop,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "loot_tables_exit_sign_plain_left_drop",
        LOOT_TABLES_TESTS::testExitSignPlainLeftDrop,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "loot_tables_exit_sign_plain_right_drop",
        LOOT_TABLES_TESTS::testExitSignPlainRightDrop,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "loot_tables_fire_alarm_switch_eu_drop",
        LOOT_TABLES_TESTS::testFireAlarmSwitchEuDrop,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "loot_tables_fire_alarm_switch_jp_drop",
        LOOT_TABLES_TESTS::testFireAlarmSwitchJpDrop,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "loot_tables_fire_alarm_control_panel_drop",
        LOOT_TABLES_TESTS::testFireAlarmControlPanelDrop,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "loot_tables_fire_alarm_light_drop",
        LOOT_TABLES_TESTS::testFireAlarmLightDrop,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "loot_tables_fire_pole_drop",
        LOOT_TABLES_TESTS::testFirePoleDrop,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "loot_tables_fire_hydrant_drop",
        LOOT_TABLES_TESTS::testFireHydrantDrop,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_block_items_fire_extinguisher_item",
        MOD_BLOCK_ITEMS_TESTS::testFireExtinguisherItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_block_items_fire_extinguisher_copper_item",
        MOD_BLOCK_ITEMS_TESTS::testFireExtinguisherCopperItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_block_items_fire_sprinkler_item",
        MOD_BLOCK_ITEMS_TESTS::testFireSprinklerItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_block_items_fire_alarm_switch_item",
        MOD_BLOCK_ITEMS_TESTS::testFireAlarmSwitchItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_block_items_fire_alarm_bell_item",
        MOD_BLOCK_ITEMS_TESTS::testFireAlarmBellItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_block_items_fire_alarm_siren_item",
        MOD_BLOCK_ITEMS_TESTS::testFireAlarmSirenItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_block_items_fire_alarm_smoke_detector_item",
        MOD_BLOCK_ITEMS_TESTS::testFireAlarmSmokeDetectorItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_block_items_fire_alarm_smoke_detector_silent_item",
        MOD_BLOCK_ITEMS_TESTS::testFireAlarmSmokeDetectorSilentItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_block_items_fire_extinguisher_sign_item",
        MOD_BLOCK_ITEMS_TESTS::testFireExtinguisherSignItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_block_items_fire_extinguisher_sign_left_item",
        MOD_BLOCK_ITEMS_TESTS::testFireExtinguisherSignLeftItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_block_items_fire_extinguisher_sign_right_item",
        MOD_BLOCK_ITEMS_TESTS::testFireExtinguisherSignRightItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_block_items_exit_sign_item",
        MOD_BLOCK_ITEMS_TESTS::testExitSignItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_block_items_exit_sign_left_item",
        MOD_BLOCK_ITEMS_TESTS::testExitSignLeftItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_block_items_exit_sign_left_down_item",
        MOD_BLOCK_ITEMS_TESTS::testExitSignLeftDownItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_block_items_exit_sign_left_up_item",
        MOD_BLOCK_ITEMS_TESTS::testExitSignLeftUpItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_block_items_exit_sign_right_item",
        MOD_BLOCK_ITEMS_TESTS::testExitSignRightItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_block_items_exit_sign_right_down_item",
        MOD_BLOCK_ITEMS_TESTS::testExitSignRightDownItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_block_items_exit_sign_right_up_item",
        MOD_BLOCK_ITEMS_TESTS::testExitSignRightUpItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_block_items_exit_sign_plain_left_item",
        MOD_BLOCK_ITEMS_TESTS::testExitSignPlainLeftItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_block_items_exit_sign_plain_right_item",
        MOD_BLOCK_ITEMS_TESTS::testExitSignPlainRightItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_block_items_fire_alarm_switch_eu_item",
        MOD_BLOCK_ITEMS_TESTS::testFireAlarmSwitchEuItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_block_items_fire_alarm_switch_jp_item",
        MOD_BLOCK_ITEMS_TESTS::testFireAlarmSwitchJpItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_block_items_fire_alarm_control_panel_item",
        MOD_BLOCK_ITEMS_TESTS::testFireAlarmControlPanelItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_block_items_fire_alarm_light_item",
        MOD_BLOCK_ITEMS_TESTS::testFireAlarmLightItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_block_items_fire_pole_item",
        MOD_BLOCK_ITEMS_TESTS::testFirePoleItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_block_items_fire_hydrant_item",
        MOD_BLOCK_ITEMS_TESTS::testFireHydrantItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_blocks_fire_extinguisher_block",
        MOD_BLOCKS_TESTS::testFireExtinguisherBlock,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_blocks_fire_extinguisher_copper_block",
        MOD_BLOCKS_TESTS::testFireExtinguisherCopperBlock,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_blocks_fire_sprinkler_block",
        MOD_BLOCKS_TESTS::testFireSprinklerBlock,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_blocks_fire_alarm_switch_block",
        MOD_BLOCKS_TESTS::testFireAlarmSwitchBlock,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_blocks_fire_alarm_bell_block",
        MOD_BLOCKS_TESTS::testFireAlarmBellBlock,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_blocks_fire_alarm_siren_block",
        MOD_BLOCKS_TESTS::testFireAlarmSirenBlock,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_blocks_fire_alarm_smoke_detector_block",
        MOD_BLOCKS_TESTS::testFireAlarmSmokeDetectorBlock,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_blocks_fire_alarm_smoke_detector_silent_block",
        MOD_BLOCKS_TESTS::testFireAlarmSmokeDetectorSilentBlock,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_blocks_fire_extinguisher_sign_block",
        MOD_BLOCKS_TESTS::testFireExtinguisherSignBlock,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_blocks_fire_extinguisher_sign_left_block",
        MOD_BLOCKS_TESTS::testFireExtinguisherSignLeftBlock,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_blocks_fire_extinguisher_sign_right_block",
        MOD_BLOCKS_TESTS::testFireExtinguisherSignRightBlock,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_blocks_exit_sign_block",
        MOD_BLOCKS_TESTS::testExitSignBlock,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_blocks_exit_sign_left_block",
        MOD_BLOCKS_TESTS::testExitSignLeftBlock,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_blocks_exit_sign_left_down_block",
        MOD_BLOCKS_TESTS::testExitSignLeftDownBlock,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_blocks_exit_sign_left_up_block",
        MOD_BLOCKS_TESTS::testExitSignLeftUpBlock,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_blocks_exit_sign_right_block",
        MOD_BLOCKS_TESTS::testExitSignRightBlock,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_blocks_exit_sign_right_down_block",
        MOD_BLOCKS_TESTS::testExitSignRightDownBlock,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_blocks_exit_sign_right_up_block",
        MOD_BLOCKS_TESTS::testExitSignRightUpBlock,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_blocks_exit_sign_plain_left_block",
        MOD_BLOCKS_TESTS::testExitSignPlainLeftBlock,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_blocks_exit_sign_plain_right_block",
        MOD_BLOCKS_TESTS::testExitSignPlainRightBlock,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_blocks_fire_alarm_switch_eu_block",
        MOD_BLOCKS_TESTS::testFireAlarmSwitchEuBlock,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_blocks_fire_alarm_switch_jp_block",
        MOD_BLOCKS_TESTS::testFireAlarmSwitchJpBlock,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_blocks_fire_alarm_control_panel_block",
        MOD_BLOCKS_TESTS::testFireAlarmControlPanelBlock,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_blocks_fire_alarm_light_block",
        MOD_BLOCKS_TESTS::testFireAlarmLightBlock,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_blocks_fire_pole_block",
        MOD_BLOCKS_TESTS::testFirePoleBlock,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_blocks_fire_hydrant_block",
        MOD_BLOCKS_TESTS::testFireHydrantBlock,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_items_fire_axe_item",
        MOD_ITEMS_TESTS::testFireAxeItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_items_fire_helmet_item",
        MOD_ITEMS_TESTS::testFireHelmetItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_items_fire_chestplate_item",
        MOD_ITEMS_TESTS::testFireChestplateItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_items_fire_leggings_item",
        MOD_ITEMS_TESTS::testFireLeggingsItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_items_fire_boots_item",
        MOD_ITEMS_TESTS::testFireBootsItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_items_fire_helmet_light_item",
        MOD_ITEMS_TESTS::testFireHelmetLightItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_items_fire_chestplate_light_item",
        MOD_ITEMS_TESTS::testFireChestplateLightItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_items_fire_leggings_light_item",
        MOD_ITEMS_TESTS::testFireLeggingsLightItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "mod_items_fire_boots_light_item",
        MOD_ITEMS_TESTS::testFireBootsLightItem,
        STRUCTURE_1X1X1,
        DEFAULT_MAX_TICKS);
    register(
        "smoke_mod_registered", SMOKE_TESTS::testModRegistered, STRUCTURE_1X1X1, DEFAULT_MAX_TICKS);
  }

  private ModGameTests() {}

  public static void register(IEventBus modEventBus) {
    if (FMLEnvironment.isProduction()) {
      return;
    }

    TEST_FUNCTIONS.register(modEventBus);
  }

  @SubscribeEvent
  public static void registerGameTests(RegisterGameTestsEvent event) {
    Holder<TestEnvironmentDefinition<?>> environment =
        event.registerEnvironment(
            Identifier.fromNamespaceAndPath(Constants.MOD_ID, "default"),
            new TestEnvironmentDefinition.AllOf(List.of()));

    for (TestEntry testEntry : TEST_ENTRIES) {
      event.registerTest(
          testEntry.testFunction().getId(),
          new FunctionGameTestInstance(
              testEntry.testFunction().getKey(),
              new TestData<>(environment, testEntry.structure(), testEntry.maxTicks(), 0, true)));
    }
  }

  private static void register(
      String name, Consumer<GameTestHelper> testFunction, Identifier structure, int maxTicks) {
    TEST_ENTRIES.add(
        new TestEntry(TEST_FUNCTIONS.register(name, () -> testFunction), structure, maxTicks));
  }

  private record TestEntry(
      DeferredHolder<Consumer<GameTestHelper>, Consumer<GameTestHelper>> testFunction,
      Identifier structure,
      int maxTicks) {}
}

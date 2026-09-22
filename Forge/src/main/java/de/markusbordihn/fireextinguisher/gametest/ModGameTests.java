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
import java.util.function.Consumer;
import net.minecraft.core.registries.Registries;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.registries.DeferredRegister;

/**
 * Forge does not scan game test annotations, so every test function is registered here and paired
 * with a {@code data/fire_extinguisher/test_instance} entry.
 */
public final class ModGameTests {

  private static final DeferredRegister<Consumer<GameTestHelper>> TEST_FUNCTIONS =
      DeferredRegister.create(Registries.TEST_FUNCTION, Constants.MOD_ID);

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
        FIRE_ALARM_CONTROL_PANEL_TESTS::testRedstonePowersAlarmsInRange);
    register(
        "fire_alarm_control_panel_smoke_detector_triggers_panel_without_wiring",
        FIRE_ALARM_CONTROL_PANEL_TESTS::testSmokeDetectorTriggersPanelWithoutWiring);
    register(
        "fire_alarm_control_panel_does_not_power_alarms_out_of_range",
        FIRE_ALARM_CONTROL_PANEL_TESTS::testDoesNotPowerAlarmsOutOfRange);
    register(
        "fire_alarm_smoke_detector_detects_fire", FIRE_ALARM_SMOKE_DETECTOR_TESTS::testDetectsFire);
    register(
        "fire_alarm_smoke_detector_silent_detects_fire",
        FIRE_ALARM_SMOKE_DETECTOR_TESTS::testSilentDetectsFire);
    register(
        "fire_alarm_smoke_detector_releases_after_fire_is_gone",
        FIRE_ALARM_SMOKE_DETECTOR_TESTS::testReleasesAfterFireIsGone);
    register(
        "fire_alarm_smoke_detector_detects_fire_placed_before_detector",
        FIRE_ALARM_SMOKE_DETECTOR_TESTS::testDetectsFirePlacedBeforeDetector);
    register("fire_armor_worn_pieces_are_counted", FIRE_ARMOR_TESTS::testWornPiecesAreCounted);
    register(
        "fire_armor_light_armor_pieces_are_counted",
        FIRE_ARMOR_TESTS::testLightArmorPiecesAreCounted);
    register(
        "fire_armor_other_material_is_not_counted",
        FIRE_ARMOR_TESTS::testOtherMaterialIsNotCounted);
    register(
        "fire_armor_protection_duration_stacks_per_worn_piece",
        FIRE_ARMOR_TESTS::testProtectionDurationStacksPerWornPiece);
    register("fire_detection_extinguish_fire", FIRE_DETECTION_TESTS::testExtinguishFire);
    register("fire_detection_extinguish_soul_fire", FIRE_DETECTION_TESTS::testExtinguishSoulFire);
    register(
        "fire_detection_extinguish_soul_campfire",
        FIRE_DETECTION_TESTS::testExtinguishSoulCampfire);
    register(
        "fire_detection_lit_campfire_is_no_fire_source",
        FIRE_DETECTION_TESTS::testLitCampfireIsNoFireSource);
    register(
        "fire_hydrant_refills_used_fire_extinguisher",
        FIRE_HYDRANT_TESTS::testRefillsUsedFireExtinguisher);
    register("fire_hydrant_ignores_other_items", FIRE_HYDRANT_TESTS::testIgnoresOtherItems);
    register(
        "fire_pole_slides_down_without_fall_damage",
        FIRE_POLE_TESTS::testSlidesDownWithoutFallDamage);
    register(
        "fire_sprinkler_extinguishes_fire_below", FIRE_SPRINKLER_TESTS::testExtinguishesFireBelow);
    register(
        "fire_sprinkler_extinguishes_fire_at_horizontal_offset",
        FIRE_SPRINKLER_TESTS::testExtinguishesFireAtHorizontalOffset);
    register("loot_tables_fire_extinguisher_drop", LOOT_TABLES_TESTS::testFireExtinguisherDrop);
    register(
        "loot_tables_fire_extinguisher_copper_drop",
        LOOT_TABLES_TESTS::testFireExtinguisherCopperDrop);
    register("loot_tables_fire_sprinkler_drop", LOOT_TABLES_TESTS::testFireSprinklerDrop);
    register("loot_tables_fire_alarm_switch_drop", LOOT_TABLES_TESTS::testFireAlarmSwitchDrop);
    register("loot_tables_fire_alarm_bell_drop", LOOT_TABLES_TESTS::testFireAlarmBellDrop);
    register("loot_tables_fire_alarm_siren_drop", LOOT_TABLES_TESTS::testFireAlarmSirenDrop);
    register(
        "loot_tables_fire_alarm_smoke_detector_drop",
        LOOT_TABLES_TESTS::testFireAlarmSmokeDetectorDrop);
    register(
        "loot_tables_fire_alarm_smoke_detector_silent_drop",
        LOOT_TABLES_TESTS::testFireAlarmSmokeDetectorSilentDrop);
    register(
        "loot_tables_fire_extinguisher_sign_drop", LOOT_TABLES_TESTS::testFireExtinguisherSignDrop);
    register(
        "loot_tables_fire_extinguisher_sign_left_drop",
        LOOT_TABLES_TESTS::testFireExtinguisherSignLeftDrop);
    register(
        "loot_tables_fire_extinguisher_sign_right_drop",
        LOOT_TABLES_TESTS::testFireExtinguisherSignRightDrop);
    register("loot_tables_exit_sign_drop", LOOT_TABLES_TESTS::testExitSignDrop);
    register("loot_tables_exit_sign_left_drop", LOOT_TABLES_TESTS::testExitSignLeftDrop);
    register("loot_tables_exit_sign_left_down_drop", LOOT_TABLES_TESTS::testExitSignLeftDownDrop);
    register("loot_tables_exit_sign_left_up_drop", LOOT_TABLES_TESTS::testExitSignLeftUpDrop);
    register("loot_tables_exit_sign_right_drop", LOOT_TABLES_TESTS::testExitSignRightDrop);
    register("loot_tables_exit_sign_right_down_drop", LOOT_TABLES_TESTS::testExitSignRightDownDrop);
    register("loot_tables_exit_sign_right_up_drop", LOOT_TABLES_TESTS::testExitSignRightUpDrop);
    register("loot_tables_exit_sign_plain_left_drop", LOOT_TABLES_TESTS::testExitSignPlainLeftDrop);
    register(
        "loot_tables_exit_sign_plain_right_drop", LOOT_TABLES_TESTS::testExitSignPlainRightDrop);
    register("loot_tables_fire_alarm_switch_eu_drop", LOOT_TABLES_TESTS::testFireAlarmSwitchEuDrop);
    register("loot_tables_fire_alarm_switch_jp_drop", LOOT_TABLES_TESTS::testFireAlarmSwitchJpDrop);
    register(
        "loot_tables_fire_alarm_control_panel_drop",
        LOOT_TABLES_TESTS::testFireAlarmControlPanelDrop);
    register("loot_tables_fire_alarm_light_drop", LOOT_TABLES_TESTS::testFireAlarmLightDrop);
    register("loot_tables_fire_pole_drop", LOOT_TABLES_TESTS::testFirePoleDrop);
    register("loot_tables_fire_hydrant_drop", LOOT_TABLES_TESTS::testFireHydrantDrop);
    register(
        "mod_block_items_fire_extinguisher_item", MOD_BLOCK_ITEMS_TESTS::testFireExtinguisherItem);
    register(
        "mod_block_items_fire_extinguisher_copper_item",
        MOD_BLOCK_ITEMS_TESTS::testFireExtinguisherCopperItem);
    register("mod_block_items_fire_sprinkler_item", MOD_BLOCK_ITEMS_TESTS::testFireSprinklerItem);
    register(
        "mod_block_items_fire_alarm_switch_item", MOD_BLOCK_ITEMS_TESTS::testFireAlarmSwitchItem);
    register("mod_block_items_fire_alarm_bell_item", MOD_BLOCK_ITEMS_TESTS::testFireAlarmBellItem);
    register(
        "mod_block_items_fire_alarm_siren_item", MOD_BLOCK_ITEMS_TESTS::testFireAlarmSirenItem);
    register(
        "mod_block_items_fire_alarm_smoke_detector_item",
        MOD_BLOCK_ITEMS_TESTS::testFireAlarmSmokeDetectorItem);
    register(
        "mod_block_items_fire_alarm_smoke_detector_silent_item",
        MOD_BLOCK_ITEMS_TESTS::testFireAlarmSmokeDetectorSilentItem);
    register(
        "mod_block_items_fire_extinguisher_sign_item",
        MOD_BLOCK_ITEMS_TESTS::testFireExtinguisherSignItem);
    register(
        "mod_block_items_fire_extinguisher_sign_left_item",
        MOD_BLOCK_ITEMS_TESTS::testFireExtinguisherSignLeftItem);
    register(
        "mod_block_items_fire_extinguisher_sign_right_item",
        MOD_BLOCK_ITEMS_TESTS::testFireExtinguisherSignRightItem);
    register("mod_block_items_exit_sign_item", MOD_BLOCK_ITEMS_TESTS::testExitSignItem);
    register("mod_block_items_exit_sign_left_item", MOD_BLOCK_ITEMS_TESTS::testExitSignLeftItem);
    register(
        "mod_block_items_exit_sign_left_down_item",
        MOD_BLOCK_ITEMS_TESTS::testExitSignLeftDownItem);
    register(
        "mod_block_items_exit_sign_left_up_item", MOD_BLOCK_ITEMS_TESTS::testExitSignLeftUpItem);
    register("mod_block_items_exit_sign_right_item", MOD_BLOCK_ITEMS_TESTS::testExitSignRightItem);
    register(
        "mod_block_items_exit_sign_right_down_item",
        MOD_BLOCK_ITEMS_TESTS::testExitSignRightDownItem);
    register(
        "mod_block_items_exit_sign_right_up_item", MOD_BLOCK_ITEMS_TESTS::testExitSignRightUpItem);
    register(
        "mod_block_items_exit_sign_plain_left_item",
        MOD_BLOCK_ITEMS_TESTS::testExitSignPlainLeftItem);
    register(
        "mod_block_items_exit_sign_plain_right_item",
        MOD_BLOCK_ITEMS_TESTS::testExitSignPlainRightItem);
    register(
        "mod_block_items_fire_alarm_switch_eu_item",
        MOD_BLOCK_ITEMS_TESTS::testFireAlarmSwitchEuItem);
    register(
        "mod_block_items_fire_alarm_switch_jp_item",
        MOD_BLOCK_ITEMS_TESTS::testFireAlarmSwitchJpItem);
    register(
        "mod_block_items_fire_alarm_control_panel_item",
        MOD_BLOCK_ITEMS_TESTS::testFireAlarmControlPanelItem);
    register(
        "mod_block_items_fire_alarm_light_item", MOD_BLOCK_ITEMS_TESTS::testFireAlarmLightItem);
    register("mod_block_items_fire_pole_item", MOD_BLOCK_ITEMS_TESTS::testFirePoleItem);
    register("mod_block_items_fire_hydrant_item", MOD_BLOCK_ITEMS_TESTS::testFireHydrantItem);
    register("mod_blocks_fire_extinguisher_block", MOD_BLOCKS_TESTS::testFireExtinguisherBlock);
    register(
        "mod_blocks_fire_extinguisher_copper_block",
        MOD_BLOCKS_TESTS::testFireExtinguisherCopperBlock);
    register("mod_blocks_fire_sprinkler_block", MOD_BLOCKS_TESTS::testFireSprinklerBlock);
    register("mod_blocks_fire_alarm_switch_block", MOD_BLOCKS_TESTS::testFireAlarmSwitchBlock);
    register("mod_blocks_fire_alarm_bell_block", MOD_BLOCKS_TESTS::testFireAlarmBellBlock);
    register("mod_blocks_fire_alarm_siren_block", MOD_BLOCKS_TESTS::testFireAlarmSirenBlock);
    register(
        "mod_blocks_fire_alarm_smoke_detector_block",
        MOD_BLOCKS_TESTS::testFireAlarmSmokeDetectorBlock);
    register(
        "mod_blocks_fire_alarm_smoke_detector_silent_block",
        MOD_BLOCKS_TESTS::testFireAlarmSmokeDetectorSilentBlock);
    register(
        "mod_blocks_fire_extinguisher_sign_block", MOD_BLOCKS_TESTS::testFireExtinguisherSignBlock);
    register(
        "mod_blocks_fire_extinguisher_sign_left_block",
        MOD_BLOCKS_TESTS::testFireExtinguisherSignLeftBlock);
    register(
        "mod_blocks_fire_extinguisher_sign_right_block",
        MOD_BLOCKS_TESTS::testFireExtinguisherSignRightBlock);
    register("mod_blocks_exit_sign_block", MOD_BLOCKS_TESTS::testExitSignBlock);
    register("mod_blocks_exit_sign_left_block", MOD_BLOCKS_TESTS::testExitSignLeftBlock);
    register("mod_blocks_exit_sign_left_down_block", MOD_BLOCKS_TESTS::testExitSignLeftDownBlock);
    register("mod_blocks_exit_sign_left_up_block", MOD_BLOCKS_TESTS::testExitSignLeftUpBlock);
    register("mod_blocks_exit_sign_right_block", MOD_BLOCKS_TESTS::testExitSignRightBlock);
    register("mod_blocks_exit_sign_right_down_block", MOD_BLOCKS_TESTS::testExitSignRightDownBlock);
    register("mod_blocks_exit_sign_right_up_block", MOD_BLOCKS_TESTS::testExitSignRightUpBlock);
    register("mod_blocks_exit_sign_plain_left_block", MOD_BLOCKS_TESTS::testExitSignPlainLeftBlock);
    register(
        "mod_blocks_exit_sign_plain_right_block", MOD_BLOCKS_TESTS::testExitSignPlainRightBlock);
    register("mod_blocks_fire_alarm_switch_eu_block", MOD_BLOCKS_TESTS::testFireAlarmSwitchEuBlock);
    register("mod_blocks_fire_alarm_switch_jp_block", MOD_BLOCKS_TESTS::testFireAlarmSwitchJpBlock);
    register(
        "mod_blocks_fire_alarm_control_panel_block",
        MOD_BLOCKS_TESTS::testFireAlarmControlPanelBlock);
    register("mod_blocks_fire_alarm_light_block", MOD_BLOCKS_TESTS::testFireAlarmLightBlock);
    register("mod_blocks_fire_pole_block", MOD_BLOCKS_TESTS::testFirePoleBlock);
    register("mod_blocks_fire_hydrant_block", MOD_BLOCKS_TESTS::testFireHydrantBlock);
    register("mod_items_fire_axe_item", MOD_ITEMS_TESTS::testFireAxeItem);
    register("mod_items_fire_helmet_item", MOD_ITEMS_TESTS::testFireHelmetItem);
    register("mod_items_fire_chestplate_item", MOD_ITEMS_TESTS::testFireChestplateItem);
    register("mod_items_fire_leggings_item", MOD_ITEMS_TESTS::testFireLeggingsItem);
    register("mod_items_fire_boots_item", MOD_ITEMS_TESTS::testFireBootsItem);
    register("mod_items_fire_helmet_light_item", MOD_ITEMS_TESTS::testFireHelmetLightItem);
    register("mod_items_fire_chestplate_light_item", MOD_ITEMS_TESTS::testFireChestplateLightItem);
    register("mod_items_fire_leggings_light_item", MOD_ITEMS_TESTS::testFireLeggingsLightItem);
    register("mod_items_fire_boots_light_item", MOD_ITEMS_TESTS::testFireBootsLightItem);
    register("smoke_mod_registered", SMOKE_TESTS::testModRegistered);
  }

  private ModGameTests() {}

  public static void register(BusGroup modBusGroup) {
    if (FMLLoader.isProduction()) {
      return;
    }

    TEST_FUNCTIONS.register(modBusGroup);
  }

  private static void register(String name, Consumer<GameTestHelper> testFunction) {
    TEST_FUNCTIONS.register(name, () -> testFunction);
  }
}

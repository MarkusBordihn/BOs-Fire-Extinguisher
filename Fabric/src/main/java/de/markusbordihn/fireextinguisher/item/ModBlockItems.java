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

package de.markusbordihn.fireextinguisher.item;

import de.markusbordihn.fireextinguisher.Constants;
import de.markusbordihn.fireextinguisher.block.ExitSignBlocks;
import de.markusbordihn.fireextinguisher.block.FireAlarmBlocks;
import de.markusbordihn.fireextinguisher.block.FireExtinguisherBlocks;
import de.markusbordihn.fireextinguisher.block.FireExtinguisherSignBlocks;
import de.markusbordihn.fireextinguisher.blockitem.ExitSignBlockItem;
import de.markusbordihn.fireextinguisher.blockitem.FireAlarmBellBlockItem;
import de.markusbordihn.fireextinguisher.blockitem.FireAlarmSirenBlockItem;
import de.markusbordihn.fireextinguisher.blockitem.FireAlarmSmokeDetectorBlockItem;
import de.markusbordihn.fireextinguisher.blockitem.FireAlarmSwitchBlockItem;
import de.markusbordihn.fireextinguisher.blockitem.FireExtinguisherBlockItem;
import de.markusbordihn.fireextinguisher.blockitem.FireExtinguisherSignBlockItem;
import de.markusbordihn.fireextinguisher.blockitem.FireSprinklerBlockItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModBlockItems {

  private static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  public static Item FIRE_EXTINGUISHER;
  public static Item FIRE_EXTINGUISHER_COPPER;
  public static Item FIRE_SPRINKLER;
  public static Item FIRE_ALARM_SWITCH;
  public static Item FIRE_ALARM_BELL;
  public static Item FIRE_ALARM_SIREN;
  public static Item FIRE_ALARM_SMOKE_DETECTOR;
  public static Item FIRE_ALARM_SMOKE_DETECTOR_SILENT;
  public static Item FIRE_EXTINGUISHER_SIGN;
  public static Item FIRE_EXTINGUISHER_SIGN_LEFT;
  public static Item FIRE_EXTINGUISHER_SIGN_RIGHT;
  public static Item EXIT_SIGN;
  public static Item EXIT_SIGN_LEFT;
  public static Item EXIT_SIGN_LEFT_DOWN;
  public static Item EXIT_SIGN_LEFT_UP;
  public static Item EXIT_SIGN_RIGHT;
  public static Item EXIT_SIGN_RIGHT_DOWN;
  public static Item EXIT_SIGN_RIGHT_UP;

  protected ModBlockItems() {}

  public static void registerModBlockItems() {

    log.info("- {} Fire Extinguisher Block Items ...", Constants.LOG_REGISTER_PREFIX);
    FIRE_EXTINGUISHER =
        Registry.register(
            BuiltInRegistries.ITEM,
            Constants.MOD_ID_PREFIX + FireExtinguisherBlockItem.ID,
            new FireExtinguisherBlockItem(
                FireExtinguisherBlocks.FIRE_EXTINGUISHER, FireExtinguisherBlockItem.ID));
    FIRE_EXTINGUISHER_COPPER =
        Registry.register(
            BuiltInRegistries.ITEM,
            Constants.MOD_ID_PREFIX + FireExtinguisherBlockItem.ID_COPPER,
            new FireExtinguisherBlockItem(
                FireExtinguisherBlocks.FIRE_EXTINGUISHER_COPPER,
                FireExtinguisherBlockItem.ID_COPPER));

    log.info("- {} Fire Alarm Block Items ...", Constants.LOG_REGISTER_PREFIX);
    FIRE_SPRINKLER =
        Registry.register(
            BuiltInRegistries.ITEM,
            Constants.MOD_ID_PREFIX + FireSprinklerBlockItem.ID,
            new FireSprinklerBlockItem(FireAlarmBlocks.FIRE_SPRINKLER));
    FIRE_ALARM_SWITCH =
        Registry.register(
            BuiltInRegistries.ITEM,
            Constants.MOD_ID_PREFIX + FireAlarmSwitchBlockItem.ID,
            new FireAlarmSwitchBlockItem(FireAlarmBlocks.FIRE_ALARM_SWITCH));
    FIRE_ALARM_BELL =
        Registry.register(
            BuiltInRegistries.ITEM,
            Constants.MOD_ID_PREFIX + FireAlarmBellBlockItem.ID,
            new FireAlarmBellBlockItem(FireAlarmBlocks.FIRE_ALARM_BELL));
    FIRE_ALARM_SIREN =
        Registry.register(
            BuiltInRegistries.ITEM,
            Constants.MOD_ID_PREFIX + FireAlarmSirenBlockItem.ID,
            new FireAlarmSirenBlockItem(FireAlarmBlocks.FIRE_ALARM_SIREN));
    FIRE_ALARM_SMOKE_DETECTOR =
        Registry.register(
            BuiltInRegistries.ITEM,
            Constants.MOD_ID_PREFIX + FireAlarmSmokeDetectorBlockItem.ID,
            new FireAlarmSmokeDetectorBlockItem(
                FireAlarmBlocks.FIRE_ALARM_SMOKE_DETECTOR, FireAlarmSmokeDetectorBlockItem.ID));
    FIRE_ALARM_SMOKE_DETECTOR_SILENT =
        Registry.register(
            BuiltInRegistries.ITEM,
            Constants.MOD_ID_PREFIX + FireAlarmSmokeDetectorBlockItem.ID_SILENT,
            new FireAlarmSmokeDetectorBlockItem(
                FireAlarmBlocks.FIRE_ALARM_SMOKE_DETECTOR_SILENT,
                FireAlarmSmokeDetectorBlockItem.ID_SILENT));

    log.info("- {} Fire Extinguisher Sign Block Items ...", Constants.LOG_REGISTER_PREFIX);
    FIRE_EXTINGUISHER_SIGN =
        Registry.register(
            BuiltInRegistries.ITEM,
            Constants.MOD_ID_PREFIX + FireExtinguisherSignBlockItem.ID,
            new FireExtinguisherSignBlockItem(
                FireExtinguisherSignBlocks.FIRE_EXTINGUISHER_SIGN,
                FireExtinguisherSignBlockItem.ID));
    FIRE_EXTINGUISHER_SIGN_LEFT =
        Registry.register(
            BuiltInRegistries.ITEM,
            Constants.MOD_ID_PREFIX + FireExtinguisherSignBlockItem.ID_LEFT,
            new FireExtinguisherSignBlockItem(
                FireExtinguisherSignBlocks.FIRE_EXTINGUISHER_SIGN_LEFT,
                FireExtinguisherSignBlockItem.ID_LEFT));
    FIRE_EXTINGUISHER_SIGN_RIGHT =
        Registry.register(
            BuiltInRegistries.ITEM,
            Constants.MOD_ID_PREFIX + FireExtinguisherSignBlockItem.ID_RIGHT,
            new FireExtinguisherSignBlockItem(
                FireExtinguisherSignBlocks.FIRE_EXTINGUISHER_SIGN_RIGHT,
                FireExtinguisherSignBlockItem.ID_RIGHT));

    log.info("{} Exit Sign Block Items ...", Constants.LOG_SUB_REGISTER_PREFIX);
    EXIT_SIGN =
        Registry.register(
            BuiltInRegistries.ITEM,
            Constants.MOD_ID_PREFIX + ExitSignBlockItem.ID,
            new ExitSignBlockItem(ExitSignBlocks.EXIT_SIGN, ExitSignBlockItem.ID));
    EXIT_SIGN_LEFT =
        Registry.register(
            BuiltInRegistries.ITEM,
            Constants.MOD_ID_PREFIX + ExitSignBlockItem.ID_LEFT,
            new ExitSignBlockItem(ExitSignBlocks.EXIT_SIGN_LEFT, ExitSignBlockItem.ID_LEFT));
    EXIT_SIGN_LEFT_DOWN =
        Registry.register(
            BuiltInRegistries.ITEM,
            Constants.MOD_ID_PREFIX + ExitSignBlockItem.ID_LEFT_DOWN,
            new ExitSignBlockItem(
                ExitSignBlocks.EXIT_SIGN_LEFT_DOWN, ExitSignBlockItem.ID_LEFT_DOWN));
    EXIT_SIGN_LEFT_UP =
        Registry.register(
            BuiltInRegistries.ITEM,
            Constants.MOD_ID_PREFIX + ExitSignBlockItem.ID_LEFT_UP,
            new ExitSignBlockItem(ExitSignBlocks.EXIT_SIGN_LEFT_UP, ExitSignBlockItem.ID_LEFT_UP));
    EXIT_SIGN_RIGHT =
        Registry.register(
            BuiltInRegistries.ITEM,
            Constants.MOD_ID_PREFIX + ExitSignBlockItem.ID_RIGHT,
            new ExitSignBlockItem(ExitSignBlocks.EXIT_SIGN_RIGHT, ExitSignBlockItem.ID_RIGHT));
    EXIT_SIGN_RIGHT_DOWN =
        Registry.register(
            BuiltInRegistries.ITEM,
            Constants.MOD_ID_PREFIX + ExitSignBlockItem.ID_RIGHT_DOWN,
            new ExitSignBlockItem(
                ExitSignBlocks.EXIT_SIGN_RIGHT_DOWN, ExitSignBlockItem.ID_RIGHT_DOWN));
    EXIT_SIGN_RIGHT_UP =
        Registry.register(
            BuiltInRegistries.ITEM,
            Constants.MOD_ID_PREFIX + ExitSignBlockItem.ID_RIGHT_UP,
            new ExitSignBlockItem(
                ExitSignBlocks.EXIT_SIGN_RIGHT_UP, ExitSignBlockItem.ID_RIGHT_UP));
  }
}

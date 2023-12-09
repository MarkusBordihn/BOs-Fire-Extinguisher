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
import de.markusbordihn.fireextinguisher.block.FireExtinguisherSign;
import de.markusbordihn.fireextinguisher.block.ModBlocks;
import de.markusbordihn.fireextinguisher.blockitem.ExitSignBlockItem;
import de.markusbordihn.fireextinguisher.blockitem.FireAlarmBellBlockItem;
import de.markusbordihn.fireextinguisher.blockitem.FireAlarmSirenBlockItem;
import de.markusbordihn.fireextinguisher.blockitem.FireAlarmSmokeDetectorBlockItem;
import de.markusbordihn.fireextinguisher.blockitem.FireAlarmSwitchBlockItem;
import de.markusbordihn.fireextinguisher.blockitem.FireExtinguisherBlockItem;
import de.markusbordihn.fireextinguisher.blockitem.FireExtinguisherSignBlockItem;
import de.markusbordihn.fireextinguisher.blockitem.FireSprinklerBlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockItems {

  public static final DeferredRegister<Item> ITEMS =
      DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);

  // Fire Extinguisher
  public static final RegistryObject<Item> FIRE_EXTINGUISHER =
      ITEMS.register(
          FireExtinguisherBlockItem.NAME,
          () -> new FireExtinguisherBlockItem(ModBlocks.FIRE_EXTINGUISHER.get()));
  public static final RegistryObject<Item> FIRE_EXTINGUISHER_COPPER =
      ITEMS.register(
          FireExtinguisherBlockItem.NAME + "_copper",
          () -> new FireExtinguisherBlockItem(ModBlocks.FIRE_EXTINGUISHER_COPPER.get()));

  // Fire Alarm
  public static final RegistryObject<Item> FIRE_SPRINKLER =
      ITEMS.register(
          FireSprinklerBlockItem.NAME,
          () -> new FireSprinklerBlockItem(ModBlocks.FIRE_SPRINKLER.get()));
  public static final RegistryObject<Item> FIRE_ALARM_SWITCH =
      ITEMS.register(
          FireAlarmSwitchBlockItem.NAME,
          () -> new FireAlarmSwitchBlockItem(ModBlocks.FIRE_ALARM_SWITCH.get()));
  public static final RegistryObject<Item> FIRE_ALARM_BELL =
      ITEMS.register(
          FireAlarmBellBlockItem.NAME,
          () -> new FireAlarmBellBlockItem(ModBlocks.FIRE_ALARM_BELL.get()));
  public static final RegistryObject<Item> FIRE_ALARM_SIREN =
      ITEMS.register(
          FireAlarmSirenBlockItem.NAME,
          () -> new FireAlarmSirenBlockItem(ModBlocks.FIRE_ALARM_SIREN.get()));
  public static final RegistryObject<Item> FIRE_ALARM_SMOKE_DETECTOR =
      ITEMS.register(
          FireAlarmSmokeDetectorBlockItem.NAME,
          () -> new FireAlarmSmokeDetectorBlockItem(ModBlocks.FIRE_ALARM_SMOKE_DETECTOR.get()));
  public static final RegistryObject<Item> FIRE_ALARM_SMOKE_DETECTOR_SILENT =
      ITEMS.register(
          FireAlarmSmokeDetectorBlockItem.NAME + "_silent",
          () ->
              new FireAlarmSmokeDetectorBlockItem(
                  ModBlocks.FIRE_ALARM_SMOKE_DETECTOR_SILENT.get()));

  // Fire Extinguisher Signs
  public static final RegistryObject<Item> FIRE_EXTINGUISHER_SIGN =
      ITEMS.register(
          FireExtinguisherSign.NAME,
          () -> new FireExtinguisherSignBlockItem(ModBlocks.FIRE_EXTINGUISHER_SIGN.get()));
  public static final RegistryObject<Item> FIRE_EXTINGUISHER_SIGN_LEFT =
      ITEMS.register(
          FireExtinguisherSign.NAME + "_left",
          () -> new FireExtinguisherSignBlockItem(ModBlocks.FIRE_EXTINGUISHER_SIGN_LEFT.get()));
  public static final RegistryObject<Item> FIRE_EXTINGUISHER_SIGN_RIGHT =
      ITEMS.register(
          FireExtinguisherSign.NAME + "_right",
          () -> new FireExtinguisherSignBlockItem(ModBlocks.FIRE_EXTINGUISHER_SIGN_RIGHT.get()));

  // Exit Signs
  public static final RegistryObject<Item> EXIT_SIGN =
      ITEMS.register(
          ExitSignBlockItem.NAME, () -> new ExitSignBlockItem(ModBlocks.EXIT_SIGN.get()));
  public static final RegistryObject<Item> EXIT_SIGN_LEFT =
      ITEMS.register(
          ExitSignBlockItem.NAME + "_left",
          () -> new ExitSignBlockItem(ModBlocks.EXIT_SIGN_LEFT.get()));
  public static final RegistryObject<Item> EXIT_SIGN_LEFT_DOWN =
      ITEMS.register(
          ExitSignBlockItem.NAME + "_left_down",
          () -> new ExitSignBlockItem(ModBlocks.EXIT_SIGN_LEFT_DOWN.get()));
  public static final RegistryObject<Item> EXIT_SIGN_LEFT_UP =
      ITEMS.register(
          ExitSignBlockItem.NAME + "_left_up",
          () -> new ExitSignBlockItem(ModBlocks.EXIT_SIGN_LEFT_UP.get()));
  public static final RegistryObject<Item> EXIT_SIGN_RIGHT =
      ITEMS.register(
          ExitSignBlockItem.NAME + "_right",
          () -> new ExitSignBlockItem(ModBlocks.EXIT_SIGN_RIGHT.get()));
  public static final RegistryObject<Item> EXIT_SIGN_RIGHT_DOWN =
      ITEMS.register(
          ExitSignBlockItem.NAME + "_right_down",
          () -> new ExitSignBlockItem(ModBlocks.EXIT_SIGN_RIGHT_DOWN.get()));
  public static final RegistryObject<Item> EXIT_SIGN_RIGHT_UP =
      ITEMS.register(
          ExitSignBlockItem.NAME + "_right_up",
          () -> new ExitSignBlockItem(ModBlocks.EXIT_SIGN_RIGHT_UP.get()));

  protected ModBlockItems() {}
}

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
          FireExtinguisherBlockItem.ID,
          () ->
              new FireExtinguisherBlockItem(
                  ModBlocks.FIRE_EXTINGUISHER.get(), FireExtinguisherBlockItem.ID));
  public static final RegistryObject<Item> FIRE_EXTINGUISHER_COPPER =
      ITEMS.register(
          FireExtinguisherBlockItem.ID_COPPER,
          () ->
              new FireExtinguisherBlockItem(
                  ModBlocks.FIRE_EXTINGUISHER_COPPER.get(), FireExtinguisherBlockItem.ID_COPPER));

  // Fire Alarm
  public static final RegistryObject<Item> FIRE_SPRINKLER =
      ITEMS.register(
          FireSprinklerBlockItem.ID,
          () -> new FireSprinklerBlockItem(ModBlocks.FIRE_SPRINKLER.get()));
  public static final RegistryObject<Item> FIRE_ALARM_SWITCH =
      ITEMS.register(
          FireAlarmSwitchBlockItem.ID,
          () -> new FireAlarmSwitchBlockItem(ModBlocks.FIRE_ALARM_SWITCH.get()));
  public static final RegistryObject<Item> FIRE_ALARM_BELL =
      ITEMS.register(
          FireAlarmBellBlockItem.ID,
          () -> new FireAlarmBellBlockItem(ModBlocks.FIRE_ALARM_BELL.get()));
  public static final RegistryObject<Item> FIRE_ALARM_SIREN =
      ITEMS.register(
          FireAlarmSirenBlockItem.ID,
          () -> new FireAlarmSirenBlockItem(ModBlocks.FIRE_ALARM_SIREN.get()));
  public static final RegistryObject<Item> FIRE_ALARM_SMOKE_DETECTOR =
      ITEMS.register(
          FireAlarmSmokeDetectorBlockItem.ID,
          () ->
              new FireAlarmSmokeDetectorBlockItem(
                  ModBlocks.FIRE_ALARM_SMOKE_DETECTOR.get(), FireAlarmSmokeDetectorBlockItem.ID));
  public static final RegistryObject<Item> FIRE_ALARM_SMOKE_DETECTOR_SILENT =
      ITEMS.register(
          FireAlarmSmokeDetectorBlockItem.ID + "_silent",
          () ->
              new FireAlarmSmokeDetectorBlockItem(
                  ModBlocks.FIRE_ALARM_SMOKE_DETECTOR_SILENT.get(),
                  FireAlarmSmokeDetectorBlockItem.ID_SILENT));

  // Fire Extinguisher Signs
  public static final RegistryObject<Item> FIRE_EXTINGUISHER_SIGN =
      ITEMS.register(
          FireExtinguisherSignBlockItem.ID,
          () ->
              new FireExtinguisherSignBlockItem(
                  ModBlocks.FIRE_EXTINGUISHER_SIGN.get(), FireExtinguisherSignBlockItem.ID));
  public static final RegistryObject<Item> FIRE_EXTINGUISHER_SIGN_LEFT =
      ITEMS.register(
          FireExtinguisherSignBlockItem.ID_LEFT,
          () ->
              new FireExtinguisherSignBlockItem(
                  ModBlocks.FIRE_EXTINGUISHER_SIGN_LEFT.get(),
                  FireExtinguisherSignBlockItem.ID_LEFT));
  public static final RegistryObject<Item> FIRE_EXTINGUISHER_SIGN_RIGHT =
      ITEMS.register(
          FireExtinguisherSignBlockItem.ID_RIGHT,
          () ->
              new FireExtinguisherSignBlockItem(
                  ModBlocks.FIRE_EXTINGUISHER_SIGN_RIGHT.get(),
                  FireExtinguisherSignBlockItem.ID_RIGHT));

  // Exit Signs
  public static final RegistryObject<Item> EXIT_SIGN =
      ITEMS.register(
          ExitSignBlockItem.ID,
          () -> new ExitSignBlockItem(ModBlocks.EXIT_SIGN.get(), ExitSignBlockItem.ID));
  public static final RegistryObject<Item> EXIT_SIGN_LEFT =
      ITEMS.register(
          ExitSignBlockItem.ID_LEFT,
          () -> new ExitSignBlockItem(ModBlocks.EXIT_SIGN_LEFT.get(), ExitSignBlockItem.ID_LEFT));
  public static final RegistryObject<Item> EXIT_SIGN_LEFT_DOWN =
      ITEMS.register(
          ExitSignBlockItem.ID_LEFT_DOWN,
          () ->
              new ExitSignBlockItem(
                  ModBlocks.EXIT_SIGN_LEFT_DOWN.get(), ExitSignBlockItem.ID_LEFT_DOWN));
  public static final RegistryObject<Item> EXIT_SIGN_LEFT_UP =
      ITEMS.register(
          ExitSignBlockItem.ID_LEFT_UP,
          () ->
              new ExitSignBlockItem(
                  ModBlocks.EXIT_SIGN_LEFT_UP.get(), ExitSignBlockItem.ID_LEFT_UP));
  public static final RegistryObject<Item> EXIT_SIGN_RIGHT =
      ITEMS.register(
          ExitSignBlockItem.ID_RIGHT,
          () -> new ExitSignBlockItem(ModBlocks.EXIT_SIGN_RIGHT.get(), ExitSignBlockItem.ID_RIGHT));
  public static final RegistryObject<Item> EXIT_SIGN_RIGHT_DOWN =
      ITEMS.register(
          ExitSignBlockItem.ID_RIGHT_DOWN,
          () ->
              new ExitSignBlockItem(
                  ModBlocks.EXIT_SIGN_RIGHT_DOWN.get(), ExitSignBlockItem.ID_RIGHT_DOWN));
  public static final RegistryObject<Item> EXIT_SIGN_RIGHT_UP =
      ITEMS.register(
          ExitSignBlockItem.ID_RIGHT_UP,
          () ->
              new ExitSignBlockItem(
                  ModBlocks.EXIT_SIGN_RIGHT_UP.get(), ExitSignBlockItem.ID_RIGHT_UP));

  protected ModBlockItems() {}
}

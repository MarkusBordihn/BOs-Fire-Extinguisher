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
import de.markusbordihn.fireextinguisher.item.equipment.FireBootsItem;
import de.markusbordihn.fireextinguisher.item.equipment.FireBootsLightItem;
import de.markusbordihn.fireextinguisher.item.equipment.FireChestplateItem;
import de.markusbordihn.fireextinguisher.item.equipment.FireChestplateLightItem;
import de.markusbordihn.fireextinguisher.item.equipment.FireHelmetItem;
import de.markusbordihn.fireextinguisher.item.equipment.FireHelmetLightItem;
import de.markusbordihn.fireextinguisher.item.equipment.FireLeggingsItem;
import de.markusbordihn.fireextinguisher.item.equipment.FireLeggingsLightItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModItems {

  public static final Item FIRE_AXE = new FireAxeItem();
  public static final Item FIRE_BOOTS = new FireBootsItem();
  public static final Item FIRE_CHESTPLATE = new FireChestplateItem();
  public static final Item FIRE_HELMET = new FireHelmetItem();
  public static final Item FIRE_LEGGINGS = new FireLeggingsItem();

  public static final Item FIRE_BOOTS_LIGHT = new FireBootsLightItem();
  public static final Item FIRE_CHESTPLATE_LIGHT = new FireChestplateLightItem();
  public static final Item FIRE_HELMET_LIGHT = new FireHelmetLightItem();
  public static final Item FIRE_LEGGINGS_LIGHT = new FireLeggingsLightItem();
  private static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  protected ModItems() {}

  public static void registerModItems() {
    log.info("{} Fire fighting weapons items ...", Constants.LOG_SUB_REGISTER_PREFIX);

    Registry.register(BuiltInRegistries.ITEM, Constants.MOD_ID_PREFIX + FireAxeItem.ID, FIRE_AXE);

    log.info("{} Fire protection armor items ...", Constants.LOG_SUB_REGISTER_PREFIX);
    Registry.register(
        BuiltInRegistries.ITEM, Constants.MOD_ID_PREFIX + FireBootsItem.ID, FIRE_BOOTS);
    Registry.register(
        BuiltInRegistries.ITEM, Constants.MOD_ID_PREFIX + FireChestplateItem.ID, FIRE_CHESTPLATE);
    Registry.register(
        BuiltInRegistries.ITEM, Constants.MOD_ID_PREFIX + FireHelmetItem.ID, FIRE_HELMET);
    Registry.register(
        BuiltInRegistries.ITEM, Constants.MOD_ID_PREFIX + FireLeggingsItem.ID, FIRE_LEGGINGS);

    log.info("{} Fire protection light armor items ...", Constants.LOG_SUB_REGISTER_PREFIX);
    Registry.register(
        BuiltInRegistries.ITEM, Constants.MOD_ID_PREFIX + FireBootsLightItem.ID, FIRE_BOOTS_LIGHT);
    Registry.register(
        BuiltInRegistries.ITEM,
        Constants.MOD_ID_PREFIX + FireChestplateLightItem.ID,
        FIRE_CHESTPLATE_LIGHT);
    Registry.register(
        BuiltInRegistries.ITEM,
        Constants.MOD_ID_PREFIX + FireHelmetLightItem.ID,
        FIRE_HELMET_LIGHT);
    Registry.register(
        BuiltInRegistries.ITEM,
        Constants.MOD_ID_PREFIX + FireLeggingsLightItem.ID,
        FIRE_LEGGINGS_LIGHT);
  }
}

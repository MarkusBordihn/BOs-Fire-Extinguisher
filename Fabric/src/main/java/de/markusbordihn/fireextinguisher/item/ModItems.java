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
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

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

  protected ModItems() {}

  public static void registerModItems() {
    Constants.LOG.info("{} Fire fighting weapons items ...", Constants.LOG_SUB_REGISTER_PREFIX);

    Registry.register(BuiltInRegistries.ITEM, Constants.MOD_ID_PREFIX + FireAxeItem.NAME, FIRE_AXE);

    Constants.LOG.info("{} Fire protection armor items ...", Constants.LOG_SUB_REGISTER_PREFIX);
    Registry.register(
        BuiltInRegistries.ITEM, Constants.MOD_ID_PREFIX + FireBootsItem.NAME, FIRE_BOOTS);
    Registry.register(
        BuiltInRegistries.ITEM, Constants.MOD_ID_PREFIX + FireChestplateItem.NAME, FIRE_CHESTPLATE);
    Registry.register(
        BuiltInRegistries.ITEM, Constants.MOD_ID_PREFIX + FireHelmetItem.NAME, FIRE_HELMET);
    Registry.register(
        BuiltInRegistries.ITEM, Constants.MOD_ID_PREFIX + FireLeggingsItem.NAME, FIRE_LEGGINGS);

    Constants.LOG.info(
        "{} Fire protection light armor items ...", Constants.LOG_SUB_REGISTER_PREFIX);
    Registry.register(
        BuiltInRegistries.ITEM,
        Constants.MOD_ID_PREFIX + FireBootsLightItem.NAME,
        FIRE_BOOTS_LIGHT);
    Registry.register(
        BuiltInRegistries.ITEM,
        Constants.MOD_ID_PREFIX + FireChestplateLightItem.NAME,
        FIRE_CHESTPLATE_LIGHT);
    Registry.register(
        BuiltInRegistries.ITEM,
        Constants.MOD_ID_PREFIX + FireHelmetLightItem.NAME,
        FIRE_HELMET_LIGHT);
    Registry.register(
        BuiltInRegistries.ITEM,
        Constants.MOD_ID_PREFIX + FireLeggingsLightItem.NAME,
        FIRE_LEGGINGS_LIGHT);
  }
}

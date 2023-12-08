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
import de.markusbordihn.fireextinguisher.tabs.ModTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

  public static final DeferredRegister<Item> ITEMS =
      DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);
  // Fire fight weapons
  public static final RegistryObject<Item> FIRE_AXE =
      ITEMS.register(
          FireAxeItem.NAME,
          () -> new FireAxeItem(new Item.Properties().tab(ModTabs.TAB_FIRE_EXTINGUISHER)));
  // Fire fight armor
  public static final RegistryObject<Item> FIRE_HELMET =
      ITEMS.register(
          FireHelmetItem.NAME,
          () -> new FireHelmetItem(new Item.Properties().tab(ModTabs.TAB_FIRE_EXTINGUISHER)));
  public static final RegistryObject<Item> FIRE_CHESTPLATE =
      ITEMS.register(
          FireChestplateItem.NAME,
          () -> new FireChestplateItem(new Item.Properties().tab(ModTabs.TAB_FIRE_EXTINGUISHER)));
  public static final RegistryObject<Item> FIRE_LEGGINGS =
      ITEMS.register(
          FireLeggingsItem.NAME,
          () -> new FireLeggingsItem(new Item.Properties().tab(ModTabs.TAB_FIRE_EXTINGUISHER)));
  public static final RegistryObject<Item> FIRE_BOOTS =
      ITEMS.register(
          FireBootsItem.NAME,
          () -> new FireBootsItem(new Item.Properties().tab(ModTabs.TAB_FIRE_EXTINGUISHER)));
  // Fire fighter armor (light)
  public static final RegistryObject<Item> FIRE_HELMET_LIGHT =
      ITEMS.register(
          FireHelmetLightItem.NAME,
          () -> new FireHelmetLightItem(new Item.Properties().tab(ModTabs.TAB_FIRE_EXTINGUISHER)));
  public static final RegistryObject<Item> FIRE_CHESTPLATE_LIGHT =
      ITEMS.register(
          FireChestplateLightItem.NAME,
          () ->
              new FireChestplateLightItem(
                  new Item.Properties().tab(ModTabs.TAB_FIRE_EXTINGUISHER)));
  public static final RegistryObject<Item> FIRE_LEGGINGS_LIGHT =
      ITEMS.register(
          FireLeggingsLightItem.NAME,
          () ->
              new FireLeggingsLightItem(new Item.Properties().tab(ModTabs.TAB_FIRE_EXTINGUISHER)));
  public static final RegistryObject<Item> FIRE_BOOTS_LIGHT =
      ITEMS.register(
          FireBootsLightItem.NAME,
          () -> new FireBootsLightItem(new Item.Properties().tab(ModTabs.TAB_FIRE_EXTINGUISHER)));

  protected ModItems() {}
}

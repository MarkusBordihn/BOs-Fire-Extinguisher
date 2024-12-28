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
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

  public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Constants.MOD_ID);

  // Fire fight weapons
  public static final DeferredItem<Item> FIRE_AXE =
      ITEMS.register(FireAxeItem.ID, () -> new FireAxeItem());

  // Firefighter armor
  public static final DeferredItem<Item> FIRE_HELMET =
      ITEMS.register(FireHelmetItem.ID, () -> new FireHelmetItem());
  public static final DeferredItem<Item> FIRE_CHESTPLATE =
      ITEMS.register(FireChestplateItem.ID, () -> new FireChestplateItem());
  public static final DeferredItem<Item> FIRE_LEGGINGS =
      ITEMS.register(FireLeggingsItem.ID, () -> new FireLeggingsItem());
  public static final DeferredItem<Item> FIRE_BOOTS =
      ITEMS.register(FireBootsItem.ID, () -> new FireBootsItem());

  // Firefighter armor (light)
  public static final DeferredItem<Item> FIRE_HELMET_LIGHT =
      ITEMS.register(FireHelmetLightItem.ID, () -> new FireHelmetLightItem());
  public static final DeferredItem<Item> FIRE_CHESTPLATE_LIGHT =
      ITEMS.register(FireChestplateLightItem.ID, () -> new FireChestplateLightItem());
  public static final DeferredItem<Item> FIRE_LEGGINGS_LIGHT =
      ITEMS.register(FireLeggingsLightItem.ID, () -> new FireLeggingsLightItem());
  public static final DeferredItem<Item> FIRE_BOOTS_LIGHT =
      ITEMS.register(FireBootsLightItem.ID, () -> new FireBootsLightItem());

  protected ModItems() {}
}

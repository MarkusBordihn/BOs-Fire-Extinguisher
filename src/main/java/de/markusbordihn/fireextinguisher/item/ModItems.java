/**
 * Copyright 2022 Markus Bordihn
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

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import de.markusbordihn.fireextinguisher.Constants;
import de.markusbordihn.fireextinguisher.block.FireExtinguisherSign;
import de.markusbordihn.fireextinguisher.block.ModBlocks;
import de.markusbordihn.fireextinguisher.Annotations.TemplateEntryPoint;

public class ModItems {

  protected ModItems() {

  }

  public static final DeferredRegister<Item> ITEMS =
      DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);

  @TemplateEntryPoint("Register Items")

  @TemplateEntryPoint("Register Block Items")
  public static final RegistryObject<Item> FIRE_EXTINGUISHER_SIGN_LEFT =
      ITEMS.register("fire_extinguisher_sign_left", () -> new BlockItem(ModBlocks.FIRE_EXTINGUISHER_SIGN_LEFT.get(),
          new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
  public static final RegistryObject<Item> FIRE_EXTINGUISHER_SIGN_RIGHT =
      ITEMS.register("fire_extinguisher_sign_right",
          () -> new BlockItem(ModBlocks.FIRE_EXTINGUISHER_SIGN_RIGHT.get(),
              new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
  public static final RegistryObject<Item> FIRE_EXTINGUISHER_SIGN =
      ITEMS.register(FireExtinguisherSign.NAME, () -> new BlockItem(ModBlocks.FIRE_EXTINGUISHER_SIGN.get(),
          new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
  public static final RegistryObject<Item> FIRE_EXTINGUISHER =
      ITEMS.register(FireExtinguisherItem.NAME,
          () -> new FireExtinguisherItem(ModBlocks.FIRE_EXTINGUISHER.get(), new Item.Properties()
              .stacksTo(1).durability(128).tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
}

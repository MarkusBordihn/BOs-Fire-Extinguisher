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

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import de.markusbordihn.fireextinguisher.Constants;
import de.markusbordihn.fireextinguisher.block.ExitSign;
import de.markusbordihn.fireextinguisher.block.FireExtinguisherSign;
import de.markusbordihn.fireextinguisher.block.ModBlocks;

public class ModItems {

  protected ModItems() {

  }

  public static final DeferredRegister<Item> ITEMS =
      DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);

  // Fire fight weapons
  public static final RegistryObject<Item> FIRE_AXE = ITEMS.register(FireAxeItem.NAME,
      () -> new FireAxeItem(Tiers.IRON, 6.0F, -3.1F, new Item.Properties()));

  // Fire fight armor
  public static final RegistryObject<Item> FIRE_HELMET =
      ITEMS.register(FireHelmetItem.NAME, () -> new FireHelmetItem(ModArmorMaterials.IRON,
          ArmorItem.Type.HELMET, new Item.Properties()));
  public static final RegistryObject<Item> FIRE_CHESTPLATE =
      ITEMS.register(FireChestplateItem.NAME, () -> new FireChestplateItem(ModArmorMaterials.IRON,
          ArmorItem.Type.CHESTPLATE, new Item.Properties()));
  public static final RegistryObject<Item> FIRE_LEGGINGS =
      ITEMS.register(FireLeggingsItem.NAME, () -> new FireLeggingsItem(ModArmorMaterials.IRON,
          ArmorItem.Type.LEGGINGS, new Item.Properties()));
  public static final RegistryObject<Item> FIRE_BOOTS = ITEMS.register(FireBootsItem.NAME,
      () -> new FireBootsItem(ModArmorMaterials.IRON, ArmorItem.Type.BOOTS, new Item.Properties()));

  // Exit Signs
  public static final RegistryObject<Item> EXIT_SIGN = ITEMS.register(ExitSign.NAME,
      () -> new BlockItem(ModBlocks.EXIT_SIGN.get(), new Item.Properties()));
  public static final RegistryObject<Item> EXIT_SIGN_LEFT = ITEMS.register(ExitSign.NAME + "_left",
      () -> new BlockItem(ModBlocks.EXIT_SIGN_LEFT.get(), new Item.Properties()));
  public static final RegistryObject<Item> EXIT_SIGN_LEFT_DOWN =
      ITEMS.register(ExitSign.NAME + "_left_down",
          () -> new BlockItem(ModBlocks.EXIT_SIGN_LEFT_DOWN.get(), new Item.Properties()));
  public static final RegistryObject<Item> EXIT_SIGN_LEFT_UP =
      ITEMS.register(ExitSign.NAME + "_left_up",
          () -> new BlockItem(ModBlocks.EXIT_SIGN_LEFT_UP.get(), new Item.Properties()));
  public static final RegistryObject<Item> EXIT_SIGN_RIGHT =
      ITEMS.register(ExitSign.NAME + "_right",
          () -> new BlockItem(ModBlocks.EXIT_SIGN_RIGHT.get(), new Item.Properties()));
  public static final RegistryObject<Item> EXIT_SIGN_RIGHT_DOWN =
      ITEMS.register(ExitSign.NAME + "_right_down",
          () -> new BlockItem(ModBlocks.EXIT_SIGN_RIGHT_DOWN.get(), new Item.Properties()));
  public static final RegistryObject<Item> EXIT_SIGN_RIGHT_UP =
      ITEMS.register(ExitSign.NAME + "_right_up",
          () -> new BlockItem(ModBlocks.EXIT_SIGN_RIGHT_UP.get(), new Item.Properties()));

  // Fire Extinguisher Signs
  public static final RegistryObject<Item> FIRE_EXTINGUISHER_SIGN =
      ITEMS.register(FireExtinguisherSign.NAME,
          () -> new BlockItem(ModBlocks.FIRE_EXTINGUISHER_SIGN.get(), new Item.Properties()));
  public static final RegistryObject<Item> FIRE_EXTINGUISHER_SIGN_LEFT =
      ITEMS.register(FireExtinguisherSign.NAME + "_left",
          () -> new BlockItem(ModBlocks.FIRE_EXTINGUISHER_SIGN_LEFT.get(), new Item.Properties()));
  public static final RegistryObject<Item> FIRE_EXTINGUISHER_SIGN_RIGHT =
      ITEMS.register(FireExtinguisherSign.NAME + "_right",
          () -> new BlockItem(ModBlocks.FIRE_EXTINGUISHER_SIGN_RIGHT.get(), new Item.Properties()));

  // Fire Extinguisher
  public static final RegistryObject<Item> FIRE_EXTINGUISHER = ITEMS.register(
      FireExtinguisherItem.NAME, () -> new FireExtinguisherItem(ModBlocks.FIRE_EXTINGUISHER.get(),
          new Item.Properties().stacksTo(1).durability(128)));
}

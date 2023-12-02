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
import de.markusbordihn.fireextinguisher.block.ExitSign;
import de.markusbordihn.fireextinguisher.block.ExitSignBlocks;
import de.markusbordihn.fireextinguisher.block.FireExtinguisherSign;
import de.markusbordihn.fireextinguisher.block.FireExtinguisherSignBlocks;
import de.markusbordihn.fireextinguisher.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

public class ModBlockItems {

  public static final BlockItem FIRE_EXTINGUISHER =
      new FireExtinguisherBlockItem(ModBlocks.FIRE_EXTINGUISHER);

  protected ModBlockItems() {}

  public static void registerModBlockItems() {

    Constants.LOG.info("- {} Fire Extinguisher Block Items ...", Constants.LOG_REGISTER_PREFIX);
    Registry.register(
        Registry.ITEM, Constants.MOD_ID_PREFIX + FireExtinguisherBlockItem.NAME, FIRE_EXTINGUISHER);

    Constants.LOG.info(
        "- {} Fire Extinguisher Sign Block Items ...", Constants.LOG_REGISTER_PREFIX);
    Registry.register(
        Registry.ITEM,
        Constants.MOD_ID_PREFIX + FireExtinguisherSign.NAME,
        new BlockItem(FireExtinguisherSignBlocks.FIRE_EXTINGUISHER_SIGN, new Item.Properties()));
    Registry.register(
        Registry.ITEM,
        Constants.MOD_ID_PREFIX + FireExtinguisherSign.NAME + "_left",
        new BlockItem(
            FireExtinguisherSignBlocks.FIRE_EXTINGUISHER_SIGN_LEFT, new Item.Properties()));
    Registry.register(
        Registry.ITEM,
        Constants.MOD_ID_PREFIX + FireExtinguisherSign.NAME + "_right",
        new BlockItem(
            FireExtinguisherSignBlocks.FIRE_EXTINGUISHER_SIGN_RIGHT, new Item.Properties()));

    Constants.LOG.info("{} Exit Sign Block Items ...", Constants.LOG_SUB_REGISTER_PREFIX);
    Registry.register(
        Registry.ITEM,
        Constants.MOD_ID_PREFIX + ExitSign.NAME,
        new BlockItem(ExitSignBlocks.EXIT_SIGN, new Item.Properties()));
    Registry.register(
        Registry.ITEM,
        Constants.MOD_ID_PREFIX + ExitSign.NAME + "_left",
        new BlockItem(ExitSignBlocks.EXIT_SIGN_LEFT, new Item.Properties()));
    Registry.register(
        Registry.ITEM,
        Constants.MOD_ID_PREFIX + ExitSign.NAME + "_left_down",
        new BlockItem(ExitSignBlocks.EXIT_SIGN_LEFT_DOWN, new Item.Properties()));
    Registry.register(
        Registry.ITEM,
        Constants.MOD_ID_PREFIX + ExitSign.NAME + "_left_up",
        new BlockItem(ExitSignBlocks.EXIT_SIGN_LEFT_UP, new Item.Properties()));
    Registry.register(
        Registry.ITEM,
        Constants.MOD_ID_PREFIX + ExitSign.NAME + "_right",
        new BlockItem(ExitSignBlocks.EXIT_SIGN_RIGHT, new Item.Properties()));
    Registry.register(
        Registry.ITEM,
        Constants.MOD_ID_PREFIX + ExitSign.NAME + "_right_down",
        new BlockItem(ExitSignBlocks.EXIT_SIGN_RIGHT_DOWN, new Item.Properties()));
    Registry.register(
        Registry.ITEM,
        Constants.MOD_ID_PREFIX + ExitSign.NAME + "_right_up",
        new BlockItem(ExitSignBlocks.EXIT_SIGN_RIGHT_UP, new Item.Properties()));
  }
}

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

package de.markusbordihn.fireextinguisher.tabs;

import de.markusbordihn.fireextinguisher.block.ExitSignBlocks;
import de.markusbordihn.fireextinguisher.block.FireAlarmBlocks;
import de.markusbordihn.fireextinguisher.block.FireExtinguisherBlocks;
import de.markusbordihn.fireextinguisher.block.FireExtinguisherSignBlocks;
import de.markusbordihn.fireextinguisher.item.ModItems;
import net.minecraft.world.item.CreativeModeTab.DisplayItemsGenerator;
import net.minecraft.world.item.CreativeModeTab.ItemDisplayParameters;
import net.minecraft.world.item.CreativeModeTab.Output;

public class FireExtinguisherItems implements DisplayItemsGenerator {

  protected FireExtinguisherItems() {}

  @Override
  public void accept(ItemDisplayParameters itemDisplayParameters, Output output) {
    output.accept(FireExtinguisherBlocks.FIRE_EXTINGUISHER.asItem().getDefaultInstance());
    output.accept(FireExtinguisherBlocks.FIRE_EXTINGUISHER_COPPER.asItem().getDefaultInstance());

    output.accept(FireAlarmBlocks.FIRE_SPRINKLER.asItem().getDefaultInstance());
    output.accept(FireAlarmBlocks.FIRE_ALARM_SWITCH.asItem().getDefaultInstance());
    output.accept(FireAlarmBlocks.FIRE_ALARM_BELL.asItem().getDefaultInstance());
    output.accept(FireAlarmBlocks.FIRE_ALARM_SIREN.asItem().getDefaultInstance());
    output.accept(FireAlarmBlocks.FIRE_ALARM_SMOKE_DETECTOR.asItem().getDefaultInstance());
    output.accept(FireAlarmBlocks.FIRE_ALARM_SMOKE_DETECTOR_SILENT.asItem().getDefaultInstance());

    output.accept(ModItems.FIRE_AXE.getDefaultInstance());

    output.accept(ModItems.FIRE_BOOTS.getDefaultInstance());
    output.accept(ModItems.FIRE_CHESTPLATE.getDefaultInstance());
    output.accept(ModItems.FIRE_HELMET.getDefaultInstance());
    output.accept(ModItems.FIRE_LEGGINGS.getDefaultInstance());

    output.accept(ModItems.FIRE_BOOTS_LIGHT.getDefaultInstance());
    output.accept(ModItems.FIRE_CHESTPLATE_LIGHT.getDefaultInstance());
    output.accept(ModItems.FIRE_HELMET_LIGHT.getDefaultInstance());
    output.accept(ModItems.FIRE_LEGGINGS_LIGHT.getDefaultInstance());

    output.accept(FireExtinguisherSignBlocks.FIRE_EXTINGUISHER_SIGN.asItem().getDefaultInstance());
    output.accept(
        FireExtinguisherSignBlocks.FIRE_EXTINGUISHER_SIGN_LEFT.asItem().getDefaultInstance());
    output.accept(
        FireExtinguisherSignBlocks.FIRE_EXTINGUISHER_SIGN_RIGHT.asItem().getDefaultInstance());

    output.accept(ExitSignBlocks.EXIT_SIGN.asItem().getDefaultInstance());
    output.accept(ExitSignBlocks.EXIT_SIGN_LEFT.asItem().getDefaultInstance());
    output.accept(ExitSignBlocks.EXIT_SIGN_LEFT_DOWN.asItem().getDefaultInstance());
    output.accept(ExitSignBlocks.EXIT_SIGN_LEFT_UP.asItem().getDefaultInstance());
    output.accept(ExitSignBlocks.EXIT_SIGN_RIGHT.asItem().getDefaultInstance());
    output.accept(ExitSignBlocks.EXIT_SIGN_RIGHT_DOWN.asItem().getDefaultInstance());
    output.accept(ExitSignBlocks.EXIT_SIGN_RIGHT_UP.asItem().getDefaultInstance());
  }
}

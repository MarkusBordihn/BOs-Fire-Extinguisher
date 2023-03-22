/**
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

import net.minecraft.world.item.CreativeModeTab.DisplayItemsGenerator;
import net.minecraft.world.item.CreativeModeTab.ItemDisplayParameters;
import net.minecraft.world.item.CreativeModeTab.Output;

import de.markusbordihn.fireextinguisher.item.ModItems;

public class FireExtinguisherItems implements DisplayItemsGenerator {

  protected FireExtinguisherItems() {}

  @Override
  public void accept(ItemDisplayParameters itemDisplayParameters, Output output) {
    // Fire fight weapons
    output.accept(ModItems.FIRE_AXE.get());

    // Fire fight armor
    output.accept(ModItems.FIRE_HELMET.get());
    output.accept(ModItems.FIRE_CHESTPLATE.get());
    output.accept(ModItems.FIRE_LEGGINGS.get());
    output.accept(ModItems.FIRE_BOOTS.get());

    // Exit Signs
    output.accept(ModItems.EXIT_SIGN.get());
    output.accept(ModItems.EXIT_SIGN_LEFT.get());
    output.accept(ModItems.EXIT_SIGN_LEFT_DOWN.get());
    output.accept(ModItems.EXIT_SIGN_LEFT_UP.get());
    output.accept(ModItems.EXIT_SIGN_RIGHT.get());
    output.accept(ModItems.EXIT_SIGN_RIGHT_DOWN.get());
    output.accept(ModItems.EXIT_SIGN_RIGHT_UP.get());

    // Fire Extinguisher Signs
    output.accept(ModItems.FIRE_EXTINGUISHER_SIGN.get());
    output.accept(ModItems.FIRE_EXTINGUISHER_SIGN_LEFT.get());
    output.accept(ModItems.FIRE_EXTINGUISHER_SIGN_RIGHT.get());

    // Fire Extinguisher
    output.accept(ModItems.FIRE_EXTINGUISHER.get());
  }

}

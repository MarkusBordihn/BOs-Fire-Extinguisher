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

import de.markusbordihn.fireextinguisher.Constants;
import de.markusbordihn.fireextinguisher.item.ModBlockItems;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModTabs {
  public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
      DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Constants.MOD_ID);
  protected static Holder<CreativeModeTab> TAB_FIRE_EXTINGUISHER =
      CREATIVE_TABS.register(
          "tab",
          () ->
              CreativeModeTab.builder()
                  .icon(() -> ModBlockItems.FIRE_EXTINGUISHER.get().getDefaultInstance())
                  .displayItems(new FireExtinguisherItems())
                  .title(Component.translatable("itemGroup.fire_extinguisher.tab"))
                  .build());

  protected ModTabs() {}
}

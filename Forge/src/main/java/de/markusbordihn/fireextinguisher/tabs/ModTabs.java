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
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;

public class ModTabs {
  protected static CreativeModeTab TAB_FIRE_EXTINGUISHER;

  protected ModTabs() {}

  public static void handleCreativeModeTabRegister(CreativeModeTabEvent.Register event) {

    Constants.LOG.info("{} creative mod tabs ...", Constants.LOG_REGISTER_PREFIX);

    TAB_FIRE_EXTINGUISHER =
        event.registerCreativeModeTab(
            new ResourceLocation(Constants.MOD_ID, "tab"),
            builder ->
                builder
                    .icon(() -> new ItemStack(ModBlockItems.FIRE_EXTINGUISHER.get()))
                    .displayItems(new FireExtinguisherItems())
                    .title(Component.translatable("itemGroup.fire_extinguisher.tab"))
                    .build());
  }
}

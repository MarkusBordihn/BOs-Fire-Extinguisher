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

package de.markusbordihn.fireextinguisher.tabs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import net.minecraftforge.event.CreativeModeTabEvent;

import de.markusbordihn.fireextinguisher.Constants;
import de.markusbordihn.fireextinguisher.item.ModItems;

public class FireExtinguisherTab {

  protected static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  protected FireExtinguisherTab() {}

  public static CreativeModeTab FIRE_EXTINGUISHER_TAB;

  public static void handleCreativeModeTabRegister(CreativeModeTabEvent.Register event) {

    log.info("{} creative mod tabs ...", Constants.LOG_REGISTER_PREFIX);

    FIRE_EXTINGUISHER_TAB =
        event.registerCreativeModeTab(new ResourceLocation(Constants.MOD_ID), builder -> {
          builder.icon(() -> new ItemStack(ModItems.FIRE_EXTINGUISHER.get()))
              .displayItems((featureFlagSet, creativeModeTab, hasPermissions) -> {
                // Fire fight weapons
                creativeModeTab.accept(ModItems.FIRE_AXE.get());

                // Fire fight armor
                creativeModeTab.accept(ModItems.FIRE_HELMET.get());
                creativeModeTab.accept(ModItems.FIRE_CHESTPLATE.get());
                creativeModeTab.accept(ModItems.FIRE_LEGGINGS.get());
                creativeModeTab.accept(ModItems.FIRE_BOOTS.get());

                // Exit Signs
                creativeModeTab.accept(ModItems.EXIT_SIGN.get());
                creativeModeTab.accept(ModItems.EXIT_SIGN_LEFT.get());
                creativeModeTab.accept(ModItems.EXIT_SIGN_LEFT_DOWN.get());
                creativeModeTab.accept(ModItems.EXIT_SIGN_LEFT_UP.get());
                creativeModeTab.accept(ModItems.EXIT_SIGN_RIGHT.get());
                creativeModeTab.accept(ModItems.EXIT_SIGN_RIGHT_DOWN.get());
                creativeModeTab.accept(ModItems.EXIT_SIGN_RIGHT_UP.get());

                // Fire Extinguisher Signs
                creativeModeTab.accept(ModItems.FIRE_EXTINGUISHER_SIGN.get());
                creativeModeTab.accept(ModItems.FIRE_EXTINGUISHER_SIGN_LEFT.get());
                creativeModeTab.accept(ModItems.FIRE_EXTINGUISHER_SIGN_RIGHT.get());

                // Fire Extinguisher
                creativeModeTab.accept(ModItems.FIRE_EXTINGUISHER.get());
              }).title(Component.translatable("fire_extinguisher")).build();
        });
  }

}

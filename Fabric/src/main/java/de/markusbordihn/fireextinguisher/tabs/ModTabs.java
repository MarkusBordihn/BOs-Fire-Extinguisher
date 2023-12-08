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
import de.markusbordihn.fireextinguisher.block.ExitSignBlocks;
import de.markusbordihn.fireextinguisher.block.FireAlarmBlocks;
import de.markusbordihn.fireextinguisher.block.FireExtinguisherBlocks;
import de.markusbordihn.fireextinguisher.block.FireExtinguisherSignBlocks;
import de.markusbordihn.fireextinguisher.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

public class ModTabs {

  private static final CreativeModeTab TAB_FIRE_EXTINGUISHER =
      FabricItemGroup.builder(new ResourceLocation(Constants.MOD_ID, "tab"))
          .icon(() -> FireExtinguisherBlocks.FIRE_EXTINGUISHER.asItem().getDefaultInstance())
          .build();

  protected ModTabs() {}

  public static void registerModTabs() {
    Constants.LOG.info("{} Fire Extinguisher Tabs ...", Constants.LOG_SUB_REGISTER_PREFIX);
    ItemGroupEvents.modifyEntriesEvent(TAB_FIRE_EXTINGUISHER)
        .register(
            content -> {
              content.accept(
                  FireExtinguisherBlocks.FIRE_EXTINGUISHER.asItem().getDefaultInstance());
              content.accept(
                  FireExtinguisherBlocks.FIRE_EXTINGUISHER_COPPER.asItem().getDefaultInstance());

              content.accept(FireAlarmBlocks.FIRE_SPRINKLER.asItem().getDefaultInstance());
              content.accept(FireAlarmBlocks.FIRE_ALARM_SWITCH.asItem().getDefaultInstance());
              content.accept(FireAlarmBlocks.FIRE_ALARM_BELL.asItem().getDefaultInstance());
              content.accept(FireAlarmBlocks.FIRE_ALARM_SIREN.asItem().getDefaultInstance());
              content.accept(
                  FireAlarmBlocks.FIRE_ALARM_SMOKE_DETECTOR.asItem().getDefaultInstance());
              content.accept(
                  FireAlarmBlocks.FIRE_ALARM_SMOKE_DETECTOR_SILENT.asItem().getDefaultInstance());

              content.accept(ModItems.FIRE_AXE.getDefaultInstance());

              content.accept(ModItems.FIRE_BOOTS.getDefaultInstance());
              content.accept(ModItems.FIRE_CHESTPLATE.getDefaultInstance());
              content.accept(ModItems.FIRE_HELMET.getDefaultInstance());
              content.accept(ModItems.FIRE_LEGGINGS.getDefaultInstance());

              content.accept(ModItems.FIRE_BOOTS_LIGHT.getDefaultInstance());
              content.accept(ModItems.FIRE_CHESTPLATE_LIGHT.getDefaultInstance());
              content.accept(ModItems.FIRE_HELMET_LIGHT.getDefaultInstance());
              content.accept(ModItems.FIRE_LEGGINGS_LIGHT.getDefaultInstance());

              content.accept(
                  FireExtinguisherSignBlocks.FIRE_EXTINGUISHER_SIGN.asItem().getDefaultInstance());
              content.accept(
                  FireExtinguisherSignBlocks.FIRE_EXTINGUISHER_SIGN_LEFT
                      .asItem()
                      .getDefaultInstance());
              content.accept(
                  FireExtinguisherSignBlocks.FIRE_EXTINGUISHER_SIGN_RIGHT
                      .asItem()
                      .getDefaultInstance());

              content.accept(ExitSignBlocks.EXIT_SIGN.asItem().getDefaultInstance());
              content.accept(ExitSignBlocks.EXIT_SIGN_LEFT.asItem().getDefaultInstance());
              content.accept(ExitSignBlocks.EXIT_SIGN_LEFT_DOWN.asItem().getDefaultInstance());
              content.accept(ExitSignBlocks.EXIT_SIGN_LEFT_UP.asItem().getDefaultInstance());
              content.accept(ExitSignBlocks.EXIT_SIGN_RIGHT.asItem().getDefaultInstance());
              content.accept(ExitSignBlocks.EXIT_SIGN_RIGHT_DOWN.asItem().getDefaultInstance());
              content.accept(ExitSignBlocks.EXIT_SIGN_RIGHT_UP.asItem().getDefaultInstance());
            });
  }
}

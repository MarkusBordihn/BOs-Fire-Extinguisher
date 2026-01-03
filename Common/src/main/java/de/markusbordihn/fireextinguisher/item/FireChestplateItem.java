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
import de.markusbordihn.fireextinguisher.config.FireExtinguisherConfig;
import de.markusbordihn.fireextinguisher.utils.ToolTips;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class FireChestplateItem extends FireProtectionArmorItem {

  public static final String NAME = "fire_chestplate";

  public FireChestplateItem() {
    this(new Properties());
  }

  public FireChestplateItem(Properties properties) {
    super(EquipmentSlot.CHEST, properties);
  }

  @Override
  public Class<?> getArmorClass() {
    return this.getClass();
  }

  @Override
  public void appendHoverText(
      ItemStack itemStack, Level level, List<Component> tooltipList, TooltipFlag tooltipFlag) {
    ToolTips.addTooltip(
        tooltipList,
        Component.translatable(Constants.TEXT_PREFIX + NAME + "_description")
            .withStyle(ChatFormatting.GRAY));
    if (FireExtinguisherConfig.fireProtectionEnabled) {
      ToolTips.addTooltip(
          tooltipList,
          Component.translatable(
                  Constants.TEXT_PREFIX + "fire_armor_config",
                  Math.round((FireExtinguisherConfig.fireProtectionRenew / 20.0) * 10) / 10.0,
                  Math.round((FireExtinguisherConfig.fireProtectionDuration / 20.0) * 10) / 10.0)
              .withStyle(ChatFormatting.GREEN));
    }
    if (FireExtinguisherConfig.fireChestplateSlowDownEnabled) {
      ToolTips.addTooltip(
          tooltipList,
          Component.translatable(Constants.TEXT_PREFIX + "fire_armor_slow_down")
              .withStyle(ChatFormatting.DARK_RED));
    }
  }
}

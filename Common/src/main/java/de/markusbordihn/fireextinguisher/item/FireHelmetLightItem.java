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
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class FireHelmetLightItem extends FireProtectionArmorItem {

  public static final String NAME = "fire_helmet_light";

  private int ticker = 0;

  public FireHelmetLightItem() {
    this(new Properties());
  }

  public FireHelmetLightItem(Properties properties) {
    super(
        ModArmorMaterials.FIRE_PROTECTION_LIGHT.getArmorMaterialHolder(),
        ArmorItem.Type.HELMET,
        properties);
  }

  @Override
  protected void fireArmorTick(ItemStack itemStack, Level level, ServerPlayer serverPlayer) {
    if (Boolean.TRUE.equals(
            FireExtinguisherConfig.fireProtectionLightEnabled
                && ticker++ > FireExtinguisherConfig.fireProtectionLightRenew)
        && !serverPlayer.hasEffect(MobEffects.FIRE_RESISTANCE)) {
      serverPlayer.addEffect(
          new MobEffectInstance(
              MobEffects.FIRE_RESISTANCE, FireExtinguisherConfig.fireProtectionLightDuration));
      ticker = 0;
    }
  }

  @Override
  public Class<?> getArmorClass() {
    return this.getClass();
  }

  @Override
  public void appendHoverText(
      ItemStack itemStack,
      TooltipContext tooltipContext,
      List<Component> tooltipList,
      TooltipFlag tooltipFlag) {
    ToolTips.addTooltip(
        tooltipList,
        Component.translatable(Constants.TEXT_PREFIX + NAME + "_description")
            .withStyle(ChatFormatting.GRAY));
    if (Boolean.TRUE.equals(FireExtinguisherConfig.fireProtectionLightEnabled)) {
      ToolTips.addTooltip(
          tooltipList,
          Component.translatable(
                  Constants.TEXT_PREFIX + "fire_armor_config",
                  Math.round((FireExtinguisherConfig.fireProtectionLightRenew / 20.0) * 10) / 10.0,
                  Math.round((FireExtinguisherConfig.fireProtectionLightDuration / 20.0) * 10)
                      / 10.0)
              .withStyle(ChatFormatting.GREEN));
    }
  }
}

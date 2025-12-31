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

package de.markusbordihn.fireextinguisher.item.equipment;

import de.markusbordihn.fireextinguisher.Constants;
import de.markusbordihn.fireextinguisher.config.FireExtinguisherConfig;
import de.markusbordihn.fireextinguisher.utils.ToolTips;
import java.util.function.Consumer;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.Level;

public class FireBootsItem extends FireProtectionArmorItem {

  public static final String ID = "fire_boots";

  private int ticker = 0;

  public FireBootsItem() {
    this(
        new Properties()
            .setId(
                ResourceKey.create(
                    Registries.ITEM, Identifier.fromNamespaceAndPath(Constants.MOD_ID, ID))));
  }

  public FireBootsItem(Properties properties) {
    super(ArmorType.BOOTS, properties);
  }

  @Override
  protected void fireArmorTick(ItemStack itemStack, Level level, ServerPlayer serverPlayer) {
    if (Boolean.TRUE.equals(
            FireExtinguisherConfig.fireProtectionEnabled
                && ticker++ > FireExtinguisherConfig.fireProtectionRenew)
        && !serverPlayer.hasEffect(MobEffects.FIRE_RESISTANCE)) {
      serverPlayer.addEffect(
          new MobEffectInstance(
              MobEffects.FIRE_RESISTANCE, FireExtinguisherConfig.fireProtectionDuration));
      if (Boolean.TRUE.equals(FireExtinguisherConfig.fireBootsSlowDownEnabled)) {
        serverPlayer.addEffect(
            new MobEffectInstance(
                MobEffects.SLOWNESS, FireExtinguisherConfig.fireProtectionDuration));
      }
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
      TooltipDisplay tooltipDisplay,
      Consumer<Component> tooltipConsumer,
      TooltipFlag tooltipFlag) {
    ToolTips.addTooltip(
        tooltipConsumer,
        Component.translatable(Constants.TEXT_PREFIX + ID + "_description")
            .withStyle(ChatFormatting.GRAY));
    if (Boolean.TRUE.equals(FireExtinguisherConfig.fireProtectionEnabled)) {
      ToolTips.addTooltip(
          tooltipConsumer,
          Component.translatable(
                  Constants.TEXT_PREFIX + "fire_armor_config",
                  Math.round((FireExtinguisherConfig.fireProtectionRenew / 20.0) * 10) / 10.0,
                  Math.round((FireExtinguisherConfig.fireProtectionDuration / 20.0) * 10) / 10.0)
              .withStyle(ChatFormatting.GREEN));
    }
    if (Boolean.TRUE.equals(FireExtinguisherConfig.fireBootsSlowDownEnabled)) {
      ToolTips.addTooltip(
          tooltipConsumer,
          Component.translatable(Constants.TEXT_PREFIX + "fire_armor_slow_down")
              .withStyle(ChatFormatting.DARK_RED));
    }
  }
}

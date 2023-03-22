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

package de.markusbordihn.fireextinguisher.item;

import java.util.List;
import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import de.markusbordihn.fireextinguisher.Constants;
import de.markusbordihn.fireextinguisher.config.CommonConfig;

public class FireBootsItem extends ArmorItem {

  protected static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  public static final String NAME = "fire_boots";

  private static final CommonConfig.Config COMMON = CommonConfig.COMMON;

  private int ticker = 0;

  public FireBootsItem(ArmorMaterial material, ArmorItem.Type type, Properties properties) {
    super(material, type, properties);
  }

  @Override
  public boolean isFireResistant() {
    return true;
  }

  @Override
  public void onArmorTick(ItemStack stack, Level level, Player player) {
    // Add an delay where the player is not protected to make sure the item is not over powered.
    if (Boolean.TRUE.equals(COMMON.fireProtectionEnabled.get() && !level.isClientSide
        && ticker++ > COMMON.fireProtectionRenew.get())
        && !player.hasEffect(MobEffects.FIRE_RESISTANCE)) {
      player.addEffect(
          new MobEffectInstance(MobEffects.FIRE_RESISTANCE, COMMON.fireProtectionDuration.get()));
      if (Boolean.TRUE.equals(COMMON.fireBootsSlowDownEnabled.get())) {
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,
            COMMON.fireProtectionDuration.get()));
      }
      ticker = 0;
    }
    super.onArmorTick(stack, level, player);
  }

  @Override
  public void appendHoverText(ItemStack itemStack, @Nullable Level level,
      List<Component> tooltipList, TooltipFlag tooltipFlag) {
    tooltipList.add(Component.translatable(Constants.TEXT_PREFIX + NAME + "_description"));
    if (Boolean.TRUE.equals(COMMON.fireProtectionEnabled.get())) {
      tooltipList.add(Component
          .translatable(Constants.TEXT_PREFIX + "fire_armor_config",
              Math.round((COMMON.fireProtectionRenew.get() / 20.0) * 10) / 10.0,
              Math.round((COMMON.fireProtectionDuration.get() / 20.0) * 10) / 10.0)
          .withStyle(ChatFormatting.GREEN));
    }
    if (Boolean.TRUE.equals(COMMON.fireBootsSlowDownEnabled.get())) {
      tooltipList.add(Component.translatable(Constants.TEXT_PREFIX + "fire_armor_slow_down")
          .withStyle(ChatFormatting.DARK_RED));
    }
  }

}

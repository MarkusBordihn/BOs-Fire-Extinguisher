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
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class FireBootsItem extends FireProtectionArmorItem {

  public static final String NAME = "fire_boots";

  private int ticker = 0;

  public FireBootsItem() {
    this(new Properties());
  }

  public FireBootsItem(Properties properties) {
    super(EquipmentSlot.FEET, properties);
  }

  @Override
  protected void fireArmorTick(ItemStack itemStack, Level level, ServerPlayer serverPlayer) {
    if (Boolean.TRUE.equals(
            COMMON.fireProtectionEnabled.get() && ticker++ > COMMON.fireProtectionRenew.get())
        && !serverPlayer.hasEffect(MobEffects.FIRE_RESISTANCE)) {
      serverPlayer.addEffect(
          new MobEffectInstance(MobEffects.FIRE_RESISTANCE, COMMON.fireProtectionDuration.get()));
      if (Boolean.TRUE.equals(COMMON.fireBootsSlowDownEnabled.get())) {
        serverPlayer.addEffect(
            new MobEffectInstance(
                MobEffects.MOVEMENT_SLOWDOWN, COMMON.fireProtectionDuration.get()));
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
      @Nullable Level level,
      List<Component> tooltipList,
      TooltipFlag tooltipFlag) {
    tooltipList.add(new TranslatableComponent(Constants.TEXT_PREFIX + NAME + "_description"));
    if (Boolean.TRUE.equals(COMMON.fireProtectionEnabled.get())) {
      tooltipList.add(
          new TranslatableComponent(
                  Constants.TEXT_PREFIX + "fire_armor_config",
                  Math.round((COMMON.fireProtectionRenew.get() / 20.0) * 10) / 10.0,
                  Math.round((COMMON.fireProtectionDuration.get() / 20.0) * 10) / 10.0)
              .withStyle(ChatFormatting.GREEN));
    }
    if (Boolean.TRUE.equals(COMMON.fireBootsSlowDownEnabled.get())) {
      tooltipList.add(
          new TranslatableComponent(Constants.TEXT_PREFIX + "fire_armor_slow_down")
              .withStyle(ChatFormatting.DARK_RED));
    }
  }
}

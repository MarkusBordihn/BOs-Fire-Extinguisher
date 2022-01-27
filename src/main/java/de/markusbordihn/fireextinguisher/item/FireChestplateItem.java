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
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import de.markusbordihn.fireextinguisher.Constants;
import de.markusbordihn.fireextinguisher.config.CommonConfig;

@EventBusSubscriber
public class FireChestplateItem extends ArmorItem {

  protected static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  public static final String NAME = "fire_chestplate";

  private static final CommonConfig.Config COMMON = CommonConfig.COMMON;

  private static boolean fireChestplateSlowDownEnabled = COMMON.fireChestplateSlowDownEnabled.get();
  private static boolean fireProtectionEnabled = COMMON.fireProtectionEnabled.get();
  private static int fireProtectionRenew = COMMON.fireProtectionRenew.get();
  private static int fireProtectionDuration = COMMON.fireProtectionDuration.get();

  private int ticker = 0;

  public FireChestplateItem(ArmorMaterial material, EquipmentSlot slot, Properties properties) {
    super(material, slot, properties);
  }

  @SubscribeEvent
  public static void handleServerAboutToStartEvent(ServerAboutToStartEvent event) {
    fireChestplateSlowDownEnabled = COMMON.fireChestplateSlowDownEnabled.get();
    fireProtectionEnabled = COMMON.fireProtectionEnabled.get();
    fireProtectionRenew = COMMON.fireProtectionRenew.get();
    fireProtectionDuration = COMMON.fireProtectionDuration.get();
  }

  @Override
  public boolean isFireResistant() {
    return true;
  }

  @Override
  public void onArmorTick(ItemStack stack, Level level, Player player) {
    // Add an delay where the player is not protected to make sure the item is not over powered.
    if (fireProtectionEnabled && !level.isClientSide && ticker++ > fireProtectionRenew
        && !player.hasEffect(MobEffects.FIRE_RESISTANCE)) {
      player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, fireProtectionDuration));
      if (fireChestplateSlowDownEnabled) {
        player
            .addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, fireProtectionDuration));
      }
      ticker = 0;
    }
    super.onArmorTick(stack, level, player);
  }

  @Override
  public void appendHoverText(ItemStack itemStack, @Nullable Level level,
      List<Component> tooltipList, TooltipFlag tooltipFlag) {
    tooltipList.add(new TranslatableComponent(Constants.TEXT_PREFIX + NAME + "_description"));
    if (fireProtectionEnabled) {
      tooltipList.add(new TranslatableComponent(Constants.TEXT_PREFIX + "fire_armor_config",
          Math.round((fireProtectionRenew / 20.0) * 10) / 10.0,
          Math.round((fireProtectionDuration / 20.0) * 10) / 10.0).withStyle(ChatFormatting.GREEN));
    }
    if (fireChestplateSlowDownEnabled) {
      tooltipList.add(new TranslatableComponent(Constants.TEXT_PREFIX + "fire_armor_slow_down")
          .withStyle(ChatFormatting.DARK_RED));
    }
  }

}

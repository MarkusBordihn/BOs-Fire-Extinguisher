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

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;

import de.markusbordihn.fireextinguisher.Constants;
import de.markusbordihn.fireextinguisher.config.CommonConfig;

@EventBusSubscriber
public class FireHelmetItem extends ArmorItem {

  protected static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  public static final String NAME = "fire_helmet";

  private static final CommonConfig.Config COMMON = CommonConfig.COMMON;

  private static boolean fireProtectionEnabled = COMMON.fireProtectionEnabled.get();
  private static int fireProtectionRenew = COMMON.fireProtectionRenew.get();
  private static int fireProtectionDuration = COMMON.fireProtectionDuration.get();

  private int ticker = 0;

  public FireHelmetItem(IArmorMaterial material, EquipmentSlotType slot, Item.Properties properties) {
    super(material, slot, properties);
  }

  @SubscribeEvent
  public static void handleServerAboutToStartEvent(FMLServerAboutToStartEvent event) {
    fireProtectionEnabled = COMMON.fireProtectionEnabled.get();
    fireProtectionRenew = COMMON.fireProtectionRenew.get();
    fireProtectionDuration = COMMON.fireProtectionDuration.get();
  }

  @Override
  public boolean isFireResistant() {
    return true;
  }

  @Override
  public void onArmorTick(ItemStack stack, World level, PlayerEntity player) {
    // Add an delay where the player is not protected to make sure the item is not over powered.
    if (fireProtectionEnabled && !level.isClientSide && ticker++ > fireProtectionRenew
        && !player.hasEffect(Effects.FIRE_RESISTANCE)) {
      player.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, fireProtectionDuration));
      ticker = 0;
    }
    super.onArmorTick(stack, level, player);
  }

  @OnlyIn(Dist.CLIENT)
  @Override
  public void appendHoverText(ItemStack itemStack, @Nullable World level,
      List<ITextComponent> tooltipList, ITooltipFlag tooltipFlag) {
    tooltipList.add(new TranslationTextComponent(Constants.TEXT_PREFIX + NAME + "_description"));
    if (fireProtectionEnabled) {
      tooltipList.add(new TranslationTextComponent(Constants.TEXT_PREFIX + "fire_armor_config",
          Math.round((fireProtectionRenew / 20.0) * 10) / 10.0,
          Math.round((fireProtectionDuration / 20.0) * 10) / 10.0).withStyle(TextFormatting.GREEN));
    }
  }

}

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

import de.markusbordihn.fireextinguisher.config.FireExtinguisherConfig;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FireProtectionArmorItem extends ArmorItem {

  protected static final Map<UUID, Long> lastEffectTime = new HashMap<>();

  public FireProtectionArmorItem(EquipmentSlot slot, Properties properties) {
    this(ModArmorMaterials.FIRE_PROTECTION, slot, properties);
  }

  public FireProtectionArmorItem(
      ArmorMaterial armorMaterial, EquipmentSlot slot, Properties properties) {
    super(armorMaterial, slot, properties);
  }

  protected static int countWornFireArmorPieces(ServerPlayer player, ArmorMaterial material) {
    int count = 0;
    for (EquipmentSlot slot : EquipmentSlot.values()) {
      if (slot.getType() == EquipmentSlot.Type.ARMOR) {
        ItemStack armorPiece = player.getItemBySlot(slot);
        if (armorPiece.getItem() instanceof FireProtectionArmorItem fireArmor
            && fireArmor.getMaterial() == material) {
          count++;
        }
      }
    }
    return count;
  }

  protected static boolean hasSlowDownArmor(ServerPlayer player) {
    ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
    if (chestplate.getItem() instanceof FireChestplateItem
        && FireExtinguisherConfig.fireChestplateSlowDownEnabled) {
      return true;
    }
    ItemStack leggings = player.getItemBySlot(EquipmentSlot.LEGS);
    if (leggings.getItem() instanceof FireLeggingsItem
        && FireExtinguisherConfig.fireLeggingsSlowDownEnabled) {
      return true;
    }
    ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
    return boots.getItem() instanceof FireBootsItem
        && FireExtinguisherConfig.fireBootsSlowDownEnabled;
  }

  @Override
  public void inventoryTick(
      ItemStack itemStack, Level level, Entity entity, int slot, boolean selected) {
    if (!level.isClientSide
        && entity instanceof ServerPlayer serverPlayer
        && serverPlayer.getItemBySlot(EquipmentSlot.HEAD) == itemStack
        && itemStack.getItem() instanceof FireProtectionArmorItem) {
      fireArmorTick(itemStack, level, serverPlayer);
    }
    super.inventoryTick(itemStack, level, entity, slot, selected);
  }

  protected void fireArmorTick(ItemStack itemStack, Level level, ServerPlayer serverPlayer) {}

  public Class<?> getArmorClass() {
    return FireProtectionArmorItem.class;
  }

  @Override
  public boolean isFireResistant() {
    return true;
  }
}

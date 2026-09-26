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

import de.markusbordihn.fireextinguisher.config.FireExtinguisherConfig;
import de.markusbordihn.fireextinguisher.item.ModArmorMaterials;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.Level;

public class FireProtectionArmorItem extends Item {

  private final ArmorMaterial armorMaterial;

  public FireProtectionArmorItem(ArmorType type, Properties properties) {
    this(ModArmorMaterials.FIRE_PROTECTION.getArmorMaterial(), type, properties);
  }

  public FireProtectionArmorItem(
      ArmorMaterial armorMaterial, ArmorType armorType, Properties properties) {
    super(properties.humanoidArmor(armorMaterial, armorType).fireResistant());
    this.armorMaterial = armorMaterial;
  }

  public static int countWornFireArmorPieces(Player player, ArmorMaterial armorMaterial) {
    int count = 0;
    for (EquipmentSlot equipmentSlot : EquipmentSlot.values()) {
      if (equipmentSlot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR
          && player.getItemBySlot(equipmentSlot).getItem()
              instanceof FireProtectionArmorItem fireArmor
          && fireArmor.getArmorMaterial().equals(armorMaterial)) {
        count++;
      }
    }
    return count;
  }

  public static int calculateProtectionDuration(
      Player player, ArmorMaterial armorMaterial, int durationPerArmorPiece) {
    return durationPerArmorPiece * countWornFireArmorPieces(player, armorMaterial);
  }

  protected static boolean hasSlowDownArmor(ServerPlayer serverPlayer) {
    if (serverPlayer.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof FireChestplateItem
        && FireExtinguisherConfig.fireChestplateSlowDownEnabled) {
      return true;
    }

    if (serverPlayer.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof FireLeggingsItem
        && FireExtinguisherConfig.fireLeggingsSlowDownEnabled) {
      return true;
    }

    return serverPlayer.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof FireBootsItem
        && FireExtinguisherConfig.fireBootsSlowDownEnabled;
  }

  public ArmorMaterial getArmorMaterial() {
    return this.armorMaterial;
  }

  @Override
  public void inventoryTick(
      ItemStack itemStack, ServerLevel serverLevel, Entity entity, EquipmentSlot equipmentSlot) {
    if (entity instanceof ServerPlayer serverPlayer
        && (equipmentSlot == EquipmentSlot.BODY
            || equipmentSlot == EquipmentSlot.CHEST
            || equipmentSlot == EquipmentSlot.HEAD
            || equipmentSlot == EquipmentSlot.LEGS
            || equipmentSlot == EquipmentSlot.FEET
                && itemStack.getItem().getClass().equals(getArmorClass()))) {
      fireArmorTick(itemStack, serverLevel, serverPlayer);
    }
    super.inventoryTick(itemStack, serverLevel, entity, equipmentSlot);
  }

  protected void fireArmorTick(ItemStack itemStack, Level level, ServerPlayer serverPlayer) {
    // Implement in sub classes.
  }

  public Class<?> getArmorClass() {
    return FireProtectionArmorItem.class;
  }
}

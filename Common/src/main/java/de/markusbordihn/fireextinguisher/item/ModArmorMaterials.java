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
import java.util.Map;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;

public enum ModArmorMaterials {
  FIRE_PROTECTION(
      "fire_protection_armor",
      15,
      makeDefense(2, 5, 6, 2, 5),
      9,
      SoundEvents.ARMOR_EQUIP_IRON,
      0.0F,
      0.0F,
      ItemTags.REPAIRS_IRON_ARMOR,
      ResourceKey.create(
          ResourceKey.createRegistryKey(Identifier.withDefaultNamespace("equipment_asset")),
          Identifier.fromNamespaceAndPath(Constants.MOD_ID, "fire_protection_armor"))),
  FIRE_PROTECTION_LIGHT(
      "fire_protection_light_armor",
      15,
      makeDefense(2, 5, 6, 2, 5),
      9,
      SoundEvents.ARMOR_EQUIP_IRON,
      0.0F,
      0.0F,
      ItemTags.REPAIRS_IRON_ARMOR,
      ResourceKey.create(
          ResourceKey.createRegistryKey(Identifier.withDefaultNamespace("equipment_asset")),
          Identifier.fromNamespaceAndPath(Constants.MOD_ID, "fire_protection_light_armor")));

  private final String name;
  private final ArmorMaterial armorMaterial;

  ModArmorMaterials(
      String name,
      int durability,
      Map<ArmorType, Integer> defense,
      int enchantmentValue,
      Holder<SoundEvent> equipSound,
      float toughness,
      float knockbackResistance,
      TagKey<Item> repairIngredient,
      ResourceKey<EquipmentAsset> assetId) {
    this.name = name;
    this.armorMaterial =
        new ArmorMaterial(
            durability,
            defense,
            enchantmentValue,
            equipSound,
            toughness,
            knockbackResistance,
            repairIngredient,
            assetId);
  }

  private static Map<ArmorType, Integer> makeDefense(
      int boots, int leggings, int chestplate, int helmet, int body) {
    return Map.of(
        ArmorType.BOOTS,
        boots,
        ArmorType.LEGGINGS,
        leggings,
        ArmorType.CHESTPLATE,
        chestplate,
        ArmorType.HELMET,
        helmet,
        ArmorType.BODY,
        body);
  }

  public String getName() {
    return this.name;
  }

  public ArmorMaterial getArmorMaterial() {
    return this.armorMaterial;
  }
}

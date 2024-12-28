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
import java.util.EnumMap;
import java.util.Map;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

public enum ModArmorMaterials {
  FIRE_PROTECTION(
      "fire_protection_armor",
      Util.make(
          new EnumMap(ArmorType.class),
          map -> {
            map.put(ArmorType.BOOTS, 2);
            map.put(ArmorType.LEGGINGS, 5);
            map.put(ArmorType.CHESTPLATE, 6);
            map.put(ArmorType.HELMET, 2);
            map.put(ArmorType.BODY, 5);
          }),
      9,
      SoundEvents.ARMOR_EQUIP_IRON,
      ItemTags.REPAIRS_IRON_ARMOR,
      ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "fire_protection_armor"),
      0.0F,
      0.0F),
  FIRE_PROTECTION_LIGHT(
      "fire_protection_light_armor",
      Util.make(
          new EnumMap(ArmorType.class),
          map -> {
            map.put(ArmorType.BOOTS, 2);
            map.put(ArmorType.LEGGINGS, 5);
            map.put(ArmorType.CHESTPLATE, 6);
            map.put(ArmorType.HELMET, 2);
            map.put(ArmorType.BODY, 5);
          }),
      9,
      SoundEvents.ARMOR_EQUIP_IRON,
      ItemTags.REPAIRS_IRON_ARMOR,
      ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "fire_protection_light_armor"),
      0.0F,
      0.0F);

  private final String name;
  private final ResourceLocation resourceLocation;
  private final ArmorMaterial armorMaterial;
  private Holder<ArmorMaterial> holder;

  ModArmorMaterials(
      String name,
      Map<ArmorType, Integer> defense,
      int enchantmentValue,
      Holder<SoundEvent> equipSound,
      TagKey<Item> repairIngredient,
      ResourceLocation resourceLocation,
      float toughness,
      float knockbackResistance) {
    this.name = name;
    this.resourceLocation = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name);
    this.armorMaterial =
        new ArmorMaterial(
            15,
            defense,
            enchantmentValue,
            equipSound,
            toughness,
            knockbackResistance,
            repairIngredient,
            resourceLocation);
  }

  public String getName() {
    return this.name;
  }

  public ArmorMaterial getArmorMaterial() {
    return this.armorMaterial;
  }
}

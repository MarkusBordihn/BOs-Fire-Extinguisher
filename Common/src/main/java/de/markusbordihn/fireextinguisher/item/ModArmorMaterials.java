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

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterial.Layer;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public enum ModArmorMaterials {
  FIRE_PROTECTION(
      "fire_protection_armor",
      Util.make(
          new EnumMap(ArmorItem.Type.class),
          map -> {
            map.put(Type.BOOTS, 2);
            map.put(Type.LEGGINGS, 5);
            map.put(Type.CHESTPLATE, 6);
            map.put(Type.HELMET, 2);
            map.put(Type.BODY, 5);
          }),
      9,
      SoundEvents.ARMOR_EQUIP_IRON,
      () -> Ingredient.of(Items.IRON_INGOT),
      List.of(new ArmorMaterial.Layer(new ResourceLocation("fire_protection_armor"))),
      0.0F,
      0.0F),
  FIRE_PROTECTION_LIGHT(
      "fire_protection_light_armor",
      Util.make(
          new EnumMap(ArmorItem.Type.class),
          map -> {
            map.put(Type.BOOTS, 2);
            map.put(Type.LEGGINGS, 5);
            map.put(Type.CHESTPLATE, 6);
            map.put(Type.HELMET, 2);
            map.put(Type.BODY, 5);
          }),
      9,
      SoundEvents.ARMOR_EQUIP_IRON,
      () -> Ingredient.of(Items.IRON_INGOT),
      List.of(new ArmorMaterial.Layer(new ResourceLocation("fire_protection_armor"))),
      0.0F,
      0.0F);

  static {
    FIRE_PROTECTION.holder =
        Registry.registerForHolder(
            BuiltInRegistries.ARMOR_MATERIAL,
            FIRE_PROTECTION.getResourceLocation(),
            FIRE_PROTECTION.getArmorMaterial());
    FIRE_PROTECTION_LIGHT.holder =
        Registry.registerForHolder(
            BuiltInRegistries.ARMOR_MATERIAL,
            FIRE_PROTECTION_LIGHT.getResourceLocation(),
            FIRE_PROTECTION_LIGHT.getArmorMaterial());
  }

  private final String name;
  private final ResourceLocation resourceLocation;
  private final ArmorMaterial armorMaterial;
  private Holder<ArmorMaterial> holder;

  ModArmorMaterials(
      String name,
      Map<Type, Integer> defense,
      int enchantmentValue,
      Holder<SoundEvent> equipSound,
      Supplier<Ingredient> repairIngredient,
      List<Layer> layers,
      float toughness,
      float knockbackResistance) {
    this.name = name;
    this.resourceLocation = new ResourceLocation(name);
    this.armorMaterial =
        new ArmorMaterial(
            defense,
            enchantmentValue,
            equipSound,
            repairIngredient,
            layers,
            toughness,
            knockbackResistance);
  }

  public String getName() {
    return this.name;
  }

  public ArmorMaterial getArmorMaterial() {
    return this.armorMaterial;
  }

  public Holder<ArmorMaterial> getArmorMaterialHolder() {
    return this.holder;
  }

  public ResourceLocation getResourceLocation() {
    return this.resourceLocation;
  }
}

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

import java.util.function.Supplier;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import de.markusbordihn.fireextinguisher.Constants;

public enum ModArmorMaterials implements ArmorMaterial {
  IRON(new ResourceLocation(Constants.MOD_ID, "iron").toString(), 15, new int[] {2, 5, 6, 2}, 9,
      SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
        return Ingredient.of(Items.IRON_INGOT);
      });

  private static final int[] HEALTH_PER_SLOT = new int[] {13, 15, 16, 11};
  private final String name;
  private final int durabilityMultiplier;
  private final int[] slotProtections;
  private final int enchantmentValue;
  private final SoundEvent sound;
  private final float toughness;
  private final float knockbackResistance;
  private final LazyLoadedValue<Ingredient> repairIngredient;

  private ModArmorMaterials(String name, int p_40475_, int[] p_40476_, int p_40477_, SoundEvent soundEvent, float p_40479_, float p_40480_, Supplier<Ingredient> p_40481_) {
      this.name = name;
      this.durabilityMultiplier = p_40475_;
      this.slotProtections = p_40476_;
      this.enchantmentValue = p_40477_;
      this.sound = soundEvent;
      this.toughness = p_40479_;
      this.knockbackResistance = p_40480_;
      this.repairIngredient = new LazyLoadedValue<>(p_40481_);
   }

  public int getDurabilityForSlot(EquipmentSlot equipmentSlot) {
    return HEALTH_PER_SLOT[equipmentSlot.getIndex()] * this.durabilityMultiplier;
  }

  public int getDefenseForSlot(EquipmentSlot equipmentSlot) {
    return this.slotProtections[equipmentSlot.getIndex()];
  }

  public int getEnchantmentValue() {
    return this.enchantmentValue;
  }

  public SoundEvent getEquipSound() {
    return this.sound;
  }

  public Ingredient getRepairIngredient() {
    return this.repairIngredient.get();
  }

  public String getName() {
    return this.name;
  }

  public float getToughness() {
    return this.toughness;
  }

  public float getKnockbackResistance() {
    return this.knockbackResistance;
  }
}

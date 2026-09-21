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

package de.markusbordihn.fireextinguisher.gametest;

import de.markusbordihn.fireextinguisher.item.FireProtectionArmorItem;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class FireArmorTestHelper {

  private static final int DURATION_PER_ARMOR_PIECE = 40;

  private FireArmorTestHelper() {}

  public static void testWornPiecesAreCounted(
      GameTestHelper helper,
      ArmorMaterial material,
      Item helmet,
      Item chestplate,
      Item leggings,
      Item boots) {
    Player player = helper.makeMockSurvivalPlayer();
    assertWornPieces(helper, player, material, 0);

    player.setItemSlot(EquipmentSlot.HEAD, new ItemStack(helmet));
    assertWornPieces(helper, player, material, 1);

    player.setItemSlot(EquipmentSlot.CHEST, new ItemStack(chestplate));
    player.setItemSlot(EquipmentSlot.LEGS, new ItemStack(leggings));
    player.setItemSlot(EquipmentSlot.FEET, new ItemStack(boots));
    assertWornPieces(helper, player, material, 4);

    helper.succeed();
  }

  public static void testOtherMaterialIsNotCounted(
      GameTestHelper helper, ArmorMaterial material, Item helmet, Item otherMaterialChestplate) {
    Player player = helper.makeMockSurvivalPlayer();
    player.setItemSlot(EquipmentSlot.HEAD, new ItemStack(helmet));
    player.setItemSlot(EquipmentSlot.CHEST, new ItemStack(otherMaterialChestplate));
    assertWornPieces(helper, player, material, 1);

    helper.succeed();
  }

  public static void testProtectionDurationStacksPerWornPiece(
      GameTestHelper helper, ArmorMaterial material, Item helmet, Item chestplate) {
    Player player = helper.makeMockSurvivalPlayer();
    assertProtectionDuration(helper, player, material, 0);

    player.setItemSlot(EquipmentSlot.HEAD, new ItemStack(helmet));
    assertProtectionDuration(helper, player, material, DURATION_PER_ARMOR_PIECE);

    player.setItemSlot(EquipmentSlot.CHEST, new ItemStack(chestplate));
    assertProtectionDuration(helper, player, material, DURATION_PER_ARMOR_PIECE * 2);

    helper.succeed();
  }

  private static void assertProtectionDuration(
      GameTestHelper helper, Player player, ArmorMaterial material, int expectedDuration) {
    int duration =
        FireProtectionArmorItem.calculateProtectionDuration(
            player, material, DURATION_PER_ARMOR_PIECE);
    helper.assertTrue(
        duration == expectedDuration,
        "Fire protection lasts " + duration + " ticks instead of " + expectedDuration);
  }

  private static void assertWornPieces(
      GameTestHelper helper, Player player, ArmorMaterial material, int expectedCount) {
    int wornPieces = FireProtectionArmorItem.countWornFireArmorPieces(player, material);
    helper.assertTrue(
        wornPieces == expectedCount,
        "Counted " + wornPieces + " worn fire armor pieces instead of " + expectedCount);
  }
}

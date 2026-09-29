/*
 * Copyright 2026 Markus Bordihn
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

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class FireExtinguisherTestHelper {

  private static final BlockPos GROUND_POS = new BlockPos(1, 0, 1);
  private static final BlockPos FIRE_POS = GROUND_POS.above();
  private static final BlockPos SIDE_FIRE_POS = new BlockPos(2, 1, 1);
  private static final int BURNING_TICKS = 100;
  private static final int MAX_REMAINING_FIRE_TICKS = 2;

  private FireExtinguisherTestHelper() {}

  public static void testSprayExtinguishesFire(GameTestHelper helper, Item fireExtinguisher) {
    helper.setBlock(GROUND_POS, Blocks.NETHERRACK);
    helper.setBlock(SIDE_FIRE_POS.below(), Blocks.NETHERRACK);
    helper.setBlock(FIRE_POS, Blocks.FIRE);
    helper.setBlock(SIDE_FIRE_POS, Blocks.FIRE);
    Player player = survivalPlayerHolding(helper, fireExtinguisher);
    ItemStack itemStack = player.getMainHandItem();
    fireExtinguisher.useOn(useOnGround(helper, player));
    helper.assertBlockNotPresent(Blocks.FIRE, FIRE_POS);
    helper.assertBlockNotPresent(Blocks.FIRE, SIDE_FIRE_POS);
    helper.assertTrue(itemStack.getDamageValue() == 1, "Spraying fire did not use durability");
    assertCooldownStarted(helper, player, fireExtinguisher);
  }

  public static void testSprayWithoutFireKeepsDurability(
      GameTestHelper helper, Item fireExtinguisher) {
    helper.setBlock(GROUND_POS, Blocks.STONE);
    Player player = survivalPlayerHolding(helper, fireExtinguisher);
    ItemStack itemStack = player.getMainHandItem();
    fireExtinguisher.useOn(useOnGround(helper, player));
    helper.assertTrue(itemStack.getDamageValue() == 0, "Spraying without fire used durability");
    assertCooldownStarted(helper, player, fireExtinguisher);
  }

  public static void testSneakingPlacesFireExtinguisher(
      GameTestHelper helper, Item fireExtinguisher) {
    helper.setBlock(GROUND_POS, Blocks.STONE);
    Player player = survivalPlayerHolding(helper, fireExtinguisher);
    player.setShiftKeyDown(true);
    fireExtinguisher.useOn(useOnGround(helper, player));
    helper.assertBlockPresent(Block.byItem(fireExtinguisher), GROUND_POS.above());
  }

  public static void testUseExtinguishesBurningPlayer(
      GameTestHelper helper, Item fireExtinguisher) {
    Player player = survivalPlayerHolding(helper, fireExtinguisher);
    ItemStack itemStack = player.getMainHandItem();
    player.setRemainingFireTicks(BURNING_TICKS);
    fireExtinguisher.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
    helper.assertTrue(
        player.getRemainingFireTicks() <= MAX_REMAINING_FIRE_TICKS, "Player is still burning");
    helper.assertTrue(itemStack.getDamageValue() == 1, "Extinguishing a player used no durability");
    assertCooldownStarted(helper, player, fireExtinguisher);
  }

  public static void testUseWithoutBurningDoesNothing(
      GameTestHelper helper, Item fireExtinguisher) {
    Player player = survivalPlayerHolding(helper, fireExtinguisher);
    ItemStack itemStack = player.getMainHandItem();
    fireExtinguisher.use(helper.getLevel(), player, InteractionHand.MAIN_HAND);
    helper.assertTrue(itemStack.getDamageValue() == 0, "Using without fire used durability");
    helper.assertFalse(
        player.getCooldowns().isOnCooldown(fireExtinguisher), "Cooldown started without fire");
  }

  public static void testSprayOnBurningMob(GameTestHelper helper, Item fireExtinguisher) {
    Pig pig = helper.spawn(EntityType.PIG, FIRE_POS);
    pig.setRemainingFireTicks(BURNING_TICKS);
    float fullHealth = pig.getHealth();
    Player player = survivalPlayerHolding(helper, fireExtinguisher);
    ItemStack itemStack = player.getMainHandItem();
    fireExtinguisher.interactLivingEntity(itemStack, player, pig, InteractionHand.MAIN_HAND);
    helper.assertTrue(
        pig.getRemainingFireTicks() <= MAX_REMAINING_FIRE_TICKS, "Pig is still burning");
    helper.assertTrue(pig.hasEffect(MobEffects.MOVEMENT_SLOWDOWN), "Pig was not slowed down");
    helper.assertTrue(pig.getHealth() < fullHealth, "Pig took no damage");
    helper.assertTrue(itemStack.getDamageValue() == 1, "Spraying a mob used no durability");
    assertCooldownStarted(helper, player, fireExtinguisher);
  }

  private static void assertCooldownStarted(GameTestHelper helper, Player player, Item item) {
    helper.assertTrue(player.getCooldowns().isOnCooldown(item), "Cooldown was not started");
  }

  private static Player survivalPlayerHolding(GameTestHelper helper, Item item) {
    Player player = helper.makeMockSurvivalPlayer();
    player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(item));
    return player;
  }

  private static UseOnContext useOnGround(GameTestHelper helper, Player player) {
    BlockPos absolutePos = helper.absolutePos(GROUND_POS);
    return new UseOnContext(
        player,
        InteractionHand.MAIN_HAND,
        new BlockHitResult(Vec3.atCenterOf(absolutePos), Direction.UP, absolutePos, false));
  }
}

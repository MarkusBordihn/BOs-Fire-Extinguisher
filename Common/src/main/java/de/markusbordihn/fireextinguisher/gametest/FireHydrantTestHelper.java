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

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class FireHydrantTestHelper {

  private static final BlockPos HYDRANT_POS = new BlockPos(1, 1, 1);
  private static final int USED_DAMAGE_VALUE = 100;

  private FireHydrantTestHelper() {}

  public static void testRefillsUsedFireExtinguisher(
      GameTestHelper helper, Block fireHydrant, Item fireExtinguisher) {
    helper.setBlock(HYDRANT_POS, fireHydrant);
    ItemStack itemStack = new ItemStack(fireExtinguisher);
    itemStack.setDamageValue(USED_DAMAGE_VALUE);
    Player player = playerHolding(helper, itemStack);
    InteractionResult result = useHydrant(helper, fireHydrant, player);
    helper.assertTrue(result.consumesAction(), "Fire hydrant ignored the fire extinguisher");
    helper.assertTrue(itemStack.getDamageValue() == 0, "Fire extinguisher was not refilled");
  }

  public static void testIgnoresOtherItems(GameTestHelper helper, Block fireHydrant) {
    helper.setBlock(HYDRANT_POS, fireHydrant);
    Player player = playerHolding(helper, new ItemStack(Items.STICK));
    InteractionResult result = useHydrant(helper, fireHydrant, player);
    helper.assertTrue(result == InteractionResult.PASS, "Fire hydrant reacted to a stick");
  }

  private static Player playerHolding(GameTestHelper helper, ItemStack itemStack) {
    Player player = helper.makeMockPlayer();
    player.setItemInHand(InteractionHand.MAIN_HAND, itemStack);
    return player;
  }

  private static InteractionResult useHydrant(
      GameTestHelper helper, Block fireHydrant, Player player) {
    BlockPos absolutePos = helper.absolutePos(HYDRANT_POS);
    return fireHydrant.use(
        helper.getBlockState(HYDRANT_POS),
        helper.getLevel(),
        absolutePos,
        player,
        InteractionHand.MAIN_HAND,
        new BlockHitResult(Vec3.atCenterOf(absolutePos), Direction.UP, absolutePos, false));
  }
}

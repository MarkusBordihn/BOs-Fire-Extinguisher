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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class FireAxeTestHelper {

  private static final BlockPos GROUND_POS = new BlockPos(1, 0, 1);
  private static final BlockPos FIRE_POS = GROUND_POS.above();

  private FireAxeTestHelper() {}

  public static void testExtinguishesFire(GameTestHelper helper, Item fireAxe) {
    helper.setBlock(GROUND_POS, Blocks.NETHERRACK);
    helper.setBlock(FIRE_POS, Blocks.FIRE);
    ItemStack itemStack = useOnGround(helper, fireAxe);
    helper.assertBlockNotPresent(Blocks.FIRE, FIRE_POS);
    helper.assertTrue(itemStack.getDamageValue() == 1, "Extinguishing fire used no durability");
  }

  public static void testExtinguishesLitCampfire(GameTestHelper helper, Item fireAxe) {
    helper.setBlock(GROUND_POS, Blocks.STONE);
    helper.setBlock(FIRE_POS, Blocks.CAMPFIRE);
    helper.assertBlockProperty(FIRE_POS, CampfireBlock.LIT, true);
    ItemStack itemStack = useOnGround(helper, fireAxe);
    helper.assertBlockProperty(FIRE_POS, CampfireBlock.LIT, false);
    helper.assertTrue(itemStack.getDamageValue() == 1, "Campfire used no durability");
  }

  public static void testWithoutFireKeepsDurability(GameTestHelper helper, Item fireAxe) {
    helper.setBlock(GROUND_POS, Blocks.STONE);
    ItemStack itemStack = useOnGround(helper, fireAxe);
    helper.assertTrue(itemStack.getDamageValue() == 0, "Using without fire used durability");
  }

  private static ItemStack useOnGround(GameTestHelper helper, Item fireAxe) {
    Player player = helper.makeMockSurvivalPlayer();
    ItemStack itemStack = new ItemStack(fireAxe);
    player.setItemInHand(InteractionHand.MAIN_HAND, itemStack);
    BlockPos absolutePos = helper.absolutePos(GROUND_POS);
    fireAxe.useOn(
        new UseOnContext(
            player,
            InteractionHand.MAIN_HAND,
            new BlockHitResult(Vec3.atCenterOf(absolutePos), Direction.UP, absolutePos, false)));
    return itemStack;
  }
}

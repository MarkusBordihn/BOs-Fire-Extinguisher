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

import de.markusbordihn.fireextinguisher.utils.FireDetection;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;

public class FireDetectionTestHelper {

  private static final BlockPos BASE_POS = new BlockPos(1, 1, 1);
  private static final BlockPos FIRE_POS = BASE_POS.above();

  private FireDetectionTestHelper() {}

  public static void testExtinguishFire(GameTestHelper helper) {
    helper.setBlock(BASE_POS, Blocks.STONE);
    helper.setBlock(FIRE_POS, Blocks.FIRE);
    helper.assertBlockPresent(Blocks.FIRE, FIRE_POS);
    extinguishAroundFire(helper);
    helper.assertBlockNotPresent(Blocks.FIRE, FIRE_POS);
    helper.assertBlockPresent(Blocks.STONE, BASE_POS);
  }

  public static void testExtinguishSoulFire(GameTestHelper helper) {
    helper.setBlock(BASE_POS, Blocks.SOUL_SOIL);
    helper.setBlock(FIRE_POS, Blocks.SOUL_FIRE);
    helper.assertBlockPresent(Blocks.SOUL_FIRE, FIRE_POS);
    extinguishAroundFire(helper);
    helper.assertBlockNotPresent(Blocks.SOUL_FIRE, FIRE_POS);
    helper.assertBlockPresent(Blocks.SOUL_SOIL, BASE_POS);
  }

  public static void testExtinguishSoulCampfire(GameTestHelper helper) {
    helper.setBlock(BASE_POS, Blocks.STONE);
    helper.setBlock(FIRE_POS, Blocks.SOUL_CAMPFIRE);
    helper.assertBlockProperty(FIRE_POS, CampfireBlock.LIT, true);
    extinguishAroundFire(helper);
    helper.assertBlockPresent(Blocks.SOUL_CAMPFIRE, FIRE_POS);
    helper.assertBlockProperty(FIRE_POS, CampfireBlock.LIT, false);
  }

  public static void testLitCampfireIsNoFireSource(GameTestHelper helper) {
    helper.setBlock(BASE_POS, Blocks.STONE);
    helper.setBlock(FIRE_POS, Blocks.CAMPFIRE);
    helper.assertBlockProperty(FIRE_POS, CampfireBlock.LIT, true);
    helper.assertFalse(
        FireDetection.hasFireWithin(helper.getLevel(), helper.absolutePos(FIRE_POS), 1, 1, 1),
        "Lit campfire is reported as fire and raises a false smoke detector alarm");
    extinguishAroundFire(helper);
    helper.assertBlockPresent(Blocks.CAMPFIRE, FIRE_POS);
    helper.assertBlockProperty(FIRE_POS, CampfireBlock.LIT, false);
  }

  private static void extinguishAroundFire(GameTestHelper helper) {
    FireDetection.extinguishWithin(helper.getLevel(), helper.absolutePos(FIRE_POS), 1, 1, 1);
  }
}

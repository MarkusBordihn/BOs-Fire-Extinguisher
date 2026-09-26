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
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.animal.pig.Pig;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class FirePoleTestHelper {

  public static final int TIMEOUT_TICKS = 100;
  private static final BlockPos FLOOR_POS = new BlockPos(1, 0, 1);
  private static final int POLE_HEIGHT = 4;
  private static final float LETHAL_FALL_DISTANCE = 20.0F;

  private FirePoleTestHelper() {}

  public static void testSlidesDownWithoutFallDamage(GameTestHelper helper, Block firePole) {
    helper.setBlock(FLOOR_POS, Blocks.STONE);
    for (int i = 1; i <= POLE_HEIGHT; i++) {
      helper.setBlock(FLOOR_POS.above(i), firePole);
    }
    Pig pig = helper.spawn(EntityTypes.PIG, FLOOR_POS.above(POLE_HEIGHT + 1));
    pig.fallDistance = LETHAL_FALL_DISTANCE;
    pig.setDeltaMovement(0.0, -1.0, 0.0);
    float fullHealth = pig.getHealth();
    helper.succeedWhen(
        () -> {
          helper.assertTrue(pig.onGround(), "Pig is still sliding down the fire pole");
          helper.assertTrue(pig.getHealth() >= fullHealth, "Pig took fall damage");
        });
  }
}

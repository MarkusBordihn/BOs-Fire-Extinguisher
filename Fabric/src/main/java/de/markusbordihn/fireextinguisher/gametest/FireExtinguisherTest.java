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

import de.markusbordihn.fireextinguisher.item.ModBlockItems;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;

@SuppressWarnings("unused")
public class FireExtinguisherTest {

  @GameTest(template = "fire_extinguisher:gametest.3x3x3")
  public void testSprayExtinguishesFire(GameTestHelper helper) {
    FireExtinguisherTestHelper.testSprayExtinguishesFire(helper, ModBlockItems.FIRE_EXTINGUISHER);
    helper.succeed();
  }

  @GameTest(template = "fire_extinguisher:gametest.3x3x3")
  public void testSprayWithoutFireKeepsDurability(GameTestHelper helper) {
    FireExtinguisherTestHelper.testSprayWithoutFireKeepsDurability(
        helper, ModBlockItems.FIRE_EXTINGUISHER);
    helper.succeed();
  }

  @GameTest(template = "fire_extinguisher:gametest.3x3x3")
  public void testSneakingPlacesFireExtinguisher(GameTestHelper helper) {
    FireExtinguisherTestHelper.testSneakingPlacesFireExtinguisher(
        helper, ModBlockItems.FIRE_EXTINGUISHER);
    helper.succeed();
  }

  @GameTest(template = "fire_extinguisher:gametest.3x3x3")
  public void testUseExtinguishesBurningPlayer(GameTestHelper helper) {
    FireExtinguisherTestHelper.testUseExtinguishesBurningPlayer(
        helper, ModBlockItems.FIRE_EXTINGUISHER);
    helper.succeed();
  }

  @GameTest(template = "fire_extinguisher:gametest.3x3x3")
  public void testUseWithoutBurningDoesNothing(GameTestHelper helper) {
    FireExtinguisherTestHelper.testUseWithoutBurningDoesNothing(
        helper, ModBlockItems.FIRE_EXTINGUISHER);
    helper.succeed();
  }

  @GameTest(template = "fire_extinguisher:gametest.3x3x3")
  public void testSprayOnBurningMob(GameTestHelper helper) {
    FireExtinguisherTestHelper.testSprayOnBurningMob(helper, ModBlockItems.FIRE_EXTINGUISHER);
    helper.succeed();
  }
}

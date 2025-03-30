/*
 * Copyright 2024 Markus Bordihn
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

import de.markusbordihn.fireextinguisher.item.ModItems;
import net.minecraft.gametest.framework.GameTestHelper;

@SuppressWarnings("unused")
public class ModItemsTest {

  public void testFireAxeItem(GameTestHelper helper) {
    ModItemsTestHelper.testModItem(helper, ModItems.FIRE_AXE.get());
    helper.succeed();
  }

  public void testFireHelmetItem(GameTestHelper helper) {
    ModItemsTestHelper.testModItem(helper, ModItems.FIRE_HELMET.get());
    helper.succeed();
  }

  public void testFireChestplateItem(GameTestHelper helper) {
    ModItemsTestHelper.testModItem(helper, ModItems.FIRE_CHESTPLATE.get());
    helper.succeed();
  }

  public void testFireLeggingsItem(GameTestHelper helper) {
    ModItemsTestHelper.testModItem(helper, ModItems.FIRE_LEGGINGS.get());
    helper.succeed();
  }

  public void testFireBootsItem(GameTestHelper helper) {
    ModItemsTestHelper.testModItem(helper, ModItems.FIRE_BOOTS.get());
    helper.succeed();
  }

  public void testFireHelmetLightItem(GameTestHelper helper) {
    ModItemsTestHelper.testModItem(helper, ModItems.FIRE_HELMET_LIGHT.get());
    helper.succeed();
  }

  public void testFireChestplateLightItem(GameTestHelper helper) {
    ModItemsTestHelper.testModItem(helper, ModItems.FIRE_CHESTPLATE_LIGHT.get());
    helper.succeed();
  }

  public void testFireLeggingsLightItem(GameTestHelper helper) {
    ModItemsTestHelper.testModItem(helper, ModItems.FIRE_LEGGINGS_LIGHT.get());
    helper.succeed();
  }

  public void testFireBootsLightItem(GameTestHelper helper) {
    ModItemsTestHelper.testModItem(helper, ModItems.FIRE_BOOTS_LIGHT.get());
    helper.succeed();
  }
}

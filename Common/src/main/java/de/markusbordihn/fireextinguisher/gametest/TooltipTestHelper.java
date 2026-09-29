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

import de.markusbordihn.fireextinguisher.Constants;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public class TooltipTestHelper {

  private TooltipTestHelper() {}

  public static void testAllItemTooltips(GameTestHelper helper) {
    Set<String> failedItems = new TreeSet<>();
    int testedItems = 0;
    Item.TooltipContext tooltipContext = Item.TooltipContext.of(helper.getLevel());
    for (Item item : BuiltInRegistries.ITEM) {
      ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(item);
      if (!itemId.getNamespace().equals(Constants.MOD_ID)) {
        continue;
      }

      testedItems++;
      for (TooltipFlag tooltipFlag : List.of(TooltipFlag.NORMAL, TooltipFlag.ADVANCED)) {
        try {
          if (new ItemStack(item).getTooltipLines(tooltipContext, null, tooltipFlag).isEmpty()) {
            failedItems.add(itemId + " has no tooltip lines");
          }
        } catch (RuntimeException | LinkageError e) {
          failedItems.add(itemId + " throws " + e);
        }
      }
    }

    if (testedItems == 0) {
      helper.fail("No " + Constants.MOD_ID + " items found in the item registry!");
      return;
    }

    if (!failedItems.isEmpty()) {
      helper.fail("Tooltip creation failed: " + String.join(", ", failedItems));
      return;
    }

    helper.succeed();
  }
}

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

package de.markusbordihn.fireextinguisher;

import de.markusbordihn.fireextinguisher.platform.Services;
import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public class CommonClass {

  protected CommonClass() {}

  // This method serves as an initialization hook for the mod. The vanilla
  // game has no mechanism to load tooltip listeners so this must be
  // invoked from a mod loader specific project like Forge or Fabric.
  public static void init() {

    de.markusbordihn.fireextinguisher.Constants.LOG.info(
        "Hello from Common init on {}! we are currently in a {} environment!",
        Services.PLATFORM.getPlatformName(),
        Services.PLATFORM.isDevelopmentEnvironment() ? "development" : "production");
  }

  // This method serves as a hook to modify item tooltips. The vanilla game
  // has no mechanism to load tooltip listeners so this must be registered
  // by a mod loader like Forge or Fabric.
  public static void onItemTooltip(ItemStack stack, TooltipFlag context, List<Component> tooltip) {
    // Placeholder for future implementation.
  }
}

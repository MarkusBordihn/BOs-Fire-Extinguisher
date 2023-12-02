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

import de.markusbordihn.fireextinguisher.block.ModBlocks;
import de.markusbordihn.fireextinguisher.config.CommonConfig;
import de.markusbordihn.fireextinguisher.config.ForgeConfigHelperFabric;
import de.markusbordihn.fireextinguisher.item.ModBlockItems;
import de.markusbordihn.fireextinguisher.item.ModItems;
import net.fabricmc.api.ModInitializer;

public class FireExtinguisher implements ModInitializer {

  @Override
  public void onInitialize() {
    // Use Fabric to bootstrap the Common mod.
    Constants.LOG.info("Initializing {} (Fabric) ...", Constants.MOD_NAME);
    CommonClass.init();

    Constants.LOG.info("{} Config ...", Constants.LOG_REGISTER_PREFIX);
    new ForgeConfigHelperFabric().registerCommonConfig(CommonConfig.COMMON_SPEC);

    Constants.LOG.info("{} Blocks ...", Constants.LOG_REGISTER_PREFIX);
    ModBlocks.registerModBlocks();

    Constants.LOG.info("{} Block Items ...", Constants.LOG_REGISTER_PREFIX);
    ModBlockItems.registerModBlockItems();

    Constants.LOG.info("{} Items ...", Constants.LOG_REGISTER_PREFIX);
    ModItems.registerModItems();
  }
}

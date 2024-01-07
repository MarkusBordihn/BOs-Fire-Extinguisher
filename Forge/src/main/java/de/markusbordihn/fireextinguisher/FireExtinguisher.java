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
import de.markusbordihn.fireextinguisher.config.ForgeConfigHelperForge;
import de.markusbordihn.fireextinguisher.item.ModBlockItems;
import de.markusbordihn.fireextinguisher.item.ModItems;
import de.markusbordihn.fireextinguisher.sounds.ModSoundEvents;
import de.markusbordihn.fireextinguisher.tabs.ModTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Constants.MOD_ID)
public class FireExtinguisher {

  public static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  public FireExtinguisher() {
    final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    log.info("{} Config ...", Constants.LOG_REGISTER_PREFIX);
    new ForgeConfigHelperForge().registerCommonConfig(CommonConfig.COMMON_SPEC);

    log.info("{} Blocks ...", Constants.LOG_REGISTER_PREFIX);
    ModBlocks.BLOCKS.register(modEventBus);

    log.info("{} Block Items ...", Constants.LOG_REGISTER_PREFIX);
    ModBlockItems.ITEMS.register(modEventBus);

    log.info("{} Items ...", Constants.LOG_REGISTER_PREFIX);
    ModItems.ITEMS.register(modEventBus);

    Constants.LOG.info("{} Sound Events ...", Constants.LOG_REGISTER_PREFIX);
    ModSoundEvents.SOUNDS.register(modEventBus);

    DistExecutor.unsafeRunWhenOn(
        Dist.CLIENT, () -> () -> ModTabs.CREATIVE_TABS.register(modEventBus));
  }
}

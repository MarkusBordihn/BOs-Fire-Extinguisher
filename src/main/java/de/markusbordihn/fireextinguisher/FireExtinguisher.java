package de.markusbordihn.fireextinguisher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import de.markusbordihn.fireextinguisher.block.ModBlocks;
import de.markusbordihn.fireextinguisher.item.ModItems;

@Mod(Constants.MOD_ID)
public class FireExtinguisher {

  public static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  public FireExtinguisher() {
    final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    modEventBus.addListener(ClientSetup::new);

    log.info("Register Items ...");
    ModItems.ITEMS.register(modEventBus);

    log.info("Register Blocks ...");
    ModBlocks.BLOCKS.register(modEventBus);
  }
}

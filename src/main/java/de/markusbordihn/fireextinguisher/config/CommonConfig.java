/**
 * Copyright 2022 Markus Bordihn
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

package de.markusbordihn.fireextinguisher.config;

import org.apache.commons.lang3.tuple.Pair;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;

import de.markusbordihn.fireextinguisher.Constants;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class CommonConfig {

  protected static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  public static final ForgeConfigSpec commonSpec;
  public static final Config COMMON;

  protected CommonConfig() {}

  static {
    com.electronwill.nightconfig.core.Config.setInsertionOrderPreserved(true);
    final Pair<Config, ForgeConfigSpec> specPair =
        new ForgeConfigSpec.Builder().configure(Config::new);
    commonSpec = specPair.getRight();
    COMMON = specPair.getLeft();
    log.info("{} Common config ...", Constants.LOG_REGISTER_PREFIX);
    ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, commonSpec);
  }

  public static class Config {


    public final ForgeConfigSpec.IntValue fireExtinguisherRadius;

    public final ForgeConfigSpec.IntValue fireAxtRadius;

    public final ForgeConfigSpec.BooleanValue fireProtectionEnabled;
    public final ForgeConfigSpec.IntValue fireProtectionDuration;
    public final ForgeConfigSpec.IntValue fireProtectionRenew;

    public final ForgeConfigSpec.BooleanValue fireBootsSlowDownEnabled;
    public final ForgeConfigSpec.BooleanValue fireChestplateSlowDownEnabled;
    public final ForgeConfigSpec.BooleanValue fireLeggingsSlowDownEnabled;

    Config(ForgeConfigSpec.Builder builder) {
      builder.comment(Constants.MOD_NAME);

      builder.push("General");
      builder.pop();

      builder.push("Fire Extinguisher");
      fireExtinguisherRadius = builder.comment("The radius around the target block to stop fire.")
          .defineInRange("fireExtinguisherRadius", 2, 0, 8);
      builder.pop();

      builder.push("Fire Axt");
      fireAxtRadius = builder.comment("The radius around the target block to stop fire.")
          .defineInRange("fireAxtRadius", 1, 0, 8);
      builder.pop();

      builder.push("Fire Protection");
      fireProtectionEnabled =
          builder.comment("Enable/Disable fire protection affects from fire armor.")
              .define("fireProtectionEnabled", true);
      fireProtectionRenew =
          builder.comment("Defines the amount of ticks after the fire protection get's renewed.")
              .defineInRange("fireProtectionRenew", 40, 20, 6000);
      fireProtectionDuration =
          builder.comment("Defines the amount of ticks how long the fire protection is enabled.")
              .defineInRange("fireProtectionDuration", 20, 10, 6000);
      builder.pop();

      builder.push("Fire Boots");
      fireBootsSlowDownEnabled =
          builder.comment("Enable/Disable slow down effect of wearing the fire boots.")
              .define("fireBootsSlowDownEnabled", true);
      builder.pop();

      builder.push("Fire Chestplate");
      fireChestplateSlowDownEnabled =
          builder.comment("Enable/Disable slow down effect of wearing the fire chestplate.")
              .define("fireChestplateSlowDownEnabled", true);
      builder.pop();

      builder.push("Fire Leggings");
      fireLeggingsSlowDownEnabled =
          builder.comment("Enable/Disable slow down effect of wearing the fire leggings.")
              .define("fireLeggingsSlowDownEnabled", true);
      builder.pop();
    }
  }
}

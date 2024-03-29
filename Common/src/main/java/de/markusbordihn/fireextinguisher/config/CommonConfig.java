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

package de.markusbordihn.fireextinguisher.config;

import de.markusbordihn.fireextinguisher.Constants;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class CommonConfig {

  public static final ForgeConfigSpec COMMON_SPEC;
  public static final Config COMMON;

  static {
    com.electronwill.nightconfig.core.Config.setInsertionOrderPreserved(true);
    final Pair<Config, ForgeConfigSpec> specPair =
        new ForgeConfigSpec.Builder().configure(Config::new);
    COMMON_SPEC = specPair.getRight();
    COMMON = specPair.getLeft();
  }

  protected CommonConfig() {}

  public static class Config {

    public final ForgeConfigSpec.IntValue fireExtinguisherRadius;
    public final ForgeConfigSpec.IntValue fireSprinklerRadiusX;
    public final ForgeConfigSpec.IntValue fireSprinklerRadiusY;
    public final ForgeConfigSpec.IntValue fireSprinklerRadiusZ;

    public final ForgeConfigSpec.IntValue smokeDetectorRadiusX;
    public final ForgeConfigSpec.IntValue smokeDetectorRadiusY;
    public final ForgeConfigSpec.IntValue smokeDetectorRadiusZ;

    public final ForgeConfigSpec.IntValue fireAxtRadius;

    public final ForgeConfigSpec.BooleanValue fireProtectionEnabled;
    public final ForgeConfigSpec.IntValue fireProtectionDuration;
    public final ForgeConfigSpec.IntValue fireProtectionRenew;

    public final ForgeConfigSpec.BooleanValue fireBootsSlowDownEnabled;
    public final ForgeConfigSpec.BooleanValue fireChestplateSlowDownEnabled;
    public final ForgeConfigSpec.BooleanValue fireLeggingsSlowDownEnabled;

    public final ForgeConfigSpec.BooleanValue fireProtectionLightEnabled;
    public final ForgeConfigSpec.IntValue fireProtectionLightDuration;
    public final ForgeConfigSpec.IntValue fireProtectionLightRenew;

    Config(ForgeConfigSpec.Builder builder) {
      builder.comment(Constants.MOD_NAME);

      builder.push("General");
      builder.pop();

      builder.push("Fire Extinguisher");
      fireExtinguisherRadius =
          builder
              .comment("The radius around the target block to stop fire.")
              .defineInRange("fireExtinguisherRadius", 2, 0, 8);
      builder.pop();

      builder.push("Fire Axt");
      fireAxtRadius =
          builder
              .comment("The radius around the target block to stop fire.")
              .defineInRange("fireAxtRadius", 1, 0, 8);
      builder.pop();

      builder.push("Fire Sprinkler");
      fireSprinklerRadiusX =
          builder
              .comment(
                  "The Manhattan distance around the target block to spread water in X direction.")
              .defineInRange("fireSprinklerRadiusX", 2, 0, 8);
      fireSprinklerRadiusY =
          builder
              .comment(
                  "The Manhattan distance around the target block to spread water in Y direction.")
              .defineInRange("fireSprinklerRadiusY", 3, 0, 8);
      fireSprinklerRadiusZ =
          builder
              .comment(
                  "The Manhattan distance around the target block to spread water in Z direction.")
              .defineInRange("fireSprinklerRadiusZ", 2, 0, 8);
      builder.pop();

      builder.push("Smoke Detector");
      smokeDetectorRadiusX =
          builder
              .comment(
                  "The Manhattan distance around the target block to detect fire in X direction.")
              .defineInRange("smokeDetectorRadiusX", 3, 0, 8);
      smokeDetectorRadiusY =
          builder
              .comment(
                  "The Manhattan distance around the target block to detect fire in Y direction.")
              .defineInRange("smokeDetectorRadiusY", 5, 0, 8);
      smokeDetectorRadiusZ =
          builder
              .comment(
                  "The Manhattan distance around the target block to detect fire in Z direction.")
              .defineInRange("smokeDetectorRadiusZ", 3, 0, 8);
      builder.pop();

      builder.push("Fire Protection (heavy)");
      fireProtectionEnabled =
          builder
              .comment("Enable/Disable fire protection affects from fire armor.")
              .define("fireProtectionEnabled", true);
      fireProtectionRenew =
          builder
              .comment("Defines the amount of ticks after the fire protection get's renewed.")
              .defineInRange("fireProtectionRenew", 80, 20, 6000);
      fireProtectionDuration =
          builder
              .comment("Defines the amount of ticks how long the fire protection is enabled.")
              .defineInRange("fireProtectionDuration", 25, 10, 6000);

      builder.push("Fire Boots");
      fireBootsSlowDownEnabled =
          builder
              .comment("Enable/Disable slow down effect of wearing the fire boots.")
              .define("fireBootsSlowDownEnabled", true);
      builder.pop();

      builder.push("Fire Chestplate");
      fireChestplateSlowDownEnabled =
          builder
              .comment("Enable/Disable slow down effect of wearing the fire chestplate.")
              .define("fireChestplateSlowDownEnabled", true);
      builder.pop();

      builder.push("Fire Leggings");
      fireLeggingsSlowDownEnabled =
          builder
              .comment("Enable/Disable slow down effect of wearing the fire leggings.")
              .define("fireLeggingsSlowDownEnabled", true);
      builder.pop();

      builder.pop();

      builder.push("Fire Protection (light)");
      fireProtectionLightEnabled =
          builder
              .comment("Enable/Disable fire protection light affects from fire armor.")
              .define("fireProtectionLightEnabled", true);
      fireProtectionLightRenew =
          builder
              .comment("Defines the amount of ticks after the fire protection light get's renewed.")
              .defineInRange("fireProtectionLightRenew", 180, 20, 6000);
      fireProtectionLightDuration =
          builder
              .comment("Defines the amount of ticks how long the fire protection light is enabled.")
              .defineInRange("fireProtectionLightDuration", 40, 10, 6000);
      builder.pop();
    }
  }
}

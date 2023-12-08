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

package de.markusbordihn.fireextinguisher.block;

import de.markusbordihn.fireextinguisher.Constants;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

  public static final DeferredRegister<Block> BLOCKS =
      DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MOD_ID);
  // Fire Extinguisher
  public static final RegistryObject<Block> FIRE_EXTINGUISHER =
      BLOCKS.register(FireExtinguisherBlock.NAME, () -> FireExtinguisherBlocks.FIRE_EXTINGUISHER);
  public static final RegistryObject<Block> FIRE_EXTINGUISHER_COPPER =
      BLOCKS.register(
          FireExtinguisherBlock.NAME + "_copper",
          () -> FireExtinguisherBlocks.FIRE_EXTINGUISHER_COPPER);

  // Fire Alarm
  public static final RegistryObject<Block> FIRE_SPRINKLER =
      BLOCKS.register(FireSprinklerBlock.NAME, () -> FireAlarmBlocks.FIRE_SPRINKLER);
  public static final RegistryObject<Block> FIRE_ALARM_SWITCH =
      BLOCKS.register(FireAlarmSwitchBlock.NAME, () -> FireAlarmBlocks.FIRE_ALARM_SWITCH);
  public static final RegistryObject<Block> FIRE_ALARM_BELL =
      BLOCKS.register(FireAlarmBellBlock.NAME, () -> FireAlarmBlocks.FIRE_ALARM_BELL);
  public static final RegistryObject<Block> FIRE_ALARM_SIREN =
      BLOCKS.register(FireAlarmSirenBlock.NAME, () -> FireAlarmBlocks.FIRE_ALARM_SIREN);
  public static final RegistryObject<Block> FIRE_ALARM_SMOKE_DETECTOR =
      BLOCKS.register(
          FireAlarmSmokeDetectorBlock.NAME, () -> FireAlarmBlocks.FIRE_ALARM_SMOKE_DETECTOR);
  public static final RegistryObject<Block> FIRE_ALARM_SMOKE_DETECTOR_SILENT =
      BLOCKS.register(
          FireAlarmSmokeDetectorSilentBlock.NAME,
          () -> FireAlarmBlocks.FIRE_ALARM_SMOKE_DETECTOR_SILENT);

  // Fire Extinguisher Signs
  public static final RegistryObject<Block> FIRE_EXTINGUISHER_SIGN =
      BLOCKS.register(
          FireExtinguisherSign.NAME, () -> FireExtinguisherSignBlocks.FIRE_EXTINGUISHER_SIGN);
  public static final RegistryObject<Block> FIRE_EXTINGUISHER_SIGN_LEFT =
      BLOCKS.register(
          FireExtinguisherSign.NAME + "_left",
          () -> FireExtinguisherSignBlocks.FIRE_EXTINGUISHER_SIGN_LEFT);
  public static final RegistryObject<Block> FIRE_EXTINGUISHER_SIGN_RIGHT =
      BLOCKS.register(
          FireExtinguisherSign.NAME + "_right",
          () -> FireExtinguisherSignBlocks.FIRE_EXTINGUISHER_SIGN_RIGHT);

  // Exit Signs
  public static final RegistryObject<Block> EXIT_SIGN =
      BLOCKS.register(ExitSign.NAME, () -> ExitSignBlocks.EXIT_SIGN);
  public static final RegistryObject<Block> EXIT_SIGN_LEFT =
      BLOCKS.register(ExitSign.NAME + "_left", () -> ExitSignBlocks.EXIT_SIGN_LEFT);
  public static final RegistryObject<Block> EXIT_SIGN_LEFT_DOWN =
      BLOCKS.register(ExitSign.NAME + "_left_down", () -> ExitSignBlocks.EXIT_SIGN_LEFT_DOWN);
  public static final RegistryObject<Block> EXIT_SIGN_LEFT_UP =
      BLOCKS.register(ExitSign.NAME + "_left_up", () -> ExitSignBlocks.EXIT_SIGN_LEFT_UP);
  public static final RegistryObject<Block> EXIT_SIGN_RIGHT =
      BLOCKS.register(ExitSign.NAME + "_right", () -> ExitSignBlocks.EXIT_SIGN_RIGHT);
  public static final RegistryObject<Block> EXIT_SIGN_RIGHT_DOWN =
      BLOCKS.register(ExitSign.NAME + "_right_down", () -> ExitSignBlocks.EXIT_SIGN_RIGHT_DOWN);
  public static final RegistryObject<Block> EXIT_SIGN_RIGHT_UP =
      BLOCKS.register(ExitSign.NAME + "_right_up", () -> ExitSignBlocks.EXIT_SIGN_RIGHT_UP);

  protected ModBlocks() {}
}

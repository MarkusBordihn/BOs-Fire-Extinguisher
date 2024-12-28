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
import de.markusbordihn.fireextinguisher.blockitem.FireExtinguisherSignBlockItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModBlocks {

  private static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  protected ModBlocks() {}

  public static void registerModBlocks() {
    log.info("{} Fire Extinguisher Blocks...", Constants.LOG_SUB_REGISTER_PREFIX);
    Registry.register(
        BuiltInRegistries.BLOCK,
        Constants.MOD_ID_PREFIX + FireExtinguisherBlock.ID,
        FireExtinguisherBlocks.FIRE_EXTINGUISHER);
    Registry.register(
        BuiltInRegistries.BLOCK,
        Constants.MOD_ID_PREFIX + FireExtinguisherBlock.ID + "_copper",
        FireExtinguisherBlocks.FIRE_EXTINGUISHER_COPPER);

    log.info("{} Fire Alarm Blocks...", Constants.LOG_SUB_REGISTER_PREFIX);
    Registry.register(
        BuiltInRegistries.BLOCK,
        Constants.MOD_ID_PREFIX + FireSprinklerBlock.ID,
        FireAlarmBlocks.FIRE_SPRINKLER);
    Registry.register(
        BuiltInRegistries.BLOCK,
        Constants.MOD_ID_PREFIX + FireAlarmSwitchBlock.ID,
        FireAlarmBlocks.FIRE_ALARM_SWITCH);
    Registry.register(
        BuiltInRegistries.BLOCK,
        Constants.MOD_ID_PREFIX + FireAlarmBellBlock.ID,
        FireAlarmBlocks.FIRE_ALARM_BELL);
    Registry.register(
        BuiltInRegistries.BLOCK,
        Constants.MOD_ID_PREFIX + FireAlarmSirenBlock.ID,
        FireAlarmBlocks.FIRE_ALARM_SIREN);
    Registry.register(
        BuiltInRegistries.BLOCK,
        Constants.MOD_ID_PREFIX + FireAlarmSmokeDetectorBlock.ID,
        FireAlarmBlocks.FIRE_ALARM_SMOKE_DETECTOR);
    Registry.register(
        BuiltInRegistries.BLOCK,
        Constants.MOD_ID_PREFIX + FireAlarmSmokeDetectorSilentBlock.ID,
        FireAlarmBlocks.FIRE_ALARM_SMOKE_DETECTOR_SILENT);

    log.info("{} Fire Extinguisher Sign Blocks...", Constants.LOG_SUB_REGISTER_PREFIX);
    Registry.register(
        BuiltInRegistries.BLOCK,
        Constants.MOD_ID_PREFIX + FireExtinguisherSignBlockItem.ID,
        FireExtinguisherSignBlocks.FIRE_EXTINGUISHER_SIGN);
    Registry.register(
        BuiltInRegistries.BLOCK,
        Constants.MOD_ID_PREFIX + FireExtinguisherSignBlockItem.ID_LEFT,
        FireExtinguisherSignBlocks.FIRE_EXTINGUISHER_SIGN_LEFT);
    Registry.register(
        BuiltInRegistries.BLOCK,
        Constants.MOD_ID_PREFIX + FireExtinguisherSignBlockItem.ID_RIGHT,
        FireExtinguisherSignBlocks.FIRE_EXTINGUISHER_SIGN_RIGHT);

    log.info("{} Exit Sign Blocks ...", Constants.LOG_SUB_REGISTER_PREFIX);
    Registry.register(
        BuiltInRegistries.BLOCK, Constants.MOD_ID_PREFIX + ExitSign.ID, ExitSignBlocks.EXIT_SIGN);
    Registry.register(
        BuiltInRegistries.BLOCK,
        Constants.MOD_ID_PREFIX + ExitSign.ID_LEFT,
        ExitSignBlocks.EXIT_SIGN_LEFT);
    Registry.register(
        BuiltInRegistries.BLOCK,
        Constants.MOD_ID_PREFIX + ExitSign.ID_LEFT_DOWN,
        ExitSignBlocks.EXIT_SIGN_LEFT_DOWN);
    Registry.register(
        BuiltInRegistries.BLOCK,
        Constants.MOD_ID_PREFIX + ExitSign.ID_LEFT_UP,
        ExitSignBlocks.EXIT_SIGN_LEFT_UP);
    Registry.register(
        BuiltInRegistries.BLOCK,
        Constants.MOD_ID_PREFIX + ExitSign.ID_RIGHT,
        ExitSignBlocks.EXIT_SIGN_RIGHT);
    Registry.register(
        BuiltInRegistries.BLOCK,
        Constants.MOD_ID_PREFIX + ExitSign.ID_RIGHT_DOWN,
        ExitSignBlocks.EXIT_SIGN_RIGHT_DOWN);
    Registry.register(
        BuiltInRegistries.BLOCK,
        Constants.MOD_ID_PREFIX + ExitSign.ID_RIGHT_UP,
        ExitSignBlocks.EXIT_SIGN_RIGHT_UP);
  }
}

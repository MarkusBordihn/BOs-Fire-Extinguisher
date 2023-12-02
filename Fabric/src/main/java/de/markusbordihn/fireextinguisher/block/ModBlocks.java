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
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;

public class ModBlocks {

  public static final Block FIRE_EXTINGUISHER = new FireExtinguisherBlock();

  protected ModBlocks() {}

  public static void registerModBlocks() {
    Constants.LOG.info("{} Fire Extinguisher Blocks...", Constants.LOG_SUB_REGISTER_PREFIX);
    Registry.register(
        Registry.BLOCK, Constants.MOD_ID_PREFIX + FireExtinguisherBlock.NAME, FIRE_EXTINGUISHER);

    Constants.LOG.info("{} Fire Extinguisher Sign Blocks...", Constants.LOG_SUB_REGISTER_PREFIX);
    Registry.register(
        Registry.BLOCK,
        Constants.MOD_ID_PREFIX + FireExtinguisherSign.NAME,
        FireExtinguisherSignBlocks.FIRE_EXTINGUISHER_SIGN);
    Registry.register(
        Registry.BLOCK,
        Constants.MOD_ID_PREFIX + FireExtinguisherSign.NAME + "_left",
        FireExtinguisherSignBlocks.FIRE_EXTINGUISHER_SIGN_LEFT);
    Registry.register(
        Registry.BLOCK,
        Constants.MOD_ID_PREFIX + FireExtinguisherSign.NAME + "_right",
        FireExtinguisherSignBlocks.FIRE_EXTINGUISHER_SIGN_RIGHT);

    Constants.LOG.info("{} Exit Sign Blocks ...", Constants.LOG_SUB_REGISTER_PREFIX);
    Registry.register(
        Registry.BLOCK, Constants.MOD_ID_PREFIX + ExitSign.NAME, ExitSignBlocks.EXIT_SIGN);
    Registry.register(
        Registry.BLOCK,
        Constants.MOD_ID_PREFIX + ExitSign.NAME + "_left",
        ExitSignBlocks.EXIT_SIGN_LEFT);
    Registry.register(
        Registry.BLOCK,
        Constants.MOD_ID_PREFIX + ExitSign.NAME + "_left_down",
        ExitSignBlocks.EXIT_SIGN_LEFT_DOWN);
    Registry.register(
        Registry.BLOCK,
        Constants.MOD_ID_PREFIX + ExitSign.NAME + "_left_up",
        ExitSignBlocks.EXIT_SIGN_LEFT_UP);
    Registry.register(
        Registry.BLOCK,
        Constants.MOD_ID_PREFIX + ExitSign.NAME + "_right",
        ExitSignBlocks.EXIT_SIGN_RIGHT);
    Registry.register(
        Registry.BLOCK,
        Constants.MOD_ID_PREFIX + ExitSign.NAME + "_right_down",
        ExitSignBlocks.EXIT_SIGN_RIGHT_DOWN);
    Registry.register(
        Registry.BLOCK,
        Constants.MOD_ID_PREFIX + ExitSign.NAME + "_right_up",
        ExitSignBlocks.EXIT_SIGN_RIGHT_UP);
  }
}

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
import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class ForgeConfigHelperFabric implements IForgeConfigHelper {

  @Override
  public void registerServerConfig(ForgeConfigSpec spec) {
    ModLoadingContext.registerConfig(Constants.MOD_ID, ModConfig.Type.SERVER, spec);
  }

  @Override
  public void registerClientConfig(ForgeConfigSpec spec) {
    ModLoadingContext.registerConfig(Constants.MOD_ID, ModConfig.Type.CLIENT, spec);
  }

  @Override
  public void registerCommonConfig(ForgeConfigSpec spec) {
    ModLoadingContext.registerConfig(Constants.MOD_ID, ModConfig.Type.COMMON, spec);
  }
}
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
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class FireExtinguisherClient {

  public static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  public FireExtinguisherClient(final FMLClientSetupEvent event) {
    log.info("{} Client Setup ...", Constants.LOG_REGISTER_PREFIX);

    event.enqueueWork(
        () -> {
          ItemBlockRenderTypes.setRenderLayer(
              ModBlocks.FIRE_EXTINGUISHER.get(), RenderType.cutoutMipped());
          ItemBlockRenderTypes.setRenderLayer(
              ModBlocks.FIRE_EXTINGUISHER_COPPER.get(), RenderType.cutoutMipped());

          ItemBlockRenderTypes.setRenderLayer(
              ModBlocks.FIRE_SPRINKLER.get(), RenderType.cutoutMipped());
          ItemBlockRenderTypes.setRenderLayer(
              ModBlocks.FIRE_ALARM_SWITCH.get(), RenderType.cutoutMipped());
          ItemBlockRenderTypes.setRenderLayer(
              ModBlocks.FIRE_ALARM_BELL.get(), RenderType.cutoutMipped());
          ItemBlockRenderTypes.setRenderLayer(
              ModBlocks.FIRE_ALARM_SIREN.get(), RenderType.cutoutMipped());
          ItemBlockRenderTypes.setRenderLayer(
              ModBlocks.FIRE_ALARM_SMOKE_DETECTOR.get(), RenderType.cutoutMipped());
          ItemBlockRenderTypes.setRenderLayer(
              ModBlocks.FIRE_ALARM_SMOKE_DETECTOR_SILENT.get(), RenderType.cutoutMipped());

          ItemBlockRenderTypes.setRenderLayer(ModBlocks.EXIT_SIGN.get(), RenderType.cutoutMipped());
          ItemBlockRenderTypes.setRenderLayer(
              ModBlocks.EXIT_SIGN_LEFT.get(), RenderType.cutoutMipped());
          ItemBlockRenderTypes.setRenderLayer(
              ModBlocks.EXIT_SIGN_LEFT_DOWN.get(), RenderType.cutoutMipped());
          ItemBlockRenderTypes.setRenderLayer(
              ModBlocks.EXIT_SIGN_LEFT_UP.get(), RenderType.cutoutMipped());
          ItemBlockRenderTypes.setRenderLayer(
              ModBlocks.EXIT_SIGN_RIGHT.get(), RenderType.cutoutMipped());
          ItemBlockRenderTypes.setRenderLayer(
              ModBlocks.EXIT_SIGN_RIGHT_DOWN.get(), RenderType.cutoutMipped());
          ItemBlockRenderTypes.setRenderLayer(
              ModBlocks.EXIT_SIGN_RIGHT_UP.get(), RenderType.cutoutMipped());
        });
  }
}
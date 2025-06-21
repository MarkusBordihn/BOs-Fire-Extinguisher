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

import de.markusbordihn.fireextinguisher.block.ExitSignBlocks;
import de.markusbordihn.fireextinguisher.block.FireAlarmBlocks;
import de.markusbordihn.fireextinguisher.block.FireExtinguisherBlocks;
import de.markusbordihn.fireextinguisher.tabs.ModTabs;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FireExtinguisherClient implements ClientModInitializer {

  protected static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  @Override
  public void onInitializeClient() {
    // Use Fabric to bootstrap the Common mod.
    log.info("Initializing {} (Fabric-Client) ...", Constants.MOD_NAME);

    log.info("{} Tabs ...", Constants.LOG_REGISTER_PREFIX);
    ModTabs.registerModTabs();

    log.info("{} Block Layers ...", Constants.LOG_REGISTER_PREFIX);
    BlockRenderLayerMap.putBlock(
        FireExtinguisherBlocks.FIRE_EXTINGUISHER, ChunkSectionLayer.CUTOUT_MIPPED);
    BlockRenderLayerMap.putBlock(
        FireExtinguisherBlocks.FIRE_EXTINGUISHER_COPPER, ChunkSectionLayer.CUTOUT_MIPPED);

    BlockRenderLayerMap.putBlock(FireAlarmBlocks.FIRE_SPRINKLER, ChunkSectionLayer.CUTOUT_MIPPED);
    BlockRenderLayerMap.putBlock(
        FireAlarmBlocks.FIRE_ALARM_SWITCH, ChunkSectionLayer.CUTOUT_MIPPED);
    BlockRenderLayerMap.putBlock(FireAlarmBlocks.FIRE_ALARM_BELL, ChunkSectionLayer.CUTOUT_MIPPED);
    BlockRenderLayerMap.putBlock(FireAlarmBlocks.FIRE_ALARM_SIREN, ChunkSectionLayer.CUTOUT_MIPPED);
    BlockRenderLayerMap.putBlock(
        FireAlarmBlocks.FIRE_ALARM_SMOKE_DETECTOR, ChunkSectionLayer.CUTOUT_MIPPED);
    BlockRenderLayerMap.putBlock(
        FireAlarmBlocks.FIRE_ALARM_SMOKE_DETECTOR_SILENT, ChunkSectionLayer.CUTOUT_MIPPED);

    BlockRenderLayerMap.putBlock(ExitSignBlocks.EXIT_SIGN, ChunkSectionLayer.CUTOUT_MIPPED);
    BlockRenderLayerMap.putBlock(ExitSignBlocks.EXIT_SIGN_LEFT, ChunkSectionLayer.CUTOUT_MIPPED);
    BlockRenderLayerMap.putBlock(
        ExitSignBlocks.EXIT_SIGN_LEFT_DOWN, ChunkSectionLayer.CUTOUT_MIPPED);
    BlockRenderLayerMap.putBlock(ExitSignBlocks.EXIT_SIGN_LEFT_UP, ChunkSectionLayer.CUTOUT_MIPPED);
    BlockRenderLayerMap.putBlock(ExitSignBlocks.EXIT_SIGN_RIGHT, ChunkSectionLayer.CUTOUT_MIPPED);
    BlockRenderLayerMap.putBlock(
        ExitSignBlocks.EXIT_SIGN_RIGHT_DOWN, ChunkSectionLayer.CUTOUT_MIPPED);
    BlockRenderLayerMap.putBlock(
        ExitSignBlocks.EXIT_SIGN_RIGHT_UP, ChunkSectionLayer.CUTOUT_MIPPED);
  }
}

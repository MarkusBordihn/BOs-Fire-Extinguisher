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
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.renderer.RenderType;

public class FireExtinguisherClient implements ClientModInitializer {

  @Override
  public void onInitializeClient() {
    // Use Fabric to bootstrap the Common mod.
    Constants.LOG.info("Initializing {} (Fabric-Client) ...", Constants.MOD_NAME);
    CommonClass.init();

    Constants.LOG.info("{} Tabs ...", Constants.LOG_REGISTER_PREFIX);
    ModTabs.registerModTabs();

    Constants.LOG.info("{} Block Layers ...", Constants.LOG_REGISTER_PREFIX);
    BlockRenderLayerMap.INSTANCE.putBlock(
        FireExtinguisherBlocks.FIRE_EXTINGUISHER, RenderType.cutoutMipped());
    BlockRenderLayerMap.INSTANCE.putBlock(
        FireExtinguisherBlocks.FIRE_EXTINGUISHER_COPPER, RenderType.cutoutMipped());

    BlockRenderLayerMap.INSTANCE.putBlock(
        FireAlarmBlocks.FIRE_SPRINKLER, RenderType.cutoutMipped());
    BlockRenderLayerMap.INSTANCE.putBlock(
        FireAlarmBlocks.FIRE_ALARM_SWITCH, RenderType.cutoutMipped());
    BlockRenderLayerMap.INSTANCE.putBlock(
        FireAlarmBlocks.FIRE_ALARM_BELL, RenderType.cutoutMipped());
    BlockRenderLayerMap.INSTANCE.putBlock(
        FireAlarmBlocks.FIRE_ALARM_SIREN, RenderType.cutoutMipped());
    BlockRenderLayerMap.INSTANCE.putBlock(
        FireAlarmBlocks.FIRE_ALARM_SMOKE_DETECTOR, RenderType.cutoutMipped());
    BlockRenderLayerMap.INSTANCE.putBlock(
        FireAlarmBlocks.FIRE_ALARM_SMOKE_DETECTOR_SILENT, RenderType.cutoutMipped());

    BlockRenderLayerMap.INSTANCE.putBlock(ExitSignBlocks.EXIT_SIGN, RenderType.cutoutMipped());
    BlockRenderLayerMap.INSTANCE.putBlock(ExitSignBlocks.EXIT_SIGN_LEFT, RenderType.cutoutMipped());
    BlockRenderLayerMap.INSTANCE.putBlock(
        ExitSignBlocks.EXIT_SIGN_LEFT_DOWN, RenderType.cutoutMipped());
    BlockRenderLayerMap.INSTANCE.putBlock(
        ExitSignBlocks.EXIT_SIGN_LEFT_UP, RenderType.cutoutMipped());
    BlockRenderLayerMap.INSTANCE.putBlock(
        ExitSignBlocks.EXIT_SIGN_RIGHT, RenderType.cutoutMipped());
    BlockRenderLayerMap.INSTANCE.putBlock(
        ExitSignBlocks.EXIT_SIGN_RIGHT_DOWN, RenderType.cutoutMipped());
    BlockRenderLayerMap.INSTANCE.putBlock(
        ExitSignBlocks.EXIT_SIGN_RIGHT_UP, RenderType.cutoutMipped());

    // Some code like events require special initialization from the
    // loader specific code.
    ItemTooltipCallback.EVENT.register(CommonClass::onItemTooltip);
  }
}

/*
 * Copyright 2026 Markus Bordihn
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

package de.markusbordihn.fireextinguisher.alarm;

import de.markusbordihn.fireextinguisher.block.AbstractFireAlarmSignalBlock;
import de.markusbordihn.fireextinguisher.block.FireAlarmControlPanelBlock;
import de.markusbordihn.fireextinguisher.block.FireAlarmSwitchBlock;
import de.markusbordihn.fireextinguisher.config.FireExtinguisherConfig;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class FireAlarmNetwork {

  private static final Map<ResourceKey<Level>, Set<BlockPos>> activePanels = new HashMap<>();
  private static final Map<ResourceKey<Level>, Set<BlockPos>> activeSources = new HashMap<>();
  private static WeakReference<MinecraftServer> knownServer = new WeakReference<>(null);

  private FireAlarmNetwork() {}

  private static void resetIfServerChanged(ServerLevel serverLevel) {
    MinecraftServer server = serverLevel.getServer();
    if (knownServer.get() != server) {
      knownServer = new WeakReference<>(server);
      activePanels.clear();
      activeSources.clear();
    }
  }

  private static Set<BlockPos> positions(
      Map<ResourceKey<Level>, Set<BlockPos>> registry, ServerLevel serverLevel) {
    return registry.computeIfAbsent(serverLevel.dimension(), dimension -> new HashSet<>());
  }

  public static boolean isWithinPanelRadius(BlockPos panelPos, BlockPos blockPos) {
    return Math.abs(panelPos.getX() - blockPos.getX())
            <= FireExtinguisherConfig.fireAlarmControlPanelRadiusX
        && Math.abs(panelPos.getY() - blockPos.getY())
            <= FireExtinguisherConfig.fireAlarmControlPanelRadiusY
        && Math.abs(panelPos.getZ() - blockPos.getZ())
            <= FireExtinguisherConfig.fireAlarmControlPanelRadiusZ;
  }

  public static List<BlockPos> findSignalBlocksAround(ServerLevel serverLevel, BlockPos center) {
    return FireAlarmAreaScanner.findLoadedBlocks(
        serverLevel,
        center,
        FireExtinguisherConfig.fireAlarmControlPanelRadiusX,
        FireExtinguisherConfig.fireAlarmControlPanelRadiusY,
        FireExtinguisherConfig.fireAlarmControlPanelRadiusZ,
        blockState -> blockState.getBlock() instanceof AbstractFireAlarmSignalBlock);
  }

  public static boolean activatePanel(ServerLevel serverLevel, BlockPos blockPos) {
    resetIfServerChanged(serverLevel);
    return positions(activePanels, serverLevel).add(blockPos.immutable());
  }

  public static void deactivatePanel(ServerLevel serverLevel, BlockPos blockPos) {
    resetIfServerChanged(serverLevel);
    positions(activePanels, serverLevel).remove(blockPos);
  }

  public static boolean activateSource(ServerLevel serverLevel, BlockPos blockPos) {
    resetIfServerChanged(serverLevel);
    if (!positions(activeSources, serverLevel).add(blockPos.immutable())) {
      return false;
    }

    for (BlockPos panelPos : findSignalBlocksAround(serverLevel, blockPos)) {
      BlockState panelState = serverLevel.getBlockState(panelPos);
      if (panelState.getBlock() instanceof FireAlarmControlPanelBlock controlPanel
          && !controlPanel.isPowered(panelState)) {
        controlPanel.setPowered(serverLevel, panelPos, panelState, true);
      }
    }
    return true;
  }

  public static void deactivateSource(ServerLevel serverLevel, BlockPos blockPos) {
    resetIfServerChanged(serverLevel);
    if (!positions(activeSources, serverLevel).remove(blockPos)) {
      return;
    }

    for (BlockPos panelPos : List.copyOf(positions(activePanels, serverLevel))) {
      if (isWithinPanelRadius(panelPos, blockPos) && serverLevel.isLoaded(panelPos)) {
        BlockState panelState = serverLevel.getBlockState(panelPos);
        if (panelState.getBlock() instanceof FireAlarmControlPanelBlock controlPanel) {
          controlPanel.reevaluate(serverLevel, panelPos);
        }
      }
    }
  }

  public static void releaseSwitchesAround(ServerLevel serverLevel, BlockPos panelPos) {
    List<BlockPos> switchPositions =
        FireAlarmAreaScanner.findLoadedBlocks(
            serverLevel,
            panelPos,
            FireExtinguisherConfig.fireAlarmControlPanelRadiusX,
            FireExtinguisherConfig.fireAlarmControlPanelRadiusY,
            FireExtinguisherConfig.fireAlarmControlPanelRadiusZ,
            blockState -> blockState.getBlock() instanceof FireAlarmSwitchBlock);
    for (BlockPos switchPos : switchPositions) {
      BlockState switchState = serverLevel.getBlockState(switchPos);
      if (switchState.getBlock() instanceof FireAlarmSwitchBlock alarmSwitch) {
        alarmSwitch.release(serverLevel, switchPos, switchState);
      }
    }
  }

  public static boolean isCoveredByActivePanel(ServerLevel serverLevel, BlockPos blockPos) {
    resetIfServerChanged(serverLevel);
    return hasActiveEntryWithin(
        positions(activePanels, serverLevel),
        serverLevel,
        blockPos,
        blockState -> blockState.getBlock() instanceof FireAlarmControlPanelBlock);
  }

  public static boolean hasActiveSourceWithin(ServerLevel serverLevel, BlockPos panelPos) {
    resetIfServerChanged(serverLevel);
    return hasActiveEntryWithin(
        positions(activeSources, serverLevel),
        serverLevel,
        panelPos,
        blockState -> blockState.hasProperty(BlockStateProperties.POWERED));
  }

  private static boolean hasActiveEntryWithin(
      Set<BlockPos> entries,
      ServerLevel serverLevel,
      BlockPos blockPos,
      Predicate<BlockState> expectedBlock) {
    Iterator<BlockPos> iterator = entries.iterator();
    while (iterator.hasNext()) {
      BlockPos entryPos = iterator.next();
      if (!isWithinPanelRadius(entryPos, blockPos)) {
        continue;
      }
      if (!serverLevel.isLoaded(entryPos)) {
        return true;
      }
      BlockState entryState = serverLevel.getBlockState(entryPos);
      if (expectedBlock.test(entryState) && entryState.getValue(BlockStateProperties.POWERED)) {
        return true;
      }
      iterator.remove();
    }
    return false;
  }
}

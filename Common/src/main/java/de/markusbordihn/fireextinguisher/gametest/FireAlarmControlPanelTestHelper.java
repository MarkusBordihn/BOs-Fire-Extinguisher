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

package de.markusbordihn.fireextinguisher.gametest;

import de.markusbordihn.fireextinguisher.block.AbstractFireAlarmSignalBlock;
import de.markusbordihn.fireextinguisher.config.FireExtinguisherConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;

public class FireAlarmControlPanelTestHelper {

  public static final int TIMEOUT_TICKS = 300;
  public static final String BATCH = "fireAlarmControlPanel";
  private static final int ISOLATED_PANEL_RADIUS = 1;
  private static final BlockPos PANEL_POS = new BlockPos(1, 2, 1);
  private static final BlockPos BELL_POS = new BlockPos(0, 2, 1);
  private static final BlockPos REDSTONE_POS = new BlockPos(2, 2, 1);
  private static final BlockPos DETECTOR_POS = new BlockPos(1, 1, 2);
  private static final BlockPos FIRE_POS = new BlockPos(2, 1, 2);
  private static final BlockPos OUT_OF_RANGE_PANEL_POS = new BlockPos(0, 2, 1);
  private static final BlockPos OUT_OF_RANGE_BELL_POS = new BlockPos(2, 2, 1);
  private static final BlockPos OUT_OF_RANGE_REDSTONE_POS = new BlockPos(0, 2, 0);

  private static boolean panelRadiusIsolated = false;
  private static int defaultRadiusX;
  private static int defaultRadiusY;
  private static int defaultRadiusZ;

  private FireAlarmControlPanelTestHelper() {}

  public static void testRedstonePowersAlarmsInRange(
      GameTestHelper helper, Block controlPanel, Block alarmBell) {
    isolatePanelFromNeighbourTests();
    placePanelAndBell(helper, controlPanel, alarmBell);
    helper.setBlock(REDSTONE_POS, Blocks.REDSTONE_BLOCK);
    helper
        .startSequence()
        .thenExecuteAfter(10, () -> assertPanelAndBellPowered(helper, true))
        .thenExecuteAfter(30, () -> helper.destroyBlock(REDSTONE_POS))
        .thenExecuteAfter(20, () -> assertPanelAndBellPowered(helper, false))
        .thenExecuteAfter(190, () -> assertPanelAndBellPowered(helper, false))
        .thenSucceed();
  }

  public static void testSmokeDetectorTriggersPanelWithoutWiring(
      GameTestHelper helper, Block controlPanel, Block alarmBell, Block smokeDetector) {
    isolatePanelFromNeighbourTests();
    placePanelAndBell(helper, controlPanel, alarmBell);
    helper.setBlock(DETECTOR_POS.below(), Blocks.STONE);
    helper.setBlock(FIRE_POS.below(), Blocks.NETHERRACK);
    helper.setBlock(
        DETECTOR_POS,
        smokeDetector
            .defaultBlockState()
            .setValue(FaceAttachedHorizontalDirectionalBlock.FACE, AttachFace.FLOOR));
    helper.setBlock(FIRE_POS, Blocks.FIRE);
    helper
        .startSequence()
        .thenExecuteAfter(
            20,
            () -> {
              assertPowered(helper, DETECTOR_POS, true);
              assertPanelAndBellPowered(helper, true);
            })
        .thenExecuteAfter(10, () -> helper.setBlock(FIRE_POS, Blocks.AIR))
        .thenExecuteAfter(
            170,
            () -> {
              assertPowered(helper, DETECTOR_POS, false);
              assertPanelAndBellPowered(helper, false);
            })
        .thenSucceed();
  }

  public static void testDoesNotPowerAlarmsOutOfRange(
      GameTestHelper helper, Block controlPanel, Block alarmBell) {
    isolatePanelFromNeighbourTests();
    helper.setBlock(OUT_OF_RANGE_PANEL_POS.south(), Blocks.STONE);
    helper.setBlock(OUT_OF_RANGE_BELL_POS.south(), Blocks.STONE);
    helper.setBlock(OUT_OF_RANGE_PANEL_POS, wallMountedFacingNorth(controlPanel));
    helper.setBlock(OUT_OF_RANGE_BELL_POS, wallMountedFacingNorth(alarmBell));
    assertPowered(helper, OUT_OF_RANGE_PANEL_POS, false);
    assertPowered(helper, OUT_OF_RANGE_BELL_POS, false);
    helper.setBlock(OUT_OF_RANGE_REDSTONE_POS, Blocks.REDSTONE_BLOCK);
    helper
        .startSequence()
        .thenExecuteAfter(
            20,
            () -> {
              assertPowered(helper, OUT_OF_RANGE_PANEL_POS, true);
              assertPowered(helper, OUT_OF_RANGE_BELL_POS, false);
            })
        .thenExecuteAfter(60, () -> assertPowered(helper, OUT_OF_RANGE_BELL_POS, false))
        .thenSucceed();
  }

  private static void isolatePanelFromNeighbourTests() {
    if (!panelRadiusIsolated) {
      defaultRadiusX = FireExtinguisherConfig.fireAlarmControlPanelRadiusX;
      defaultRadiusY = FireExtinguisherConfig.fireAlarmControlPanelRadiusY;
      defaultRadiusZ = FireExtinguisherConfig.fireAlarmControlPanelRadiusZ;
      panelRadiusIsolated = true;
    }
    FireExtinguisherConfig.fireAlarmControlPanelRadiusX = ISOLATED_PANEL_RADIUS;
    FireExtinguisherConfig.fireAlarmControlPanelRadiusY = ISOLATED_PANEL_RADIUS;
    FireExtinguisherConfig.fireAlarmControlPanelRadiusZ = ISOLATED_PANEL_RADIUS;
  }

  public static void restoreConfiguredPanelRadius() {
    if (!panelRadiusIsolated) {
      return;
    }
    FireExtinguisherConfig.fireAlarmControlPanelRadiusX = defaultRadiusX;
    FireExtinguisherConfig.fireAlarmControlPanelRadiusY = defaultRadiusY;
    FireExtinguisherConfig.fireAlarmControlPanelRadiusZ = defaultRadiusZ;
    panelRadiusIsolated = false;
  }

  private static void placePanelAndBell(
      GameTestHelper helper, Block controlPanel, Block alarmBell) {
    helper.setBlock(PANEL_POS.south(), Blocks.STONE);
    helper.setBlock(BELL_POS.south(), Blocks.STONE);
    helper.setBlock(PANEL_POS, wallMountedFacingNorth(controlPanel));
    helper.setBlock(BELL_POS, wallMountedFacingNorth(alarmBell));
    assertPanelAndBellPowered(helper, false);
  }

  private static BlockState wallMountedFacingNorth(Block block) {
    return block
        .defaultBlockState()
        .setValue(FaceAttachedHorizontalDirectionalBlock.FACE, AttachFace.WALL)
        .setValue(FaceAttachedHorizontalDirectionalBlock.FACING, Direction.NORTH);
  }

  private static void assertPanelAndBellPowered(GameTestHelper helper, boolean powered) {
    assertPowered(helper, PANEL_POS, powered);
    assertPowered(helper, BELL_POS, powered);
  }

  private static void assertPowered(GameTestHelper helper, BlockPos blockPos, boolean powered) {
    helper.assertBlockProperty(blockPos, AbstractFireAlarmSignalBlock.POWERED, powered);
  }
}

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

package de.markusbordihn.fireextinguisher.gametest;

import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class FireAlarmSignalTestHelper {

  public static final int TIMEOUT_TICKS = 100;
  private static final BlockPos SIGNAL_POS = new BlockPos(1, 2, 1);
  private static final BlockPos REDSTONE_POS = new BlockPos(2, 2, 1);
  private static final BlockPos LAMP_POS = new BlockPos(0, 2, 1);

  private FireAlarmSignalTestHelper() {}

  public static void testFollowsRedstoneSignal(GameTestHelper helper, Block signalBlock) {
    placeWallMounted(helper, signalBlock);
    assertPowered(helper, false);
    helper.setBlock(REDSTONE_POS, Blocks.REDSTONE_BLOCK);
    helper
        .startSequence()
        .thenExecuteAfter(5, () -> assertPowered(helper, true))
        .thenExecute(() -> helper.destroyBlock(REDSTONE_POS))
        .thenExecuteAfter(10, () -> assertPowered(helper, false))
        .thenSucceed();
  }

  public static void testForwardsRedstoneSignal(GameTestHelper helper, Block signalBlock) {
    placeWallMounted(helper, signalBlock);
    helper.setBlock(LAMP_POS, Blocks.REDSTONE_LAMP);
    helper.setBlock(REDSTONE_POS, Blocks.REDSTONE_BLOCK);
    helper
        .startSequence()
        .thenExecuteAfter(
            5,
            () -> {
              assertPowered(helper, true);
              helper.assertBlockProperty(LAMP_POS, RedstoneLampBlock.LIT, true);
            })
        .thenExecute(() -> helper.destroyBlock(REDSTONE_POS))
        .thenExecuteAfter(
            10,
            () -> {
              assertPowered(helper, false);
              helper.assertBlockProperty(LAMP_POS, RedstoneLampBlock.LIT, false);
            })
        .thenSucceed();
  }

  public static void testSwitchTogglesRedstoneSignal(GameTestHelper helper, Block alarmSwitch) {
    placeWallMounted(helper, alarmSwitch);
    helper.setBlock(LAMP_POS, Blocks.REDSTONE_LAMP);
    Player player = helper.makeMockPlayer();
    helper
        .startSequence()
        .thenExecute(() -> helper.useBlock(SIGNAL_POS, player))
        .thenExecuteAfter(
            5,
            () -> {
              assertPowered(helper, true);
              helper.assertBlockProperty(LAMP_POS, RedstoneLampBlock.LIT, true);
            })
        .thenExecute(() -> helper.useBlock(SIGNAL_POS, player))
        .thenExecuteAfter(
            10,
            () -> {
              assertPowered(helper, false);
              helper.assertBlockProperty(LAMP_POS, RedstoneLampBlock.LIT, false);
            })
        .thenSucceed();
  }

  private static void placeWallMounted(GameTestHelper helper, Block block) {
    helper.setBlock(SIGNAL_POS.south(), Blocks.STONE);
    helper.setBlock(SIGNAL_POS, GameTestHelpers.wallMountedFacingNorth(block));
  }

  private static void assertPowered(GameTestHelper helper, boolean powered) {
    helper.assertBlockProperty(SIGNAL_POS, BlockStateProperties.POWERED, powered);
  }
}

/**
 * Copyright 2022 Markus Bordihn
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

package de.markusbordihn.fireextinguisher.item;

import java.util.List;
import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import de.markusbordihn.fireextinguisher.Constants;
import de.markusbordihn.fireextinguisher.config.CommonConfig;

public class FireAxeItem extends AxeItem {

  protected static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  public static final String NAME = "fire_axe";

  private static final CommonConfig.Config COMMON = CommonConfig.COMMON;

  public FireAxeItem(Tier tier, float attackBase, float attackSpeed, Properties properties) {
    super(tier, attackBase, attackSpeed, properties);
  }

  public static void stopFire(Level level, Player player, InteractionHand hand, BlockPos targetBlockPos,
      ItemStack itemStack) {
    Iterable<BlockPos> blockPositions = BlockPos.withinManhattan(targetBlockPos.above(),
        COMMON.fireAxtRadius.get(), COMMON.fireAxtRadius.get(), COMMON.fireAxtRadius.get());
    boolean hasStoppedFire = false;

    for (BlockPos blockPos : blockPositions) {
      BlockState blockState = level.getBlockState(blockPos);
      if (blockState.is(Blocks.FIRE)) {

        // Remove block on server only.
        if (!level.isClientSide) {
          log.debug("[FireAxe] Removing Fire Block {} at {}", blockState, blockPos);
          level.removeBlock(blockPos, false);
        }

        // Play fire extinguish sound on the client.
        if (level.isClientSide) {
          stopFireSound(level, player);
        }

        hasStoppedFire = true;
      }
    }
    if (hasStoppedFire) {
      hurtAndBreak(level, itemStack, player, hand);
    }
  }

  public static void stopFireSound(Level level, Player player) {
    if (level.isClientSide) {
      player.playSound(SoundEvents.FIRE_EXTINGUISH, 1.0F, 1.0F);
    }
  }

  public static void hurtAndBreak(Level level, ItemStack itemStack, Player player, InteractionHand hand) {
    if (!level.isClientSide) {
      itemStack.hurtAndBreak(1, player, serverPlayer -> serverPlayer.broadcastBreakEvent(hand));
    }
  }

  @Override
  public InteractionResult useOn(UseOnContext context) {
    Level level = context.getLevel();
    BlockPos blockPos = context.getClickedPos();
    Player player = context.getPlayer();
    ItemStack itemStack = context.getItemInHand();
    InteractionHand interactionHand = context.getHand();

    stopFire(level, player, interactionHand, blockPos, itemStack);

    return InteractionResult.sidedSuccess(context.getLevel().isClientSide());
  }

  @Override
  public boolean mineBlock(ItemStack itemStack, Level level, BlockState blockState,
      BlockPos blockPos, LivingEntity livingEntity) {
    if (livingEntity instanceof Player player) {
      stopFire(level, player, player.getUsedItemHand(), blockPos, itemStack);
    }
    return super.mineBlock(itemStack, level, blockState, blockPos, livingEntity);
  }

  @Override
  public boolean isFireResistant() {
    return true;
  }

  @Override
  public void appendHoverText(ItemStack itemStack, @Nullable Level level,
      List<Component> tooltipList, TooltipFlag tooltipFlag) {
    tooltipList.add(Component.translatable(Constants.TEXT_PREFIX + NAME + "_description",
        COMMON.fireAxtRadius.get()));
    tooltipList.add(Component.translatable(Constants.TEXT_PREFIX + NAME + "_use")
        .withStyle(ChatFormatting.GREEN));
  }

}

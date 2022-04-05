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

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;

import de.markusbordihn.fireextinguisher.Constants;
import de.markusbordihn.fireextinguisher.config.CommonConfig;

@EventBusSubscriber
public class FireAxeItem extends AxeItem {

  protected static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  public static final String NAME = "fire_axe";

  private static final CommonConfig.Config COMMON = CommonConfig.COMMON;

  private static int fireAxtRadius = COMMON.fireAxtRadius.get();

  public FireAxeItem(IItemTier tier, float attackBase, float attackSpeed, Properties properties) {
    super(tier, attackBase, attackSpeed, properties);
  }

  @SubscribeEvent
  public static void handleServerAboutToStartEvent(FMLServerAboutToStartEvent event) {
    fireAxtRadius = COMMON.fireAxtRadius.get();
  }

  public void stopFire(World level, PlayerEntity player, Hand hand, BlockPos targetBlockPos,
      ItemStack itemStack) {
    Iterable<BlockPos> blockPositions = BlockPos.withinManhattan(targetBlockPos.above(),
        fireAxtRadius, fireAxtRadius, fireAxtRadius);
    boolean hasStoppedFire = false;
    for (BlockPos blockPos : blockPositions) {
      BlockState blockState = level.getBlockState(blockPos);
      if (blockState.is(Blocks.FIRE)) {
        // Remove block on server and client
        level.removeBlock(blockPos, false);

        // Play fire extinguish sound on the client
        if (level.isClientSide) {
          player.playSound(SoundEvents.FIRE_EXTINGUISH, 1.0F, 1.0F);
        }
        hasStoppedFire = true;
      }
    }
    if (!level.isClientSide && hasStoppedFire) {
      itemStack.hurtAndBreak(1, player, serverPlayer -> {
        serverPlayer.broadcastBreakEvent(hand);
      });
    }
  }

  @Override
  public boolean mineBlock(ItemStack itemStack, World level, BlockState blockState,
      BlockPos blockPos, LivingEntity livingEntity) {
    if (livingEntity instanceof PlayerEntity) {
      PlayerEntity player = (PlayerEntity) livingEntity;
      stopFire(level, player, player.getUsedItemHand(), blockPos, itemStack);
    }
    return super.mineBlock(itemStack, level, blockState, blockPos, livingEntity);
  }

  @Override
  public boolean isFireResistant() {
    return true;
  }

  @OnlyIn(Dist.CLIENT)
  @Override
  public void appendHoverText(ItemStack itemStack, @Nullable World level,
      List<ITextComponent> tooltipList, ITooltipFlag tooltipFlag) {
    tooltipList.add(new TranslationTextComponent(Constants.TEXT_PREFIX + NAME + "_description",
        fireAxtRadius));
    tooltipList.add(new TranslationTextComponent(Constants.TEXT_PREFIX + NAME + "_use")
        .withStyle(TextFormatting.GREEN));
  }

}

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

package de.markusbordihn.fireextinguisher.item;

import de.markusbordihn.fireextinguisher.Constants;
import de.markusbordihn.fireextinguisher.config.FireExtinguisherConfig;
import de.markusbordihn.fireextinguisher.utils.ToolTips;
import java.util.function.Consumer;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FireAxeItem extends AxeItem {

  public static final String ID = "fire_axe";

  protected static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  public FireAxeItem() {
    this(
        ToolMaterial.IRON,
        new Item.Properties()
            .setId(
                ResourceKey.create(
                    Registries.ITEM, Identifier.fromNamespaceAndPath(Constants.MOD_ID, ID)))
            .fireResistant());
  }

  public FireAxeItem(ToolMaterial tier, Properties properties) {
    super(tier, 6.0F, -3.2F, properties);
  }

  public static void stopFire(
      Level level,
      Player player,
      InteractionHand hand,
      BlockPos targetBlockPos,
      ItemStack itemStack) {
    Iterable<BlockPos> blockPositions =
        BlockPos.withinManhattan(
            targetBlockPos.above(),
            FireExtinguisherConfig.fireAxtRadius,
            FireExtinguisherConfig.fireAxtRadius,
            FireExtinguisherConfig.fireAxtRadius);
    boolean hasStoppedFire = false;
    for (BlockPos blockPos : blockPositions) {
      BlockState blockState = level.getBlockState(blockPos);
      if (blockState.is(Blocks.FIRE)) {

        // Remove block on client and server.
        log.debug("[Fire Axt] Removing Fire Block {} at {}", blockState, blockPos);
        level.removeBlock(blockPos, false);

        // Play fire extinguish sound on the client
        stopFireSound(level, player);

        hasStoppedFire = true;
      } else if (blockState.is(Blocks.CAMPFIRE)
          && blockState.getBlock() instanceof CampfireBlock
          && CampfireBlock.isLitCampfire(blockState)) {

        // Remove block on client and server.
        log.debug("[Fire Axt] Extinguishing Campfire Block {} at {}", blockState, blockPos);
        level.setBlockAndUpdate(blockPos, blockState.setValue(CampfireBlock.LIT, false));

        // Play fire extinguish sound on the client
        stopFireSound(level, player);

        hasStoppedFire = true;
      }
    }
    if (hasStoppedFire) {
      hurtAndBreak(level, itemStack, player, hand);
    }
  }

  public static void stopFireSound(Level level, Player player) {
    if (level.isClientSide()) {
      player.playSound(SoundEvents.FIRE_EXTINGUISH, 1.0F, 1.0F);
    }
  }

  public static void hurtAndBreak(
      Level level, ItemStack itemStack, Player player, InteractionHand interactionHand) {
    if (!level.isClientSide()) {
      itemStack.hurtAndBreak(
          1,
          player,
          interactionHand == InteractionHand.MAIN_HAND
              ? EquipmentSlot.MAINHAND
              : EquipmentSlot.OFFHAND);
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

    return context.getLevel().isClientSide()
        ? InteractionResult.SUCCESS
        : InteractionResult.CONSUME;
  }

  @Override
  public boolean mineBlock(
      ItemStack itemStack,
      Level level,
      BlockState blockState,
      BlockPos blockPos,
      LivingEntity livingEntity) {
    if (livingEntity instanceof Player player) {
      stopFire(level, player, player.getUsedItemHand(), blockPos, itemStack);
    }
    return super.mineBlock(itemStack, level, blockState, blockPos, livingEntity);
  }

  @Override
  public void appendHoverText(
      ItemStack itemStack,
      TooltipContext tooltipContext,
      TooltipDisplay tooltipDisplay,
      Consumer<Component> tooltipConsumer,
      TooltipFlag tooltipFlag) {
    ToolTips.addTooltip(
        tooltipConsumer,
        Component.translatable(
                Constants.TEXT_PREFIX + ID + "_description", FireExtinguisherConfig.fireAxtRadius)
            .withStyle(ChatFormatting.GRAY));
    ToolTips.addTooltip(
        tooltipConsumer,
        Component.translatable(Constants.TEXT_PREFIX + ID + "_use")
            .withStyle(ChatFormatting.GREEN));
  }
}

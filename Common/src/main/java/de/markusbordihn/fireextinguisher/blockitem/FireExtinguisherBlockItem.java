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

package de.markusbordihn.fireextinguisher.blockitem;

import de.markusbordihn.fireextinguisher.Constants;
import de.markusbordihn.fireextinguisher.block.FireExtinguisherBlock;
import de.markusbordihn.fireextinguisher.config.CommonConfig;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Vanishable;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;

public class FireExtinguisherBlockItem extends BlockItem implements Vanishable {

  public static final String NAME = "fire_extinguisher";
  private static final CommonConfig.Config COMMON = CommonConfig.COMMON;
  private static final double X_SHIFT = 0.0;
  private static final double Y_SHIFT = 1.6;
  private static final double Z_SHIFT = 0.0;
  private static final int PARTICLE_FRAMES = 8;
  private static final int ATTACK_EFFECT_DURATION = 200;

  public FireExtinguisherBlockItem(Block block) {
    this(block, new Properties().stacksTo(1).durability(128));
  }

  public FireExtinguisherBlockItem(Block block, Properties properties) {
    super(block, properties);
  }

  public static void stopFireAnimation(Player player, Level level, BlockPos blockPos) {
    if (!level.isClientSide) {
      return;
    }

    // Calculate source and target position
    double x = player.getX();
    double y = player.getY();
    double z = player.getZ();

    // Correct position to item position.
    x = (x >= 0) ? x + X_SHIFT : x - X_SHIFT;
    y = (y >= 0) ? y + Y_SHIFT : y - Y_SHIFT;
    z = (z >= 0) ? z + Z_SHIFT : z - Z_SHIFT;

    // Show particle in targeted block direction.
    if (blockPos != null) {
      double targetX = blockPos.getX() + 0.5;
      double targetY = blockPos.getY() + 0.5;
      double targetZ = blockPos.getZ() + 0.5;
      double targetXRatio = (targetX - x) / PARTICLE_FRAMES;
      double targetYRatio = (targetY - y) / PARTICLE_FRAMES;
      double targetZRatio = (targetZ - z) / PARTICLE_FRAMES;
      for (int i = 0; i < PARTICLE_FRAMES; ++i) {
        x += targetXRatio;
        y += targetYRatio;
        z += targetZRatio;
        level.addParticle(ParticleTypes.CLOUD, x, y, z, 0D, 0D, 0D);
      }
    }
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
            COMMON.fireExtinguisherRadius.get(),
            COMMON.fireExtinguisherRadius.get(),
            COMMON.fireExtinguisherRadius.get());
    boolean hasStoppedFire = false;
    for (BlockPos blockPos : blockPositions) {
      BlockState blockState = level.getBlockState(blockPos);

      if (blockState.is(Blocks.FIRE)) {
        level.removeBlock(blockPos, false);
        hasStoppedFire = true;
        break;
      } else if (blockState.is(Blocks.CAMPFIRE)
          && blockState.getBlock() instanceof CampfireBlock
          && Boolean.TRUE.equals(blockState.getValue(CampfireBlock.LIT))) {
        level.setBlockAndUpdate(blockPos, blockState.setValue(CampfireBlock.LIT, false));
        hasStoppedFire = true;
        break;
      }
    }
    if (hasStoppedFire) {
      stopFireSound(level, player);
      hurtAndBreak(level, itemStack, player, hand);
    }
  }

  public static void stopFireSound(Level level, Player player) {
    if (level.isClientSide) {
      player.playSound(SoundEvents.FIRE_EXTINGUISH, 1.0F, 1.0F);
    }
  }

  public static void hurtAndBreak(
      Level level, ItemStack itemStack, Player player, InteractionHand hand) {
    if (!level.isClientSide) {
      itemStack.hurtAndBreak(1, player, serverPlayer -> serverPlayer.broadcastBreakEvent(hand));
    }
  }

  @Override
  public boolean canAttackBlock(
      BlockState blockState, Level level, BlockPos blockPos, Player player) {
    if (blockState.getBlock() instanceof FireExtinguisherBlock) {
      return true;
    }
    return player.isShiftKeyDown();
  }

  @Override
  public InteractionResult useOn(UseOnContext context) {
    Level level = context.getLevel();
    BlockPos blockPos = context.getClickedPos();
    Player player = context.getPlayer();
    ItemStack itemStack = context.getItemInHand();
    InteractionHand interactionHand = context.getHand();

    // Place block if shift key is down.
    if (player != null && player.isShiftKeyDown()) {
      return super.useOn(context);
    }
    stopFireAnimation(player, level, blockPos);
    stopFire(level, player, interactionHand, blockPos, itemStack);

    return InteractionResult.FAIL;
  }

  @Override
  public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
    ItemStack itemStack = player.getItemInHand(hand);

    // Let player stop fire on them self, if he is not targeting any block.
    if (player.isOnFire()) {
      if (player.getRemainingFireTicks() > 2) {
        player.setRemainingFireTicks(2);
      }
      stopFireAnimation(player, level, player.blockPosition());
      stopFireSound(level, player);
      hurtAndBreak(level, itemStack, player, hand);
    }
    return InteractionResultHolder.pass(itemStack);
  }

  @Override
  public InteractionResult interactLivingEntity(
      ItemStack itemStack, Player player, LivingEntity livingEntity, InteractionHand hand) {
    BlockPos blockPos = livingEntity.getOnPos();
    Level level = player.getLevel();
    stopFireAnimation(player, player.getLevel(), blockPos.above());

    // Set frozen
    livingEntity.setTicksFrozen(ATTACK_EFFECT_DURATION * 5);

    // Remove fire if needed
    if (livingEntity.isOnFire()) {
      livingEntity.setRemainingFireTicks(2);
    }

    if (!level.isClientSide) {
      // Add slowness effect for movement and jump
      livingEntity.addEffect(
          new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, ATTACK_EFFECT_DURATION, 10));
      livingEntity.addEffect(
          new MobEffectInstance(MobEffects.SLOW_FALLING, ATTACK_EFFECT_DURATION, 10));

      // Hurt Mob Entity
      if (!(livingEntity instanceof Player)) {
        float attackDamage = 0.5F;
        if (livingEntity instanceof Blaze) {
          attackDamage = 3.0F;
        }
        livingEntity.hurt(level.damageSources().magic(), attackDamage);
      }

      // Damage item
      hurtAndBreak(level, itemStack, player, hand);
    } else {
      stopFireSound(level, player);
    }
    return InteractionResult.PASS;
  }

  @Override
  public int getUseDuration(ItemStack itemStack) {
    return 10;
  }

  @Override
  public boolean isFireResistant() {
    return true;
  }

  @Override
  public void appendHoverText(
      ItemStack itemStack,
      @Nullable Level level,
      List<Component> tooltipList,
      TooltipFlag tooltipFlag) {
    tooltipList.add(
        Component.translatable(Constants.TOOLTIP_PREFIX + NAME, COMMON.fireExtinguisherRadius.get())
            .withStyle(ChatFormatting.GRAY));
    tooltipList.add(
        Component.translatable(Constants.TEXT_PREFIX + NAME + "_use")
            .withStyle(ChatFormatting.GREEN));
    tooltipList.add(
        Component.translatable(Constants.TEXT_PREFIX + NAME + "_place")
            .withStyle(ChatFormatting.DARK_GREEN));
  }
}

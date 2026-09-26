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
import de.markusbordihn.fireextinguisher.config.FireExtinguisherConfig;
import de.markusbordihn.fireextinguisher.utils.FireDetection;
import de.markusbordihn.fireextinguisher.utils.ToolTips;
import java.util.function.Consumer;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class FireExtinguisherBlockItem extends BlockItem {

  public static final String ID = "fire_extinguisher";
  public static final String ID_COPPER = "fire_extinguisher_copper";
  private static final double NOZZLE_OFFSET = 1.2;
  private static final int TRAIL_PARTICLES = 12;
  private static final int BURST_PARTICLES = 24;
  private static final int ATTACK_EFFECT_DURATION = 200;

  public FireExtinguisherBlockItem(Block block, String id) {
    this(
        block,
        new Properties()
            .useBlockDescriptionPrefix()
            .setId(
                ResourceKey.create(
                    Registries.ITEM, Identifier.fromNamespaceAndPath(Constants.MOD_ID, id)))
            .stacksTo(1)
            .durability(128)
            .fireResistant());
  }

  public FireExtinguisherBlockItem(Block block, Properties properties) {
    super(block, properties);
  }

  public static void stopFireAnimation(Player player, Level level, BlockPos blockPos) {
    if (blockPos == null || !(level instanceof ServerLevel serverLevel)) {
      return;
    }

    Vec3 eyePosition = player.getEyePosition();
    Vec3 targetPosition = Vec3.atCenterOf(blockPos);
    Vec3 nozzlePosition =
        eyePosition.add(
            player
                .getLookAngle()
                .scale(Math.min(NOZZLE_OFFSET, eyePosition.distanceTo(targetPosition) * 0.5)));

    for (int i = 1; i <= TRAIL_PARTICLES; i++) {
      Vec3 trailPosition = nozzlePosition.lerp(targetPosition, i / (double) TRAIL_PARTICLES);
      serverLevel.sendParticles(
          ParticleTypes.CLOUD,
          trailPosition.x,
          trailPosition.y,
          trailPosition.z,
          1,
          0.1,
          0.1,
          0.1,
          0.01);
    }
    serverLevel.sendParticles(
        ParticleTypes.CLOUD,
        targetPosition.x,
        targetPosition.y,
        targetPosition.z,
        BURST_PARTICLES,
        0.4,
        0.4,
        0.4,
        0.02);
  }

  public static void stopFire(
      ServerLevel serverLevel,
      Player player,
      InteractionHand hand,
      BlockPos targetBlockPos,
      ItemStack itemStack) {
    int extinguished =
        FireDetection.extinguishWithin(
            serverLevel,
            targetBlockPos.above(),
            FireExtinguisherConfig.fireExtinguisherRadiusX,
            FireExtinguisherConfig.fireExtinguisherRadiusY,
            FireExtinguisherConfig.fireExtinguisherRadiusZ);
    if (extinguished > 0) {
      hurtAndBreak(serverLevel, itemStack, player, hand);
      serverLevel.playSound(
          null, targetBlockPos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0f, 1.0f);
    }
    addUseCooldown(player, itemStack);
  }

  private static void addUseCooldown(Player player, ItemStack itemStack) {
    if (FireExtinguisherConfig.fireExtinguisherCooldownTicks > 0) {
      player
          .getCooldowns()
          .addCooldown(itemStack, FireExtinguisherConfig.fireExtinguisherCooldownTicks);
    }
  }

  public static void stopFireSound(Level level, Player player) {
    if (level.isClientSide()) {
      player.playSound(SoundEvents.FIRE_EXTINGUISH, 1.0F, 1.0F);
    }
  }

  public static void hurtAndBreak(
      Level level, ItemStack itemStack, Player player, InteractionHand hand) {
    if (!level.isClientSide()) {
      itemStack.hurtAndBreak(
          1,
          player,
          hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
    }
  }

  @Override
  public boolean canDestroyBlock(
      ItemStack itemStack,
      BlockState blockState,
      Level level,
      BlockPos blockPos,
      LivingEntity livingEntity) {
    if (blockState.getBlock() instanceof FireExtinguisherBlock) {
      return true;
    }
    return livingEntity instanceof Player player ? player.isShiftKeyDown() : false;
  }

  @Override
  public InteractionResult useOn(UseOnContext context) {
    Level level = context.getLevel();
    BlockPos blockPos = context.getClickedPos();
    Player player = context.getPlayer();
    ItemStack itemStack = context.getItemInHand();
    InteractionHand interactionHand = context.getHand();

    if (player == null) {
      return InteractionResult.PASS;
    }

    if (player.isSecondaryUseActive()) {
      return super.useOn(context);
    }

    if (level instanceof ServerLevel serverLevel) {
      stopFireAnimation(player, serverLevel, blockPos.above());
      stopFire(serverLevel, player, interactionHand, blockPos, itemStack);
    }
    return InteractionResult.SUCCESS;
  }

  @Override
  public InteractionResult use(Level level, Player player, InteractionHand hand) {
    ItemStack itemStack = player.getItemInHand(hand);
    if (player.isOnFire()) {
      if (player.getRemainingFireTicks() > 2) {
        player.setRemainingFireTicks(2);
      }
      stopFireAnimation(player, level, player.blockPosition());
      stopFireSound(level, player);
      hurtAndBreak(level, itemStack, player, hand);
      if (!level.isClientSide()) {
        addUseCooldown(player, itemStack);
      }
    }
    return InteractionResult.PASS;
  }

  @Override
  public InteractionResult interactLivingEntity(
      ItemStack itemStack, Player player, LivingEntity livingEntity, InteractionHand hand) {
    BlockPos blockPos = livingEntity.getOnPos();
    Level level = player.level();

    stopFireAnimation(player, player.level(), blockPos.above());
    livingEntity.setTicksFrozen(ATTACK_EFFECT_DURATION * 5);
    if (livingEntity.isOnFire()) {
      livingEntity.setRemainingFireTicks(2);
    }

    if (!level.isClientSide()) {
      livingEntity.addEffect(
          new MobEffectInstance(MobEffects.SLOWNESS, ATTACK_EFFECT_DURATION, 10));
      livingEntity.addEffect(
          new MobEffectInstance(MobEffects.SLOW_FALLING, ATTACK_EFFECT_DURATION, 10));
      if (!(livingEntity instanceof Player)) {
        float attackDamage = 0.5F;
        if (livingEntity instanceof Blaze) {
          attackDamage = 3.0F;
        }
        livingEntity.hurt(level.damageSources().magic(), attackDamage);
      }
      hurtAndBreak(level, itemStack, player, hand);
      addUseCooldown(player, itemStack);
    } else {
      stopFireSound(level, player);
    }
    return InteractionResult.PASS;
  }

  @Override
  public int getUseDuration(ItemStack itemStack, LivingEntity livingEntity) {
    return 10;
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
                Constants.TOOLTIP_PREFIX + ID, FireExtinguisherConfig.fireExtinguisherRadiusX)
            .withStyle(ChatFormatting.GRAY));
    ToolTips.addTooltip(
        tooltipConsumer,
        Component.translatable(Constants.TEXT_PREFIX + ID + "_use")
            .withStyle(ChatFormatting.GREEN));
    ToolTips.addTooltip(
        tooltipConsumer,
        Component.translatable(Constants.TEXT_PREFIX + ID + "_place")
            .withStyle(ChatFormatting.DARK_GREEN));
  }
}

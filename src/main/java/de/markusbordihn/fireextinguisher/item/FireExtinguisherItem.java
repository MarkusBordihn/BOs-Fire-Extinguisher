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

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
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
import de.markusbordihn.fireextinguisher.block.FireExtinguisherBlock;
import de.markusbordihn.fireextinguisher.config.CommonConfig;

@EventBusSubscriber
public class FireExtinguisherItem extends BlockItem implements IVanishable {

  protected static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  public static final String NAME = "fire_extinguisher";

  private static final CommonConfig.Config COMMON = CommonConfig.COMMON;

  private static int fireExtinguisherRadius = COMMON.fireExtinguisherRadius.get();

  private static final double X_SHIFT = 0.0;
  private static final double Y_SHIFT = 1.6;
  private static final double Z_SHIFT = 0.0;
  private static final int PARTICLE_FRAMES = 8;

  private static final int ATTACK_EFFECT_DURATION = 200;

  public FireExtinguisherItem(Block block, Properties properties) {
    super(block, properties);
  }

  @SubscribeEvent
  public static void handleServerAboutToStartEvent(FMLServerAboutToStartEvent event) {
    fireExtinguisherRadius = COMMON.fireExtinguisherRadius.get();
  }

  public static void stopFireAnimation(PlayerEntity player, World level, BlockPos blockPos) {
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

  public static void stopFire(World level, PlayerEntity player, Hand hand, BlockPos targetBlockPos,
      ItemStack itemStack) {
    Iterable<BlockPos> blockPositions = BlockPos.withinManhattan(targetBlockPos.above(),
        fireExtinguisherRadius, fireExtinguisherRadius, fireExtinguisherRadius);
    boolean hasStoppedFire = false;
    for (BlockPos blockPos : blockPositions) {
      BlockState blockState = level.getBlockState(blockPos);
      if (blockState.is(Blocks.FIRE)) {

        // Remove block on server and client
        log.debug("[FireExtinguisher] Removing Fire Block {} at {}", blockState, blockPos);
        level.removeBlock(blockPos, false);

        // Play fire extinguish sound on the client
        stopFireSound(level, player);

        hasStoppedFire = true;
      }
    }
    if (hasStoppedFire) {
      hurtAndBreak(level, itemStack, player, hand);
    }
  }

  public static void stopFireSound(World level, PlayerEntity player) {
    if (level.isClientSide) {
      player.playSound(SoundEvents.FIRE_EXTINGUISH, 1.0F, 1.0F);
    }
  }

  public static void hurtAndBreak(World level, ItemStack itemStack, PlayerEntity player,
      Hand hand) {
    if (!level.isClientSide) {
      itemStack.hurtAndBreak(1, player, serverPlayer -> {
        serverPlayer.broadcastBreakEvent(hand);
      });
    }
  }

  @Override
  public boolean canAttackBlock(BlockState blockState, World level, BlockPos blockPos,
      PlayerEntity player) {
    if (blockState.getBlock() instanceof FireExtinguisherBlock) {
      return true;
    }
    return player.isShiftKeyDown();
  }

  @Override
  public ActionResultType useOn(ItemUseContext context) {
    World level = context.getLevel();
    BlockPos blockPos = context.getClickedPos();
    PlayerEntity player = context.getPlayer();
    ItemStack itemStack = context.getItemInHand();
    Hand hand = context.getHand();

    // Place block if shift key is down.
    if (player != null && player.isShiftKeyDown()) {
      return super.useOn(context);
    }

    stopFireAnimation(player, level, blockPos);
    stopFire(level, player, hand, blockPos, itemStack);

    return ActionResultType.FAIL;
  }

  @Override
  public ActionResult<ItemStack> use(World level, PlayerEntity player, Hand hand) {
    ItemStack itemStack = player.getItemInHand(hand);

    // Let player to stop fire on them self, if he is not targeting any block.
    if (player.isOnFire()) {
      if (player.getRemainingFireTicks() > 2) {
        player.setRemainingFireTicks(2);
      }
      stopFireAnimation(player, level, player.blockPosition());
      stopFireSound(level, player);
      hurtAndBreak(level, itemStack, player, hand);
    }
    return ActionResult.pass(itemStack);
  }

  @Override
  public ActionResultType interactLivingEntity(ItemStack itemStack, PlayerEntity player,
      LivingEntity livingEntity, Hand hand) {
    BlockPos blockPos = livingEntity.blockPosition();
    World level = player.level;
    stopFireAnimation(player, level, blockPos.above());

    // Remove fire if needed
    if (livingEntity.isOnFire()) {
      livingEntity.setRemainingFireTicks(2);
    }

    if (!level.isClientSide) {
      // Add slowness effect for movement and jump
      livingEntity
          .addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, ATTACK_EFFECT_DURATION, 10));
      livingEntity.addEffect(new EffectInstance(Effects.SLOW_FALLING, ATTACK_EFFECT_DURATION, 10));

      // Hurt Mob Entity
      if (!(livingEntity instanceof PlayerEntity)) {
        float attackDamage = 0.5F;
        if (livingEntity instanceof BlazeEntity) {
          attackDamage = 3.0F;
        }
        livingEntity.hurt(DamageSource.MAGIC, attackDamage);
      }

      // Damage item
      hurtAndBreak(level, itemStack, player, hand);
    } else {
      stopFireSound(level, player);
    }
    return ActionResultType.PASS;
  }

  @Override
  public int getUseDuration(ItemStack itemStack) {
    return 10;
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
        fireExtinguisherRadius));
    tooltipList.add(new TranslationTextComponent(Constants.TEXT_PREFIX + NAME + "_use")
        .withStyle(TextFormatting.GREEN));
    tooltipList.add(new TranslationTextComponent(Constants.TEXT_PREFIX + NAME + "_place")
        .withStyle(TextFormatting.GRAY));
  }
}

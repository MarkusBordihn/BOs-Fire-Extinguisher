package de.markusbordihn.fireextinguisher.item;

import java.util.List;
import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
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
import net.minecraft.world.level.block.state.BlockState;

import de.markusbordihn.fireextinguisher.Constants;

public class FireExtinguisherItem extends BlockItem implements Vanishable {

  protected static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  public static final String NAME = "fire_extinguisher";

  private static final double X_SHIFT = 0.0;
  private static final double Y_SHIFT = 1.6;
  private static final double Z_SHIFT = 0.0;
  private static final int PARTICLE_FRAMES = 8;
  private static final int FIRE_STOP_RADIUS = 1;

  private static final int ATTACK_EFFECT_DURATION = 200;

  public FireExtinguisherItem(Block block, Properties properties) {
    super(block, properties);
  }

  public void stopFireAnimation(Player player, Level level, BlockPos blockPos) {
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
        level.addParticle(ParticleTypes.CLOUD, x, y, z, 0.0D, 0.0D, 0.0D);
      }
    }
  }

  public void stopFire(Level level, Player player, InteractionHand hand, BlockPos targetBlockPos,
      ItemStack itemStack) {
    Iterable<BlockPos> blockPositions = BlockPos.withinManhattan(targetBlockPos.above(),
        FIRE_STOP_RADIUS, FIRE_STOP_RADIUS, FIRE_STOP_RADIUS);
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
      itemStack.hurtAndBreak(1, player, (serverPlayer) -> {
        serverPlayer.broadcastBreakEvent(hand);
      });
    }
  }

  @Override
  public boolean canAttackBlock(BlockState blockState, Level level, BlockPos blockPos,
      Player player) {
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
    if (player.isShiftKeyDown()) {
      return super.useOn(context);
    }
    stopFireAnimation(player, level, blockPos);
    stopFire(level, player, interactionHand, blockPos, itemStack);

    return InteractionResult.FAIL;
  }

  @Override
  public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
    return InteractionResultHolder.pass(player.getItemInHand(hand));
  }

  @Override
  public InteractionResult interactLivingEntity(ItemStack itemStack, Player player,
      LivingEntity livingEntity, InteractionHand hand) {
    BlockPos blockPos = livingEntity.getOnPos();
    Level level = player.getLevel();
    stopFireAnimation(player, player.getLevel(), blockPos.above());

    // Set frozen
    livingEntity.setTicksFrozen(ATTACK_EFFECT_DURATION * 5);

    if (!level.isClientSide) {
      // Add slowness effect for movement and jump
      livingEntity.addEffect(
          new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, ATTACK_EFFECT_DURATION, 10));
      livingEntity
          .addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, ATTACK_EFFECT_DURATION, 10));

      // Hurt Entity
      float attackDamage = 0.5F;
      if (livingEntity instanceof Blaze) {
        attackDamage = 3.0F;
      }
      livingEntity.hurt(DamageSource.MAGIC, attackDamage);

      // Damage item
      itemStack.hurtAndBreak(1, player, (serverPlayer) -> {
        serverPlayer.broadcastBreakEvent(hand);
      });
    }
    return InteractionResult.PASS;
  }

  @Override
  public int getUseDuration(ItemStack itemStack) {
    return 10;
  }

  @Override
  public void appendHoverText(ItemStack itemStack, @Nullable Level level,
      List<Component> tooltipList, TooltipFlag tooltipFlag) {
    tooltipList.add(new TranslatableComponent(Constants.TEXT_PREFIX + NAME + "_description"));
    tooltipList.add(new TranslatableComponent(Constants.TEXT_PREFIX + NAME + "_use")
        .withStyle(ChatFormatting.GREEN));
    tooltipList.add(new TranslatableComponent(Constants.TEXT_PREFIX + NAME + "_place")
        .withStyle(ChatFormatting.GRAY));
  }
}

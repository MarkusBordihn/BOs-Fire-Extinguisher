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

package de.markusbordihn.fireextinguisher.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import de.markusbordihn.fireextinguisher.Constants;

public class ModBlocks {

  protected ModBlocks() {

  }

  public static final DeferredRegister<Block> BLOCKS =
      DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MOD_ID);

  // Exit Signs
  public static final RegistryObject<Block> EXIT_SIGN =
      BLOCKS.register(ExitSign.NAME, () -> new ExitSign(BlockBehaviour.Properties.of(Material.STONE)
          .requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(SoundType.METAL)));
  public static final RegistryObject<Block> EXIT_SIGN_LEFT = BLOCKS.register(
      ExitSign.NAME + "_left", () -> new ExitSign(BlockBehaviour.Properties.of(Material.STONE)
          .requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(SoundType.METAL)));
  public static final RegistryObject<Block> EXIT_SIGN_LEFT_DOWN = BLOCKS.register(
      ExitSign.NAME + "_left_down", () -> new ExitSign(BlockBehaviour.Properties.of(Material.STONE)
          .requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(SoundType.METAL)));
  public static final RegistryObject<Block> EXIT_SIGN_LEFT_UP = BLOCKS.register(
      ExitSign.NAME + "_left_up", () -> new ExitSign(BlockBehaviour.Properties.of(Material.STONE)
          .requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(SoundType.METAL)));
  public static final RegistryObject<Block> EXIT_SIGN_RIGHT = BLOCKS.register(
      ExitSign.NAME + "_right", () -> new ExitSign(BlockBehaviour.Properties.of(Material.STONE)
          .requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(SoundType.METAL)));
  public static final RegistryObject<Block> EXIT_SIGN_RIGHT_DOWN = BLOCKS.register(
      ExitSign.NAME + "_right_down", () -> new ExitSign(BlockBehaviour.Properties.of(Material.STONE)
          .requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(SoundType.METAL)));
  public static final RegistryObject<Block> EXIT_SIGN_RIGHT_UP = BLOCKS.register(
      ExitSign.NAME + "_right_up", () -> new ExitSign(BlockBehaviour.Properties.of(Material.STONE)
          .requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(SoundType.METAL)));

  // Fire Extinguisher Signs
  public static final RegistryObject<Block> FIRE_EXTINGUISHER_SIGN =
      BLOCKS.register(FireExtinguisherSign.NAME,
          () -> new FireExtinguisherSign(BlockBehaviour.Properties.of(Material.STONE)
              .requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(SoundType.GLASS)));
  public static final RegistryObject<Block> FIRE_EXTINGUISHER_SIGN_LEFT =
      BLOCKS.register(FireExtinguisherSign.NAME + "_left",
          () -> new FireExtinguisherSignPosition(BlockBehaviour.Properties.of(Material.STONE)
              .requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(SoundType.GLASS)));
  public static final RegistryObject<Block> FIRE_EXTINGUISHER_SIGN_RIGHT =
      BLOCKS.register(FireExtinguisherSign.NAME + "_right",
          () -> new FireExtinguisherSignPosition(BlockBehaviour.Properties.of(Material.STONE)
              .requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(SoundType.GLASS)));

  // Fire Extinguisher
  public static final RegistryObject<Block> FIRE_EXTINGUISHER = BLOCKS.register(
      FireExtinguisherBlock.NAME, () -> new FireExtinguisherBlock(BlockBehaviour.Properties
          .of(Material.STONE).instabreak().noOcclusion().sound(SoundType.METAL)));
}

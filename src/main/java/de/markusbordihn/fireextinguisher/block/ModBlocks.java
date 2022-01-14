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
import de.markusbordihn.fireextinguisher.Annotations.TemplateEntryPoint;

public class ModBlocks {

  protected ModBlocks() {

  }

  public static final DeferredRegister<Block> BLOCKS =
      DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MOD_ID);

  @TemplateEntryPoint("Register Blocks")

  // Signs
  public static final RegistryObject<Block> FIRE_EXTINGUISHER_SIGN_LEFT =
      BLOCKS.register("fire_extinguisher_sign_left",
          () -> new FireExtinguisherSignPosition(
              BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops()
                  .strength(3.0F, 6.0F).lightLevel(blockState -> 10).sound(SoundType.GLASS)));
  public static final RegistryObject<Block> FIRE_EXTINGUISHER_SIGN_RIGHT =
      BLOCKS.register("fire_extinguisher_sign_right",
          () -> new FireExtinguisherSignPosition(
              BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops()
                  .strength(3.0F, 6.0F).lightLevel(blockState -> 10).sound(SoundType.GLASS)));
  public static final RegistryObject<Block> FIRE_EXTINGUISHER_SIGN =
      BLOCKS.register(FireExtinguisherSign.NAME,
          () -> new FireExtinguisherSign(
              BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops()
                  .strength(3.0F, 6.0F).lightLevel(blockState -> 10).sound(SoundType.GLASS)));

  // Fire Extinguisher
  public static final RegistryObject<Block> FIRE_EXTINGUISHER = BLOCKS.register(
      FireExtinguisherBlock.NAME, () -> new FireExtinguisherBlock(BlockBehaviour.Properties
          .of(Material.STONE).instabreak().noOcclusion().sound(SoundType.METAL)));
}

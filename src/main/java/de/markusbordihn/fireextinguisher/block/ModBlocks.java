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
  public static final RegistryObject<Block> FIRE_EXTINGUISHER = BLOCKS.register(
      FireExtinguisherBlock.NAME, () -> new FireExtinguisherBlock(BlockBehaviour.Properties
          .of(Material.STONE).instabreak().noOcclusion().sound(SoundType.METAL)));
}

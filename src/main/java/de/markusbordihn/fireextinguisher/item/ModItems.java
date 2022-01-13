package de.markusbordihn.fireextinguisher.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import de.markusbordihn.fireextinguisher.Constants;
import de.markusbordihn.fireextinguisher.block.ModBlocks;
import de.markusbordihn.fireextinguisher.Annotations.TemplateEntryPoint;

public class ModItems {

  protected ModItems() {

  }

  public static final DeferredRegister<Item> ITEMS =
      DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);

  @TemplateEntryPoint("Register Items")

  @TemplateEntryPoint("Register Block Items")
  public static final RegistryObject<Item> FIRE_EXTINGUISHER =
      ITEMS.register(FireExtinguisherItem.NAME,
          () -> new FireExtinguisherItem(ModBlocks.FIRE_EXTINGUISHER.get(), new Item.Properties()
              .stacksTo(1).durability(128).tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
}

package de.markusbordihn.fireextinguisher.tabs;

import de.markusbordihn.fireextinguisher.item.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class FireExtinguisherTab {

  protected FireExtinguisherTab() {}

  public static final CreativeModeTab TAB_FIRE_EXTINGUISHER =
      new CreativeModeTab("fire_extinguisher") {
        public ItemStack makeIcon() {
          return new ItemStack(ModItems.FIRE_EXTINGUISHER.get());
        }
      };

}

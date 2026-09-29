/*
 * Copyright 2026 Markus Bordihn
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

package de.markusbordihn.fireextinguisher.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DataResourcesTest {

  @Test
  @DisplayName("Every block has a loot table that drops the block itself")
  void blockLootTablesDropBlock() {
    List<String> invalidLootTables = new ArrayList<>();
    for (String blockName : ModResources.baseNames(ModResources.BLOCKSTATES)) {
      Path lootTable = ModResources.BLOCK_LOOT_TABLES.resolve(blockName + ".json");
      if (!Files.isRegularFile(lootTable)) {
        invalidLootTables.add(blockName + " has no loot table");
      } else if (!ModResources.stringValues(ModResources.readJson(lootTable), "name")
          .contains(ModResources.NAMESPACE + ":" + blockName)) {
        invalidLootTables.add(blockName + " does not drop itself");
      }
    }
    assertEquals(List.of(), invalidLootTables);
  }

  @Test
  @DisplayName("Every recipe is unlocked by an advancement")
  void recipesAreUnlockedByAdvancements() {
    Set<String> lockedRecipes = this.recipeIds();
    lockedRecipes.removeAll(this.rewardedRecipeIds());
    assertEquals(Set.of(), lockedRecipes);
  }

  @Test
  @DisplayName("Advancements only reward existing recipes")
  void advancementRewardsExist() {
    Set<String> unknownRecipes = this.rewardedRecipeIds();
    unknownRecipes.removeAll(this.recipeIds());
    assertEquals(Set.of(), unknownRecipes);
  }

  @Test
  @DisplayName("Recipe advancements reward the recipe they are named after")
  void recipeAdvancementsRewardOwnRecipe() {
    List<String> mismatchedAdvancements = new ArrayList<>();
    for (Path advancement : ModResources.jsonFiles(ModResources.ADVANCEMENTS)) {
      String ownRecipeId = ModResources.NAMESPACE + ":" + ModResources.baseName(advancement);
      if (!this.rewardedRecipeIds(advancement).contains(ownRecipeId)) {
        mismatchedAdvancements.add(ModResources.resourceId(ModResources.ADVANCEMENTS, advancement));
      }
    }
    assertEquals(List.of(), mismatchedAdvancements);
  }

  @Test
  @DisplayName("Mod items used in recipes have item models")
  void recipeItemsExist() {
    Set<String> unknownItems = new TreeSet<>();
    for (Path recipe : ModResources.jsonFiles(ModResources.RECIPES)) {
      for (String itemId : ModResources.stringValues(ModResources.readJson(recipe), "item")) {
        if (ModResources.isModId(itemId) && !this.itemModelExists(itemId)) {
          unknownItems.add(recipe.getFileName() + " -> " + itemId);
        }
      }
    }
    assertEquals(Set.of(), unknownItems);
  }

  @Test
  @DisplayName("Block tags only contain existing mod blocks")
  void blockTagsContainExistingBlocks() {
    Set<String> blockNames = ModResources.baseNames(ModResources.BLOCKSTATES);
    Set<String> unknownBlocks = new TreeSet<>();
    for (Path tag : ModResources.jsonFiles(ModResources.ROOT.resolve("data"))) {
      if (!tag.toString().replace('\\', '/').contains("/tags/blocks/")) {
        continue;
      }

      for (JsonElement value : ModResources.readJson(tag).getAsJsonArray("values")) {
        String blockId = value.getAsString();
        if (ModResources.isModId(blockId) && !blockNames.contains(ModResources.path(blockId))) {
          unknownBlocks.add(tag.getFileName() + " -> " + blockId);
        }
      }
    }
    assertEquals(Set.of(), unknownBlocks);
  }

  private boolean itemModelExists(String itemId) {
    Path itemModel = ModResources.ITEM_MODELS.resolve(ModResources.path(itemId) + ".json");
    return Files.isRegularFile(itemModel);
  }

  private Set<String> recipeIds() {
    Set<String> recipeIds = new TreeSet<>();
    for (Path recipe : ModResources.jsonFiles(ModResources.RECIPES)) {
      recipeIds.add(ModResources.resourceId(ModResources.RECIPES, recipe));
    }
    return recipeIds;
  }

  private Set<String> rewardedRecipeIds() {
    Set<String> rewardedRecipeIds = new TreeSet<>();
    for (Path advancement : ModResources.jsonFiles(ModResources.ADVANCEMENTS)) {
      rewardedRecipeIds.addAll(this.rewardedRecipeIds(advancement));
    }
    return rewardedRecipeIds;
  }

  private Set<String> rewardedRecipeIds(Path advancement) {
    Set<String> rewardedRecipeIds = new TreeSet<>();
    JsonObject rewards = ModResources.readJson(advancement).getAsJsonObject("rewards");
    if (rewards != null && rewards.has("recipes")) {
      rewards
          .getAsJsonArray("recipes")
          .forEach(recipeId -> rewardedRecipeIds.add(recipeId.getAsString()));
    }
    return rewardedRecipeIds;
  }
}

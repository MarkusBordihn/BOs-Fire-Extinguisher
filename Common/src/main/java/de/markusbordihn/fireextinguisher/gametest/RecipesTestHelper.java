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

package de.markusbordihn.fireextinguisher.gametest;

import de.markusbordihn.fireextinguisher.Constants;
import java.util.List;
import net.minecraft.advancements.Advancement;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.crafting.RecipeManager;

public class RecipesTestHelper {

  private static final FileToIdConverter RECIPE_FILES = FileToIdConverter.json("recipes");
  private static final FileToIdConverter ADVANCEMENT_FILES = FileToIdConverter.json("advancements");

  private RecipesTestHelper() {}

  public static void testAllRecipesLoad(GameTestHelper helper) {
    MinecraftServer server = helper.getLevel().getServer();
    RecipeManager recipeManager = server.getRecipeManager();
    List<ResourceLocation> recipeIds = modResourceIds(server, RECIPE_FILES);
    helper.assertFalse(recipeIds.isEmpty(), "No recipe files found");
    for (ResourceLocation recipeId : recipeIds) {
      helper.assertTrue(
          recipeManager.byKey(recipeId).isPresent(), "Recipe " + recipeId + " failed to load");
    }
  }

  public static void testRecipeAdvancementsUnlockLoadedRecipes(GameTestHelper helper) {
    MinecraftServer server = helper.getLevel().getServer();
    RecipeManager recipeManager = server.getRecipeManager();
    List<ResourceLocation> advancementIds = modResourceIds(server, ADVANCEMENT_FILES);
    helper.assertFalse(advancementIds.isEmpty(), "No advancement files found");
    for (ResourceLocation advancementId : advancementIds) {
      Advancement advancement = server.getAdvancements().getAdvancement(advancementId);
      helper.assertTrue(advancement != null, "Advancement " + advancementId + " failed to load");
      for (ResourceLocation recipeId : advancement.getRewards().getRecipes()) {
        helper.assertTrue(
            recipeManager.byKey(recipeId).isPresent(),
            "Advancement " + advancementId + " unlocks missing recipe " + recipeId);
      }
    }
  }

  private static List<ResourceLocation> modResourceIds(
      MinecraftServer server, FileToIdConverter fileToIdConverter) {
    return fileToIdConverter.listMatchingResources(server.getResourceManager()).keySet().stream()
        .filter(file -> file.getNamespace().equals(Constants.MOD_ID))
        .map(fileToIdConverter::fileToId)
        .sorted()
        .toList();
  }
}

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
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AssetResourcesTest {

  private static final String ENGLISH_LANG = "en_us";

  @Test
  @DisplayName("Blockstates only reference existing block models")
  void blockstateModelsExist() {
    List<String> missingModels = new ArrayList<>();
    for (Path blockstate : ModResources.jsonFiles(ModResources.BLOCKSTATES)) {
      for (String modelId : ModResources.stringValues(ModResources.readJson(blockstate), "model")) {
        if (!this.modelExists(modelId)) {
          missingModels.add(blockstate.getFileName() + " -> " + modelId);
        }
      }
    }
    assertEquals(List.of(), missingModels);
  }

  @Test
  @DisplayName("Model parents exist")
  void modelParentsExist() {
    List<String> missingParents = new ArrayList<>();
    for (Path model : this.modelFiles()) {
      JsonElement parent = ModResources.readJson(model).get("parent");
      if (parent != null && !this.modelExists(parent.getAsString())) {
        missingParents.add(model.getFileName() + " -> " + parent.getAsString());
      }
    }
    assertEquals(List.of(), missingParents);
  }

  @Test
  @DisplayName("Model textures exist")
  void modelTexturesExist() {
    List<String> missingTextures = new ArrayList<>();
    for (Path model : this.modelFiles()) {
      JsonObject textures = ModResources.readJson(model).getAsJsonObject("textures");
      if (textures == null) {
        continue;
      }

      for (Map.Entry<String, JsonElement> texture : textures.entrySet()) {
        String textureId = texture.getValue().getAsString();
        if (ModResources.isModId(textureId) && !this.textureExists(textureId)) {
          missingTextures.add(model.getFileName() + " -> " + textureId);
        }
      }
    }
    assertEquals(List.of(), missingTextures);
  }

  @Test
  @DisplayName("Every block has an item model")
  void blocksHaveItemModels() {
    Set<String> missingItemModels = ModResources.baseNames(ModResources.BLOCKSTATES);
    missingItemModels.removeAll(ModResources.baseNames(ModResources.ITEM_MODELS));
    assertEquals(Set.of(), missingItemModels);
  }

  @Test
  @DisplayName("Every block and item has an English name")
  void blocksAndItemsHaveEnglishNames() {
    Set<String> englishKeys = this.langKeys(ENGLISH_LANG);
    Set<String> blockNames = ModResources.baseNames(ModResources.BLOCKSTATES);
    Set<String> missingKeys = new TreeSet<>();
    for (String blockName : blockNames) {
      missingKeys.add("block." + ModResources.NAMESPACE + "." + blockName);
    }
    for (String itemName : ModResources.baseNames(ModResources.ITEM_MODELS)) {
      if (!blockNames.contains(itemName)) {
        missingKeys.add("item." + ModResources.NAMESPACE + "." + itemName);
      }
    }
    missingKeys.removeAll(englishKeys);
    assertEquals(Set.of(), missingKeys);
  }

  @Test
  @DisplayName("Every translation has the same keys as English")
  void translationsMatchEnglishKeys() {
    Set<String> englishKeys = this.langKeys(ENGLISH_LANG);
    Set<String> translations = ModResources.baseNames(ModResources.LANG);
    translations.remove(ENGLISH_LANG);
    assertFalse(translations.isEmpty(), "No translations found");
    for (String translation : translations) {
      Set<String> translationKeys = this.langKeys(translation);
      Set<String> missingKeys = new TreeSet<>(englishKeys);
      missingKeys.removeAll(translationKeys);
      Set<String> unknownKeys = new TreeSet<>(translationKeys);
      unknownKeys.removeAll(englishKeys);
      assertEquals(Set.of(), missingKeys, translation + " is missing keys");
      assertEquals(Set.of(), unknownKeys, translation + " has keys unknown to " + ENGLISH_LANG);
    }
  }

  @Test
  @DisplayName("Every sound event has its sound file")
  void soundFilesExist() {
    JsonObject soundEvents = ModResources.readJson(ModResources.ASSETS.resolve("sounds.json"));
    List<String> missingSounds = new ArrayList<>();
    for (Map.Entry<String, JsonElement> soundEvent : soundEvents.entrySet()) {
      for (String soundId : ModResources.stringValues(soundEvent.getValue(), "name")) {
        Path soundFile = ModResources.ASSETS.resolve("sounds").resolve(ModResources.path(soundId));
        if (!Files.isRegularFile(Path.of(soundFile + ".ogg"))) {
          missingSounds.add(soundEvent.getKey() + " -> " + soundId);
        }
      }
    }
    assertEquals(List.of(), missingSounds);
  }

  private List<Path> modelFiles() {
    return ModResources.jsonFiles(ModResources.ASSETS.resolve("models"));
  }

  private boolean modelExists(String modelId) {
    if (!ModResources.isModId(modelId)) {
      return true;
    }

    Path modelFile = ModResources.ASSETS.resolve("models").resolve(ModResources.path(modelId));
    return Files.isRegularFile(Path.of(modelFile + ".json"));
  }

  private boolean textureExists(String textureId) {
    Path texturesDirectory = ModResources.ASSETS.resolve("textures");
    return Files.isRegularFile(texturesDirectory.resolve(ModResources.path(textureId) + ".png"));
  }

  private Set<String> langKeys(String language) {
    return new TreeSet<>(
        ModResources.readJson(ModResources.LANG.resolve(language + ".json")).keySet());
  }
}

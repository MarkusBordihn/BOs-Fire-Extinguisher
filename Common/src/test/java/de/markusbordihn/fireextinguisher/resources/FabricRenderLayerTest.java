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
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FabricRenderLayerTest {

  private static final Path FABRIC_CLIENT =
      Path.of(
          "..",
          "Fabric",
          "src",
          "main",
          "java",
          "de",
          "markusbordihn",
          "fireextinguisher",
          "FireExtinguisherClient.java");
  private static final String TRANSLUCENT = "translucent";

  @Test
  @DisplayName("Blocks with a model render_type use the same render layer on Fabric")
  void renderTypesAreRegisteredOnFabric() {
    String fabricClientSource = this.readWithoutWhitespace(FABRIC_CLIENT);
    List<String> missingRenderLayers = new ArrayList<>();
    int blocksWithRenderType = 0;
    for (Path blockstate : ModResources.jsonFiles(ModResources.BLOCKSTATES)) {
      Set<String> renderTypes = new TreeSet<>();
      for (String modelId : ModResources.stringValues(ModResources.readJson(blockstate), "model")) {
        String renderType = this.renderType(modelId);
        if (renderType != null) {
          renderTypes.add(renderType);
        }
      }
      if (renderTypes.isEmpty()) {
        continue;
      }

      blocksWithRenderType++;
      String blockName = ModResources.baseName(blockstate);
      String chunkSectionLayer = this.chunkSectionLayer(renderTypes);
      String blockConstant = blockName.toUpperCase(Locale.ROOT);
      if (!fabricClientSource.contains(
          "." + blockConstant + ",ChunkSectionLayer." + chunkSectionLayer + ")")) {
        missingRenderLayers.add(blockName + " -> " + chunkSectionLayer);
      }
    }
    assertTrue(blocksWithRenderType > 0, "No block model declares a render_type");
    assertEquals(List.of(), missingRenderLayers);
  }

  private String renderType(String modelId) {
    if (!ModResources.isModId(modelId)) {
      return null;
    }

    Path modelFile = ModResources.ASSETS.resolve("models").resolve(ModResources.path(modelId));
    JsonObject model = ModResources.readJson(Path.of(modelFile + ".json"));
    JsonElement renderType = model.get("render_type");
    if (renderType != null) {
      return ModResources.path(renderType.getAsString());
    }

    JsonElement parent = model.get("parent");
    return parent == null ? null : this.renderType(parent.getAsString());
  }

  private String chunkSectionLayer(Set<String> renderTypes) {
    String renderType = TRANSLUCENT;
    if (!renderTypes.contains(TRANSLUCENT)) {
      renderType = renderTypes.iterator().next();
    }
    return renderType.toUpperCase(Locale.ROOT);
  }

  private String readWithoutWhitespace(Path path) {
    try {
      return Files.readString(path).replaceAll("\\s+", "");
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }
}

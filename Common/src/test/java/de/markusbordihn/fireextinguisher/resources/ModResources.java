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

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class ModResources {

  static final String NAMESPACE = "fire_extinguisher";
  static final Path ROOT = Path.of("src", "main", "resources");
  static final Path ASSETS = ROOT.resolve("assets").resolve(NAMESPACE);
  static final Path DATA = ROOT.resolve("data").resolve(NAMESPACE);
  static final Path BLOCKSTATES = ASSETS.resolve("blockstates");
  static final Path BLOCK_MODELS = ASSETS.resolve("models").resolve("block");
  static final Path ITEM_MODELS = ASSETS.resolve("models").resolve("item");
  static final Path LANG = ASSETS.resolve("lang");
  static final Path BLOCK_LOOT_TABLES = DATA.resolve("loot_table").resolve("blocks");
  static final Path RECIPES = DATA.resolve("recipe");
  static final Path ADVANCEMENTS = DATA.resolve("advancement");

  private ModResources() {}

  static JsonObject readJson(Path path) {
    try (Reader reader = Files.newBufferedReader(path)) {
      return JsonParser.parseReader(reader).getAsJsonObject();
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  static List<Path> jsonFiles(Path directory) {
    try (Stream<Path> paths = Files.walk(directory)) {
      return paths
          .filter(Files::isRegularFile)
          .filter(path -> path.getFileName().toString().endsWith(".json"))
          .sorted()
          .toList();
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  static String baseName(Path path) {
    String fileName = path.getFileName().toString();
    return fileName.substring(0, fileName.lastIndexOf('.'));
  }

  static Set<String> baseNames(Path directory) {
    return jsonFiles(directory).stream()
        .map(ModResources::baseName)
        .collect(Collectors.toCollection(TreeSet::new));
  }

  static String resourceId(Path directory, Path file) {
    String relativePath = directory.relativize(file).toString().replace('\\', '/');
    return NAMESPACE + ":" + relativePath.substring(0, relativePath.lastIndexOf('.'));
  }

  static boolean isModId(String id) {
    return id.startsWith(NAMESPACE + ":");
  }

  static String path(String id) {
    return id.substring(id.indexOf(':') + 1);
  }

  static List<String> stringValues(JsonElement element, String key) {
    List<String> values = new ArrayList<>();
    collectStringValues(element, key, values);
    return values;
  }

  private static void collectStringValues(JsonElement element, String key, List<String> values) {
    if (element.isJsonArray()) {
      element.getAsJsonArray().forEach(child -> collectStringValues(child, key, values));
      return;
    }

    if (!element.isJsonObject()) {
      return;
    }

    for (Map.Entry<String, JsonElement> entry : element.getAsJsonObject().entrySet()) {
      JsonElement value = entry.getValue();
      if (entry.getKey().equals(key) && value.isJsonPrimitive()) {
        values.add(value.getAsString());
      } else {
        collectStringValues(value, key, values);
      }
    }
  }
}

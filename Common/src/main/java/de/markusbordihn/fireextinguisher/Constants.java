/*
 * Copyright 2023 Markus Bordihn
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

package de.markusbordihn.fireextinguisher;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class Constants {

  // General Mod definitions
  public static final String LOG_NAME = "Fire Extinguisher";
  public static final String LOG_ICON = "🔥";
  public static final String LOG_ICON_NAME = LOG_ICON + " " + LOG_NAME;
  public static final String LOG_REGISTER_PREFIX = LOG_ICON + "Register " + LOG_NAME;

  public static final String LOG_SUB_REGISTER_PREFIX = "- Register " + LOG_NAME;
  public static final String MOD_COMMAND = "fire_extinguisher";
  public static final String MOD_ID = "fire_extinguisher";
  public static final String MOD_ID_PREFIX = MOD_ID + ":";
  public static final String MOD_NAME = "Fire Extinguisher";
  public static final String MOD_URL =
      "https://www.curseforge.com/minecraft/mc-mods/fire-extinguisher";
  // Prefixes
  public static final String TEXT_PREFIX = "text.fire_extinguisher.";
  public static final String TOOLTIP_PREFIX = "tooltip.fire_extinguisher.";
  // Static paths
  public static Path GAME_DIR = Paths.get("").toAbsolutePath();
  public static Path CONFIG_DIR = GAME_DIR.resolve("config");

  private Constants() {}
}

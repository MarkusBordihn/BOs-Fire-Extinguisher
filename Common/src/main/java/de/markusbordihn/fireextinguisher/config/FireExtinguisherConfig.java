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

package de.markusbordihn.fireextinguisher.config;

import java.io.File;
import java.util.Properties;
import java.util.Set;

public class FireExtinguisherConfig extends Config {
  public static final String CONFIG_FILE_NAME = "fire_extinguisher.cfg";
  public static final String CONFIG_FILE_HEADER = "Fire Extinguisher Configuration";

  public static int fireExtinguisherRadiusX = 1;
  public static int fireExtinguisherRadiusY = 2;
  public static int fireExtinguisherRadiusZ = 1;
  public static int fireExtinguisherCooldownTicks = 10;

  public static Set<String> extinguishableBlocks = Set.of();

  public static int fireAxtRadius = 1;

  public static int fireSprinklerRadiusX = 2;
  public static int fireSprinklerRadiusY = 4;
  public static int fireSprinklerRadiusZ = 2;

  public static int smokeDetectorRadiusX = 3;
  public static int smokeDetectorRadiusY = 5;
  public static int smokeDetectorRadiusZ = 3;

  public static boolean fireProtectionEnabled = true;
  public static int fireProtectionDuration = 45;
  public static int fireProtectionRenew = 80;

  public static boolean fireProtectionLightEnabled = true;
  public static int fireProtectionLightDuration = 25;
  public static int fireProtectionLightRenew = 40;

  public static boolean fireBootsSlowDownEnabled = true;
  public static boolean fireChestplateSlowDownEnabled = true;
  public static boolean fireLeggingsSlowDownEnabled = true;

  public static int fireAlarmControlPanelRadiusX = 16;
  public static int fireAlarmControlPanelRadiusY = 8;
  public static int fireAlarmControlPanelRadiusZ = 16;
  public static boolean fireAlarmControlPanelLatching = false;

  public static int firePoleSlideSpeed = 10;

  public static boolean fireHydrantRefillEnabled = true;

  public static void registerConfig() {
    registerConfigFile(CONFIG_FILE_NAME, CONFIG_FILE_HEADER);
    parseConfigFile();
  }

  public static void parseConfigFile() {
    File configFile = getConfigFile(CONFIG_FILE_NAME);
    Properties properties = readConfigFile(configFile);
    Properties unmodifiedProperties = (Properties) properties.clone();

    fireExtinguisherRadiusX =
        parseConfigValue(properties, "fireExtinguisherRadiusX", fireExtinguisherRadiusX);
    fireExtinguisherRadiusY =
        parseConfigValue(properties, "fireExtinguisherRadiusY", fireExtinguisherRadiusY);
    fireExtinguisherRadiusZ =
        parseConfigValue(properties, "fireExtinguisherRadiusZ", fireExtinguisherRadiusZ);
    fireExtinguisherCooldownTicks =
        parseConfigValue(
            properties, "fireExtinguisherCooldownTicks", fireExtinguisherCooldownTicks);

    extinguishableBlocks =
        parseConfigValue(properties, "extinguishableBlocks", extinguishableBlocks);

    fireAxtRadius = parseConfigValue(properties, "fireAxtRadius", fireAxtRadius);

    fireSprinklerRadiusX =
        parseConfigValue(properties, "fireSprinklerRadiusX", fireSprinklerRadiusX);
    fireSprinklerRadiusY =
        parseConfigValue(properties, "fireSprinklerRadiusY", fireSprinklerRadiusY);
    fireSprinklerRadiusZ =
        parseConfigValue(properties, "fireSprinklerRadiusZ", fireSprinklerRadiusZ);

    smokeDetectorRadiusX =
        parseConfigValue(properties, "smokeDetectorRadiusX", smokeDetectorRadiusX);
    smokeDetectorRadiusY =
        parseConfigValue(properties, "smokeDetectorRadiusY", smokeDetectorRadiusY);
    smokeDetectorRadiusZ =
        parseConfigValue(properties, "smokeDetectorRadiusZ", smokeDetectorRadiusZ);

    fireProtectionEnabled =
        parseConfigValue(properties, "fireProtectionEnabled", fireProtectionEnabled);
    fireProtectionDuration =
        parseConfigValue(properties, "fireProtectionDuration", fireProtectionDuration);
    fireProtectionRenew = parseConfigValue(properties, "fireProtectionRenew", fireProtectionRenew);

    fireProtectionLightEnabled =
        parseConfigValue(properties, "fireProtectionLightEnabled", fireProtectionLightEnabled);
    fireProtectionLightDuration =
        parseConfigValue(properties, "fireProtectionLightDuration", fireProtectionLightDuration);
    fireProtectionLightRenew =
        parseConfigValue(properties, "fireProtectionLightRenew", fireProtectionLightRenew);

    fireBootsSlowDownEnabled =
        parseConfigValue(properties, "fireBootsSlowDownEnabled", fireBootsSlowDownEnabled);
    fireChestplateSlowDownEnabled =
        parseConfigValue(
            properties, "fireChestplateSlowDownEnabled", fireChestplateSlowDownEnabled);
    fireLeggingsSlowDownEnabled =
        parseConfigValue(properties, "fireLeggingsSlowDownEnabled", fireLeggingsSlowDownEnabled);

    fireAlarmControlPanelRadiusX =
        parseConfigValue(properties, "fireAlarmControlPanelRadiusX", fireAlarmControlPanelRadiusX);
    fireAlarmControlPanelRadiusY =
        parseConfigValue(properties, "fireAlarmControlPanelRadiusY", fireAlarmControlPanelRadiusY);
    fireAlarmControlPanelRadiusZ =
        parseConfigValue(properties, "fireAlarmControlPanelRadiusZ", fireAlarmControlPanelRadiusZ);
    fireAlarmControlPanelLatching =
        parseConfigValue(
            properties, "fireAlarmControlPanelLatching", fireAlarmControlPanelLatching);

    firePoleSlideSpeed = parseConfigValue(properties, "firePoleSlideSpeed", firePoleSlideSpeed);

    fireHydrantRefillEnabled =
        parseConfigValue(properties, "fireHydrantRefillEnabled", fireHydrantRefillEnabled);

    fireExtinguisherRadiusX = Math.max(0, fireExtinguisherRadiusX);
    fireExtinguisherRadiusY = Math.max(0, fireExtinguisherRadiusY);
    fireExtinguisherRadiusZ = Math.max(0, fireExtinguisherRadiusZ);
    fireExtinguisherCooldownTicks = Math.max(0, fireExtinguisherCooldownTicks);
    fireAxtRadius = Math.max(0, fireAxtRadius);
    fireSprinklerRadiusX = Math.max(0, fireSprinklerRadiusX);
    fireSprinklerRadiusY = Math.max(0, fireSprinklerRadiusY);
    fireSprinklerRadiusZ = Math.max(0, fireSprinklerRadiusZ);
    smokeDetectorRadiusX = Math.max(0, smokeDetectorRadiusX);
    smokeDetectorRadiusY = Math.max(0, smokeDetectorRadiusY);
    smokeDetectorRadiusZ = Math.max(0, smokeDetectorRadiusZ);
    fireProtectionDuration = Math.max(0, fireProtectionDuration);
    fireProtectionRenew = Math.max(0, fireProtectionRenew);
    fireProtectionLightDuration = Math.max(0, fireProtectionLightDuration);
    fireProtectionLightRenew = Math.max(0, fireProtectionLightRenew);
    fireAlarmControlPanelRadiusX = Math.max(0, fireAlarmControlPanelRadiusX);
    fireAlarmControlPanelRadiusY = Math.max(0, fireAlarmControlPanelRadiusY);
    fireAlarmControlPanelRadiusZ = Math.max(0, fireAlarmControlPanelRadiusZ);
    firePoleSlideSpeed = Math.min(40, Math.max(1, firePoleSlideSpeed));

    updateConfigFileIfChanged(configFile, CONFIG_FILE_HEADER, properties, unmodifiedProperties);
  }
}

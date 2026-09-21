# 🧯 Fire Extinguisher and more - Stop the Fire

[![Fire Extinguisher Versions](http://cf.way2muchnoise.eu/versions/Minecraft_567225_all.svg)](https://www.curseforge.com/minecraft/mc-mods/fire-extinguisher)

[![Download on CurseForge](http://cf.way2muchnoise.eu/title/567225.svg)](https://www.curseforge.com/minecraft/mc-mods/fire-extinguisher)
[![Fire Extinguisher Downloads](http://cf.way2muchnoise.eu/full_567225_downloads.svg)](https://www.curseforge.com/minecraft/mc-mods/fire-extinguisher)

[![Download on Modrinth](https://img.shields.io/badge/dynamic/json?labelColor=black&color=grey&label=&query=title&url=https://api.modrinth.com/v2/project/GlhPy70K&style=flat&logo=modrinth)](https://modrinth.com/mod/fire-extinguisher)
[![Fire Extinguisher Downloads](https://img.shields.io/badge/dynamic/json?labelColor=black&color=grey&label=&suffix=%20downloads&query=downloads&url=https://api.modrinth.com/v2/project/GlhPy70K&style=flat&logo=modrinth)](https://modrinth.com/mod/fire-extinguisher)

[![Report an Issue](https://img.shields.io/badge/dynamic/json?label=Report%20an%20Issue%20%2F%20Bug%20%2F%20Crash%20%2F%20Feature%20Request&labelColor=black&color=grey&query=title&url=https://api.modrinth.com/v2/project/GlhPy70K&style=flat&logo=github)][issues]

[![Wiki](https://img.shields.io/badge/dynamic/json?label=Wiki&labelColor=black&color=grey&query=title&url=https://api.modrinth.com/v2/project/CgGEe1h3&style=flat&logo=github)][wiki]

![Fire Extinguisher][logo]

The Fire Extinguisher and More mod adds extinguishers, fire alarms, sprinklers and safety signs to
fight and prevent fires.

## Features

Tools and blocks for fighting and preventing fires:

- **Fire Extinguisher (Normal and Copper):** Put out fire, soul fire, campfires and modded fire
  blocks. Both variants work the same, the copper one is a decorative alternative.
- **Fire Hydrant:** Refill a used fire extinguisher with a simple right-click.
- **Fire Pole:** Slide down from the top floor of your fire station without any fall damage.
- **Fire Axe:** Remove the fire around a block by right-clicking it or breaking it.
- **Fire-Protecting Armor (Helmet, Chestplate, Leggings, Boots):** Two variants, Heavy and Light,
  with different protection and movement speed. Each worn piece extends the protection.
- **Fire Alarm Control Panel:** Connect smoke detectors and alarm switches to bells, sirens, lights
  and sprinklers within a configurable radius without any redstone wiring. Panel and light emit a
  redstone signal while the alarm is active.
- **Fire Alarm Bell, Siren, Light, and Switch:** Raise the alarm by redstone or by hand. Switches
  come as classic, EU call point and Japanese push button variants.
- **Fire Sprinkler and Smoke Detector:** Detect fire and put it out automatically.
- **Fire Extinguisher Location Signs and Exit Location Signs:** Mark extinguishers and escape
  routes, including emergency exit signs without a direction arrow.
- **Customization via Configuration File:** Adjust cooldowns, ranges and toggles in the config file.

## 🔥 Modded Fire Blocks

Fire blocks from other mods are extinguished automatically when they extend the vanilla fire block
or are listed in the `minecraft:fire` block tag. Other blocks can be added by a data pack via the
`fire_extinguisher:extinguishable` block tag or by listing their ids in the `extinguishableBlocks`
config option, e.g. `extinguishableBlocks=burnt:fire_block,minecraft:magma_block`.

## ⚙️ Configuration

The config file offers the following additional options:

- `fireExtinguisherCooldownTicks`: Delay between two sprays of the fire extinguisher.
- `fireAlarmControlPanelRadiusX/Y/Z`: Range of the fire alarm control panel, `0` disables it.
- `fireAlarmControlPanelLatching`: Keep the alarm active until the control panel is reset by hand.
- `firePoleSlideSpeed`: Sliding speed of the fire pole in blocks per second.
- `fireHydrantRefillEnabled`: Allow refilling fire extinguishers at the fire hydrant.

With a diverse range of items at your disposal, the Fire Extinguisher and More mod offers a dynamic
firefighting experience that goes beyond the ordinary. Are you ready to face the flames and protect
your world from the destructive power of fire?

## ℹ️ More Information

Please check https://github.com/MarkusBordihn/BOs-Fire-Extinguisher/wiki for additional information.

[logo]: Common/src/main/resources/logo.png

[wiki]: https://github.com/MarkusBordihn/BOs-Fire-Extinguisher/wiki

[issues]: https://github.com/MarkusBordihn/BOs-Fire-Extinguisher/issues

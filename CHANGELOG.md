# Changelog for Fire Extinguisher (1.18.2)

## Note

This change log includes the summarized changes.
For the full changelog, please go to the [GitHub History][history] instead.

### 9.0.0

- Fixed critical thread-safety issue with fire armor cooldowns on multiplayer servers.
- Fixed smoke detector not triggering immediately after placement.
- Fixed fire sprinkler searching in wrong position and missing nearby fires.
- Fixed fire extinguisher interaction returning wrong result causing unwanted actions.
- Fixed fire axe sound only playing client-side instead of for all players.
- Fixed missing 3d sound attenuation for sounds.
- Removed campfire detection from smoke detectors to prevent false alarms.
- Added config validation to prevent negative radius values causing silent failures.
- Added armor duration stacking: wearing multiple armor pieces now multiplies fire protection
  duration.
- Improved code quality by simplifying boolean checks throughout codebase.
- Code refactoring and performance improvements.

### 8.3.0

- Fixed Fire Alarm Smoke Detector texture bleeding with shaders.
- Fixed issues where fire extinguishing foam is not visible for others.
- Fixed smaller rendering issues with the Replay Mod.

### 8.2.0

- Fixed consistent tooltip line wrapping in Fabric when TooltipFix mod is not present.
- Fixed some important sounds only playing on the client side.
- Fixed Fire Axt is not working on campfires.
- Moved some specific Fire Extinguisher logic to the server side.
- Added debug command to help with troubleshooting and lower logging outputs.
- Code refactoring and performance improvements.

### 8.1.0

- Added Chinese (zh_cn, zh_hk, zh_tw) translations. Special thanks to @Raaay-Fung.

### 8.0.0

- Fixed #9 by correct formatting recipes for 1.21.x.
- Fixed #4 by adding recipe advancements to unlock the crafting recipes.
- Added automatic game tests for blocks, block items and items.
- Simplified Fire Extinguisher sign recipe.
- Fixed smaller issues and improved performance.
- Updated documentation and logo.

### 7.0.0

- Added simple game tests.
- Fixed some minor bugs and improved performance.
- Replaced `forge-config-api-port` with simple config file.

### 6.3.0

- Smaller bug fixes and improvements.

### 6.2.0

- Added lightweight fire armor.
- Fixed rotation of fire sprinkler.

### 6.1.0

- Added Fire Sprinkler Block
- Added Fire Alarm Bell Block
- Added Fire Alarm Siren Block
- Added Fire Alarm Switch Block
- Added Smoke Detector Block
- Added Smoke Detector (silent) Block
- Added Copper Fire Extinguisher Item
- Improved textures and models

### 6.0.0

- Refactored code and improved performance
- Added Fabric port

[history]: https://github.com/MarkusBordihn/BOs-Fire-Extinguisher/commits/

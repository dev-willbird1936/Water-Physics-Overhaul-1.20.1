# Water Physics Overhaul

[![Latest release](https://img.shields.io/github/v/release/dev-willbird1936/Water-Physics-Overhaul?include_prereleases)](https://github.com/dev-willbird1936/Water-Physics-Overhaul/releases)
![Minecraft 1.21.1](https://img.shields.io/badge/Minecraft-1.21.1-62B47A)
![Loader: NeoForge](https://img.shields.io/badge/loader-NeoForge-EF6C00)

Finite-water simulation for Minecraft that makes water move, equalize, slide downhill, and settle according to nearby terrain and configurable simulation limits.

The current main line targets **Minecraft 1.21.1 with NeoForge**. A maintained **Minecraft 1.20.1 Forge** build is also available.

## Compatibility

| Minecraft | Loader | Source line | Status |
| --- | --- | --- | --- |
| 1.21.1 | NeoForge 21.1.227 or later | `main` | Current alpha line |
| 1.20.1 | Forge 47.4.16 or later | `mc/1.20.1` | Maintained alpha line |

Use matching Minecraft, loader, Water Physics Overhaul, and SKDS Core versions. Alpha releases can contain incomplete behavior and compatibility problems.

## Features

- finite, level-based surface water storage
- equalization across nearby spaces
- downhill sliding toward lower terrain
- configurable equalization, sliding, and bucket reach distances
- advanced bucket item
- chunk-level fluid data
- custom water interaction helpers
- integration hooks for the WPO add-on mods
- block-state integration for representative waterloggable blocks

Unlike normal vanilla source spreading, the simulation moves and settles finite water amounts according to nearby state and configured limits.

## Install

1. Install the loader that matches your Minecraft version.
2. Download the matching Water Physics Overhaul JAR from [Releases](https://github.com/dev-willbird1936/Water-Physics-Overhaul/releases).
3. Download the matching [`SKDS-Core`](https://github.com/dev-willbird1936/SKDS-Core) dependency.
4. Put both JAR files in the Minecraft `mods` folder.
5. Start the game and verify both mods in the Mods screen.

## Configuration

The common configuration file is:

```text
config/wpo/common.toml
```

The in-game configuration screen exposes:

- `performancePreset`
- `setMaxEqualizeDistance`
- `setMaxSlidingDistance`
- `setMaxBucketDistance`

The performance preset controls simulation aggressiveness. `CUSTOM` unlocks manual equalization and sliding distances.

## Add-ons

Two original add-on mods extend the base simulation:

- [`WPO Environmental Expansion`](https://github.com/dev-willbird1936/WPO-Environmental-Expansion) adds rain collection, puddles, evaporation, absorption, drought, seasonal effects, and biome-aware environmental behavior.
- [`WPO Hydraulic Utilities`](https://github.com/dev-willbird1936/WPO-Hydraulic-Utilities) adds pumps, drains, nozzles, valves, grates, watertight doors, watertight trapdoors, and creative fluid sources.

For external fluid transport, Pipez is the recommended companion pipe mod.

## Build From Source

Clone [`SKDS-Core`](https://github.com/dev-willbird1936/SKDS-Core) next to this repository:

```text
../SKDS-Core
../Water-Physics-Overhaul
```

For Minecraft 1.21.1 NeoForge, use `main` in both repositories:

```powershell
git switch main
git -C ..\SKDS-Core switch main
.\gradlew.bat build
```

For Minecraft 1.20.1 Forge, use `mc/1.20.1` in both repositories:

```powershell
git switch mc/1.20.1
git -C ..\SKDS-Core switch mc/1.20.1
.\gradlew.bat build
```

Stage a release JAR:

```powershell
.\gradlew.bat stageRelease
```

Version-specific values live in `versions/<mcVersion>.properties`, but source-divergent loader lines still require their matching branches.

## Version Strategy

- `main` contains the current 1.21.1 NeoForge line.
- `mc/1.20.1` contains the maintained 1.20.1 Forge line.
- `versions/<minecraft-version>.properties` stores version-specific dependency and release values.
- `mc/<minecraft-version>` branches are used when loader or source code diverges.
- release tags use `v<minecraft-version>-<mod-version>`.

## Credits and License

- Original Water Physics Overhaul: `Sasai_Kudasai_BM`
- 1.18.2 work used during the port: `Felicis`
- 1.20.1 Forge and 1.21.1 NeoForge port maintenance: [`dev-willbird1936`](https://github.com/dev-willbird1936)

The mod metadata declares **All Rights Reserved**.

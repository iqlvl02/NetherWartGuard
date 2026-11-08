# Nether Wart Guard (Client-side)

A client-side Fabric mod for Minecraft 1.21.11. It cancels vanilla attack handling while the player's crosshair is over Nether Wart at age 0, 1, or 2. Fully grown Nether Wart (age 3) and all other blocks are unaffected. Creative Mode is left unchanged.

## Requirements
- Minecraft Java Edition 1.21.11
- Fabric Loader 0.18.1 or newer
- Fabric API for 1.21.11
- Java 21

## Build
Run `./gradlew build` (Windows: `gradlew.bat build`). The remapped mod JAR is created in `build/libs/`.

## Install
Put the built JAR in your own `.minecraft/mods` folder alongside Fabric API. The server does not need this mod.

## Multiplayer limitation
This mod only intercepts the normal attack input on your client. It cannot enforce protection against server-side commands, plugins, other mods, or server-authoritative actions that bypass normal client block-breaking input. It is not a server-side security feature.

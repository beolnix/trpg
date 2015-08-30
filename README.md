# Tiny Role playing game

## Project details
0.1 - current stable version.

## Install And Run binaries

### Requirements
- Java 1.8 is required to run the game.
- java program must be in $PATH.

### Instruction
1. Download binary distrib from here: [trpg-distr-0.1.zip](http://nexus.beolnix.com/service/local/repositories/releases/content/com/beolnix/trpg-distr/0.1/trpg-distr-0.1.zip)
2. Unpack
3. run trpg.sh

## Parameters
Use --help to see all available command line parameters

## Installation from sources

### Requirements

- java 1.8 is required
- maven 3.x

### Instruction

1. git clone git@github.com:beolnix/trpg.git
2. mvn clean install
3. unpack ${project.basedir}/target/trpg-distr-${project.version}.zip
4. run trpg.sh

## Source code short introduction

Project consist of the following modules
- **args-parser** - module for command line arguments parsing and it nicely prints help
- **game-engine** - the game itself
- **trpg-distr** - module packs and assembles distr of the game. It creates zip archive basically.

### game-engine module

#### com.beolnix.trpg.App
It is the entry point.
App class reads command line arguments, loads the game from file if it is required and launches com.beolnix.trpg.gameplay.GameScenario class.

#### com.beolnix.trpg.gameplay.GameScenario
The class describes how the game should go step by step.
The game itslef is just sequence of Scenes launched by **GameScenario** one by one.

#### com.beolnix.trpg.gameplay.scene.Scene
Each screen in the game defined as the implementation of this interface and Used in GameScenario.

#### com.beolnix.trpg.gameplay.scene.SimpleTerminalScene
Each Scene must extend this archetype to correctly work with the **Terminal**

#### com.beolnix.trpg.terminal.SimpleTerminal
Used as the abstraction layer to simplify testing of the Scenes

### args-parser module

#### com.beolnix.trpg.cmdargs.ArgumentsParser
Main Interface of the module. Used to register supported command line arguments and to process the input.

### trpg-distr
Creates game distr as a zip archive packed with game-engine and bash script.
Automatically corrects project version number in the bash script.



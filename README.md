# HWR OOP Lecture Project - Chess

This repository contains a student project created for an ongoing lecture on object-oriented
programming with Kotlin at HWR Berlin (summer term 2025).

> :warning: This code is for educational purposes only. Do not rely on it!

## Prerequisites

Installed:

1. IDE of your choice (e.g. IntelliJ IDEA)
2. JDK of choice installed (e.g. through IntelliJ IDEA)
3. Maven installed (e.g. through IntelliJ IDEA)
4. Git installed

## Local Development

This project uses [Apache Maven][maven] as build tool.

To build from your shell (without an additional local installation of Maven), ensure that `./mvnw`
is executable:

```
chmod +x ./mvnw
```

I recommend not to dive into details about Maven at the beginning.
Instead, you can use [just][just] to build the project.
It reads the repositories `justfile` which maps simplified commands to corresponding sensible Maven
calls.

With _just_ installed, you can simply run this command to perform a build of this project and run
all of its tests:

```
just build
```

## Abstract

This is a command-line chess application written in Kotlin that supports multiple games by ID, basic piece movement, and
board visualization using ASCII art. It follows a clean architecture with persistence layers for saving and loading
games.

Start a new game with:

```
./chess new_game <id>
```

Show the current state of a game:

```
./chess game show <id>
```

Make a move in an existing game:

```
./chess on <id> move <from> to <to>
```

Most Interesting Problems:

- Implementing a clean architecture with multiple layers.
- Handling piece movement and validation.
- Creating a user-friendly command-line interface.
- Persisting game state and loading it back.
- Visualizing the chess board using ASCII art.
- Managing multiple games by ID.

## Feature List

| Nr. | Feature                       | Implementation               | Tests                                         |
|-----|-------------------------------|------------------------------|-----------------------------------------------|
| 1   | Creating a game               | `Game`, `GameFactory`        | `CliNewGameTest`                              |
| 2   | Loading & Deleting a game     | `GameStorage`                | `GameStorageTest`                             |
| 3   | FEN Parsing & Generation      | `ParserFEN`, `GeneratorFEN`  | `ParserFENTest`, `GeneratorFENTest`           |
| 4   | Moving a piece                | `Board`, `MoveDesired`       | `CliMoveTest`,      `MoveTests`               |
| 5   | Validating piece movement     | `MoveDesiredValidator`       | `MoveValidatorTest`, `MoveTests`              |
| 6   | Capturing & scoring           | `Game`, `Board`              | `CliCaptureTest`,`MoveTests`                  |
| 7   | Check / Checkmate detection   | `BoardStateCalculator`       | `CheckTest`, `GameEndCheckMateTest`           |
| 8   | Draw detection (50-move etc.) | `BoardStateCalculator`       | `DrawTest`                                    |
| 9   | Simulated move handling       | `Board.simulateMoveAndCheck` | `CheckTest`, `GameEndCheckMateTest`           |
| 10  | GameState tracking            | `Game`                       | `GameEndDrawTest`    , `GameEndCheckMateTest` |
| 11  | SaveEntry parsing             | `ParserSaveEntry`            | `GameStorageTest`                             |
| 12  | Persistent game history       | `GameStorage`                | `GameStorageTest`                             |
| 13  | ASCII board rendering         | `Board.toAscii()`            | `GameTest`                                    |
### Formatting

The repository contains an IntelliJ IDEA formatter configuration file.
It is located in the `.intellij` folder (not in `.idea`, which is the folder created by IntelliJ IDEA).
To use the formatter, you need to import the configuration into your IntelliJ IDEA settings.

Under **Settings**, go to **Editor**, then **Code Style**, click the **Gear Symbol** next to the Dropdown, click *
*Import Scheme**, then **IntelliJ IDEA code style XML**. Finally, select the `.intellij/formatter.xml` file.

Make sure to always use the imported `HWR OOP` code style when formatting your code.
Be aware that it might differ from the code style configured in your *Project*, or IntelliJ's *Default* code style.

### Multiple remote repositories

Your local repository should have a reference to both the fork (your own remote repository)
and the original remote repository.
To configure your git remote repositories, use the `git remote` command set.

1. Clone your fork and go enter the repository.

```
git clone <fork-url>
cd <created-folder>
```

2. Now your fork is configured as primary remote repository (origin).
   Next to origin, you should add the original repository as a second remote repository (upstream).

```
git remote add upstream <repository-url>
```

3. Verify that both remotes are configured correctly.
   The following command should list both remotes: origin and upstream.

```
git remote -v
```

4. To fetch changes from all remote repositories, use:

```
git fetch --all
```

5. If there are interesting changes (in e.g. the `main` branch) to merge into your branch, use:

```
git pull upstream main
```

[maven]: https://maven.apache.org/

[just]: https://github.com/casey/just

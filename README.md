# Formula Uno
## Description
This is a vector racing game developed in Java, where players compete on 2D circuits. The game supports a single human player, who challenges a series of bot-controlled opponents, each with unique strategies. The game system includes collision management, dynamic move selection, and a continuous stream of graphical updates via JavaFX.
The human player takes precedence in move selection, while the bots respond according to their strategies. The goal is to complete the circuit before opponents, avoiding mistakes such as going off the track or collisions with other players. It is based on the paper game whose rules are available here: https://en.wikipedia.org/wiki/Racetrack_(game).

## Screenshots (in-game)

<img width="1287" height="822" alt="immagine" src="https://github.com/user-attachments/assets/dd750fff-5bbe-41c5-b34c-358633d69fba" />

## Getting Started
### Prerequisites
The project is built using Java and Gradle. To run the project, you need to have Java Development Kit (JDK) installed on your machine. You can download it from the official Oracle website or use OpenJDK.
The jdk version currently used in this project is `openjdk-21`.

Set the `JAVA_HOME` environment variable to point to your JDK installation path.

The following approach is temporary and the environment variable will persist only for the current session.

Windows:
```bash
set JAVA_HOME=C:\path\to\jdk
```

Linux/Mac:
```bash
JAVA_HOME=/path/to/jdk
```

### Build the Project
Run the following command in the terminal to build the project:

Windows:
```bash
gradlew.bat build
```

Linux/Mac:
```bash
./gradlew build
```

### Run the Project
Run the following command in the terminal to run the project:
Windows:
```bash
gradlew.bat run
```

Linux/Mac:
```bash
./gradlew run
```

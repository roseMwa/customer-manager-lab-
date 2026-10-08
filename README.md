Customer Manager App

A Java project developed using IntelliJ IDEA, JDK 21, and Gradle.

Requirements

Before running the project, make sure you have the following installed:

Java JDK 21
Gradle (latest version)
IntelliJ IDEA

You can verify your Java installation with:

java -version


The project was developed using JDK 21.

To verify Gradle:

gradle -version

Getting Started
1. Clone the repository
git clone <repository-url>
cd <project-directory>

2. Open the project in IntelliJ IDEA

Open IntelliJ IDEA and select:

File → Open

Then select the project's root directory.

IntelliJ IDEA should automatically detect the Gradle project and import its dependencies.

3. Configure JDK 21

In IntelliJ IDEA, make sure the project is using JDK 21:

File → Project Structure → Project

Set:

SDK: JDK 21
Language level: 21

If the project uses Gradle settings, also check:

Settings → Build, Execution, Deployment → Build Tools → Gradle

and make sure Gradle is using JDK 21.

Running the Project

The project can be run using Gradle.

Using the Gradle command
gradle run


Alternatively, if the Gradle Wrapper is included in the project, it is recommended to use:

Windows:

gradlew run


macOS/Linux:

./gradlew run


The Gradle Wrapper ensures that the project uses the Gradle version specified by the project rather than relying on a globally installed Gradle version.

Running from IntelliJ IDEA

You can also run the project directly from IntelliJ IDEA by:

Opening the project.
Navigating to the main Java class.
Finding the main method.
Clicking the green Run ▶ button.

Alternatively, you can use the Gradle tool window in IntelliJ IDEA and run the run task under:

Gradle
└── Tasks
    └── application
        └── run

Project Structure

A typical project structure looks like this:

project-name/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── ...
│   └── test/
│       └── java/
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
├── gradle/
│   └── wrapper/
└── README.md

Technologies
Java 21
Gradle
IntelliJ IDEA
Gradle

This project uses Gradle as its build and dependency-management system.

Common commands:

gradle build


Build the project.

gradle test


Run the project's tests.

gradle run


Build and run the application.

gradle clean


Remove previously generated build files.

Notes
Make sure JDK 21 is installed and configured correctly.
If the project includes the Gradle Wrapper (gradlew / gradlew.bat), prefer using the wrapper instead of a globally installed Gradle version.
IntelliJ IDEA can automatically import and manage the Gradle project.
Author

<Your Name>

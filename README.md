# controlresell-ui

Shared components library for Compose Multiplatform, used for ControlResell apps.

## Installation

To use this component library, add the following to your `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.controlresell:ui:0.2.1")
}
```

## Usage

First, wrap your application in a `ControlResellTheme`:

```kotlin
@Composable
fun App() {
    ControlResellTheme {
        // Your app content here
    }
}
```

Then, you can use the provided components.

## Storytale

You can explore the components and their usage in the Storytale app:

```
./gradlew desktopStoriesRun
```

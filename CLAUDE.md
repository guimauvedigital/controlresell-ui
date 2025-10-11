# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is the UI Component Library for ControlResell - a Compose Multiplatform library providing reusable UI components, design system tokens, and shared UI utilities for all ControlResell applications (mobile apps, desktop apps, and web).

## Build System

- **Build Tool**: Gradle with Kotlin DSL
- **Kotlin Version**: 2.1.10
- **JVM Target**: Java 21
- **Compose Multiplatform**: For cross-platform UI

## Common Commands

### Building and Testing

```bash
# Run all tests
./gradlew jvmTest

# Build the library
./gradlew build
```

### Running Storytale (Component Viewer)

Storytale is a component development and showcase tool similar to Storybook:

```bash
# Run desktop stories viewer
./gradlew desktopStoriesRun

# Build stories for web
./gradlew jsBrowserProductionWebpack
```

### Code Quality

```bash
# Run detekt linting
./gradlew detekt

# Run all checks (tests + linting)
./gradlew check
```

## Architecture

### Module Structure

The library is organized as a single multiplatform module with platform-specific implementations:

**src/commonMain**: Shared UI components
- **components/**: Reusable UI components (buttons, cards, inputs, etc.)
- **theme/**: Design system tokens (colors, typography, spacing)
- **icons/**: Custom icon set
- **utils/**: UI utilities and extensions

**src/androidMain**: Android-specific implementations
**src/iosMain**: iOS-specific implementations
**src/desktopMain**: Desktop (JVM) specific implementations
**src/jsMain**: Web (JavaScript) specific implementations

### Technology Stack

**UI Framework:**
- Compose Multiplatform (shared UI across all platforms)
- Material 3 design system as foundation

**Testing:**
- kotlin.test for component tests
- Compose UI testing for interaction tests

**Build & Publishing:**
- Maven publish plugin for library distribution
- NPM publish plugin for JS/TypeScript consumption

**Tools:**
- Storytale for component development and documentation

### Design System

The library implements the ControlResell design system with:
- **Colors**: Primary, secondary, surface, error, etc.
- **Typography**: Text styles for different content types
- **Spacing**: Consistent spacing scale
- **Elevation**: Shadow and elevation tokens
- **Corner Radius**: Rounding scale

## Development Notes

### Testing

Tests are located in `src/commonTest`, `src/androidTest`, etc.

Example test structure:
```kotlin
class ButtonTest {
    @Test
    fun `button displays text correctly`() {
        val text = "Click Me"

        composeTestRule.setContent {
            ControlResellButton(
                text = text,
                onClick = {}
            )
        }

        composeTestRule.onNodeWithText(text).assertExists()
    }

    @Test
    fun `button triggers onClick when clicked`() {
        var clicked = false

        composeTestRule.setContent {
            ControlResellButton(
                text = "Click Me",
                onClick = { clicked = true }
            )
        }

        composeTestRule.onNodeWithText("Click Me").performClick()
        assertTrue(clicked)
    }
}
```

### Creating Components

Follow these patterns when creating new components:

**Button Component Example:**
```kotlin
@Composable
fun ControlResellButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    variant: ButtonVariant = ButtonVariant.Primary,
    size: ButtonSize = ButtonSize.Medium,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null
) {
    val colors = when (variant) {
        ButtonVariant.Primary -> ButtonDefaults.buttonColors(
            containerColor = ControlResellTheme.colors.primary,
            contentColor = ControlResellTheme.colors.onPrimary
        )
        ButtonVariant.Secondary -> ButtonDefaults.buttonColors(
            containerColor = ControlResellTheme.colors.secondary,
            contentColor = ControlResellTheme.colors.onSecondary
        )
        ButtonVariant.Outlined -> ButtonDefaults.outlinedButtonColors()
        ButtonVariant.Text -> ButtonDefaults.textButtonColors()
    }

    val contentPadding = when (size) {
        ButtonSize.Small -> PaddingValues(horizontal = 12.dp, vertical = 6.dp)
        ButtonSize.Medium -> PaddingValues(horizontal = 16.dp, vertical = 10.dp)
        ButtonSize.Large -> PaddingValues(horizontal = 20.dp, vertical = 14.dp)
    }

    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        contentPadding = contentPadding
    ) {
        if (leadingIcon != null) {
            leadingIcon()
            Spacer(modifier = Modifier.width(8.dp))
        }

        Text(
            text = text,
            style = when (size) {
                ButtonSize.Small -> ControlResellTheme.typography.labelSmall
                ButtonSize.Medium -> ControlResellTheme.typography.labelMedium
                ButtonSize.Large -> ControlResellTheme.typography.labelLarge
            }
        )

        if (trailingIcon != null) {
            Spacer(modifier = Modifier.width(8.dp))
            trailingIcon()
        }
    }
}

enum class ButtonVariant { Primary, Secondary, Outlined, Text }
enum class ButtonSize { Small, Medium, Large }
```

**Card Component Example:**
```kotlin
@Composable
fun ControlResellCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    elevation: Dp = 2.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    val cardModifier = if (onClick != null) {
        modifier.clickable(onClick = onClick)
    } else {
        modifier
    }

    Card(
        modifier = cardModifier,
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
        colors = CardDefaults.cardColors(
            containerColor = ControlResellTheme.colors.surface,
            contentColor = ControlResellTheme.colors.onSurface
        ),
        shape = RoundedCornerShape(ControlResellTheme.radius.medium)
    ) {
        Column(
            modifier = Modifier.padding(ControlResellTheme.spacing.medium)
        ) {
            content()
        }
    }
}
```

### Theme System

Define and use the theme system:

```kotlin
object ControlResellTheme {
    val colors: ControlResellColors
        @Composable
        get() = LocalControlResellColors.current

    val typography: ControlResellTypography
        @Composable
        get() = LocalControlResellTypography.current

    val spacing: ControlResellSpacing
        @Composable
        get() = LocalControlResellSpacing.current

    val radius: ControlResellRadius
        @Composable
        get() = LocalControlResellRadius.current
}

@Immutable
data class ControlResellColors(
    val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val onSecondary: Color,
    val surface: Color,
    val onSurface: Color,
    val background: Color,
    val onBackground: Color,
    val error: Color,
    val onError: Color
)

val LightColorScheme = ControlResellColors(
    primary = Color(0xFF6200EE),
    onPrimary = Color.White,
    secondary = Color(0xFF03DAC6),
    onSecondary = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    background = Color(0xFFF5F5F5),
    onBackground = Color.Black,
    error = Color(0xFFB00020),
    onError = Color.White
)

val DarkColorScheme = ControlResellColors(
    primary = Color(0xFFBB86FC),
    onPrimary = Color.Black,
    secondary = Color(0xFF03DAC6),
    onSecondary = Color.Black,
    surface = Color(0xFF121212),
    onSurface = Color.White,
    background = Color.Black,
    onBackground = Color.White,
    error = Color(0xFFCF6679),
    onError = Color.Black
)

@Immutable
data class ControlResellTypography(
    val displayLarge: TextStyle,
    val displayMedium: TextStyle,
    val displaySmall: TextStyle,
    val headlineLarge: TextStyle,
    val headlineMedium: TextStyle,
    val headlineSmall: TextStyle,
    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val titleSmall: TextStyle,
    val bodyLarge: TextStyle,
    val bodyMedium: TextStyle,
    val bodySmall: TextStyle,
    val labelLarge: TextStyle,
    val labelMedium: TextStyle,
    val labelSmall: TextStyle
)

@Immutable
data class ControlResellSpacing(
    val xxs: Dp = 2.dp,
    val xs: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 24.dp,
    val xl: Dp = 32.dp,
    val xxl: Dp = 48.dp
)

@Immutable
data class ControlResellRadius(
    val small: Dp = 4.dp,
    val medium: Dp = 8.dp,
    val large: Dp = 16.dp,
    val full: Dp = 9999.dp
)

@Composable
fun ControlResellTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme
    val typography = DefaultTypography
    val spacing = ControlResellSpacing()
    val radius = ControlResellRadius()

    CompositionLocalProvider(
        LocalControlResellColors provides colors,
        LocalControlResellTypography provides typography,
        LocalControlResellSpacing provides spacing,
        LocalControlResellRadius provides radius
    ) {
        MaterialTheme(
            colorScheme = if (darkTheme) darkColorScheme() else lightColorScheme(),
            content = content
        )
    }
}
```

### Using Storytale

Create stories for components to showcase and test them:

```kotlin
// In src/commonMain/kotlin/stories/ButtonStories.kt
fun buttonStories() = story("Button") {
    add("Primary") {
        ControlResellButton(
            text = "Primary Button",
            onClick = {}
        )
    }

    add("Secondary") {
        ControlResellButton(
            text = "Secondary Button",
            onClick = {},
            variant = ButtonVariant.Secondary
        )
    }

    add("With Icons") {
        ControlResellButton(
            text = "With Icon",
            onClick = {},
            leadingIcon = {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        )
    }

    add("Disabled") {
        ControlResellButton(
            text = "Disabled Button",
            onClick = {},
            enabled = false
        )
    }

    add("Sizes") {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            ControlResellButton(text = "Small", onClick = {}, size = ButtonSize.Small)
            ControlResellButton(text = "Medium", onClick = {}, size = ButtonSize.Medium)
            ControlResellButton(text = "Large", onClick = {}, size = ButtonSize.Large)
        }
    }
}
```

### Component Guidelines

When creating components:

1. **Composable naming**: Use `ControlResell` prefix (e.g., `ControlResellButton`)
2. **Parameters order**: modifier, required params, optional params, content lambda
3. **Default values**: Provide sensible defaults for all optional parameters
4. **Variants**: Use enums for variants (Primary, Secondary, etc.)
5. **Accessibility**: Include contentDescription for images/icons
6. **Documentation**: Add KDoc comments for public APIs
7. **Testing**: Write tests for different states and interactions
8. **Stories**: Create Storytale stories for visual testing

### Platform-Specific Implementations

Use expect/actual for platform-specific UI:

```kotlin
// commonMain
expect @Composable fun PlatformSpecificComponent()

// androidMain
@Composable
actual fun PlatformSpecificComponent() {
    // Android-specific implementation
}

// iosMain
@Composable
actual fun PlatformSpecificComponent() {
    // iOS-specific implementation
}
```

### Icons

Define custom icons using ImageVector:

```kotlin
object ControlResellIcons {
    val Logo: ImageVector
        get() = ImageVector.Builder(
            name = "Logo",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                pathFillType = PathFillType.EvenOdd
            ) {
                // Path data
                moveTo(12f, 2f)
                lineTo(22f, 12f)
                lineTo(12f, 22f)
                lineTo(2f, 12f)
                close()
            }
        }.build()
}
```

### Publishing

The library is published to Maven for consumption:

```kotlin
// In app's build.gradle.kts
dependencies {
    implementation("com.controlresell:ui:1.0.0")
}

// Usage in app
@Composable
fun MyScreen() {
    ControlResellTheme {
        ControlResellButton(
            text = "Click Me",
            onClick = { /* ... */ }
        )
    }
}
```

### Best Practices

1. **Keep components simple**: Each component should do one thing well
2. **Make components flexible**: Use modifiers and composition
3. **Follow Material Design**: Use Material 3 as foundation
4. **Maintain consistency**: Use theme tokens, not hardcoded values
5. **Document extensively**: Every component should have KDoc
6. **Test thoroughly**: Unit tests and UI tests for all components
7. **Use Storytale**: Develop components in isolation
8. **Version carefully**: Follow semantic versioning

## CI/CD

The project uses GitHub Actions with custom runners:

- **Test Pipeline**: Runs tests for all platforms
- **Build Pipeline**: Builds library artifacts
- **Deploy Pipeline**: Publishes to Maven Central

## Publishing

- Library published to Maven for Kotlin/JVM/Android consumption
- NPM package for JavaScript/TypeScript consumption
- iOS framework for iOS app integration

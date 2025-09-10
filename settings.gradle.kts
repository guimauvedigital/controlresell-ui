pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            // Plugins
            version("kotlin", "2.1.21")
            plugin("multiplatform", "org.jetbrains.kotlin.multiplatform").versionRef("kotlin")
            plugin("android", "org.jetbrains.kotlin.android").versionRef("kotlin")
            plugin("serialization", "org.jetbrains.kotlin.plugin.serialization").versionRef("kotlin")
            plugin("compose-compiler", "org.jetbrains.kotlin.plugin.compose").versionRef("kotlin")
            plugin("compose-hot-reload", "org.jetbrains.compose.hot-reload").version("1.0.0-beta04")
            plugin("compose-multiplatform", "org.jetbrains.compose").version("1.8.1")
            plugin("android-library", "com.android.library").version("8.7.1")
            plugin("storytale", "org.jetbrains.compose.storytale").version("0.0.4-alpha01+dev18")
            plugin("kover", "org.jetbrains.kotlinx.kover").version("0.8.3")
            plugin("detekt", "io.gitlab.arturbosch.detekt").version("1.23.8")
            plugin("ksp", "com.google.devtools.ksp").version("2.1.21-2.0.2")
            plugin("maven", "com.vanniktech.maven.publish").version("0.30.0")
            plugin("pkg", "digital.guimauve.pkg").version("0.1.3")

            // ControlResell
            library("controlresell-core", "com.controlresell:core:1.2.3")
            library("controlresell-core-commons", "com.controlresell:core-commons:1.2.3")
        }
    }
}

rootProject.name = "controlresell-ui"
include(":ui")

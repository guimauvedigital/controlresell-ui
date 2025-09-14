import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.hot.reload)
    alias(libs.plugins.storytale)
    alias(libs.plugins.maven)
}

mavenPublishing {
    publishing {
        repositories {
            publishToMavenCentral(com.vanniktech.maven.publish.SonatypeHost.CENTRAL_PORTAL)
        }
    }
    signAllPublications()
    pom {
        name.set("controlresell-ui")
        description.set("Shared UI components for ControlResell.")
        url.set("https://github.com/guimauvedigital/controlresell-ui")

        licenses {
            license {
                name.set("Apache 2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("NathanFallet")
                name.set("Nathan Fallet")
                email.set("contact@nathanfallet.me")
                url.set("https://www.nathanfallet.me")
            }
        }
        scm {
            url.set("https://github.com/guimauvedigital/controlresell-ui.git")
        }
    }
}

kotlin {
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ControlResellUI"
            isStatic = true
            binaryOption("bundleId", "com.controlresell.ui")
        }
    }

    androidTarget()

    jvm("desktop")

    js {
        browser {
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        add(project.projectDir.path)
                    }
                }
            }
        }
        binaries.executable()
    }

    applyDefaultHierarchyTemplate()

    sourceSets {
        all {
            languageSettings {
                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            }
        }
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.components.resources)

                api(libs.phosphoricon)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.appcompat)
                implementation(libs.androidx.activity.compose)
                implementation(libs.compose.uitooling)
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
                implementation(compose.desktop.currentOs)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(compose.html.core)
            }
        }

        val iosMain by getting {
            dependencies {
            }
        }

    }
}

android {
    namespace = "com.controlresell.ui"
    compileSdk = 35

    buildFeatures {
        compose = true
    }
    defaultConfig {
        minSdk = 26
    }
    sourceSets["main"].apply {
        manifest.srcFile("src/androidMain/AndroidManifest.xml")
        res.srcDirs("src/androidMain/resources")
        resources.srcDirs("src/commonMain/resources")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.controlresell.ui"
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.controlresell.ui"
            packageVersion = "1.0.0"
        }
    }
}

compose.web {

}

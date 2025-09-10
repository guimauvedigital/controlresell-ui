import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.multiplatform)
    //alias(libs.plugins.android.library)
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
            pkg(project)
        }
    }
    signAllPublications()
    pom {
        name.set("controlresell-ui")
        description.set("Shared UI components for ControlResell.")
        url.set("https://github.com/guimauvedigital/controlresell-ui")

        licenses {
            license {
                name.set("GPL-3.0")
                url.set("https://opensource.org/licenses/GPL-3.0")
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
    applyDefaultHierarchyTemplate()

    //androidTarget()

    jvm("desktop")

    /*
    js {
        browser()
        binaries.executable()
    }
     */

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        all {
            languageSettings {
                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            }
        }
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        /*
        val androidMain by getting {
            dependencies {
                implementation("androidx.appcompat:appcompat:1.7.1")
                implementation("androidx.activity:activity-compose:1.10.1")
                implementation("androidx.compose.ui:ui-tooling:1.9.0")
                //implementation(libs.androidx.appcompat)
                //implementation(libs.androidx.activityCompose)
                //implementation(libs.compose.uitooling)
            }
        }
         */

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
                implementation(compose.desktop.currentOs)
            }
        }

        /*
        val jsMain by getting {
            dependencies {
                implementation(compose.html.core)
            }
        }
         */

        val iosMain by getting {
            dependencies {
            }
        }

    }
}

/*
android {
    namespace = "com.controlresell.ui"
    compileSdk = 35

    buildFeatures {
        compose = false
    }
    defaultConfig {
        minSdk = 21
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
 */

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

/*
compose.experimental.web {
    application {}
}
 */

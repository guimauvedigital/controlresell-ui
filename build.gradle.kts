plugins {
    alias(libs.plugins.multiplatform) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.maven) apply false
    alias(libs.plugins.pkg)
}

allprojects {
    group = "com.controlresell"
    version = "0.1.1"

    repositories {
        google()
        mavenCentral()
        pkg(project)
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        mavenLocal()
    }
}

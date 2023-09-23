buildscript {
    dependencies {
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.3")
    }
}
plugins {
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id ("com.android.application") version "8.1.1" apply false
    id ("com.google.devtools.ksp") version "1.9.0-1.0.11" apply false
    id ("com.android.library") version "8.1.1" apply false
    id ("org.jetbrains.kotlin.android") version "1.8.20" apply false
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    id("org.sonarqube") version "5.0.0.4638"
}
buildscript {
    repositories {
        google()
    }
    dependencies {
        val nav_version = "2.6.0"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    }
}
true // Needed to make the Suppress annotation work for the plugins block

sonar {
  properties {
    property("sonar.projectKey", "GroceriesDevOpsOrg_andriod-launcher-v2.0_837835ea-ff0a-431b-b917-92754453755a")
    property("sonar.projectName", "andriod-launcher-v2.0")
  }
}

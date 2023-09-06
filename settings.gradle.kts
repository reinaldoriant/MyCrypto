pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MyCrypto"
include(":app")
include(":feed_feature:feed_domain")
include(":feed_feature:feed_http")
include(":feed_feature:feed_cache")
include(":feed_feature:feed_presentation")
include(":detail_feature:detail_ui")
include(":decorator")
include(":shared")

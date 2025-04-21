pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "MoviesJetpack"
include(":app")

// Core
include(":core")

// Network
include(":core:network")
include(":core:network:public")
include(":core:network:impl")

include(":core:shared")
include(":splash")
include(":core:storage")
include(":core:storage:public")
include(":core:storage:impl")

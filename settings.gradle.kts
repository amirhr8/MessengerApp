pluginManagement {
    repositories {
//        google {
//            content {
//                includeGroupByRegex("com\\.android.*")
//                includeGroupByRegex("com\\.google.*")
//                includeGroupByRegex("androidx.*")
//            }
//        }
        google()
        mavenCentral()
        gradlePluginPortal()
       maven (url = uri("https://maven.myket.ir"))
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven (url = uri("https://maven.myket.ir"))
    }
}

rootProject.name = "MessengerApp"
include(":app")
 
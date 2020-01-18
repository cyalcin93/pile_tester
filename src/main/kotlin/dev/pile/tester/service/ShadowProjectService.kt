package dev.pile.tester.service

import dev.pile.tester.dto.ProjectSettings

fun setupShadowProject(settings: ProjectSettings) {
    createDirectory("${settings.name}/src")
    createDirectory("${settings.name}/src/main")
    createDirectory("${settings.name}/src/main/resources")
    createDirectory("${settings.name}/src/main/kotlin")
    createDirectory("${settings.name}/src/main/kotlin/pile")
    createDirectory("${settings.name}/src/main/kotlin/pile/tester")
    createDirectory("${settings.name}/src/main/kotlin/pile/tester/${settings.name}")
    createDirectory("${settings.name}/src/test")
    createDirectory("${settings.name}/src/test/kotlin")

    setupMainFile(settings)
}

private fun setupMainFile(settings: ProjectSettings) {
    createFile("${settings.name}/src/main/kotlin/pile/tester/${settings.name}/Main.kt")
}
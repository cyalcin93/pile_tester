package dev.pile.tester.service

import dev.pile.tester.dto.ProjectSettings

fun setupShadowProject(settings: ProjectSettings) {
    FileService.createDirectory("${settings.name}/src")
    FileService.createDirectory("${settings.name}/src/main")
    FileService.createDirectory("${settings.name}/src/main/resources")
    FileService.createDirectory("${settings.name}/src/main/kotlin")
    FileService.createDirectory("${settings.name}/src/main/kotlin/pile")
    FileService.createDirectory("${settings.name}/src/main/kotlin/pile/tester")
    FileService.createDirectory("${settings.name}/src/main/kotlin/pile/tester/${settings.name}")
    FileService.createDirectory("${settings.name}/src/test")
    FileService.createDirectory("${settings.name}/src/test/kotlin")

    setupMainFile(settings)
}

private fun setupMainFile(settings: ProjectSettings) {
    FileService.createFile("${settings.name}/src/main/kotlin/pile/tester/${settings.name}/Main.kt")
}
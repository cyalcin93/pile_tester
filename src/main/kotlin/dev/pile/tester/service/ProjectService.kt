package dev.pile.tester.service

import dev.pile.tester.dto.*
import dev.pile.tester.util.YAMLParse
import java.io.File

var projectRoot = "."  // fixme get this from somewhere

fun createProject(projectName: String) {
    setupProjectRootDirectory(projectName)
    val projectSettings = setupProjectSettings(projectName)
    setupProjectDirectory(projectSettings)
}

fun isValidProjectDirectory(): Message? {
    if(!checkIfProjectSettingsExists(projectRoot)) {
        return Message(true, "settings could not be found")
    }
    val projectSettings = YAMLParse.parseDto("settings.v1.yaml", ProjectSettings::class)

    checkIfValidProjectSettings(projectSettings)?.takeIf { it.error }?.apply { return this }

    return null
}

fun getAllSettingsFiles(path: String) = File(path).
        walk().
        filter { it.name.endsWith(".yaml") }.
        filter { it.name.startsWith("settings.v") }


fun checkIfProjectSettingsExists(path: String) =
        getAllSettingsFiles(path).
        count().
        let { if (it > 0) return true else false }

fun addSuiteToProject(suite: SuiteSettings) {
    val projectSettings = getLatestProjectSettings()
    val lastIndex = projectSettings.suites.size
    suite.order = lastIndex
    projectSettings.suites.add(suite)
    projectSettings.version++

    YAMLParse.parseFile(projectSettings,
            "settings.v${projectSettings.version}.yaml")
}

private fun getVersionFromSettingsFileName(file: File): Int = file.nameWithoutExtension.substringAfter("settings.v").toInt()

fun getLatestProjectSettings(): ProjectSettings {
    val file = getAllSettingsFiles(projectRoot).maxBy { file -> getVersionFromSettingsFileName(file) }

    return YAMLParse.parseDto("$projectRoot/${file?.name}", ProjectSettings::class)
}


private fun setupProjectRootDirectory(projectName: String) {
    createDirectory(projectName)
}

private fun checkIfValidProjectSettings(projectSettings: ProjectSettings): Message? {
    if(projectSettings.type != "project_type") {
        return Message(true, "settings.yaml is not of type 'project_type'")
    }

    checkIfFileExists("${projectSettings.name}/SetupProject.kt").takeIf { !it}?.apply {
        return Message(true, "setup file named " +
                "${projectSettings.setup_file} could not be found")
    }

    return null
}

private fun setupProjectSettings(projectName: String): ProjectSettings {
    val settingsDTO = SettingsFactory.create(projectName, SettingsType.PROJECT)
    createYAMLSettingsFile(settingsDTO, "$projectName/settings.v1.yaml")

    return settingsDTO as ProjectSettings
}

private fun setupProjectDirectory(settings: ProjectSettings) {
    createFile("${settings.name}/SetupProject.kt")
    setupShadowProject(settings)
}
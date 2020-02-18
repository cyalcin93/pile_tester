package dev.pile.tester.service

import dev.pile.tester.dto.Message
import dev.pile.tester.dto.SettingsFactory
import dev.pile.tester.dto.SettingsType
import dev.pile.tester.dto.SuiteSettings
import java.lang.Exception


object SuiteService {
    fun createSuite(name: String, description: String) {
        val suiteSettings = setupSuiteSettings(name, description)
        setupSuiteRootDirectory(name)
        ProjectService.addSuiteToProject(suiteSettings)
        setupSuiteDirectory(suiteSettings)
    }

    fun isValidSuiteDirectory(): Message? {

        try {
            ProjectService.isValidProjectDirectory()
        } catch (e: Exception) {
            println("errrorrr")
        }
//    if(!checkIfFileExists("settings.yaml")) {
//        return Message(true, "settings.yaml could not be found")
//    }
//    val suiteSettings = YAMLParse.parseDto("settings.yaml", SuiteSettings::class)
//    checkIfValidSuiteSettings(suiteSettings)?.takeIf { it.error }?.apply { return this }

        return null
    }


    private fun setupSuiteRootDirectory(suiteName: String) {
        FileService.createDirectory(suiteName)
    }

    private fun checkIfValidSuiteSettings(suiteSettings: SuiteSettings): Message? {
        if(suiteSettings.type != "suite_type") {
            return Message(true, "settings.yaml is not of type 'suite_type'")
        }

        return null
    }

    private fun setupSuiteSettings(suiteName: String, description: String): SuiteSettings {
        val settingsDTO = SettingsFactory.create(suiteName, SettingsType.REST_SUITE) as SuiteSettings
        settingsDTO.description = description
        createYAMLSettingsFile(settingsDTO, "../settings.yaml")

        return settingsDTO
    }

    private fun setupSuiteDirectory(settings: SuiteSettings) {
        FileService.createFile("${settings.name}/SetupSuite.kt")
        FileService.createFile("${settings.name}/TeardownSuite.kt")
    }
}
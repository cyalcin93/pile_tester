package dev.pile.tester.service

import dev.pile.tester.dto.*
//import dev.pile.tester.util.YAMLParse

fun createSuite(name: String, description: String) {
    val suiteSettings = setupSuiteSettings(name, description)
    setupSuiteRootDirectory(name)
    addSuiteToProject(suiteSettings)
    setupSuiteDirectory(suiteSettings)
}

fun isValidSuiteDirectory(): Message? {
//    if(!checkIfFileExists("settings.yaml")) {
//        return Message(true, "settings.yaml could not be found")
//    }
//    val suiteSettings = YAMLParse.parseDto("settings.yaml", SuiteSettings::class)
//
//    checkIfValidSuiteSettings(suiteSettings)?.takeIf { it.error }?.apply { return this }

    return null
}


private fun setupSuiteRootDirectory(suiteName: String) {
    createDirectory(suiteName)
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
    createFile("${settings.name}/SetupSuite.kt")
    createFile("${settings.name}/TeardownSuite.kt")
}
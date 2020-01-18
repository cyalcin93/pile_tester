package dev.pile.tester.dto

interface Settings {
    var name: String
    var setup_file: String
    var type: String
}

enum class SettingsType(val realName: String) {
    PROJECT("project_type"), REST_SUITE("rest_suite_type"),
    SELENIUM_SUITE("selenium_suite_type")
}

object SettingsFactory {
    fun create(name: String, settingsType: SettingsType): Settings = when (settingsType) {
        SettingsType.PROJECT -> ProjectSettings(name, settingsType.realName)
        SettingsType.REST_SUITE, SettingsType.SELENIUM_SUITE -> SuiteSettings(name, settingsType.realName)
    }
}

data class ProjectSettings(
        override var name: String,
        override var type: String,
        override var setup_file: String = "SetupProject.kt",

        var suites: MutableList<SuiteSettings> = mutableListOf(),
        var version: Int = 1,
        var global_variables: LinkedHashMap<String, *> =
           linkedMapOf(
               "exampleGlobalVar" to "yolo",
               "exampleGlobalIntVar" to 1337)): Settings


data class SuiteSettings(
        override var name: String,
        override var type: String,
        override var setup_file: String = "SetupSuite.kt",
        var order: Int = 0,
        var description: String = "",
        var tests: List<String> = emptyList()): Settings
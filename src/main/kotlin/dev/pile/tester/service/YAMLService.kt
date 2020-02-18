package dev.pile.tester.service

import dev.pile.tester.dto.Settings
import dev.pile.tester.util.YAMLParse

fun createYAMLSettingsFile(settingsDTO: Settings, pathName: String) {
    FileService.createFile(pathName)
    YAMLParse.parseFile(settingsDTO, pathName)
}
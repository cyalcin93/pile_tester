package dev.pile.tester.service

import java.io.File

enum class FilePermission {
    READ_ONLY, ALL
}

fun createDirectory(directoryPath: String): Boolean {
    val directory = File(directoryPath)
    if(!directory.mkdirs()) return false

    return true
}

fun createFile(filePath: String): Boolean {
    val file = File(filePath)
    if(!file.createNewFile()) return false

    return true
}

private fun setPermission(file: File, permission: FilePermission) {
    when (permission) {
        FilePermission.READ_ONLY -> {
            file.setReadOnly()
        }
        FilePermission.ALL -> {}
    }
}

fun getAllSettingsFiles() = File(".").
        walkBottomUp().
        filter { it.name.endsWith(".yaml") }.
        filter { it.name.startsWith("settings.v") }

fun checkIfProjectSettingsExists() = File(".").
        walkBottomUp(). //
        filter { it.name.endsWith(".yaml") }.
        filter { it.name.startsWith("settings.v") }.
        count().
        let { if (it > 0) return true else false }

fun checkIfFileExists(filePath: String) = File(filePath).exists()

fun checkIfDirectoryExists(directoryPath: String) =
        File(directoryPath).exists() && File(directoryPath).isDirectory
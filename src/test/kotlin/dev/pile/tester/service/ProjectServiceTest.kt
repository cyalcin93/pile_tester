package dev.pile.tester.service

import org.junit.jupiter.api.*
import java.io.File

private const val directoryPath = "src/test/resources/project_service_testing"

private data class SampleClass(val name: String, val version: Int)

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProjectServiceTest {

    @BeforeAll
    fun setupSuite() {
        projectRoot = "$directoryPath/sampleProject"
    }

    @AfterAll
    fun cleanUp() {
    }

    @Test
    fun test_getAllSettingsFiles() {
        val settingsFiles = ProjectService.getAllSettingsFiles("$directoryPath/sampleProject")
        Assertions.assertEquals(3, settingsFiles.count())

        Assertions.assertEquals(
                settingsFiles.filter { it.name == "settings.v1.yaml" }.count(),
                1)

        Assertions.assertEquals(
                settingsFiles.filter { it.name == "settings.v2.yaml" }.count(),
                1)

        Assertions.assertEquals(
                settingsFiles.filter { it.name == "settings.v3.yaml" }.count(),
                1)

        Assertions.assertEquals(
                settingsFiles.filter { it.name == "settings.v4.yaml" }.count(),
                0)

    }

    @Test
    fun test_checkIfProjectSettingsExists() {
        val exists = ProjectService.checkIfProjectSettingsExists("$directoryPath/sampleProject")
        Assertions.assertTrue(exists)

        val doesNotExist = ProjectService.checkIfProjectSettingsExists("$directoryPath/empty")
        Assertions.assertFalse(doesNotExist)
    }

    @Test
    fun test_getLatestProjectSettings() {
        val projectSettings = ProjectService.getLatestProjectSettings()
        Assertions.assertEquals("ohboy", projectSettings.name)
        Assertions.assertEquals(3, projectSettings.version)
        Assertions.assertEquals("yolo", projectSettings.global_variables["exampleGlobalVar"])
    }


    @Test
    fun test_isValidProjectDirectory() {
        //isValidProjectDirectory()

    }


}
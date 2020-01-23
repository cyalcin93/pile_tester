package dev.pile.tester.service

import org.junit.jupiter.api.*
import java.io.File

private const val directoryPath = "src/test/resources/file_service_testing"

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FileServiceTest {

    @BeforeAll
    fun setupSuite() {


    }

    @AfterAll
    fun cleanUp() {
        File("$directoryPath/createFile.txt").delete()
        File("$directoryPath/permissionFile.txt").delete()
        File("$directoryPath/createDirectory").delete()
    }

    @Test
    fun test_checkIfFileExists() {
        val exists = checkIfFileExists("$directoryPath/findThisFile.txt")
        Assertions.assertTrue(exists)

        val doesNotExist = checkIfFileExists("$directoryPath/cantFindIt.xml")
        Assertions.assertFalse(doesNotExist)
    }


    @Test
    fun test_checkIfDirectoryExists() {
        val exists = checkIfDirectoryExists("$directoryPath/findThisDirectory")
        Assertions.assertTrue(exists)

        val doesNotExist = checkIfDirectoryExists("$directoryPath/cantFindIt")
        Assertions.assertFalse(doesNotExist)
    }

    @Test
    fun test_createDirectory() {
        val exists = createDirectory("$directoryPath/findThisDirectory")
        Assertions.assertFalse(exists)

        val doesNotExist = createDirectory("$directoryPath/createDirectory")
        Assertions.assertTrue(doesNotExist)
    }

    @Test
    fun test_createFile() {
        val exists = createFile("$directoryPath/findThisFile.txt")
        Assertions.assertFalse(exists)

        val doesNotExist = createFile("$directoryPath/createFile.txt")
        Assertions.assertTrue(doesNotExist)
    }

    @Test
    fun test_setFilePermission() {
        val file = File("$directoryPath/permissionFile.txt")
        file.createNewFile()

        setFilePermission(file, FilePermission.READ_ONLY)
        Assertions.assertTrue(file.canRead())
        Assertions.assertFalse(file.canWrite())
        Assertions.assertFalse(file.canExecute())

        setFilePermission(file, FilePermission.ALL)
        Assertions.assertTrue(file.canRead())
        Assertions.assertTrue(file.canWrite())
        Assertions.assertTrue(file.canExecute())
    }

}
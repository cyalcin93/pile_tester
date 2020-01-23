package dev.pile.tester.util

import org.junit.jupiter.api.*
import java.io.File

const val directoryPath = "src/test/resources/yaml_parse_testing"

private data class SampleClass(val name: String, val version: Int)

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class YamlParseTest {

    @BeforeAll
    fun setupSuite() {
    }

    @AfterAll
    fun cleanUp() {
        File("$directoryPath/test.yaml").delete()
    }

    @Test
    fun test_parseFile() {
        val sampleClass = SampleClass("testParseDto", 7)
        YAMLParse.parseFile(sampleClass, "$directoryPath/test.yaml")
        Assertions.assertTrue(File("$directoryPath/test.yaml").exists())
    }

    @Test
    fun test_parseDto() {
        val sampleClass = YAMLParse.parseDto("$directoryPath/parseThisFile.yaml", SampleClass::class)
        Assertions.assertEquals("parseThisFile", sampleClass.name)
        Assertions.assertEquals(9, sampleClass.version)
    }
}
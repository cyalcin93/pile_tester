package dev.pile.tester.util

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.io.File
import java.nio.file.FileSystems
import java.nio.file.Files
import kotlin.reflect.KClass

object YAMLParse {

    private val mapper = let {
        val mapper = ObjectMapper(YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER))
        mapper.registerModule(KotlinModule())
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
        mapper
    }

    /**
     * Takes in a data class (with ::class) and parses it by the fileName provided, returning the appropriate class
     * originally provided with parsed data.
     */
    fun <T: Any> parseDto(fileName: String, dto: KClass<T>): T {
        return Files.newBufferedReader(FileSystems.getDefault().getPath(fileName)).use { mapper.readValue(it, dto.java) }
    }

    /**
     * Takes in the loaded DTO class and parses the data over to your file
     */
    fun <T: Any> parseFile(dto: T, fileName: String) {
        mapper.writeValue(File(fileName), dto)
    }
}
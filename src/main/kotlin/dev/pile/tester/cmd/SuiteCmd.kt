package dev.pile.tester.cmd

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt
import dev.pile.tester.service.createSuite
import dev.pile.tester.service.isValidSuiteDirectory

class CreateSuite: CliktCommand() {
    val name: String by option(help = "Name")
            .prompt("Suite name")
    private val description: String by option(help = "Description")
            .prompt("Suite description")
    override fun run() {
        echo("Attempting to create $name suite with the description:\n\"$description\"")
        createSuite(name, description)
    }

}

class Suite : CliktCommand() {

    class Run: CliktCommand() {
        override fun run() {
            // fixme run all tests, allow appropriate flags
        }
    }

    override fun run() {
        isValidSuiteDirectory()
    }
}
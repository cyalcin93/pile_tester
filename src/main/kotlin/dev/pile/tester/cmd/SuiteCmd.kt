package dev.pile.tester.cmd

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt
import dev.pile.tester.service.ProjectService
import dev.pile.tester.service.SuiteService

class CreateSuite: CliktCommand(name = "suite") {
    val name: String by option(help = "Name")
            .prompt("Suite name")
    private val description: String by option(help = "Description")
            .prompt("Suite description")
    override fun run() {
        echo("Attempting to create $name suite with the description:\n\"$description\"")
        try {
            ProjectService.isValidProjectDirectory()
            SuiteService.createSuite(name, description)
        } catch (e: Exception) {
            echo(e.message)
            echo("FAIL")
        }
    }

}

class Suite : CliktCommand() {

    class Run: CliktCommand() {
        override fun run() {
            // fixme run all tests, allow appropriate flags
        }
    }

    override fun run() {
        SuiteService.isValidSuiteDirectory()
    }
}
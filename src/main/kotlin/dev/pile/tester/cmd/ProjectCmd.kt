package dev.pile.tester.cmd

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt
import dev.pile.tester.service.ProjectService

class CreateProject : CliktCommand(name = "project") {
    val name: String by option(help = "Name")
            .prompt("Project name")

    override fun run() {
        echo("Attempting to create project with name $name")

        try {
            ProjectService.createProject(name)
            echo("Project directory for $name was created successfully")
        } catch (e: Exception) {
            echo(e.message)
            echo("FAIL")
        }
    }
}

class Project : CliktCommand() {

    class Build: CliktCommand() {
        override fun run() {
            // fixme build the kotlin code
        }
    }

    class Run: CliktCommand() {
        override fun run() {
            // fixme run all tests, allow appropriate flags
        }
    }

    override fun run() {
        ProjectService.isValidProjectDirectory()
    }
}
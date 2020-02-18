package dev.pile.tester

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import dev.pile.tester.cmd.*

fun main(args: Array<String>) {
    Pile()
        .subcommands(
                New().subcommands(
                        CreateProject(),
                        CreateSuite()
                ),
                Project().subcommands(
                        Project.Build(),
                        Project.Run()
                ),
                Suite().subcommands(
                        Suite.Run()
                )
        ).main(args)
}

class New : CliktCommand() {
    override fun run() {}
}
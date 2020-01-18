package dev.pile.tester

import com.github.ajalt.clikt.core.subcommands
import dev.pile.tester.cmd.*

fun main(args: Array<String>) {
    Pile()
        .subcommands(
                CreateProject(),
                Project().subcommands(
                        Project.Build(),
                        Project.Run(),
                        CreateSuite()
                ),
                Suite().subcommands(
                        Suite.Run()
                )
        ).main(args)
}
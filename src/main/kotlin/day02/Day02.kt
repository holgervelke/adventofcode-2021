package day02

import java.io.File

fun readFile(filename: String): String = File(filename).readText()
fun readInput(filename: String): List<Command> =
    readFile(filename).split("\n")
        .map { line -> line.split(" ") }
        .map { parts -> Command(direction = parts[0], amount = parts[1].toInt()) }

fun move(position: Position, commands: List<Command>) =
    commands.fold(position) { currentPos, command -> move(currentPos, command) }

fun move(position: Position, command: Command): Position = when (command.direction) {
    "forward" -> Position(position.horizontal + command.amount, position.depth)
    "down" -> Position(position.horizontal, position.depth + command.amount)
    "up" -> Position(position.horizontal, position.depth - command.amount)
    else -> position
}

fun moveAimed(positionWithAim: PositionWithAim, commands: List<Command>) =
    commands.fold(positionWithAim) { currentPos, command -> moveAimed(currentPos, command) }

fun moveAimed(positionWithAim: PositionWithAim, command: Command): PositionWithAim = when (command.direction) {
    "forward" -> PositionWithAim(
        Position(
            positionWithAim.position.horizontal + command.amount,
            positionWithAim.position.depth + (command.amount * positionWithAim.aim)
        ), positionWithAim.aim
    )
    "down" -> PositionWithAim(positionWithAim.position, positionWithAim.aim + command.amount)
    "up" -> PositionWithAim(positionWithAim.position, positionWithAim.aim - command.amount)
    else -> positionWithAim
}

class Command(val direction: String, val amount: Int)

class Position(var horizontal: Int, var depth: Int)

class PositionWithAim(val position: Position, val aim: Int)

fun main() {
    val commands: List<Command> = readInput("src/main/resources/day02/input.txt")
    val resultPart1: Position = move(Position(0, 0), commands)
    println("final horizontal position: ${resultPart1.horizontal} final depth ${resultPart1.depth}")
    println("What do you get if you multiply your final horizontal position by your final depth? ${resultPart1.horizontal * resultPart1.depth}")

    val resultPart2: Position = moveAimed(PositionWithAim(Position(0, 0), 0), commands).position
    println("final horizontal position: ${resultPart2.horizontal} final depth ${resultPart2.depth}")
    println("What do you get if you multiply your final horizontal position by your final depth? ${resultPart2.horizontal * resultPart2.depth}")
}
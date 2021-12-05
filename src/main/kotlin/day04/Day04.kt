package day04

import utils.readFile

fun readInput(filename: String): Input {
    val splits = readFile(filename).split("\n\n")
    val numbers = splits[0].split(",")
    val boards = splits.drop(1).map { boardString -> boardString.split("\n").map { line -> line.split(" ").filter { e -> e != "" } } }
    return Input(numbers, boards)
}

class Input(val numbers: List<String>, val boards: List<List<List<String>>>)

fun part1(input: Input): Int {
    val boards = input.boards.map { b -> Board(b) }
    input.numbers.forEach{n ->
        run {
            boards.forEach { b ->
                run {
                    b.draw(n)
                }
            }
            if (boards.any{ b -> b.finished() }) {
                return boards.first{ b -> b.finished() }.result(n)
            }
        }
    }
    return 0
}

fun part2(input: Input): Int {
    var boards = input.boards.map { b -> Board(b) }
    input.numbers.forEach{n ->
        run {
            boards.forEach { b ->
                run {
                    b.draw(n)
                }
            }
            if (boards.size > 1) {
                boards = boards.filter { b -> !b.finished() }
            } else if (boards.first().finished()) {
                return boards.first().result(n)
            }
        }
    }
    return 0
}

class Board(private var board: List<List<String>>) {

    private val selected: String = "x"

    fun draw(x: String) {
        board = board.map{
            line -> line.map { n -> if (x == n) selected else n }
        }
    }

    fun result(nStr: String) :Int {
        val number = nStr.toInt()
        val boardSum = board.flatMap { line -> line.map{ n -> if (n == selected) 0 else n.toInt()} }
            .reduce{acc, n -> acc + n }
        return number * boardSum
    }

    fun finished(): Boolean {
        return linesFinished() || colsFinished()
    }

    private fun linesFinished(): Boolean = board.any { line -> line.all { n -> n == selected } }

    private fun colsFinished(): Boolean {
        return board.first().mapIndexed{index, _ -> board.all{line -> line[index] == selected}}.any{ bool -> bool }
    }
}

fun main() {
    val input1 = readInput("src/main/resources/day04.txt")
    println("final score for first winning board ${part1(input1)}")
    val input2 = readInput("src/main/resources/day04.txt")
    println("final score for last winning board ${part2(input2)}")
}
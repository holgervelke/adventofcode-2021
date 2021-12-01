package day01

import java.io.File

fun readFile(filename: String): String = File(filename).readText()

fun readInput(filename: String): List<Int> = readFile(filename).split("\n").map { s -> s.toInt() }

fun numberOfIncreased(depthArray: List<Int>) = depthArray.windowed(2, 1, false)
    .map { pair -> pair[0] < pair[1] }
    .map { increased -> if (increased) 1 else 0 }
    .reduce { acc, next -> acc + next }

fun numberOfIncreasedWithSlidingWindow(depthArray: List<Int>): Int {
    val windowedDepthArray = depthArray.windowed(5, 1, false)
        .map { window -> window.reduce { acc, next -> acc + next } }
    return numberOfIncreased(windowedDepthArray)
}

fun main() {
    val input = readInput("src/main/resources/day01/part-1.txt")
    val count = numberOfIncreased(input)
    println("Day 1")
    println("$count measurements are larger than the previous measurement")
    val countWindows = numberOfIncreasedWithSlidingWindow(input)
    println("$countWindows sums are larger than the previous sum")
}
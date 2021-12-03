package day03

import java.io.File

fun readFile(filename: String): String = File(filename).readText()
fun readInput(fileName: String) : List<List<Int>> =
    readFile(fileName).split("\n")
        .map { line -> line.toCharArray() }
        .map { charArray -> charArray.map { c -> c.toString().toInt() }}

fun part1(input: List<List<Int>>): Int {
    val gammaBits = toGammaBits(input)
    val epsilonBits = gammaBits.map { element -> if (element == 1) 0 else 1 }
    val gamma = toInt(gammaBits)
    val epsilon = toInt(epsilonBits)
    return gamma * epsilon
}

fun part2(input: List<List<Int>>): Int {
    val oxygen = lifeSupportRating(input,0) { e -> e }
    val co2 = lifeSupportRating(input,0) { e -> if (e == 1) 0 else 1 }
    return oxygen * co2
}

fun lifeSupportRating(input: List<List<Int>>, index: Int, bitCrit: (Int) -> Int): Int {
    if (input.size == 1) return toInt(input[0])
    return lifeSupportRating(filter(input, index, toGammaBits(input).map(bitCrit)), index+1, bitCrit)
}

fun toGammaBits(input: List<List<Int>>) : List<Int> = input
    .reduce{ acc, list -> acc.mapIndexed{ index, element -> element + list[index]} }
    .map { element -> if (element >= (input.size / 2.0)) 1 else 0 }

fun filter(input: List<List<Int>>, index: Int, significantBits: List<Int>): List<List<Int>> {
    val value = significantBits[index]
    return input.filter { element -> element[index] == value }
}

fun toInt(bits: List<Int>): Int = bits.reduce{ acc, element -> (acc * 2)+element}

fun main() {
    val input = readInput("src/main/resources/day03.txt")
    println("power consumption of the submarine: ${part1(input)}") // 4139586
    println("life support rating of the submarine ${part2(input)}") // 1800151
}
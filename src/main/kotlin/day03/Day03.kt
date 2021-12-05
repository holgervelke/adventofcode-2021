package day03

import utils.readFile

fun readInput(fileName: String) : List<List<Int>> =
    readFile(fileName).split("\n")
        .map { line -> line.toCharArray() }
        .map { charArray -> charArray.map { c -> c.toString().toInt() }}

val oneIsSignificant: (Int) -> Int = { e -> e }
val zeroIsSignificant: (Int) -> Int = { e -> if (e == 1) 0 else 1 }

fun part1(input: List<List<Int>>): Int {
    val gamma = powerRating(input, oneIsSignificant)
    val epsilon = powerRating(input, zeroIsSignificant)
    return gamma * epsilon
}

fun powerRating(input: List<List<Int>>, bitCrit: (Int) -> Int) = toInt(significantBits(input,bitCrit))

fun part2(input: List<List<Int>>): Int {
    val oxygen = lifeSupportRating(input,0, oneIsSignificant)
    val co2 = lifeSupportRating(input,0, zeroIsSignificant)
    return oxygen * co2
}

fun lifeSupportRating(input: List<List<Int>>, index: Int, bitCrit: (Int) -> Int): Int {
    if (input.size == 1) return toInt(input[0])
    return lifeSupportRating(filter(input, index, significantBits(input, bitCrit)), index+1, bitCrit)
}

fun significantBits(input: List<List<Int>>, bitCrit: (Int) -> Int) : List<Int> = input
    .reduce{ acc, list -> acc.mapIndexed{ index, element -> element + list[index]} }
    .map { element -> if (element >= (input.size / 2.0)) 1 else 0 }
    .map(bitCrit)

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
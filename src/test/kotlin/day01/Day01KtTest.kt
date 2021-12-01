package day01

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.test.expect

internal class Day01KtTest {

    @Test
    fun numberOfIncreased() {
        val input = readInput("src/main/resources/day01/part-1-test.txt")
        assertEquals(7, numberOfIncreased(input))
    }

    @Test
    fun numberOfIncreasedWithSlidingWindow() {
        val input = readInput("src/main/resources/day01/part-1-test.txt")
        assertEquals(5, numberOfIncreasedWithSlidingWindow(input))
    }
}
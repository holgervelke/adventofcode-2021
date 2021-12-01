val input: String = "199\n" +
            "200\n" +
            "208\n" +
            "210\n" +
            "200\n" +
            "207\n" +
            "240\n" +
            "269\n" +
            "260\n" +
            "263"

fun numberOfIncreased(input: String) : Int {
    val depthArray = input.split("\n").map { s -> s.toInt() }
    val result = depthArray.foldIndexed(0) {index, count, depth ->
            var result: Int = count
            if (index > 0) {
                val prevDepth = depthArray[index-1]
                if (depth > prevDepth) result = count + 1
            }
            result
        }
    return result
}

fun main(args: Array<String>) {
    val count = numberOfIncreased(input)
    println("Day 1")
    println("${count} measurements are larger than the previous measurement")
}
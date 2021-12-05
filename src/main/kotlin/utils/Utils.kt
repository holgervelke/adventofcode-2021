package utils

import java.io.File

fun readFile(filename: String): String = File(filename).readText()
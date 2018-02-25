package io.levelsoftware.sandbox.kotlin

import java.util.*
import kotlin.streams.asSequence
import kotlin.system.measureNanoTime

fun logMessage(message: String) {
    val lastItem = Thread.currentThread().stackTrace.last()
    println("[$lastItem:${Thread.currentThread().name}]: $message")
}

fun measureAverageRuntime(runs: Int = 10000, code: () -> Unit): Long {
    var sum = 0L
    (0 until runs).forEach { sum += measureNanoTime(code) }
    return sum / runs
}

fun determineStackSize(count: Int = 1) {
    try {
        determineStackSize(count + 1)
    } catch(exception: StackOverflowError) {
        System.err.print("Stack size $count\n")
    }
}

// https://stackoverflow.com/a/46944275/5125812
fun getRandomString(length: Long): String {
    val source = "abcdefghijklmnopqrstuvwxyz"
    return Random().ints(length, 0, source.length)
        .asSequence()
        .map(source::get)
        .joinToString("")
}
package io.levelsoftware.sandbox.kotlin

fun logMessage(message: String) {
    val lastItem = Thread.currentThread().stackTrace.last()
    println("[$lastItem:${Thread.currentThread().name}]: $message")
}
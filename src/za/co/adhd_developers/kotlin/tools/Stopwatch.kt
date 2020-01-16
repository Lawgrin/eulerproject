package za.co.adhd_developers.kotlin.tools

import za.co.adhd_developers.kotlin.convertTime

class Stopwatch {

    var startTime: Long = -1L

    fun mark() {
        startTime = System.nanoTime()
    }

    fun lap(): String {
        val endTime = System.nanoTime()
        val timeTaken = endTime - startTime
        return if (timeTaken > 0) convertTime(timeTaken) else ""
    }
}
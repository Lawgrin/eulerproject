package za.co.adhd_developers.kotlin

import java.util.*


internal var questions = intArrayOf(0)
internal var start = 1
internal var end = 28

fun main(args: Array<String>) {

    if (questions.isEmpty()) {
        questions = IntArray((end - start) + 1)
        for ((index, i) in (start..end).withIndex()) {
            questions[index] = i
        }
    }

    for (questNum in questions) {
        try {
            val cls = Class.forName("za.co.adhd_developers.kotlin.questions.Question" + questNum.toString())

            val question = cls.newInstance()
            if (question is Question) {
                val startTime = Date().time
                question.doWork()
                val endTime = Date().time
                val timeTaken = endTime - startTime
                question.printAnswer()
                println("Time taken: $timeTaken(ms)")
            }
        } catch (e: ClassNotFoundException) {
            println("No Class with name Question$questNum")
            e.printStackTrace()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

    }

}
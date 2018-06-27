package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import java.util.*

class Question19 : Question {

    var answer = 0

    override fun doWork() {
        val calendar = Calendar.getInstance()
        for (year in 1901 until 2001) {
            for (month in 1 until 13) {
                calendar.set(year, month, 1)

                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                    this.answer++
                }
            }
        }
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 19")
        println("The number of Sundays that fall on the first of the month, is: " + this.answer)
        println("=================================")
    }
}
package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import za.co.adhd_developers.kotlin.tools.getFactors

class Question12 : Question {

    var answer = 0L

    override fun doWork() {
        var currentNumber = 1L
        var currentTotal = 1L
        while (answer == 0L) {
            currentNumber++
            currentTotal += currentNumber
            if (getFactors(currentTotal).size > 500) {
                this.answer = currentTotal
            }
        }
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 12")
        println("The first triangle number to have over 5 divisors, is: " + this.answer)
        println("=================================")
    }
}
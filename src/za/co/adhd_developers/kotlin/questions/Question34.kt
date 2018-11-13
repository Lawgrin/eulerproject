package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import za.co.adhd_developers.kotlin.tools.getFactoral

class Question34 : Question {

    var answer = 0

    override fun doWork() {
        for (i in 1..1000000) {
            val factSum = getSumOfFactorals(i)

            if (factSum== i) {
                this.answer = this.answer.plus(i)
            }
        }
    }

    fun getSumOfFactorals(number: Int): Int {

        if (number == 1 || number == 2) {
            return 0
        }
        val chars = number.toString()

        var runningTotal = 0
        chars.forEach { c ->
            runningTotal = runningTotal.plus(getFactoral(c.toString().toInt()))
        }

        return runningTotal
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 34")
        println("The sum of all numbers which are equal to the sum of the factorial of their digits, is: " + this.answer)
        println("=================================")
    }
}
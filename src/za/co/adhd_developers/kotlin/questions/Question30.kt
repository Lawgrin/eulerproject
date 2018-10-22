package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question

class Question30 : Question {

    val powAnswers = HashMap<String, Double>()

    val powTo = "5"

    var answer = 0.0

    override fun doWork() {
        for (i in 0 until 10) {
            val pow = Math.pow(i.toDouble(), powTo.toDouble())

            powAnswers[i.toString()] = pow
        }

        val min = 2
        val max = 10000000

        for (n in min until max) {
            var digits = n.toString().toCharArray().map { c -> c.toString() }

            var possible = 0.0

            digits.forEach { s: String -> possible = possible.plus(powAnswers[s]!!) }

            if (possible == n.toDouble()) {
                answer = answer.plus(possible)
                println(answer)
            }
        }
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 30")
        println("The sum of all numbers that can be written as the sum of fifth powers of their digits, is: " + this.answer)
        println("=================================")
    }
}
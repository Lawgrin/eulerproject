package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import za.co.adhd_developers.kotlin.tools.getFactors
import za.co.adhd_developers.kotlin.tools.isPrime

class Question3 : Question {
    var answer = 0L

    override fun doWork() {
        var start = 600851475143L

        getFactors(start).forEach {
            if (isPrime(it)) {
                if (it > this.answer) {
                    this.answer = it
                }
            }
        }
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 3")
        println("Largest prime number: " + this.answer)
        println("=================================")
    }
}
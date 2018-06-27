package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import za.co.adhd_developers.kotlin.tools.getNextPrime

class Question7 : Question {

    var answer = 0L

    override fun doWork() {
        var currentPrime = 2L

        for (i in 2..10001) {
            currentPrime = getNextPrime(currentPrime)
        }

        this.answer = currentPrime
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 7")
        println("The 10 001st prime number: " + this.answer)
        println("=================================")
    }
}
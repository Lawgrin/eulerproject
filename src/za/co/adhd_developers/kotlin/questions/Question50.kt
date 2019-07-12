package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import za.co.adhd_developers.kotlin.tools.MyToolbox

class Question50 : Question {

    private val target = 1000000L
    private var answer = 0L

    override fun doWork() {
        var currentPrime = MyToolbox.NumberToolbox.getNextPrime(1L)
        var runningTotal = 0L
        while (runningTotal.plus(currentPrime) < target) {
            runningTotal += currentPrime
            currentPrime = MyToolbox.NumberToolbox.getNextPrime(currentPrime)
        }

        var n = 0
        while (!MyToolbox.NumberToolbox.isPrime(runningTotal)) {
            n++
            currentPrime = MyToolbox.NumberToolbox.getNthPrime(n)
            runningTotal -= currentPrime
        }

        this.answer = runningTotal
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 50")
        println("The highest prime with the most consecutive primes, is: " + this.answer)
        println("=================================")
    }
}
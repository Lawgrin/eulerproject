package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import za.co.adhd_developers.kotlin.tools.getAllCyclicallyRotations
import za.co.adhd_developers.kotlin.tools.isPrime

class Question35 : Question {

    var answer = 0

    override fun doWork() {
        for (i in 1 until 1000000) {
            if (isPrime(i.toLong())) {
                val arr = getAllCyclicallyRotations(i.toString())

                var allPrime = true

                for (s in arr) {
                    if (!isPrime(s.toLong())) {
                        allPrime = false
                    }
                    if (!allPrime) {
                        break
                    }
                }

                if (allPrime) {
                    answer++
                }
            }
        }
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 35")
        println("The total number of circular primes below one million, is: " + this.answer)
        println("=================================")
    }
}
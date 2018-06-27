package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import za.co.adhd_developers.kotlin.tools.isPrime

class Question10 : Question {

    var answer = 2L

    override fun doWork() {
        for (i in 3L until 2000000L step 2) {
            if (isPrime(i)) this.answer += i
        }
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 10")
        println("The sum of all primes below 2 000 000 is: " + this.answer)
        println("=================================")
    }
}
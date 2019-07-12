package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import java.math.BigInteger

class Question48 : Question {

    private var answer = BigInteger.ZERO

    override fun doWork() {
        var finalTotal = BigInteger.ZERO
        (1L..1000L).forEach {
            finalTotal = finalTotal.plus(BigInteger.valueOf(it).pow(it.toInt()))
        }

        this.answer = finalTotal.mod(BigInteger.valueOf(10000000000L))
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 48")
        println("The last 10 digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000, is: " + this.answer.toString())
        println("=================================")
    }
}
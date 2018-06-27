package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import java.math.BigInteger

class Question16 : Question {

    var answer = 0

    override fun doWork() {
        var number = BigInteger("2")

        number = number.pow(1000)

        this.answer = number.toString().toCharArray().sumBy { c -> c.toString().toInt() }
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 16")
        println("The sum of the numbers from 2^1000 is: " + this.answer)
        println("=================================")
    }
}
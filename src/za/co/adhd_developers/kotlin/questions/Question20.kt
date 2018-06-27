package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import za.co.adhd_developers.kotlin.tools.factorial
import java.math.BigInteger

class Question20 : Question {

    var answer = 0

    override fun doWork() {
        answer = factorial(BigInteger("100")).toString().split(Regex("|")).sumBy { s -> if (!s.isEmpty()) s.toInt() else 0 }
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 20")
        println("The sum of the digits in the number 100!, is: " + this.answer)
        println("=================================")
    }
}
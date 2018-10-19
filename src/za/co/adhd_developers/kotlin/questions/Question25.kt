package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import java.math.BigInteger

class Question25 : Question {

    var answer = 2

    override fun doWork() {

        var previous : BigInteger = BigInteger("1")
        var current : BigInteger = BigInteger("1")

        while (current.toString().length != 1000) {
            var newNumb = previous.plus(current)

            previous = current
            current = newNumb
            answer++
        }
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 25")
        println("The index of the first term with 1000 digits, is: " + this.answer)
        println("=================================")
    }
}
package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import za.co.adhd_developers.kotlin.tools.KPermUtil
import za.co.adhd_developers.kotlin.tools.isPrime

class Question41 : Question {

    var answer = 0L

    override fun doWork() {
        var numbers = "123456789"

        while (this.answer == 0L) {
            val permUtil = KPermUtil(numbers.map { c -> c.toString() }.toTypedArray())

            var currentPerm = permUtil.next()

            while (currentPerm != null) {
                val currentNumber = currentPerm.joinToString("").toLong()
                if (isPrime(currentNumber)) {
                    if (currentNumber > this.answer) {
                        this.answer = currentNumber
                    }
                }
                currentPerm = permUtil.next()
            }

            numbers = numbers.substring(0, numbers.length - 1)
        }

    }

    override fun printAnswer() {
        println("=================================")
        println("Question 41")
        println("The largest n-digit pandigital prime that exists, is: " + this.answer)
        println("=================================")
    }
}
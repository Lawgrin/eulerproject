package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import za.co.adhd_developers.kotlin.tools.isPandigital

class Question32 : Question {

    var answer = 0

    override fun doWork() {

        var correct = HashSet<Int>()

        for (a in 1..10000) {
            for (b in 1..10000) {
                val product = a.times(b)

                if ("$product$a$b".length > 9) {
                    break
                } else if ("$product$a$b".length == 9) {
                    if (correct.contains(product)) {
                        continue
                    }
                    if (isPandigital("$product$a$b")) {
                        correct.add(product)
                    }
                }
            }
        }

        answer = correct.sum()
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 32")
        println("The sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through 9 pandigital, is: ${this.answer}")
        println("=================================")
    }
}
package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question

class Question2 : Question {

    var answer = 2
    override fun doWork() {
        var last = 1
        var current = 2

        while (current < 4000000) {
            val tmp = current
            current += last
            last = tmp
            if (current % 2 == 0) {
                this.answer += current;
            }
        }
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 2")
        println("Sum of even fib terms: " + this.answer)
        println("=================================")
    }
}
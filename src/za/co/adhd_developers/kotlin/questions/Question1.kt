package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question

class Question1 : Question {

    var answer = 0

    override fun doWork() {

        for (i in 1..999) {
            if (i % 3 == 0 || i % 5 == 0) {
                answer += i
            }
        }
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 1")
        println("Sum of multiples: " + this.answer)
        println("=================================")
    }

}
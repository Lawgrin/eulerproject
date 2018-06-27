package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question

class Question5 : Question {

    var answer = 0

    override fun doWork() {
        var number = 380
        while (answer == 0) {
            var correct = true
            for (i in 1..20) {
                if (number % i != 0) {
                    correct = false
                    break
                }
            }

            if (!correct) {
                number += 380
            } else {
                this.answer = number
            }
        }
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 5")
        println("Smallest Multiple 1 - 20: " + this.answer)
        println("=================================")
    }
}
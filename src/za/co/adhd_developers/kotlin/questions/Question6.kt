package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question

class Question6 : Question {

    var answer = 0

    override fun doWork() {
        var sum = 0
        var sqrSum = 0

        for (i in 1..100) {
            sqrSum += (i * i)
            sum += i
        }

        sum *= sum

        this.answer = Math.abs(sqrSum - sum);
    }

    override fun printAnswer() {

        println("=================================")
        println("Question 6")
        println("The difference is: " + this.answer)
        println("=================================")
    }
}
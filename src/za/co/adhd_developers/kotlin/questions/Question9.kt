package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question

class Question9 : Question {
    var answer = 0;

    override fun doWork() {
        for (a in 1..1000) {
            for (b in a + 1..1000) {

                var c = 1000 - (a+b)

                if (Math.pow(a.toDouble(), 2.toDouble()) + Math.pow(b.toDouble(), 2.toDouble()) == Math.pow(c.toDouble(), 2.toDouble())) {
                    this.answer = a * b * c
                }
            }
        }
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 9")
        println("The product 'abc' is: " + this.answer)
        println("=================================")
    }
}
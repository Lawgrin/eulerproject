package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question

class Question29 :Question {

    val limit = 101
    var answer = 0
    override fun doWork() {

        var terms: MutableSet<Double> = mutableSetOf()

        for (a in 2 until limit) {
            for (b in 2 until limit) {
                terms.add(Math.pow(a.toDouble(),b.toDouble()))
            }
        }
        answer = terms.size
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 29")
        println("Amount of distinct terms in the sequence, is: " + this.answer)
        println("=================================")
    }
}
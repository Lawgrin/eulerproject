package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import za.co.adhd_developers.kotlin.tools.getFactors

class Question23 : Question {

    var answer = 0L

    override fun doWork() {

        var abundantNumbers = HashSet<Long>()

        var possibleSums = HashSet<Long>()

        for (i in 1L..28123L) {
            var sum = 0L
            getFactors(i).forEach { fact: Long -> if (fact != i) sum += fact }

            if (sum > i) {
                abundantNumbers.add(i)

                abundantNumbers.forEach { num: Long -> possibleSums.add(num + i) }
            }
        }

        this.answer = (1L..28123L).filterNot { i -> possibleSums.contains(i) }.sum()
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 23")
        println("The sum of all the positive integers, is: " + this.answer)
        println("=================================")
    }
}
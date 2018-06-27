package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import java.util.*

class Question14 : Question {

    var answer = 0L
    var chain = 0

    override fun doWork() {

        var results = Hashtable<Long, Int>()

        for (i in 1L..999999L) {
            var number = i
            var count = 0
            while (number > 1) {

                if (results.containsKey(number)) {
                    count += results.getValue(number)
                    number = 1
                } else {
                    number = if (number.rem(2) == 0L) {
                        number.div(2L)
                    } else {
                        number.times(3L).plus(1L)
                    }
                    count++
                }
            }
            results.put(i, count)
        }

        this.chain = results.values.sortedDescending().first()

        results.filter { entry -> entry.value == this.chain }.forEach { t, u -> this.answer = t }
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 14")
        println("The starting number with the longest chain is: " + this.answer + " (" + this.chain + ")")
        println("=================================")
    }
}
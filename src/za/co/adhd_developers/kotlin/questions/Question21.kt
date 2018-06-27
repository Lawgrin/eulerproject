package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import za.co.adhd_developers.kotlin.tools.getFactors
import java.util.*

class Question21 : Question {

    var answer = 0L

    override fun doWork() {

        val alreadyDone = Hashtable<Long, Long>()

        for (i in 1L..10000L) {

            if (!alreadyDone.containsKey(i)) {
                var sum = 0L

                getFactors(i).forEach { fact: Long -> if (fact != i) sum += fact }

                if (i != sum) {
                    alreadyDone[i] = sum
                }
            }
        }

        val divisors = alreadyDone.filter { entry -> if (alreadyDone.containsKey(entry.value)) alreadyDone[entry.value] == entry.key else false}

        divisors.keys.forEach { div: Long -> this.answer += div }
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 21")
        println("The sum of all the amicable numbers under 10000, is: " + this.answer)
        println("=================================")
    }
}
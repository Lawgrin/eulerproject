package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import za.co.adhd_developers.kotlin.tools.isPandigital

class Question38 : Question {

    var answer = 0

    override fun doWork() {

        for (i in 1 until 1000000) {
            var hold = ""
            hold += i.times(1).toString()
            hold += i.times(2).toString()
            var n = 1

            while (hold.length < 9) {
                hold += i.times(n).toString()
                n++
            }

            if (hold.length != 9) {
                continue
            }

            if (isPandigital(hold)) {
                if (this.answer < hold.toInt()) {
                    this.answer = hold.toInt()
                }
            }
        }

    }

    override fun printAnswer() {
        println("=================================")
        println("Question 38")
        println("The largest 1 to 9 pandigital 9-digit number that can be formed as the concatenated product of an integer with (1,2, ... , n) where n > 1, is: " + this.answer)
        println("=================================")

    }
}
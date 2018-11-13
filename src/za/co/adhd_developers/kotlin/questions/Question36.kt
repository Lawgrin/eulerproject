package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import za.co.adhd_developers.kotlin.tools.isPalindromic

class Question36 : Question {

    var answer = 0

    override fun doWork() {
        for (i in 1 until 1000000) {

            if (i.toString().startsWith("0") || i.toString().endsWith("0")) {
                continue
            }

            val base10 = isPalindromic(i.toString())

            if (base10) {
                val base2Str = Integer.toBinaryString(i)

                if (base2Str.toString().startsWith("0") || base2Str.toString().endsWith("0")) {
                    continue
                }

                val base2 = isPalindromic(base2Str)

                if (base2) {
                    this.answer = this.answer.plus(i)
                }
            }
        }
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 36")
        println("The sum of all numbers that are palindromic in base 10 and base 2, is: " + this.answer)
        println("=================================")
    }
}
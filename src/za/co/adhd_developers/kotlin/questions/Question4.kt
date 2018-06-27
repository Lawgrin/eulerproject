package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import za.co.adhd_developers.kotlin.tools.isPalindromic

class Question4 : Question {

    var answer = 0

    override fun doWork() {
        for (lhs in 100..999) {
            for (rhs in lhs..999) {
                val product = lhs * rhs
                if (answer < product) {
                    if (isPalindromic(product.toString())) {
                        this.answer = product
                    }
                }
            }
        }
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 4")
        println("This is a palindrome: " + this.answer)
        println("=================================")
    }
}
package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import za.co.adhd_developers.kotlin.tools.isTriangle

class Question42 : Question {

    var answer = 0

    override fun doWork() {
        val wordsFileContent = Question42::class.java.getResource("/externalResources/Question42_words.txt").readText()

        val words = wordsFileContent.replace("\"","").split(",")

        words.forEach { s: String ->
            if (isTriangle(s.sumByDouble { c -> c.toDouble() - 64.0 })) {
                this.answer++
            }
        }
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 42")
        println("The total amount of triangle words in the given file, is: " + this.answer)
        println("=================================")
    }
}
package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import kotlin.math.roundToInt

class Question39 : Question {

    var answer: Int = 0
    var answerCount: Int = 0

    override fun doWork() {
        for (target in 1..1000) {

            val solutions = HashSet<String>()

            for (a in 1..(target/2)) {
                for (b in 1..(target/2)) {
                    if (a + b >= target) {
                        break
                    }

                    val hypot = Math.sqrt(Math.pow(a.toDouble(),2.0).plus(Math.pow(b.toDouble(),2.0)))

                    if (hypot.isNaN()) {
                        continue
                    }

                    val h = Math.floor(hypot).roundToInt()

                    if (hypot > h) {
                        continue
                    }

                    if (a.plus(b).plus(h) != target) {
                        continue
                    }

                    val item = "{${Math.max(a, b)},${Math.min(a, b)},$h}"
                    solutions.add(item)
                }
            }

            if (answerCount < solutions.size) {
                this.answerCount = solutions.size
                this.answer = target
            }

        }
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 39")
        println("The number with the most solutions <= 1000, is: " + this.answer)
        println("=================================")
    }
}
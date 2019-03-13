package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import za.co.adhd_developers.kotlin.tools.getPentagonal
import za.co.adhd_developers.kotlin.tools.isPentagonal

class Question44 : Question {

    var answer = 0

    override fun doWork() {
        for (j in 1..10000) {
            val pJ = getPentagonal(j.toDouble())

            for (k in j+1..10000) {
                val pK = getPentagonal(k.toDouble())

                if (isPentagonal(pJ.plus(pK))) {
                    if (isPentagonal(pK.minus(pJ))) {
                        println("$pJ | $pK - ${pK.minus(pJ)}")
                    }
                }
            }
        }
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 44")
        println("The difference of th pentagonal pair that matches the criteria, is: " + this.answer)
        println("=================================")
    }
}
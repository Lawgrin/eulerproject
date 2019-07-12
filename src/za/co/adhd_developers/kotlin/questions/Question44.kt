package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import za.co.adhd_developers.kotlin.tools.MyToolbox

class Question44 : Question {

    var answer = 0.0

    override fun doWork() {
        for (j in 1..10000) {
            val pJ = MyToolbox.PolygonalToolBox.getPentagonal(j)

            for (k in j + 1..10000) {
                val pK = MyToolbox.PolygonalToolBox.getPentagonal(k)

                if (MyToolbox.PolygonalToolBox.isPentagonal(pJ.plus(pK))) {
                    if (MyToolbox.PolygonalToolBox.isPentagonal(pK.minus(pJ))) {
                        this.answer = pK.minus(pJ)
                    }
                }
            }
        }
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 44")
        println("The difference of th pentagonal pair that matches the criteria, is: " + this.answer.toInt())
        println("=================================")
    }
}
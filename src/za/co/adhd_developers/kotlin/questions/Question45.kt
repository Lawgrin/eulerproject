package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import za.co.adhd_developers.kotlin.tools.MyToolbox

class Question45 : Question {

    var answer = 0.0

    override fun doWork() {
        var index = 144
        this.answer = MyToolbox.PolygonalToolBox.getHexagonalNumber(index)
        while (!isCorrect()) {
            index++
            this.answer = MyToolbox.PolygonalToolBox.getHexagonalNumber(index)
        }
    }

    fun isCorrect(): Boolean {
        return MyToolbox.PolygonalToolBox.isPentagonal(this.answer) && MyToolbox.PolygonalToolBox.isTriangle(this.answer)
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 45")
        println("The next triangle number that is also pentagonal and hexagonal, is: " + this.answer.toLong())
        println("=================================")
    }
}
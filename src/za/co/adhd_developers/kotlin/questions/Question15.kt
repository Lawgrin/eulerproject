package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.java.tools.Utils
import za.co.adhd_developers.kotlin.Question
import java.math.BigInteger

class Question15 : Question{

    var gridSize = BigInteger("20")
    var answer = BigInteger.ZERO

    override fun doWork() {
        //See http://www.robertdickau.com/manhattan.html on how to work this out
        val top = Utils.factorial(this.gridSize.multiply(BigInteger("2")))

        var bottom = Utils.factorial(this.gridSize)
        bottom = bottom.pow(2)

        this.answer = top.divide(bottom)
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 15")
        println("In a " + this.gridSize + "x" + this.gridSize + " grid there are : " + this.answer + " paths")
        println("=================================")
    }
}
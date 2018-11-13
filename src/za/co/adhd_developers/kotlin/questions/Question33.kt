package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import java.math.BigDecimal
import java.math.RoundingMode

class Question33 : Question {

    private var finalNume = 0
    private var finalDenom = 0
    private var answer = 0

    override fun doWork() {
        for (nume in 10..99) {
            for (denom in nume+1..99) {

                var newNume = nume
                var newDenom = denom

                if (nume.toString()[0].toString() == denom.toString()[1].toString()) {
                    newNume = nume.toString()[1].toString().toInt()
                    newDenom = denom.toString()[0].toString().toInt()
                } else if (nume.toString()[1].toString() == denom.toString()[0].toString()) {
                    newNume = nume.toString()[0].toString().toInt()
                    newDenom = denom.toString()[1].toString().toInt()
                }

                if (newNume > 10 || newDenom > 10 || newNume == 0 || newDenom == 0) {
                    continue
                }

                var larger = BigDecimal(nume)
                larger = larger.divide(BigDecimal(denom), (denom - 1) * 2, RoundingMode.DOWN)


                var smaller = BigDecimal(newNume)
                smaller = smaller.divide(BigDecimal(newDenom), (denom - 1) * 2, RoundingMode.DOWN)

                if (larger == smaller) {
                    finalDenom = if (this.finalDenom == 0) {
                        newDenom
                    } else {
                        finalDenom.times(newDenom)
                    }

                    finalNume = if (this.finalNume == 0) {
                        newNume
                    } else {
                        finalNume.times(newNume)
                    }
                }
            }
        }

        this.answer = this.finalDenom / this.finalNume
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 33")
        println("The product of all denominators, is: " + this.answer)
        println("=================================")
    }
}
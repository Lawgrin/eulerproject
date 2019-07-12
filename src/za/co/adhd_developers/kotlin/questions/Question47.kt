package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import za.co.adhd_developers.kotlin.tools.MyToolbox

class Question47 : Question {

    var answer: Long = 0L

    override fun doWork() {
//        println(MyToolbox.NumberToolbox.countPrimeFactors(644))
        val target = 4

        var currentNumber = 1L

        while (answer == 0L) {
            if (MyToolbox.NumberToolbox.countUniquePrimeFactors(currentNumber) != target) {
                currentNumber++
                continue
            }

            if (MyToolbox.NumberToolbox.countUniquePrimeFactors(currentNumber.plus(1)) != target) {
                currentNumber += 2
                continue
            }

            if (MyToolbox.NumberToolbox.countUniquePrimeFactors(currentNumber.plus(2)) != target) {
                currentNumber += 3
                continue
            }

            if (MyToolbox.NumberToolbox.countUniquePrimeFactors(currentNumber.plus(3)) != target) {
                currentNumber += 4
                continue
            }

            answer = currentNumber
        }
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 47")
        println("The first four consecutive integers to have four distinct prime factors each, starts with: " + this.answer)
        println("=================================")
    }
}
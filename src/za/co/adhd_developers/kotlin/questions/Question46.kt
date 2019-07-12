package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import za.co.adhd_developers.kotlin.tools.MyToolbox

class Question46 : Question {

    var answer: Long = 0

    override fun doWork() {
        var oddNumber: Long = 5
        var otherNumber: Long = 1
        var currPrime: Long = 3

        var possible: Long = 0

        while (this.answer == 0L) {
            if (MyToolbox.NumberToolbox.isPrime(oddNumber)) {
                oddNumber += 2
                continue
            }
            val tmp = oddNumber.minus(currPrime)

            if (tmp < 1) {
                if (possible == 0L) {
                    oddNumber += 2
                } else {
                    this.answer = possible
                }
                continue
            }

            val sqrAns = 2.times(otherNumber.times(otherNumber))

            if (tmp == sqrAns) {
                oddNumber += 2
                currPrime = 3
                otherNumber = 1
                possible = 0
                continue
            }

            if (tmp > sqrAns) {
                possible = oddNumber
                otherNumber++
                continue
            }

            currPrime = MyToolbox.NumberToolbox.getNextPrime(currPrime)
            otherNumber = 1
        }
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 46")
        println("The smallest odd composite that cannot be written as the sum of a prime and twice a square, is: " + this.answer)
        println("=================================")
    }
}
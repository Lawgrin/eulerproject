package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import za.co.adhd_developers.kotlin.tools.isPrime

class Question37 : Question {

    var answer: Long = 0

    override fun doWork() {

        var count = 0
        var currNumber: Long = 10L

        while (count < 11) {
            if (isPrime(currNumber)) {
                var checkingNumber = currNumber.toString()
                checkingNumber = checkingNumber.removeRange(0,1)

                var ltr = true

                while (checkingNumber.isNotEmpty()) {
                    if (!isPrime(checkingNumber.toLong())) {
                        ltr = false
                        checkingNumber = ""
                    } else {
                        checkingNumber = checkingNumber.removeRange(0, 1)
                    }
                }

                var rtl = true

                checkingNumber = currNumber.toString()
                checkingNumber = checkingNumber.substring(0,checkingNumber.length-1)

                while (checkingNumber.isNotEmpty()) {
                    if (!isPrime(checkingNumber.toLong())) {
                        rtl = false
                        checkingNumber = ""
                    } else {
                        checkingNumber = checkingNumber.substring(0, checkingNumber.length - 1)
                    }
                }

                if (ltr && rtl) {
                    count++
                    this.answer = this.answer.plus(currNumber)
                }
            }
            currNumber++
        }
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 37")
        println("The sum of prime numbers that are truncatable from left to right and right to left, is: " + this.answer)
        println("=================================")
    }
}
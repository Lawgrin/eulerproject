package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import za.co.adhd_developers.kotlin.tools.isPrime

class Question27 : Question {

    var finalA = 0L
    var finalB = 0L
    var finalCount = 0L
    override fun doWork() {
        for (a in -999L until 999L) {
            for (b in -1000L until 1000L) {
                var n = 0L
                var result = n.times(n).plus(a.times(n)).plus(b)

                while (isPrime(result)) {
                    n++
                    result = n.times(n).plus(a.times(n)).plus(b)
                }

                if (finalCount < n) {
                    finalCount = n
                    finalA = a
                    finalB = b
                }
            }
        }
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 27")
        println("The product of the coefficients, a and b, is: ${finalA * finalB}")
        println("=================================")
    }
}
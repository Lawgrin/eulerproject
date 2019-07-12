package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.regex.Pattern

class Question26 : Question {

    var answer = 0
    var oldLength = 0
    override fun doWork() {
        for (number in 999 downTo 2) {
            val scale = (number - 1) * 2
            if (scale / 2 < oldLength) {
                continue
            }
            val original = BigDecimal.ONE.divide(BigDecimal("$number"), scale, RoundingMode.DOWN).stripTrailingZeros().toString()

            val recurring = original.substring(2)

            var copyFromIndex = 0
            var copyLength = 1

            while (copyFromIndex <= number.minus(1)) {
                while (copyFromIndex + copyLength <= number.minus(1)) {
                    var prefex = ""
                    if (copyFromIndex > 0) {
                        prefex = recurring.substring(0, copyFromIndex)
                    }

                    val checkFor = recurring.substring(copyFromIndex, copyFromIndex + copyLength)
                    val regex = "^0.${if (prefex.isNotEmpty()) "($prefex)" else ""}($checkFor){2,}\$"

                    val matcher = Pattern.compile(regex).matcher(original)

                    if (matcher.find()) {
                        copyFromIndex = number.minus(1)

                        val newLength = checkFor.length
                        if (oldLength < newLength) {
                            oldLength = newLength
                            answer = number
                        }
                    } else {
                        copyLength++
                    }
                }
                copyFromIndex++
                copyLength = 1
            }
        }
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 26")
        println("The 1/d with the longest recurring cycle, is: ${this.answer}")
        println("The length of the cycle for 1/${this.answer}, is ${this.oldLength}")
        println("=================================")
    }
}
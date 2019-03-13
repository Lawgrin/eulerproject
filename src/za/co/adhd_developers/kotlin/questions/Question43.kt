package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import za.co.adhd_developers.kotlin.tools.KPermUtil
import za.co.adhd_developers.kotlin.tools.isPandigital

class Question43 : Question {

    var answer = 0L

    override fun doWork() {
        val numbers = "1234567890"

        val permUtil = KPermUtil(numbers.map { c -> c.toString() }.toTypedArray())

        var currentPerm = permUtil.next()

        while (currentPerm != null) {

            val possible = currentPerm.joinToString("")

            if (possible.startsWith("0")) {
                currentPerm = permUtil.next()
                continue
            }

            if (!isPandigital(possible, 0)) {
                currentPerm = permUtil.next()
                continue
            }

            var index = 1
            if (possible.substring(index,index+3).toInt().rem(2) != 0) {
                currentPerm = permUtil.next()
                continue
            }
            index++
            if (possible.substring(index,index+3).toInt().rem(3) != 0) {
                currentPerm = permUtil.next()
                continue
            }
            index++
            if (possible.substring(index,index+3).toInt().rem(5) != 0) {
                currentPerm = permUtil.next()
                continue
            }
            index++
            if (possible.substring(index,index+3).toInt().rem(7) != 0) {
                currentPerm = permUtil.next()
                continue
            }
            index++
            if (possible.substring(index,index+3).toInt().rem(11) != 0) {
                currentPerm = permUtil.next()
                continue
            }
            index++
            if (possible.substring(index,index+3).toInt().rem(13) != 0) {
                currentPerm = permUtil.next()
                continue
            }
            index++
            if (possible.substring(index,index+3).toInt().rem(17) != 0) {
                currentPerm = permUtil.next()
                continue
            }

            answer = answer.plus(possible.toLong())
            currentPerm = permUtil.next()
        }
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 43")
        println("The sum of all 0 to 9 pandigital numbers with the sub-string divisibility property, is: " + this.answer)
        println("=================================")
    }
}
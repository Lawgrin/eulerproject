package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question

class Question28 : Question {

    val limit = 1001
    var answer = 0

    override fun doWork() {
        var currentTotal = 3

        var currentNumber = 3
        var nextJump = 10
        var endPoint = limit * limit - (limit - 1) * 3

        while (currentNumber < endPoint) {
            currentNumber += nextJump
            nextJump += 8
            currentTotal += currentNumber
        }

        currentTotal += 5
        currentNumber = 5
        nextJump = 12
        endPoint = limit * limit - (limit - 1) * 2

        while (currentNumber < endPoint) {
            currentNumber += nextJump
            nextJump += 8
            currentTotal += currentNumber
        }

        currentTotal += 7
        currentNumber = 7
        nextJump = 14
        endPoint = limit * limit - (limit - 1)

        while (currentNumber < endPoint) {
            currentNumber += nextJump
            nextJump += 8
            currentTotal += currentNumber
        }

        currentTotal += 9
        currentNumber = 9
        nextJump = 16
        endPoint = limit * limit

        while (currentNumber < endPoint) {
            currentNumber += nextJump
            nextJump += 8
            currentTotal += currentNumber
        }

        this.answer = currentTotal + 1
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 28")
        println("The sum of the numbers on the diagonals in a 1001 by 1001 spiral, is: " + this.answer)
        println("=================================")
    }
}
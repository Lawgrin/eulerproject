package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question

class Question24 : Question {

    val limit = 1000000

    var answer = ""

    override fun doWork() {

        var permutation = "0123456789"

        for (i in 1..limit - 1) {
            permutation = generateNextPermutations(permutation)
        }

        answer = permutation
    }

    private fun generateNextPermutations(previousPerm: String): String {

        var movingIndex = -1
        var movingToIndex = -1

        for (i in 0 until previousPerm.length - 1) {

            if (previousPerm[i].toString().compareTo(previousPerm[i + 1].toString()) < 0) {
                movingIndex = i
            }
        }

        if (movingIndex == -1) {
            return previousPerm
        }

        for (i in movingIndex + 1 until previousPerm.length) {
            if (previousPerm[i].toString().compareTo(previousPerm[movingIndex].toString()) > 0) {
                if (movingToIndex == -1) {
                    movingToIndex = i
                } else if (previousPerm[i].toString().compareTo(previousPerm[movingToIndex].toString()) < 0) {
                    movingToIndex = i
                }
            }
        }

        if (movingToIndex == -1) {
            return previousPerm
        }

        val movingChar = previousPerm[movingIndex].toString()
        val movingToChar = previousPerm[movingToIndex].toString()

        var newPerm = previousPerm
        newPerm = newPerm.replaceRange(movingIndex, movingIndex + 1, movingToChar)
        newPerm = newPerm.replaceRange(movingToIndex, movingToIndex + 1, movingChar)

        val locked = newPerm.substring(0, movingIndex + 1)
        val sorting = newPerm.substring(movingIndex + 1).toCharArray()

        sorting.sort()

        return locked + sorting.joinToString("")
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 24")
        println("The millionth lexicographic permutation, is: " + this.answer)
        println("=================================")
    }
}
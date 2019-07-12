package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import za.co.adhd_developers.kotlin.tools.MyToolbox

class Question49 : Question {

    private var answer = ""

    override fun doWork() {
        wayFive()
    }

    private fun wayFive() {
        for (currNumber in 1000L..9999L) {
            if (!MyToolbox.NumberToolbox.isPrime(currNumber)) {
                continue
            }

            for (diff in 1L until 10000L.minus(currNumber)) {
                val secondNumber = currNumber.plus(diff)
                val lastNumber = secondNumber.plus(diff)

                if (lastNumber > 9999) {
                    continue
                }

                if (!MyToolbox.NumberToolbox.isPrime(secondNumber)) {
                    continue
                }

                if (!MyToolbox.NumberToolbox.isPrime(lastNumber)) {
                    continue
                }

                if (!MyToolbox.TextToolbox.isPermutationOfAll(currNumber.toString(), secondNumber.toString(), lastNumber.toString())) {
                    continue
                }

                this.answer = currNumber.toString() + secondNumber.toString() + lastNumber.toString()
            }
        }
    }

    fun wayTwo() {
        for (currentNumber in 1489L until 9999L step 2L) {
            if (!MyToolbox.NumberToolbox.isPrime(currentNumber)) {
                continue
            }

            for (diff in 1 until (10000.minus(currentNumber))) {
                val secondNumber = currentNumber.plus(diff)
                val lastNumber = secondNumber.plus(diff)

                if (lastNumber > 9999) {
                    break
                }

                if (!MyToolbox.TextToolbox.isPermutationOfAll(currentNumber.toString(), secondNumber.toString(), lastNumber.toString())) {
                    continue
                }

                if (!MyToolbox.NumberToolbox.isPrime(secondNumber)) {
                    continue
                }

                if (!MyToolbox.NumberToolbox.isPrime(lastNumber)) {
                    continue
                }

                this.answer = "$currentNumber$secondNumber$lastNumber"
            }
        }
    }

    fun wayOne() {
        var currentNumber = MyToolbox.NumberToolbox.getNextPrime(1487)
        while (currentNumber < 10000 && this.answer.isEmpty()) {
            for (diff in 1 until (10000.minus(currentNumber))) {
                val secondNumber = currentNumber.plus(diff)
                val lastNumber = secondNumber.plus(diff)

                if (lastNumber > 9999) {
                    break
                }

                if (!MyToolbox.TextToolbox.isPermutationOfAll(currentNumber.toString(), secondNumber.toString(), lastNumber.toString())) {
                    continue
                }

                if (!MyToolbox.NumberToolbox.isPrime(secondNumber)) {
                    continue
                }

                if (!MyToolbox.NumberToolbox.isPrime(lastNumber)) {
                    continue
                }

                this.answer = "$currentNumber$secondNumber$lastNumber"
            }
            currentNumber = MyToolbox.NumberToolbox.getNextPrime(currentNumber)
        }
    }

    fun someWorkNeeded() {
//        var currentNumber = MyToolbox.NumberToolbox.getNextPrime(1000)
//        val primeList = arrayListOf<Long>()
//
//        while (currentNumber < 10000) {
//            primeList.add(currentNumber)
//            currentNumber = MyToolbox.NumberToolbox.getNextPrime(currentNumber)
//        }
//
//        primeList.forEach { println(it) }
//        primeList.groupBy { MyToolbox.TextToolbox.splitStringByLength(it.toString()).sorted().joinToString("") }
//                .filter { entry -> entry.value.size > 2 }
//                .toSortedMap()
//                .forEach { t, u -> println("$t - $u") }

//        primeList.forEach { println(it) }
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 49")
        println("The 12-digit number do you form by concatenating the three terms in this sequence, is: " + this.answer)
        println("=================================")
    }
}
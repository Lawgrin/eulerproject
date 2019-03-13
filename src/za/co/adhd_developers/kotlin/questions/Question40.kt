package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question

class Question40 : Question {

    var answer = 1

    override fun doWork() {
        var length = 0
        var currNumber = 1

        var lookingFor = 1
        while (length < lookingFor) {
            val strNumber = currNumber.toString()
            if (length + strNumber.length >= lookingFor) {
                val index = lookingFor.minus(length).minus(1)
                this.answer = answer.times(strNumber.substring(index, index + 1).toInt())
            }
            length += strNumber.length
            currNumber++
        }

        lookingFor *= 10//10
        while (length < lookingFor) {
            val strNumber = currNumber.toString()
            if (length + strNumber.length >= lookingFor) {
                val index = lookingFor.minus(length).minus(1)
                this.answer = answer.times(strNumber.substring(index, index + 1).toInt())
            }
            length += strNumber.length
            currNumber++
        }

        lookingFor *= 10//100
        while (length < lookingFor) {
            val strNumber = currNumber.toString()
            if (length + strNumber.length >= lookingFor) {
                val index = lookingFor.minus(length).minus(1)
                this.answer = answer.times(strNumber.substring(index, index + 1).toInt())
            }
            length += strNumber.length
            currNumber++
        }

        lookingFor *= 10//1000
        while (length < lookingFor) {
            val strNumber = currNumber.toString()
            if (length + strNumber.length >= lookingFor) {
                val index = lookingFor.minus(length).minus(1)
                this.answer = answer.times(strNumber.substring(index, index + 1).toInt())
            }
            length += strNumber.length
            currNumber++
        }

        lookingFor *= 10//10000
        while (length < lookingFor) {
            val strNumber = currNumber.toString()
            if (length + strNumber.length >= lookingFor) {
                val index = lookingFor.minus(length).minus(1)
                this.answer = answer.times(strNumber.substring(index, index + 1).toInt())
            }
            length += strNumber.length
            currNumber++
        }

        lookingFor *= 10//100000
        while (length < lookingFor) {
            val strNumber = currNumber.toString()
            if (length + strNumber.length >= lookingFor) {
                val index = lookingFor.minus(length).minus(1)
                this.answer = answer.times(strNumber.substring(index, index + 1).toInt())
            }
            length += strNumber.length
            currNumber++
        }

        lookingFor *= 10//1000000
        while (length < lookingFor) {
            val strNumber = currNumber.toString()
            if (length + strNumber.length >= lookingFor) {
                val index = lookingFor.minus(length).minus(1)
                this.answer = answer.times(strNumber.substring(index, index + 1).toInt())
            }
            length += strNumber.length
            currNumber++
        }
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 40")
        println("The product of the 1th, 10th, 100th, 1000th, 10000th, 100000th and 1000000th, is: " + this.answer)
        println("=================================")
    }
}
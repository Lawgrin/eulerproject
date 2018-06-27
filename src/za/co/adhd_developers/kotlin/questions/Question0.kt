package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question


class Question0 : Question {
    override fun doWork() {

        val string = "759AFF"

        val number: Long = string.toLong(8)

        println(number.toString(8))
    }

    override fun printAnswer() {

    }
}
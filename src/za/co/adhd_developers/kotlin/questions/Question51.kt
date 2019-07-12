package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import za.co.adhd_developers.kotlin.tools.MyToolbox

class Question51 : Question {

    private var answer = ""

    override fun doWork() {
        val primeList = arrayListOf<Long>()
        var currPrime = MyToolbox.NumberToolbox.getNextPrime(99999L)
        while (currPrime < 1000000L) {
            primeList.add(currPrime)
            currPrime = MyToolbox.NumberToolbox.getNextPrime(currPrime)
        }

        var templates = arrayListOf<String>()
        MyToolbox.PermutationToolbox.getPermutations(MyToolbox.TextToolbox.splitStringByLength("ddddd?????").toTypedArray(), 6) {
            templates.add(it.joinToString(""))
        }
//        templates = arrayListOf(templates.distinct().toTypedArray())
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 51")
        println("The smallest prime which us part of an eight prime value family, is: " + this.answer)
        println("=================================")
    }
}
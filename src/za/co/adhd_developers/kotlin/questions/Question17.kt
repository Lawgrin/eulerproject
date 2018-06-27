package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import java.util.*

class Question17() : Question {

    var staticStrings = Hashtable<Int, String>()
    var stringBuilder = StringBuilder()

    init {
        this.staticStrings[1] = "one"
        this.staticStrings[2] = "two"
        this.staticStrings[3] = "three"
        this.staticStrings[4] = "four"
        this.staticStrings[5] = "five"
        this.staticStrings[6] = "six"
        this.staticStrings[7] = "seven"
        this.staticStrings[8] = "eight"
        this.staticStrings[9] = "nine"
        this.staticStrings[10] = "ten"
        this.staticStrings[11] = "eleven"
        this.staticStrings[12] = "twelve"
        this.staticStrings[13] = "thirteen"
        this.staticStrings[14] = "fourteen"
        this.staticStrings[15] = "fifteen"
        this.staticStrings[16] = "sixteen"
        this.staticStrings[17] = "seventeen"
        this.staticStrings[18] = "eighteen"
        this.staticStrings[19] = "nineteen"
        this.staticStrings[20] = "twenty"
        this.staticStrings[30] = "thirty"
        this.staticStrings[40] = "forty"
        this.staticStrings[50] = "fifty"
        this.staticStrings[60] = "sixty"
        this.staticStrings[70] = "seventy"
        this.staticStrings[80] = "eighty"
        this.staticStrings[90] = "ninety"
        this.staticStrings[100] = "onehundred"
        this.staticStrings[200] = "twohundred"
        this.staticStrings[300] = "threehundred"
        this.staticStrings[400] = "fourhundred"
        this.staticStrings[500] = "fivehundred"
        this.staticStrings[600] = "sixhundred"
        this.staticStrings[700] = "sevenhundred"
        this.staticStrings[800] = "eighthundred"
        this.staticStrings[900] = "ninehundred"
        this.staticStrings[1000] = "onethousand"
    }

    override fun doWork() {
        for (i in 1..1000) {
            var finalString: String

            if (this.staticStrings.containsKey(i)) {
                finalString = this.staticStrings[i]!!
            } else {
                var value = i
                var strHundred = ""
                var strTens = ""
                var strValue = ""

                val hundred = value - value % 100
                value -= hundred

                if (this.staticStrings.containsKey(hundred)) {
                    strHundred = this.staticStrings[hundred]!!
                }

                if (this.staticStrings.containsKey(value)) {
                    strTens = this.staticStrings[value]!!
                } else {
                    val tens = value - value % 10
                    value -= tens

                    if (this.staticStrings.containsKey(tens)) {
                        strTens = this.staticStrings[tens]!!
                    }

                    if (this.staticStrings.containsKey(value)) {
                        strValue = this.staticStrings[value]!!
                    }
                }

                finalString = strHundred + (if (strHundred.isNotEmpty()) "and" else "") + "" + strTens + "" + strValue
            }

            this.stringBuilder.append(finalString)
        }
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 17")
        println("Number of letters of the numbers 1 - 1000 written out, is: " + this.stringBuilder.toString().length)
        println("=================================")
    }
}
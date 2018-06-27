package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.java.resources.NameScore
import za.co.adhd_developers.kotlin.Question
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class Question22 : Question {

    var answer = 0L

    override fun doWork() {
        try {
            val inputStream = javaClass.getResourceAsStream("/externalResources/Question22_names.txt")
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))

            val stringBuffer = StringBuffer()
            var s = bufferedReader.readLine()
            while (s != null) {
                stringBuffer.append(s.replace("\"", ""))
                s = bufferedReader.readLine()
            }

            bufferedReader.close()

            val names = stringBuffer.toString().split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

            names.sort()

            names.forEachIndexed { index, name -> this.answer += (name.sumBy { c -> c.toInt().minus(64) } * (index + 1)) }

        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    override fun printAnswer() {
        println("=================================")
        println("Question 22")
        println("The total of all the name scores in the file, is: " + this.answer)
        println("=================================")
    }
}
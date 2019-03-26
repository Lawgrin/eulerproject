package za.co.adhd_developers.kotlin


internal var questions = intArrayOf(0)
internal var start = 1
internal var end = 28

fun main(args: Array<String>) {

    if (questions.isEmpty()) {
        questions = IntArray((end - start) + 1)
        for ((index, i) in (start..end).withIndex()) {
            questions[index] = i
        }
    }

    for (questNum in questions) {
        try {
            val cls = Class.forName("za.co.adhd_developers.kotlin.questions.Question" + questNum.toString())

            val question = cls.newInstance()
            if (question is Question) {
                val startTime = System.nanoTime()
                question.doWork()
                val endTime = System.nanoTime()
                val timeTaken = endTime - startTime
                question.printAnswer()
                println("Time taken: ${convertTime(timeTaken)}")
            }
        } catch (e: ClassNotFoundException) {
            println("No Class with name Question $questNum")
            e.printStackTrace()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

    }
}

fun convertTime(inTimeDiff: Long): String {
    var timeDifference = inTimeDiff

    var minutes: Int = 0
    var seconds: Int = 0
    var miliseconds: Int = 0
    var microseconds: Int = 0
    var nanoseconds: Int = 0

    if (timeDifference >= 60000000000) {
        minutes = timeDifference.div(60000000000).toInt()
        timeDifference = timeDifference.minus(minutes.times(60000000000))
    }

    if (timeDifference > 1000000000) {
        seconds = timeDifference.div(1000000000).toInt()
        timeDifference = timeDifference.minus(seconds.times(1000000000))
    }

    if (timeDifference > 1000000) {
        miliseconds = timeDifference.div(1000000).toInt()
        timeDifference = timeDifference.minus(miliseconds.times(1000000))
    }

    if (timeDifference > 1000) {
        microseconds = timeDifference.div(1000).toInt()
        timeDifference = timeDifference.minus(microseconds.times(1000))
    }

    nanoseconds = timeDifference.toInt()


    return "$minutes:$seconds.${miliseconds.toString().padStart(3,"0".toCharArray()[0])} ${microseconds}μs ${nanoseconds}ns"
}
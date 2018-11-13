package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question
import java.io.File
import java.lang.StringBuilder
import java.nio.file.Files
import java.nio.file.StandardOpenOption

class Question0 : Question {

    val readPath = "/Users/grantpienaar/Desktop/UBI Documentation/user_data_crash_/"
    val writePath = "/Users/grantpienaar/Desktop/UBI Documentation/UserIds.txt"

    val limit = 5000

    override fun doWork() {
        val folder = File(readPath)

        if (!folder.exists()) {
            return
        }

        if (folder.listFiles() == null) {
            return
        }

        val files : Array<File> = folder.listFiles()

        val userIds = HashMap<String, Int>()

        for (file in files) {
            if (!file.name.endsWith(".csv")) {
                continue
            }
            val data = Files.readAllLines(file.toPath())
            for (i in 1..data.size - 1) {
                if (data[i].isEmpty()) {
                    continue
                }
                val entryData = data[i].split(",")
                val userId = entryData[0]
                val count = entryData[entryData.size-1].toInt()

                if (userIds.containsKey(userId)) {
                    var currentCount = userIds[userId]!!

                    currentCount += count
                    userIds[userId] = currentCount
                } else {
                    userIds[userId] = count
                }
            }
        }

        println(userIds.size)

        var outFile = File(writePath)

        if (!outFile.exists()) {
            outFile.createNewFile()
        } else {
            Files.write(outFile.toPath(), byteArrayOf())
        }

        val output = StringBuilder()

        userIds.forEach { t, u ->
            output.append("$t")
            output.append("\n")
        }

        Files.write(outFile.toPath(), output.toString().toByteArray())


//        val data = Files.readAllLines(File(readPath).toPath())
//
//        var folderNumber = 0
//        var destFile = getFile(folderNumber)
//        for (i in 0..data.size - 1) {
//            if (i > (limit-1) && i.rem(limit) == 0) {
//                folderNumber++
//                destFile = getFile(folderNumber)
//            }
//            if (destFile != null) {
//                Files.write(destFile!!.toPath(), arrayListOf(data[i]), StandardOpenOption.APPEND)
//            }
//        }
    }

    fun getFile(number: Int): File? {
        val newFile = File("$writePath$number.csv")

        if (!newFile.exists()) {
            if (newFile.createNewFile()) {
                return newFile
            }
        } else {

        }

        return null
    }

    override fun printAnswer() {

    }
}
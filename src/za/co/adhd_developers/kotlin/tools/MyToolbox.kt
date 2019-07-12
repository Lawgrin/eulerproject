package za.co.adhd_developers.kotlin.tools

import java.math.BigInteger
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.pow

class MyToolbox {

    companion object Tools {
        fun <T> isNullOrEmpty(data: T?): Boolean {
            return when (data) {
                null -> true
                is CharSequence -> (data as CharSequence).length == 0
                is Map<*, *> -> (data as Map<*, *>).size == 0
                is Collection<*> -> (data as Collection<*>).size == 0
                is Array<*> -> (data as Array<*>).size == 0
                else -> false
            }
        }
    }

    class TextToolbox {
        companion object Tools {
            fun splitStringByLength(text: String?, length: Int = 1): List<String> {
                if (isNullOrEmpty(text)) {
                    throw IllegalArgumentException("Text cannot be null or empty")
                }
                val splitArr = text!!.split("(?<=\\G.{$length})".toRegex())

                //Need to do this cause splitting by length 1 returns and extra blank space
                //both text.split("|") and text.split("(?<=\\G.{1})")
                return if (splitArr.size > text.length.div(length)) {
                    splitArr.take(text.length.div(length))
                } else {
                    splitArr
                }
            }

            fun isValidInteger(text: String?): Boolean {
                if (isNullOrEmpty(text)) {
                    return false
                }

                return text!!.matches("^-?\\d{1,9}\$".toRegex())
            }

            fun isDigitsOnly(text: String?): Boolean {
                if (isNullOrEmpty(text)) {
                    return false
                }

                return text!!.matches("^\\d+$".toRegex())
            }

            fun isPermutationOfAll(vararg values: String): Boolean {
                if (values.isEmpty()) {
                    return false
                }

                if (values.size == 1) {
                    return true
                }

                val length = values[0].length
                if (!values.all { it.length == length }) {
                    return false
                }

                if (values.size != values.distinctBy { it }.size) {
                    return false
                }

                return values.map { splitStringByLength(it).sorted().joinToString("") }.distinct().size == 1
            }

            fun isPermutationOfEachOther(stringOne: String, stringTwo: String): Boolean {
                if (stringOne.length != stringTwo.length) {
                    return false
                }

                if (stringOne == stringTwo) {
                    return false
                }

                splitStringByLength("1234").sorted().joinToString { "" }

                return splitStringByLength(stringOne).sorted().joinToString("") == splitStringByLength(stringTwo).sorted().joinToString("")
            }

            fun isRSAIDvalid(idnumber: String): Boolean {
                //idnumber = 9202295213873
                if (isNullOrEmpty(idnumber)) {
                    return false
                }

                if (!isDigitsOnly(idnumber)) {
                    return false
                }

                if (idnumber.length != 13) {
                    return false
                }

                //9202295213873 > [9, 2, 0, 2, 2, 9, 5, 2, 1, 3, 8, 7, 3]
                val digits = splitStringByLength(idnumber).toTypedArray()

                //920229 > 1991/02/29
                val dob = digits.copyOf(6).joinToString("")

                //920229 = is valid (leap year)
                if (!DateTimeToolbox.isValidDate(dob, "yymmdd")) {
                    return false
                }

                var sumCheck = 0
                var productCheck = 0
                //3
                val lastDigit = digits[digits.lastIndex].toInt()
                digits.forEachIndexed { index, value ->
                    when {
                        //[9, 2, 0, 2, 2, 9, 5, 2, 1, 3, 8, 7, 3] > [9, -, 0, -, 2, -, 5, -, 1, -, 8, -, -]
                        NumberToolbox.isOdd(index.plus(1).toLong()) && index != digits.lastIndex -> sumCheck = sumCheck.plus(value.toInt())

                        //[9, 2, 0, 2, 2, 9, 5, 2, 1, 3, 8, 7, 3] > [-, 2, -, 2, -, 9, -, 2, -, 3, -, 7, -]
                        NumberToolbox.isEven(index.plus(1).toLong()) -> productCheck = productCheck.times(10).plus(value.toInt())
                    }
                }
                //[-, 2, -, 2, -, 9, -, 2, -, 3, -, 7, -] > productCheck = 229237
                //[9, -, 0, -, 2, -, 5, -, 1, -, 8, -, -] > 9+0+2+5+1+8 > sumCheck = 25

                //229237 > 229237 x 2 > productCheck = 458474
                productCheck = productCheck.times(2)

                //458474 > 4+5+8+4+7+4 > 32
                val productCheckSum = NumberToolbox.sumEachDigit(productCheck)

                //(10 - ((sumCheck + productCheckSum) % 10)) % 10
                //(10 - ((25 + 32) % 10)) % 10
                //(10 - (57 % 10)) % 10
                //(10 - 7) % 10
                //3 % 10
                //3
                val checkDigit = 10.minus(sumCheck.plus(productCheckSum).rem(10)).rem(10)

                return lastDigit == checkDigit
            }
        }
    }

    class CollectionToolbox {
        companion object Tools {
            fun <T> containsAny(collection: Collection<T>, vararg elements: T): Boolean {
                return collection.any { elements.contains(it) }
            }
        }
    }

    class HexToolbox {
        companion object Tools {
            fun byteArrayToHexString(byteArr: ByteArray): String {
                return byteArr.map { byte -> String.format("%02X", byte) }.joinToString("")
            }

            fun hexStringToByteArray(s: String): ByteArray {
                val len = s.length
                val byteArr = ByteArray(len / 2)
                var i = 0
                while (i < len) {
                    byteArr[i / 2] = ((Character.digit(s[i], 16) shl 4) + Character.digit(s[i + 1], 16)).toByte()
                    i += 2
                }
                return byteArr
            }
        }
    }

    class PermutationToolbox {

        companion object Tools {
            fun <T> getPermutations(array: Array<T>, sizePerPermutation: Int = array.size, permutationListener: ((Array<T>) -> Boolean)) {
                val permutationWorker = PermutationWorker(array, sizePerPermutation)

                var permutation = permutationWorker.next()
                while (permutation != null) {
                    if (permutationListener(permutation)) {
                        permutation = permutationWorker.next()
                    } else {
                        return
                    }
                }
            }
        }

        private class PermutationWorker<T>(arr: Array<T>, permSize: Int = arr.size) {

            private var arr: Array<T>? = null
            private val permSwappings: IntArray

            init {
                this.arr = arr.clone()
                this.permSwappings = IntArray(permSize)
                for (i in permSwappings.indices) {
                    permSwappings[i] = i
                }
            }

            operator fun next(): Array<T>? {
                if (arr == null) {
                    return null
                }

                val res = Arrays.copyOf(arr!!, permSwappings.size)
                //Prepare next
                var i = permSwappings.size - 1
                while (i >= 0 && permSwappings[i] == arr!!.size - 1) {
                    swap(i, permSwappings[i]) //Undo the swap represented by permSwappings[i]
                    permSwappings[i] = i
                    i--
                }

                if (i < 0) {
                    arr = null
                } else {
                    val prev = permSwappings[i]
                    swap(i, prev)
                    val next = prev + 1
                    permSwappings[i] = next
                    swap(i, next)
                }

                return res
            }

            private fun swap(i: Int, j: Int) {
                val tmp = arr!![i]
                arr!![i] = arr!![j]
                arr!![j] = tmp
            }
        }
    }

    class NumberToolbox {
        companion object Tools {

            fun getFactors(number: Long): Array<Long> {
                var factor: Long = 1
                var endPoint = number
                val factors = arrayListOf<Long>()

                while (factor < endPoint) {
                    if (!factors.contains(factor)) {
                        if (number % factor == 0L) {
                            factors.add(factor)
                            endPoint = number.div(factor)
                            factors.add(endPoint)
                        }
                    }
                    factor++
                }

                return factors.toTypedArray()
            }

            fun countUniquePrimeFactors(number: Long): Int {
                return getFactors(number).filter { isPrime(it) }.union(emptyList()).size
            }

            fun isPrime(number: Long): Boolean {
                if (number <= 1L) {
                    return false
                }

                if (number > 2L && isEven(number)) {
                    return false
                }

                val top = Math.sqrt(number.toDouble()).toLong() + 1
                for (i in 3 until top step 2) {
                    if (number % i == 0L) {
                        return false
                    }
                }

                return true
            }

            fun getNthPrime(n: Int): Long {
                var count = 1
                var prime = 2L
                while (count < n) {
                    prime = getNextPrime(prime)
                    count++
                }
                return prime
            }

            fun getNextPrime(number: Long): Long {
                if (number >= Long.MAX_VALUE.minus(2)) {
                    return -1L
                }

                var newPrime = number.plus(1)
                if (newPrime != 2L && isEven(newPrime)) {
                    newPrime++
                }

                while (!isPrime(newPrime)) {
                    if (newPrime >= Long.MAX_VALUE.minus(2)) {
                        return -1L
                    }
                    newPrime += 2
                }

                return newPrime
            }

            fun isEven(number: Long): Boolean {
                return number.rem(2) == 0L
            }

            fun isOdd(number: Long): Boolean {
                return !isEven(number)
            }

            fun sumEachDigit(number: Int): Int {
                var tmp = number
                var finalSum = 0
                while (tmp > 0) {
                    finalSum += tmp.rem(10)
                    tmp = tmp.div(10)
                }
                return finalSum
            }
        }

        fun bigIntegerSlowPow(base: BigInteger, power: BigInteger): BigInteger {
            var iteration = BigInteger.ONE
            var result = base
            while (iteration < power) {
                result = result.times(base)
                iteration++
            }

            return result
        }

        enum class Base(val numeric: Int, val validChars: List<String>) {
            ONE(1, listOf("1")),
            TWO(2, listOf("1", "0")),
            THREE(3, listOf("1", "0", "2")),
            FOUR(4, listOf("1", "0", "2", "3")),
            FIVE(5, listOf("1", "0", "2", "3", "4")),
            SIX(6, listOf("1", "0", "2", "3", "4", "5")),
            SEVEN(7, listOf("1", "0", "2", "3", "4", "5", "6")),
            EIGHT(8, listOf("1", "0", "2", "3", "4", "5", "6", "7")),
            NINE(9, listOf("1", "0", "2", "3", "4", "5", "6", "7", "8")),
            TEN(10, listOf("1", "0", "2", "3", "4", "5", "6", "7", "8", "9")),
            ELEVEN(11, listOf("1", "0", "2", "3", "4", "5", "6", "7", "8", "9", "A")),
            TWELVE(12, listOf("1", "0", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B")),
            THIRTEEN(13, listOf("1", "0", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C")),
            FOURTEEN(14, listOf("1", "0", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D")),
            FIFTEEN(15, listOf("1", "0", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E")),
            SIXTEEN(16, listOf("1", "0", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F")),
            SEVENTEEN(17, listOf("1", "0", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G")),
            EIGHTEEN(18, listOf("1", "0", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H")),
            NINETEEN(19, listOf("1", "0", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I")),
            TWENTY(20, listOf("1", "0", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J")),
            TWENTY_ONE(21, listOf("1", "0", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K")),
            TWENTY_TWO(22, listOf("1", "0", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L")),
            TWENTY_THREE(23, listOf("1", "0", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M")),
            TWENTY_FOUR(24, listOf("1", "0", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N")),
            TWENTY_FIVE(25, listOf("1", "0", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O")),
            TWENTY_SIX(26, listOf("1", "0", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P")),
            TWENTY_SEVEN(27, listOf("1", "0", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q")),
            TWENTY_EIGHT(28, listOf("1", "0", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R")),
            TWENTY_NINE(29, listOf("1", "0", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S")),
            THIRTY(30, listOf("1", "0", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T")),
            THIRTY_ONE(31, listOf("1", "0", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U")),
            THIRTY_TWO(32, listOf("1", "0", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V")),
            THIRTY_THREE(33, listOf("1", "0", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W")),
            THIRTY_FOUR(34, listOf("1", "0", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X")),
            THIRTY_FIVE(35, listOf("1", "0", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y")),
            THIRTY_SIX(36, listOf("1", "0", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"));

            companion object {
                fun valueOf(value: Int): Base {
                    return values().first { it.numeric == value }
                }
            }
        }
    }

    class PolygonalToolBox {
        companion object Tools {

            fun getTriangleNumber(number: Int): Double {
                return 0.5.times(number).times(number.plus(1))
            }

            fun getTriangleOrigin(number: Double): Double {
                val a = 0.5.times(10)
                val b = 0.5.times(10)
                val c = 0.minus(number.times(10))

                val underSqrt = Math.pow(b, 2.0).plus((-4).times(a.times(c)))

                val underDivide = 2.times(a)

                val sqrt = Math.sqrt(underSqrt)

                val aboveDivide = b.times(-1).plus(sqrt)

                return aboveDivide / underDivide
            }

            fun isTriangle(number: Double): Boolean {
                val ans = getTriangleOrigin(number)

                if (ans.isNaN()) {
                    return false
                }

                return ans == Math.floor(ans)
            }

            fun getPentagonal(number: Int): Double {
                return number.times(3.times(number).minus(1)).div(2.0)
            }

            fun getPentagonalOrigin(number: Double): Double {
                val a = 1.5 * 10
                val b = -0.5 * 10
                val c = 0 - number * 10

                val underSqrt = Math.pow(b, 2.0) + -4 * (a * c)

                val underDivide = 2 * a

                val sqrt = Math.sqrt(underSqrt)

                val aboveDivide = b * -1 + sqrt

                return aboveDivide / underDivide
            }

            fun isPentagonal(number: Double): Boolean {
                val ans = getPentagonalOrigin(number)

                if (ans.isNaN()) {
                    return false
                }

                return ans == Math.floor(ans)
            }

            fun getHexagonalNumber(number: Int): Double {
//                number * ((2 * number) - 1)
                return number.times(2.times(number).minus(1.0))
            }

            fun getHexagonalOrigin(number: Double): Double {
                val a = 2.0
                val b = -1.0
                val c = 0 - number

                val underSqrt = b.pow(2).plus((-4).times(a.times(c)))

                val underDivide = 2 * a

                val sqrt = Math.sqrt(underSqrt)

                val aboveDivide = b.times(-1).plus(sqrt)

                return aboveDivide / underDivide
            }

            fun isHexagonal(number: Double): Boolean {
                val ans = getHexagonalOrigin(number)

                if (ans.isNaN()) {
                    return false
                }

                return ans == Math.floor(ans)

            }
        }
    }

    class DateTimeToolbox {
        /**
         * G	Era designator          Text	                AD
         * y	Year	                Year	                1996; 96
         * Y	Week year	            Year	                2009; 09
         * M	Month in year	        Month	                July; Jul; 07
         * w	Week in year	        Number	                27
         * W	Week in month	        Number	                2
         * D	Day in year	            Number	                189
         * d	Day in month	        Number	                10
         * F	Day of week in month	Number	                2
         * E	Day name in week	    Text	                Tuesday; Tue
         * u	Day number of week 	    Number	                1 (1 = Monday, ..., 7 = Sunday)
         * a	Am/pm marker	        Text	                PM
         * H	Hour in day (0-23)	    Number	                0
         * k	Hour in day (1-24)	    Number	                24
         * K	Hour in am/pm (0-11)	Number	                0
         * h	Hour in am/pm (1-12)	Number	                12
         * m	Minute in hour	        Number	                30
         * s	Second in minute	    Number	                55
         * S	Millisecond	            Number	                978
         * z	Time zone	            General time zone	    Pacific Standard Time; PST; GMT-08:00
         * Z	Time zone	            RFC 822 time zone	    -0800
         * X	Time zone	            ISO 8601 time zone	    -08; -0800; -08:00
         */
        companion object Tools {

            fun isValidDate(text: String?, pattern: String = "yyyy-MM-dd'T'HH:mm:ssz"): Boolean {
                if (isNullOrEmpty(text)) {
                    return false
                }

                val sdf = SimpleDateFormat()
                sdf.applyPattern(pattern)

                return try {
                    val parsedDate: Date? = sdf.parse(text!!)
                    parsedDate != null
                } catch (pe: ParseException) {
                    false
                }
            }

            fun convertTo(value: Double, from: TimeUnit, to: TimeUnit): Double {
                val valueToConvert = from.getDurationInSeconds(value)
                val conversionDenominator = to.getDurationInSeconds(1.0)

                return valueToConvert / conversionDenominator
            }

            fun getHumanReadableDuration(duration: Double, timeUnit: TimeUnit): String {
                return TimeUnit.Day.writeOut(convertTo(duration, timeUnit, TimeUnit.Day)).trim()
            }
        }

        enum class TimeUnit(private val si: String, private val durationInSeconds: Double, private val oneStepDown: TimeUnit? = null) {
            Picosecond("ps", 1e-12, null),
            Nanosecond("ns", 1e-9, Picosecond),
            Microsecond("µs", 1e-6, Nanosecond),
            Millisecond("ms", 1e-3, Microsecond),
            Second("s", 1.0, Millisecond),
            Minute("m", 60.0, Second),
            Hour("hr", 3600.0, Minute),
            Day("d", 86400.0, Hour);

            fun getDurationInSeconds(value: Double): Double {
                return value.times(this.durationInSeconds)
            }

            fun writeOut(value: Double): String {
                val retStr = StringBuilder("")

                val wholeValue = Math.floor(value).toInt()
                val leftOver = value.minus(wholeValue)

                if (wholeValue > 0) {
                    retStr.append(wholeValue).append(this.si)
                }
                if (this.oneStepDown != null) {
                    if (leftOver > 0) {
                        val passOn = convertTo(leftOver, this, this.oneStepDown)
                        val strToAdd = this.oneStepDown.writeOut(passOn)
                        if (strToAdd != "0${this.oneStepDown.si}") {
                            retStr.append(" ").append(strToAdd)
                        }
                    }
                }
                if (retStr.isEmpty()) {
                    retStr.append("0").append(this.si)
                }

                return retStr.toString()
            }
        }
    }

    class MathToolbox {
        companion object Tools {

            fun generateSphericalPoints(n: Int): List<Triple<Double, Double, Double>> {
                return NX(n, 0.1 + 1.2 * n)
            }

            fun NX(n: Int, x: Double): List<Triple<Double, Double, Double>> {
                val points: ArrayList<Triple<Double, Double, Double>> = arrayListOf()

                val start = (-1.0 + 1.0 / (n - 1.0))
//                val startPart1 = n.minus(1.0)
//                val startPart2 = 1.0.div(startPart1)
//                val start = (-1.0).plus(startPart2)
//                val start = (-1.0).plus(1.0.div(n.minus(1.0)))

                val increment = (2.0 - 2.0 / (n - 1.0)) / (n - 1.0)
//                val incrementPart1 = n.minus(1.0)
//                val incrementPart2 = 2.0.div(incrementPart1)
//                val incrementPart3 = 2.0.minus(incrementPart2)
//                val incrementPart4 = n.minus(1.0)
//                val increment = incrementPart3.div(incrementPart4)
//                val increment = 2.0.minus(2.0.div(n.minus(1.0))).div(n.minus(1.0))

                (0 until n).forEach {
                    val s = start.plus(it.times(increment))
                    val tmpX = s * x

                    val tmpY = Math.PI / 2 * Math.copySign(1.0, s) * (1.0 - Math.sqrt(1.0 - Math.abs(s)))
//                    val tmpYPart1 = Math.PI.div(2)
//                    val tmpYPart2 = Math.copySign(1.0, s)

//                    val tmpYPart3Step1 = Math.abs(s)
//                    val tmpYPart3Step2 = 1.0.minus(tmpYPart3Step1)
//                    val tmpYPart3Step3 = Math.sqrt(tmpYPart3Step2)
//                    val tmpYPart3 = 1.0.minus(tmpYPart3Step3)

//                    val tmpY = tmpYPart1.times(tmpYPart2).times(tmpYPart3)
//                    val tmpY = Math.PI.div(2).times(Math.copySign(1.0, s)).times(1.0.minus(Math.sqrt(1.0.minus(Math.abs(s)))))

                    points.add(sphericalCoordinate(tmpX, tmpY))
                }
                return points.toList()
            }

            fun sphericalCoordinate(x: Double, y: Double): Triple<Double, Double, Double> {

                val scX = Math.cos(x) * Math.cos(y)
                val scY = Math.sin(x) * Math.cos(y)
                val scZ = Math.sin(y)

                return Triple(scX, scY, scZ)
            }
        }
    }

    class LengthToolBox {

        companion object Tools {
            fun convertTo(value: Double, from: MeasurementUnit, to: MeasurementUnit): Double {
                val valueToConvert = from.getLengthInCM(value)
                val conversionDenominator = to.getLengthInCM(1.0)

                return valueToConvert / conversionDenominator
            }
        }

        enum class MeasurementUnit(private val si: String, private val cmBaseLength: Double, private val oneStepDown: MeasurementUnit? = null) {
            Nanometer("nm", 1e-7),
            Micrometer("μm", 1e-4, Nanometer),
            Millimetre("mm", 0.1, Micrometer),
            Centimetre("cm", 1.0, Millimetre),
            Meter("m", 100.0, Centimetre),
            Kilometer("km", 100000.0, Meter),
            Inch("in", 2.54, null),
            Foot("ft", 30.48, Inch),
            Yard("yd", 91.44, Foot),
            Mile("mi", 160934.0, Yard);

            fun getLengthInCM(value: Double): Double {
                return value.times(this.cmBaseLength)
            }

            fun writeOut(value: Double): String {
                val retStr = StringBuilder("")

                val wholeValue = Math.floor(value).toInt()
                val leftOver = value.minus(wholeValue)

                if (wholeValue > 0) {
                    retStr.append(wholeValue).append(this.si)
                }
                if (this.oneStepDown != null) {
                    if (leftOver > 0) {
                        val passOn = convertTo(leftOver, this, this.oneStepDown)
                        val strToAdd = this.oneStepDown.writeOut(passOn)
                        if (strToAdd != "0${this.oneStepDown.si}") {
                            retStr.append(" ").append(strToAdd)
                        }
                    }
                }
                if (retStr.isEmpty()) {
                    retStr.append("0").append(this.si)
                }

                return retStr.toString()
            }
        }
    }
}
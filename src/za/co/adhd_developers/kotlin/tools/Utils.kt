package za.co.adhd_developers.kotlin.tools

import za.co.adhd_developers.kotlin.Question
import java.math.BigInteger

fun Question.getFactors(number: Long): HashSet<Long> {
    var factor: Long = 1
    var endPoint = number
    val factors = HashSet<Long>()

    while (factor < endPoint) {
        if (number.rem(factor) == 0L) {
            factors.add(factor)
            endPoint = number.div(factor)
            factors.add(endPoint)
        }
        factor++
    }

    return factors
}

fun Question.isPrime(number: Long): Boolean {
    if (number <= 1) {
        return false
    }

    if (number > 2 && number % 2 == 0L) {
        return false
    }

    val top = Math.sqrt(number.toDouble()).toInt() + 1
    for (i in 3 until top step 2) {
        if (number % i == 0L) {
            return false
        }
    }

    return true
}

fun Question.getNextPrime(number: Long): Long {
    var newPrime = number + 1
    if (newPrime % 2 == 0L) {
        newPrime++
    }
    while (!isPrime(newPrime)) {
        newPrime += 2
    }

    return newPrime;
}

fun Question.getPrimeFactors(number: Long): HashSet<Long> {
    var factor: Long = 1
    var endPoint = number
    val factors = HashSet<Long>()

    while (factor < endPoint) {
        if (number % factor == 0L) {
            if (isPrime(factor)) {
                factors.add(factor)
            }
            endPoint = Math.floorDiv(number, factor)
            if (isPrime(endPoint)) {
                factors.add(endPoint)
            }
        }
        factor++
    }

    return factors
}

fun Question.isPalindromic(text: String): Boolean {
    return text.equals(text.reversed(), false)
}

fun factorial(number: BigInteger): BigInteger {
    return if (number <= BigInteger("1")) {
        BigInteger("1")
    } else {
        val tmp = factorial(number.subtract(BigInteger("1")))
        number.multiply(tmp)
    }
}
package za.co.adhd_developers.kotlin.tools

import za.co.adhd_developers.kotlin.Question

fun Question.getTriangleNumber(n: Int): Double {
    return 0.5.times(n).times(n.plus(1))
}

fun Question.getTriangleOrigin(number: Double): Double {
    val a = 0.5.times(10)
    val b = 0.5.times(10)
    val c = 0.minus(number.times(10))

    val underSqrt = Math.pow(b, 2.0).plus((-4).times(a.times(c)))

    val underDivide = 2.times(a)

    val sqrt = Math.sqrt(underSqrt)

    val aboveDivide = b.times(-1).plus(sqrt)

    return aboveDivide / underDivide
}

fun Question.isTriangle(number: Double) : Boolean {
    val ans = getTriangleOrigin(number)

    if (ans.isNaN()) {
        return false
    }

    return ans == Math.floor(ans)
}

fun Question.getPentagonal(number: Double): Double {
    return number.times(3.times(number).minus(1)).div(2)
}

fun Question.getPentagonalOrigin(number: Double): Double {
    val a = 1.5 * 10
    val b = -0.5 * 10
    val c = 0 - number * 10

    val underSqrt = Math.pow(b, 2.0) + -4 * (a * c)

    val underDivide = 2 * a

    val sqrt = Math.sqrt(underSqrt)

    val aboveDivide = b * -1 + sqrt

    return aboveDivide / underDivide
}

fun Question.isPentagonal(number: Double): Boolean {
    val ans = getPentagonalOrigin(number)

    if (ans.isNaN()) {
        return false
    }

    return ans == Math.floor(ans)
}
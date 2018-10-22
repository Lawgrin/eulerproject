package za.co.adhd_developers.kotlin.questions

import za.co.adhd_developers.kotlin.Question

class Question31 : Question {

    var answer = 0

    override fun doWork() {

        var total = 0

        for (p200 in 0..1) {

            if (p200 > 0) {
                total -= p200.minus(1).times(200)
            }
            total += p200.times(200)
            if (total == 200) {
                answer++
                total -= p200.times(200)
                break
            } else if (total > 200) {
                total -= p200.times(200)
                break
            }
            for (p100 in 0..2) {

                if (p100 > 0) {
                    total -= p100.minus(1).times(100)
                }
                total += p100.times(100)
                if (total == 200) {
                    answer++
                    total -= p100.times(100)
                    break
                } else if (total > 200) {
                    total -= p100.times(100)
                    break
                }
                for (p50 in 0..4) {

                    if (p50 > 0) {
                        total -= p50.minus(1).times(50)
                    }
                    total += p50.times(50)
                    if (total == 200) {
                        answer++
                        total -= p50.times(50)
                        break
                    } else if (total > 200) {
                        total -= p50.times(50)
                        break
                    }
                    for (p20 in 0..10) {

                        if (p20 > 0) {
                            total -= p20.minus(1).times(20)
                        }
                        total += p20.times(20)
                        if (total == 200) {
                            answer++
                            total -= p20.times(20)
                            break
                        } else if (total > 200) {
                            total -= p20.times(20)
                            break
                        }
                        for (p10 in 0..20) {

                            if (p10 > 0) {
                                total -= p10.minus(1).times(10)
                            }
                            total += p10.times(10)
                            if (total == 200) {
                                answer++
                                total -= p10.times(10)
                                break
                            } else if (total > 200) {
                                total -= p10.times(10)
                                break
                            }
                            for (p5 in 0..40) {

                                if (p5 > 0) {
                                    total -= p5.minus(1).times(5)
                                }
                                total += p5.times(5)
                                if (total == 200) {
                                    answer++
                                    total -= p5.times(5)
                                    break
                                } else if (total > 200) {
                                    total -= p5.times(5)
                                    break
                                }
                                for (p2 in 0..100) {

                                    if (p2 > 0) {
                                        total -= p2.minus(1).times(2)
                                    }
                                    total += p2.times(2)
                                    if (total == 200) {
                                        answer++
                                        total -= p2.times(2)
                                        break
                                    } else if (total > 200) {
                                        total -= p2.times(2)
                                        break
                                    }
                                    for (p1 in 0..200) {

                                        if (p1 > 0) {
                                            total -= p1.minus(1).times(1)
                                        }
                                        total += p1.times(1)
                                        if (total == 200) {
                                            answer++
                                            total -= p1.times(1)
                                            break
                                        } else if (total > 200) {
                                            total -= p1.times(1)
                                            break
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun printAnswer() {
        println("=================================")
        println("Question 31")
        println("The total number of coin combinations, is: " + this.answer)
        println("=================================")
    }
}
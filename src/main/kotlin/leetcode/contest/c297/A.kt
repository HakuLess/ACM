package leetcode.contest.c297

import utils.print

fun main() {
    val s = SolutionA()
    s.calculateTax(
        arrayOf(
            intArrayOf(3, 50),
            intArrayOf(7, 10),
            intArrayOf(12, 25)
        ), 10
    ).print()

    s.calculateTax(
        arrayOf(
            intArrayOf(1, 0),
            intArrayOf(4, 25),
            intArrayOf(5, 50)
        ), 2
    ).print()
}

class SolutionA {
    fun calculateTax(brackets: Array<IntArray>, income: Int): Double {
        var ans = 0.0
        for (i in brackets.indices) {
            if (brackets[i][0] <= income) {
                ans += if (i == 0) brackets[i][0] * brackets[i][1] else {
                    (brackets[i][0] - brackets[i - 1][0]) * brackets[i][1]
                }
            } else {
                ans += if (i == 0) income * brackets[i][1] else {
                    (income - brackets[i - 1][0]) * brackets[i][1]
                }
                break
            }
            ans.print()
        }
        return ans / 100
    }
}
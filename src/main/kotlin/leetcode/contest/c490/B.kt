package leetcode.contest.c490

import utils.print

fun main() {
    val s = SolutionB()
    s.isDigitorialPermutation(145).print()
    s.isDigitorialPermutation(10).print()
}

class SolutionB {
    fun isDigitorialPermutation(n: Int): Boolean {

        val set = listOf(1, 2, 145, 40585)

        fun digitCount(x: Int): IntArray {
            val count = IntArray(10)
            var num = x
            if (num == 0) count[0]++
            while (num > 0) {
                count[num % 10]++
                num /= 10
            }
            return count
        }

        val targetCount = digitCount(n)

        for (c in set) {
            if (c.toString().length == n.toString().length) {
                if (targetCount.contentEquals(digitCount(c))) {
                    return true
                }
            }
        }

        return false
    }
}
package leetcode.contest.c298

import utils.print

fun main() {
    val s = SolutionB()
    s.minimumNumbers(58, 9).print()

    s.minimumNumbers(2, 8).print()
}

class SolutionB {
    fun minimumNumbers(num: Int, k: Int): Int {
        if (num == 0) return 0

        var ans = 0
        val arr = ArrayList<Int>()
        var cur = k
        while (cur % 10 !in arr) {
            arr.add(cur % 10)
            ans++
            if (cur % 10 == num % 10) {
                if (cur > num) return -1
                else return ans
            }
            cur += k
        }

        return -1
    }
}
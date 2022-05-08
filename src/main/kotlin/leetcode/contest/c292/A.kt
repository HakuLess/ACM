package leetcode.contest.c292

import utils.print

fun main() {
    val s = SolutionA()
    s.largestGoodInteger("6777133339").print()
}

class SolutionA {
    fun largestGoodInteger(num: String): String {
        var ans = ""
        var cur = -1
        for (i in 0 until num.length - 2) {
            val a = num.substring(i, i + 3)
            if (num[i] == num[i + 1] && num[i + 1] == num[i + 2]) {
                if (a.toInt() > cur) {
                    cur = a.toInt()
                    ans = a
                }
            }
        }
        return ans
    }
}
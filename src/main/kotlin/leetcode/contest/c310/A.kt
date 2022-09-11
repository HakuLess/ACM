package leetcode.contest.c310

import utils.print

fun main() {
    val s = SolutionA()
    s.mostFrequentEven(intArrayOf(0, 1, 2, 2, 4, 4, 1)).print()
}

class SolutionA {
    fun mostFrequentEven(nums: IntArray): Int {
        val map = HashMap<Int, Int>()
        nums.forEach {
            map[it] = map.getOrDefault(it, 0) + 1
        }
        var c = 0
        var ans = Int.MAX_VALUE
        map.forEach { t, u ->
            if (t % 2 == 0 && u >= c) {
                if (u > c) {
                    ans = t
                    c = u
                } else {
                    if (t < ans) {
                        ans = t
                    }
                }
            }
        }
        return if (ans == Int.MAX_VALUE) -1 else ans
    }
}
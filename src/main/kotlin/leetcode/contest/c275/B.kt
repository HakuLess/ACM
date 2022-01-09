package leetcode.contest.c275

import utils.print

fun main() {
    val s = SolutionB()
    s.minSwaps(intArrayOf(0, 1, 0, 1, 1, 0, 0)).print()
}

class SolutionB {
    fun minSwaps(nums: IntArray): Int {
        val c = nums.count { it == 1 }
        val arr = ArrayList<Int>()
        arr.addAll(nums.toTypedArray())
        arr.addAll(nums.toTypedArray())

        var cur = 0
        var ans = 0
        for (i in arr.indices) {
            cur += arr[i]
            if (i >= c) {
                cur -= arr[i - c]
            }
            ans = maxOf(ans, cur)
        }
        return c - ans
    }
}
package leetcode.contest.b104

import utils.print

fun main() {
    val s = SolutionD()
    s.sumOfPower(intArrayOf(2, 1, 4)).print()
    // 13928461
    s.sumOfPower(intArrayOf(76, 24, 96, 82, 97)).print()
}

class SolutionD {
    fun sumOfPower(nums: IntArray): Int {
        nums.sort()
        var ans = 0L
        val mod = 1000000007L
        var step = 0L
        nums.forEach {
            ans = (ans + it.toLong() * (it * (it + step) % mod)) % mod
            step = (step * 2 + it) % mod
        }
        return (ans % mod).toInt()
    }
}
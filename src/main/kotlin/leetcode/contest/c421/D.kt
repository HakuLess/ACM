package leetcode.contest.c421

import utils.print

fun main() {
    val s = SolutionD()
    s.lengthAfterTransformations(
        "abcyy",
        2,
        listOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2)
    ).print()
}

// https://leetcode.cn/contest/weekly-contest-421/problems/total-characters-in-string-after-transformations-ii/
class SolutionD {
    fun lengthAfterTransformations(s: String, t: Int, nums: List<Int>): Int {
        val mod = 1_000_000_007L

        var arr = LongArray(26)
        s.forEach {
            arr[it - 'a']++
        }

        repeat(t) {
            val new = LongArray(26)

            for (i in 0 until 26) {
                repeat(nums[i]) {
                    new[(i + it + 1) % 26] += arr[i] % mod
                }
            }
            arr = new
        }

        return (arr.sumOf { it } % mod).toInt()
    }
}
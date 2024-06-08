package leetcode.contest.b132

import utils.print

fun main() {
    val s = SolutionD()
    // 4
//    s.maximumLength(intArrayOf(1, 2, 1, 1, 3), 2).print()
//    // 2
//    s.maximumLength(intArrayOf(1, 2, 3, 4, 5, 1), 0).print()
//
//    //
//    s.maximumLength(intArrayOf(2, 15), 2).print()

    s.maximumLength(intArrayOf(29, 30, 30), 0).print()
//    s.maximumLength(intArrayOf(7, 19, 17, 29), 1).print()

}

class SolutionD {
    fun maximumLength(nums: IntArray, k: Int): Int {
        val n = nums.size
        val dp = Array(n + 1) { Array(k + 2) { IntArray(2) } }
        val map = HashMap<Int, Int>()

        for (i in 1..n) {
            for (j in 1..k + 1) {
                // 到位置i，不以当前元素结尾
                dp[i][j][0] = maxOf(dp[i - 1][j][0], dp[i - 1][j][1])
                // 到位置i，以当前元素结尾
                dp[i][j][1] = maxOf(dp[i - 1][j - 1][0], dp[i - 1][j - 1][1]) + 1

                val index = map[nums[i - 1]]
                if (index != null) {
                    // 到位置i，以当前元素结尾 上一个相同元素结尾值+1
                    dp[i][j][1] = maxOf(dp[i][j][1], dp[index + 1][j][1] + 1)
                }
            }
            map[nums[i - 1]] = i - 1
        }

        return maxOf(dp[n][k + 1][0], dp[n][k + 1][1])
    }
}
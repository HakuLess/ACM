package leetcode.contest.c474

class SolutionB {
    fun maxProduct(nums: IntArray): Long {

        val sorted = nums.sorted()

        // 提取最小和最大部分
        val min3 = sorted.take(3)
        val max3 = sorted.takeLast(3).reversed()

        var ans = maxOf(
            1L * max3[0] * max3[1] * max3[2],
            1L * min3[0] * min3[1] * max3[0]
        )

        // 允许替换一个数为极值：±100000
        val extremes = listOf(-100000, 100000)

        for (rep in extremes) {
            // 替换后重新考虑能出现的组合
            ans = maxOf(ans,
                1L * rep * max3[0] * max3[1],
                1L * rep * max3[0] * min3[0],
                1L * rep * min3[0] * min3[1]
            )
        }

        return ans
    }
}
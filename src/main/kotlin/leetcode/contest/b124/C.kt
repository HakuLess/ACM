package leetcode.contest.b124

import utils.print

fun main() {
    val s = SolutionC()
    // 3
//    s.maxOperations(intArrayOf(3, 2, 1, 2, 3, 4)).print()
    // 2
//    s.maxOperations(intArrayOf(3, 2, 6, 1, 4)).print()
    // 3
//    s.maxOperations(intArrayOf(1, 1, 1, 1, 1, 1)).print()

    // 4
    s.maxOperations(intArrayOf(1, 1, 2, 3, 2, 2, 1, 3, 3)).print()
    // 2
    s.maxOperations(intArrayOf(3, 3, 7, 3, 2, 4, 2, 3)).print()
}

class SolutionC {
    fun maxOperations(nums: IntArray): Int {
        val n = nums.size
        val sum1 = nums[0] + nums[1]
        val sum2 = nums[n - 2] + nums[n - 1]
        val sum3 = nums[0] + nums[n - 1]

        var res = 0

        val seen = HashMap<String, Int>()

        fun dfs(i: Int, j: Int, sum: Int): Int {
            if (i >= j) return 0
            val key = "$i,$j,$sum"
            if (key in seen) return seen[key]!!

            var ans = 0
            if (i + 1 in nums.indices && nums[i] + nums[i + 1] == sum) {
                ans = maxOf(ans, dfs(i + 2, j, sum) + 1)
            }
            if (j - 1 in nums.indices && j - 1 > i && nums[j] + nums[j - 1] == sum) {
                ans = maxOf(ans, dfs(i, j - 2, sum) + 1)
            }
            if (i in nums.indices && j in nums.indices && nums[i] + nums[j] == sum) {
                ans = maxOf(ans, dfs(i + 1, j - 1, sum) + 1)
            }
            return ans.also {
                seen[key] = it
            }
        }

        res = maxOf(res, dfs(0, n - 1, sum1))
        res = maxOf(res, dfs(0, n - 1, sum2))
        res = maxOf(res, dfs(0, n - 1, sum3))

        return res
    }
}
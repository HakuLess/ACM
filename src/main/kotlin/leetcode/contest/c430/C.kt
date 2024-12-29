package leetcode.contest.c430

class SolutionC {
    fun numberOfSubsequences(nums: IntArray): Long {
        val productMap = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()
        val n = nums.size

        // 存储所有 (q, s) 的乘积
        for (q in 1 until n - 2) {
            for (s in q + 2 until n) {
                val product = nums[q] * nums[s]
                productMap.computeIfAbsent(product) { mutableListOf() }.add(Pair(q, s))
            }
        }

        var count = 0L

        // 查找满足条件的 (p, r)
        for (p in 0 until n - 3) {
            for (r in p + 2 until n - 1) {
                val product = nums[p] * nums[r]
                // 如果有相同乘积，检查间隔条件
                productMap[product]?.forEach { (q, s) ->
                    if (p < q && r < s) {
                        count++
                    }
                }
            }
        }

        return count
    }
}
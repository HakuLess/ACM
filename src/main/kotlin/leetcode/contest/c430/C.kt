package leetcode.contest.c430

class SolutionC {
    fun numberOfSubsequences(nums: IntArray): Long {
        val map = HashMap<Double, Int>()
        var res: Long = 0L

        for (i in 4 until nums.size - 2) {
            val q = nums[i - 2].toDouble()

            // 作为 nums[r]
            val cur = nums[i].toDouble()

            // 不断在 Map 中补充 nums[p] / nums[q] 的值
            for (p in 0 until i - 3) {
                val div = nums[p] / q
                map[div] = map.getOrDefault(div, 0) + 1
            }

            // 保证 nums[p] / nums[q] = nums[s] / nums[r]
            for (s in i + 2 until nums.size) {
                res += map.getOrDefault(nums[s] / cur, 0)
            }
        }
        return res
    }
}
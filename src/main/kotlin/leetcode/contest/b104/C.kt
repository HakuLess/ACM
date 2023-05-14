package leetcode.contest.b104

class SolutionC {
    fun maximumOr(nums: IntArray, k: Int): Long {

        val n = nums.size
        val left = LongArray(n)
        val right = LongArray(n)
        for (i in nums.indices) {
            if (i == 0) left[i] = nums[i].toLong()
            else left[i] = left[i - 1] or nums[i].toLong()
        }
        for (i in nums.indices.reversed()) {
            if (i == nums.lastIndex) right[i] = nums[i].toLong()
            else right[i] = right[i + 1] or nums[i].toLong()
        }

        var ans = 0L
        for (i in nums.indices) {
            val l = if (i - 1 >= 0) left[i - 1] else 0
            val r = if (i + 1 <= n - 1) right[i + 1] else 0
            ans = maxOf(ans, l or r or (nums[i].toLong() shl k))
        }
        return ans
    }
}
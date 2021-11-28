package leetcode.contest.c269

class SolutionB {
    fun getAverages(nums: IntArray, k: Int): IntArray {
        var cur = 0L
        var size = 0
        val ans = IntArray(nums.size) { -1 }
        for (i in nums.indices) {
            cur += nums[i]
            size++
            if (size == k * 2 + 1) {
                ans[i - k] = (cur / size).toInt()
                cur -= nums[i - k * 2]
                size--
            }
        }
        return ans
    }
}
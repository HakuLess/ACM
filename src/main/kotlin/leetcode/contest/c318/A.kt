package leetcode.contest.c318

class SolutionA {
    fun applyOperations(nums: IntArray): IntArray {
        val ans = ArrayList<Int>()
        for (i in 0 until nums.size - 1) {
            if (nums[i] == nums[i + 1]) {
                nums[i] = 2 * nums[i]
                nums[i + 1] = 0
            }
            ans.add(nums[i])
        }
        ans.add(nums.last())
        val filter = ArrayList<Int>()
        filter.addAll(ans.filter { it != 0 })
        filter.addAll(ans.filter { it == 0 })
        return filter.toIntArray()
    }
}
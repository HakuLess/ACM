package leetcode.contest.c315

class SolutionB {
    fun countDistinctIntegers(nums: IntArray): Int {
        val set = nums.toHashSet()
        for (i in nums.indices) {
            set.add(nums[i].toString().reversed().toInt())
        }
        return set.size
    }
}
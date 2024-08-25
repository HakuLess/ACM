package leetcode.contest.c412

class SolutionA {
    fun getFinalState(nums: IntArray, k: Int, multiplier: Int): IntArray {
        repeat(k) {
            var minIndex = 0
            for (j in 1 until nums.size) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j
                }
            }
            nums[minIndex] *= multiplier
        }
        return nums
    }
}
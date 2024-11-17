package leetcode.contest.c424

class SolutionA {
    fun countValidSelections(nums: IntArray): Int {
        val n = nums.size

        fun isAllZero(arr: IntArray): Boolean {
            return arr.all { it == 0 }
        }

        fun simulate(nums: IntArray, start: Int, direction: Int): Boolean {
            val numsCopy = nums.clone()
            var curr = start
            var dir = direction

            while (curr in 0 until n) {
                if (numsCopy[curr] == 0) {
                    curr += dir
                } else if (numsCopy[curr] > 0) {
                    numsCopy[curr]--
                    dir = -dir
                    curr += dir
                }
            }

            return isAllZero(numsCopy)
        }

        var ans = 0
        for (i in nums.indices) {
            if (nums[i] == 0) {
                if (simulate(nums, i, 1)) {
                    ans++
                }
                if (simulate(nums, i, -1)) {
                    ans++
                }
            }
        }

        return ans
    }
}
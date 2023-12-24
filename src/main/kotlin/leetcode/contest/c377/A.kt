package leetcode.contest.c377

class SolutionA {
    fun numberGame(nums: IntArray): IntArray {
        val ans = ArrayList<Int>()
        nums.sort()
        for (i in nums.indices step 2) {
            ans.add(nums[i + 1])
            ans.add(nums[i])
        }
        return ans.toIntArray()
    }
}
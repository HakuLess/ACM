package leetcode.contest.c376

class SolutionB {
    fun divideArray(nums: IntArray, k: Int): Array<IntArray> {
        nums.sort()
        val ans = ArrayList<IntArray>()
        for (i in nums.indices step 3) {
            val cur = intArrayOf(nums[i], nums[i + 1], nums[i + 2])
            if (nums[i + 2] - nums[i] > k) {
                ans.clear()
                break
            }
            ans.add(cur)
        }
        return ans.toTypedArray()
    }
}
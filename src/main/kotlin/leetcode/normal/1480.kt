package leetcode.normal

class Solution1480 {
    fun runningSum(nums: IntArray): IntArray {
//        val ans = IntArray(nums.size)
//        for (i in ans.indices) {
//            ans[i] = (if (i > 0) ans[i - 1] else 0) + nums[i]
//        }
//        return ans
        val ans = arrayListOf(nums[0])
        nums.reduce { acc, i -> (acc + i).also { ans.add(it) } }
        return ans.toIntArray()
    }
}
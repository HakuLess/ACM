package leetcode.contest.b67

class SolutionA {
    fun maxSubsequence(nums: IntArray, k: Int): IntArray {
        val a = ArrayList<Pair<Int, Int>>()
        for (i in nums.indices) {
            a.add(Pair(i, nums[i]))
        }
        a.sortByDescending { it.second }
        val ans = ArrayList<Pair<Int, Int>>()
        for (i in 0 until k) {
            ans.add(a[i])
        }
        return ans.sortedBy { it.first }.map { it.second }.toIntArray()
    }
}
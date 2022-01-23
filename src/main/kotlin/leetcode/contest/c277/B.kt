package leetcode.contest.c277

class SolutionB {
    fun rearrangeArray(nums: IntArray): IntArray {
        val a = nums.filter { it < 0 }
        val b = nums.filter { it > 0 }
        val ans = ArrayList<Int>()
        for (i in a.indices) {
            ans.add(b[i])
            ans.add(a[i])
        }
        return ans.toIntArray()
    }
}
package leetcode.contest.b97

class SolutionA {
    fun separateDigits(nums: IntArray): IntArray {
        val ans = ArrayList<Int>()
        nums.joinToString("").forEach {
            ans.add(it - '0')
        }
        return ans.toIntArray()
    }
}
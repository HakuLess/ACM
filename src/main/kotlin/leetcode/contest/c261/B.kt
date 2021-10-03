package leetcode.contest.c261

class Solution5891 {
    fun missingRolls(rolls: IntArray, mean: Int, n: Int): IntArray {
        val left = (rolls.size + n) * mean - rolls.sum()
        if (left.toDouble() / n > 6.0 || left.toDouble() / n < 1.0) {
            return intArrayOf()
        }
        val ans = IntArray(n)
        for (i in 0 until left) {
            ans[i % n]++
        }
        return ans
    }
}
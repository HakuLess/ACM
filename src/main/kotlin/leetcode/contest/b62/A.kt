package leetcode.contest.b62

class Solution5871 {
    fun construct2DArray(original: IntArray, m: Int, n: Int): Array<IntArray> {
        if (original.size != m * n) return emptyArray()
        val ans = Array<IntArray>(m) { IntArray(n) }
        for (i in original.indices) {
            ans[i / n][i % n] = original[i]
        }
        return ans
    }
}
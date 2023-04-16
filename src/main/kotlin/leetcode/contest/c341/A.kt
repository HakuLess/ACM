package leetcode.contest.c341

class SolutionA {
    fun rowAndMaximumOnes(mat: Array<IntArray>): IntArray {
        val ans = IntArray(2)
        for (i in mat.indices) {
            val cur = mat[i].count { it == 1 }
            if (cur > ans[1]) {
                ans[0] = i
                ans[1] = cur
            }
        }
        return ans
    }
}
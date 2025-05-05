package leetcode.normal

class Solution1128 {
    fun numEquivDominoPairs(dominoes: Array<IntArray>): Int {
        var ans = 0
        for (i in dominoes.indices) {
            for (j in i + 1 until dominoes.size) {
                val (a, b) = dominoes[i]
                val (c, d) = dominoes[j]
                if (a == d && b == c) {
                    ans++
                } else if (a == c && b == d) {
                    ans++
                }
            }
        }
        return ans
    }
}
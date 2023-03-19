package leetcode.contest.c337

class SolutionA {
    fun evenOddBit(n: Int): IntArray {
        val ans = intArrayOf(0, 0)
        n.toString(2).reversed().forEachIndexed { index, c ->
            if (c == '1') {
                ans[index % 2]++
            }
        }
        return ans
    }
}
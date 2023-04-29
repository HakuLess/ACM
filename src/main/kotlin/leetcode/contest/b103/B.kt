package leetcode.contest.b103

class SolutionB {
    fun findThePrefixCommonArray(A: IntArray, B: IntArray): IntArray {
        val n = A.size
        val ans = IntArray(n)
        val a = hashSetOf<Int>()
        val b = hashSetOf<Int>()
        for (i in 0 until n) {
            a.add(A[i])
            b.add(B[i])
            ans[i] = a.count { it in b }
        }
        return ans
    }
}
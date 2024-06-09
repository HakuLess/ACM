package leetcode.contest.c401

class SolutionB {
    fun valueAfterKSeconds(n: Int, k: Int): Int {
        val mod = 1_000_000_007L
        val a = LongArray(n) { 1 }

        for (time in 1..k) {
            val prefixSum = LongArray(n)
            prefixSum[0] = a[0]
            for (i in 1 until n) {
                prefixSum[i] = (prefixSum[i - 1] + a[i]) % mod
            }
            for (i in 0 until n) {
                a[i] = prefixSum[i]
            }
        }

        return (a[n - 1] % mod).toInt()
    }
}
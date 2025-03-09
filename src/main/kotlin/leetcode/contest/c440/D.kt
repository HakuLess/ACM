package leetcode.contest.c440

class SolutionD {
    fun maxSubarrays(n: Int, conflictingPairs: Array<IntArray>): Long {

        fun helper(n: Int, arr: IntArray): Long {
            val dp = LongArray(n + 2) { n + 1L }
            dp[n] = arr[n].toLong()
            for (i in n - 1 downTo 1) {
                dp[i] = minOf(arr[i].toLong(), dp[i + 1])
            }
            var sum = 0L
            for (i in 1..n) {
                sum += if (dp[i] == n + 1L) n - i + 1L else (dp[i] - i)
            }
            return sum
        }

        val pairs = conflictingPairs.map {
            val a = it[0]
            val b = it[1]
            if (a < b) intArrayOf(a, b) else intArrayOf(b, a)
        }

        val list = Array(n + 1) { mutableListOf<Int>() }
        for (p in pairs) {
            val a = p[0]
            val b = p[1]
            list[a].add(b)
        }

        val arr = IntArray(n + 1) { n + 1 }
        for (i in 1..n) {
            if (list[i].isNotEmpty()) {
                arr[i] = list[i].minOrNull()!!
            }
        }

        val cnt = helper(n, arr)
        var ans = cnt

        for (p in pairs) {
            val a = p[0]
            val b = p[1]
            if (b != arr[a] || list[a].count { it == b } > 1) continue
            val newXForA = if (list[a].size > 1) {
                list[a].sorted()[1]
            } else {
                n + 1
            }
            val newX = arr.copyOf()
            newX[a] = newXForA
            val nextAns = helper(n, newX)
            ans = maxOf(ans, nextAns)
        }
        return ans
    }
}
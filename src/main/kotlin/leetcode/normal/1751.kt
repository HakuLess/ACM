package leetcode.normal

class Solution1751 {
    fun maxValue(events: Array<IntArray>, k: Int): Int {

        data class Event(val start: Int, val end: Int, val value: Int)

        val sortedEvents = events.map { Event(it[0], it[1], it[2]) }.sortedBy { it.end }

        val n = sortedEvents.size

        // 二分查找：查找在 i 之前结束的最后一个不冲突的会议
        fun findLastNonConflict(i: Int): Int {
            var left = 0
            var right = i - 1
            var res = -1
            while (left <= right) {
                val mid = (left + right) / 2
                if (sortedEvents[mid].end < sortedEvents[i].start) {
                    res = mid
                    left = mid + 1
                } else {
                    right = mid - 1
                }
            }
            return res
        }

        // dp[i][j] = 前 i 个会议中，选 j 个会议的最大价值
        val dp = Array(n + 1) { IntArray(k + 1) }

        for (i in 1..n) {
            val curr = sortedEvents[i - 1]
            val last = findLastNonConflict(i - 1)
            for (j in 1..k) {
                // 不参加当前会议
                dp[i][j] = maxOf(dp[i][j], dp[i - 1][j])
                // 参加当前会议
                val take = dp[last + 1][j - 1] + curr.value
                dp[i][j] = maxOf(dp[i][j], take)
            }
        }

        return dp[n][k]
    }
}
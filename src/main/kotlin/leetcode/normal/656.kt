package leetcode.normal

import utils.print
import java.util.*

fun main() {
    val s = Solution656()
    s.cheapestJump(intArrayOf(1, 2, 4, -1, 2), 2).joinToString().print()
    s.cheapestJump(intArrayOf(1, 2, 4, -1, 2), 1).joinToString().print()
    s.cheapestJump(intArrayOf(0, 0, 0, 0, 0, 0), 3).joinToString().print()

}

class Solution656 {
    fun cheapestJump(coins: IntArray, maxJump: Int): List<Int> {

        fun getMin(a: String, b: String): String {
            if (a.isEmpty()) return b
            val aa = a.split(',').map { it.toInt() }
            val bb = b.split(',').map { it.toInt() }
            for (i in 0 until minOf(aa.size, bb.size)) {
                if (aa[i] < bb[i]) {
                    return a
                } else if (bb[i] < aa[i]) {
                    return b
                }
            }
            return if (aa.size > bb.size) b else a
        }

        val n = coins.size
        val dp = IntArray(n) { Int.MAX_VALUE / 2 }
        dp[0] = coins[0]
        for (i in 0 until n) {
            for (j in 1..maxJump) {
                if (i + j in dp.indices && coins[i + j] != -1) {
                    dp[i + j] = minOf(dp[i + j], dp[i] + coins[i + j])
                }
            }
        }
        if (dp.last() == Int.MAX_VALUE / 2) return emptyList()
        val seen = BooleanArray(n)
        val ans = Array<String>(n) { "" }
        ans[ans.lastIndex] = "$n"
        val pq = PriorityQueue<Int>(compareBy { -it })
        pq.offer(coins.lastIndex)
        while (pq.isNotEmpty()) {
            val cur = pq.poll()
            for (i in cur - 1 downTo maxOf(0, cur - maxJump)) {
                if (dp[i] + coins[cur] == dp[cur]) {
                    if (!seen[i]) {
                        pq.offer(i)
                        seen[i] = true
                    }
                    ans[i] = getMin(ans[i], "${i + 1},${ans[cur]}")
                }
            }
        }
        return ans[0].split(",").map { it.toInt() }
    }
}
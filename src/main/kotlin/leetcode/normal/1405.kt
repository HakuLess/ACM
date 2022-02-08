package leetcode.normal

import utils.print
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val s = Solution1405()
    s.longestDiverseString(1, 1, 7).print()
}

class Solution1405 {
    fun longestDiverseString(a: Int, b: Int, c: Int): String {
        val pq = PriorityQueue<Pair<Int, Char>>(compareBy { -it.first })
        if (a > 0) pq.offer(Pair(a, 'a'))
        if (b > 0) pq.offer(Pair(b, 'b'))
        if (c > 0) pq.offer(Pair(c, 'c'))
        val ans = ArrayList<Char>()
        while (pq.isNotEmpty()) {
            val cur = pq.poll()
            if (ans.size >= 2 && ans[ans.lastIndex - 1] == cur.second && ans[ans.lastIndex] == cur.second) {
                if (pq.isEmpty()) {
                    break
                } else {
                    val item = pq.poll()
                    if (item.first > 1) {
                        pq.offer(Pair(item.first - 1, item.second))
                    }
                    ans.add(item.second)
                }
                pq.offer(cur)
            } else {
                if (cur.first > 1) {
                    pq.offer(Pair(cur.first - 1, cur.second))
                }
                ans.add(cur.second)
            }
        }
        return ans.joinToString("")
    }
}
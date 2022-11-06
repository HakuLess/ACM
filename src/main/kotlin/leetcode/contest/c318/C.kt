package leetcode.contest.c318

import utils.print
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val s = SolutionC()
    s.totalCost(intArrayOf(17, 12, 10, 2, 7, 2, 11, 20, 8), 3, 4).print()

    s.totalCost(intArrayOf(31, 25, 72, 79, 74, 65, 84, 91, 18, 59, 27, 9, 81, 33, 17, 58), 11, 2).print()
}

class SolutionC {
    fun totalCost(costs: IntArray, k: Int, candidates: Int): Long {
        // value index isLeft
        val cur = PriorityQueue<Triple<Int, Int, Boolean>>(compareBy({ it.first }, { it.second }))

        val c = ArrayList<Pair<Int, Int>>()
        for (i in costs.indices) {
            c.add(Pair(costs[i], i))
        }

        var lc = 0
        var rc = 0
        var ans = 0L
        var count = 0
        while (c.isNotEmpty()) {
            if (lc < candidates) {
                val item = c.removeAt(0)
                cur.add(Triple(item.first, item.second, true))
                lc++
                continue
            }
            if (rc < candidates) {
                val item = c.removeAt(c.lastIndex)
                cur.add(Triple(item.first, item.second, false))
                rc++
                continue
            }
            if (count < k) {
                val item = cur.poll()
                ans += item.first
                if (item.third) {
                    lc--
                } else {
                    rc--
                }
                count++
            }
            if (count == k) break
        }

        while (count < k) {
            val item = cur.poll()
            ans += item.first
            count++
        }
        return ans
    }
}
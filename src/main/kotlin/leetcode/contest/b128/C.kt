package leetcode.contest.b128

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class SolutionC {
    fun minimumTime(n: Int, edges: Array<IntArray>, disappear: IntArray): IntArray {

        // 节点 + 时间
        val map = HashMap<Int, ArrayList<Pair<Int, Int>>>()
        edges.forEach {
            map[it[0]] = map.getOrDefault(it[0], arrayListOf())
            map[it[0]]!!.add(Pair(it[1], it[2]))
            map[it[1]] = map.getOrDefault(it[1], arrayListOf())
            map[it[1]]!!.add(Pair(it[0], it[2]))
        }


        val ans = IntArray(n) { -1 }

        val seen = HashSet<Int>()

        // 节点 + 时间
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
        pq.offer(Pair(0, 0))
        while (pq.isNotEmpty()) {
            val item = pq.poll()
            if (disappear[item.first] <= item.second) continue
            if (item.first in seen) continue

            ans[item.first] = item.second
            seen.add(item.first)

            map[item.first]?.forEach {
                val next = Pair(it.first, it.second + item.second)
                pq.offer(next)
            }
        }

        return ans
    }
}
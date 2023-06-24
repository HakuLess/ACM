package leetcode.contest.b107

import utils.print
import utils.toGrid
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main() {
    val s = SolutionD()
//    s.countServers(3, "[[1,3],[2,6],[1,5]]".toGrid(), 5, intArrayOf(10, 11)).print()
    // [0,1]
    s.countServers(3, "[[2,4],[2,1],[1,2],[3,1]]".toGrid(), 2, intArrayOf(3, 4)).print()
}

class SolutionD {
    fun countServers(n: Int, logs: Array<IntArray>, x: Int, queries: IntArray): IntArray {

        val ans = HashMap<Int, Int>()
        val l = ArrayList<Triple<Int, Int, Int>>()
        logs.forEach {
            // time server insert
            l.add(Triple(it[1], it[0], 1))
        }
        queries.forEach {
            l.add(Triple(it, -1, -1))
        }
        // 先插入 后查询
        l.sortWith(compareBy({ it.first }, { -it.third }))

        val cur = LinkedList<Triple<Int, Int, Int>>()
        val map = HashMap<Int, Int>()
        l.forEach {
            if (it.third == 1) {
                cur.add(it)
//                println("add $it")
                map[it.second] = map.getOrDefault(it.second, 0) + 1
            } else if (it.third == -1) {
                // cur排除时间不满足
                while (cur.isNotEmpty() && cur.first.first < it.first - x) {
//                    println("${cur.first.first} cmp ${it.first - x}")
                    val server = cur.first.second
                    map[server] = map.getOrDefault(server, 0) - 1
                    if (map[server]!! <= 0) {
                        map.remove(server)
                    }
                    cur.removeAt(0)
                }

//                println("put ${it.first} with ${map.keys.size}")
                ans[it.first] = map.keys.size
            }
        }
        return queries.map {
            n - ans[it]!!
        }.toIntArray()
    }
}
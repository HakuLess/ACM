package leetcode.contest.b125

import utils.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.math.sin

fun main() {
    val s = SolutionC()
    s.countPairsOfConnectableServers("[[0,1,1],[1,2,5],[2,3,13],[3,4,9],[4,5,2]]".toGrid(), 1).print()
    s.countPairsOfConnectableServers("[[0,6,3],[6,5,3],[0,3,1],[3,2,7],[3,1,6],[3,4,2]]".toGrid(), 3).print()
}

class SolutionC {
    fun countPairsOfConnectableServers(edges: Array<IntArray>, signalSpeed: Int): IntArray {
        val n = edges.size + 1
        val g = Graph(n)

        // 构建邻接表
        for (edge in edges) {
            val (a, b, weight) = edge
            g.addEdge(a, b, weight)
        }

        val ans = IntArray(n)

        fun bfs(i: Int) {
            if (g.adj[i].size < 2) return

            val seen = HashSet<Int>()
            seen.add(i)

            val tmp = HashMap<Int, ArrayList<Int>>()

            val queue: Queue<Triple<Int, Int, Int>> = LinkedList<Triple<Int, Int, Int>>()
            g.adj[i].forEach {
                // 标记 方向 & 当前 & 距离
                queue.offer(Triple(it, it, g.weight[i]!![it]!!))
                seen.add(it)
                tmp[it] = arrayListOf()
                tmp[it]!!.add(g.weight[i]!![it]!!)

//                println("first: ${i} -> $it with ${g.weight[i]!![it]!!}")
            }
            while (queue.isNotEmpty()) {
                val size = queue.size
                for (i in 0 until size) {
                    val item = queue.poll()
                    g.adj[item.second].forEach { next ->
                        if (next !in seen) {

//                            val nextV = item.second + g.weight[item.second]!![next]!!
//                            println("$i: add $nextV from ${item.third} + ${g.weight[item.second]!![next]!!}")

                            queue.offer(Triple(item.first, next, item.third + g.weight[item.second]!![next]!!))
                            seen.add(next)

                            tmp[item.first]!!.add(item.third + g.weight[item.second]!![next]!!)
                        }
                    }
                }
            }

//            tmp.forEach { t, u ->
//                println("$i:  $t with ${u.joinToString()}")
//            }

            for (a in tmp.keys) {
                for (b in tmp.keys) {
                    if (a == b) continue
//                    println("$i: add $a * $b with ${tmp[a]!!.size} * ${tmp[b]!!.size}")
                    ans[i] += tmp[a]!!.count { it % signalSpeed == 0 } * tmp[b]!!.count { it % signalSpeed == 0 }
                }
            }
            ans[i] /= 2
        }


        for (i in 0 until n) {
            bfs(i)
        }

        return ans
    }
}
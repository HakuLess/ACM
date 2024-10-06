package leetcode.contest.c418

import utils.print
import utils.printInt
import utils.toGraph
import utils.toGrid
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

fun main() {
    val s = SolutionC()
//    s.constructGridLayout(4, "[[0,1],[0,2],[1,3],[2,3]]".toGrid()).print()
//    s.constructGridLayout(5, "[[0,1],[1,3],[2,3],[2,4]]".toGrid()).print()
//    s.constructGridLayout(9, "[[0,1],[0,4],[0,5],[1,7],[2,3],[2,4],[2,5],[3,6],[4,6],[4,7],[6,8],[7,8]]".toGrid())
//        .print()
//
//    s.constructGridLayout(6, "[[0,1],[0,3],[0,4],[1,2],[1,5],[2,4],[3,5]]".toGrid()).print()
//    s.constructGridLayout(6, "[[0,1],[0,3],[1,2],[2,3],[2,4],[3,5],[4,5]]".toGrid()).print()
//    s.constructGridLayout(8, "[[0,1],[0,3],[0,4],[1,5],[1,6],[2,3],[2,4],[3,6],[5,7],[6,7]]".toGrid()).print()
    s.constructGridLayout(
        15,
        "[[0,1],[0,3],[1,13],[1,14],[2,6],[2,10],[3,9],[3,13],[4,10],[4,12],[5,6],[5,7],[5,10],[5,12],[6,8],[7,8],[7,9],[7,13],[8,11],[9,12],[11,13],[11,14]]".toGrid()
    ).print()
}

class SolutionC {
    fun constructGridLayout(n: Int, edges: Array<IntArray>): Array<IntArray> {
        val g = edges.toGraph(n)
        val map = HashMap<Int, Int>()
        val mapV = HashMap<Int, ArrayList<Int>>()
        g.adj.forEachIndexed { index, it ->
            map[it.size] = map.getOrDefault(it.size, 0) + 1
            mapV[it.size] = mapV.getOrDefault(it.size, arrayListOf<Int>())
            mapV[it.size]!!.add(index)
        }

        map.printInt()

//        mapV.forEach { t, u ->
//            println("$t : ${u.joinToString()}")
//        }

        fun tryMake(row: Int, col: Int): Array<IntArray> {
            val kv = HashMap<Pair<Int, Int>, Int>()
            val ans = Array<IntArray>(row) { IntArray(col) }

            if (row == 1) {
                kv[Pair(0, 0)] = mapV[1]!!.first()
            } else {
                kv[Pair(0, 0)] = mapV[2]!!.first()
            }
//        println("set ${Pair(0, 0)} with ${kv[Pair(0, 0)]}")

            val queue: Queue<Pair<Int, Int>> = PriorityQueue<Pair<Int, Int>>(compareBy({ it.first }, { it.second }))
            queue.offer(Pair(0, 0))
            val seen = HashSet<Pair<Int, Int>>()
            val seenItem = HashSet<Int>()
            seenItem.add(kv[Pair(0, 0)]!!)

            while (queue.isNotEmpty()) {
                val size = queue.size
                for (i in 0 until size) {
                    val (x, y) = queue.poll()

//                println("cur is $x $y")

                    // 第一行
                    if (x == 0) {
                        if (y > 0) {
                            // 左侧Item
                            val left = kv[Pair(x, y - 1)]!!

                            val item = if (y == col - 1) g.adj[left].first { it !in seenItem && g.adj[it].size <= 2 }
                            else g.adj[left].first { it !in seenItem && g.adj[it].size != 4 }
                            seenItem.add(item)
                            kv[Pair(x, y)] = item
//                        println("set ${Pair(x, y)} with $item")
                        }
                    } else {
                        // 仅通过上面确定
                        if (y == 0) {
                            val top = kv[Pair(x - 1, y)]!!
                            val item = g.adj[top].first { it !in seenItem && g.adj[it].size != 4 }
                            seenItem.add(item)
                            kv[Pair(x, y)] = item
//                        println("set ${Pair(x, y)} with $item")
                        } else {
                            // 通过左面 & 上面 共同确定
                            val left = kv[Pair(x, y - 1)]!!
                            val top = kv[Pair(x - 1, y)]!!

//                        println("get top $top left $left")
//                        g.adj[top].joinToString().print()
//                        g.adj[left].joinToString().print()
//                        g.adj[top].intersect(g.adj[left]).joinToString().print()

                            val item = g.adj[top].intersect(g.adj[left]).first { it !in seenItem }
//                        println("get item ========= $item")
                            seenItem.add(item)
                            kv[Pair(x, y)] = item
//                        println("set ${Pair(x, y)} with $item")
                        }
                    }

                    Pair(x, y + 1).let {
                        if (it !in seen && y + 1 < col) {
//                        println("offer $it")
                            queue.offer(it)
                            seen.add(it)
                        }
                    }

                    Pair(x + 1, y).let {
                        if (it !in seen && x + 1 < row) {
//                        println("offer $it")
                            queue.offer(it)
                            seen.add(it)
                        }
                    }
                }
            }


            kv.forEach { k, v ->
//            println("$k with $v")
                ans[k.first][k.second] = v
            }
            return ans
        }

        for (i in 1..n) {
            if (n % i == 0) {
                try {
                    return tryMake(i, n / i)
                } catch (ex: Exception) {

                }
            }
        }

        return emptyArray()
    }
}
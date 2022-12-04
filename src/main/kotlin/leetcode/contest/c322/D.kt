package leetcode.contest.c322

import utils.*
import java.util.*
import kotlin.collections.HashSet

fun main() {
    val s = SolutionD()
    // 4
    s.magnificentSets(6, "[[1,2],[1,4],[1,5],[2,6],[2,3],[4,6]]".toGrid()).print()
//    // -1
    s.magnificentSets(3, "[[1,2],[2,3],[3,1]]".toGrid()).print()

}

// todo -1的判断 BFS找到seen
class SolutionD {
    fun magnificentSets(n: Int, edges: Array<IntArray>): Int {
        val g = Graph(n + 1)
        edges.forEach {
            g.addEdge(it[0], it[1], 1)
        }

        // 判断是否有长度为3的环
//        for (i in 1..n) {
//            for (j in i + 1..n) {
//                for (k in j + 1..n) {
//                    if (g.weight[i]?.get(j) == 1 && g.weight[i]?.get(k) == 1 && g.weight[j]?.get(k) == 1) {
//                        return -1
//                    }
//                }
//            }
//        }


        fun bfs(start: Int, seen: HashSet<Int>): Int {
            if (start in seen) return 0
            val queue: Queue<Int> = LinkedList<Int>()
            queue.offer(start)
            var step = 0
            val set = HashSet<Int>()
            while (queue.isNotEmpty()) {
                val size = queue.size
                step++
                set.clear()
//                println("level $step ${queue.joinToString()}")
                for (i in 0 until size) {
                    val item = queue.poll()
                    if (item in seen) continue
                    seen.add(item)
                    g.adj[item].forEach {
                        if (it !in seen)
                            set.add(it)
                    }
                }
                set.forEach {
                    queue.offer(it)
                }
            }
            return step
        }

        fun getDis(start: Int): HashSet<Int> {
            val seen = HashSet<Int>()
            val queue: Queue<Int> = LinkedList<Int>()
            queue.offer(start)
            var step = 0
            val set = HashSet<Int>()
            while (queue.isNotEmpty()) {
                val size = queue.size
                step++
                set.clear()
//                println("level $step ${queue.joinToString()}")
                for (i in 0 until size) {
                    val item = queue.poll()
                    if (item in seen) continue
                    seen.add(item)
                    g.adj[item].forEach {
                        if (it !in seen)
                            set.add(it)
                    }
                }
                set.forEach {
                    queue.offer(it)
                }
            }
            return seen
        }

        var ans = 0
        val seen = HashSet<Int>()
        g.adj.mapIndexed { index, linkedList -> Pair(index, linkedList) }.drop(1).sortedBy { it.second.size }.forEach {
            if (it.first in seen) return@forEach

//            seen.clear()
//            val all = ArrayList<Int>()
            val all = getDis(it.first)
//            all.add(it.first)
//            all.addAll(it.second)

//            all.addAll(dis[it.first].mapIndexed { index, l -> Pair(index, l) }.filter { it.second <= Int.MAX_VALUE }
//                .map { it.first.toInt() })

//            if (all.size >= 2) {
//                println("${it.first} ${it.second}")
//                println("${it.first} ====== ${all.size} ${all.joinToString()}")
//            }
            var tmp = 0
            all.forEach {
                tmp = maxOf(tmp, bfs(it, seen).also {
                    seen.removeAll(all)
                })
            }
            ans += tmp
            seen.addAll(all)
//            seen.addAll(all)
//            ans += bfs(it.first)
        }
        return if (ans == 0) -1 else ans
    }
}
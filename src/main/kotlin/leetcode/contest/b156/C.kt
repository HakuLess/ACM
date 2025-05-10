package leetcode.contest.b156

import utils.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

fun main() {
    val s = SolutionC()
//    s.maxWeight(3, "[[0,1,1],[1,2,2]]".toGrid(), 2, 4).print()
//    s.maxWeight(2, "[[0,1,3]]".toGrid(), 22, 217).print()
    // 13
    s.maxWeight(4, "[[0,2,3],[1,3,10],[0,3,5],[2,3,10],[0,1,1]]".toGrid(), 2, 217).print()
    // 9
    s.maxWeight(3, "[[0,1,8],[0,2,8],[1,2,9]]".toGrid(), 1, 511).print()
}

class SolutionC {
    fun maxWeight(n: Int, edges: Array<IntArray>, k: Int, t: Int): Int {
        if (k == 0) return 0
        if (edges.isEmpty()) return -1

        val dpPrev = Array(n) { BooleanArray(t) }
        val dpCurr = Array(n) { BooleanArray(t) }

        for ((u, v, w) in edges) {
            if (w < t) {
                dpPrev[v][w] = true
            }
        }

        for (e in 2..k) {
            for (v in 0 until n) dpCurr[v].fill(false)

            for ((u, v, w) in edges) {
                if (w >= t) continue
                for (s in w until t) {
                    if (dpPrev[u][s - w]) {
                        dpCurr[v][s] = true
                    }
                }
            }
            for (v in 0 until n) {
                dpPrev[v] = dpCurr[v].copyOf()
            }
        }

        var ans = -1
        for (v in 0 until n) {
            for (s in 0 until t) {
                if (dpPrev[v][s] && s > ans) {
                    ans = s
                }
            }
        }
        return ans
    }

//    fun maxWeight(n: Int, edges: Array<IntArray>, k: Int, t: Int): Int {
//        if (k == 0) return 0
//        if (edges.isEmpty()) return -1
//
//        val g = edges.toGraphOri(n, 0)
//        var ans = -1
//
//        val seen = HashSet<String>()
//        fun dfs(cur: Int, l: LinkedList<Int>, sum: Int) {
//
////            val key = "$cur,${l.joinToString(";")},${sum}"
//            val key = "$cur,${l.size},${sum}"
//            if (key in seen) return
//            seen.add(key)
//
//            var nextSum = sum
//            while (nextSum >= t || l.size > k) {
//                nextSum -= l.removeFirst()
//            }
//
//            if (l.size == k) {
//                if (nextSum < t) {
//                    ans = maxOf(ans, nextSum)
//                }
//            }
//
//            g.adj[cur].forEach {
////                println("try enter $cur -> $it")
//                val cloneL = l.clone() as LinkedList<Int>
//                cloneL.add(g.weight[cur]!![it]!!)
//                dfs(it, cloneL, nextSum + g.weight[cur]!![it]!!)
//            }
//        }
//
//        val indegree = BooleanArray(n)
//        for (edge in edges) {
//            indegree[edge[1]] = true
//        }
//        for (i in 0 until n) {
//            if (!indegree[i]) {
//                val l = LinkedList<Int>()
//                dfs(i, l, 0)
//            }
//        }
//
//        return ans
//    }
}

//class Solution:
//    def maxWeight(self, n: int, edges: List[List[int]], k: int, t: int) -> int:
//g = [[] for _ in range(n)]
//for u, v, w in edges:
//g[u].append((v, w))
//
//ans = -1
//
//@functools.lru_cache(None)
//def dfs(u, uk, ut):
//nonlocal ans
//if uk == k:
//ans = max(ans, ut)
//return
//for v, w in g[u]:
//if ut+w < t:
//dfs(v, uk+1, ut+w)
//
//for i in range(n):
//dfs(i, 0, 0)
//
//return ans
package leetcode.contest.c456

import utils.UFS
import utils.biMax
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    // 2
    s.maxStability(3, "[[0,1,2,1],[1,2,3,0]]".toGrid(), 1).print()
    // 6
    s.maxStability(3, "[[0,1,4,0],[1,2,3,0],[0,2,1,0]]".toGrid(), 2).print()
    // -1
    s.maxStability(3, "[[0,1,1,1],[1,2,1,1],[2,0,1,1]]".toGrid(), 0).print()
}

class SolutionD {
    fun maxStability(n: Int, edges: Array<IntArray>, k: Int): Int {

        return biMax(1L, Long.MAX_VALUE / 2) { min ->
            val ufs = UFS(n)
            // 计算边数量，> n - 1 则有环
            var cnt = 0

            // 先统计必须边
            val mustEdges = edges.filter { it[3] == 1 }
            for ((u, v, s, must) in mustEdges) {
                if (s < min) return@biMax false
                if (ufs.union(u, v)) {
                    cnt++
                } else {
                    // 必须边若有重复，则证明有环
                    return@biMax false
                }
            }

            // 可选边（记录是否升级）
            val optionalEdges = mutableListOf<Triple<Int, Int, Boolean>>()

            val notMustEdges = edges.filter { it[3] == 0 }
            for ((u, v, s, must) in notMustEdges) {
                if (s >= min) {
                    optionalEdges.add(Triple(u, v, false))
                } else if (s * 2 >= min) {
                    optionalEdges.add(Triple(u, v, true))
                }
            }

            // 尽量补满 n-1 条边，优先填充不升级
            optionalEdges.sortBy { it.third }

            var usedK = 0
            for ((u, v, upgrade) in optionalEdges) {
                // 刚好满足生成树，则可以返回
                if (cnt == n - 1) break
                if (upgrade && usedK >= k) continue
                if (ufs.union(u, v)) {
                    cnt++
                    if (upgrade) {
                        usedK++
                    }
                }
            }

            return@biMax cnt == n - 1
        }.toInt()
    }
}
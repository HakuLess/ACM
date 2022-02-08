package leetcode.normal

import utils.*

fun main() {
    val s = Solution1617()
//    s.countSubgraphsForEachDiameter(4, "[[1,2],[2,3],[2,4]]".toGrid()).print()
//    s.countSubgraphsForEachDiameter(3, "[[1,2],[2,3]]".toGrid()).print()
    s.countSubgraphsForEachDiameter(4, "[[1,3],[1,4],[2,3]]".toGrid()).print()
}

class Solution1617 {
    fun countSubgraphsForEachDiameter(n: Int, edges: Array<IntArray>): IntArray {
        val ans = IntArray(n - 1)
        val bits = Bits(n - 1)
        bits.eachMask { mask ->
            val v = hashSetOf<Int>()
            val indexList = arrayListOf<Int>()
            bits.eachBit(mask) { index, b ->
                if (b) {
                    v.add(edges[index][0] - 1)
                    v.add(edges[index][1] - 1)
                    indexList.add(index)
                }
            }

            val g = Graph(n)
            indexList.forEach {
                g.addEdge(edges[it][0] - 1, edges[it][1] - 1, 1)
            }

            if (v.size == 0) return@eachMask

            val m = g.floyd()
            for (a in v) {
                for (b in v) {
                    if (m[a][b] > n) {
                        return@eachMask
                    }
                }
            }
            var tmp = 0L
            for (i in m.indices) {
                for (j in m[0].indices) {
                    if (m[i][j] < n) {
                        tmp = maxOf(tmp, m[i][j])
                    }
                }
            }
            ans[tmp.toInt() - 1]++
        }
        return ans
    }
}
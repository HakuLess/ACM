package leetcode.normal

import utils.Graph
import utils.TypedUFS
import utils.dijkstra

class Solution1971 {
    fun validPath(n: Int, edges: Array<IntArray>, start: Int, end: Int): Boolean {
        val ufs = TypedUFS<Int>(n)
        edges.forEach {
            ufs.union(it[0], it[1])
        }
        return !ufs.union(start, end)
    }
}
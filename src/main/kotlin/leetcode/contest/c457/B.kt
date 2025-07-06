package leetcode.contest.c457

import utils.UFS
import utils.print
import utils.toGrid
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main() {
    val s = SolutionB()
    // [3,2,3]
    s.processQueries(5, "[[1,2],[2,3],[3,4],[4,5]]".toGrid(), "[[1,3],[2,1],[1,1],[2,2],[1,2]]".toGrid()).print()
    // [1,-1]
//    s.processQueries(3, "[]".toGrid(), "[[1,1],[2,1],[1,1]]".toGrid()).print()
}

class SolutionB {
    fun processQueries(c: Int, connections: Array<IntArray>, queries: Array<IntArray>): IntArray {
        val ufs = UFS(c + 1)
        connections.forEach {
            ufs.union(it[0], it[1])
        }

        val setMap = HashMap<Int, TreeSet<Int>>()
        for (i in 1..c) {
            val key = ufs.find(i)
            setMap[key] = setMap.getOrDefault(key, TreeSet())
            setMap[key]!!.add(i)
        }

        val ans = ArrayList<Int>()
        queries.forEach { (op, x) ->
            val root = ufs.find(x)
            if (op == 1) {
                if (setMap[root]!!.contains(x)) {
                    ans.add(x)
                } else {
                    ans.add(setMap[root]!!.firstOrNull() ?: -1)
                }
            } else {
                setMap[root]!!.remove(x)
            }
        }
        return ans.toIntArray()
    }
}
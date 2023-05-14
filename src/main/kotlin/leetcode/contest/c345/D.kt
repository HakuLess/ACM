package leetcode.contest.c345

import utils.UFS
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    s.countCompleteComponents(6, "[[0,1],[0,2],[1,2],[3,4]]".toGrid()).print()
    s.countCompleteComponents(6, "[[0,1],[0,2],[1,2],[3,4],[3,5]]".toGrid()).print()
}

// 计算完全联通分量
class SolutionD {
    fun countCompleteComponents(n: Int, edges: Array<IntArray>): Int {

        val ufs = UFS(n)
        // 计算点的度
        val e = IntArray(n)
        edges.forEach {
            e[it[0]]++
            e[it[1]]++
            ufs.union(it[0], it[1])
        }

        val map = HashMap<Int, ArrayList<Int>>()
        for (i in 0 until n) {
            val p = ufs.find(i)
            map[p] = map.getOrDefault(p, arrayListOf())
            map[p]!!.add(i)
        }

        var ans = 0
        map.keys.forEach {
            if (map[it]!!.all { e[it] + 1 == ufs.sz[ufs.find(it)] }) {
                ans++
            }
        }
        return ans
    }
}
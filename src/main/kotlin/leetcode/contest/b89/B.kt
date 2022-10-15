package leetcode.contest.b89

import utils.SegmentTree
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionB()
    s.productQueries(15, "[[0,1],[2,2],[0,3]]".toGrid()).print()
    s.productQueries(2, "[[0,0]]".toGrid()).print()
}

class SolutionB {
    fun productQueries(n: Int, queries: Array<IntArray>): IntArray {
        val ans = IntArray(queries.size)
//        n.toString(2).print()
        val tmp = ArrayList<Long>()
        var cur = 1L
        n.toString(2).reversed().forEach {
            if (it == '1') {
                tmp.add(cur)
            }
            cur *= 2
        }
//        tmp.joinToString().print()
        val mod = 1000000007L
        val root = SegmentTree<Long>(0, tmp.size * 2, 0) { a, b -> (a * b) % mod }
        for (i in tmp.indices) {
            root.update(root, i, tmp[i])
        }
        for (i in queries.indices) {
            ans[i] = (root.query(root, queries[i][0], queries[i][1]) % mod).toInt()
        }
        return ans
    }
}
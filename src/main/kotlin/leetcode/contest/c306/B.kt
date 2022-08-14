package leetcode.contest.c306

class SolutionB {
    fun edgeScore(edges: IntArray): Int {
        val ans = LongArray(edges.size)
        var res = -1
        var cur = 0L
        for (i in edges.indices) {
            ans[edges[i]] += i.toLong()
        }
        for (i in ans.indices) {
            if (ans[i] > cur) {
                res = i
                cur = ans[i]
            }
        }
        return res
    }
}
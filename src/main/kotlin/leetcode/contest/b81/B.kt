package leetcode.contest.b81

import utils.UFS

class SolutionB {
    fun countPairs(n: Int, edges: Array<IntArray>): Long {
        val ufs = UFS(n)
        edges.forEach {
            ufs.union(it[0], it[1])
        }
        val map = HashMap<Int, Int>()
        for (i in 0 until n) {
            val key = ufs.find(i)
            map[key] = map.getOrDefault(key, 0) + 1
        }
        var ans = 0L
        map.forEach { key, value ->
            ans += 1L * value * (n - value)
        }
        return ans / 2
    }
}
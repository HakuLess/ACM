package leetcode.contest.b93

class SolutionB {
    fun maxStarSum(vals: IntArray, edges: Array<IntArray>, k: Int): Int {
        val map = HashMap<Int, ArrayList<Int>>()
        for (i in vals.indices) {
            map[i] = map.getOrDefault(i, arrayListOf())
        }
        edges.forEach {
            map[it[0]]!!.add(vals[it[1]])
            map[it[1]]!!.add(vals[it[0]])
        }
        var ans = Int.MIN_VALUE
        map.forEach { t, u ->
            u.sortDescending()
            ans = maxOf(ans, u.take(k).filter { it >= 0 }.sum() + vals[t])
        }
        return ans
    }
}
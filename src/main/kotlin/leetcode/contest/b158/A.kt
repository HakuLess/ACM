package leetcode.contest.b158

class SolutionA {
    fun maxSumDistinctTriplet(x: IntArray, y: IntArray): Int {
        val map = HashMap<Int, Int>()
        for (i in x.indices) {
            val key = x[i]
            val value = y[i]
            map[key] = maxOf(map.getOrDefault(key, 0), value)
        }
        if (map.size < 3) return -1
        return map.values.sortedDescending().take(3).sum()
    }
}
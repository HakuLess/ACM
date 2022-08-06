package leetcode.contest.b84

class SolutionA {
    fun mergeSimilarItems(items1: Array<IntArray>, items2: Array<IntArray>): List<List<Int>> {
        val map = HashMap<Int, Int>()
        items1.forEach {
            map[it[0]] = map.getOrDefault(it[0], 0) + it[1]
        }
        items2.forEach {
            map[it[0]] = map.getOrDefault(it[0], 0) + it[1]
        }
        val ans = ArrayList<ArrayList<Int>>()
        map.forEach { t, u ->
            ans.add(arrayListOf(t, u))
        }
        ans.sortBy { it[0] }
        return ans
    }
}
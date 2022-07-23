package leetcode.contest.c302

class SolutionC {
    fun smallestTrimmedNumbers(nums: Array<String>, queries: Array<IntArray>): IntArray {
        return queries.map {
            val k = it[0]
            val trim = it[1]
            // value index
            val arr = ArrayList<Pair<String, Int>>()
            nums.forEachIndexed { index, s ->
                val v = s.takeLast(trim)
                arr.add(Pair(v, index))
            }
            arr.sortWith(compareBy({ it.first }, { it.second }))
            arr[k - 1].second
        }.toIntArray()
    }
}
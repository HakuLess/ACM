package leetcode.contest.b65

class SolutionC {
    fun maximumBeauty(items: Array<IntArray>, queries: IntArray): IntArray {
        items.sortBy { it[0] }
        var index = 0
        val map = HashMap<Int, Int>()
        var max = 0
        queries.sorted().forEach {
            while (index in items.indices && items[index][0] <= it) {
                max = maxOf(max, items[index][1])
                index++
            }
            map[it] = max
        }
        return queries.map { map[it]!! }.toIntArray()
    }
}
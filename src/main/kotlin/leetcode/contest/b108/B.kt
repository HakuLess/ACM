package leetcode.contest.b108

fun main() {
    val s = SolutionB()
    s.relocateMarbles(intArrayOf(3, 4), intArrayOf(4, 3, 1, 2, 2, 3, 2, 4, 1), intArrayOf(3, 1, 2, 2, 3, 2, 4, 1, 1))
}

class SolutionB {
    fun relocateMarbles(nums: IntArray, moveFrom: IntArray, moveTo: IntArray): List<Int> {
        val map = HashMap<Int, Int>()
        nums.forEach {
            map[it] = map.getOrDefault(it, 0) + 1
        }
        for (i in moveFrom.indices) {
            val from = moveFrom[i]
            val to = moveTo[i]
            if (from == to) continue
            map[to] = map.getOrDefault(to, 0) + map.getOrDefault(from, 0)
            map.remove(from)
        }
        return map.keys.sorted()
    }
}
package leetcode.contest.c258

class Solution5868 {
    fun interchangeableRectangles(rectangles: Array<IntArray>): Long {
        val map = HashMap<Double, Long>()
        rectangles.forEach {
            val cur = it[0].toDouble() / it[1].toDouble()
            map[cur] = map.getOrDefault(cur, 0) + 1L
        }
        var ans = 0L
        map.forEach { t, u ->
            ans += u * (u - 1)
        }
        return ans / 2
    }
}
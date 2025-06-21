package leetcode.contest.b159

class SolutionB {
    fun maxArea(coords: Array<IntArray>): Long {
        val yMap = HashMap<Long, Pair<Long, Long>>()
        val xMap = HashMap<Long, Pair<Long, Long>>()

        var minX = Long.MAX_VALUE
        var maxX = Long.MIN_VALUE
        var minY = Long.MAX_VALUE
        var maxY = Long.MIN_VALUE

        for ((a, b) in coords) {
            val x = a.toLong()
            val y = b.toLong()
            // 更新x、y 对应另一维度的最大值、最小值
            yMap[y] = yMap.getOrDefault(y, Pair(x, x)).let { Pair(minOf(it.first, x), maxOf(it.second, x)) }
            xMap[x] = xMap.getOrDefault(x, Pair(y, y)).let { Pair(minOf(it.first, y), maxOf(it.second, y)) }

            minX = minOf(minX, x)
            maxX = maxOf(maxX, x)
            minY = minOf(minY, y)
            maxY = maxOf(maxY, y)
        }

        var ans = 0L

        for ((y, pair) in yMap) {
            val width = pair.second - pair.first
            val height = maxOf(y - minY, maxY - y)
            ans = maxOf(ans, width * height)
        }

        for ((x, pair) in xMap) {
            val height = pair.second - pair.first
            val width = maxOf(x - minX, maxX - x)
            ans = maxOf(ans, width * height)
        }

        return if (ans == 0L) -1 else ans
    }
}
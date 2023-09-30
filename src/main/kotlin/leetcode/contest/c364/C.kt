package leetcode.contest.c364

import utils.print

fun main() {
    val s = SolutionC()
    s.maximumSumOfHeights(listOf(3, 2, 5, 5, 2, 3)).print()
}

class SolutionC {
    fun maximumSumOfHeights(maxHeights: List<Int>): Long {
        var ans = 0L
        val sorted = maxHeights.mapIndexed { index, i -> Pair(index, i) }.sortedByDescending { it.second }

        sorted.subList(0, minOf(maxHeights.size, 100)).forEach {
            val i = it.first
            var tmp = 0L + maxHeights[i]
            var left = maxHeights[i]
            for (j in i - 1 downTo 0) {
                left = minOf(left, maxHeights[j])
                tmp += left
            }

            var right = maxHeights[i]
            for (j in i + 1 until maxHeights.size) {
                right = minOf(right, maxHeights[j])
                tmp += right
            }
            ans = maxOf(ans, tmp)
        }
        return ans
    }
}
package leetcode.contest.c438

import utils.print
import utils.toGrid
import kotlin.math.abs
import kotlin.random.Random

fun main() {
    val s = SolutionD()
    // 2
    s.maxDistance(4, "[[4,4],[3,4],[2,0],[4,3],[4,0]]".toGrid(), 4).print()
}

class SolutionD {
    fun maxDistance(side: Int, points: Array<IntArray>, k: Int): Int {

        var left = 0
        var right = 2 * side
        var answer = 0

        val pointsLong = points.map { it.map { it.toLong() }.toLongArray() }

        // 0L 放置溢出导致错误
        val comparators = listOf(
            compareBy<LongArray> { 0L + it[0] },
            compareByDescending<LongArray> { 0L + it[0] },
            compareBy<LongArray> { 0L + it[1] },
            compareByDescending<LongArray> { 0L + it[1] },
            compareBy<LongArray> { 0L + it[0] + it[1] },
            compareByDescending<LongArray> { 0L + it[0] + it[1] },
            compareBy<LongArray> { 0L + -it[0] + it[1] },
            compareByDescending<LongArray> { 0L + -it[0] + it[1] },
            compareBy<LongArray> { 0L + it[0] - it[1] },
            compareByDescending<LongArray> { 0L + it[0] - it[1] }
        )

        while (left <= right) {
            val mid = (left + right) ushr 1
            var found = false

            for (comparator in comparators) {
                val sortedPoints = ArrayList<LongArray>()
                sortedPoints.addAll(pointsLong.sortedWith(comparator))
                repeat(10) {
                    if (found) return@repeat
                    val selected = mutableListOf<LongArray>()
                    for (point in sortedPoints) {
                        var valid = true
                        for (p in selected) {
                            val distance = abs(point[0] - p[0]) + abs(point[1] - p[1])
                            if (distance < mid) {
                                valid = false
                                break
                            }
                        }
                        if (valid) {
                            selected.add(point)
                            if (selected.size >= k) {
                                found = true
                                break
                            }
                        }
                    }

                    sortedPoints.add(0, sortedPoints.removeLast())
                }
                if (found) break
            }

            if (found) {
                answer = mid
                left = mid + 1
            } else {
                right = mid - 1
            }
        }

        return answer

    }
}
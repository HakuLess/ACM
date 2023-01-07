package leetcode.contest.b95

import utils.biMax
import utils.preSumArray
import utils.print

fun main() {
    val s = SolutionD()
    // 5
    s.maxPower(intArrayOf(1, 2, 4, 5, 0), 1, 2).print()
    // 4
    s.maxPower(intArrayOf(4, 4, 4, 4), 0, 3).print()

    // 138
    s.maxPower(intArrayOf(57, 70, 35, 30, 29, 13, 17, 88, 89, 49), 1, 90).print()
}

class SolutionD {
    fun maxPower(stations: IntArray, r: Int, k: Int): Long {
        val preSum = stations.preSumArray(true)

        return biMax(0L, Long.MAX_VALUE) {
            var sum = 0L
            val st = stations.map { it.toLong() }.toLongArray()
            var offset = 0L
            val off = LongArray(st.size)
            for (i in st.indices) {

                if (i - r - 1 in st.indices) {
                    offset -= off[i - r - 1]
                }

                val curSum = preSum[minOf(i + r + 1, preSum.lastIndex)] - preSum[maxOf(i - r, 0)]

                // 当前不足
                if (curSum + offset < it) {
                    val add = it - curSum - offset
                    sum += add
                    if (sum > k) return@biMax false
                    offset += add
                    off[minOf(i + r, off.lastIndex)] = add
                }
            }
            sum <= k
        }
    }
}
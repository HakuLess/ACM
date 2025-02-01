package leetcode.contest.b149

import utils.print

fun main() {
    val s = SolutionB()
    s.maxFreeTime(5, 1, intArrayOf(1, 3), intArrayOf(2, 5)).print()
    s.maxFreeTime(10, 1, intArrayOf(0, 2, 9), intArrayOf(1, 4, 10)).print()
}

class SolutionB {
    fun maxFreeTime(eventTime: Int, k: Int, startTime: IntArray, endTime: IntArray): Int {
        val n = startTime.size
        val gaps = ArrayList<Int>()
        gaps.add(startTime[0])
        for (i in 1 until n) {
            gaps.add(startTime[i] - endTime[i - 1])
        }
        gaps.add(eventTime - endTime[n - 1])

//        gaps.joinToString().print()

        var tmp = 0
        var ans = tmp
        for (i in gaps.indices) {
//            println("$i: $tmp  + ${gaps[i]} - ${gaps.getOrNull(i - k - 1)}")
            tmp += gaps[i]
            if (i > k) {
                tmp -= gaps[i - k - 1]
            }
            ans = maxOf(ans, tmp)
        }

        return ans
    }
}
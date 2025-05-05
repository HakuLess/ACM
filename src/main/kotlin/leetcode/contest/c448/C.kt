package leetcode.contest.c448

import utils.print

fun main() {
    val s = SolutionC()
    s.minTravelTime(10, 4, 1, intArrayOf(0, 3, 8, 10), intArrayOf(5, 8, 3, 6)).print()
}

// TODO Not Finished
class SolutionC {
    fun minTravelTime(l: Int, n: Int, k: Int, position: IntArray, time: IntArray): Int {

        fun dfs(pos: Int, lstDis: Int, lstTime: Int, leftK: Int): Int {
            val key = "$pos $lstDis $lstTime $leftK"

            if (pos >= n) {
                if (leftK > 0) return Int.MAX_VALUE / 4
                if (leftK == 0) return 0
            }

            val distance = if (pos == 0) {
                position[pos]
            } else {
                position[pos] - position[pos - 1]
            }

            // 不合并下一个
            val ans0 = (lstDis + distance) * (lstTime + time[pos - 1]) + dfs(pos + 1, 0, 0, leftK)


            // 合并下一个，前提K还有剩余 && pos不是最后一位
            var ans1 = Int.MAX_VALUE
            if (leftK > 0 && pos != n - 1) {
                ans1 = dfs(pos + 1, lstDis + distance, lstTime + time[pos], leftK - 1)
            }
            return minOf(ans0, ans1).also {
                println("$key with $it")
            }
        }

        return dfs(1, 0, 0, k)

    }
}
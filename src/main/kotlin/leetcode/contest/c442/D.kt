package leetcode.contest.c442

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    s.minOperations("[[1,2],[2,4]]".toGrid()).print()
}

class SolutionD {
    fun minOperations(queries: Array<IntArray>): Long {
        val maxK = 16
        val p = LongArray(maxK)
        p[0] = 1L
        for (i in 1 until maxK) {
            p[i] = p[i - 1] * 4
        }

        // index对应需要执行次数，内部值范围在 p[i] ~ (p[i + 1] - 1)
//        p.print()

        var ans = 0L
        for (query in queries) {
            val l = query[0].toLong()
            val r = query[1].toLong()

            var totalSteps = 0L
            for (k in 1 until maxK) {
                val lk = p[k - 1]
                val rk = p[k] - 1
                val low = maxOf(l, lk)
                val high = minOf(r, rk)

                // 单范围内计算
                if (low <= high) {
                    val cntK = high - low + 1
                    totalSteps += cntK * k
                }
                if (rk >= r) break
            }

            // 向上取整
            ans += (totalSteps + 1) / 2
        }
        return ans
    }
}
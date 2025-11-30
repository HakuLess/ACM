package leetcode.contest.c478

import utils.print
import utils.toGrid
import kotlin.math.abs

fun main() {
    val s = SolutionD()
    // 1 2
    s.minOperations(intArrayOf(1, 4, 7), 3, "[[0,1],[0,2]]".toGrid()).print()

    // -1 0 1
    s.minOperations(intArrayOf(1, 2, 4), 2, "[[0,2],[0,0],[1,2]]".toGrid()).print()
}

// Not Finished TLE
class SolutionD {
    fun minOperations(nums: IntArray, k: Int, queries: Array<IntArray>): LongArray {

        val n = nums.size
        val mod = nums.map { it % k }
        val arr = nums.map { it / k }

        // 按值排序时需要保留原索引以支持区间查询
        val idx = (0 until n).sortedBy { arr[it] }
        val sortedArr = idx.map { arr[it] }
        val prefix = LongArray(n + 1)
        for (i in 0 until n) prefix[i + 1] = prefix[i] + sortedArr[i]

        // 每个位置在排序后的位置
        val pos = IntArray(n)
        for (i in 0 until n) pos[idx[i]] = i

        fun cal(l: Int, r: Int): Long {
            val p = pos.slice(l..r).sorted()
            val m = p.size
            val mid = p[m / 2]
            val median = sortedArr[mid]

            var cost = 0L
            for (pi in p) {
                cost += abs(sortedArr[pi] - median)
            }
            return cost
        }

        val ans = LongArray(queries.size)

        for (qi in queries.indices) {
            val (l, r) = queries[qi]

            // 条件检查：mod 必须全相同
            val base = mod[l]
            var ok = true
            for (i in l..r) {
                if (mod[i] != base) {
                    ok = false; break
                }
            }

//            println("search $l..$r ok $ok  ${mod[l]}")
            if (!ok) {
                ans[qi] = -1
                continue
            }

            // 计算操作次数
            ans[qi] = cal(l, r)
        }

        return ans
    }
}
package leetcode.contest.c476

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    s.countStableSubarrays(intArrayOf(3, 1, 2), "[[0,1],[1,2],[0,2]]".toGrid()).print()
    s.countStableSubarrays(intArrayOf(2, 2), "[[0,1],[0,0]]".toGrid()).print()
}

/**
 * Not Finished TLE
 * */
class SolutionD {
    fun countStableSubarrays(nums: IntArray, queries: Array<IntArray>): LongArray {
        val n = nums.size

        // 1. 预处理 nxt[i] 获取到不逆序可扩展最右侧Index
        val nxt = IntArray(n)
        nxt[n - 1] = n - 1
        for (i in n - 2 downTo 0) {
            nxt[i] = if (nums[i] <= nums[i + 1]) nxt[i + 1] else i
        }

        // 2. 将 i 按 nxt[i] 排序
        val order = (0 until n).sortedBy { nxt[it] }

        // 3. 查询按 r 升序
        val qs = queries.indices.map {
            Query(queries[it][0], queries[it][1], it)
        }.sortedBy { it.r }

        val fen = Fenwick(n)

        val ans = LongArray(queries.size)
        var ptr = 0

        // 循环处理查询
        for (q in qs) {
            val r = q.r
            val l = q.l

            // 将所有 nxt[i] <= r 的 i 放入 Fenwick1
            while (ptr < n && nxt[order[ptr]] <= r) {
                val i = order[ptr]
                fen.add(i, nxt[i] - i + 1)
                ptr++
            }

            val full = fen.range(l, r)

            // nxt[i] > r 时贡献为 (r - i + 1)
            fen.range(l, r).let {
                // 所以额外统计 nxt[i] <= r 的数量
                var cnt = 0
                for (i in l..r) {
                    if (nxt[i] <= r) {
                        cnt++
                    }
                }
                (r - l + 1) - cnt
            }

            var sumLarge = 0L
            for (i in l..r) if (nxt[i] > r) sumLarge += (r - i + 1)

            ans[q.idx] = full + sumLarge
        }

        return ans
    }
}

data class Query(val l: Int, val r: Int, val idx: Int)

class Fenwick(val n: Int) {
    val tree = LongArray(n + 1)

    fun add(i0: Int, v: Int) {
        var i = i0 + 1
        while (i <= n) {
            tree[i] = tree[i] + v
            i += i and -i
        }
    }

    fun sum(i0: Int): Long {
        var i = i0 + 1
        var s = 0L
        while (i > 0) {
            s += tree[i]
            i -= i and -i
        }
        return s
    }

    fun range(l: Int, r: Int) =
        if (l > r) 0 else sum(r) - sum(l - 1)
}
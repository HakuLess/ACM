package leetcode.contest.c446

import utils.SegmentTree
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    s.resultArray(intArrayOf(1, 2, 3, 4, 5), 3, "[[2,2,0,2],[3,3,3,0],[0,1,0,1]]".toGrid()).print()
}

class SolutionD {
    fun resultArray(nums: IntArray, k: Int, queries: Array<IntArray>): IntArray {

        val n = nums.size

        // 节点 区间%k值 & 前缀总计数
        val root = SegmentTree<Pair<Int, IntArray>>(
            start = 0,
            end = n,
            merge = { a, b ->
                // 区间整体余数计算
                val p = (a.first * b.first) % k
                val c = IntArray(k)
                // 左侧计数先保留
                for (r in 0 until k) {
                    c[r] += a.second[r]
                }
                // 右侧计数前缀 余数与左侧相乘 计算整体前缀计数
                for (r in 0 until k) {
                    val ways = b.second[r]
                    val mod = (a.first * r) % k
                    c[mod] += ways
                }
                Pair(p, c)
            }
        )

        for (i in nums.indices) {
            val v = nums[i] % k
            val mod = IntArray(k)
            mod[v] = 1
            root.update(root, i, Pair(v, mod))
        }

        val result = IntArray(queries.size)
        for ((i, q) in queries.withIndex()) {
            val (idx, value, start, x) = q

            val v = value % k
            val mod = IntArray(k)
            mod[v] = 1
            root.update(root, idx, Pair(v, mod))

            val ans = root.query(root, start, n - 1)
            result[i] = ans.second[x]
        }

        return result
    }
}
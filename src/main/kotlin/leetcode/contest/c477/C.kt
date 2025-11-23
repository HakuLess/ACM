package leetcode.contest.c477

import utils.SegmentTree
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionC()
    s.sumAndMultiply("10203004", "[[0,7],[1,3],[4,6]]".toGrid()).print()
    s.sumAndMultiply("1000", "[[0,3],[1,1]]".toGrid()).print()
    s.sumAndMultiply("9876543210", "[[0,9]]".toGrid()).print()
}

class SolutionC {
    fun sumAndMultiply(s: String, queries: Array<IntArray>): IntArray {

        val mod = 1_000_000_007L
        lateinit var pow10: LongArray

        data class Node(
            // 非零数字个数
            val len: Int,
            // 非零数字总和
            val sum: Long,
            // 拼接后的数 % mod
            val valMod: Long
        )

        // 合并两个节点
        fun mergeNode(a: Node, b: Node): Node {
            if (a.len == 0) return b
            if (b.len == 0) return a
            val totalLen = a.len + b.len
            val totalSum = a.sum + b.sum
            val totalVal = (a.valMod * pow10[b.len] % mod + b.valMod) % mod
            return Node(totalLen, totalSum, totalVal)
        }


        val n = s.length

        // 预处理10幂次
        pow10 = LongArray(n + 5)
        pow10[0] = 1
        for (i in 1..n) pow10[i] = pow10[i - 1] * 10 % mod

        // 构建 Node 数组
        val arr = Array<Node>(n) { i ->
            val d = s[i] - '0'
            if (d == 0) Node(0, 0, 0)
            else Node(1, d.toLong(), d.toLong())
        }

        // 使用你的模板 SegmentTree 构建
        val st = SegmentTree(
            start = 0,
            end = n - 1,
            value = null,
            merge = ::mergeNode
        )
        val root = st.build(arr)!!     // 树根

        // 处理 queries
        val ans = IntArray(queries.size)
        for (i in queries.indices) {
            val (l, r) = queries[i]
            val node = st.query(root, l, r)
            ans[i] = ((node.valMod * node.sum) % mod).toInt()
        }

        return ans
    }
}
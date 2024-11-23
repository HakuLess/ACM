package utils.segment


class SegmentTreeGPTMinus(private val n: Int) {
    private val tree: IntArray = IntArray(4 * n)
    private val lazy: IntArray = IntArray(4 * n)

    // 更新区间 [l, r] 加上 val
    fun rangeAdd(l: Int, r: Int, value: Int) {
        rangeAdd(0, 0, n - 1, l, r, value)
    }

    private fun rangeAdd(node: Int, nodeLeft: Int, nodeRight: Int, l: Int, r: Int, value: Int) {
        // 如果有延迟标记，先更新当前节点
        if (lazy[node] != 0) {
            tree[node] += lazy[node]
            if (nodeLeft != nodeRight) {
                lazy[2 * node + 1] += lazy[node]
                lazy[2 * node + 2] += lazy[node]
            }
            lazy[node] = 0
        }

        // 完全不在范围内，返回
        if (r < nodeLeft || nodeRight < l) return

        // 如果当前区间完全在查询范围内
        if (l <= nodeLeft && nodeRight <= r) {
            tree[node] += value
            if (nodeLeft != nodeRight) {
                lazy[2 * node + 1] += value
                lazy[2 * node + 2] += value
            }
            return
        }

        // 部分在查询范围内，继续分割
        val mid = (nodeLeft + nodeRight) / 2
        rangeAdd(2 * node + 1, nodeLeft, mid, l, r, value)
        rangeAdd(2 * node + 2, mid + 1, nodeRight, l, r, value)

        tree[node] = minOf(tree[2 * node + 1], tree[2 * node + 2])
    }

    // 查询区间 [l, r] 的最小值
    fun query(l: Int, r: Int): Int {
        return query(0, 0, n - 1, l, r)
    }

    private fun query(node: Int, nodeLeft: Int, nodeRight: Int, l: Int, r: Int): Int {
        // 如果有延迟标记，先更新当前节点
        if (lazy[node] != 0) {
            tree[node] += lazy[node]
            if (nodeLeft != nodeRight) {
                lazy[2 * node + 1] += lazy[node]
                lazy[2 * node + 2] += lazy[node]
            }
            lazy[node] = 0
        }

        // 完全不在范围内，返回一个非常大的值
        if (r < nodeLeft || nodeRight < l) return Int.MAX_VALUE

        // 完全在查询范围内
        if (l <= nodeLeft && nodeRight <= r) {
            return tree[node]
        }

        // 部分在查询范围内，继续分割
        val mid = (nodeLeft + nodeRight) / 2
        val leftQuery = query(2 * node + 1, nodeLeft, mid, l, r)
        val rightQuery = query(2 * node + 2, mid + 1, nodeRight, l, r)

        return minOf(leftQuery, rightQuery)
    }
}
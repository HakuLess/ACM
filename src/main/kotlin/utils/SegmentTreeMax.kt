package utils

class SegmentTreeMax {
    private val tree = mutableMapOf<Int, Long>() // 存储每个节点的值
    private val lazy = mutableMapOf<Int, Long>() // 存储懒惰更新的值

    // 区域更新
    fun updateRange(start: Int, end: Int, value: Long) {
        updateRangeUtil(0, 0, Int.MAX_VALUE, start, end, value)
    }

    // 区域查询
    fun queryMax(start: Int, end: Int): Long {
        return queryMaxUtil(0, 0, Int.MAX_VALUE, start, end)
    }

    private fun updateRangeUtil(node: Int, nodeStart: Int, nodeEnd: Int, rangeStart: Int, rangeEnd: Int, value: Long) {
        // 如果有未处理的懒惰更新
        if (lazy[node] != null) {
            tree[node] = (tree[node] ?: 0) + lazy[node]!! * (nodeEnd - nodeStart + 1)
            if (nodeStart != nodeEnd) {
                lazy[node * 2 + 1] = (lazy[node * 2 + 1] ?: 0) + lazy[node]!!
                lazy[node * 2 + 2] = (lazy[node * 2 + 2] ?: 0) + lazy[node]!!
            }
            lazy.remove(node)
        }

        // 完全不在范围内
        if (nodeStart > nodeEnd || nodeStart > rangeEnd || nodeEnd < rangeStart) {
            return
        }

        // 完全覆盖
        if (nodeStart >= rangeStart && nodeEnd <= rangeEnd) {
            tree[node] = (tree[node] ?: 0) + value * (nodeEnd - nodeStart + 1)
            if (nodeStart != nodeEnd) {
                lazy[node * 2 + 1] = (lazy[node * 2 + 1] ?: 0) + value
                lazy[node * 2 + 2] = (lazy[node * 2 + 2] ?: 0) + value
            }
            return
        }

        // 部分覆盖
        val mid = (nodeStart + nodeEnd) / 2
        updateRangeUtil(node * 2 + 1, nodeStart, mid, rangeStart, rangeEnd, value)
        updateRangeUtil(node * 2 + 2, mid + 1, nodeEnd, rangeStart, rangeEnd, value)

        // 更新当前节点的最大值
        tree[node] = maxOf(
            tree[node * 2 + 1] ?: Long.MIN_VALUE,
            tree[node * 2 + 2] ?: Long.MIN_VALUE
        )
    }

    private fun queryMaxUtil(node: Int, nodeStart: Int, nodeEnd: Int, rangeStart: Int, rangeEnd: Int): Long {
        // 如果有未处理的懒惰更新
        if (lazy[node] != null) {
            tree[node] = (tree[node] ?: 0) + lazy[node]!! * (nodeEnd - nodeStart + 1)
            if (nodeStart != nodeEnd) {
                lazy[node * 2 + 1] = (lazy[node * 2 + 1] ?: 0) + lazy[node]!!
                lazy[node * 2 + 2] = (lazy[node * 2 + 2] ?: 0) + lazy[node]!!
            }
            lazy.remove(node)
        }

        // 完全不在范围内
        if (nodeStart > nodeEnd || nodeStart > rangeEnd || nodeEnd < rangeStart) {
            return Long.MIN_VALUE // 返回负无穷大，表示不在范围内
        }

        // 完全在范围内
        if (nodeStart >= rangeStart && nodeEnd <= rangeEnd) {
            return tree[node] ?: Long.MIN_VALUE
        }

        // 部分覆盖
        val mid = (nodeStart + nodeEnd) / 2
        val leftMax = queryMaxUtil(node * 2 + 1, nodeStart, mid, rangeStart, rangeEnd)
        val rightMax = queryMaxUtil(node * 2 + 2, mid + 1, nodeEnd, rangeStart, rangeEnd)

        return maxOf(leftMax, rightMax)
    }
}

// 示例用法
fun main() {
    val segmentTree = SegmentTreeMax()

    // 示例：对区间 [10^8 ,10^9 ] 增加5
    segmentTree.updateRange(100000000, 1000000000.toInt(), 5)

    // 查询区间 [10^8 ,10^9 ] 的最大值
    println(segmentTree.queryMax(100000000.toInt(), 1000000000.toInt())) // 输出结果，应该是5

    // 示例：对区间 [5*10^8 ,7*10^8 ] 增加3
    segmentTree.updateRange(500000000.toInt(), 700000000.toInt(), 3)

    // 查询区间 [5*10^8 ,7*10^8 ] 的最大值
    println(segmentTree.queryMax(500000000.toInt(), 700000000.toInt())) // 输出结果，应该是8
}
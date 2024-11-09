class SegmentTreeNode(var start: Long, var end: Long, var value: Long = 0L) {
    var left: SegmentTreeNode? = null
    var right: SegmentTreeNode? = null
    var lazy: Long = 0L
}

class DynamicSegmentTree(private val rangeStart: Long, private val rangeEnd: Long) {
    private var root: SegmentTreeNode? = SegmentTreeNode(rangeStart, rangeEnd)

    private fun pushDown(node: SegmentTreeNode) {
        if (node.lazy != 0L) {
            node.value += node.lazy
            if (node.start != node.end) {
                if (node.left == null) {
                    val mid = (node.start + node.end) / 2
                    node.left = SegmentTreeNode(node.start, mid)
                    node.right = SegmentTreeNode(mid + 1, node.end)
                }
                node.left!!.lazy += node.lazy
                node.right!!.lazy += node.lazy
            }
            node.lazy = 0L
        }
    }

    private fun updateRange(node: SegmentTreeNode?, start: Long, end: Long, value: Long) {
        if (node == null || start > node.end || end < node.start) return  // No overlap

        if (start <= node.start && end >= node.end) {  // Total overlap
            node.lazy += value
            pushDown(node)
            return
        }

        // Partial overlap
        pushDown(node)
        val mid = (node.start + node.end) / 2
        if (node.left == null) {
            node.left = SegmentTreeNode(node.start, mid)
            node.right = SegmentTreeNode(mid + 1, node.end)
        }
        updateRange(node.left, start, end, value)
        updateRange(node.right, start, end, value)
        node.value = maxOf(node.left!!.value, node.right!!.value)
    }

    fun updateRange(start: Long, end: Long, value: Long) {
        updateRange(root, start, end, value)
    }

    private fun queryMax(node: SegmentTreeNode?, start: Long, end: Long): Long {
        if (node == null || start > node.end || end < node.start) return Long.MIN_VALUE  // No overlap

        pushDown(node)
        if (start <= node.start && end >= node.end) return node.value  // Total overlap

        // Partial overlap
        val leftMax = queryMax(node.left, start, end)
        val rightMax = queryMax(node.right, start, end)
        return maxOf(leftMax, rightMax)
    }

    fun queryMax(start: Long, end: Long): Long {
        return queryMax(root, start, end)
    }
}
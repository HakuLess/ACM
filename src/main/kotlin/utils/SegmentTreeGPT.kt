package utils

// 支持乘积与加法的线段树
class SegmentTreeGPT(private val arr: LongArray) {
    private val treeSum = LongArray(4 * arr.size)
    private val treeMul = LongArray(4 * arr.size)
    private val treeAdd = LongArray(4 * arr.size)

    init {
        buildTree(1, 0, arr.size - 1)
    }

    private fun buildTree(node: Int, start: Int, end: Int) {
        if (start == end) {
            treeSum[node] = arr[start]
            return
        }
        val mid = (start + end) / 2
        buildTree(leftChild(node), start, mid)
        buildTree(rightChild(node), mid + 1, end)
        updateNode(node, leftChild(node), rightChild(node))
    }

    fun querySum(left: Int, right: Int): Long {
        return queryHelperSum(1, 0, arr.size - 1, left, right)
    }

    private fun queryHelperSum(node: Int, start: Int, end: Int, left: Int, right: Int): Long {
        if (left > end || right < start) {
            return 0
        }
        if (start >= left && end <= right) {
            return treeSum[node]
        }
        pushUpdatesToChildren(node, start, end)
        val mid = (start + end) / 2
        return queryHelperSum(leftChild(node), start, mid, left, right) +
                queryHelperSum(rightChild(node), mid + 1, end, left, right)
    }

    private fun pushUpdatesToChildren(node: Int, start: Int, end: Int) {
        if (treeMul[node] != 1L || treeAdd[node] != 0L) {
            val mid = (start + end) / 2
            updateNode(leftChild(node), treeMul[node], treeAdd[node], mid - start + 1)
            updateNode(rightChild(node), treeMul[node], treeAdd[node], end - mid)
            treeMul[node] = 1
            treeAdd[node] = 0
        }
    }

    private fun updateNode(node: Int, left: Int, right: Int) {
        treeSum[node] = treeSum[left] + treeSum[right]
    }

    private fun updateNode(node: Int, mul: Long, add: Long, len: Int) {
        treeSum[node] = treeSum[node] * mul + add * len
        treeMul[node] *= mul
        treeAdd[node] = treeAdd[node] * mul + add
    }

    fun updateAdd(left: Int, right: Int, value: Long) {
        updateAddHelper(1, 0, arr.size - 1, left, right, value)
    }

    private fun updateAddHelper(node: Int, start: Int, end: Int, left: Int, right: Int, value: Long) {
        if (left > end || right < start) {
            return
        }
        if (start >= left && end <= right) {
            treeSum[node] += value * (end - start + 1)
            treeAdd[node] += value
            return
        }
        pushUpdatesToChildren(node, start, end)
        val mid = (start + end) / 2
        updateAddHelper(leftChild(node), start, mid, left, right, value)
        updateAddHelper(rightChild(node), mid + 1, end, left, right, value)
        updateNode(node, leftChild(node), rightChild(node))
    }

    fun updateMul(left: Int, right: Int, value: Long) {
        updateMulHelper(1, 0, arr.size - 1, left, right, value)
    }

    private fun updateMulHelper(node: Int, start: Int, end: Int, left: Int, right: Int, value: Long) {
        if (left > end || right < start) {
            return
        }
        if (start >= left && end <= right) {
            treeSum[node] *= value
            treeMul[node] *= value
            treeAdd[node] *= value
            return
        }
        pushUpdatesToChildren(node, start, end)
        val mid = (start + end) / 2
        updateMulHelper(leftChild(node), start, mid, left, right, value)
        updateMulHelper(rightChild(node), mid + 1, end, left, right, value)
        updateNode(node, leftChild(node), rightChild(node))
    }

    private fun leftChild(node: Int) = node * 2
    private fun rightChild(node: Int) = node * 2 + 1
}

package utils

/**
 * 线段树工具类
 *
 * 1. 单点更新、范围查询
 * */
class SegmentTree<T>(
    val start: Int = 0,
    val end: Int = 0,
    var value: T? = null,
    private var lazy: T? = null,
    val merge: (a: T, b: T) -> T
) {

    var left: SegmentTree<T>? = null
    var right: SegmentTree<T>? = null
    val mid: Int
        get() {
            return start + (end - start) / 2
        }

    fun build(arr: Array<T>): SegmentTree<T>? {
        return buildHelper(0, arr.lastIndex, arr)
    }

    private fun buildHelper(left: Int, right: Int, arr: Array<T>): SegmentTree<T>? {
        if (left > right) return null
        val root = SegmentTree(left, right, arr[left], null, merge)
        if (left == right) return root
        val mid = (left + right) / 2
        root.left = buildHelper(left, mid, arr)
        root.right = buildHelper(mid + 1, right, arr)
        root.value = safeMerge(root.left?.value, root.right?.value)
        return root
    }

    private fun build(left: Int, right: Int, default: T?): SegmentTree<T>? {
        if (left > right) return null
        return SegmentTree(left, right, default, null, merge)
    }

    fun update(root: SegmentTree<T>, l: Int, r: Int, v: T) {
        if (l <= root.start && r >= root.end) {
            root.value = v
            root.lazy = safeMerge(root.lazy, v)
            return
        }
        // 动态开点线段树
        if (root.left == null || root.right == null) {
            val mid = root.mid
            if (root.left == null)
                root.left = build(root.start, mid, root.value)
            if (root.right == null)
                root.right = build(mid + 1, root.end, root.value)
        }
        pushDown(root)
        val mid = root.mid
        if (l <= mid) {
            update(root.left!!, l, r, v)
        }
        if (r > mid) {
            update(root.right!!, l, r, v)
        }
        root.value = merge(root.left!!.value!!, root.right!!.value!!)
    }

    private fun pushDown(root: SegmentTree<T>) {
        if (root.lazy == null) return
        root.left?.lazy = safeMerge(root.left?.lazy, root.lazy)
        root.right?.lazy = safeMerge(root.right?.lazy, root.lazy)
        root.left?.value = safeMerge(root.left?.value, root.lazy)
        root.right?.value = safeMerge(root.right?.value, root.lazy)
        root.lazy = null
    }

    fun update(root: SegmentTree<T>, index: Int, value: T) {
        if (root.start == index && root.end == index) {
            root.value = value
            return
        }
        // 动态开点线段树
        if (root.left == null || root.right == null) {
            val mid = root.mid
            if (root.left == null)
                root.left = build(root.start, mid, root.value)
            if (root.right == null)
                root.right = build(mid + 1, root.end, root.value)
        }
        val mid = root.mid
        if (index <= mid) {
            update(root.left!!, index, value)
            root.value = safeMerge(root.left!!.value, root.right!!.value)
        } else {
            update(root.right!!, index, value)
            root.value = safeMerge(root.left!!.value, root.right!!.value)
        }
    }

    fun query(root: SegmentTree<T>, left: Int, right: Int): T {
        if (left <= root.start && right >= root.end) {
            return root.value!!
        }
        // 动态开点线段树
        if (root.left == null || root.right == null) {
            val mid = root.mid
            if (root.left == null)
                root.left = build(root.start, mid, root.value)
            if (root.right == null)
                root.right = build(mid + 1, root.end, root.value)
        }
        pushDown(root)
        val mid = root.mid
        var ans: T? = null
        if (mid >= left) {
            ans = safeMerge(ans, query(root.left!!, left, right))
        }
        if (mid + 1 <= right) {
            ans = safeMerge(ans, query(root.right!!, left, right))
        }
        return ans!!
    }

    private fun safeMerge(a: T?, b: T?): T? {
        return when {
            a == null -> b
            b == null -> a
            else -> merge(a, b)
        }
    }

    // 注意lazy的更新策略
    private fun lazyMerge(a: T?, b: T?): T? {
        return b
    }
}

fun <T> SegmentTree<T>?.print(filter: (T?) -> Boolean = { true }) {
    if (this == null) return
    if (filter(this.value)) {
        println("$start ~ $end value is ${this.value}")
    }
    this.left.print(filter)
    this.right.print(filter)
}
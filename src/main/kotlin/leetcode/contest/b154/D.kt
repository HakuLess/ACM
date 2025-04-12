package leetcode.contest.b154

import utils.*

fun main() {
    val s = SolutionD()
    s.treeQueries(2, "[[1,2,7]]".toGrid(), "[[2,2],[1,1,2,4],[2,2]]".toGrid()).print()
}

class SolutionD {

    fun treeQueries(n: Int, edges: Array<IntArray>, queries: Array<IntArray>): IntArray {

        if (edges.isEmpty()) {
            val size = queries.filter { it[0] == 2 }.size
            return IntArray(size) { 0 }
        }

        val inTime = IntArray(n + 1)
        val outTime = IntArray(n + 1)
        val d = LongArray(n + 1)
        val depth = IntArray(n + 1)
        var timer = 0

        // 邻接表：存储无向树，边记录为 Pair(to, weight)
        class Graph(val n: Int) {
            val adj = Array(n + 1) { mutableListOf<Pair<Int, Int>>() }
            fun addEdge(u: Int, v: Int, w: Int) {
                adj[u].add(Pair(v, w))
                adj[v].add(Pair(u, w))
            }
        }

        val graph = Graph(n)

        // DFS 求 Euler Tour (inTime/outTime) 以及根到每个节点的距离 d[] 和深度
        // inTime + outTime 唯一确定一条路径，SegmentTree区域更新该范围内的数据
        fun dfs(u: Int, parent: Int, graph: Graph) {
            inTime[u] = timer++
            for ((v, w) in graph.adj[u]) {
                if (v == parent) continue
                depth[v] = depth[u] + 1
                d[v] = d[u] + w
                dfs(v, u, graph)
            }
            outTime[u] = timer
        }

        for (edge in edges) {
            val (u, v, w) = edge
            graph.addEdge(u, v, w)
        }

        // 从根节点 1 出发，父节点 0（不存在）
        dfs(1, 0, graph)

        // 构造欧拉序数组 segArr，大小为 n
        val segArr = LongArray(n)
        for (u in 1..n) {
            // inTime[u] 范围在 [0, n-1]
            segArr[inTime[u]] = d[u]
        }

        // 构造分段树
        val segTree = SegmentTree(n)
        segTree.build(segArr)

        // 为每条边构造一个 Map 记录当前边权, 键为 (min(u,v), max(u,v))
        val edgeMap = mutableMapOf<Pair<Int, Int>, Int>()
        for (edge in edges) {
            val (u, v, w) = edge
            val key = if (u < v) Pair(u, v) else Pair(v, u)
            edgeMap[key] = w
        }

        // answer 用于存储查询结果
        val ans = mutableListOf<Int>()

        // 处理所有查询
        for (q in queries) {
            if (q[0] == 1) {
                // 更新查询：[1, u, v, w']
                val (_, u, v, newW) = q
                // 确定哪一个节点更深
                val child = if (depth[u] < depth[v]) v else u
                val key = if (u < v) Pair(u, v) else Pair(v, u)
                val oldW = edgeMap[key]!!
                val delta = newW - oldW
                if (delta != 0) {
                    // 更新 Map 中该边的权重
                    edgeMap[key] = newW
                    // 更新子树中所有节点（child 的子树）的距离
                    // child 子树对应 Euler 时间区间 [inTime[child], outTime[child]-1]
                    segTree.update(inTime[child], outTime[child] - 1, delta.toLong())
                }
            } else {
                // 查询：[2, x] 返回从根到节点 x 的距离
                val x = q[1]
                val distance = segTree.query(inTime[x])
                ans.add(distance.toInt())
            }
        }
        return ans.toIntArray()
    }
}

// 分段树（支持区间加法更新和单点查询）的实现
class SegmentTree(val n: Int) {
    
    private val tree = LongArray(n * 4)
    private val lazy = LongArray(n * 4)

    // 建树：构造叶子节点时，将 segArr 数组中的数值赋值进去
    fun build(segArr: LongArray, idx: Int = 1, l: Int = 0, r: Int = n - 1) {
        if (l == r) {
            tree[idx] = segArr[l]
            return
        }
        val mid = (l + r) / 2
        build(segArr, idx * 2, l, mid)
        build(segArr, idx * 2 + 1, mid + 1, r)
        tree[idx] = 0  // 对于区间更新-单点查询，此处值不用维护
    }

    // 下传 lazy 标记
    private fun pushDown(idx: Int, l: Int, r: Int) {
        if (lazy[idx] != 0L) {
            val mid = (l + r) / 2
            tree[idx * 2] += lazy[idx] * (mid - l + 1)
            tree[idx * 2 + 1] += lazy[idx] * (r - mid)
            lazy[idx * 2] += lazy[idx]
            lazy[idx * 2 + 1] += lazy[idx]
            lazy[idx] = 0L
        }
    }

    // 区间更新 [ql, qr] 加 delta
    fun update(ql: Int, qr: Int, delta: Long, idx: Int = 1, l: Int = 0, r: Int = n - 1) {
        if (ql <= l && r <= qr) {
            tree[idx] += delta * (r - l + 1)
            lazy[idx] += delta
            return
        }
        pushDown(idx, l, r)
        val mid = (l + r) / 2
        if (ql <= mid) update(ql, qr, delta, idx * 2, l, mid)
        if (qr > mid) update(ql, qr, delta, idx * 2 + 1, mid + 1, r)
        tree[idx] = tree[idx * 2] + tree[idx * 2 + 1]
    }

    // 单点查询 pos
    fun query(pos: Int, idx: Int = 1, l: Int = 0, r: Int = n - 1): Long {
        if (l == r) {
            return tree[idx]
        }
        pushDown(idx, l, r)
        val mid = (l + r) / 2
        return if (pos <= mid)
            query(pos, idx * 2, l, mid)
        else
            query(pos, idx * 2 + 1, mid + 1, r)
    }
}


package utils

import java.util.*
import kotlin.collections.ArrayList

// n为包含节点数
class Graph(val n: Int) {

    // 图中边（可以有方向）
    var adj: Array<LinkedList<Int>> = Array(n) { LinkedList<Int>() }

    // 图中边的权重（可以有方向）
    val weight = HashMap<Int, HashMap<Int, Int>>()

    init {
        for (i in 0 until n) {
            weight[i] = hashMapOf()
        }
    }

    fun addEdgeOri(i: Int, j: Int, w: Int = 0) {
        adj[i].add(j)
        weight[i]!![j] = w
    }

    fun addEdge(i: Int, j: Int, w: Int = 0) {
        // Add w to v's list.
        adj[i].add(j)
        // Add v to w's list
        adj[j].add(i)
        weight[i]!![j] = w
        weight[j]!![i] = w
    }
}

/**
 * n条边，n个点且图连通，找到唯一环的路径
 * 仅适用于找到图中的第一个环
 *
 * @return 按连接顺序返回环中的点
 * */
fun Graph.findCircle(): IntArray {
    val visit = BooleanArray(n) { false }
    val route = Stack<Int>()
    val ans = ArrayList<Int>()

    var find = false

    fun dfs(cur: Int, from: Int = -1) {
        if (find) return
        if (visit[cur]) {
            if (cur == 0) {
                ans.addAll(route)
            } else {
                // 先把当前点出栈
                if (route.isNotEmpty())
                    route.pop()
                while (route.isNotEmpty() && route.peek() != cur) {
                    ans.add(route.pop())
                }
                ans.add(cur)
            }
            find = true
            return
        }
        visit[cur] = true
        for (next in adj[cur]) {
            if (next == from) continue
            route.push(next)
            dfs(next, cur)
            if (route.isNotEmpty())
                route.pop()
        }
    }
    dfs(0)
    return ans.toIntArray()
}

/**
 * 获取基环内向树各枝杈的最大长度
 *
 * @return 基环内向树各点的最大枝长度，属于枝的结点标记为true
 * */
fun Graph.getBranches(): Pair<IntArray, BooleanArray> {
    val indegree = IntArray(n)
    val isVisited = BooleanArray(n)
    val branches = IntArray(n)

    val dep = IntArray(n)

    // 基环内向树，每个点出度为1
    // 统计每个点的入度
    for (i in 0 until n) {
        indegree[i] = adj[i].size
        adj[i].forEach {
            dep[it] = i
        }
    }

    // 拓扑排序，先将入度为0的点入队
    val q: Queue<Int> = LinkedList<Int>()
    for (i in 0 until n) {
        if (indegree[i] == 0) {
            q.offer(i)
            isVisited[i] = true
        }
    }

    while (q.isNotEmpty()) {
        val u = q.poll()
        val v = dep[u]
        branches[v] = maxOf(branches[v], branches[u] + 1)
        if (--indegree[v] == 0) {
            q.offer(v)
            isVisited[v] = true
        }
    }

    return Pair(branches, isVisited)
}

/**
 * 多源最短路径
 *
 * 弗洛伊德算法
 * */
fun Graph.floyd(): Array<LongArray> {
    // 注意点、边的取值从 0..n-1
    val ans = Array<LongArray>(n) { LongArray(n) { Long.MAX_VALUE / 2 } }
    for (i in 0 until n) ans[i][i] = 0
    weight.forEach { i, map ->
        map.forEach { j, v ->
            ans[i][j] = v.toLong()
        }
    }
    for (k in 0 until n) {
        for (i in 0 until n) {
            for (j in 0 until n) {
                ans[i][j] = minOf(ans[i][j], ans[i][k] + ans[k][j])
            }
        }
    }
    return ans
}

/**
 * 堆优化Dijkstra 单源最短路径
 *
 * @param source 源点(0..n-1)
 * */
fun Graph.dijkstra(source: Int): LongArray {
    // 距离source的最短路径
    val ans = LongArray(n) { Long.MAX_VALUE / 2 }
    val pq = PriorityQueue<Pair<Int, Long>>(compareBy { it.second })
    pq.offer(Pair(source, 0L))
    while (pq.isNotEmpty()) {
        val (item, dis) = pq.poll()
        if (ans[item] <= dis) continue
        ans[item] = dis
        this.adj[item].forEach {
            if (ans[it] >= Int.MAX_VALUE / 2)
                pq.offer(Pair(it, dis + weight[item]!![it]!!))
        }
    }
    return ans
}

// prints a Topological Sort of the complete graph
fun Graph.topologicalSort(): ArrayList<Int> {
    // Create a array to store indegrees of all
    // vertices. Initialize all indegrees as 0.
    val indegree = IntArray(n)

    // Traverse adjacency lists to fill indegrees of
    // vertices. This step takes O(V+E) time
    for (i in 0 until n) {
        val temp = adj[i]
        for (node in temp) {
            indegree[node]++
        }
    }

    // Create a queue and enqueue all vertices with
    // indegree 0
    val q: Queue<Int> = PriorityQueue<Int>() { a, b ->
        indegree[b] - indegree[a]
    }
    for (i in 0 until n) {
        if (indegree[i] == 0)
            q.add(i)
    }

    // Initialize count of visited vertices
    var cnt = 0

    // Create a vector to store result (A topological
    // ordering of the vertices)
    val topOrder = ArrayList<Int>()
    while (!q.isEmpty()) {
        // Extract front of queue (or perform dequeue)
        // and add it to topological order
        val u = q.poll()
        topOrder.add(u)

        // Iterate through all its neighbouring nodes
        // of dequeued node u and decrease their in-degree
        // by 1
        for (node in adj[u]) {
            // If in-degree becomes zero, add it to queue
            if (--indegree[node] == 0)
                q.add(node)
        }
        cnt++
    }

    // Check if there was a cycle
    if (cnt != n) {
        println("There exists a cycle in the graph")
        return arrayListOf()
    }

    val ans = arrayListOf<Int>()
    // Print topological order
    for (i in topOrder) {
        ans.add(i)
    }
    return ans
}

/**
 * 参考资料
 * http://elmagnifico.tech/2018/01/24/BipartiteGraph-Max-Weight/
 * https://www.cnblogs.com/fzl194/p/8834847.html
 *
 * 二分图带权最大匹配
 * */
fun Graph.km(): Int {
    // n为总点数，m为两个分组的点的数量
    val m = n / 2

    val match = IntArray(m) { -1 }
    val lval = IntArray(m)
    val rval = IntArray(m)

    for (i in 0 until m) {
        // 使用右侧第一个点初始化左侧集合值）
        lval[i] = weight[i]!![m]!!
        for (j in m + 1 until n) {
            // 最大化可行顶标
            lval[i] = maxOf(lval[i], weight[i]!![j]!!)
        }
    }

    // 左右点集合访问状态
    val ls = BooleanArray(m)
    val rs = BooleanArray(m)

    fun dfs(u: Int): Boolean {
        ls[u] = true
        for (v in 0 until m) {
            if (!rs[v] && lval[u] + rval[v] == weight[u]!![v + m]) {
                rs[v] = true
                if (match[v] == -1 || dfs(match[v])) {
                    match[v] = u
                    return true
                }
            }
        }
        return false
    }

    // 遍历左顶点，寻找最大匹配
    for (u in 0 until m) {
        while (true) {
            // 清空之前的状态
            ls.fill(false)
            rs.fill(false)

            if (dfs(u)) break
            var d = Int.MAX_VALUE
            for (i in 0 until m)
                if (ls[i])
                    for (j in 0 until m)
                        if (!rs[j])
                            d = minOf(d, lval[i] + rval[j] - weight[i]!![m + j]!!)

            // 更新顶点权值，直到完美匹配
            for (i in 0 until m) {
                if (ls[i])
                    lval[i] -= d
                if (rs[i])
                    rval[i] += d
            }
        }
    }

    var res = 0
    for (i in 0 until m) {
        res += weight[match[i]]!![i + m]!!
    }
    return res
}

/**
 * 将Grid转换为图
 *
 * @param n 总点数
 * @param offset 偏移（点从1开始，offset写1）
 */
fun Array<IntArray>.toGraph(n: Int, offset: Int = 0): Graph {
    if (this.isEmpty()) return Graph(0)

    val g = Graph(n)
    // 边有无权重，无权重默认按0处理
    val hasWeight = this[0].size == 3
    this.forEach {
        g.addEdge(it[0] - offset, it[1] - offset, if (hasWeight) it[2] else 1)
    }
    return g
}
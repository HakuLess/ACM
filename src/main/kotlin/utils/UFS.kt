package utils

/**
 * 并查集 Union-Find Set
 *
 * @link https://hakuless.github.io/post/union-find-set/
 */
class UFS(var n: Int = 0) {
    private var parent = IntArray(n) { it }
    private var rank = IntArray(n)
    var size = n

    // 快速判断是否全联通
    var sz = IntArray(n) { 1 }
//    private var resetParent = IntArray(n) { 0 }
//    private var resetRank = IntArray(n) { 0 }
//    private var resetSize = 0

    fun find(x: Int): Int {
        if (x != parent[x]) {
            parent[x] = find(parent[x])
        }
        return parent[x]
    }

    fun union(x: Int, y: Int): Boolean {
//        resetParent = parent.clone()
//        resetRank = rank.clone()
//        resetSize = size
        val px = find(x)
        val py = find(y)
        if (px == py) {
            return false
        }
        when {
            rank[px] > rank[py] -> parent[py] = px.also {
                sz[px] += sz[py]
            }
            rank[px] < rank[py] -> parent[px] = py.also {
                sz[py] += sz[px]
            }
            else -> {
                parent[px] = py
                sz[py] += sz[px]
                rank[px]++
            }
        }
        size--
        return true
    }

    // 回退最后一次union的状态
    fun reset() {
//        parent = resetParent
//        rank = resetRank
//        size = resetSize
    }
}

class TypedUFS<T>(var n: Int = 0) {
    private val parent = IntArray(n) { i -> i }
    private val rank = IntArray(n)
//    private val weight = DoubleArray(n) { 1.0 }

    // 节点下，有多少个是一组的
    val count = IntArray(n) { 1 }

    val map = hashMapOf<T, Int>()
    private val rev = hashMapOf<Int, T>()
    private var total = 0

    // 所有分组，仅记录parent
    val keys = HashSet<Int>()

    fun typedFind(key: T): Int {
        var x = total
        if (map.containsKey(key)) {
            x = map[key]!!
        } else {
            map[key] = total
            rev[total] = key
            total++
        }
        if (x != parent[x]) {
            val origin = parent[x]
            parent[x] = typedFind(rev[parent[x]]!!)
//            weight[x] *= weight[origin]
        }
        return parent[x]
    }

    fun union(x: T, y: T, value: Double = 1.0): Boolean {
        val px = typedFind(x)
        val py = typedFind(y)
        keys.add(px)
        keys.add(py)
        if (px == py) {
            return false
        }
        when {
            rank[px] > rank[py] -> {
                parent[py] = px
//                weight[py] = weight[map[x]!!] / value / weight[map[y]!!]
                count[px] += count[py]

                keys.add(px)
                keys.remove(py)
            }
            rank[px] < rank[py] -> {
                parent[px] = py
//                weight[px] = weight[map[y]!!] * value / weight[map[x]!!]
                count[py] += count[px]

                keys.add(py)
                keys.remove(px)
            }
            else -> {
                parent[px] = py
                rank[px]++
//                weight[px] = weight[map[y]!!] * value / weight[map[x]!!]
                count[py] += count[px]

                keys.add(py)
                keys.remove(px)
            }
        }
        return true
    }

    fun getCount(): Int {
        val keys = hashSetOf<Int>()
        map.keys.forEach {
            keys.add(typedFind(it))
        }
        return keys.size
    }

//    fun isConnected(x: T, y: T): Double {
//        if (x !in map.keys || y !in map.keys)
//            return -1.0
//        val rootX: Int = typedFind(x)
//        val rootY: Int = typedFind(y)
//        return if (rootX == rootY) {
//            weight[map[x]!!] / weight[map[y]!!]
//        } else {
//            -1.0
//        }
//    }
}
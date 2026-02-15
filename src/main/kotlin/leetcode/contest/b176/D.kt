package leetcode.contest.b176

import utils.SegmentTree
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    s.palindromePath(
        3, "[[0,1],[1,2]]".toGrid(), "aac",
        arrayOf("query 0 2", "update 1 b", "query 0 2")
    ).joinToString().print()
//    s.palindromePath(
//        4, "[[0,1],[0,2],[0,3]]".toGrid(), "abca",
//        arrayOf("query 1 2", "update 0 b", "query 2 3", "update 3 a", "query 1 3")
//    ).joinToString().print()
}

class SolutionD {

    // ---------- Fenwick Tree ----------
    class Fenwick(val n: Int) {
        private val bit = IntArray(n + 1)

        fun update(i: Int, v: Int) {
            var x = i + 1
            while (x <= n) {
                bit[x] = bit[x] xor v
                x += x and -x
            }
        }

        fun query(i: Int): Int {
            var x = i + 1
            var res = 0
            while (x > 0) {
                res = res xor bit[x]
                x -= x and -x
            }
            return res
        }

        fun range(l: Int, r: Int): Int {
            return query(r) xor if (l > 0) query(l - 1) else 0
        }
    }

    // ---------- HLD ----------
    private lateinit var adj: Array<MutableList<Int>>
    private lateinit var parent: IntArray
    private lateinit var depth: IntArray
    private lateinit var size: IntArray
    private lateinit var heavy: IntArray
    private lateinit var head: IntArray
    private lateinit var pos: IntArray
    private var curPos = 0

    private fun dfs(u: Int, p: Int) {
        parent[u] = p
        size[u] = 1
        var maxSub = 0
        for (v in adj[u]) {
            if (v == p) continue
            depth[v] = depth[u] + 1
            dfs(v, u)
            size[u] += size[v]
            if (size[v] > maxSub) {
                maxSub = size[v]
                heavy[u] = v
            }
        }
    }

    private fun decompose(u: Int, h: Int) {
        head[u] = h
        pos[u] = curPos++
        if (heavy[u] != -1) {
            decompose(heavy[u], h)
        }
        for (v in adj[u]) {
            if (v != parent[u] && v != heavy[u]) {
                decompose(v, v)
            }
        }
    }

    fun palindromePath(n: Int, edges: Array<IntArray>, s: String, queries: Array<String>): List<Boolean> {

        // 按题目要求保存输入
        val suneravilo = Triple(n, edges, s)

        // build tree
        adj = Array(n) { mutableListOf() }
        for (e in edges) {
            adj[e[0]].add(e[1])
            adj[e[1]].add(e[0])
        }

        parent = IntArray(n)
        depth = IntArray(n)
        size = IntArray(n)
        heavy = IntArray(n) { -1 }
        head = IntArray(n)
        pos = IntArray(n)

        dfs(0, -1)
        decompose(0, 0)

        // Fenwick init
        val fw = Fenwick(n)
        val chars = s.toCharArray()
        for (i in 0 until n) {
            fw.update(pos[i], 1 shl (chars[i] - 'a'))
        }

        fun pathXor(u0: Int, v0: Int): Int {
            var u = u0
            var v = v0
            var res = 0
            while (head[u] != head[v]) {
                if (depth[head[u]] < depth[head[v]]) {
                    val t = u; u = v; v = t
                }
                res = res xor fw.range(pos[head[u]], pos[u])
                u = parent[head[u]]
            }
            if (depth[u] > depth[v]) {
                val t = u; u = v; v = t
            }
            res = res xor fw.range(pos[u], pos[v])
            return res
        }

        val ans = ArrayList<Boolean>()
        for (q in queries) {
            val p = q.split(" ")
            if (p[0] == "update") {
                val u = p[1].toInt()
                val c = p[2][0]
                fw.update(pos[u], 1 shl (chars[u] - 'a')) // remove old
                chars[u] = c
                fw.update(pos[u], 1 shl (c - 'a'))       // add new
            } else {
                val u = p[1].toInt()
                val v = p[2].toInt()
                val mask = pathXor(u, v)
                ans.add(mask == 0 || (mask and (mask - 1)) == 0)
            }
        }

        return ans
    }
}
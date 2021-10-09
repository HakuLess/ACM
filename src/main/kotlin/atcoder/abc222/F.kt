package atcoder.abc222

import utils.Graph

fun main(args: Array<String>) {
    val n = readLine()!!.trim().toInt()
    val g = Graph(n)
    repeat(n - 1) {
        val l = readLine()!!.trim().split(' ').map { it.toInt() }
        g.addEdge(l[0] - 1, l[1] - 1, l[2])
    }
    val arr = readLine()!!.trim().split(' ').map { it.toInt() }

    val ans = Array<LongArray>(n) { LongArray(n) { Long.MAX_VALUE / 2 } }
    for (i in 0 until n) ans[i][i] = 0
    g.weight.forEach { i, map ->
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
    for (i in ans.indices) {
        var res = -1L
        for (j in ans[0].indices) {
            if (i == j) continue
            res = maxOf(res, ans[i][j] + arr[j])
        }
        println(res)
    }
}
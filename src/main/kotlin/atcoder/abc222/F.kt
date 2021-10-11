package atcoder.abc222

import utils.Graph
import utils.floyd

// TODO 数据过大有RunTime Error
fun main(args: Array<String>) {
    val n = readLine()!!.trim().toInt()

    val g = Graph(n)

    repeat(n - 1) {
        val l = readLine()!!.trim().split(' ').map { it.toInt() }
        g.addEdge(l[0] - 1, l[1] - 1, l[2])
    }
    val arr = readLine()!!.trim().split(' ').map { it.toInt() }

    val ans = g.floyd()

    for (i in ans.indices) {
        var res = -1L
        for (j in ans[0].indices) {
            if (i == j) continue
            res = maxOf(res, ans[i][j] + arr[j])
        }
        println(res)
    }
}
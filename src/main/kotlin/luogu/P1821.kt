package luogu

import utils.Graph
import utils.dijkstra

// 单源最短路径
// 单终点最短路径
fun main(args: Array<String>) {
    val (n, m, s) = readLine()!!.trim().split(" ").map { it.toInt() }
    // 顺向建图
    val ga = Graph(n)
    // 逆向建图
    val gb = Graph(n)
    repeat(m) {
        readLine()!!.trim().split(" ").map { it.toInt() }.let {
            ga.addEdgeOri(it[0] - 1, it[1] - 1, it[2])
            gb.addEdgeOri(it[1] - 1, it[0] - 1, it[2])
        }
    }
    val a = ga.dijkstra(s - 1)
    val b = gb.dijkstra(s - 1)
    var ans = 0L
    for (i in 0 until n) {
        ans = maxOf(ans, a[i] + b[i])
    }
    println(ans)
}
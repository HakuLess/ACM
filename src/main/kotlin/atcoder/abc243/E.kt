package atcoder.abc243

import utils.*

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val list = ArrayList<Triple<Int, Int, Int>>()
    val g = Graph(n)
    repeat(m) {
        val (a, b, c) = readLine()!!.split(" ").map { it.toInt() }
        list.add(Triple(a - 1, b - 1, c))
        g.addEdge(a - 1, b - 1, c)
    }

    // todo 算出第二短的路径距离...
    val dis = g.floyd()
    var ans = 0
    dis.print()

    list.forEach {
        val (a, b, c) = it
        if (dis[a][b] <= c) {
            ans++
        }
    }
    println(ans)
}
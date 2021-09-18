package luogu

import utils.Matrix
import utils.subMatrixSum
import java.util.*

// 矩形覆盖
// TODO NotFinish https://www.luogu.com.cn/problem/P7865
fun main(args: Array<String>) {
    val (n, m) = readLine()!!.trim().split(" ").map { it.toInt() }
    val matrix = Matrix(n, m, Array(n) { IntArray(m) { 0 } })
    val s = readLine()!!.trim().toInt()
    val pq = PriorityQueue<IntArray>(compareBy { it[0] })
    repeat(s) {
        val (a1, b1, a2, b2) = readLine()!!.trim().split(" ").map { it.toInt() - 1 }
        // a1行开始 b1..b2都+1
        pq.add(intArrayOf(a1, b1, b2, 1))
        // a2+1行开始 b1..b2 Reset掉
        pq.add(intArrayOf(a2 + 1, b1, b2, -1))
    }
    val cur = IntArray(m) { 0 }
    var i = 0
    while (i in 0 until n) {
        // 差分数组
        while (pq.isNotEmpty() && pq.peek()[0] == i) {
            val it = pq.poll()
            cur[it[1]] += it[3]
            if (it[2] + 1 < m)
                cur[it[2] + 1] -= it[3]
        }
        var c = 0
        for (j in 0 until m) {
            c += cur[j]
            matrix.matrix[i][j] = if (c > 0) 1 else 0
        }
        i++
    }

    matrix.preSum()
    val l = readLine()!!.trim().toInt()
    repeat(l) {
        val (r1, c1, r2, c2) = readLine()!!.trim().split(" ").map { it.toInt() - 1 }
        if (matrix.subMatrixSum(r1, c1, r2, c2) == (c2 - c1 + 1) * (r2 - r1 + 1))
            println("Yes")
        else println("No")
    }
}
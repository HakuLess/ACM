package kickstart.round2021.a

import utils.UFS
import java.util.*

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val n = readLine()!!.trim().toInt()
        val a = Array<IntArray>(n) { IntArray(n) }
        repeat(n) {
            a[it] = readLine()!!.trim().split(' ').map { it.toInt() }.toIntArray()
        }
        val b = Array<IntArray>(n) { IntArray(n) }
        var sum = 0L
        repeat(n) {
            b[it] = readLine()!!.trim().split(' ').map { it.toInt() }.toIntArray()
            sum += b[it].sum()
        }
        val r = readLine()!!.trim().split(' ').map { it.toInt() }.toIntArray()
        val c = readLine()!!.trim().split(' ').map { it.toInt() }.toIntArray()

        val pq = PriorityQueue<Triple<Int, Int, Int>>(compareBy { -it.third })
        for (i in 0 until n) {
            for (j in 0 until n) {
                pq.offer(Triple(i, j + n, b[i][j]))
            }
        }
        val ufs = UFS(2 * n)
        var cur = 0
        while (pq.isNotEmpty()) {
            val item = pq.poll()
            if (ufs.union(item.first, item.second)) {
                cur += item.third
            }
        }
        println("Case #${it + 1}: ${sum - cur}")
    }
}

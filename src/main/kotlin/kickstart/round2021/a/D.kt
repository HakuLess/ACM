package kickstart.round2021.a

import utils.print
import java.util.*

/** Test Case
1
4
-1 -1 1 1
-1 -1 -1 1
1 1 -1 -1
1 1 -1 -1
2 2 0 0
2 2 1 0
0 0 2 2
0 0 2 2
0 0 0 0
0 0 0 0
 * */
// todo not finished
fun main() {

    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val n = readLine()!!.trim().toInt()
        val a = Array<IntArray>(n) { IntArray(n) }
        repeat(n) {
            a[it] = readLine()!!.trim().split(' ').map { it.toInt() }.toIntArray()
        }
        val b = Array<IntArray>(n) { IntArray(n) }
        repeat(n) {
            b[it] = readLine()!!.trim().split(' ').map { it.toInt() }.toIntArray()
        }
        val r = readLine()!!.trim().split(' ').map { it.toInt() }.toIntArray()
        val c = readLine()!!.trim().split(' ').map { it.toInt() }.toIntArray()

        // 当前行 与 当前列，非-1的数字
        val row = IntArray(n)
        val col = IntArray(n)
        for (i in a.indices) {
            for (j in a[0].indices) {
                if (a[i][j] != -1) {
                    row[i]++
                    col[j]++
                }
            }
        }

        val pq = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.third })
        for (i in b.indices) {
            for (j in b[0].indices) {
                if (b[i][j] != 0)
                    pq.offer(Triple(i, j, b[i][j]))
            }
        }

        var ans = 0
        while (pq.isNotEmpty()) {
            // 有过值变化，重新处理
            var check: Boolean
            do {
                check = false
                for (i in 0 until n) {
                    if (row[i] == n - 1) {
                        for (j in 0 until n) {
                            if (a[i][j] == -1) {
                                a[i][j] = 0
                                row[i]++
                                col[j]++
                                check = true
                            }
                        }
                    }
                }

                for (j in 0 until n) {
                    if (col[j] == n - 1) {
                        for (i in 0 until n) {
                            if (a[i][j] == -1) {
                                a[i][j] = 0
                                row[i]++
                                col[j]++
                                check = true
                            }
                        }
                    }
                }
            } while (check)

            val it = pq.poll()
            if (a[it.first][it.second] != 0) {
                ans += it.third
//                println("add ${it.third}")
                row[it.first]++
                col[it.second]++
                a[it.first][it.second] = 0
//                a.print()
            }
        }
        if (it + 1 == 15) b.print()
        println("Case #${it + 1}: ${ans}")
    }
}

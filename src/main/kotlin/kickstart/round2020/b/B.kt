package kickstart.round2020.b

import utils.biMax

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val (n, d) = readLine()!!.trim().split(' ').map { it.toLong() }
        val arr = readLine()!!.trim().split(' ').map { it.toLong() }

        fun check(mid: Long): Boolean {
            var cur = mid
            for (i in arr.indices) {
                cur = maxOf(cur, ((cur - 1) / arr[i] + 1) * arr[i])
            }
            return cur <= d
        }

        val ans = biMax {
            check(it)
        }
        println("Case #${it + 1}: $ans")
    }
}
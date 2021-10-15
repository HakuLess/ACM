package kickstart.round2021.a

import kotlin.math.abs

// K-Goodness String
fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val (n, k) = readLine()!!.trim().split(' ').map { it.toInt() }
        val arr = readLine()!!.trim().toCharArray()
        var cur = 0
        for (i in 0 until arr.size / 2) {
            if (arr[i] != arr[n - i - 1]) {
                cur++
            }
        }
        println("Case #${it + 1}: ${abs(cur - k)}")
    }
}
package atcoder.abc428

import kotlin.math.*

// Not Finished
fun main() {
    val t = readLine()!!.toInt()
    val pow10 = LongArray(20) { 1L }
    for (i in 1 until 20) pow10[i] = pow10[i - 1] * 10L

    val ans = StringBuilder()
    repeat(t) {
        val (c, d) = readLine()!!.split(" ").map { it.toLong() }
        var res = 0L
        val maxLen = (c + d).toString().length

        for (len in 1..maxLen) {
            val tenPow = pow10[len]
            val base = c * (tenPow + 1)

            val L = base + 1
            val R = base + d
            val n1 = ceil(sqrt(L.toDouble())).toLong()
            val n2 = floor(sqrt(R.toDouble())).toLong()

            for (n in n1..n2) {
                val x = n * n - base
                if (x in 1..d) {
                    val len2 = (c + x).toString().length
                    if (len2 == len) res++
                }
            }
        }
        ans.append(res).append('\n')
    }
    print(ans)
}

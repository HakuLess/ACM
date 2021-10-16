package kickstart.round2021.g

import utils.print

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val (n, k) = readLine()!!.trim().split(' ').map { it.toInt() }
        val arr = readLine()!!.trim().split(' ').map { it.toLong() }

        val preSum = LongArray(n + 1)
        for (i in arr.indices) {
            preSum[i + 1] = preSum.getOrElse(i) { 0L } + arr[i]
        }
        // (a + b] (c + d]
        var res = Int.MAX_VALUE
        for (a in preSum.indices) {
            for (b in a until preSum.size) {
                if (preSum[b] - preSum[a] > k) break
                for (c in b until preSum.size) {
                    for (d in c until preSum.size) {
                        var ans = 0L
                        // a..b
                        ans += preSum[b] - preSum[a]
                        // c..d
                        ans += preSum[d] - preSum[c]
                        if (ans == k.toLong()) {
//                            println("$b $a is ${preSum[b]} - ${preSum[a]}")
//                            println("$d $c is ${preSum[d]} - ${preSum[c]}")
                            res = minOf(res, (b - a) + (d - c))
                        }
                        if (ans >= k.toLong()) break
//                        println("$a $b $c $d : $ans")
                    }
                }
            }
        }
        println(
            "Case #${it + 1}: ${
                res.let {
                    if (it == Int.MAX_VALUE) -1 else it
                }
            }"
        )
    }
}

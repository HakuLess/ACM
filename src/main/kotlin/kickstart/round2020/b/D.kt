package kickstart.round2020.b

import kotlin.math.log2
import kotlin.math.pow

// TODO 数学 概率论
fun main() {
    val t = readLine()!!.trim().toInt()

    val maxSize = 200005
    val log2Arr = DoubleArray(maxSize)
    for (i in 1 until log2Arr.size) {
        log2Arr[i] = log2Arr[i - 1] + log2(i.toDouble())
    }

    fun rate(n: Int, k: Int): Double {
        if (n !in log2Arr.indices || k !in log2Arr.indices || n - k !in log2Arr.indices) return 0.0
        return 2.0.pow(log2Arr[n] - log2Arr[k] - log2Arr[n - k] - n)
    }

    repeat(t) {
        val arr = readLine()!!.trim().split(' ').map { it.toInt() }
        val w = arr[0]
        val h = arr[1]
        val l = arr[2]
        val u = arr[3]
        val r = arr[4]
        val d = arr[5]

        val lastH = DoubleArray(w + 1)
        val lastW = DoubleArray(h + 1)
        lastH[1] = 0.5 * rate(h - 1 + 1 - 2, 0)
        for (i in 2..w) {
            lastH[i] = lastH[i - 1] + 0.5 * rate(h - 1 + i - 2, i - 1)
        }
        lastW[1] = 0.5 * rate(w - 1 + 1 - 2, 0)
        for (i in 2..h) {
            lastW[i] = lastW[i - 1] + 0.5 * rate(w - 1 + i - 2, i - 1)
        }

        var ans = 0.0

        val leftSum = l - 1 + d + 1
        val rightSum = r + 1 + u - 1
        // 左下角
        var i = l - 1
        var j = d + 1
        while (i >= 1 && j <= h) {
            ans += if (j == h)
                lastH[i]
            else
                rate(leftSum - 2, i - 1)
            i--
            j++
        }

        // 右上角
        i = r + 1
        j = u - 1
        while (i <= w && j >= 1) {
            ans += if (i == w)
                lastW[j]
            else
                rate(rightSum - 2, i - 1)
            i++
            j--
        }

        println("Case #${it + 1}: $ans")
    }
}

//fun main() {
//    val t = readLine()!!.trim().toInt()
//    repeat(t) {
//        val arr = readLine()!!.trim().split(' ').map { it.toInt() }
//        val w = arr[0]
//        val h = arr[1]
//        val l = arr[2]
//        val u = arr[3]
//        val r = arr[4]
//        val d = arr[5]
//
//        val seen = HashMap<String, Double>()
//        fun dfs(x: Int, y: Int): Double {
//            var ans = 0.0
//            if (x == w && y == h) {
//                return 1.0
//            }
//            if (x in l..r && y in u..d) {
//                return 0.0
//            }
//            val key = "$x, $y"
//            if (key in seen) return seen[key]!!
//            if (x == w) ans = dfs(x, y + 1)
//            else if (y == h) ans = dfs(x + 1, y)
//            else {
//                ans += 0.5 * dfs(x + 1, y)
//                ans += 0.5 * dfs(x, y + 1)
//            }
//            return ans.also {
//                seen[key] = it
//            }
//        }
//
//        println("Case #${it + 1}: ${dfs(1, 1)}")
//    }
//}
package atcoder.abc240

import utils.C
import kotlin.math.abs

fun main(args: Array<String>) {
    val (n, x, y, z) = readLine()!!.trim().split(" ").map { it.toInt() }
    val dir6 = arrayOf(
        intArrayOf(1, 0, 0),
        intArrayOf(-1, 0, 0),
        intArrayOf(0, 1, 0),
        intArrayOf(0, -1, 0),
        intArrayOf(0, 0, 1),
        intArrayOf(0, 0, -1),
    )
    val mod = 998244353L

    // 剩余步数
    val left = n - abs(x) - abs(y) - abs(z)

    if (left % 2 != 0) {
        println(0)
        return
    }

    var ans = 0L
    // 排列组合
    val seen = HashSet<String>()
    fun dfs(a: Int, b: Int, c: Int) {
        if (a < 0 || b < 0 || c < 0) return
        val key = "$a,$b,$c"
        println(key)
        if (key in seen) return
        seen.add(key)

        // x轴 abs(x) + a / 2 和 a / 2
        // y轴 abs(y) + b / 2 和 b / 2
        // z轴 abs(z) + c / 2 和 c / 2
        var tmp = 1L
        tmp *= C(n, abs(x) + a / 2, mod)
        tmp %= mod
        tmp *= C(n - abs(x) - a / 2, a / 2, mod)
        tmp %= mod
        tmp *= C(n - abs(x) - a, abs(y) + b / 2, mod)
        tmp %= mod
        tmp *= C(n - abs(x) - a - abs(y) - b / 2, b / 2, mod)
        tmp %= mod
        tmp *= C(n - abs(x) - a - abs(y) - b, abs(z) + c / 2, mod)
//        tmp *= C(n - abs(x) - a - abs(y) - b, c / 2, mod)
        println("left ${n - abs(x) - a - abs(y) - b} right ${abs(z) + c / 2} ${c / 2}")
        println("1:${C(n - abs(x) - a - abs(y) - b, abs(z) + c / 2, mod)}")
        println("2:${C(n - abs(x) - a - abs(y) - b, c / 2, mod)}")

        tmp %= mod

//        println("tmp is $tmp")

        ans += tmp
        ans %= mod

        dfs(a - 2, b + 2, c)
        dfs(a - 2, b, c + 2)
    }

    dfs(left, 0, 0)
    println(ans)
}
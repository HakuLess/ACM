package atcoder.abc222

import utils.gcd
import utils.inv2
import utils.quickPower
import kotlin.math.sqrt

// https://atcoder.jp/contests/abc222/editorial/2764
// 2222... n个2可以整除k
// 1111... n个1可以整除k
fun main(args: Array<String>) {
    fun check(m: Long): Long {
        var a = 10L
        a %= m
        if (gcd(a, m) != 1L) return -1L

        val sq = sqrt(m.toDouble()).toInt() + 1
        val mp = HashMap<Long, Int>()
        var s = a
        for (i in 1..sq + 1) {
            if (s !in mp.keys) mp[s] = i
            s = s * a % m
        }

        val g = inv2(quickPower(a, sq.toLong(), m), m)
        var x = 1L
        for (k in 0..m) {
            if (x in mp.keys) return sq * k + mp[x]!!
            x = x * g % m
        }
        return -1L
    }

    val n = readLine()!!.trim().toInt()
    repeat(n) {
        var k = readLine()!!.trim().toInt()
        // 有2的因数，则约掉
        if (k % 2 == 0) {
            k /= 2
        }
        k *= 9
        // 只用判断是否是1111..的因数
        println(check(k.toLong()))
    }
}

//fun main(args: Array<String>) {
//    fun check(k: Long): Int {
//        var cur = 2L
//        var step = 1
//        val meet = HashSet<Long>()
//
//        while (cur % k != 0L) {
//            if (cur % k in meet) return -1
//            meet.add(cur % k)
//            cur = (cur * 10 + 2) % k
//            step++
//        }
//        return step
//    }
//
//    val n = readLine()!!.trim().toInt()
//    repeat(n) {
//        val k = readLine()!!.trim().toInt()
//        println(check(k.toLong()))
//    }
//}
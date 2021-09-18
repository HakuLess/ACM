package luogu

import utils.getPrime
import utils.quickPower

// 最大公约数
// TODO Not Finish https://www.luogu.com.cn/problem/P5435
// MLE Memory Limit Exceeded.
fun main(args: Array<String>) {
    val mod = 998244353
    val n = readLine()!!.trim().toInt()
    val a = readLine()!!.trim().split(" ").map { it.toInt() }
    val b = readLine()!!.trim().split(" ").map { it.toInt() }

    val primes = getPrime(maxOf(a.maxOrNull()!!, b.maxOrNull()!!) + 5)
    val mapA = a.map {
        val map = HashMap<Int, Int>()
        var cur = it
        while (cur != 1) {
            if (primes[cur] == 1) {
                map[cur] = map.getOrDefault(cur, 0) + 1
                break
            }
            map[primes[cur]] = map.getOrDefault(primes[cur], 0) + 1
            cur /= primes[cur]
        }
        map
    }
    val mapB = b.map {
        val map = HashMap<Int, Int>()
        var cur = it
        while (cur != 1) {
            if (primes[cur] == 1) {
                map[cur] = map.getOrDefault(cur, 0) + 1
                break
            }
            map[primes[cur]] = map.getOrDefault(primes[cur], 0) + 1
            cur /= primes[cur]
        }
        map
    }

    for (i in 0 until n) {
        var res = 0L
        for (j in 0 until n) {
            var ans = 1L
            mapA[i].forEach { (key, value) ->
                val c = mapB[j].getOrDefault(key, 0)
                if (c != 0) {
                    ans *= quickPower(key.toLong(), minOf(c, value).toLong())
                    ans %= mod
                }
            }
            res += quickPower((i + 1).toLong(), (j + 1).toLong()) * ans
            res %= mod
        }
        println(res)
    }
}
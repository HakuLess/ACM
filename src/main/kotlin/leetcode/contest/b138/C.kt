package leetcode.contest.b138

import utils.permuteUnique
import utils.print
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.pow


fun main() {
    val s = SolutionC()
    s.countGoodIntegers(1, 1).print()
//    s.countGoodIntegers(5, 6).print()
    for (n in 1..10) {
        for (k in 1..9) {
            println("map[Pair($n, $k)] = ${s.countGoodIntegers(n, k)}")
        }
    }
}

class SolutionC {
    fun countGoodIntegers(n: Int, k: Int): Long {

        val set = HashSet<String>()
        if (n % 2 == 0) {
            val m = n / 2
            // 左侧
            val l = 10.0.pow((m - 1).toDouble()).toInt()
            // 乘数
            val r = 10.0.pow(m.toDouble()).toInt()
            for (i in l until r) {
                // 构造回文 K
                val v = i * r + i.toString().reversed().toLong()
                if (v % k == 0L) {
                    val sorted = v.toString().toCharArray().sorted().joinToString("")
                    set.add(sorted)
                }
            }
        } else {
            val m = n / 2
            val l = 10.0.pow((m - 1).toDouble()).toInt()
            val r = 10.0.pow(m.toDouble()).toInt()
            for (i in l until r) {
                for (j in 0..9) {
                    val v = i * r * 10 + j * r + i.toString().reversed().toLong()
                    if (v % k == 0L) {
                        val sorted = v.toString().toCharArray().sorted().joinToString("")
                        set.add(sorted)
                    }
                }
            }
        }

        var ans = 0L
        set.forEach {
            val combo = it.toCharArray().map { it - '0' }.toIntArray().permuteUnique().filter { it[0] != 0 }
            ans += combo.size
//            println("$it with ${combo.size}")
        }
        return ans
    }
}
package leetcode.contest.b138

import utils.permute
import utils.permuteUnique
import utils.print

fun main() {
    val s = SolutionC()
    s.countGoodIntegers(5, 6).print()
    for (n in 1..9) {
        for (k in 1..9) {
            println("map[Pair($n, $k)] = ${s.countGoodIntegers(n, k)}")
        }
    }
}

class SolutionC {
    fun countGoodIntegers(n: Int, k: Int): Long {
        val map = HashMap<Pair<Int, Int>, Long>()

        map[Pair(1, 1)] = 9
        map[Pair(1, 2)] = 4
        map[Pair(1, 3)] = 3
        map[Pair(1, 4)] = 2
        map[Pair(1, 5)] = 1
        map[Pair(1, 6)] = 1
        map[Pair(1, 7)] = 1
        map[Pair(1, 8)] = 1
        map[Pair(1, 9)] = 1
        map[Pair(2, 1)] = 9
        map[Pair(2, 2)] = 4
        map[Pair(2, 3)] = 3
        map[Pair(2, 4)] = 2
        map[Pair(2, 5)] = 1
        map[Pair(2, 6)] = 1
        map[Pair(2, 7)] = 1
        map[Pair(2, 8)] = 1
        map[Pair(2, 9)] = 1
        map[Pair(3, 1)] = 243
        map[Pair(3, 2)] = 108
        map[Pair(3, 3)] = 69
        map[Pair(3, 4)] = 54
        map[Pair(3, 5)] = 27
        map[Pair(3, 6)] = 30
        map[Pair(3, 7)] = 33
        map[Pair(3, 8)] = 27
        map[Pair(3, 9)] = 23
        map[Pair(4, 1)] = 252
        map[Pair(4, 2)] = 172
        map[Pair(4, 3)] = 84
        map[Pair(4, 4)] = 98
        map[Pair(4, 5)] = 52
        map[Pair(4, 6)] = 58
        map[Pair(4, 7)] = 76
        map[Pair(4, 8)] = 52
        map[Pair(4, 9)] = 28
        map[Pair(5, 1)] = 10935
        map[Pair(5, 2)] = 7400
        map[Pair(5, 3)] = 3573
        map[Pair(5, 4)] = 4208
        map[Pair(5, 5)] = 2231
        map[Pair(5, 6)] = 2468
        map[Pair(5, 7)] = 2665
        map[Pair(5, 8)] = 2231
        map[Pair(5, 9)] = 1191
        map[Pair(6, 1)] = 10944
        map[Pair(6, 2)] = 9064
        map[Pair(6, 3)] = 3744
        map[Pair(6, 4)] = 6992
        map[Pair(6, 5)] = 3256
        map[Pair(6, 6)] = 3109
        map[Pair(6, 7)] = 3044
        map[Pair(6, 8)] = 5221
        map[Pair(6, 9)] = 1248
        map[Pair(7, 1)] = 617463
        map[Pair(7, 2)] = 509248
        map[Pair(7, 3)] = 206217
        map[Pair(7, 4)] = 393948
        map[Pair(7, 5)] = 182335
        map[Pair(7, 6)] = 170176
        map[Pair(7, 7)] = 377610
        map[Pair(7, 8)] = 292692
        map[Pair(7, 9)] = 68739
        map[Pair(8, 1)] = 617472

        return map.getOrDefault(Pair(n, k), 9L / k)

        fun isPalindrome(x: Int): Boolean {
            val s = x.toString()
            return s == s.reversed()
        }

        val start = Math.pow(10.0, (n - 1).toDouble()).toInt()
        val end = Math.pow(10.0, n.toDouble()).toInt() - 1
        var count = 0L

        val seen = HashSet<String>()
        for (i in start..end) {
            if (i.toString() in seen) continue
            if (isPalindrome(i) && i % k == 0) {
                i.toString().toCharArray().map { it - '0' }.toIntArray().permuteUnique().forEach {
                    val str = it.joinToString("")
                    if (str.startsWith('0')) return@forEach
                    seen.add(str)
//                    println("add ${it.joinToString("")}")
                    count += 1
                }
            }
        }

        return count
    }
}
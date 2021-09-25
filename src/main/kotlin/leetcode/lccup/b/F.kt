package leetcode.lccup.b

import utils.print

fun main() {
    val s = SolutionF()
    s.ringGame(longArrayOf(5, 4, 6, 2, 7)).print()
//    s.ringGame(longArrayOf(12, 7, 11, 3, 9)).print()
//    s.ringGame(longArrayOf(1, 1, 1)).print()
}

class SolutionF {
    fun ringGame(challenge: LongArray): Long {
        val n = challenge.size
        val arr = challenge.mapIndexed { index, it ->
            minOf(it xor challenge[(index - 1 + n) % n], it xor challenge[(index + 1) % n])
        }
        println(arr.joinToString())
        var ans = Long.MAX_VALUE
        val l = ArrayList<Long>()
        for (i in challenge.indices) {
            l.add(maxOf(arr[i], challenge[i] or arr[i]))
            ans = minOf(ans, maxOf(arr[i], challenge[i] or arr[i]))
        }
        l.joinToString().print()
        return ans
    }
}
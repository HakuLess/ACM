package leetcode.contest.c390

import utils.print

fun main() {
    val s = SolutionB()
//    s.minOperations(11).print()
//    s.minOperations(1).print()
    s.minOperations(3).print()
    s.minOperations(2).print()
}

class SolutionB {
    fun minOperations(k: Int): Int {
        if (k == 1) return 0
        var ans = k
        for (i in 2..k) {
            // 将数组加到i
            // 复制a份
            val a = k / i + (if (k % i == 0) 0 else 1)
            println("$a with $i")
            ans = minOf(ans, i + a - 2)
        }
        return ans
    }
}